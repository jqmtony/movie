package com.yc.movie.bean;

import java.io.Serializable;
/**
 * �ظ�
 * @author Administrator
 *
 */
import java.sql.Timestamp;
public class Reply implements Serializable {
	private Long replyId; //�ظ�id
	private Long replyUserId; //�û�id
	private Users user; //�û�����
	private Long replyCommentId; //����ID
	private Comment comment;  //���۶���
	private Timestamp replyCreateTime;  //�ظ�ʱ��
	private String replyContent;  //�ظ����� 
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
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", replyUserId=" + replyUserId + ", user=" + user + ", replyCommentId="
				+ replyCommentId + ", comment=" + comment + ", replyCreateTime=" + replyCreateTime + ", replyContent="
				+ replyContent + "]";
	}
	
}
