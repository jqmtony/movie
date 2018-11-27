package com.yc.movie.web.servlet;

import java.sql.SQLException;
import java.util.List;

import com.yc.exception.MovieException;
import com.yc.movie.bean.Movies;

public class MovieService{
	private MovieDao md = new MovieDao();

	/**
	 * ��ѯ���е�movie
	 * @return
	 * @throws MovieException 
	 */
	public List<Movies> findAllMovie() throws MovieException {
		try {
			return md.findAllMovie();
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}
}
