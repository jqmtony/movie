package com.yc.movie.bean;

import java.io.Serializable;

/**
 * ���ͱ�
 * @author yxh
 *
 */
public class Classifys implements Serializable {
	private Long classifyId;  //����ID
	private Long classifyMovieId; //��Ӧ��ӰID
	private Long classifyTeleplayId;  //��Ӧ���Ӿ�ID
	private Movies movie;  //��Ӧ��Ӱ
	private Teleplay teleplay; //��Ӧ���Ӿ�
	private String classifyNameString; //������
	private String classifyDescribe; //��������
	private Long classifyName;  //����ID
	private ClassifyName classifyNameObj; //���Ͷ���
	private Classifys parentClassify; //������
	public Long getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Long classifyId) {
		this.classifyId = classifyId;
	}
	public Long getClassifyMovieId() {
		return classifyMovieId;
	}
	public void setClassifyMovieId(Long classifyMovieId) {
		this.classifyMovieId = classifyMovieId;
	}
	public Long getClassifyTeleplayId() {
		return classifyTeleplayId;
	}
	public void setClassifyTeleplayId(Long classifyTeleplayId) {
		this.classifyTeleplayId = classifyTeleplayId;
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
	public String getClassifyNameString() {
		return classifyNameString;
	}
	public void setClassifyNameString(String classifyNameString) {
		this.classifyNameString = classifyNameString;
	}
	public String getClassifyDescribe() {
		return classifyDescribe;
	}
	public void setClassifyDescribe(String classifyDescribe) {
		this.classifyDescribe = classifyDescribe;
	}
	public Long getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(Long classifyName) {
		this.classifyName = classifyName;
	}
	public ClassifyName getClassifyNameObj() {
		return classifyNameObj;
	}
	public void setClassifyNameObj(ClassifyName classifyNameObj) {
		this.classifyNameObj = classifyNameObj;
	}
	public Classifys getParentClassify() {
		return parentClassify;
	}
	public void setParentClassify(Classifys parentClassify) {
		this.parentClassify = parentClassify;
	}
	@Override
	public String toString() {
		return "Classifys [classifyId=" + classifyId + ", classifyMovieId=" + classifyMovieId + ", classifyTeleplayId="
				+ classifyTeleplayId + ", movie=" + movie + ", teleplay=" + teleplay + ", classifyNameString="
				+ classifyNameString + ", classifyDescribe=" + classifyDescribe + ", classifyName=" + classifyName
				+ ", classifyNameObj=" + classifyNameObj + ", parentClassify=" + parentClassify + "]";
	}
	
}
