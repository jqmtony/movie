package com.yc.movie.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class Test1 {
	
	@Test
	public void fun1() throws IOException{
		Properties p = new Properties();
		File file = new File("G:/mer.properties");
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file);
		
		p.load(fis);
		p.remove("1");
		System.out.println(p.getProperty("1"));
		p.store(fos, "");
		fis.close();
		fos.close();
	}
}
