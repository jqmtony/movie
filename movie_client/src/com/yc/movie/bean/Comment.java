package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 评论
 * @author Administrator
 *
 */
import java.util.List;
public class Comment implements Serializable {
	private Long commentId;  //评论ID
	private Long commentUserId;  //用户ID
	private Users user;  //用户对象
	private Long commentReplyId; //回复ID
	private List<Reply> replyList;  //回复对象
	private Long commentMovieId;  //对应电影ID
	private Movies movie;  //电影对象
	private Long commentTeleplayId; //对应电视剧ID
	private Teleplay teleplay;  //电视剧对象
	private String commentContent;  //评论内容
	private Timestamp commentCreateTime;  //评论时间
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
