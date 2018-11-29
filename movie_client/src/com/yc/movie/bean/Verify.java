package com.yc.movie.bean;

import java.io.Serializable;

/**
 * 验证码
 * @author yxh
 *
 */
public class Verify implements Serializable {
	private String inputVerify;  //用户输入的验证码
	private String createVerify;	//点击生成的验证码
	public String getInputVerify() {
		return inputVerify;
	}
	public void setInputVerify(String inputVerify) {
		this.inputVerify = inputVerify;
	}
	public String getCreateVerify() {
		return createVerify;
	}
	public void setCreateVerify(String createVerify) {
		this.createVerify = createVerify;
	}
	@Override
	public String toString() {
		return "Verify [inputVerify=" + inputVerify + ", createVerify=" + createVerify + "]";
	}
	
}
