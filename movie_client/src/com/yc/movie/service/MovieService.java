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

	/**
	 * ͨ��ID���ҵ�Ӱ
	 * @param id
	 * @return
	 * @throws MovieException 
	 */
	public Movies findMovieById(Long id) throws MovieException {
		try {
			Movies movie =  md.findMovieById(id);	//ͨ��id�õ�movie����
			movie = md.createMovie(movie); //����Ӧ������ӵ�movie������
			return movie;
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
		}  
	}

	/**
	 * ͨ��ID���ҵ��Ӿ�
	 * @param id
	 * @return
	 * @throws MovieException 
	 */
	public Teleplay findTeleplayById(Long id) throws MovieException {
		try {
			Teleplay teleplay =  md.findTeleplayById(id);	//ͨ��id�õ����Ӿ����
			teleplay = md.createTeleplay(teleplay); //����Ӧ������ӵ����Ӿ������
			return teleplay;
		} catch (SQLException e) {
			throw new MovieException("ϵͳ�쳣�����Ժ����ԣ�");
		}  
	}
}
