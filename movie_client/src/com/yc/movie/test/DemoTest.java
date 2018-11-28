package com.yc.movie.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	public void fun3(){
		Student s1 = new Student(1, 432,"zhangsan");
		Student s2 = new Student(2, 112,"lisi");
		Student s3 = new Student(3, 574,"wangwu");
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
	public int age;
	public int num;
	public String Name;
	public Student(int age, int num, String name) {
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