CREATE DATABASE movie	DEFAULT CHARACTER SET utf8;
USE movie;

/*����Ա*/
create table `admins`(
	`adminId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`adminRegisterCode` varchar(255),  /*ע����*/
	`adminName` varchar(255),  /*����*/
	`adminTel` varchar(255),	/*�ֻ���*/
	`adminAddr` varchar(255), /*��ַ*/
	`adminCreateTime` datetime,  /*����ʱ��*/
	`adminWeight` int(11),  /*Ȩֵ*/
	`adminEmail` varchar(255), /*�����ַ*/
	`adminPwd` varchar(255),  /*����*/
	PRIMARY KEY (`adminId`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*����Ա��¼��־��*/
create table `adminLoginRecord`(
	`alrId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`alrLoginTime` datetime,  /*��¼ʱ��*/
	`alrLoginIp` varchar(255), /*��¼IP*/
	`alrAdminId` int(11),  /*����ԱId*/
	`alrStatus` varchar(255) /*��¼״̬*/
);

/*�û�*/
create table `users`(
	`userId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`userName` varchar(255),  /*����*/
	`userEmail` varchar(255),  /*�����ַ*/
	`userPwd` varchar(255),  /*����*/
	`userCreateTime` datetime,  /*����ʱ��*/
	`userTel` varchar(255), /*�ֻ���*/
	`userPayNum` varchar(255), /*֧���˺�*/
	`userPayPwd` varchar(255) /*֧������*/
);

/*����Ա��¼��־��*/
create table `userLoginRecord`(
	`ulrId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`ulrLoginTime` datetime,  /*��¼ʱ��*/
	`ulrLoginIp` varchar(255), /*��¼IP*/
	`ulrAdminId` int(11),  /*����ԱId*/
	`ulrStatus` varchar(255) /*��¼״̬*/
);

/*�̻���*/
create table `merchant`(
	`merId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`merName` varchar(255),  /*��ʵ����*/
	`merTel` varchar(255),	/*�ֻ���*/
	`merEmail` varchar(255), /*����*/
	`merAddr` varchar(255),	/*ʵ����ַ*/
	`merPwd` varchar(255),	/*��¼����*/
	`merIDCard` varchar(255),	/*���֤����*/
	`merStatus` varchar(255)	/*�̻�״̬  0����  1����ʹ�� */
);

/*��ӰƱ*/
create table `ticket`(
	`ticketId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`ticketMovieId` int(11),  /*��Ӧ��Ӱ��ID*/
	`ticketStatus` varchar(255),  /*��ӰƱ��״̬*/
	`ticketBuyBy` int(11), /*������*/
	`ticketStartTime` int(11),  /*��ʼʱ��*/
	`ticketIndentId` int(11)  /*��Ӧ����*/
);

/*���ֿ�*/
create table `integral`(
	`integralId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`integralUserId` int(11), /*��Ӧ�û�ID*/
	`integralCount` int(11)  /*��������*/
);

/*����*/
create table `indent`(
	`indentId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`indentUserId` int(11), /*��Ӧ�û�ID*/
	`indentStatus` varchar(255),  /*����״̬*/
	`indentRemark` varchar(500),  /*������ע*/
	`indentCreateTime` datetime  /*����ʱ��*/
);

/*��Ӱ*/
create table `movies`(
	`movieId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`movieMerId` int(11), /*�����̻�ID*/
	`movieIntegralNum` int(11), /*���ֵ���*/
	`movieName` varchar(255),  /*��Ӱ��*/
	`movieGradeNum` int(11), /*������*/
	`movieDescribe` varchar(500),  /*��Ӱ����*/
	`moviePath` varchar(500),  /*��Ӱ�ۿ�����*/
	`moviePrice` decimal(7,2),  /*�۸�*/
	`movieStatus` varchar(255),	/*��Ӱ״̬  0��ʾ�¼ܣ������ˣ� 1��ʾ�ϼ�*/
	`movieCreateTime` datetime	/*�ϼ�ʱ��*/
);

/*���Ӿ�*/
create table `teleplay`(
	`teleplayId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`teleplayMerId` int(11), /*�����̻�ID*/
	`teleplayIntegralNum` int(11), /*������*/
	`teleplayName` varchar(255),  /*���Ӿ���*/
	`teleplayGradeNum` int(11), /*������*/
	`teleplayDescribe` varchar(500),  /*���Ӿ�����*/
	`teleplayPath` varchar(500),  /*���Ӿ�ۿ�����*/
	`teleplayCreateTime` datetime	/*�ϼ�ʱ��*/
);

/*ͼƬ��*/
create table `images`(
	`imgId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`imgMovieId` int(11),  /*��ӰID*/
	`imgTeleplayId` int(11),	/*���Ӿ�*/
	`imgMerId` int(11),  /*�̻��ŵ���*/
	`imgAdminId` int(11), /*����Աͷ��*/
	`imgUserId` int(11),	/*�û�ͷ��*/
	`imgMerchantId` int(11), /*��Ӧ�̻�*/
	`imgTicketId` int(11),  /*��Ӧ��ӰƱ*/
	`imgNewId` int(11),	/*��Ӧ����*/
	`imgStatus` varchar(255),	/*ͼƬ��չʾ�ط�    1Ϊ����  2Ϊ����չʾ   3Ϊ�������*/
	`imgPath`	varchar(255)	/*ͼƬ·��*/
);

/*���ݱ�*/
create table `protagonists`(
	`proId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`proMovieId` int(11), /*��Ӧ��ӰID*/
	`proAdminId` int(11),  /*��Ӧ����ԱID*/
	`proUserId` int(11), /*��Ӧ�û�ID*/ 
	`proTeleplayId` int(11),  /*��Ӧ���Ӿ�ID*/
	`proName` varchar(255), /*��������*/
	`proLink` varchar(255) /*�����߽�������*/
);

/*����*/
create table news(
	int(11) newId,
);

/*�����*/
create table `classifys`(
	`classifyId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`classifyMovieId` int(11),  /*��Ӧ��Ӱ*/
	`classifyTeleplayId` int(11), /*��Ӧ���Ӿ�*/
	`classifyName` varchar(255),  /*�������*/
	`classifyDescribe` varchar(255), /*�������*/
	`classifyParentId` int(11)  /*��ID*/
);
