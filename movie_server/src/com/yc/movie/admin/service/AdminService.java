package com.yc.movie.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.admin.bean.Verification;
import com.yc.movie.admin.dao.AdminDao;
import com.yc.movie.admin.exception.AdminException;
import com.yc.movie.utils.JdbcUtils;
import com.yc.movie.utils.CommonsUtils;

public class AdminService {
	private AdminDao ad = new AdminDao();
	
	
	/**
	 * ��¼ҵ��
	 * @param form	
	 * @return ��½�ɹ���Admins����(��Ϣ������)
	 */
	public Admins login(Admins form,Verification v) throws AdminException{
		try{
			//�����֤�벻Ϊnull���ж���֤���Ƿ���ȷ
			if(v != null){
				if(v.getInCode() == null || v.getInCode().trim().isEmpty())
					throw new AdminException("��������֤��!");
				if(!v.getInCode().equalsIgnoreCase(v.getText())){
					throw new AdminException("��֤�����!");
				}
			}
			
			//�û�����ʽ(����)��֤  ������ʽ
			if(form.getAdminEmail()==null || form.getAdminEmail().trim().isEmpty())
				throw new AdminException("�����������ַ!");
			if(!form.getAdminEmail().matches(CommonsUtils.EMAIL_REGX)){
				throw new AdminException("�����ʽ������:xxx@xxx.xxx!");
			}
			
			//����ad��findAdminByEmail()�����ж��û���(����)�Ƿ����
			Admins admin = ad.findAdminByEmail(form.getAdminEmail());
			if(admin == null){
				throw new AdminException("������δע�ᣬ����ȥע��!");
			}
			
			//����ad��findAdminByPwd()�����ж������Ƿ���ȷ
			admin = ad.findAdminByPwd(form.getAdminEmail(),form.getAdminPwd());
			if(admin == null){
				throw new AdminException("�������!");
			}
			return admin;
		}catch(SQLException e){
			throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
		}
		
	}

	/**
	 * ����¼��¼������ӵ����ݿ�
	 * @param alr
	 * @throws AdminException 
	 */
	public void addALR(AdminLoginRecord alr) throws AdminException {
		try {
			JdbcUtils.beginTransaction();  //��������
			
			ad.addALR(alr);	//����AdminDao��addALR()�������
			
			JdbcUtils.commitTransaction();  //�ύ����
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();  //�ع�
			} catch (SQLException e1) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			} 
		}  
	}
	/**
	 * ����Id���Admins����
	 * @param alterId
	 * @return
	 * @throws AdminException 
	 */
	public Admins findById(Long alterId) throws AdminException {
		try {
			return ad.findAdminById(alterId);
		} catch (SQLException e) {
			throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * ע�����Աʱʧ������У��  ajax
	 * @param form
	 * @param status
	 * @throws AdminException 
	 */
	public void registerBlur(Admins form, String status,String pwd2) throws AdminException {
		switch(status){
		case "4":
			//ע����
			//1.������ʽ�ж�  ����ȷ���쳣
			if(form.getAdminRegisterCode() == null || !form.getAdminRegisterCode().matches(CommonsUtils.REGISTER_CODE_REGX)){
				throw new AdminException("ע�����ʽ����ȷ��");
			}
			
			//2.�����ļ���ѯ   �ж��Ƿ���ڴ�ע����   �����ھ����쳣
			boolean flag = false;
			Properties p = new Properties();	//����Properties��  ������Ǽ����п��Ժ�IO�����������   ��Map����
			try {
				p.load(this.getClass().getClassLoader().getResourceAsStream("registerCode.properties"));  //���������ļ�
				Set<Object> keySet = p.keySet();	//p<Object,Object>   �õ�p��key����
				for(Object key:keySet){	//�������е�ע����
					String str = String.valueOf(p.get(key)); //ͨ����ȡֵ    �õ�һ��ע����
					if(str.equals(form.getAdminRegisterCode())){	//�ж��Ƿ���ͬ
						flag = true;
					}
				}
				if(flag == false){
					throw new AdminException("ע���벻���ڣ�");
				}
			} catch (IOException e) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			}
			
			//3.���ݿ��ѯ  �жϴ�ע�����Ƿ��ѱ�ʹ��  ����Ѿ���ʹ�þ����쳣
			try {
				Admins admin = ad.findAdminByRegisterCode(form.getAdminRegisterCode());
				if(admin != null){
					throw new AdminException("��ע�����ѱ�ʹ�ã�");
				}
			} catch (SQLException e) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			}
	
			break;
		case "1":
			//����
			//1.������ʽ�ж�  ����ȷ���쳣
			if(form.getAdminEmail() == null || !form.getAdminEmail().matches(CommonsUtils.EMAIL_REGX)){
				throw new AdminException("�����ʽ����ȷ��");
			}
			
			//2.��ѯ���ݿ�  �ж��Ƿ��Ѵ��ڴ�����  ����Ѵ��ھ����쳣
			try {
				Admins admin = ad.findAdminByEmail(form.getAdminEmail());
				if(admin != null){
					throw new AdminException("�������ѱ�ʹ�ã�");
				}
			} catch (SQLException e) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			}
			
			break;
		case "2":
			//����
			//1.������ʽ�ж�  ����ȷ���쳣
			if(form.getAdminPwd() == null || !form.getAdminPwd().matches(CommonsUtils.PWD_REGX)){
				throw new AdminException("�����ʽ����ȷ:��������ĸ���ֻ��»��ߣ�");
			}
			
			break;
		case "3":
			//ȷ������
			//1.�ж�ȷ�������Ƿ�����������ͬ   ����ͬ�����쳣
			if(pwd2 == null || !form.getAdminPwd().trim().equals(pwd2)){
				throw new AdminException("������������벻��ͬ��");
			}
			
			break;
		default:throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * ����Ա��¼֮ǰ��EmailУ��  ajaxʧ��
	 * @param adminEmail
	 * @throws AdminException 
	 */
	public void loginAdminEmailRegx(String adminEmail) throws AdminException {
		//�жϸ�ʽ�Ƿ���ȷ
		if(adminEmail==null || adminEmail.isEmpty())
			throw new AdminException("�����������ַ!");
		if(!adminEmail.matches(CommonsUtils.EMAIL_REGX))
			throw new AdminException("�����ʽ������:xxx@xxx.xxx!");
		try {
			Admins adm = ad.findAdminByEmail(adminEmail);
			if(adm == null)
				throw new AdminException("������δע�ᣬ����ȥע��!");
		} catch (SQLException e) {
			throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
		}
		
	}

	/**
	 * ע��
	 * @param form
	 * @throws AdminException 
	 */
	public void register(Admins form,String pwd2) throws AdminException {
		//1.����У��
		registerBlur(form,"1",pwd2);
		registerBlur(form,"2",pwd2);
		registerBlur(form,"3",pwd2);
		registerBlur(form,"4",pwd2);
		
		//2.�������
		form.setAdminPwd(CommonsUtils.parseMD5(form.getAdminPwd()));
		
		//3.����Ȩֵ
		//  ����ע����������Ȩֵ   ��������ĸ��ȷ����ʲô����Ա
		Integer str = Integer.parseInt(form.getAdminRegisterCode().substring(0, 1));  //�õ�����ĸ
		Long weight = 0l;
		switch(str){
		case 0:weight=10000l;break;
		case 1:weight=1000l;break;
		case 2:weight=100l;break;
		}
		form.setAdminWeight(weight);
		
		//4.����ע��ʱ��
		form.setAdminCreateTime(new Timestamp(new Date().getTime()));
		
		//5.ע������
		try {
			JdbcUtils.beginTransaction();	//��������
			
			ad.addAdmin(form);  //��admin��ӵ����ݿ�
			
			JdbcUtils.commitTransaction();  //�ύ����
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();  //�ع�
			} catch (SQLException e1) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
		
		//ע��ɹ�  ����ע��ɹ��ʼ�
		String to = form.getAdminEmail();  //�����ռ���
		Object[] codes = {form.getAdminEmail(),CommonsUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")};  //�滻��λ   ����Ա  ʱ��
		String fileName = "register_admin_email.properties";
		CommonsUtils.sendMail(this.getClass(), to, codes, fileName);
	}

	/**
	 * �޸�����֮ǰ��У��    ajax
	 * @param form
	 * @param status
	 * @param pwd2
	 * @throws AdminException 
	 */
	public void resetPwdBlur(Admins form, String status, String pwd2) throws AdminException {
		switch(status){
		case "4":
			//ע����
			//1.������ʽ�ж�  ����ȷ���쳣
			if(form.getAdminRegisterCode() == null || !form.getAdminRegisterCode().matches(CommonsUtils.REGISTER_CODE_REGX)){
				throw new AdminException("ע�����ʽ����ȷ��");
			}
			
			try {
				//2.ͨ��ע�����ѯ���ݿ����Ƿ����admin����   �����ھ����쳣
				Admins admin = ad.findAdminByRegisterCode(form.getAdminRegisterCode());
				if(admin == null){
					throw new AdminException("ע���벻���ڻ�δ��ʹ�ã�");
				}
				
				//3.ͨ�������ע����ͬʱ��ѯ
				if(form.getAdminEmail() == null)
					throw new AdminException("������д���䣡");
				admin = ad.findAdminByRegisterCodeAndEmail(form.getAdminRegisterCode(),form.getAdminEmail());
				if(admin == null)
					throw new AdminException("ע���������䲻ƥ�䣡");
			} catch (SQLException e) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			}
	
			break;
		case "1":
			//����
			//1.������ʽ�ж�  ����ȷ���쳣
			if(form.getAdminEmail() == null || !form.getAdminEmail().matches(CommonsUtils.EMAIL_REGX)){
				throw new AdminException("�����ʽ����ȷ��");
			}
			
			//2.��ѯ���ݿ�  �ж��Ƿ��Ѵ��ڴ�����  ����Ѵ��ھ����쳣
			try {
				Admins admin = ad.findAdminByEmail(form.getAdminEmail());
				if(admin == null){
					throw new AdminException("������δ��ע�ᣡ");
				}
			} catch (SQLException e) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			}
			
			break;
		case "2":
			//����
			//1.������ʽ�ж�  ����ȷ���쳣
			if(form.getAdminPwd() == null || !form.getAdminPwd().matches(CommonsUtils.PWD_REGX)){
				throw new AdminException("�����ʽ����ȷ:��������ĸ���ֻ��»��ߣ�");
			}
			
			break;
		case "3":
			//ȷ������
			//1.�ж�ȷ�������Ƿ�����������ͬ   ����ͬ�����쳣
			if(pwd2 == null || !form.getAdminPwd().trim().equals(pwd2)){
				throw new AdminException("������������벻��ͬ��");
			}
			
			break;
		default:throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * �޸�����
	 * @param form
	 * @param pwd2
	 * @throws AdminException 
	 */
	public void resetPwd(Admins form, String pwd2) throws AdminException {
		//1.����У��
		resetPwdBlur(form,"1",pwd2);
		resetPwdBlur(form,"2",pwd2);
		resetPwdBlur(form,"3",pwd2);
		resetPwdBlur(form,"4",pwd2);
		
		//2.�������
		form.setAdminPwd(CommonsUtils.parseMD5(form.getAdminPwd()));
		
		
		//3.�޸�����
		try {
			JdbcUtils.beginTransaction();	//��������
			
			ad.resetPwd(form.getAdminEmail(),form.getAdminPwd());  //�޸�����
			
			JdbcUtils.commitTransaction();  //�ύ����
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();  //�ع�
			} catch (SQLException e1) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
		
		//�޸ĳɹ�  �����ʼ�
		//localhost:8080/movie_server
		//
		String to = form.getAdminEmail();  //�����ռ���
		Object[] codes = {form.getAdminEmail(),CommonsUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss")};  //�滻��λ   ����Ա  ʱ��
		String fileName = "reset_password_email.properties";
		CommonsUtils.sendMail(this.getClass(), to, codes, fileName);
	}
}
