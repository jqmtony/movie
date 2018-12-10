package com.yc.movie.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.yc.movie.bean.Verification;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CommonsUtils {
	public static final String PWD_REGX = "[A-Za-z\\d.!@#$%^&*]{6,18}"; // ����
	public static final String USERNAME_REGX = "[A-Za-z][A-Za-z\\d_]{5,18}"; // �û���
	public static final String REGISTER_CODE_REGX = "[A-Z\\d]{4}[-][A-Z\\d]{4}[-][A-Z\\d]{4}[-][A-Z\\d]{4}"; // ע����
	public static final String NAME_REGX = "^(([\\u4e00-\\u9fa5]{2,8}))$"; // ����
	public static final String ID_NUM_REGX = "[1-9]\\d{16}[0-9X]"; // ���֤��
	public static final String QQ_NUM_REGX = "[1-9]\\d{4,10}"; // QQ��
	public static final String EMAIL_REGX = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$"; // ����
	public static final String AGE_REGX = "\\d{1,3}"; // ����
	public static final String SEX_REGX = "[��Ů]"; // �Ա�
	public static final String TEL_NUM_REGX = "[1][34578]\\d{9}"; // �ֻ�����
	public static final String IP_ADDR_AND_PRO_NAME = "address_and_projectName.properties";  //��Ŀ��IP��ַ����Ŀ�����ڵ������ļ�
	public static final String VERIFY_TEL_REGX = "[0-9]{6}";
	public static final String VERIFY_EMAIL_REGX = "[a-zA-Z0-9]{6}";
	public static final int VERIFY_CODE_TYPE_EMAIL = 1;
	public static final int VERIFY_CODE_TYPE_TEL = 2;
	private static String codes = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";
	public static Random ra = new Random(); // ���������
	private static String smsKey = "d41d8cd98f00b204e980";
	private static String smsUsername = "naivestruggle";
	private static String smsUrl = "http://utf8.api.smschinese.cn";
	
	/**
	 * �����õĿ�ȸ߶�ѹ��ͼƬ�ļ�<br> �ȱ���ԭ�ļ�����ѹ�����ϴ� 
     * @param oldFile  Ҫ����ѹ�����ļ�ȫ·�� 
     * @param newFile  ���ļ� 
     * @param width  ��� 
     * @param height �߶� 
     * @param quality ���� 
     * @return ����ѹ������ļ���ȫ·�� 
	 */
	public static String zipWidthHeightImageFile(File oldFile,File newFile, int width, int height) {  
		
		if (oldFile == null) {  
            return null;  
        }  
        String newImage = null;  
        try {  
            /** �Է������ϵ���ʱ�ļ����д��� */  
        	System.out.println("oldFile:"+oldFile.getAbsolutePath());
            Image srcFile = ImageIO.read(oldFile);  
            
            String srcImgPath = newFile.getAbsoluteFile().toString();
            System.out.println(srcImgPath);
            String subfix = "jpg";
    		subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".")+1,srcImgPath.length());
 
    		BufferedImage buffImg = null; 
    		if(subfix.equals("png")){
    			buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    		}else{
    			buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		}
 
    		Graphics2D graphics = buffImg.createGraphics();
    		graphics.setBackground(new Color(255,255,255));
    		graphics.setColor(new Color(255,255,255));
    		graphics.fillRect(0, 0, width, height);
    		graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);  
 
    		ImageIO.write(buffImg, subfix, new File(srcImgPath));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return newImage;  
    }
	
	/**
	 * ���ɶ�����
	 * @return
	 */
	public static String createIndentNum(){
		Date d = new Date();
		return dateFormat(d, "yyyyMMddHHmmss")+d.getTime();
	}
	/**
	 * ��iso8859����ת��Ϊutf-8
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getString_ISO8859_To_UTF8(String str) throws UnsupportedEncodingException{
		return new String(str.getBytes("iso-8859-1"),"utf-8");
	}
	
	
	/**
	 * ������е�ʡ
	 * @return
	 */
	public static Map<String, String> xmlProvince(Class c) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		// ����SaxReader����
		SAXReader saxReader = new SAXReader();
		// ͨ��saxReader�е�read()��ȡ��dom4j �ĵ����� Document
		org.dom4j.Document document = null;
		try {
			document = saxReader.read(c.getClassLoader().getResourceAsStream("Provinces.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// ��ȡ��Ԫ��
		org.dom4j.Element root = document.getRootElement();
		Iterator it = root.elementIterator();
		int i = 1;
		while (it.hasNext()) {
			org.dom4j.Element book = (org.dom4j.Element) it.next();
			map.put(i + "", book.getStringValue());
			i++;
		}
		return map;
	}

	/**
	 * ���ݳ��е�id��ȡ��
	 * @param key
	 * @param c
	 * @return
	 */
	public static Map<String,String> xmlDistricts(String key,Class c) {
		Map<String,String> map = new LinkedHashMap<String,String>();
		SAXReader saxReader = new SAXReader();
		org.dom4j.Document document = null;
		try {
			document = saxReader.read(c.getClassLoader().getResourceAsStream("Districts.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		org.dom4j.Element root = document.getRootElement();
		Iterator it = root.elementIterator();
		int i = 1;
		while (it.hasNext()) {
			org.dom4j.Element book = (org.dom4j.Element) it.next();
			List<Attribute> arrlist = book.attributes();
			for (Attribute attr : arrlist){
				if (attr.getName().equals("CID"))
					if (attr.getValue().equals(key)){
						map.put(i + "", book.getStringValue());
					}
			}
			i++;
		}
		return map;
	}

	/**
	 * ����ʡid��ȡ���е�Map
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String,String> xmlCities(String key,Class c) {
		Map<String,String> map = new LinkedHashMap<String,String>();
		SAXReader saxReader = new SAXReader();
		org.dom4j.Document document = null;
		try {
			document = saxReader.read(c.getClassLoader().getResourceAsStream("Cities.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		org.dom4j.Element root = document.getRootElement();
		Iterator it = root.elementIterator();
		int i = 1;
		while (it.hasNext()) {
			org.dom4j.Element book = (org.dom4j.Element) it.next();
			List<Attribute> arrlist = book.attributes();
			for (Attribute attr : arrlist)
				if (attr.getName().equals("PID"))
					if (attr.getValue().equals(key))
						map.put(i+"",book.getStringValue());
			i++;
		}
		return map;
	}

	/**
	 * ��ȡcity���е�һ�����ж�Ӧ��CID
	 * @param dvolue
	 * @return
	 */
	public static String xmlgetCitiesID(String dvolue,Class c) {
		SAXReader saxReader = new SAXReader();
		org.dom4j.Document document = null;
		try {
			document = saxReader.read(c.getClassLoader().getResourceAsStream("Cities.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		org.dom4j.Element root = document.getRootElement();
		Iterator it = root.elementIterator();
		while (it.hasNext()) {
			org.dom4j.Element book = (org.dom4j.Element) it.next();
			List<Attribute> arrlist = book.attributes();
			for (Attribute attr : arrlist)
				if (attr.getName().equals("ID"))
					if (book.getStringValue().equals(dvolue))
						return attr.getStringValue();
		}
		return "";
	}
	
	
	/**
	 * ���Ͷ���
	 * @param smsUrl
	 * @param userName
	 * @param key
	 * @param toTel
	 * @param content
	 * @throws IOException
	 * @throws HttpException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendTelCode(String toTel,String content){
		HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(smsUrl);  //"http://utf8.api.smschinese.cn"
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// ��ͷ�ļ�������ת��
        NameValuePair[] data = { new NameValuePair("Uid",smsUsername ),  //"naivestruggle"
                                 new NameValuePair("Key", smsKey), //"d41d8cd98f00b204e980"
                                 new NameValuePair("smsMob", toTel), //"15570906290"
                                 new NameValuePair("smsText", content) };//"��֤�룺8888"
        post.setRequestBody(data);

        try {
			client.executeMethod(post);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
//        System.out.println("statusCode:" + statusCode);
//        for (Header h : headers) {
//            System.out.println(h.toString());
//        }
        try {
			String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        System.out.println(result); // ��ӡ������Ϣ״̬

        post.releaseConnection();
	}
	
	
	/**
	 * ͨ��cookie����ȡrequest�е�cookie����   ���û�оͷ���null
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request,String cookieName){
		Cookie[] cookies = request.getCookies();
    	for(Cookie c:cookies)
    		if(c.getName().equals(cookieName))
    			return c;
    	return null;
	}
	/**
	 * ����һ������Ϊlen����֤���ı�
	 * @param len ��֤�볤��
	 * @param type ��֤������  ������֤��  �ֻ���֤��
	 * @return
	 */
	public static String createVerifyCode(int len,int type){
		StringBuilder sb = new StringBuilder();
		switch(type){
		case 1:
			for(int i=0;i<len;i++){
				int index = ra.nextInt(codes.length());
				sb.append(codes.charAt(index));
			}
			return sb.toString();
		case 2:
			for(int i=0;i<len;i++)
				sb.append(ra.nextInt(10));
			return sb.toString();
		default:throw new RuntimeException();
		}
	}
	/**
	 * �ϴ��ļ�  ����Ҫ���������ݿ��·��
	 * @param root	�洢�ļ��ĸ�Ŀ¼
	 * @param fi	�ļ��������
	 * @return	���������ݿ��·��
	 * @throws Exception
	 */
	public static String uploadFile(String root, FileItem fi) throws Exception {
		//�õ��ļ���
		String filename = fi.getName();
		
		//�����ļ����ľ���·������
		int index = filename.lastIndexOf("\\");
		if(index != -1){
			filename = filename.substring(index+1);
		}
		
		//�����ļ�ͬ�����⣬���ļ��������uuidǰ׺��
		String savename = getUUID() + "_" + filename;
		
		//�õ�hashCode
		int hCode = filename.hashCode();
		//ת����16����
		String hex = Integer.toHexString(hCode);
		
		//��ȡhex��ǰ������ĸ����root������һ������һ��������·��
		File dirFile = new File(root,"/"+hex.charAt(0)+"/"+hex.charAt(1));
		
		//����Ŀ¼��
		dirFile.mkdirs();
		
		//����Ŀ���ļ�
		File destFile = new File(dirFile,savename);
		
//					File sqlFile = new File("/WEB-INF/files","/"+hex.charAt(0)+"/"+hex.charAt(1));
		String sqlPath = dirFile+"\\"+savename;
		
		
		//����
		fi.write(destFile);
		return sqlPath;
	}
	
	/**
	 * Part�ϴ�ͼƬ
	 * @param root
	 * @param part
	 * @return
	 */
	public static String uploadImage(HttpServletRequest request,String root,Part part,int width,int height){
		String s1 = root;
		root = request.getServletContext().getRealPath(root);
		//�õ��ļ���
		String filename = part.getSubmittedFileName();
		
		//�����ļ����ľ���·������
		int index = filename.lastIndexOf("\\");
		if(index != -1){
			filename = filename.substring(index+1);
		}
		
		//�����ļ�ͬ�����⣬���ļ��������uuidǰ׺��
		String savename = getUUID() + "_" + filename;
		
		//�õ�hashCode
		int hCode = filename.hashCode();
		//ת����16����
		String hex = Integer.toHexString(hCode);
		
		//��ȡhex��ǰ������ĸ����root������һ������һ��������·��
		String s = "/"+hex.charAt(0)+"/"+hex.charAt(1);
		File dirFile = new File(root,s);
		
		//����Ŀ¼��
		dirFile.mkdirs();
		
		//����Ŀ���ļ�
		File destFile = new File(dirFile,savename);
		if(!destFile.exists())
			try {
				destFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//					File sqlFile = new File("/WEB-INF/files","/"+hex.charAt(0)+"/"+hex.charAt(1));
		String sqlPath = s1+s+"/"+savename;
		if(sqlPath.contains("movie_server")){
			sqlPath = sqlPath.replace("movie_server", "movie_client");
		}else if(sqlPath.contains("movie_client")){
			sqlPath = sqlPath.replace("movie_client", "movie_server");
		}
		
		
		//����
		try {
			part.write(dirFile+"/"+savename);
//			System.out.println("��һ���ϴ���Ŀ¼��"+dirFile.getAbsolutePath()+"/"+savename);
			
			//ѹ��ͼƬ
			CommonsUtils.zipWidthHeightImageFile(destFile, destFile, width, height);
			
			//����ͼƬ
			String newFileDir = dirFile.getAbsolutePath().replace("movie_server", "movie_client");
			File f = new File(newFileDir);
			f.mkdirs(); //����Ŀ¼��
			cloneFile(new File(dirFile.getAbsolutePath()+"\\"+savename), new File(f,savename));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlPath;
	}
	
	/**
	 * Part�ϴ��ļ�
	 * @param root
	 * @param part
	 * @return
	 */
	public static String uploadFile(HttpServletRequest request,String root,Part part){
		String s1 = root;
		root = request.getServletContext().getRealPath(root);
		//�õ��ļ���
		String filename = part.getSubmittedFileName();
		
		//�����ļ����ľ���·������
		int index = filename.lastIndexOf("\\");
		if(index != -1){
			filename = filename.substring(index+1);
		}
		
		//�����ļ�ͬ�����⣬���ļ��������uuidǰ׺��
		String savename = getUUID() + "_" + filename;
		
		//�õ�hashCode
		int hCode = filename.hashCode();
		//ת����16����
		String hex = Integer.toHexString(hCode);
		
		//��ȡhex��ǰ������ĸ����root������һ������һ��������·��
		String s = "/"+hex.charAt(0)+"/"+hex.charAt(1);
		File dirFile = new File(root,s);
		
		//����Ŀ¼��
		dirFile.mkdirs();
		
		//����Ŀ���ļ�
		File destFile = new File(dirFile,savename);
		if(!destFile.exists())
			try {
				destFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//					File sqlFile = new File("/WEB-INF/files","/"+hex.charAt(0)+"/"+hex.charAt(1));
		String sqlPath = s1+s+"/"+savename;
		sqlPath = sqlPath.replace("movie_server", "movie_client");
		
		//����
		try {
			part.write(dirFile+"/"+savename);
//			System.out.println("��һ���ϴ���Ŀ¼��"+dirFile.getAbsolutePath()+"/"+savename);
			
			//����ͼƬ
			String newFileDir = dirFile.getAbsolutePath().replace("movie_server", "movie_client");
			File f = new File(newFileDir);
			f.mkdirs(); //����Ŀ¼��
			cloneFile(new File(dirFile.getAbsolutePath()+"\\"+savename), new File(f,savename));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlPath;
	}
	
	/**
	 * �ϴ��ļ�  ����Ҫ���������ݿ��·��
	 * @param root	�洢�ļ��ĸ�Ŀ¼
	 * @return	���������ݿ��·��
	 * @throws Exception
	 */
	public static String uploadFile(String root, File file){
		
		//�õ��ļ���
		String filename = file.getName();
		
		//�����ļ����ľ���·������
		int index = filename.lastIndexOf("\\");
		if(index != -1){
			filename = filename.substring(index+1);
		}
		
		//�����ļ�ͬ�����⣬���ļ��������uuidǰ׺��
		String savename = getUUID() + "_" + filename;
		
		//�õ�hashCode
		int hCode = filename.hashCode();
		//ת����16����
		String hex = Integer.toHexString(hCode);
		
		//��ȡhex��ǰ������ĸ����root������һ������һ��������·��
		File dirFile = new File(root,"/"+hex.charAt(0)+"/"+hex.charAt(1));
		
		//����Ŀ¼��
		dirFile.mkdirs();
		
		//����Ŀ���ļ�
		File destFile = new File(dirFile,savename);
		
//					File sqlFile = new File("/WEB-INF/files","/"+hex.charAt(0)+"/"+hex.charAt(1));
		String sqlPath = dirFile+"\\"+savename;
		
		
		//����  //����
		cloneFile(file,destFile);
		return sqlPath;
	}
	
	/**
	 * �����ļ�
	 * @param oldFile
	 * @param newFile
	 */
	public static void cloneFile(File oldFile,File newFile){
		if(!oldFile.exists())
			throw new RuntimeException("û���ҵ�Դ�ļ�");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			if(!newFile.exists())
				newFile.createNewFile();
				
			fis = new FileInputStream(oldFile);
			fos = new FileOutputStream(newFile);
			byte[] buf = new byte[fis.available()];
			int len = 0;
			while((len = fis.read(buf)) != -1){
				fos.write(buf, 0, len);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				if(fis != null)
					fis.close();
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * �Ƴ�cookie
	 * @param request
	 * @param response
	 * @param cookieName	cookie������
	 */
	public static void removeCookie(HttpServletRequest request,HttpServletResponse response,String cookieName){
		Cookie[] cookies = request.getCookies();  //�õ���ǰ��������е�����cookie
		for(Cookie cookie:cookies){  //����
			if(cookie.getName().equals(cookieName)){
				cookie.setMaxAge(0);
				cookie.setValue("");
				response.addCookie(cookie);
			}
		}
	}
	/**
	 * �������ļ��л�ȡ��Ŀ��IP��ַ����Ŀ��
	 * @return	localhost:8080/movie_server/
	 * @throws IOException 
	 */
	public static String getAddressAndProName(Class thisClass) throws IOException{
		Properties p = new Properties();
		p.load(thisClass.getClassLoader().getResourceAsStream(IP_ADDR_AND_PRO_NAME));
		String addr = p.getProperty("address");
		String proName = p.getProperty("projectName");
		return addr+proName;
	}
	/**
	 * ����ָ���������ַ��������룬��������ֵ
	 * @param dateString	2018-01-01
	 * @param mask	yyyy-MM-dd
	 * @return	�������ڶ���
	 * @throws ParseException 
	 */
	public static Date dateFormat(String dateString,String mask){
		try {
			return new SimpleDateFormat(mask).parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ����ָ�������ڶ�������룬���ظ�ʽ����������ַ���
	 * @param date	���ڶ���
	 * @param mask	yyyy-MM-dd
	 * @return	���������ַ���
	 */
	public static String dateFormat(Date date,String mask) {
		return new SimpleDateFormat(mask).format(date);
	}
	
	/**
	 * �Դ����ַ�������BASE64���벢����
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String encodeByBASE64(String str) throws IOException{
		return new BASE64Encoder().encode(str.getBytes("UTF-8"));
	}
	
	/**
	 * �Դ����ַ�������BASE64���벢����
	 * @param str
	 * @return
	 * @throws IOException 
	 */
	public static String decodeByBASE64(String str) throws IOException{
		return new String(new BASE64Decoder().decodeBuffer(str),"UTF-8");
	}
	
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
				case "java.math.BigDecimal":
					me.invoke(t, BigDecimal.valueOf(Double.valueOf(params)));
					break;
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
				String params = String.valueOf(map.get(attrName));
				if(params == null)
					continue g1;
				try {
					g2:switch(attrType){
					case "java.lang.Double":
						me.invoke(bean, Double.parseDouble(params));
						break g2;
					case "java.lang.Integer":
						me.invoke(bean, Integer.parseInt(params));
						break g2;
					case "java.lang.String":
						me.invoke(bean, params); 
						break g2;
					case "java.lang.Long":
						me.invoke(bean, Long.parseLong(params)); 
						break g2;
					case "java.sql.Date":
						DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						me.invoke(bean, new java.sql.Date(dateformat.parse(params).getTime())); 
						break g2;
					case "java.sql.Timestamp":
						me.invoke(bean,Timestamp.valueOf(params)); 
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
		return bean;
	}
	
	/**
	 * �õ�pc
	 * @param request
	 * @return
	 */
	public static int getPageListPc(HttpServletRequest request) {
		String pc = request.getParameter("pc");
		if(pc == null || pc.isEmpty()){
			pc = "1";
		}
		return Integer.parseInt(pc);
	}
	
	/**
	 * ��ȡһ��javax.mail.Session��ʵ������
	 * @param mailHost	������������
	 * @param username	�û���(���������ַ)
	 * @param password	����(�ǵ�������¼��Ȩ��)
	 * @return	javax.mail.Session����
	 */
	public static Session createMailSession(String mailHost,String username,String password){
		Properties prop = new Properties();
		prop.setProperty("mail.host", mailHost);	//���÷�����������
		prop.setProperty("mail.smtp.auth", "true");	//������Ҫ��֤
		
		Authenticator auth = new Authenticator(){
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username,password);  //�û���������
			}
		};
		return Session.getInstance(prop,auth);
	}
	
	/**
	 * �����ʼ�
	 * @param session javax.mail.Session����
	 * @param mail	Ҫ���͵��ʼ�����
	 */
	public static void sendMail(Session session,Mail mail){
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(mail.getFromMailAddress()));	//���÷�����
			msg.setRecipients(RecipientType.TO, mail.getToMailAddress());  //�����ռ���
			if(mail.getCcMailAddress()!= null && !mail.getCcMailAddress().isEmpty())
				msg.setRecipients(RecipientType.CC, mail.getCcMailAddress());  //���ó���
			if(mail.getBccMailAddress() !=null && !mail.getBccMailAddress().isEmpty())
				msg.setRecipients(RecipientType.BCC, mail.getBccMailAddress());  //���ð���
			msg.setSubject(mail.getMailSubject());	//��������
			
			List<AttachBean> fileBodyList = mail.getAttachBeanList();  //�õ��ļ���������
			
			if(fileBodyList == null){ //���û���ļ�����
				msg.setContent(mail.getMailContent(),"text/html;charset=utf-8");	//��������
			}else{	//������ļ�����
				MimeMultipart list = new MimeMultipart();  //�����ಿ�����ݼ���
				
				MimeBodyPart part1 = new MimeBodyPart(); //����MimeBodyPart
				part1.setContent(mail.getMailContent(),"text/html;charset=utf-8"); //�������Ĳ���������
				list.addBodyPart(part1); //�����Ĳ�����ӵ�������
				
				for(AttachBean ab:fileBodyList){
					MimeBodyPart part = new MimeBodyPart();	//����MimeBodyPart
					part.attachFile(ab.getFile());	//���ø���������
					part.setFileName(MimeUtility.encodeText(ab.getAttachBeanName()));  //������ʾ���ļ����ƣ���������������
					list.addBodyPart(part);	//��������ӵ�����������
				}
				
				msg.setContent(list);	//�������ø��ʼ���Ϊ�ʼ�������
			}
			
			Transport.send(msg); //����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ͨ�������ļ������ʼ�
	 * @param c	��ǰ��
	 * @param to	���͵�
	 * @param code	������
	 * host=smtp.163.com   //������
		uname=naivestruggle    //�����û���
		pwd=QQ981214		//�����������¼��Ȩ��
		from=naivestruggle@163.com //�����ַ
		subject=����һ���ʼ�
		content=<a href="http://localhost:8080/Blog_Server/UserServlet?method=active&code={0}">���������ɼ���</a>  //{0} �Ǵ���ռλ
	 */
	public static void sendMail(Class thisClass,String to,Object[] codes,String fileName){
		//��ȡ�����ļ�����
		Properties props = new Properties();
		try {
			props.load(thisClass.getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String host = props.getProperty("host").trim();  //��ȡ����������
		String uname = props.getProperty("uname").trim();  //��ȡ�����û���
		String pwd = props.getProperty("pwd").trim();   //��ȡ��������¼��Ȩ����
		String from = props.getProperty("from").trim();   //��ȡ������
		String subject = props.getProperty("subject").trim();  //��ȡ����
		String content = props.getProperty("content");  //��ȡ�ʼ�����
		if(codes!=null && codes.length>0)
			content = MessageFormat.format(content,codes);  //�滻ռλ��
		Mail mail = new Mail(from,to,subject,content);  //�����ʼ�����
		Session session = createMailSession(host,uname,pwd);  //���Լ��Ĺ��߻�ȡsession
		sendMail(session,mail);  //���ʼ�
	}
	
	/**
	 * MD5����
	 * @param inStr ��Ҫ���ܵ��ַ���
	 * @return һ��32λ������
	 */
	public static String parseMD5(String inStr){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("����ʧ��");
		}
		char[] charArr = inStr.toCharArray();
		byte[] byteArr = new byte[charArr.length];
		for(int i=0;i<charArr.length;i++){
			byteArr[i] = (byte) charArr[i];
		}
		
		byte[] md5Bytes = md5.digest(byteArr);
		
		StringBuilder hexValue = new StringBuilder();
		
		for(int i=0;i<md5Bytes.length;i++){
			int val = (int)md5Bytes[i] & 0xff;
			if(val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
	}
	
	/**
	 * ��ȡһ��32λ�Ĳ��ظ����ַ���
	 * 	�ַ������������д��ĸ���
	 * @return
	 */
	public static String getUUID() {
		return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
