package com.yc.movie.admin.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.sun.jmx.snmp.Timestamp;

public class AdminDaoTest {
	@Test
	public void fun1(){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
	} 
}
