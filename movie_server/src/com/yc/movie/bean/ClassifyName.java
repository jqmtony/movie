package com.yc.movie.bean;

import java.io.Serializable;

public class ClassifyName implements Serializable {
	private Long classifyNameId;
	private Classifys classify;
	private String classifyNameString;
	public Long getClassifyNameId() {
		return classifyNameId;
	}
	public void setClassifyNameId(Long classifyNameId) {
		this.classifyNameId = classifyNameId;
	}
	public Classifys getClassify() {
		return classify;
	}
	public void setClassify(Classifys classify) {
		this.classify = classify;
	}
	public String getClassifyNameString() {
		return classifyNameString;
	}
	public void setClassifyNameString(String classifyNameString) {
		this.classifyNameString = classifyNameString;
	}
	@Override
	public String toString() {
		return "ClassifyName [classifyNameId=" + classifyNameId + ", classify=" + classify + ", classifyNameString="
				+ classifyNameString + "]";
	}
	
}
