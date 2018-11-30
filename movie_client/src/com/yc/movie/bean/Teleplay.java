package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * ���Ӿ�
 * @author yxh
 *
 */
public class Teleplay implements Serializable {
	private Long teleplayId;  //���Ӿ�ID
	private Merchant merchant; //��Ӧ�̻�
	private Long teleplayMerId;  //��Ӧ�̻�ID
	private Long teleplayIntegralNum; //������
	private String teleplayName; //���Ӿ���
	private Long teleplayGradeNum; //������
	private String teleplayDescribe; //���Ӿ�����
	private String teleplayPath; //���Ӿ�ۿ�����
	private Timestamp teleplayCreateTime; //���Ӿ��ϼ�ʱ��
	private List<Classifys> classifysList;  //��Ӧ�����ͼ���
	private List<Images> imgList;  //��Ӧ��ͼƬ����
	private List<Protagonists> proList;  //��Ӧ���ݼ���
	public Long getTeleplayId() {
		return teleplayId;
	}
	public void setTeleplayId(Long teleplayId) {
		this.teleplayId = teleplayId;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Long getTeleplayMerId() {
		return teleplayMerId;
	}
	public void setTeleplayMerId(Long teleplayMerId) {
		this.teleplayMerId = teleplayMerId;
	}
	public Long getTeleplayIntegralNum() {
		return teleplayIntegralNum;
	}
	public void setTeleplayIntegralNum(Long teleplayIntegralNum) {
		this.teleplayIntegralNum = teleplayIntegralNum;
	}
	public String getTeleplayName() {
		return teleplayName;
	}
	public void setTeleplayName(String teleplayName) {
		this.teleplayName = teleplayName;
	}
	public Long getTeleplayGradeNum() {
		return teleplayGradeNum;
	}
	public void setTeleplayGradeNum(Long teleplayGradeNum) {
		this.teleplayGradeNum = teleplayGradeNum;
	}
	public String getTeleplayDescribe() {
		return teleplayDescribe;
	}
	public void setTeleplayDescribe(String teleplayDescribe) {
		this.teleplayDescribe = teleplayDescribe;
	}
	public String getTeleplayPath() {
		return teleplayPath;
	}
	public void setTeleplayPath(String teleplayPath) {
		this.teleplayPath = teleplayPath;
	}
	public Timestamp getTeleplayCreateTime() {
		return teleplayCreateTime;
	}
	public void setTeleplayCreateTime(Timestamp teleplayCreateTime) {
		this.teleplayCreateTime = teleplayCreateTime;
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
	@Override
	public String toString() {
		return "Teleplay [teleplayId=" + teleplayId + ", merchant=" + merchant + ", teleplayMerId=" + teleplayMerId
				+ ", teleplayIntegralNum=" + teleplayIntegralNum + ", teleplayName=" + teleplayName
				+ ", teleplayGradeNum=" + teleplayGradeNum + ", teleplayDescribe=" + teleplayDescribe
				+ ", teleplayPath=" + teleplayPath + ", teleplayCreateTime=" + teleplayCreateTime + ", classifysList="
				+ classifysList + ", imgList=" + imgList + ", proList=" + proList + "]";
	}
	
}
