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
	private Double movieGradeNum;	//评分数
	private String movieDescribe;  //电影描述
	private String moviePath;	//观看地址
	private BigDecimal moviePrice; //单价
	private String movieStatus;  /*电影状态  0表示下架（卖完了） 1表示上架*/
	private Timestamp movieCreateTime; //上架时间
	private List<Ticket> ticketList;  //对应电影票对象集合
	private List<Classifys> classifysList;  //对应的类型集合
	private List<Images> imgList;  //对应的图片集合
	private List<Protagonists> proList;  //对应主演集合
	public List<Ticket> getTicketList() {
		return ticketList;
	}
	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
	public List<Classifys> getClassifysList() {
		return classifysList;
	}
	public void setClassifysList(List<Classifys> classifysList) {
		this.classifysList = classifysList;
	}
	public List<Images> getImgList() {
		return imgList;
	}
	public void setImgList(List<Images> imgList) {
		this.imgList = imgList;
	}
	public List<Protagonists> getProList() {
		return proList;
	}
	public void setProList(List<Protagonists> proList) {
		this.proList = proList;
	}
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

	public Double getMovieGradeNum() {
		return movieGradeNum;
	}
	public void setMovieGradeNum(Double movieGradeNum) {
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
