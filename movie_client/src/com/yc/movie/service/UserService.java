package com.yc.movie.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.mail.Session;

import com.yc.exception.UserException;
import com.yc.movie.bean.Integral;
import com.yc.movie.bean.UserLoginRecord;
import com.yc.movie.bean.Users;
import com.yc.movie.bean.Verify;
import com.yc.movie.dao.UserDao;
import com.yc.utils.CommonsUtils;
import com.yc.utils.JdbcUtils;

public class UserService {
	private UserDao ud = new UserDao();
	public static final int UPDATE_TYPE_PWD = 1;
	public static final int UPDATE_TYPE_REGISTER = 2;

	/**
	 * ע��
	 * @param user 
	 * @param userPwd2
	 * @param ura  �Ƿ�ѡ����ͬ��ע��Э��
	 * @throws UserException 
	 */
	public void register(Users user, String userPwd2,String ura) throws UserException {
		String userName = user.getUserName().trim();  //��ȡ�����������
		String userEmail = user.getUserEmail().trim();  //��ȡ�������Email
		String userPwd = user.getUserPwd().trim();  //��ȡ�����������
		// �ж������Ƿ�Ϊnull
		if(userName == null || userName.isEmpty())
			throw new UserException("������������");
		
		//�ж������Ƿ�Ϊnull
		if(userEmail == null || userEmail.isEmpty())
			throw new UserException("���������䣡");
		
		//�ж������Ƿ�Ϊnull
		if(userPwd == null || userPwd.isEmpty())
			throw new UserException("���������룡");
		
		//�ж�ȷ�������Ƿ�Ϊnull
		if(userPwd2 == null || userPwd2.trim().isEmpty())
			throw new UserException("������ȷ�����룡");
		
		//������ʽУ��
		if(!userName.matches(CommonsUtils.NAME_REGX))
			throw new UserException("������ʽ����ȷ��");
		
		//�����ʽУ��
		if(!userEmail.matches(CommonsUtils.EMAIL_REGX))
			throw new UserException("�����ʽ����ȷ��");
		
		//�����Ƿ��ѱ�ע��
		try {
			Users u = ud.findUserBySelectConf(new String[]{"userEmail"} ,userEmail);
			if(u != null)
				throw new UserException("�������ѱ�ע�ᣡ");
		} catch (SQLException e) {
			throw new UserException("ϵͳ�������Ժ����ԣ�");
		}
		
		//�����ʽУ��
		if(!userPwd.matches(CommonsUtils.PWD_REGX))
			throw new UserException("�����ʽ����ȷ��");
		
		//�ж������ȷ�������Ƿ���ͬ
		if(!userPwd.equals(userPwd2))
			throw new UserException("������������벻һ����");
		
		//�������
		user.setUserPwd(CommonsUtils.parseMD5(userPwd));
		
		//ע��
		try {
			JdbcUtils.beginTransaction();
			ud.insertUser(user); //�����¼
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("ϵͳ�������Ժ����ԣ�");
			}
		}
		
		
		//�����ʼ�
		//{0},��ã�������ע�᡾��Ӱ���á��˺ţ��������ȷ��ע�᣺<a href="http://{1}mail/registerSucceed.jsp">���������м���</a>
		String fileName = "register_user_email.properties";
		String to = userEmail;
		try {
			String addIp = CommonsUtils.getAddressAndProName(this.getClass());
			Object[] codes = {userName,addIp};
			CommonsUtils.sendMail(this.getClass(), to, codes, fileName);
		} catch (IOException e) {
			throw new UserException("ϵͳ����ϵͳ�ļ��ѱ��𻵣�");
		}
	}

	/**
	 * ��¼
	 * @param form
	 * @return
	 * @throws UserException 
	 */
	public Users login(Users form,UserLoginRecord ulr) throws UserException {
		//�õ����ֿ����˺����͵�ֵ 
		String userAccount = form.getUserAccount();
		String userEmail = form.getUserEmail();
		String userTel = form.getUserTel();
		
		String pwd = form.getUserPwd();  //�õ�����  ��δ���ܵģ�
		
		
		// �ж��˺��Ƿ�Ϊnull  Ϊnull�����쳣  ����Ͱ�ֵ���浽username��
		String username = null;
		if(userAccount == null || userAccount.isEmpty())
			if(userEmail == null || userEmail.isEmpty())
				if(userTel == null || userTel.isEmpty())
					throw new UserException("�˺Ų���Ϊ��");
				else username = userTel;
			else username = userEmail;
		else username = userAccount;
			
		//�ж������Ƿ�Ϊ��
		if(pwd == null || pwd.isEmpty())
			throw new UserException("���벻��Ϊ��");
		
		//�ж��˺Ÿ�ʽ�Ƿ���ȷ  ����ȷ�����쳣   ����Ͱ��˺����ͱ��浽selectConf��
		String selectConf = ""; 
		if(!username.matches(CommonsUtils.USERNAME_REGX))
			if(!username.matches(CommonsUtils.EMAIL_REGX))
				if(!username.matches(CommonsUtils.TEL_NUM_REGX))
					throw new UserException("�˺Ÿ�ʽ����");
				else selectConf = "userTel";
			else selectConf = "userEmail";
		else selectConf = "userAccount";
		
		Users user = null;
		try {
			//�ж��˺��Ƿ����
			user = ud.findUserBySelectConf(new String[]{selectConf},username);
			if(user == null)
				throw new UserException("���˺Ż�δע��");
			
			//�ж������Ƿ���ȷ
			pwd = CommonsUtils.parseMD5(pwd);  //����
			String[] selectConfs = {selectConf,"userPwd"};
			user = ud.findUserBySelectConf(selectConfs, username,pwd);
			if(user == null)
				throw new UserException("�������");
		} catch (SQLException e) {
			throw new UserException("ϵͳ�쳣�����Ժ�����");
		}
		
		//��ӵ�¼��־
		ulr.setUser(user);  //���õ�¼����
		try {
			JdbcUtils.beginTransaction(); //��������
			ud.insertULR(ulr);  //���
			JdbcUtils.commitTransaction(); //�ر�����
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction(); //�ع�
			} catch (SQLException e1) { 
				throw new UserException("ϵͳ�쳣�����Ժ�����");
			}  
		} 
		
		try {
			user = ud.createUser(user);//����Ӧ�ļ���װ��user������
		} catch (SQLException e) {
			throw new UserException("ϵͳ�쳣�����Ժ�����");
		}  
		
		//��ͨ���˾͵�¼�ɹ�  ���ص�ǰ��¼����
		return user;
	}


	/**
	 * �޸��û�
	 * @param form ������  userAccount  userPwd
	 * @param code	�ֻ���/����
	 * @param userPwd2	ȷ������
	 * @param verify	��֤�����
	 * @param type  ����   �޸�����/ע���û�
	 * @throws UserException 
	 */
	public void updateUser(Users form, String code, String userPwd2, Verify verify,int type) throws UserException {
		String account = form.getUserAccount();    //�õ��û���
		String userPwd = form.getUserPwd();        //�õ�����
		
		// �ж��û����Ƿ�Ϊ��
		if(account == null || account.isEmpty())
			throw new UserException("�������û���");
		
		//�ж��ֻ���/�����Ƿ�Ϊ��
		if(code == null || code.isEmpty())
			throw new UserException("�������ֻ���/����");
		
		//�ж������Ƿ�Ϊ��
		if(userPwd == null || userPwd.isEmpty())
			throw new UserException("����������");
		
		//�ж�ȷ�������Ƿ�Ϊ��
		if(userPwd2 == null || userPwd2.isEmpty())
			throw new UserException("������ȷ������");
		
		//�ж���֤���Ƿ�Ϊ��
		if(verify == null || verify.getInputVerify().isEmpty())
			throw new UserException("��������֤��");
		
		//�ж��û�����ʽ�Ƿ���ȷ
		if(!account.matches(CommonsUtils.USERNAME_REGX))
			throw new UserException("�û�����ʽ����ȷ");
		
		//�ж��ֻ���/�����ʽ�Ƿ���ȷ
		String selectCode = null;
		if(!code.matches(CommonsUtils.TEL_NUM_REGX))
			if(!code.matches(CommonsUtils.EMAIL_REGX))
				throw new UserException("�ֻ���/���� ��ʽ����ȷ");
			else{selectCode = "userEmail";form.setUserEmail(code);}
		else{selectCode = "userTel";form.setUserTel(code);}
		
		//�ж������ʽ�Ƿ���ȷ
		if(!userPwd.matches(CommonsUtils.PWD_REGX))
			throw new UserException("�����ʽ����ȷ");
		
		//�ж���֤���ʽ�Ƿ���ȷ
		if("userEmail".equals(selectCode) && !verify.getInputVerify().matches(CommonsUtils.VERIFY_EMAIL_REGX))
			if("userTel".equals(selectCode) && !verify.getInputVerify().matches(CommonsUtils.VERIFY_TEL_REGX))
				throw new UserException("��֤���ʽ����");
		Users user = null;
		try {
			//�ж��û����Ƿ����
			user = ud.findUserBySelectConf(new String[]{"userAccount"}, account);
			if(type == 1){  //�޸�����
				if(user == null)
					throw new UserException("���û���������");
			}else if(type == 2){  //ע��
				if(user != null)
					throw new UserException("���û����Ѵ���");
			}
			
			
			//�ж��ֻ���/�����Ƿ����
			user = ud.findUserBySelectConf(new String[]{selectCode}, code);
			if(type == 1){  //�޸�����
				if(user == null)
					throw new UserException("�ֻ���/���� �����ڻ�δ���û�");
			}else if(type == 2){  //ע��
				if(user != null)
					throw new UserException("�ֻ���/���� �ѱ������û���");
			}
			
			if(type == 1){  //�޸�����
				//�ж��û������ֻ���/�����Ƿ�ƥ��
				String[] selectConfs = {"userAccount",selectCode};
				Object[] params = {account,code};
				user = ud.findUserBySelectConf(selectConfs, params);
				if(user == null)
					throw new UserException("�ֻ���/���� ���û�����ƥ��");
			}
			
			//�ж������ȷ�������Ƿ���ͬ
			if(!userPwd.equals(userPwd2))
				throw new UserException("������������벻��ͬ");
				
			//�ж���֤���Ƿ���ȷ
			System.out.println(verify.getInputVerify()+"+"+verify.getCreateVerify());
			if(!verify.getInputVerify().equalsIgnoreCase(verify.getCreateVerify()))
				throw new UserException("��֤�벻��ȷ");
			
		} catch (SQLException e) {
			throw new UserException("ϵͳ�쳣�����Ժ����ԣ�");
		}
		
		//����ȷ  
		form.setUserPwd(CommonsUtils.parseMD5(userPwd));  //����
		
		try {
			JdbcUtils.beginTransaction();
			if(type == 1){  //�޸�����
				ud.alterPwd(form);
			}else if(type == 2){  //����û�
				form.setUserCreateTime(new Timestamp(new Date().getTime())); //���ô���ʱ��
				ud.insertUser(form);  //����û�
				Integral in = new Integral();  //�������ֿ�����
				in.setIntegralCount(0l);  //���ó�ʼ������
				in.setUser(form);  //���ö�Ӧ�û�
				ud.insertIntegral(in);  //������ֿ������ݿ�
			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
	}

	/**
	 * ������֤��
	 * @param code
	 * @param userAccount
	 * @return
	 * @throws UserException 
	 */
	public String sentVerify(String code, String account,int type) throws UserException {
		if(type == 1){ //�޸�������ʼ�
			//�ж��û����Ƿ�Ϊ��
			if(account == null || account.isEmpty())
				throw new UserException("������Ҫ�޸�������û���");
		}
		
		//�ж��ֻ���/�����Ƿ�Ϊ��
		if(code == null || code.isEmpty())
			throw new UserException("�����������֤����ֻ���/����");
		
		if(type == 1){
			//�ж��û�����ʽ�Ƿ���ȷ
			if(!account.matches(CommonsUtils.USERNAME_REGX))
				throw new UserException("�û�����ʽ����ȷ");
		}
		
		//�ж��ֻ���/�����ʽ�Ƿ���ȷ
		String selectCode = null;
		if(!code.matches(CommonsUtils.TEL_NUM_REGX))
			if(!code.matches(CommonsUtils.EMAIL_REGX))
				throw new UserException("�ֻ���/���� ��ʽ����ȷ");
			else selectCode = "userEmail";
		else selectCode = "userTel";
		
		Users user = null;
		try{
			if(type == 1){
				//�ж��û����Ƿ����
				user = ud.findUserBySelectConf(new String[]{"userAccount"}, account);
				if(user == null)
					throw new UserException("���û���������");
			}
			
			//�ж��ֻ���/�����Ƿ����
			user = ud.findUserBySelectConf(new String[]{selectCode}, code);
			if(type == 1){
				if(user == null)
					throw new UserException("�ֻ���/���� �����ڻ�δ���û�");
			}else if(type == 2){
				if(user != null)
					throw new UserException("�ֻ���/���� �ѱ������û���");
			}
			
			if(type == 1){
				//�ж��ֻ���/�����Ƿ���������û���ƥ��
				String[] selectConfs = {"userAccount",selectCode};
				Object[] params = {account,code};
	//			System.out.println(account+"+"+code);
				user = ud.findUserBySelectConf(selectConfs, params);
				if(user == null)
					throw new UserException("�ֻ���/���� ���û�����ƥ��");
			}
			
			//�����ֻ���֤��/������֤��
			String text = null;
			if("userEmail".equals(selectCode)){
				//����������֤��   
				text = CommonsUtils.createVerifyCode(4, CommonsUtils.VERIFY_CODE_TYPE_EMAIL); //������֤��
				String fileName = null;
				Object[] codes = null;
				if(type == 1){
					fileName = "alter_user_email.properties";
					codes = new Object[]{user.getUserName(),text};  //���ò�λ
				}else if(type == 2){
					fileName = "register_user_email.properties";
					codes = new Object[]{text};  //���ò�λ
				}
				String to = code; //�����ռ���
				CommonsUtils.sendMail(this.getClass(), to, codes,fileName );  //�����ʼ�
			}else if("userTel".equals(selectCode)){
				//�����ֻ���֤��
				text = CommonsUtils.createVerifyCode(6, CommonsUtils.VERIFY_CODE_TYPE_TEL);
				
			}
			return text;  //������֤��
		}catch(SQLException e){
			throw new UserException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}
}
