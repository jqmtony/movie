package com.yc.movie.utils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

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

public class MailUtils {
	public static final String LOGINED_EMAIL_FILENAME = "loginedEmail.properties";
	/**
	 * ��ȡһ��javax.mail.Session��ʵ������
	 * @param mailHost	������������
	 * @param username	�û���(���������ַ)
	 * @param password	����(�ǵ�������¼��Ȩ��)
	 * @return	javax.mail.Session����
	 */
	public static Session createSession(String mailHost,String username,String password){
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
	public static void send(Session session,Mail mail){
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(mail.getFromMailAddress()));	//���÷�����
			msg.setRecipients(RecipientType.TO, mail.getToMailAddress());  //�����ռ���
			if(!mail.getCcMailAddress().isEmpty())
				msg.setRecipients(RecipientType.CC, mail.getCcMailAddress());  //���ó���
			if(!mail.getBccMailAddress().isEmpty())
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
	 * ����ע�ἤ���뵽����
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
	public static void send(Class thisClass,String to,Object[] code,String fileName){
		//��ȡ�����ļ�����
		Properties props = new Properties();
		try {
			props.load(thisClass.getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String host = props.getProperty("host");  //��ȡ����������
		String uname = props.getProperty("uname");  //��ȡ�����û���
		String pwd = props.getProperty("pwd");   //��ȡ��������¼��Ȩ����
		String from = props.getProperty("from");   //��ȡ������
//		String to = user.getEmail();  //��ȡ�ռ���
		String subject = props.getProperty("subject");  //��ȡ����
		String content = props.getProperty("content");  //��ȡ�ʼ�����
		if(code!=null && code.length>0)
			content = MessageFormat.format(content,code);  //�滻ռλ��
		Session session = MailUtils.createSession(host,uname,pwd);  //���Լ��Ĺ��߻�ȡsession
		Mail mail = new Mail(from,to,subject,content);  //�����ʼ�����
		MailUtils.send(session,mail);  //���ʼ�
	}
}
