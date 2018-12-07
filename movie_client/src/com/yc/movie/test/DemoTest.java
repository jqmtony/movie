package com.yc.movie.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.yc.movie.bean.Movies;
import com.yc.movie.dao.MovieDao;
import com.yc.movie.dao.UserDao;
import com.yc.utils.TxQueryRunner;

public class DemoTest {
	@Test
	public void fun6(){
		String s = "123;";
		String[] ss = s.split(";");
		System.out.println(ss.length);
	}
	@Test
	public void fun5() throws SQLException{
		QueryRunner qr = new TxQueryRunner();
		for(int i=1;i<205;i++){
			String sql = "update ticket set ticketLocationNum=? where ticketId=?";
			Object[] params = {i,i+(204*3)};
			qr.update(sql, params);
		}
	}
	@Test
	public void fun4(){
		Timestamp t = new Timestamp(new Date().getTime());
		String s = t.toString();
		System.out.println(s);
	}
	
	@Test
	public void fun1(){
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		System.out.println(map.get("1"));
	}
	
	@Test
	public void fun2() throws SQLException{
		String[] s = {"userId","userName"};
		System.out.println(new UserDao().findUserBySelectConf(s, 1l,"ÑîöÎ»¢"));
	}
	
	@Test
	public void fun3(){
		Student s1 = new Student(1l, 432d,"zhangsan");
		Student s2 = new Student(2l, 112d,"lisi");
		Student s3 = new Student(3l, 574d,"wangwu");
		List<Student> list = new ArrayList<Student>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		System.out.println(list);
		Collections.sort(list,new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				if(o1.num > o2.num) return 1;
				if(o1.num == o2.num) return 0;
				return -1;
			}
		});
		
		System.out.println(list);
	}
}


class Student{
	public Long age;
	public Double num;
	public String Name;
	public Student(Long age, Double num, String name) {
		super();
		this.age = age;
		this.num = num;
		Name = name;
	}
	@Override
	public String toString() {
		return "Student [age=" + age + ", num=" + num + ", Name=" + Name + "]";
	}
	
}