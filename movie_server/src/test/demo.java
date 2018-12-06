package test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.junit.Test;

import com.yc.movie.utils.CommonsUtils;

public class demo {
	
	@Test
	public void fun1() throws HttpException, UnsupportedEncodingException, IOException{
		String sql ="123;345;";
		String[] s = sql.split(";");
		System.out.println(s.length);
		for(String s1:s)
			System.out.println(s1);
	}
}
