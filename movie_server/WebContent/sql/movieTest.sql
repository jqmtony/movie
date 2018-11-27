CREATE DATABASE movie	DEFAULT CHARACTER SET utf8;
USE movie;

/*管理员*/
create table `admins`(
	`adminId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`adminRegisterCode` varchar(255),  /*注册码*/
	`adminName` varchar(255),  /*姓名*/
	`adminTel` varchar(255),	/*手机号*/
	`adminAddr` varchar(255), /*地址*/
	`adminCreateTime` datetime,  /*创建时间*/
	`adminWeight` int(11),  /*权值*/
	`adminEmail` varchar(255), /*邮箱地址*/
	`adminPwd` varchar(255)  /*密码*/
	PRIMARY KEY (`adminId`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*管理员登录日志表*/
create table `adminLoginRecord`(
	`alrId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`alrLoginTime` datetime,  /*登录时间*/
	`alrLoginIp` varchar(255), /*登录IP*/
	`alrAdminId` int(11),  /*管理员Id*/
	`alrStatus` varchar(255) /*登录状态*/
);

/*用户*/
create table `users`(
	`userId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`userName` varchar(255),  /*姓名*/
	`userEmail` varchar(255),  /*邮箱地址*/
	`userPwd` varchar(255),  /*密码*/
	`userCreateTime` datetime  /*创建时间*/
);

/*管理员登录日志表*/
create table `userLoginRecord`(
	`ulrId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`ulrLoginTime` datetime,  /*登录时间*/
	`ulrLoginIp` varchar(255), /*登录IP*/
	`ulrAdminId` int(11),  /*管理员Id*/
	`ulrStatus` varchar(255) /*登录状态*/
);

/*商户表*/
create table `merchant`(
	`merId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`merName` varchar(255),  /*姓名*/
	`merDescribe` varchar(500)  /*商户描述*/
);

/*电影票*/
create table `ticket`(
	`ticketId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`ticketMovieId` int(11),  /*对应电影的ID*/
	`ticketStatus` varchar(255),  /*电影票的状态*/
);

/*积分卡*/
create table `integral`(
	`integralId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`integralUserId` int(11), /*对应用户ID*/
	`integralCount` int(11),  /*积分数量*/
	`integralGrade` int(11),  /*积分卡等级*/
);

/*订单*/
create table `indent`(
	`indentId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`indentUserId` int(11), /*对应用户ID*/
	`indentTicketId` int(11),  /*对应电影票ID*/
	`indentStatus` varchar(255),  /*订单状态*/
	`indentRemark` varchar(500)  /*订单备注*/
);

/*管理员收到或发出的信息*/
create table information(
	int(11) informId,
);

/*电影*/
create table `movies`(
	`movieId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`movieMerId` int(11), /*所在商户ID*/
	`movieIntegralNum` int(11), /*积分单量*/
	`movieName` varchar(255),  /*电影名*/
	`movieProId` varchar(255), /*主演ID*/
	`movieGradeNum` int(11), /*评分数*/
	`movieDescribe` varchar(500),  /*电影描述*/
	`movieImgId` int(11),  /*图片id*/
	`moviePath` varchar(500),  /*电影观看链接*/
	`moviePrice` decimal(7,2),  /*价格*/
	`movieStatus` varchar(255),	/*电影状态  0表示下架（卖完了） 1表示上架*/
	`movieCreateTime` datetime,	/*创建时间*/
	`movieClassifyId` int(11)  /*分类Id*/
);

/*电视剧*/
create table `teleplay`(
	`teleplayId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`teleplayMerId` int(11), /*所在商户ID*/
	`teleplayIntegralNum` int(11), /*积分数*/
	`teleplayName` varchar(255),  /*电视剧名*/
	`teleplayProId` int(11), /*主演ID*/
	`teleplayGradeNum` int(11), /*评分数*/
	`teleplayDescribe` varchar(500),  /*电视剧描述*/
	`teleplayImgId` int(11),  /*图片Id*/
	`teleplayPath` varchar(500),  /*电视剧观看链接*/
	`teleplayCreateTime` datetime,	/*创建时间*/
	`teleplayClassifyId` int(11)  /*分类id*/
);

/*图片表*/
create table `images`(
	`imgId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`imgMovieId` int(11),  /*电影ID*/
	`imgTeleplayId` int(11),	/*电视剧*/
	`imgStatus` varchar(255),	/*图片的展示地方    1为封面  2为订单展示   3为剧情介绍*/
	`imgPath`	varchar(255)	/*图片路径*/
);

/*主演表*/
create table `protagonists`(
	`proId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`proMovieId` int(11), /*对应电影ID*/
	`proTeleplayId` int(11),  /*对应电视剧ID*/
	`proName` varchar(255), /*主演姓名*/
	`proLink` varchar(255) /*主演者介绍链接*/
);

/*新闻*/
create table news(
	int(11) newId,
);

/*分类表*/
create table `classifys`(
	`classifyId` int(11) NOT NULL AUTO_INCREMENT, /*ID*/
	`classifyName` varchar(255),  /*类别名称*/
	`classifyDescribe` varchar(255) /*类别描述*/
);
