package com.yc.movie.bean;

import java.io.Serializable;
/**
 * �ظ�
 * @author Administrator
 *
 */
import java.sql.Timestamp;
import java.util.List;
public class Reply implements Serializable {
	private Long replyId; //�ظ�id
	private Long replyUserId; //�û�id
	private Users user; //�û�����
	private Long replyCommentId; //����ID
	private Comment comment;  //���۶���
	private Timestamp replyCreateTime;  //�ظ�ʱ��
	private String replyContent;  //�ظ����� 
	private Long replyParId;  //�ظ��ĸ�ID
	private Long replyNum;  //�ظ���
	private List<Reply> replySonList;  //�ظ��Ķ��Ӽ���  
	public Long getReplyId() {
		return replyId;
	}
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	public Long getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Long getReplyCommentId() {
		return replyCommentId;
	}
	public void setReplyCommentId(Long replyCommentId) {
		this.replyCommentId = replyCommentId;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public Timestamp getReplyCreateTime() {
		return replyCreateTime;
	}
	public void setReplyCreateTime(Timestamp replyCreateTime) {
		this.replyCreateTime = replyCreateTime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Long getReplyParId() {
		return replyParId;
	}
	public void setReplyParId(Long replyParId) {
		this.replyParId = replyParId;
	}
	public Long getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(Long replyNum) {
		this.replyNum = replyNum;
	}
	public List<Reply> getReplySonList() {
		return replySonList;
	}
	public void setReplySonList(List<Reply> replySonList) {
		this.replySonList = replySonList;
	}
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", replyUserId=" + replyUserId + ", user=" + user + ", replyCommentId="
				+ replyCommentId + ", comment=" + comment + ", replyCreateTime=" + replyCreateTime + ", replyContent="
				+ replyContent + ", replyParId=" + replyParId + ", replyNum=" + replyNum + ", replySonList="
				+ replySonList + "]";
	}
	
}
	
