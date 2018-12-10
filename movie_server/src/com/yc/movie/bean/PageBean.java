package com.yc.movie.bean;

import java.util.List;

public class PageBean<T> {
	private Integer pc;  //��ǰҳ��page code
//	private int tp;  //��ҳ��total page
	private Integer tr;  //�ܼ�¼��total record
	private Integer ps;  //ÿҳ��¼��page size
	private List<T> beanList;  //��ǰҳ�ļ�¼
	private String url;  //url���������
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		if(pc < 1) pc = 1;
		this.pc = pc;
	}
	/**
	 * ������ҳ��
	 * @return
	 */
	public int getTp() {
		int tp = -1;
		if(tr <= 0) {
			tr = 1;
			tp = tr/ps;
			tp = tr%ps==0 ? tp:tp+1;
			tr = 0;
		}else{
			tp = tr/ps;
			tp = tr%ps==0 ? tp:tp+1;
		}
		return tp;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
}
