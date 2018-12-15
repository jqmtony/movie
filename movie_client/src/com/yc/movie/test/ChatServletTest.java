package com.yc.movie.test;

import com.yc.utils.BaseServlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatServletTest
 */
@WebServlet("/chat.s")
public class ChatServletTest extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private String data = "";
	
	/**
	 * 服务端
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void server(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ServerSocket server = null;
		ServerThread thread;
		Socket you = null;
		while(true){
			server = new ServerSocket(10005);
			System.out.println("等待客户呼叫");
			you = server.accept();  //等待客户呼叫
			System.out.println("客户地址："+you.getInetAddress());
			
			if(you != null){
				new ServerThread(you).start(); //为每个客户启动一个专门的线程
			}
		}
	}
	
	/**
	 * 客户端
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void client(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Socket mySocket = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		Thread readData;
		ReadThread read = null;
		mySocket = new Socket();
		read = new ReadThread();    
		read.setResponse(response);
		readData = new Thread(read);    //负责读取信息的线程
		String ip = "localhost";	//ip地址
		int port = 10005;  //端口号
		if(mySocket.isConnected()){
			
		}else{
			InetAddress address = InetAddress.getByName(ip);
			InetSocketAddress socketAddress = new InetSocketAddress(address, port);
			mySocket.connect(socketAddress);
			in = new DataInputStream(mySocket.getInputStream());
			out = new DataOutputStream(mySocket.getOutputStream());
			read.setIn(in);
			
			readData.start();   //启动负责读取信息的线程
		}
		
		String str = "向服务器发送的信息";
		out.writeUTF(str);  //向服务器发送信息
	}
	
	/**
	 * 发送
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void send(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		data = request.getParameter("content");
	}
	
}


