package com.yc.movie.bean;

import java.io.Serializable;

/**
 * ��֤��
 * @author yxh
 *
 */
public class Verify implements Serializable {
	private String inputVerify;  //�û��������֤��
	private String createVerify;	//������ɵ���֤��
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
