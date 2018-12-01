package com.yc.movie.merchant.service;

import java.io.IOException;
import java.sql.SQLException;

import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Verification;
import com.yc.movie.exception.MerchantException;
import com.yc.movie.merchant.dao.MerchantDao;
import com.yc.movie.utils.CommonsUtils;
import com.yc.movie.utils.JdbcUtils;

public class MerchantService {
	private MerchantDao md = new MerchantDao();

	/**
	 * ��¼
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException
	 */
	public Merchant login(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//�ж����ɵ���֤���Ƿ�Ϊnull
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//�ж��������֤���Ƿ�Ϊnull
			if(in == null || in.isEmpty())
				throw new MerchantException("��������֤�룡");
			
			//�ж���֤���Ƿ���ȷ
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("��֤���������");
		}
		
		//�ж��û����Ƿ�Ϊnull
		if(email == null || email.isEmpty())
			throw new MerchantException("�����������ַ��");
		
		//�ж������Ƿ�Ϊnull
		if(pwd == null || pwd.isEmpty())
			throw new MerchantException("���������룡");
		
		//�ж��û�����ʽ�Ƿ���ȷ
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("�����ʽ����");
		
		//�ж��û����Ƿ����
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me == null)
				throw new MerchantException("������δע�ᣡ");
			
			//�ж��û����������Ƿ�ƥ��
			pwd = CommonsUtils.parseMD5(pwd);
			me = md.findMerchantByEmailAndPwd(email,pwd);
			if(me == null)
				throw new MerchantException("�������");
			
			//��½�ɹ� ����һ���������̻�����
			me = md.createMerchant(me);
			return me;
		} catch (SQLException e) {
			throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * ע��
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException 
	 */
	public Merchant register(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//�ж����ɵ���֤���Ƿ�Ϊnull
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//�ж��������֤���Ƿ�Ϊnull
			if(in == null || in.isEmpty())
				throw new MerchantException("��������֤�룡");
			
			//�ж���֤���Ƿ���ȷ
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("��֤���������");
		}
		
		//�ж��û����Ƿ�Ϊnull
		if(email == null || email.isEmpty())
			throw new MerchantException("�����������ַ��");
		
		//�ж������Ƿ�Ϊnull
		if(pwd == null || pwd.isEmpty())
			throw new MerchantException("���������룡");
		
		//�ж��û�����ʽ�Ƿ���ȷ
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("�����ʽ����");
		
		//�ж��û����Ƿ����
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me != null)
				throw new MerchantException("�������ѱ�ע�ᣡ");
			
			//��֤�ɹ�
			form.setMerStatus("0");
			form.setMerPwd(CommonsUtils.parseMD5(form.getMerPwd()));
			
			//���ʼ�
			//���ã�������ע�᡾Ӱ�����á���̨�̻������˺ţ�<h3><a href="http://{0}mer.s?method=registerAfter">�������</a></h3>
			Object[] codes = {CommonsUtils.getAddressAndProName(this.getClass())};
			CommonsUtils.sendMail(this.getClass(), form.getMerEmail(), codes, "register_merchant_email.properties");
			return form;
		} catch(IOException e1){
			throw new MerchantException("ϵͳ�쳣��ϵͳ�ļ����𻵣�");
		}catch (SQLException e) {
			throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * �˺ż���
	 * @param me
	 * @throws MerchantException 
	 */
	public void registerAfter(Merchant me) throws MerchantException {
		try {
			me.setMerStatus("0");  //�̻��˺ŵĳ�ʼ��״̬Ϊ����   ����ʵ����֤��ſ���ʹ��
			JdbcUtils.beginTransaction();
			md.insertMerchant(me);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
	}

	/**
	 * �����޸��������ӵ�����
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException 
	 */
	public Merchant fogetBefore(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//�ж����ɵ���֤���Ƿ�Ϊnull
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//�ж��������֤���Ƿ�Ϊnull
			if(in == null || in.isEmpty())
				throw new MerchantException("��������֤�룡");
			
			//�ж���֤���Ƿ���ȷ
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("��֤���������");
		}
		
		//�ж��û����Ƿ�Ϊnull
		if(email == null || email.isEmpty())
			throw new MerchantException("�����������ַ��");
		
		//�ж��û�����ʽ�Ƿ���ȷ
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("�����ʽ����");
		
		//�ж��û����Ƿ����
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me == null)
				throw new MerchantException("������δע�ᣡ");
			
			//���ʼ�
			//�𾴵� ��{0}�����ã��������޸ġ�Ӱ�����á���̨�̻������˺ŵ����룺<h3><a href="http://{2}mer.s?method=registerAfter">����޸�</a></h3>
			Object[] codes = {email,CommonsUtils.getAddressAndProName(this.getClass())};
			CommonsUtils.sendMail(this.getClass(), form.getMerEmail(), codes, "register_forget_email.properties");
			return form;
		} catch(IOException e1){
			throw new MerchantException("ϵͳ�쳣��ϵͳ�ļ����𻵣�");
		}catch (SQLException e) {
			throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * �޸�����
	 * @param me
	 * @param pwd2
	 * @throws MerchantException 
	 */
	public void foget(Merchant me, String pwd2) throws MerchantException {
		String pwd1 = me.getMerPwd();
		//�ж������Ƿ�Ϊnull
		if(pwd1 == null || pwd1.isEmpty())
			throw new MerchantException("���������룡");

		//�ж�ȷ�������Ƿ�Ϊnull
		if(pwd2 == null || pwd2.isEmpty())
			throw new MerchantException("������ȷ�����룡");
		
		//�ж������ʽ�Ƿ���ȷ
		if(!pwd1.matches(CommonsUtils.PWD_REGX))
			throw new MerchantException("�����ʽ����ȷ��");
		
		//�ж������ȷ�������Ƿ���ͬ
		if(!pwd1.equals(pwd2))
			throw new MerchantException("������������벻��ͬ��");
		
		//����
		me.setMerPwd(CommonsUtils.parseMD5(pwd1));
		
		try {
			JdbcUtils.beginTransaction();
			//�޸�
			md.updateByPwd(me);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
	}
}
