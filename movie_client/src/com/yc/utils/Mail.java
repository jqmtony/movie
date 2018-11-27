package com.yc.utils;

import java.util.List;
import java.util.Vector;

/**
 * �ʼ�
 * @author Administrator
 *
 */
public class Mail {
	private String fromMailAddress;	//�����˵������ַ
	private String toMailAddress;	//�ռ��˵������ַ  ����ö��Ÿ���
	private String mailSubject;		//�ʼ�����
	private String mailContent;		//�ʼ�����
	private String ccMailAddress;   //�����ʼ���ַ
	private String bccMailAddress;	//�����ʼ���ַ
	
	private List<AttachBean> attachBeanList;		//�������󼯺�
	
	public Mail(String fromMailAddress, String toMailAddress, String mailSubject, String mailContent) {
		super();
		this.fromMailAddress = fromMailAddress;
		this.toMailAddress = toMailAddress;
		this.mailSubject = mailSubject;
		this.mailContent = mailContent;
	}
	
	public String getFromMailAddress() {
		return fromMailAddress;
	}

	public String getToMailAddress() {
		return toMailAddress;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public List<AttachBean> getAttachBeanList() {
		return attachBeanList;
	}

	/**
	 * ��Ӹ�������
	 * @param attachBean ��������
	 */
	public void addAttachBean(AttachBean attachBean){
		if(attachBeanList == null)
			attachBeanList = new Vector<AttachBean>();
		attachBeanList.add(attachBean);
	}
	
	/**
	 * ��ӳ����ʼ���ַ
	 * @param ccMailAddress �����ʼ���ַ
	 */
	public void addCcMailAddress(String ccMailAddress){
		this.ccMailAddress = ccMailAddress;
	}
	
	/**
	 * ��Ӱ����ʼ���ַ
	 * @param ccMailAddress �����ʼ���ַ
	 */
	public void addBccMailAddress(String bccMailAddress){
		this.bccMailAddress = bccMailAddress;
	}

	public String getCcMailAddress() {
		return ccMailAddress;
	}

	public String getBccMailAddress() {
		return bccMailAddress;
	}
}
