package com.yc.movie.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import com.yc.exception.MovieException;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Teleplay;
import com.yc.movie.dao.MovieDao;

public class MovieService{
	private MovieDao md = new MovieDao();

	/**
	 * 查询所有的movie
	 * @return
	 * @throws MovieException 
	 */
	public List<Movies> findAllMovie() throws MovieException {
		try {
			
			return md.findAllMovie();
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}
	}

	/**
	 * 通过ID查找电影
	 * @param id
	 * @return
	 * @throws MovieException 
	 */
	public Movies findMovieById(Long id) throws MovieException {
		try {
			Movies movie =  md.findMovieById(id);	//通过id得到movie对象
			movie = md.createMovie(movie); //将对应集合添加到movie对象中
			return movie;
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}  
	}

	/**
	 * 通过ID查找电视剧
	 * @param id
	 * @return
	 * @throws MovieException 
	 */
	public Teleplay findTeleplayById(Long id) throws MovieException {
		try {
			Teleplay teleplay =  md.findTeleplayById(id);	//通过id得到电视剧对象
			teleplay = md.createTeleplay(teleplay); //将对应集合添加到电视剧对象中
			return teleplay;
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}  
	}
}
