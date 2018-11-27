package com.yc.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	//ͨ�������ļ���Ĭ�����û�ȡ�����ӳض���   Ҫ��������c3p0-config.xml�ļ�
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	//����ר������  һ���߳�һ�����Ӷ��󣬴����˶��̲߳�������
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	/**
	 * �����ӳ���ȡ��һ�����Ӷ���
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		
		//�������ר�����Ӷ���Ϊ�գ�˵�������ǿ��������˵�״̬����ôΪ��һ��������ʹ�õ���ͬһ��Connection����ֱ�ӷ��ص�ǰ�����ר�����Ӷ���
		if(conn!=null) return conn;
		return dataSource.getConnection();
	}
	
	/**
	 * �������ӳض���
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	/**
	 * ��������
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException{
		Connection conn = tl.get();
		
		//���conn��Ϊnull  ˵����ǰ�Ѿ�����ĳһ������
		if(conn!=null) throw new SQLException("�Ѿ����������ˣ���Ҫ�ظ�������");
		
		conn = getConnection();		//�����ӳ��л�ȡһ�����Ӷ���
		conn.setAutoCommit(false);		//�������񷽷�
		tl.set(conn);  //�ѵ�ǰ�̵߳�Connection���󱣴�����
	}
	
	/**
	 * �ύ����
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null) throw new SQLException("��û�п������񣬲����ύ");
		conn.commit();
		conn.close();
		tl.remove();  //�ѵ�ǰ�̵߳�Connection�Ƴ�   ����ҵ��
	}
	
	/**
	 * �ع�����
	 * @throws SQLException 
	 */
	public static void roolbackTransaction() throws SQLException{
		Connection conn = tl.get();
		if(conn == null) throw new SQLException("��û�п������񣬲��ܻع�");
		conn.rollback();
		conn.close();
		tl.remove();
	}
	
	/**
	 * �ͷ�����
	 * @param conn
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException{
		Connection conn = tl.get();
		/*
		 * �����ǰ����ר�õ�����Ϊ��   
		 * 	˵��connection��������ר�õ�����
		 * ��Ϊ���ڻ�û�п�������
		 */
		if(conn == null) connection.close();
		
		/*
		 * conn ��Ϊnull�������   ˵��������
		 * 		���conn!=connection  ˵��connection��������ר������   ��Ҫ�ر�
		 */
		if(conn != connection) connection.close();
	}
}
