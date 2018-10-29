package com.cesi.actuatorfirst.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyParamRequestWrapper extends HttpServletRequestWrapper{

    private Map<String , String[]> params = new HashMap<String, String[]>();
	
	public MyParamRequestWrapper(HttpServletRequest request) {
		super(request);
		this.params.putAll(request.getParameterMap());
		// TODO Auto-generated constructor stub
	}
    @Override
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
        String[] values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }
    
    public String[] getParameterValues(String name) {//同上
         return params.get(name);
    }
 
   public void addAllParameters(Map<String , Object>otherParams) {//增加多个参数
        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
            addParameter(entry.getKey() , entry.getValue());
        }
    }
 
 
    public void addParameter(String name , Object value) {//增加参数
        if(value != null) {
        	if(value instanceof ArrayList){
        		String value1 = String.valueOf(value).substring(1,String.valueOf(value).length());
             	value = value1.substring(0,value1.length()-1);
             	params.put(name , new String[] {(String)value});
        	}
        	else if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    } 
}
