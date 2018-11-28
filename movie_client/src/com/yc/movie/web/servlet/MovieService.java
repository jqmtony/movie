package com.yc.movie.web.servlet;

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

public class MovieService{
	private MovieDao md = new MovieDao();

	/**
	 * 查询所有的movie
	 * @return
	 * @throws MovieException 
	 */
	public List<Movies> findAllMovie(String byName) throws MovieException {
		try {
			
			return md.findAllMovie(byName);
		} catch (SQLException e) {
			throw new MovieException("系统异常，请稍后再试！");
		}
	}
}
