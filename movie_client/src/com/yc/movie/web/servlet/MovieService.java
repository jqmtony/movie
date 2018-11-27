package com.yc.movie.web.servlet;

import java.sql.SQLException;
import java.util.List;

import com.yc.exception.MovieException;
import com.yc.movie.bean.Movies;

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
}
