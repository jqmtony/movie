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
import java.util.List;

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
	 * ��ͼƬ��ϢУ��
	 * @param form
	 * @param moviePro
	 * @param movieClassify
	 * @throws MerchantException 
	 */
	public void movieRegx(Movies form, String moviePro, String movieClassify) throws MerchantException {
		String name = form.getMovieName();
		BigDecimal price = form.getMoviePrice();
		String descript = form.getMovieDescribe();
		Long inte = form.getMovieIntegralNum();
		
		//�жϵ�Ӱ���Ƿ�Ϊnull
		if(name == null || name.trim().isEmpty())
			throw new MerchantException("�������Ӱ����");
		
		//�жϼ۸��Ƿ�Ϊnull
		if(price == null)
			throw new MerchantException("�������Ӱ���ۣ�");
		
		//�ж������Ƿ�Ϊnull
		if(descript == null || descript.trim().isEmpty())
			throw new MerchantException("�������Ӱ������");
		
		//�жϻ������Ƿ�Ϊnull
		if(inte == null)
			throw new MerchantException("�������������");
		
		//�ж������Ƿ�Ϊnull
		if(moviePro == null || moviePro.trim().isEmpty())
			throw new MerchantException("���������ݣ�");
		
		
	}

	/**
	 * ͼƬ��ϢУ��
	 * @param partList
	 * @throws MerchantException 
	 */
	public void movieRegx(List<Part> partList) throws MerchantException {
		if(partList == null || partList.size() < 3)
			throw new MerchantException("�ļ��ϴ��쳣��");
		
		if(partList.get(0).getSubmittedFileName() == null || partList.get(0).getSubmittedFileName().trim().isEmpty())
			throw new MerchantException("��ѡ�����ͼƬ��");
		
		if(partList.get(1).getSubmittedFileName() == null || partList.get(1).getSubmittedFileName().trim().isEmpty())
			throw new MerchantException("��ѡ�����ͼƬ��");
		
		if(partList.get(2).getSubmittedFileName() == null || partList.get(2).getSubmittedFileName().trim().isEmpty())
			throw new MerchantException("��ѡ��Ʒ��ʾͼƬ��");
	}

	/**
	 * ��Ӱ�ϼ�
	 * @param form	��Ӱ
	 * @param sqlPaths	ͼƬ·��
	 * @param moviePro	���ݼ�
	 * @param movieClassify	����
	 * @param movieCount  ��ӰƱ����
	 * @throws MerchantException
	 */
	public void addMovie(Movies form , List<String> sqlPaths ,String moviePro , List<String> movieClassify ,Integer movieCount , String movieTime) throws MerchantException {
		try {
			JdbcUtils.beginTransaction();
			md.addMovie(form);  //��ӵ�Ӱ
			
			Movies insertedMovie = md.findMovieByTime(form.getMovieCreateTime());  //��ȡ���ո���ӵĵ�Ӱ
			
			//�������
			for(String s:movieClassify){
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
			for(int i=0;i<movieCount;i++){
				Ticket t = new Ticket();
				t.setTicketStartTime(Timestamp.valueOf(movieTime));  //��ӳʱ��
				t.setMovie(insertedMovie);
				t.setTicketStatus("1");
				md.addTicket(t);
			}
			
			//���ͼƬ
			for(String s:sqlPaths){
				Images img = new Images();
				img.setImgPath(s);
				img.setImgMovieId(insertedMovie.getMovieId());
				md.addImage(img);
			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new MerchantException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
		
		
		
	}
}
