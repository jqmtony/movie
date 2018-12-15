package com.yc.movie.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Movies implements Serializable{
	private Long movieId;	//��Ӱid
	private List<Merchant> merchantList;  //��Ӧ�̻�List
	private String movieMerId;  //�̻�ID
	private Long movieIntegralNum;  //������
	private String movieName;	//��Ӱ��
	private Double movieGradeNum;	//������
	private String movieDescribe;  //��Ӱ����
	private String moviePath;	//�ۿ���ַ
	private BigDecimal moviePrice; //����
	private Long movieVisitCount;  //������
	private String movieStatus;  /*��Ӱ״̬  0��ʾ�¼ܣ������ˣ� 1��ʾ�ϼ�*/
	private Timestamp movieCreateTime; //�ϼ�ʱ��
	private Long movieTimeLong; //����ʱ��
	private String moviePrevue;  //Ԥ��Ƭ·��
	private String movieGenre; //Ƭ��  3d  2d
	private List<Ticket> ticketList;  //��Ӧ��ӰƱ���󼯺�
	private List<Classifys> classifysList;  //��Ӧ�����ͼ���
	private List<Images> imgList;  //��Ӧ��ͼƬ����
	private List<Protagonists> proList;  //��Ӧ���ݼ���
	private List<Comment> commentList;  //����
	private Integer allMovieTicketCount;  //ʣ���ӰƱ��
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
