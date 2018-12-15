package com.yc.movie.test;

import java.io.DataInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadThread implements Runnable {
	private DataInputStream in;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	

	public void run() {
		String result = "";
		while(true){
			try {
				result = in.readUTF(); //��ȡ����������������Ϣ
				System.out.println("�����������������ݣ�"+result);
				response.getWriter().append(result);  //�����ݷ��͵�ҳ��
			} catch (IOException e) {
				System.out.println("��������Ͽ�������");
				e.printStackTrace();
			} 
		}
	}
	
	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
