package com.yc.movie.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Movies implements Serializable{
	private Long movieId;	//电影id
	private Merchant merchant;  //对应商户
	private Long movieIntegralNum;  //积分数
	private String movieName;	//电影名
	private Long movieGradeNum;	//评分数
	private String movieDescribe;  //电影描述
	private String moviePath;	//观看地址
	private BigDecimal moviePrice; //单价
	private String movieStatus;  /*电影状态  0表示下架（卖完了） 1表示上架*/
	private Timestamp movieCreateTime; //上架时间
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Long getMovieIntegralNum() {
		return movieIntegralNum;
	}
	public void setMovieIntegralNum(Long movieIntegralNum) {
		this.movieIntegralNum = movieIntegralNum;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Long getMovieGradeNum() {
		return movieGradeNum;
	}
	public void setMovieGradeNum(Long movieGradeNum) {
		this.movieGradeNum = movieGradeNum;
	}
	public String getMovieDescribe() {
		return movieDescribe;
	}
	public void setMovieDescribe(String movieDescribe) {
		this.movieDescribe = movieDescribe;
	}
	public String getMoviePath() {
		return moviePath;
	}
	public void setMoviePath(String moviePath) {
		this.moviePath = moviePath;
	}
	public BigDecimal getMoviePrice() {
		return moviePrice;
	}
	public void setMoviePrice(BigDecimal moviePrice) {
		this.moviePrice = moviePrice;
	}
	public String getMovieStatus() {
		return movieStatus;
	}
	public void setMovieStatus(String movieStatus) {
		this.movieStatus = movieStatus;
	}
	public Timestamp getMovieCreateTime() {
		return movieCreateTime;
	}
	public void setMovieCreateTime(Timestamp movieCreateTime) {
		this.movieCreateTime = movieCreateTime;
	}
	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", merchant=" + merchant + ", movieIntegralNum=" + movieIntegralNum
				+ ", movieName=" + movieName + ", movieGradeNum=" + movieGradeNum + ", movieDescribe=" + movieDescribe
				+ ", moviePath=" + moviePath + ", moviePrice=" + moviePrice + ", movieStatus=" + movieStatus
				+ ", movieCreateTime=" + movieCreateTime + "]";
	}
	
}
