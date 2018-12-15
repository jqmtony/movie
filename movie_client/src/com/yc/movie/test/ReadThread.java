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
				result = in.readUTF(); //读取服务器发送来的信息
				System.out.println("服务器发送来的数据："+result);
				response.getWriter().append(result);  //将数据发送到页面
			} catch (IOException e) {
				System.out.println("与服务器断开连接了");
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
