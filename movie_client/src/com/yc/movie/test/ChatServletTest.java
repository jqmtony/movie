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
	 * �����
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
			System.out.println("�ȴ��ͻ�����");
			you = server.accept();  //�ȴ��ͻ�����
			System.out.println("�ͻ���ַ��"+you.getInetAddress());
			
			if(you != null){
				new ServerThread(you).start(); //Ϊÿ���ͻ�����һ��ר�ŵ��߳�
			}
		}
	}
	
	/**
	 * �ͻ���
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
		readData = new Thread(read);    //�����ȡ��Ϣ���߳�
		String ip = "localhost";	//ip��ַ
		int port = 10005;  //�˿ں�
		if(mySocket.isConnected()){
			
		}else{
			InetAddress address = InetAddress.getByName(ip);
			InetSocketAddress socketAddress = new InetSocketAddress(address, port);
			mySocket.connect(socketAddress);
			in = new DataInputStream(mySocket.getInputStream());
			out = new DataOutputStream(mySocket.getOutputStream());
			read.setIn(in);
			
			readData.start();   //���������ȡ��Ϣ���߳�
		}
		
		String str = "����������͵���Ϣ";
		out.writeUTF(str);  //�������������Ϣ
	}
	
	/**
	 * ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void send(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		data = request.getParameter("content");
	}
	
}


