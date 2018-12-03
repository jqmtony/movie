package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * ����
 * @author Administrator
 *
 */
import java.util.List;
public class Comment implements Serializable {
	private Long commentId;  //����ID
	private Long commentUserId;  //�û�ID
	private Users user;  //�û�����
	private Long commentReplyId; //�ظ�ID
	private List<Reply> replyList;  //�ظ�����
	private Long commentMovieId;  //��Ӧ��ӰID
	private Movies movie;  //��Ӱ����
	private Long commentTeleplayId; //��Ӧ���Ӿ�ID
	private Teleplay teleplay;  //���Ӿ����
	private String commentContent;  //��������
	private Timestamp commentCreateTime;  //����ʱ��
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(Long commentUserId) {
		this.commentUserId = commentUserId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Long getCommentReplyId() {
		return commentReplyId;
	}
	public void setCommentReplyId(Long commentReplyId) {
		this.commentReplyId = commentReplyId;
	}
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	public Long getCommentMovieId() {
		return commentMovieId;
	}
	public void setCommentMovieId(Long commentMovieId) {
		this.commentMovieId = commentMovieId;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovie(Movies movie) {
		this.movie = movie;
	}
	public Long getCommentTeleplayId() {
		return commentTeleplayId;
	}
	public void setCommentTeleplayId(Long commentTeleplayId) {
		this.commentTeleplayId = commentTeleplayId;
	}
	public Teleplay getTeleplay() {
		return teleplay;
	}
	public void setTeleplay(Teleplay teleplay) {
		this.teleplay = teleplay;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Timestamp getCommentCreateTime() {
		return commentCreateTime;
	}
	public void setCommentCreateTime(Timestamp commentCreateTime) {
		this.commentCreateTime = commentCreateTime;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentUserId=" + commentUserId + ", user=" + user
				+ ", commentReplyId=" + commentReplyId + ", replyList=" + replyList + ", commentMovieId="
				+ commentMovieId + ", movie=" + movie + ", commentTeleplayId=" + commentTeleplayId + ", teleplay="
				+ teleplay + ", commentContent=" + commentContent + ", commentCreateTime=" + commentCreateTime + "]";
	}
	
	
}
