package com.yc.movie.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yc.movie.bean.Classifys;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Movies;
import com.yc.utils.TxQueryRunner;

public class MovieDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 查询所有的movie
	 * @return
	 * @throws SQLException 
	 */
	public List<Movies> findAllMovie() throws SQLException {
		String sql = "select * from movies";
		List<Movies> movieList = qr.query(sql, new BeanListHandler<Movies>(Movies.class));
		if(movieList.size() < 1)
			return null;
		for(Movies movie : movieList){
			//查询当前movie对应的类型集合
			sql = "select * from classifys where classifyMovieId=?";
			List<Classifys> classifysList = qr.query(sql, new BeanListHandler<Classifys>(Classifys.class),movie.getMovieId());
			if(classifysList.size() > 0)
				movie.setClassifysList(classifysList);  //设置类型集合
			//查询当前movie对应的图片集合
			sql = "select * from images where imgMovieId=?";
			List<Images> imgList = qr.query(sql, new BeanListHandler<Images>(Images.class),movie.getMovieId());
			if(imgList.size() > 0)
				movie.setImgList(imgList);  //设置图片集合
		}
		return movieList;
	}

}
