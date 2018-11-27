package com.yc.movie.bean;

import java.io.Serializable;

/**
 * 主演
 * @author yxh
 *
 */
public class Protagonists implements Serializable {
	private Long proId;  //主演ID
	private Movies movie; //对应电影
	private Teleplay teleplay;  //对应电视剧
	private String proName;  //主演姓名
	private String proLink;  //主演介绍链接
	public Long getProId() {
		return proId;
	}
	public void setProId(Long proId) {
		this.proId = proId;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovie(Movies movie) {
		this.movie = movie;
	}
	public Teleplay getTeleplay() {
		return teleplay;
	}
	public void setTeleplay(Teleplay teleplay) {
		this.teleplay = teleplay;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProLink() {
		return proLink;
	}
	public void setProLink(String proLink) {
		this.proLink = proLink;
	}
	@Override
	public String toString() {
		return "Protagonists [proId=" + proId + ", movie=" + movie + ", teleplay=" + teleplay + ", proName=" + proName
				+ ", proLink=" + proLink + "]";
	}

}
