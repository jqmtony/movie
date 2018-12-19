package com.yc.movie.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Test1 {
	
	@Test
	public void fun1() throws IOException{
		Jedis jedis = new Jedis();
		jedis.sadd("title:"+1, "уехЩ");
		jedis.close();
		
		jedis = new Jedis();
		System.out.println(jedis.smembers("title:1"));
	}
	
}
