package com.yc.movie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * �̻�
 * @author yxh
 *
 */
public class Merchant implements Serializable {
	private Long merId;  //�̻�ID
	private String merName; //�̻���ʵ����
	private String merTel;	//�̻��ֻ���
	private String merEmail;	//�̻�����
	private String merAddr;	//�̻����ڵ�ַ
	private String merPwd;  //�̻���¼����
	private String merIDCard;	//�̻����֤����
	private String merStatus;  //�̻�״̬  0����  1����ʹ��
	private List<Images> imgList;  //��ӦͼƬ����
	private List<Movies> movieList; //��Ӧ�����Ӱ
	public List<Movies> getMovieList() {
		return movieList;
	}
	public void setMovieList(List<Movies> movieList) {
		this.movieList = movieList;
	}
	public List<Images> getImgList() {
		return imgList;
	}
	public void setImgList(List<Images> imgList) {
		this.imgList = imgList;
	}
	public Long getMerId() {
		return merId;
	}
	public void setMerId(Long merId) {
		this.merId = merId;
	}
	public String getMerName() {
		return merName;
	}
	public void setMerName(String merName) {
		this.merName = merName;
	}
	public String getMerTel() {
		return merTel;
	}
	public void setMerTel(String merTel) {
		this.merTel = merTel;
	}
	public String getMerEmail() {
		return merEmail;
	}
	public void setMerEmail(String merEmail) {
		this.merEmail = merEmail;
	}
	public String getMerAddr() {
		return merAddr;
	}
	public void setMerAddr(String merAddr) {
		this.merAddr = merAddr;
	}
	public String getMerPwd() {
		return merPwd;
	}
	public void setMerPwd(String merPwd) {
		this.merPwd = merPwd;
	}
	public String getMerIDCard() {
		return merIDCard;
	}
	public void setMerIDCard(String merIDCard) {
		this.merIDCard = merIDCard;
	}
	public String getMerStatus() {
		return merStatus;
	}
	public void setMerStatus(String merStatus) {
		this.merStatus = merStatus;
	}
	@Override
	public String toString() {
		return "Merchant [merId=" + merId + ", merName=" + merName + ", merTel=" + merTel + ", merEmail=" + merEmail
				+ ", merAddr=" + merAddr + ", merPwd=" + merPwd + ", merIDCard=" + merIDCard + ", merStatus="
				+ merStatus + "]";
	}

}
