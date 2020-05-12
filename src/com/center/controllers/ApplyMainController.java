package com.center.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.center.beans.CoreBean;
import com.center.beans.HtmlBean;
import com.center.beans.SignInBean;
import com.center.beans.UsersBean;
import com.center.dao.CoreDao;
import com.center.dao.HtmlDao;
import com.center.dao.SignInDao;
import com.center.dao.UsersDao;
import com.center.services.HtmlService;
import com.center.services.SignInInfoService;
import com.center.services.UsersService;
import com.center.utilitize.Dictionary;
import com.center.utilitize.JudgeSameDate;
import com.center.utilitize.MD5Untils;
import com.center.utilitize.SqlExecuteException;
import com.taobao.api.ApiException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
public class ApplyMainController {
	public static Logger logger = Logger.getLogger(ApplyMainController.class);
	@Resource
	private SignInInfoService signInInfoService;
	@Resource
	private UsersService usersService;
	@Resource
	private SignInDao SignInDao;
	
	@Resource
	private UsersDao usersDao;
	

	
	@RequestMapping("login.do")
	public String login(){
		return "login";
	}
	
	@RequestMapping("view")
	public String view(){
		return "view";
	}
	@RequestMapping("cp")
	public String cp(){
		return "createPage";
	}
	@Resource
	private HtmlDao htmlDao;

	
	@RequestMapping("pb")
	public @ResponseBody int pb(String creator, String code, String page_id, String topic, String url){

		HtmlBean htmlBean=new HtmlBean();
		htmlBean.setPage_id(page_id);
		htmlBean.setCode(code);
		htmlBean.setCreator(creator);
		htmlBean.setTopic(topic);
		htmlBean.setUrl(url);
		
		int i=htmlDao.insert(htmlBean);
		
		return i;
	}
	
	@Resource
	HtmlService htmlService;
	
	@RequestMapping("checkExistence")
	public @ResponseBody int checkExistence(String name){

		return htmlService.checkExistence(name);
	}
	
	@RequestMapping("updateByPageId")
	public @ResponseBody int updateByPageIdController(String creator, String code, String page_id, String new_page_id, String topic, String url){
		
		return htmlDao.updateByPageId(creator, code, page_id, new_page_id, topic, url);
	}
	
	@RequestMapping("deleteByPageId")
	public @ResponseBody int deleteByPageId(String page_id, HttpSession session){
		String cur_user=(String) session.getAttribute("login");
		
		System.out.println("aaaa");
		System.out.println(cur_user);
		System.out.println(usersDao.selectUsersByEmail(cur_user));
		System.out.println(page_id);
		System.out.println(htmlDao.selectByUserPageId(page_id));
		
		if(cur_user==null || usersDao.selectUsersByEmail(cur_user)==null || htmlDao.selectByUserPageId(page_id)==null){
			return 0;
		}
		System.out.println(page_id);
		String nickName=usersDao.selectUsersByEmail(cur_user).getNickName();
		String creator = htmlDao.selectByUserPageId(page_id).getCreator();
		
		
		
		if(creator !=null && nickName!=null ){
			
			if(nickName.equals(creator)){
				return htmlDao.deleteByPageId(page_id);
				
			}
			
		}
		
		return 0;
	}
	
	
	@RequestMapping("getPageCode")
	public  @ResponseBody String getPageCode(String page_id){
		HtmlBean htmlBean=new HtmlBean();
		htmlBean=htmlDao.selectByUserPageId(page_id);
		
		return htmlBean.getCode();
	}
	

	
	@RequestMapping("getInfoByCreator")
	public @ResponseBody JSONArray getInfoByCreator(String creator) throws UnsupportedEncodingException{
		List<HtmlBean> beans=htmlDao.selectByCreator(creator);
		JSONArray json=new JSONArray();
		
		for(HtmlBean tb : beans){
			JSONObject jobj=new JSONObject();
			jobj.put("topic", tb.getTopic());
			jobj.put("url", tb.getUrl());
			jobj.put("page_id", tb.getPage_id());
			json.add(jobj);
		}
		
		return json;
	}
	
	@RequestMapping("getInfoByPageId")
	public @ResponseBody JSONObject getInfoByPageId(String page_id){
		HtmlBean bean=htmlDao.selectByUserPageId(page_id);
		JSONObject jobj=new JSONObject();
		jobj.put("topic",bean.getTopic());
		jobj.put("code",bean.getCode());
		
		return jobj;
	}
	
	
	
	@RequestMapping("main")
	public String search(HttpServletRequest res){
		
		UsersBean user=usersService.selectUsersByEmail("749123292@qq.com");
		
		System.out.println(user);
		
		return "main";	
	}	
	
	
	
	@RequestMapping("check_sign_time")
	public @ResponseBody Integer check_sign_time(){
		Date date=new Date();
		int h=date.getHours();
		int m=date.getMinutes();
		if(h==6){
			return 1;
		}
		if(h==7 && m<=20){
			return 1;
		}
		return 0;
	}
	
	@RequestMapping("check_is_signed")
	public @ResponseBody Integer check_is_signed(HttpSession session){
		if(session.getAttribute("login")==null || session.getAttribute("login")=="")
			return 3;//session overtime
		boolean b=checkIfTodayHasSigned((String)session.getAttribute("login"));//session->'login'->phone
		if(b) return 1;
		return 0;
	}
	
	
	@RequestMapping("getSignCount")
	public @ResponseBody Integer getSignCount(HttpServletRequest res){
		int curCount=signInInfoService.selectByPhone((String)  res.getSession().getAttribute("login")).size();
		return curCount;
	}
	
	@RequestMapping("checkIfIsWord")
	public @ResponseBody boolean checkIfIsWord(String word, HttpSession session) throws IOException{
		String path = session.getServletContext().getRealPath("");
		Dictionary d=new Dictionary(path);
		return d.contains(word);
	}
	
	@Resource
	public CoreDao coreDao;
	
	
	@RequestMapping("atest")
	public @ResponseBody boolean atest() throws IOException{
		List<CoreBean> cbs=coreDao.selectByWord("in");
		for(CoreBean cb : cbs){
			System.out.println(coreDao.insert(cb));
		}
		return true;
	}
	
	@RequestMapping("insert_core")
	public @ResponseBody boolean insert_core(CoreBean cb, HttpSession session) throws IOException{
		String author=(String) session.getAttribute("login");
		cb.setAuthor(author);
		cb.setTime(new Date().toString());
		coreDao.insert(cb);
		System.out.println(cb);
		return true;
	}
	
	@RequestMapping("video")
	public String video(@RequestParam CommonsMultipartFile file, HttpSession session) throws Exception{
		
		String path = session.getServletContext().getRealPath("");
		String fileName=file.getOriginalFilename();
		path+="\\videos";
		
		
		byte[] barr=file.getBytes();
		BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(path+"\\"+fileName));
		bout.write(barr);
		bout.flush();
		bout.close();
		
		System.out.println(path+"\\"+fileName);
		
		return "search";
	}


	

	@RequestMapping("logout")
	public String logout(HttpServletRequest res){
		res.getSession().removeAttribute("login");
		res.getSession().invalidate();
		return "main";
	}
	
	@RequestMapping("check_login_status")
	public @ResponseBody int check_login_status(HttpServletRequest res){
		Object r=res.getSession().getAttribute("login");
		if(r!=null){
			return 1;
		}else{
			return 0;
		}
	}
	
	@RequestMapping("get_login_nickName")
	public @ResponseBody String get_login_nickName(HttpServletRequest res){
		String email=(String) res.getSession().getAttribute("login");
		return usersService.selectUsersByEmail(email).getNickName();
	}
	
	
	@RequestMapping("login")
	public @ResponseBody int getCountByPhone(HttpServletRequest request,HttpServletResponse response,String email,String password) throws ParseException{
		//System.out.println("login"+phone+"   "+password+"   "+request.getParameter("phone")+"   "+request.getHeaderNames()+"   "+request.getHeader("content-type"));
		System.out.println("in login");
		if(usersService.selectUsersByEmail(email)!=null){
			String rPwd=usersService.selectUsersByEmail(email).getPassword();
			String t=new MD5Untils().md5(password);
			System.out.println(t+"  "+rPwd);
			if(rPwd.equals(t)){                 //鐧婚檰鎴愬姛
				request.getSession().setAttribute("login", email);

				return 1;      
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	@RequestMapping("if_today_has_signed")
	public @ResponseBody int if_today_has_signed(HttpSession session){
		if(checkIfTodayHasSigned((String)session.getAttribute("login")))
			return 1;
		else return 0;
	}
	
	private boolean checkIfTodayHasSigned(String phone){
		List<SignInBean> list=signInInfoService.selectByPhone(phone);
		JudgeSameDate judge=new JudgeSameDate();
		for(SignInBean bean:list){
			if(judge.isToday(bean.getSign_in_time())){
				return true;
			}
		}
		return false;
	}
	
	
	
	
	@RequestMapping("getTodySignInfo")
	public @ResponseBody JSONArray getTodySignInfo(){
		JudgeSameDate judge=new JudgeSameDate();
		JSONArray json=new JSONArray();
		List<SignInBean> list=signInInfoService.select();
		List<SignInBean> re=new ArrayList<SignInBean>();
		JSONObject jobj=new JSONObject();
		UsersBean u_temp=new UsersBean();
		SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm");  
		Integer i=1;
//		for(SignInBean bean:list){
//			if(judge.isToday(bean.getSign_in_time())){
//				u_temp=usersService.selectUsersByEmail(bean.getUser_phone());             //鑾峰緱褰撳墠鐨勭敤鎴蜂俊鎭�
//				jobj.put("name", u_temp.getName());
//				jobj.put("time", dateformat.format(bean.getSign_in_time()).toString());
//				jobj.put("series_count", u_temp.getSeries_count());
//				jobj.put("accumulate_point", u_temp.getAccumulate_point());
//				json.add(jobj);
//			}
//		}
		return json;
	}
	

	
	

	@RequestMapping("getRankingInfo")
	public @ResponseBody List<JSONObject> getRankingInfo(){
		//JSONArray ja=new JSONArray();
		List<UsersBean> list=usersService.selectInfoForRankTable();
		List<JSONObject> ja=new ArrayList<JSONObject>();
		for(UsersBean bean:list){
//			JSONObject jo=new JSONObject();
//			jo.put("name",bean.getName());
//			jo.put("count",bean.getCount());
//			jo.put("points", bean.getAccumulate_point());
//			ja.add(jo);
		}
		return ja;	
	}
	
	@RequestMapping("getCheckCode")
	public @ResponseBody int getCheckCode(String phone,HttpSession session) throws ApiException, SqlExecuteException{
		if(phone!=null && phone!=""){
		this.usersService.getCheckCode(phone, session);
		return 1;
		}	
		return 0;
	}
	
	@RequestMapping("register")
	public @ResponseBody int register(String email,String password, String nickName, HttpSession session){
		System.out.println("in");
		UsersBean userInfo = new UsersBean();
		
		userInfo.setEmail(email);
		userInfo.setNickName(nickName);
		
		if(check_email_existed(email)){
			return 0;
		}
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		userInfo.setRegisterTime(new Date());
		String nPwd=new MD5Untils().md5(password);
		userInfo.setPassword(nPwd);
		int i=usersService.insert(userInfo);
		return 1;
	}
	
	//-----------------------------------------------
	
	public boolean check_email_existed(String email){
		UsersBean bean=usersService.selectUsersByEmail(email);
		if(bean==null)
			return false;
		
		return true;
	}
	
	private void addCookie(String phone, String password,HttpServletResponse response, HttpServletRequest request)  {  
	    if(phone!=null && password!=null){  
	        //鍒涘缓Cookie  
	        Cookie phoneCookie=new Cookie("phoneForWU",phone);  
	        Cookie pswCookie=new Cookie("pswForWU",password);  
	          
	        //璁剧疆Cookie鐨勭埗璺緞  
	        phoneCookie.setPath(request.getContextPath()+"/");  
	        pswCookie.setPath(request.getContextPath()+"/");  
	          
	        phoneCookie.setMaxAge(7*24*60*60);  
            pswCookie.setMaxAge(7*24*60*60);  
	   
	        //鍔犲叆Cookie鍒板搷搴斿ご  
	        response.addCookie(phoneCookie);  
	        response.addCookie(pswCookie);  
	    }  
	} 
	
	
	
}
