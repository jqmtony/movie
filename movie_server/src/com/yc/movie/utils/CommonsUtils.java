package com.yc.movie.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;

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

import org.apache.commons.fileupload.FileItem;

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
