package com.yc.movie.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public class FormUtils {
	/**
	 * ����ͨform��ת����javaBean����
	 * @param request	�������
	 * @param c	����ת��������
	 * @return	����һ��javaBean
	 * @throws Exception
	 */
	public static <T> T toBean(HttpServletRequest request,Class<T> c){
		T t = null;
		try {
			t = (T)c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Method[] methods = t.getClass().getDeclaredMethods();
		Vector<Method> v = new Vector<Method>();
		for(int i=0;i<methods.length;i++){
			String methodName = methods[i].getName();
			if(!"set".equals(methodName.substring(0, 3))){
				//��Щ����set����
				continue;
			}
			//ʣ�µ���set����
			v.add(methods[i]);
		}
		g1:for(Method me:v){
			//��������ת��Ϊ  ������
			String s  = me.getName().substring(3);   //ȥ��set��   Defghi
			String first = s.substring(0,1).toLowerCase();   //��һλСд  d
			String attrName = first + s.substring(1);  	//������  defghi
			
			//�ҵ����Ե�����
			String attrType = me.getParameterTypes()[0].getName();  //java.lang.String
			String params = request.getParameter(attrName);
			if(params == null)
				continue g1;
			try {
				g2:switch(attrType){
				case "java.lang.Integer":
					me.invoke(t, Integer.parseInt(params));
					break g2;
				case "java.lang.String":
					me.invoke(t, params); 
					break g2;
				case "java.lang.Long":
					me.invoke(t, Long.parseLong(params)); 
					break g2;
				case "java.sql.Date":
					DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					me.invoke(t, new java.sql.Date(dateformat.parse(params).getTime())); 
					break g2;
				case "java.sql.Timestamp":
					me.invoke(t,Timestamp.valueOf(params)); 
					break g2;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}
		return t;
	}
	
	/**
	 * ��MapתΪjavabean
	 * @param map
	 * @param c
	 * @return
	 */
	public static <T> T toBean(Map<String, Object> map , Class<T> c){
		T bean = null;  //User user = null
		try {
			bean = c.newInstance();  //����һ��javabeanʵ��   //user = new User();
			Method[] methods = c.getMethods();  //�õ�ʵ����������й��з���
			Vector<Method> v = new Vector<Method>();
			for(int i=0;i<methods.length;i++){
				String methodName = methods[i].getName();
				if(!"set".equals(methodName.substring(0, 3))){
					//��Щ����set����
					continue;
				}
				//ʣ�µ���set����
				v.add(methods[i]);
			}
			
			g1:for(Method me:v){
				//��������ת��Ϊ  ������
				String s  = me.getName().substring(3);   //ȥ��set��   Defghi
				String first = s.substring(0,1).toLowerCase();   //��һλСд  d
				String attrName = first + s.substring(1);  	//������  defghi
				
				//�ҵ����Ե�����
				String attrType = me.getParameterTypes()[0].getName();  //java.lang.String
				Object params = map.get(attrName);
				if(params == null)
					continue g1;
				try {
					g2:switch(attrType){
					case "java.lang.Integer":
						me.invoke(bean, params);
						break g2;
					case "java.lang.String":
						me.invoke(bean, params); 
						break g2;
					case "java.lang.Long":
						me.invoke(bean, params); 
						break g2;
					case "java.sql.Date":
						DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						me.invoke(bean, new java.sql.Date(dateformat.parse(String.valueOf(params)).getTime())); 
						break g2;
					case "java.sql.Timestamp":
						me.invoke(bean,Timestamp.valueOf(String.valueOf(params))); 
						break g2;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �õ�pc
	 * @param request
	 * @return
	 */
	public static int getPc(HttpServletRequest request) {
		String pc = request.getParameter("pc");
		if(pc == null || pc.isEmpty()){
			pc = "1";
		}
		return Integer.parseInt(pc);
	}
}