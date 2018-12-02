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
		File file = new File("g:/db.sql");
		File file2 = new File("f:/1.sql");
		CommonsUtils.cloneFile(file, file2);
	}
}
