package com.yc.movie.bean;

import java.io.Serializable;

/**
 * ���ͱ�
 * @author yxh
 *
 */
public class Classifys implements Serializable {
	private Long classifyId;  //����ID
	private Movies movie;  //��Ӧ��Ӱ
	private Teleplay teleplay; //��Ӧ���Ӿ�
	private String classifyName; //������
	private String classifyDescribe; //��������
	private Classifys parentClassify; //������
	public Long getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Long classifyId) {
		this.classifyId = classifyId;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovie(Movies movie) {
		this.movie = movie;
	}
	public Teleplay getTeleplay() {
		return teleplay;
	}
	public void setTeleplay(Teleplay teleplay) {
		this.teleplay = teleplay;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public String getClassifyDescribe() {
		return classifyDescribe;
	}
	public void setClassifyDescribe(String classifyDescribe) {
		this.classifyDescribe = classifyDescribe;
	}
	public Classifys getParentClassify() {
		return parentClassify;
	}
	public void setParentClassify(Classifys parentClassify) {
		this.parentClassify = parentClassify;
	}
	@Override
	public String toString() {
		return "Classifys [classifyId=" + classifyId + ", movie=" + movie + ", teleplay=" + teleplay + ", classifyName="
				+ classifyName + ", classifyDescribe=" + classifyDescribe + ", parentClassify=" + parentClassify + "]";
	}
	
}
