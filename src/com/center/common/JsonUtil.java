package com.center.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.data.domain.Page;

/**
 *
 * @author long
 */
public class JsonUtil {
//    static public String ObjectToJson(String sFiler,Object o){
//        boolean isArray=true;
//        if(o==null) return "{}";
//        if(sFiler==null||sFiler.length()==0) return ObjectToJson(o);
//        Class cls=o.getClass();
//        Map context = new HashMap(); 
//        isArray=(cls.isArray() || o instanceof Collection);
//        if(isArray==false){
//            List ls= new ArrayList();
//            ls.add(o);
//            context.put("objs", ls);
//        }
//        else
//            context.put("objs", o);
//        try {
//            Object objmaps=Ognl.getValue("objs.{#"+sFiler+"}", context);
//            if(isArray==false)
//               return ObjectToJson (((Collection)objmaps).toArray()[0]);
//            else
//               return ObjectToJson(objmaps);
//        } catch (OgnlException ex) {
//            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//    static public Map StringToMap(String s) throws IOException{
//        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
//        return mapper.readValue(s, Map.class);
//    }
    static public Object ObjectToList(String sFiler,Object o){
        List resLs = new ArrayList();
        boolean isArray=true;
        if(o==null) return resLs;
        if(sFiler==null||sFiler.length()==0) return resLs;
        Class cls=o.getClass();
        Map context = new HashMap(); 
        isArray=(cls.isArray() || o instanceof Collection);
        if(isArray==false){
            List ls= new ArrayList();
            ls.add(o);
            context.put("objs", ls);
        }
        else
            context.put("objs", o);
        try {
            Object objmaps=Ognl.getValue("objs.{#"+sFiler+"}", context);
            if(isArray==false)
               return ((Collection)objmaps).toArray()[0];
            else
               return objmaps;
        } catch (OgnlException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
            return resLs;
        }
    }
//    static public String ObjectToJson(Object o){
//        ObjectMapper mapper = new ObjectMapper();
//        //mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
//        String json=null;
//        try {
//          json = mapper.writeValueAsString(o);
//        } catch (IOException ex) {
//            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return json;
//    }
//    static public String BuilNumberJson(Object o){
//        List ls=new ArrayList();
//        Page pg=(Page)o;
//        if(pg==null)  return ObjectToJson(ls);
//        for(int i=0;i<pg.getTotalPages();i++){
//            Map m = new HashMap(); 
//            m.put("val", i);
//            m.put("name", i+1);
//            ls.add(m);
//        }
//        return ObjectToJson(ls);
//    }
    static public String RandName(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer("dg");  
        for(int i = 0 ; i < 8; ++i){  
            int number = random.nextInt(62);//[0,62)  
            sb.append(str.charAt(number));  
        }  
        return sb.toString();  
    }
    
    static public String remoteHostInfo(HttpServletRequest request){
    	String clientIp = null;
    	if (request.getHeader("x-forwarded-for") == null) {  
			clientIp = request.getRemoteAddr();  
		}else  
			clientIp = request.getHeader("x-forwarded-for");
    	
    	return "ClientIP£º"+clientIp+"£¬RemoteHost£º"+request.getRemoteHost()+"£¬RemotePort£º"+
				request.getRemotePort()+"£¬RemoteUser£º"+request.getRemoteUser();
    }
}

