package com.center.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.center.beans.SignInBean;
import com.center.beans.UsersBean;
import com.center.dao.SignInDao;
import com.center.dao.UsersDao;
import com.center.services.UsersService;
import com.center.utilitize.GlobalContants;
import com.center.utilitize.SqlExecuteException;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService{
	
	
	@Resource
	private UsersDao usersDao;
	@Resource
	private SignInDao signInDao;
	
	
	
	@Override
	public UsersBean selectUsersByEmail(String email) {
		return usersDao.selectUsersByEmail(email);
	}




	@Override
	public UsersBean checkLogin(Map<String, String> map) {
		// TODO Auto-generated method stub
		return usersDao.checkLogin(map);
	}




	@Override
	public List<UsersBean> select() {
		// TODO Auto-generated method stub
		return usersDao.select();
	}




	@Override
	public int insert(UsersBean userInfo) {
		// TODO Auto-generated method stub
		return usersDao.insert(userInfo);
	}




	@Override
	public void getCheckCode(String phone,HttpSession session) throws ApiException,
			SqlExecuteException {
		// TODO Auto-generated method stub
		TaobaoClient client = new DefaultTaobaoClient(GlobalContants.DAYU_URL,
				GlobalContants.APPKEY, GlobalContants.SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		Random random = new Random();
		String code = random.nextInt(9000) + 1000 + "";
		req.setExtend(code);
		req.setSmsType("normal");
		req.setSmsFreeSignName("wakeup俱乐");
		req.setSmsParamString("{\"code\":\"" + code + "\"}");
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_15525175");

		AlibabaAliqinFcSmsNumSendResponse rsp = (AlibabaAliqinFcSmsNumSendResponse) client
				.execute(req);
		rsp.getBody();
		session.setAttribute("code", code);
		session.setAttribute("phone",phone);
		
	}




	@Override
	public boolean checkYesterdayIfSigned(String phone) throws ParseException {
		// TODO Auto-generated method stub
		 Date time2=new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
	     Calendar   calendar   =   new   GregorianCalendar(); 
	     calendar.setTime(time2); 
	     calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动 
	     Date time1=calendar.getTime(); 
		SignInBean bean=this.signInDao.selectCerntainDayCertainUserSignInfo(phone, time1, time2);
		if(bean!=null)
			return true;
		return false;
	}
	

	@Override
	public List<UsersBean> selectInfoForRankTable() {
		// TODO Auto-generated method stub
		return this.usersDao.selectInfoForRankTable();
	}




	@Override
	public int incSeriesCount(String phone) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int addAccumulatePoint(String phone, int number) {
		// TODO Auto-generated method stub
		return 0;
	}









	

	


}
