package com.yc.movie.utils;

import java.io.File;

/**
 * �ļ�����
 * @author Administrator
 *
 */
public class AttachBean {
	private File file;	//��������
	private String attachBeanName;	//������
	public AttachBean(File file, String attachBeanName) {
		super();
		this.file = file;
		this.attachBeanName = attachBeanName;
	}
	public File getFile() {
		return file;
	}
	public String getAttachBeanName() {
		return attachBeanName;
	}
}
