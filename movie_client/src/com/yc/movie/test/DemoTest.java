package com.yc.movie.test;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yc.movie.bean.Movies;
import com.yc.movie.web.servlet.MovieDao;

public class DemoTest {
	@Test
	public void fun1(){
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		System.out.println(map.get("1"));
	}
	
	@Test
	public void fun2() throws SQLException{
		List<Movies> list = new MovieDao().findAllMovie();
		for(Movies m : list){
			System.out.println(m.getImgList().get(0).getImgPath());
		}
	}
}
