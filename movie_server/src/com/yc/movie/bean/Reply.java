package com.yc.movie.bean;

import java.io.Serializable;
/**
 * 回复
 * @author Administrator
 *
 */
import java.sql.Timestamp;
public class Reply implements Serializable {
	private Long replyId; //回复id
	private Long replyUserId; //用户id
	private Users user; //用户对象
	private Long replyCommentId; //评论ID
	private Comment comment;  //评论对象
	private Timestamp replyCreateTime;  //回复时间
	private String replyContent;  //回复内容 
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
