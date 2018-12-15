package com.yc.movie.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	DataOutputStream out = null;
	DataInputStream in = null;
	String s = null;
	ServerThread(Socket t){
		socket = t;
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			try {
				String s = in.readUTF();  //����״̬   ���Ƕ�����Ϣ
				out.writeUTF(s);
			} catch (IOException e) {
				System.out.println("�ͻ��뿪��");
				e.printStackTrace();
			} 
		}
	}
}
