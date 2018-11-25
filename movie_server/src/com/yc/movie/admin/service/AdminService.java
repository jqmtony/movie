package com.yc.movie.admin.service;

import java.sql.SQLException;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;
import com.yc.movie.admin.bean.AdminLoginRecord;
import com.yc.movie.admin.bean.Admins;
import com.yc.movie.admin.bean.Verification;
import com.yc.movie.admin.dao.AdminDao;
import com.yc.movie.admin.exception.AdminException;
import com.yc.movie.utils.DateUtils;
import com.yc.movie.utils.JdbcUtils;
import com.yc.movie.utils.MailUtils;

public class AdminService {
	private AdminDao ad = new AdminDao();
	public static final String EMAIL_REGX = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
	
	/**
	 * ��¼ҵ��
	 * @param form	
	 * @return ��½�ɹ���Admins����(��Ϣ������)
	 */
	public Admins login(Admins form,Verification v) throws AdminException{
		//�����֤�벻Ϊnull���ж���֤���Ƿ���ȷ
		// TODO Auto-generated method stub
		
		//�û�����ʽ(����)��֤  ������ʽ
		// TODO Auto-generated method stub
		
		//����ad��findAdminByEmail()�����ж��û���(����)�Ƿ����
		Admins admin = ad.findAdminByEmail(form.getAdminEmail());
		// TODO Auto-generated method stub
		
		//����ad��findAdminByPwd()�����ж������Ƿ���ȷ
		admin = ad.findAdminByPwd(form.getAdminEmail(),form.getAdminPwd());
		// TODO Auto-generated method stub
		
		//��¼�ɹ���������������   
		//�𾴵Ĺ���Ա��{0},����{0}��¼�˵�Ӱ��վ����ϵͳ��<a href="http://localhost:8080/movie_server/AdminServlet?method=alterPwd&alterId={0}">�����Լ���¼?�޸����룡</a>
		Object[] code = {admin.getAdminName(),DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),admin.getAdminId()};
		MailUtils.send(this.getClass(), admin.getAdminEmail(), code,MailUtils.LOGINED_EMAIL_FILENAME);
		
		
		return admin;
	}

	/**
	 * ����¼��¼������ӵ����ݿ�
	 * @param alr
	 * @throws AdminException 
	 */
	public void addALR(AdminLoginRecord alr) throws AdminException {
		try {
			JdbcUtils.beginTransaction();  //��������
			
			ad.addALR(alr);	//����AdminDao��addALR()�������
			
			JdbcUtils.commitTransaction();  //�ύ����
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();  //�ع�
			} catch (SQLException e1) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			} 
		}  
	}

	/**
	 * �����ʼ�������Ա  ����ͨ�����������޸�����
	 * @param adminEmail
	 * @throws AdminException 
	 */
	public Admins fotPwdSendEmail(String adminEmail) throws AdminException {
		Admins adm = ad.findAdminByEmail(adminEmail);  //�õ�Ҫ�޸�����Ĺ���Ա
		if(adm == null){   //������仹û��ע��
			throw new AdminException("�����仹û��ע�ᣬ����ע�ᣡ");
		}
		//�𾴵Ĺ���Ա��{0}��<a href="http://localhost:8080/movie_server/AdminServlet?method=alterPwd&alterId={0}">����޸�����</a>
		Object[] code = {adm.getAdminName(),adm.getAdminId()};  //��䲹λ
		MailUtils.send(this.getClass(), adminEmail, code,MailUtils.ALTER_PWD_EMAIL_FILENAME);	//�����ʼ�
		return adm;
	}

	/**
	 * ����Id���Admins����
	 * @param alterId
	 * @return
	 */
	public Admins findById(Long alterId) {
		return ad.findAdminById(alterId);
	}

	/**
	 * �޸�����
	 * @param form
	 * @param adminPwd2
	 * @return
	 * @throws AdminException 
	 */
	public Admins alterPwd(Admins form, String adminPwd2) throws AdminException {
		//�ж������Ƿ�Ϊnull   form.getAdminPwd()   adminPwd2
		// TODO Auto-generated method stub
		
		//�ж��������ʽ�Ƿ���ȷ
		// TODO Auto-generated method stub
		
		//�ж��������ȷ�������Ƿ���ͬ
		// TODO Auto-generated method stub
		
		//�����������
		// TODO Auto-generated method stub
		
		//�޸����ݿ�
		try {
			JdbcUtils.beginTransaction();
			
			ad.alterPwd(form);
			
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.roolbackTransaction();
			} catch (SQLException e1) {
				throw new AdminException("ϵͳ�쳣�����Ժ����ԣ�");
			}
		}
		//�޸ĳɹ����õ��޸���ɵ�Admins���󲢷���
		Admins adm = ad.findAdminByEmail(form.getAdminEmail());
		return adm;
	}
}
