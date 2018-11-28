package com.yc.movie.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Movies implements Serializable{
	private Long movieId;	//��Ӱid
	private Merchant merchant;  //��Ӧ�̻�
	private Long movieIntegralNum;  //������
	private String movieName;	//��Ӱ��
	private Double movieGradeNum;	//������
	private String movieDescribe;  //��Ӱ����
	private String moviePath;	//�ۿ���ַ
	private BigDecimal moviePrice; //����
	private String movieStatus;  /*��Ӱ״̬  0��ʾ�¼ܣ������ˣ� 1��ʾ�ϼ�*/
	private Timestamp movieCreateTime; //�ϼ�ʱ��
	private List<Ticket> ticketList;  //��Ӧ��ӰƱ���󼯺�
	private List<Classifys> classifysList;  //��Ӧ�����ͼ���
	private List<Images> imgList;  //��Ӧ��ͼƬ����
	private List<Protagonists> proList;  //��Ӧ���ݼ���
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
