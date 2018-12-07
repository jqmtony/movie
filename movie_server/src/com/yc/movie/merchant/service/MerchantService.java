package com.yc.movie.merchant.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.yc.movie.bean.ClassifyName;
import com.yc.movie.bean.Classifys;
import com.yc.movie.bean.Images;
import com.yc.movie.bean.Merchant;
import com.yc.movie.bean.Movies;
import com.yc.movie.bean.Protagonists;
import com.yc.movie.bean.Ticket;
import com.yc.movie.bean.Verification;
import com.yc.movie.exception.MerchantException;
import com.yc.movie.merchant.dao.MerchantDao;
import com.yc.movie.utils.CommonsUtils;
import com.yc.movie.utils.JdbcUtils;

import UDP.Rece;
import UDP.Send;
import sun.font.CreatedFontTracker;

public class MerchantService {
	private MerchantDao md = new MerchantDao();

	/**
	 * ��¼
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException
	 */
	public Merchant login(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//�ж����ɵ���֤���Ƿ�Ϊnull
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//�ж��������֤���Ƿ�Ϊnull
			if(in == null || in.isEmpty())
				throw new MerchantException("��������֤�룡");
			
			//�ж���֤���Ƿ���ȷ
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("��֤���������");
		}
		
		//�ж��û����Ƿ�Ϊnull
		if(email == null || email.isEmpty())
			throw new MerchantException("�����������ַ��");
		
		//�ж������Ƿ�Ϊnull
		if(pwd == null || pwd.isEmpty())
			throw new MerchantException("���������룡");
		
		//�ж��û�����ʽ�Ƿ���ȷ
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("�����ʽ����");
		
		//�ж��û����Ƿ����
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me == null)
				throw new MerchantException("������δע�ᣡ");
			
			//�ж��û����������Ƿ�ƥ��
			pwd = CommonsUtils.parseMD5(pwd);
			me = md.findMerchantByEmailAndPwd(email,pwd);
			if(me == null)
				throw new MerchantException("�������");
			
			//��½�ɹ� ����һ���������̻�����
			me = md.createMerchant(me);
			return me;
		} catch (SQLException e) {
			throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * ע��
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException 
	 */
	public Merchant register(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//�ж����ɵ���֤���Ƿ�Ϊnull
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//�ж��������֤���Ƿ�Ϊnull
			if(in == null || in.isEmpty())
				throw new MerchantException("��������֤�룡");
			
			//�ж���֤���Ƿ���ȷ
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("��֤���������");
		}
		
		//�ж��û����Ƿ�Ϊnull
		if(email == null || email.isEmpty())
			throw new MerchantException("�����������ַ��");
		
		//�ж������Ƿ�Ϊnull
		if(pwd == null || pwd.isEmpty())
			throw new MerchantException("���������룡");
		
		//�ж��û�����ʽ�Ƿ���ȷ
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("�����ʽ����");
		
		//�ж��û����Ƿ����
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me != null)
				throw new MerchantException("�������ѱ�ע�ᣡ");
			
			//��֤�ɹ�
			form.setMerStatus("0");
			form.setMerPwd(CommonsUtils.parseMD5(form.getMerPwd()));
			
			//���ʼ�
			//���ã�������ע�᡾Ӱ�����á���̨�̻������˺ţ�<h3><a href="http://{0}mer.s?method=registerAfter">�������</a></h3>
			Object[] codes = {CommonsUtils.getAddressAndProName(this.getClass())};
			CommonsUtils.sendMail(this.getClass(), form.getMerEmail(), codes, "register_merchant_email.properties");
			return form;
		} catch(IOException e1){
			throw new MerchantException("ϵͳ�쳣��ϵͳ�ļ����𻵣�");
		}catch (SQLException e) {
			throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * �˺ż���
	 * @param me
	 * @throws MerchantException 
	 */
	public void registerAfter(Merchant me) throws MerchantException {
		try {
			me.setMerStatus("0");  //�̻��˺ŵĳ�ʼ��״̬Ϊ����   ����ʵ����֤��ſ���ʹ��
			JdbcUtils.beginTransaction();
			md.insertMerchant(me);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
	}

	/**
	 * �����޸��������ӵ�����
	 * @param form
	 * @param verify
	 * @return
	 * @throws MerchantException 
	 */
	public Merchant fogetBefore(Merchant form, Verification verify) throws MerchantException {
		String email = form.getMerEmail();
		String pwd = form.getMerPwd();
		if(verify != null){
			String in = verify.getInCode();
			String code = verify.getText();
			
			//�ж����ɵ���֤���Ƿ�Ϊnull
			if(code == null || code.isEmpty())
				throw new RuntimeException();
			
			//�ж��������֤���Ƿ�Ϊnull
			if(in == null || in.isEmpty())
				throw new MerchantException("��������֤�룡");
			
			//�ж���֤���Ƿ���ȷ
			if(!in.equalsIgnoreCase(code))
				throw new MerchantException("��֤���������");
		}
		
		//�ж��û����Ƿ�Ϊnull
		if(email == null || email.isEmpty())
			throw new MerchantException("�����������ַ��");
		
		//�ж��û�����ʽ�Ƿ���ȷ
		if(!email.matches(CommonsUtils.EMAIL_REGX))
			throw new MerchantException("�����ʽ����");
		
		//�ж��û����Ƿ����
		try {
			Merchant me = md.findMerchantByEmail(email);
			if(me == null)
				throw new MerchantException("������δע�ᣡ");
			
			//���ʼ�
			//�𾴵� ��{0}�����ã��������޸ġ�Ӱ�����á���̨�̻������˺ŵ����룺<h3><a href="http://{2}mer.s?method=registerAfter">����޸�</a></h3>
			Object[] codes = {email,CommonsUtils.getAddressAndProName(this.getClass())};
			CommonsUtils.sendMail(this.getClass(), form.getMerEmail(), codes, "register_forget_email.properties");
			return form;
		} catch(IOException e1){
			throw new MerchantException("ϵͳ�쳣��ϵͳ�ļ����𻵣�");
		}catch (SQLException e) {
			throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
		}
	}

	/**
	 * �޸�����
	 * @param me
	 * @param pwd2
	 * @throws MerchantException 
	 */
	public void foget(Merchant me, String pwd2) throws MerchantException {
		String pwd1 = me.getMerPwd();
		//�ж������Ƿ�Ϊnull
		if(pwd1 == null || pwd1.isEmpty())
			throw new MerchantException("���������룡");

		//�ж�ȷ�������Ƿ�Ϊnull
		if(pwd2 == null || pwd2.isEmpty())
			throw new MerchantException("������ȷ�����룡");
		
		//�ж������ʽ�Ƿ���ȷ
		if(!pwd1.matches(CommonsUtils.PWD_REGX))
			throw new MerchantException("�����ʽ����ȷ��");
		
		//�ж������ȷ�������Ƿ���ͬ
		if(!pwd1.equals(pwd2))
			throw new MerchantException("������������벻��ͬ��");
		
		//����
		me.setMerPwd(CommonsUtils.parseMD5(pwd1));
		
		try {
			JdbcUtils.beginTransaction();
			//�޸�
			md.updateByPwd(me);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
	}

	/**
	 * ������֤��
	 * @param tel
	 * @throws MerchantException 
	 */
	public String sendTelCode(String tel) throws MerchantException {
		//�ж��ֻ����Ƿ�ΪNull
		if(tel == null || tel.isEmpty())
			throw new MerchantException("�������ֻ���!");
		
		//�ж��ֻ��Ÿ�ʽ�Ƿ���ȷ
		if(!tel.matches(CommonsUtils.TEL_NUM_REGX))
			throw new MerchantException("�ֻ��Ÿ�ʽ����ȷ!");
		
		//����
		String code = CommonsUtils.createVerifyCode(6, CommonsUtils.VERIFY_CODE_TYPE_TEL);
		CommonsUtils.sendTelCode(tel, "�����ڽ���ʵ����֤����֤�롾"+code+"����������֤��й¶������(15��������Ч)");
		
		return code;
	}

	/**
	 * ʵ����֤
	 * @param form	merchant����
	 * @param loginedMerchant	��ǰ��¼��merchant����
	 * @param telCode	���ɵ��ֻ���֤��
	 * @param inVerify	������ֻ���֤��
	 * @throws MerchantException
	 */
	public String realName(Merchant form,Merchant loginedMerchant,String telCode,String inVerify) throws MerchantException {
		String name = form .getMerName();
		String tel = form.getMerTel();
		String addr = form.getMerAddr();
		String card = form.getMerIDCard();
		boolean flag = true;
//		System.out.println(inVerify+"+"+telCode);
//		System.out.println(addr);
		
	
		
		//�ж������Ƿ�Ϊnull
		if(name == null || name.trim().isEmpty())
			throw new MerchantException("������������");
		
		//�ж��ֻ������Ƿ�ΪNull
		if(tel == null || tel.trim().isEmpty())
			throw new MerchantException("�������ֻ��ţ�");
		
		//�жϵ�ַ�Ƿ�Ϊnull
		if(addr == null || addr.trim().isEmpty())
			throw new MerchantException("�������ַ��");
		if(addr.startsWith("�й� ʡ"))
			throw new MerchantException("�������ַ��");
		
		//�ж����֤�Ƿ�Ϊnull
		if(card == null || card.trim().isEmpty())
			throw new MerchantException("���������֤�ţ�");
		
		//�ж��������֤���Ƿ�Ϊnull
		if(inVerify == null || inVerify.trim().isEmpty())
			throw new MerchantException("�������ֻ���֤�룡");
		
		//�ж����ɵ���֤���Ƿ�Ϊnull
		if(telCode == null || telCode.trim().isEmpty())
			throw new MerchantException("��֤���������");
		
		//�ж�������ʽ
		if(!name.trim().matches(CommonsUtils.NAME_REGX))
			throw new MerchantException("������ʽ����ȷ��");
		
		//�ж��ֻ������ʽ
		if(!tel.trim().matches(CommonsUtils.TEL_NUM_REGX))
			throw new MerchantException("�ֻ��Ÿ�ʽ����ȷ��");
		
		//�ж����֤��ʽ
		if(!card.trim().matches(CommonsUtils.ID_NUM_REGX))
			throw new MerchantException("���֤�����ʽ����ȷ��");
		
		//�ж���֤���ʽ
		if(!inVerify.trim().matches("\\d{6}"))
			throw new MerchantException("��֤���ʽ����ȷ��");
		
		//�ж���֤���Ƿ���ȷ
		if(!inVerify.trim().equals(telCode.trim()))
			throw new MerchantException("��֤�����");
		try{
			//�ж����֤�Ƿ��Ѿ���ʵ��
			Merchant m = md.findMerchantByIdCard(form.getMerIDCard());
			if(m != null)
				throw new MerchantException("��������֤�����Ѿ�ʵ�����ˣ�");
			
			//�ж��ֻ����Ƿ��Ѿ���ע��
			m = md.findMerchantByTel(form.getMerTel());
			if(m != null)
				throw new MerchantException("������ֻ������Ѿ����󶨣�");
		}catch(SQLException e){
			throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
		}
		
		//TCP���͸�����Ա
		String infoID = CommonsUtils.getUUID();
//			new Thread(new Send(new DatagramSocket())).start();
		byte[] buf = infoID.getBytes();
		try {
			DatagramSocket rece = new DatagramSocket(10005);
			new Thread(new Rece(rece)).start();
			
			DatagramSocket ds = new DatagramSocket();
			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 10005);
			ds.send(dp);
			ds.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return infoID;
	}

	/**
	 * ��ѯ���е�����
	 * @return
	 */
	public List<ClassifyName> findAllClassify() {
		try {
			return md.findAllClassify();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ϢУ��
	 * @param form //movieMerId  movieName movieIntegralNum  movieDescribe movieTimeLong moviePrevue moviePrice
	 * @param m
	 * @throws MerchantException 
	 */
	public void regxMovieInfo(Movies form, Map<String, String> m) throws MerchantException {
		String moviePro = m.get("moviePro");   //����
		String classifyStr = m.get("classifyStr");  //��ȡ�����ַ���
		String moviePrevuePath = m.get("moviePrevuePath");  //Ԥ��Ƭ·��
		String movieStartTime1 = m.get("movieStartTime1");  //1������ӳʱ��
		String movieStartTime2 = m.get("movieStartTime2");  //2������ӳʱ��
		String movieStartTime3 = m.get("movieStartTime3");  //3������ӳʱ��
		String movieStartTime4 = m.get("movieStartTime4");  //4������ӳʱ��
		String movieStartTime5 = m.get("movieStartTime5");  //5������ӳʱ��
		String movieStartTime6 = m.get("movieStartTime6");  //6������ӳʱ��
		String movieImage1 = m.get("movieImage1");  //����ͼƬ
		String movieImage2 = m.get("movieImage2");  //ͼƬ2
		String movieImage3 = m.get("movieImage3");  //ͼƬ3
		String movieName = form.getMovieName();  //��Ӱ��
		Long movieIntegralNum = form.getMovieIntegralNum();  //������
		String movieDescribe = form.getMovieDescribe();  //��Ӱ���
		Long movieTimeLong = form.getMovieTimeLong();  //��Ӱʱ��
		BigDecimal moviePrice = form.getMoviePrice();  //����
		String movieGenre = form.getMovieGenre(); //Ƭ��
		
		//�жϵ�Ӱ���Ƿ�Ϊnull
		if(isNull(movieName))
			throw new MerchantException("�������Ӱ����");
		
		//�ж������Ƿ�Ϊnull
		if(isNull(moviePro))
			throw new MerchantException("���������ݣ�");
		
		//�жϷ����Ƿ�Ϊnull
		if(isNull(classifyStr))
			throw new MerchantException("���������ͣ�");
		
		//�ж�Ƭ���Ƿ�Ϊnull
		if(isNull(movieGenre))
			throw new MerchantException("������Ƭ�֣�");
		
		//�жϵ����Ƿ�Ϊnull
		if(moviePrice == null)
			throw new MerchantException("�����뵥�ۣ�");
		
		//�жϻ����Ƿ�ΪNull
		if(movieIntegralNum == null)
			throw new MerchantException("�������������");
		
		//�жϵ�Ӱʱ���Ƿ�ΪNull
		if(movieTimeLong == null)
			throw new MerchantException("�������Ӱ����ʱ����");
		
		//�ж�Ԥ��Ƭ�Ƿ�Ϊnull
		if(isNull(moviePrevuePath))
			throw new MerchantException("���ϴ�Ԥ��Ƭ��");
		
		//�ж������Ƿ�Ϊnull
		if(isNull(movieDescribe))
			throw new MerchantException("�������Ӱ��飡");
		
		//�ж���ӳʱ���Ƿ�Ϊnull
		if(isNull(movieStartTime1) && isNull(movieStartTime2) && isNull(movieStartTime3) && 
				isNull(movieStartTime4) && isNull(movieStartTime5) && isNull(movieStartTime6))
			throw new MerchantException("������ѡ��һ����Ӱ����������д��ӳʱ�䣡");
		
		//�ж�movieImage�Ƿ�Ϊnull
		if(isNull(movieImage1) || isNull(movieImage2) || isNull(movieImage3))
			throw new MerchantException("�㻹û���ϴ���Ӱ��ͼƬ����Ӱ������ͼƬ�����ϴ���");
	}
	
	/**
	 * �ж��ַ����Ƿ�ΪNull
	 * @param str
	 * @return
	 */
	private boolean isNull(String str){
		if(str == null || str.trim().isEmpty())
			return true;
		return false;
	}

	/**
	 * ��Ӱ�ϼ�
	 * @param form
	 * @param m
	 * @throws MerchantException 
	 */
	public void addMovie(Movies form, Map<String, String> m,List<String> sqlPaths,String oldMovieMerId,Long merId) throws MerchantException {
		String moviePro = m.get("moviePro");   //����
		String classifyStr = m.get("classifyStr");  //��ȡ�����ַ���
		
		String movieStartTime1 = m.get("movieStartTime1");  //1������ӳʱ��
		String movieStartTime2 = m.get("movieStartTime2");  //2������ӳʱ��
		String movieStartTime3 = m.get("movieStartTime3");  //3������ӳʱ��
		String movieStartTime4 = m.get("movieStartTime4");  //4������ӳʱ��
		String movieStartTime5 = m.get("movieStartTime5");  //5������ӳʱ��
		String movieStartTime6 = m.get("movieStartTime6");  //6������ӳʱ��
//		String moviePrevuePath = m.get("moviePrevuePath");  //Ԥ��Ƭ·��   ���ĸ��ļ���·������sqlPaths������
//		String movieImage1 = m.get("movieImage1");  //����ͼƬ
//		String movieImage2 = m.get("movieImage2");  //ͼƬ2
//		String movieImage3 = m.get("movieImage3");  //ͼƬ3
		String movieName = form.getMovieName();  //��Ӱ��
		Long movieIntegralNum = form.getMovieIntegralNum();  //������
		String movieDescribe = form.getMovieDescribe();  //��Ӱ���
		Long movieTimeLong = form.getMovieTimeLong();  //��Ӱʱ��
		String moviePrevue = form.getMoviePrevue();  //��ӰƬ��
		BigDecimal moviePrice = form.getMoviePrice();  //����
		
		form.setMovieVisitCount(0l);  //���ó�ʼ������Ϊ0
		form.setMovieGradeNum(0.0);  //���ó�ʼ����Ϊ0
		form.setMovieStatus("1");  //���õ�Ӱ��״̬  1Ϊ����
		form.setMovieCreateTime(new Timestamp(new Date().getTime()));  //���õ�Ӱ�ϼ�ʱ��
		
		//���������޸�  ��������
		try {
			JdbcUtils.beginTransaction();
			
			//������̻��Ѿ���ӳ�˴˲���Ӱ   ��ֻ�����ӵ�ӰƱ��
			Movies m1 = md.haveMovie(form.getMovieName());
			if(m1 != null){  //���ݿ����Ѿ���movie��
				if(m1.getMovieMerId().contains(form.getMovieMerId())){  //������̻��Ѿ������ⲿ��Ӱ
					//���ɵ�ӰƱ
					if(movieStartTime1!=null){ 
						createTicket(movieStartTime1, m1.getMovieId(),1,movieTimeLong,merId);
					}
					if(movieStartTime2!=null){
						createTicket(movieStartTime2, m1.getMovieId(),2,movieTimeLong,merId);
					}
					if(movieStartTime3!=null){
						createTicket(movieStartTime3, m1.getMovieId(),3,movieTimeLong,merId);
					}
					if(movieStartTime4!=null){
						createTicket(movieStartTime4, m1.getMovieId(),4,movieTimeLong,merId);
					}
					if(movieStartTime5!=null){
						createTicket(movieStartTime5, m1.getMovieId(),5,movieTimeLong,merId);
					}
					if(movieStartTime6!=null){
						createTicket(movieStartTime6, m1.getMovieId(),6,movieTimeLong,merId);
					}
					return;
				}
			}
				
			String str = oldMovieMerId+form.getMovieMerId()+";";
			md.updateMerchantId(oldMovieMerId,str);
			
			form.setMovieMerId(str);  //�޸ĵ�Ӱ���̻�ID
			md.addMovie(form); //��ӵ�Ӱ
			
			Movies insertedMovie = md.findMovieByTime(form.getMovieCreateTime());  //��ȡ���ո���ӵĵ�Ӱ
			
			//�������
			String[] classifys = classifyStr.split(";");  //��ȡ����������
			for(String s:classifys){
				Classifys c = new Classifys();
				c.setClassifyMovieId(insertedMovie.getMovieId());
				ClassifyName cn = md.findClassifyNameByName(s);  //��ȡ���Ͷ���
				c.setClassifyNameObj(cn); //�������Ͷ���
				md.addClassify(c);  //������ͱ�
			}
			
			//�������
			String[] arr = moviePro.split(";");
			for(String s:arr){
				Protagonists p = new Protagonists();
				p.setProMovieId(insertedMovie.getMovieId());
				p.setProName(s);
				md.addPro(p);  //�������
			}
			
			//���ɵ�ӰƱ
			if(movieStartTime1!=null){ 
				createTicket(movieStartTime1, insertedMovie.getMovieId(),1,movieTimeLong,merId);
			}
			if(movieStartTime2!=null){
				createTicket(movieStartTime2, insertedMovie.getMovieId(),2,movieTimeLong,merId);
			}
			if(movieStartTime3!=null){
				createTicket(movieStartTime3, insertedMovie.getMovieId(),3,movieTimeLong,merId);
			}
			if(movieStartTime4!=null){
				createTicket(movieStartTime4, insertedMovie.getMovieId(),4,movieTimeLong,merId);
			}
			if(movieStartTime5!=null){
				createTicket(movieStartTime5, insertedMovie.getMovieId(),5,movieTimeLong,merId);
			}
			if(movieStartTime6!=null){
				createTicket(movieStartTime6, insertedMovie.getMovieId(),6,movieTimeLong,merId);
			}
			
			//����ļ�·�������ݿ�
			int i = 1;
			for(String path:sqlPaths){  //Ԥ��Ƭ�����һ��
				if(i>3)
					break;
				Images img = new Images();
				img.setImgPath(path);
				if(i == 1)
					img.setImgStatus("����");
				else if(i == 2)
					img.setImgStatus("����");
				else if(i == 3)
					img.setImgStatus("��Ʒչʾ");
				img.setImgMovieId(insertedMovie.getMovieId());
				md.addImage(img);
				i++;
			}
			
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new MerchantException("ϵͳ�쳣�����Ժ�����");
			}
		}
	}

	/**
	 * ���ݵ�Ӱ�����ɵ�ӰƱ   һ����Ӱ��12 * 17 = 204����λ
	 * @param movieStartTime
	 * @param insertedMovie
	 * @param theaterNum
	 * @throws SQLException
	 */
	private void createTicket(String movieStartTime, Long insertedMovie,int theaterNum ,Long minute,Long merId) throws SQLException {
		Long num = 1l;
		for(int i=1;i<=12;i++){
			for(int j=1;j<=17;j++){
				Ticket t = new Ticket();
				t.setTicketStartTime(Timestamp.valueOf(movieStartTime));  //��ӳʱ��
				t.setTicketMovieStartTime(Timestamp.valueOf(movieStartTime)); //��ӳʱ��  ������ʵ��һ����
				t.setTicketLocation(i+"��"+j+"��");	//��λ
				t.setTicketMovieTheater(theaterNum+"����");  //��ӳ��
				t.setTicketMovieId(insertedMovie);;
				Long time = t.getTicketStartTime().getTime()+(minute*60*1000);  //�õ�����ʱ���
				t.setTicketMovieEndTime(new Timestamp(time));  //���ý���ʱ��
				t.setTicketStatus("1");
				t.setTicketMerId(merId);
				t.setTicketLocationNum(num);
				num++;
				md.addTicket(t);
			}
		}
	}

	/**
	 * //ͨ����Ӱ���������ݿ�����ͬ���̻�id
	 * @param movieName
	 * @return
	 * @throws MerchantException 
	 */
	public String getMovieMerIdByMovieName(String movieName) throws MerchantException {
		try {
			return md.getMovieMerIdByMovieName(movieName);
		} catch (SQLException e) {
			throw new MerchantException("ϵͳ�쳣�����Ժ�����");
		}
	}
}
