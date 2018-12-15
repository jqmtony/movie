package com.yc.movie.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Movies implements Serializable{
	private Long movieId;	//电影id
	private List<Merchant> merchantList;  //对应商户List
	private String movieMerId;  //商户ID
	private Long movieIntegralNum;  //积分数
	private String movieName;	//电影名
	private Double movieGradeNum;	//评分数
	private String movieDescribe;  //电影描述
	private String moviePath;	//观看地址
	private BigDecimal moviePrice; //单价
	private Long movieVisitCount;  //访问数
	private String movieStatus;  /*电影状态  0表示下架（卖完了） 1表示上架*/
	private Timestamp movieCreateTime; //上架时间
	private Long movieTimeLong; //播放时长
	private String moviePrevue;  //预告片路径
	private String movieGenre; //片种  3d  2d
	private List<Ticket> ticketList;  //对应电影票对象集合
	private List<Classifys> classifysList;  //对应的类型集合
	private List<Images> imgList;  //对应的图片集合
	private List<Protagonists> proList;  //对应主演集合
	private List<Comment> commentList;  //评论
	private Integer allMovieTicketCount;  //剩余电影票数
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public List<Merchant> getMerchantList() {
		return merchantList;
	}
	public void setMerchantList(List<Merchant> merchantList) {
		this.merchantList = merchantList;
	}
	public String getMovieMerId() {
		return movieMerId;
	}
	public void setMovieMerId(String movieMerId) {
		this.movieMerId = movieMerId;
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
	public Long getMovieVisitCount() {
		return movieVisitCount;
	}
	public void setMovieVisitCount(Long movieVisitCount) {
		this.movieVisitCount = movieVisitCount;
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
	public Long getMovieTimeLong() {
		return movieTimeLong;
	}
	public void setMovieTimeLong(Long movieTimeLong) {
		this.movieTimeLong = movieTimeLong;
	}
	public String getMoviePrevue() {
		return moviePrevue;
	}
	public void setMoviePrevue(String moviePrevue) {
		this.moviePrevue = moviePrevue;
	}
	public String getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
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
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public Integer getAllMovieTicketCount() {
		return allMovieTicketCount;
	}
	public void setAllMovieTicketCount(Integer allMovieTicketCount) {
		this.allMovieTicketCount = allMovieTicketCount;
	}
	@Override
	public String toString() {
		return "Movies [movieId=" + movieId + ", merchantList=" + merchantList + ", movieMerId=" + movieMerId
				+ ", movieIntegralNum=" + movieIntegralNum + ", movieName=" + movieName + ", movieGradeNum="
				+ movieGradeNum + ", movieDescribe=" + movieDescribe + ", moviePath=" + moviePath + ", moviePrice="
				+ moviePrice + ", movieVisitCount=" + movieVisitCount + ", movieStatus=" + movieStatus
				+ ", movieCreateTime=" + movieCreateTime + ", movieTimeLong=" + movieTimeLong + ", moviePrevue="
				+ moviePrevue + ", movieGenre=" + movieGenre + ", ticketList=" + ticketList + ", classifysList="
				+ classifysList + ", imgList=" + imgList + ", proList=" + proList + ", commentList=" + commentList
				+ ", allMovieTicketCount=" + allMovieTicketCount + "]";
	}
	
}
