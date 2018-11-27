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
	`adminPwd` varchar(255)  /*����*/
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
	`userCreateTime` datetime  /*����ʱ��*/
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
	`merName` varchar(255),  /*����*/
	`merDescribe` varchar(500)  /*�̻�����*/
);

/*��ӰƱ*/
create table `ticket`(
	`ticketId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`ticketMovieId` int(11),  /*��Ӧ��Ӱ��ID*/
	`ticketStatus` varchar(255),  /*��ӰƱ��״̬*/
);

/*���ֿ�*/
create table `integral`(
	`integralId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`integralUserId` int(11), /*��Ӧ�û�ID*/
	`integralCount` int(11),  /*��������*/
	`integralGrade` int(11),  /*���ֿ��ȼ�*/
);

/*����*/
create table `indent`(
	`indentId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`indentUserId` int(11), /*��Ӧ�û�ID*/
	`indentTicketId` int(11),  /*��Ӧ��ӰƱID*/
	`indentStatus` varchar(255),  /*����״̬*/
	`indentRemark` varchar(500)  /*������ע*/
);

/*����Ա�յ��򷢳�����Ϣ*/
create table information(
	int(11) informId,
);

/*��Ӱ*/
create table `movies`(
	`movieId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`movieMerId` int(11), /*�����̻�ID*/
	`movieIntegralNum` int(11), /*���ֵ���*/
	`movieName` varchar(255),  /*��Ӱ��*/
	`movieProId` varchar(255), /*����ID*/
	`movieGradeNum` int(11), /*������*/
	`movieDescribe` varchar(500),  /*��Ӱ����*/
	`movieImgId` int(11),  /*ͼƬid*/
	`moviePath` varchar(500),  /*��Ӱ�ۿ�����*/
	`moviePrice` decimal(7,2),  /*�۸�*/
	`movieStatus` varchar(255),	/*��Ӱ״̬  0��ʾ�¼ܣ������ˣ� 1��ʾ�ϼ�*/
	`movieCreateTime` datetime,	/*����ʱ��*/
	`movieClassifyId` int(11)  /*����Id*/
);

/*���Ӿ�*/
create table `teleplay`(
	`teleplayId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`teleplayMerId` int(11), /*�����̻�ID*/
	`teleplayIntegralNum` int(11), /*������*/
	`teleplayName` varchar(255),  /*���Ӿ���*/
	`teleplayProId` int(11), /*����ID*/
	`teleplayGradeNum` int(11), /*������*/
	`teleplayDescribe` varchar(500),  /*���Ӿ�����*/
	`teleplayImgId` int(11),  /*ͼƬId*/
	`teleplayPath` varchar(500),  /*���Ӿ�ۿ�����*/
	`teleplayCreateTime` datetime,	/*����ʱ��*/
	`teleplayClassifyId` int(11)  /*����id*/
);

/*ͼƬ��*/
create table `images`(
	`imgId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`imgMovieId` int(11),  /*��ӰID*/
	`imgTeleplayId` int(11),	/*���Ӿ�*/
	`imgStatus` varchar(255),	/*ͼƬ��չʾ�ط�    1Ϊ����  2Ϊ����չʾ   3Ϊ�������*/
	`imgPath`	varchar(255)	/*ͼƬ·��*/
);

/*���ݱ�*/
create table `protagonists`(
	`proId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`proMovieId` int(11), /*��Ӧ��ӰID*/
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
	`classifyName` varchar(255),  /*�������*/
	`classifyDescribe` varchar(255) /*�������*/
);
