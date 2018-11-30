package com.yc.movie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 商户
 * @author yxh
 *
 */
public class Merchant implements Serializable {
	private Long merId;  //商户ID
	private String merName; //商户真实姓名
	private String merTel;	//商户手机号
	private String merEmail;	//商户邮箱
	private String merAddr;	//商户所在地址
	private String merPwd;  //商户登录密码
	private String merIDCard;	//商户身份证号码
	private String merStatus;  //商户状态  0禁用  1正常使用
	private List<Images> imgList;  //对应图片集合
	private List<Movies> movieList; //对应多个电影
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
