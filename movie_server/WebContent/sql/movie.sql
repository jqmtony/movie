/*
Navicat MySQL Data Transfer

Source Server         : user
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : movie

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2018-12-06 22:29:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `adminloginrecord`
-- ----------------------------
DROP TABLE IF EXISTS `adminloginrecord`;
CREATE TABLE `adminloginrecord` (
  `alrId` int(11) NOT NULL AUTO_INCREMENT,
  `alrLoginTime` datetime DEFAULT NULL,
  `alrLoginIp` varchar(255) DEFAULT NULL,
  `alrAdminId` int(11) DEFAULT NULL,
  `alrStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`alrId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminloginrecord
-- ----------------------------
INSERT INTO `adminloginrecord` VALUES ('1', '2018-12-06 20:50:28', '192.168.15.50', null, '失败');
INSERT INTO `adminloginrecord` VALUES ('2', '2018-12-06 20:50:55', '192.168.15.50', '1', '成功');

-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminRegisterCode` varchar(255) DEFAULT NULL,
  `adminName` varchar(255) DEFAULT NULL,
  `adminTel` varchar(255) DEFAULT NULL,
  `adminAddr` varchar(255) DEFAULT NULL,
  `adminCreateTime` datetime DEFAULT NULL,
  `adminWeight` int(11) DEFAULT NULL,
  `adminEmail` varchar(255) DEFAULT NULL,
  `adminPwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', '0000-0000-0000-0000', null, null, null, '2018-11-26 20:26:38', '10000', 'naivestruggle@126.com', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for `classifyname`
-- ----------------------------
DROP TABLE IF EXISTS `classifyname`;
CREATE TABLE `classifyname` (
  `classifyNameId` int(11) NOT NULL AUTO_INCREMENT,
  `classifyNameString` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classifyNameId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classifyname
-- ----------------------------
INSERT INTO `classifyname` VALUES ('1', '动作');
INSERT INTO `classifyname` VALUES ('2', '传记');
INSERT INTO `classifyname` VALUES ('3', '犯罪');
INSERT INTO `classifyname` VALUES ('4', '家庭');
INSERT INTO `classifyname` VALUES ('5', '恐怖');
INSERT INTO `classifyname` VALUES ('6', '浪漫');
INSERT INTO `classifyname` VALUES ('7', '体育');
INSERT INTO `classifyname` VALUES ('8', '战争');
INSERT INTO `classifyname` VALUES ('9', '冒险');
INSERT INTO `classifyname` VALUES ('10', '喜剧');
INSERT INTO `classifyname` VALUES ('11', '动作');
INSERT INTO `classifyname` VALUES ('12', '幻想');
INSERT INTO `classifyname` VALUES ('13', '惊悚');
INSERT INTO `classifyname` VALUES ('14', '动画');
INSERT INTO `classifyname` VALUES ('15', '服装');
INSERT INTO `classifyname` VALUES ('16', '历史');
INSERT INTO `classifyname` VALUES ('17', '音乐');
INSERT INTO `classifyname` VALUES ('18', '心理');

-- ----------------------------
-- Table structure for `classifys`
-- ----------------------------
DROP TABLE IF EXISTS `classifys`;
CREATE TABLE `classifys` (
  `classifyId` int(11) NOT NULL AUTO_INCREMENT,
  `classifyMovieId` int(11) DEFAULT NULL,
  `classifyTeleplayId` int(11) DEFAULT NULL,
  `classifyName` int(11) DEFAULT NULL,
  `classifyDescribe` varchar(255) DEFAULT NULL,
  `classifyParentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`classifyId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classifys
-- ----------------------------
INSERT INTO `classifys` VALUES ('1', '1', null, '13', null, null);
INSERT INTO `classifys` VALUES ('2', '1', null, '1', null, null);
INSERT INTO `classifys` VALUES ('3', '2', null, '7', null, null);
INSERT INTO `classifys` VALUES ('4', '2', null, '1', null, null);

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `commentUserId` int(11) DEFAULT NULL,
  `commentReplyId` int(11) DEFAULT NULL,
  `commentMovieId` int(11) DEFAULT NULL,
  `commentTeleplayId` int(11) DEFAULT NULL,
  `commentContent` varchar(255) DEFAULT NULL,
  `commentCreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', null, '2', null, '这是一部好看的电影', '2018-12-06 21:03:10');

-- ----------------------------
-- Table structure for `images`
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `imgId` int(11) NOT NULL AUTO_INCREMENT,
  `imgMovieId` int(11) DEFAULT NULL,
  `imgAdminId` int(11) DEFAULT NULL,
  `imgUserId` int(11) DEFAULT NULL,
  `imgMerchantId` int(11) DEFAULT NULL,
  `imgTeleplayId` int(11) DEFAULT NULL,
  `imgTicketId` int(11) DEFAULT NULL,
  `imgNewId` int(11) DEFAULT NULL,
  `imgStatus` varchar(255) DEFAULT NULL,
  `imgPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`imgId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('1', '1', null, null, null, null, null, null, '封面', '/merHeadCreateImage/2/c/EE5C0D7B9BC246BFB62F99B2BA3F6E66_1.jpg');
INSERT INTO `images` VALUES ('2', '1', null, null, null, null, null, null, '介绍', '/merHeadCreateImage/2/d/C57E4E75FCC04D948DBEF4E7353B3C49_2.jpg');
INSERT INTO `images` VALUES ('3', '1', null, null, null, null, null, null, '单品展示', '/merHeadCreateImage/2/e/68E5AC5FB9F8419FA1ECBCA75DB5355F_3.jpg');
INSERT INTO `images` VALUES ('4', '2', null, null, null, null, null, null, '封面', '/merHeadCreateImage/2/c/34DC70D9DD99475F83C5DABC8C6D0439_1.jpg');
INSERT INTO `images` VALUES ('5', '2', null, null, null, null, null, null, '介绍', '/merHeadCreateImage/2/d/E0A31580685C449486A7B4A4F760BE33_2.jpg');
INSERT INTO `images` VALUES ('6', '2', null, null, null, null, null, null, '单品展示', '/merHeadCreateImage/2/e/108556163F7A4CC9912FDAEB651B2506_3.jpg');

-- ----------------------------
-- Table structure for `indent`
-- ----------------------------
DROP TABLE IF EXISTS `indent`;
CREATE TABLE `indent` (
  `indentId` int(11) NOT NULL AUTO_INCREMENT,
  `indentUserId` int(11) DEFAULT NULL,
  `indentStatus` varchar(255) DEFAULT NULL,
  `indentRemark` varchar(500) DEFAULT NULL,
  `indentCreateTime` datetime DEFAULT NULL,
  `indentNum` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`indentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of indent
-- ----------------------------

-- ----------------------------
-- Table structure for `integral`
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral` (
  `integralId` int(11) NOT NULL AUTO_INCREMENT,
  `integralUserId` int(11) DEFAULT NULL,
  `integralCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`integralId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of integral
-- ----------------------------
INSERT INTO `integral` VALUES ('1', '1', '0');

-- ----------------------------
-- Table structure for `merchant`
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `merId` int(11) NOT NULL AUTO_INCREMENT,
  `merName` varchar(255) DEFAULT NULL,
  `merTel` varchar(255) DEFAULT NULL,
  `merEmail` varchar(255) DEFAULT NULL,
  `merAddr` varchar(255) DEFAULT NULL,
  `merPwd` varchar(255) DEFAULT NULL,
  `merIDCard` varchar(255) DEFAULT NULL,
  `merStatus` varchar(255) DEFAULT NULL,
  `merStoreName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`merId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES ('1', '杨鑫虎', '15570906290', 'naivestruggle@126.com', '中国 湖南省 衡阳市 珠晖区 湖南工学院', 'e10adc3949ba59abbe56e057f20f883e', null, '0', '十万达影城');

-- ----------------------------
-- Table structure for `movies`
-- ----------------------------
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `movieId` int(11) NOT NULL AUTO_INCREMENT,
  `movieMerId` varchar(255) DEFAULT NULL,
  `movieIntegralNum` int(11) DEFAULT NULL,
  `movieName` varchar(255) DEFAULT NULL,
  `movieGradeNum` double(8,4) DEFAULT NULL,
  `movieVisitCount` int(11) DEFAULT NULL,
  `movieDescribe` varchar(500) DEFAULT NULL,
  `moviePath` varchar(500) DEFAULT NULL,
  `moviePrice` decimal(7,2) DEFAULT NULL,
  `movieStatus` varchar(255) DEFAULT NULL,
  `movieCreateTime` datetime DEFAULT NULL,
  `movieTimeLong` int(11) DEFAULT NULL,
  `moviePrevue` varchar(255) DEFAULT NULL,
  `movieGenre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movieId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movies
-- ----------------------------
INSERT INTO `movies` VALUES ('1', '1;', '12', '巨齿鲨', '0.0000', '0', '一项由中国主导的国际科研项目，正在马里亚纳海沟深处进行时，遭遇未知生物攻击，科研人员被困海底。前美国海军陆战队深海潜水专家乔纳斯·泰勒受命前往营救，再度遭遇数年前曾经在深海给自己留下终身难以磨灭记忆的史前生物巨齿鲨。乔纳斯联手科研项目中的中国女科学家张苏茵成功营救了被困人员，但营救行动却意外造成了巨齿鲨逃离深海。当史前巨兽重返浅海，人类将为自己对大自然的贪婪付出惨重的代价......', null, '12.12', '1', '2018-12-06 20:25:00', '123', '/merHeadCreateImage/d/e/ADC0B2F8C15A4CEAA67726E09E9D7F6D_meigonghe.mp4', '国产3D');
INSERT INTO `movies` VALUES ('2', '1;', '10', '复仇者联盟3：无限战争', '0.0030', '0', '《复仇者联盟3：无限战争》是漫威电影宇宙10周年的历史性集结，将为影迷们带来史诗版的终极对决。面对灭霸突然发起的闪电袭击，复仇者联盟及其所有超级英雄盟友必须全力以赴，才能阻止他对全宇宙造成毁灭性的打击。', null, '12.12', '1', '2018-12-06 20:58:04', '143', '/merHeadCreateImage/d/e/F62CE4BA2AC248B99C9FFF88121C28A7_meigonghe.mp4', '国产3D');

-- ----------------------------
-- Table structure for `protagonists`
-- ----------------------------
DROP TABLE IF EXISTS `protagonists`;
CREATE TABLE `protagonists` (
  `proId` int(11) NOT NULL AUTO_INCREMENT,
  `proMovieId` int(11) DEFAULT NULL,
  `proTeleplayId` int(11) DEFAULT NULL,
  `proName` varchar(255) DEFAULT NULL,
  `proLink` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`proId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of protagonists
-- ----------------------------
INSERT INTO `protagonists` VALUES ('1', '1', null, ' 杰森·斯坦森', null);
INSERT INTO `protagonists` VALUES ('2', '1', null, '李冰冰 ', null);
INSERT INTO `protagonists` VALUES ('3', '2', null, '小罗伯特·唐尼', null);
INSERT INTO `protagonists` VALUES ('4', '2', null, '克里斯·海姆斯沃斯', null);

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `replyId` int(11) NOT NULL AUTO_INCREMENT,
  `replyUserId` int(11) DEFAULT NULL,
  `replyCommentId` int(11) DEFAULT NULL,
  `replyCreateTime` datetime DEFAULT NULL,
  `replyContent` varchar(255) DEFAULT NULL,
  `replyParId` int(11) DEFAULT NULL,
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '1', '1', '2018-12-06 21:04:36', '这是一条回复', null);

-- ----------------------------
-- Table structure for `teleplay`
-- ----------------------------
DROP TABLE IF EXISTS `teleplay`;
CREATE TABLE `teleplay` (
  `teleplayId` int(11) NOT NULL AUTO_INCREMENT,
  `teleplayMerId` int(11) DEFAULT NULL,
  `teleplayIntegralNum` int(11) DEFAULT NULL,
  `teleplayName` varchar(255) DEFAULT NULL,
  `teleplayGradeNum` int(11) DEFAULT NULL,
  `teleplayDescribe` varchar(500) DEFAULT NULL,
  `teleplayPath` varchar(500) DEFAULT NULL,
  `teleplayCreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`teleplayId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teleplay
-- ----------------------------

-- ----------------------------
-- Table structure for `ticket`
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticketId` int(11) NOT NULL AUTO_INCREMENT,
  `ticketMovieId` int(11) DEFAULT NULL,
  `ticketStatus` varchar(255) DEFAULT NULL,
  `ticketBuyBy` int(11) DEFAULT NULL,
  `ticketStartTime` datetime DEFAULT NULL,
  `ticketIndentId` int(11) DEFAULT NULL,
  `ticketLocation` varchar(255) DEFAULT NULL,
  `ticketMovieTheater` varchar(255) DEFAULT NULL,
  `ticketMovieStartTime` datetime DEFAULT NULL,
  `ticketMovieEndTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ticketId`)
) ENGINE=InnoDB AUTO_INCREMENT=613 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('1', '1', '1', null, '2018-12-18 20:24:37', null, '1排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('2', '1', '1', null, '2018-12-18 20:24:37', null, '1排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('3', '1', '1', null, '2018-12-18 20:24:37', null, '1排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('4', '1', '1', null, '2018-12-18 20:24:37', null, '1排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('5', '1', '1', null, '2018-12-18 20:24:37', null, '1排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('6', '1', '1', null, '2018-12-18 20:24:37', null, '1排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('7', '1', '1', null, '2018-12-18 20:24:37', null, '1排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('8', '1', '1', null, '2018-12-18 20:24:37', null, '1排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('9', '1', '1', null, '2018-12-18 20:24:37', null, '1排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('10', '1', '1', null, '2018-12-18 20:24:37', null, '1排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('11', '1', '1', null, '2018-12-18 20:24:37', null, '1排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('12', '1', '1', null, '2018-12-18 20:24:37', null, '1排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('13', '1', '1', null, '2018-12-18 20:24:37', null, '1排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('14', '1', '1', null, '2018-12-18 20:24:37', null, '1排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('15', '1', '1', null, '2018-12-18 20:24:37', null, '1排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('16', '1', '1', null, '2018-12-18 20:24:37', null, '1排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('17', '1', '1', null, '2018-12-18 20:24:37', null, '1排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('18', '1', '1', null, '2018-12-18 20:24:37', null, '2排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('19', '1', '1', null, '2018-12-18 20:24:37', null, '2排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('20', '1', '1', null, '2018-12-18 20:24:37', null, '2排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('21', '1', '1', null, '2018-12-18 20:24:37', null, '2排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('22', '1', '1', null, '2018-12-18 20:24:37', null, '2排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('23', '1', '1', null, '2018-12-18 20:24:37', null, '2排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('24', '1', '1', null, '2018-12-18 20:24:37', null, '2排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('25', '1', '1', null, '2018-12-18 20:24:37', null, '2排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('26', '1', '1', null, '2018-12-18 20:24:37', null, '2排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('27', '1', '1', null, '2018-12-18 20:24:37', null, '2排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('28', '1', '1', null, '2018-12-18 20:24:37', null, '2排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('29', '1', '1', null, '2018-12-18 20:24:37', null, '2排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('30', '1', '1', null, '2018-12-18 20:24:37', null, '2排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('31', '1', '1', null, '2018-12-18 20:24:37', null, '2排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('32', '1', '1', null, '2018-12-18 20:24:37', null, '2排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('33', '1', '1', null, '2018-12-18 20:24:37', null, '2排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('34', '1', '1', null, '2018-12-18 20:24:37', null, '2排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('35', '1', '1', null, '2018-12-18 20:24:37', null, '3排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('36', '1', '1', null, '2018-12-18 20:24:37', null, '3排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('37', '1', '1', null, '2018-12-18 20:24:37', null, '3排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('38', '1', '1', null, '2018-12-18 20:24:37', null, '3排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('39', '1', '1', null, '2018-12-18 20:24:37', null, '3排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('40', '1', '1', null, '2018-12-18 20:24:37', null, '3排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('41', '1', '1', null, '2018-12-18 20:24:37', null, '3排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('42', '1', '1', null, '2018-12-18 20:24:37', null, '3排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('43', '1', '1', null, '2018-12-18 20:24:37', null, '3排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('44', '1', '1', null, '2018-12-18 20:24:37', null, '3排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('45', '1', '1', null, '2018-12-18 20:24:37', null, '3排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('46', '1', '1', null, '2018-12-18 20:24:37', null, '3排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('47', '1', '1', null, '2018-12-18 20:24:37', null, '3排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('48', '1', '1', null, '2018-12-18 20:24:37', null, '3排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('49', '1', '1', null, '2018-12-18 20:24:37', null, '3排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('50', '1', '1', null, '2018-12-18 20:24:37', null, '3排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('51', '1', '1', null, '2018-12-18 20:24:37', null, '3排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('52', '1', '1', null, '2018-12-18 20:24:37', null, '4排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('53', '1', '1', null, '2018-12-18 20:24:37', null, '4排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('54', '1', '1', null, '2018-12-18 20:24:37', null, '4排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('55', '1', '1', null, '2018-12-18 20:24:37', null, '4排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('56', '1', '1', null, '2018-12-18 20:24:37', null, '4排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('57', '1', '1', null, '2018-12-18 20:24:37', null, '4排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('58', '1', '1', null, '2018-12-18 20:24:37', null, '4排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('59', '1', '1', null, '2018-12-18 20:24:37', null, '4排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('60', '1', '1', null, '2018-12-18 20:24:37', null, '4排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('61', '1', '1', null, '2018-12-18 20:24:37', null, '4排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('62', '1', '1', null, '2018-12-18 20:24:37', null, '4排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('63', '1', '1', null, '2018-12-18 20:24:37', null, '4排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('64', '1', '1', null, '2018-12-18 20:24:37', null, '4排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('65', '1', '1', null, '2018-12-18 20:24:37', null, '4排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('66', '1', '1', null, '2018-12-18 20:24:37', null, '4排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('67', '1', '1', null, '2018-12-18 20:24:37', null, '4排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('68', '1', '1', null, '2018-12-18 20:24:37', null, '4排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('69', '1', '1', null, '2018-12-18 20:24:37', null, '5排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('70', '1', '1', null, '2018-12-18 20:24:37', null, '5排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('71', '1', '1', null, '2018-12-18 20:24:37', null, '5排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('72', '1', '1', null, '2018-12-18 20:24:37', null, '5排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('73', '1', '1', null, '2018-12-18 20:24:37', null, '5排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('74', '1', '1', null, '2018-12-18 20:24:37', null, '5排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('75', '1', '1', null, '2018-12-18 20:24:37', null, '5排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('76', '1', '1', null, '2018-12-18 20:24:37', null, '5排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('77', '1', '1', null, '2018-12-18 20:24:37', null, '5排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('78', '1', '1', null, '2018-12-18 20:24:37', null, '5排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('79', '1', '1', null, '2018-12-18 20:24:37', null, '5排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('80', '1', '1', null, '2018-12-18 20:24:37', null, '5排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('81', '1', '1', null, '2018-12-18 20:24:37', null, '5排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('82', '1', '1', null, '2018-12-18 20:24:37', null, '5排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('83', '1', '1', null, '2018-12-18 20:24:37', null, '5排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('84', '1', '1', null, '2018-12-18 20:24:37', null, '5排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('85', '1', '1', null, '2018-12-18 20:24:37', null, '5排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('86', '1', '1', null, '2018-12-18 20:24:37', null, '6排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('87', '1', '1', null, '2018-12-18 20:24:37', null, '6排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('88', '1', '1', null, '2018-12-18 20:24:37', null, '6排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('89', '1', '1', null, '2018-12-18 20:24:37', null, '6排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('90', '1', '1', null, '2018-12-18 20:24:37', null, '6排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('91', '1', '1', null, '2018-12-18 20:24:37', null, '6排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('92', '1', '1', null, '2018-12-18 20:24:37', null, '6排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('93', '1', '1', null, '2018-12-18 20:24:37', null, '6排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('94', '1', '1', null, '2018-12-18 20:24:37', null, '6排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('95', '1', '1', null, '2018-12-18 20:24:37', null, '6排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('96', '1', '1', null, '2018-12-18 20:24:37', null, '6排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('97', '1', '1', null, '2018-12-18 20:24:37', null, '6排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('98', '1', '1', null, '2018-12-18 20:24:37', null, '6排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('99', '1', '1', null, '2018-12-18 20:24:37', null, '6排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('100', '1', '1', null, '2018-12-18 20:24:37', null, '6排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('101', '1', '1', null, '2018-12-18 20:24:37', null, '6排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('102', '1', '1', null, '2018-12-18 20:24:37', null, '6排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('103', '1', '1', null, '2018-12-18 20:24:37', null, '7排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('104', '1', '1', null, '2018-12-18 20:24:37', null, '7排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('105', '1', '1', null, '2018-12-18 20:24:37', null, '7排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('106', '1', '1', null, '2018-12-18 20:24:37', null, '7排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('107', '1', '1', null, '2018-12-18 20:24:37', null, '7排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('108', '1', '1', null, '2018-12-18 20:24:37', null, '7排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('109', '1', '1', null, '2018-12-18 20:24:37', null, '7排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('110', '1', '1', null, '2018-12-18 20:24:37', null, '7排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('111', '1', '1', null, '2018-12-18 20:24:37', null, '7排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('112', '1', '1', null, '2018-12-18 20:24:37', null, '7排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('113', '1', '1', null, '2018-12-18 20:24:37', null, '7排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('114', '1', '1', null, '2018-12-18 20:24:37', null, '7排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('115', '1', '1', null, '2018-12-18 20:24:37', null, '7排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('116', '1', '1', null, '2018-12-18 20:24:37', null, '7排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('117', '1', '1', null, '2018-12-18 20:24:37', null, '7排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('118', '1', '1', null, '2018-12-18 20:24:37', null, '7排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('119', '1', '1', null, '2018-12-18 20:24:37', null, '7排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('120', '1', '1', null, '2018-12-18 20:24:37', null, '8排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('121', '1', '1', null, '2018-12-18 20:24:37', null, '8排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('122', '1', '1', null, '2018-12-18 20:24:37', null, '8排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('123', '1', '1', null, '2018-12-18 20:24:37', null, '8排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('124', '1', '1', null, '2018-12-18 20:24:37', null, '8排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('125', '1', '1', null, '2018-12-18 20:24:37', null, '8排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('126', '1', '1', null, '2018-12-18 20:24:37', null, '8排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('127', '1', '1', null, '2018-12-18 20:24:37', null, '8排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('128', '1', '1', null, '2018-12-18 20:24:37', null, '8排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('129', '1', '1', null, '2018-12-18 20:24:37', null, '8排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('130', '1', '1', null, '2018-12-18 20:24:37', null, '8排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('131', '1', '1', null, '2018-12-18 20:24:37', null, '8排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('132', '1', '1', null, '2018-12-18 20:24:37', null, '8排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('133', '1', '1', null, '2018-12-18 20:24:37', null, '8排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('134', '1', '1', null, '2018-12-18 20:24:37', null, '8排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('135', '1', '1', null, '2018-12-18 20:24:37', null, '8排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('136', '1', '1', null, '2018-12-18 20:24:37', null, '8排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('137', '1', '1', null, '2018-12-18 20:24:37', null, '9排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('138', '1', '1', null, '2018-12-18 20:24:37', null, '9排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('139', '1', '1', null, '2018-12-18 20:24:37', null, '9排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('140', '1', '1', null, '2018-12-18 20:24:37', null, '9排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('141', '1', '1', null, '2018-12-18 20:24:37', null, '9排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('142', '1', '1', null, '2018-12-18 20:24:37', null, '9排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('143', '1', '1', null, '2018-12-18 20:24:37', null, '9排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('144', '1', '1', null, '2018-12-18 20:24:37', null, '9排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('145', '1', '1', null, '2018-12-18 20:24:37', null, '9排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('146', '1', '1', null, '2018-12-18 20:24:37', null, '9排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('147', '1', '1', null, '2018-12-18 20:24:37', null, '9排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('148', '1', '1', null, '2018-12-18 20:24:37', null, '9排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('149', '1', '1', null, '2018-12-18 20:24:37', null, '9排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('150', '1', '1', null, '2018-12-18 20:24:37', null, '9排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('151', '1', '1', null, '2018-12-18 20:24:37', null, '9排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('152', '1', '1', null, '2018-12-18 20:24:37', null, '9排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('153', '1', '1', null, '2018-12-18 20:24:37', null, '9排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('154', '1', '1', null, '2018-12-18 20:24:37', null, '10排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('155', '1', '1', null, '2018-12-18 20:24:37', null, '10排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('156', '1', '1', null, '2018-12-18 20:24:37', null, '10排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('157', '1', '1', null, '2018-12-18 20:24:37', null, '10排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('158', '1', '1', null, '2018-12-18 20:24:37', null, '10排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('159', '1', '1', null, '2018-12-18 20:24:37', null, '10排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('160', '1', '1', null, '2018-12-18 20:24:37', null, '10排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('161', '1', '1', null, '2018-12-18 20:24:37', null, '10排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('162', '1', '1', null, '2018-12-18 20:24:37', null, '10排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('163', '1', '1', null, '2018-12-18 20:24:37', null, '10排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('164', '1', '1', null, '2018-12-18 20:24:37', null, '10排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('165', '1', '1', null, '2018-12-18 20:24:37', null, '10排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('166', '1', '1', null, '2018-12-18 20:24:37', null, '10排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('167', '1', '1', null, '2018-12-18 20:24:37', null, '10排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('168', '1', '1', null, '2018-12-18 20:24:37', null, '10排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('169', '1', '1', null, '2018-12-18 20:24:37', null, '10排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('170', '1', '1', null, '2018-12-18 20:24:37', null, '10排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('171', '1', '1', null, '2018-12-18 20:24:37', null, '11排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('172', '1', '1', null, '2018-12-18 20:24:37', null, '11排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('173', '1', '1', null, '2018-12-18 20:24:37', null, '11排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('174', '1', '1', null, '2018-12-18 20:24:37', null, '11排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('175', '1', '1', null, '2018-12-18 20:24:37', null, '11排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('176', '1', '1', null, '2018-12-18 20:24:37', null, '11排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('177', '1', '1', null, '2018-12-18 20:24:37', null, '11排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('178', '1', '1', null, '2018-12-18 20:24:37', null, '11排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('179', '1', '1', null, '2018-12-18 20:24:37', null, '11排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('180', '1', '1', null, '2018-12-18 20:24:37', null, '11排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('181', '1', '1', null, '2018-12-18 20:24:37', null, '11排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('182', '1', '1', null, '2018-12-18 20:24:37', null, '11排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('183', '1', '1', null, '2018-12-18 20:24:37', null, '11排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('184', '1', '1', null, '2018-12-18 20:24:37', null, '11排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('185', '1', '1', null, '2018-12-18 20:24:37', null, '11排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('186', '1', '1', null, '2018-12-18 20:24:37', null, '11排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('187', '1', '1', null, '2018-12-18 20:24:37', null, '11排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('188', '1', '1', null, '2018-12-18 20:24:37', null, '12排1列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('189', '1', '1', null, '2018-12-18 20:24:37', null, '12排2列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('190', '1', '1', null, '2018-12-18 20:24:37', null, '12排3列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('191', '1', '1', null, '2018-12-18 20:24:37', null, '12排4列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('192', '1', '1', null, '2018-12-18 20:24:37', null, '12排5列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('193', '1', '1', null, '2018-12-18 20:24:37', null, '12排6列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('194', '1', '1', null, '2018-12-18 20:24:37', null, '12排7列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('195', '1', '1', null, '2018-12-18 20:24:37', null, '12排8列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('196', '1', '1', null, '2018-12-18 20:24:37', null, '12排9列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('197', '1', '1', null, '2018-12-18 20:24:37', null, '12排10列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('198', '1', '1', null, '2018-12-18 20:24:37', null, '12排11列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('199', '1', '1', null, '2018-12-18 20:24:37', null, '12排12列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('200', '1', '1', null, '2018-12-18 20:24:37', null, '12排13列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('201', '1', '1', null, '2018-12-18 20:24:37', null, '12排14列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('202', '1', '1', null, '2018-12-18 20:24:37', null, '12排15列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('203', '1', '1', null, '2018-12-18 20:24:37', null, '12排16列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('204', '1', '1', null, '2018-12-18 20:24:37', null, '12排17列', '2号厅', '2018-12-18 20:24:37', '2018-12-18 22:27:37');
INSERT INTO `ticket` VALUES ('205', '2', '1', null, '2018-12-11 21:02:04', null, '1排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('206', '2', '1', null, '2018-12-11 21:02:04', null, '1排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('207', '2', '1', null, '2018-12-11 21:02:04', null, '1排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('208', '2', '1', null, '2018-12-11 21:02:04', null, '1排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('209', '2', '1', null, '2018-12-11 21:02:04', null, '1排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('210', '2', '1', null, '2018-12-11 21:02:04', null, '1排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('211', '2', '1', null, '2018-12-11 21:02:04', null, '1排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('212', '2', '1', null, '2018-12-11 21:02:04', null, '1排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('213', '2', '1', null, '2018-12-11 21:02:04', null, '1排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('214', '2', '1', null, '2018-12-11 21:02:04', null, '1排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('215', '2', '1', null, '2018-12-11 21:02:04', null, '1排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('216', '2', '1', null, '2018-12-11 21:02:04', null, '1排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('217', '2', '1', null, '2018-12-11 21:02:04', null, '1排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('218', '2', '1', null, '2018-12-11 21:02:04', null, '1排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('219', '2', '1', null, '2018-12-11 21:02:04', null, '1排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('220', '2', '1', null, '2018-12-11 21:02:04', null, '1排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('221', '2', '1', null, '2018-12-11 21:02:04', null, '1排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('222', '2', '1', null, '2018-12-11 21:02:04', null, '2排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('223', '2', '1', null, '2018-12-11 21:02:04', null, '2排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('224', '2', '1', null, '2018-12-11 21:02:04', null, '2排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('225', '2', '1', null, '2018-12-11 21:02:04', null, '2排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('226', '2', '1', null, '2018-12-11 21:02:04', null, '2排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('227', '2', '1', null, '2018-12-11 21:02:04', null, '2排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('228', '2', '1', null, '2018-12-11 21:02:04', null, '2排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('229', '2', '1', null, '2018-12-11 21:02:04', null, '2排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('230', '2', '1', null, '2018-12-11 21:02:04', null, '2排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('231', '2', '1', null, '2018-12-11 21:02:04', null, '2排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('232', '2', '1', null, '2018-12-11 21:02:04', null, '2排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('233', '2', '1', null, '2018-12-11 21:02:04', null, '2排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('234', '2', '1', null, '2018-12-11 21:02:04', null, '2排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('235', '2', '1', null, '2018-12-11 21:02:04', null, '2排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('236', '2', '1', null, '2018-12-11 21:02:04', null, '2排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('237', '2', '1', null, '2018-12-11 21:02:04', null, '2排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('238', '2', '1', null, '2018-12-11 21:02:04', null, '2排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('239', '2', '1', null, '2018-12-11 21:02:04', null, '3排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('240', '2', '1', null, '2018-12-11 21:02:04', null, '3排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('241', '2', '1', null, '2018-12-11 21:02:04', null, '3排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('242', '2', '1', null, '2018-12-11 21:02:04', null, '3排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('243', '2', '1', null, '2018-12-11 21:02:04', null, '3排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('244', '2', '1', null, '2018-12-11 21:02:04', null, '3排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('245', '2', '1', null, '2018-12-11 21:02:04', null, '3排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('246', '2', '1', null, '2018-12-11 21:02:04', null, '3排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('247', '2', '1', null, '2018-12-11 21:02:04', null, '3排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('248', '2', '1', null, '2018-12-11 21:02:04', null, '3排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('249', '2', '1', null, '2018-12-11 21:02:04', null, '3排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('250', '2', '1', null, '2018-12-11 21:02:04', null, '3排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('251', '2', '1', null, '2018-12-11 21:02:04', null, '3排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('252', '2', '1', null, '2018-12-11 21:02:04', null, '3排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('253', '2', '1', null, '2018-12-11 21:02:04', null, '3排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('254', '2', '1', null, '2018-12-11 21:02:04', null, '3排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('255', '2', '1', null, '2018-12-11 21:02:04', null, '3排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('256', '2', '1', null, '2018-12-11 21:02:04', null, '4排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('257', '2', '1', null, '2018-12-11 21:02:04', null, '4排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('258', '2', '1', null, '2018-12-11 21:02:04', null, '4排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('259', '2', '1', null, '2018-12-11 21:02:04', null, '4排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('260', '2', '1', null, '2018-12-11 21:02:04', null, '4排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('261', '2', '1', null, '2018-12-11 21:02:04', null, '4排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('262', '2', '1', null, '2018-12-11 21:02:04', null, '4排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('263', '2', '1', null, '2018-12-11 21:02:04', null, '4排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('264', '2', '1', null, '2018-12-11 21:02:04', null, '4排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('265', '2', '1', null, '2018-12-11 21:02:04', null, '4排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('266', '2', '1', null, '2018-12-11 21:02:04', null, '4排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('267', '2', '1', null, '2018-12-11 21:02:04', null, '4排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('268', '2', '1', null, '2018-12-11 21:02:04', null, '4排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('269', '2', '1', null, '2018-12-11 21:02:04', null, '4排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('270', '2', '1', null, '2018-12-11 21:02:04', null, '4排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('271', '2', '1', null, '2018-12-11 21:02:04', null, '4排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('272', '2', '1', null, '2018-12-11 21:02:04', null, '4排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('273', '2', '1', null, '2018-12-11 21:02:04', null, '5排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('274', '2', '1', null, '2018-12-11 21:02:04', null, '5排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('275', '2', '1', null, '2018-12-11 21:02:04', null, '5排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('276', '2', '1', null, '2018-12-11 21:02:04', null, '5排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('277', '2', '1', null, '2018-12-11 21:02:04', null, '5排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('278', '2', '1', null, '2018-12-11 21:02:04', null, '5排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('279', '2', '1', null, '2018-12-11 21:02:04', null, '5排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('280', '2', '1', null, '2018-12-11 21:02:04', null, '5排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('281', '2', '1', null, '2018-12-11 21:02:04', null, '5排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('282', '2', '1', null, '2018-12-11 21:02:04', null, '5排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('283', '2', '1', null, '2018-12-11 21:02:04', null, '5排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('284', '2', '1', null, '2018-12-11 21:02:04', null, '5排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('285', '2', '1', null, '2018-12-11 21:02:04', null, '5排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('286', '2', '1', null, '2018-12-11 21:02:04', null, '5排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('287', '2', '1', null, '2018-12-11 21:02:04', null, '5排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('288', '2', '1', null, '2018-12-11 21:02:04', null, '5排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('289', '2', '1', null, '2018-12-11 21:02:04', null, '5排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('290', '2', '1', null, '2018-12-11 21:02:04', null, '6排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('291', '2', '1', null, '2018-12-11 21:02:04', null, '6排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('292', '2', '1', null, '2018-12-11 21:02:04', null, '6排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('293', '2', '1', null, '2018-12-11 21:02:04', null, '6排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('294', '2', '1', null, '2018-12-11 21:02:04', null, '6排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('295', '2', '1', null, '2018-12-11 21:02:04', null, '6排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('296', '2', '1', null, '2018-12-11 21:02:04', null, '6排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('297', '2', '1', null, '2018-12-11 21:02:04', null, '6排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('298', '2', '1', null, '2018-12-11 21:02:04', null, '6排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('299', '2', '1', null, '2018-12-11 21:02:04', null, '6排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('300', '2', '1', null, '2018-12-11 21:02:04', null, '6排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('301', '2', '1', null, '2018-12-11 21:02:04', null, '6排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('302', '2', '1', null, '2018-12-11 21:02:04', null, '6排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('303', '2', '1', null, '2018-12-11 21:02:04', null, '6排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('304', '2', '1', null, '2018-12-11 21:02:04', null, '6排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('305', '2', '1', null, '2018-12-11 21:02:04', null, '6排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('306', '2', '1', null, '2018-12-11 21:02:04', null, '6排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('307', '2', '1', null, '2018-12-11 21:02:04', null, '7排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('308', '2', '1', null, '2018-12-11 21:02:04', null, '7排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('309', '2', '1', null, '2018-12-11 21:02:04', null, '7排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('310', '2', '1', null, '2018-12-11 21:02:04', null, '7排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('311', '2', '1', null, '2018-12-11 21:02:04', null, '7排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('312', '2', '1', null, '2018-12-11 21:02:04', null, '7排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('313', '2', '1', null, '2018-12-11 21:02:04', null, '7排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('314', '2', '1', null, '2018-12-11 21:02:04', null, '7排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('315', '2', '1', null, '2018-12-11 21:02:04', null, '7排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('316', '2', '1', null, '2018-12-11 21:02:04', null, '7排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('317', '2', '1', null, '2018-12-11 21:02:04', null, '7排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('318', '2', '1', null, '2018-12-11 21:02:04', null, '7排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('319', '2', '1', null, '2018-12-11 21:02:04', null, '7排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('320', '2', '1', null, '2018-12-11 21:02:04', null, '7排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('321', '2', '1', null, '2018-12-11 21:02:04', null, '7排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('322', '2', '1', null, '2018-12-11 21:02:04', null, '7排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('323', '2', '1', null, '2018-12-11 21:02:04', null, '7排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('324', '2', '1', null, '2018-12-11 21:02:04', null, '8排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('325', '2', '1', null, '2018-12-11 21:02:04', null, '8排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('326', '2', '1', null, '2018-12-11 21:02:04', null, '8排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('327', '2', '1', null, '2018-12-11 21:02:04', null, '8排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('328', '2', '1', null, '2018-12-11 21:02:04', null, '8排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('329', '2', '1', null, '2018-12-11 21:02:04', null, '8排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('330', '2', '1', null, '2018-12-11 21:02:04', null, '8排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('331', '2', '1', null, '2018-12-11 21:02:04', null, '8排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('332', '2', '1', null, '2018-12-11 21:02:04', null, '8排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('333', '2', '1', null, '2018-12-11 21:02:04', null, '8排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('334', '2', '1', null, '2018-12-11 21:02:04', null, '8排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('335', '2', '1', null, '2018-12-11 21:02:04', null, '8排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('336', '2', '1', null, '2018-12-11 21:02:04', null, '8排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('337', '2', '1', null, '2018-12-11 21:02:04', null, '8排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('338', '2', '1', null, '2018-12-11 21:02:04', null, '8排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('339', '2', '1', null, '2018-12-11 21:02:04', null, '8排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('340', '2', '1', null, '2018-12-11 21:02:04', null, '8排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('341', '2', '1', null, '2018-12-11 21:02:04', null, '9排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('342', '2', '1', null, '2018-12-11 21:02:04', null, '9排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('343', '2', '1', null, '2018-12-11 21:02:04', null, '9排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('344', '2', '1', null, '2018-12-11 21:02:04', null, '9排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('345', '2', '1', null, '2018-12-11 21:02:04', null, '9排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('346', '2', '1', null, '2018-12-11 21:02:04', null, '9排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('347', '2', '1', null, '2018-12-11 21:02:04', null, '9排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('348', '2', '1', null, '2018-12-11 21:02:04', null, '9排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('349', '2', '1', null, '2018-12-11 21:02:04', null, '9排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('350', '2', '1', null, '2018-12-11 21:02:04', null, '9排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('351', '2', '1', null, '2018-12-11 21:02:04', null, '9排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('352', '2', '1', null, '2018-12-11 21:02:04', null, '9排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('353', '2', '1', null, '2018-12-11 21:02:04', null, '9排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('354', '2', '1', null, '2018-12-11 21:02:04', null, '9排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('355', '2', '1', null, '2018-12-11 21:02:04', null, '9排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('356', '2', '1', null, '2018-12-11 21:02:04', null, '9排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('357', '2', '1', null, '2018-12-11 21:02:04', null, '9排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('358', '2', '1', null, '2018-12-11 21:02:04', null, '10排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('359', '2', '1', null, '2018-12-11 21:02:04', null, '10排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('360', '2', '1', null, '2018-12-11 21:02:04', null, '10排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('361', '2', '1', null, '2018-12-11 21:02:04', null, '10排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('362', '2', '1', null, '2018-12-11 21:02:04', null, '10排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('363', '2', '1', null, '2018-12-11 21:02:04', null, '10排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('364', '2', '1', null, '2018-12-11 21:02:04', null, '10排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('365', '2', '1', null, '2018-12-11 21:02:04', null, '10排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('366', '2', '1', null, '2018-12-11 21:02:04', null, '10排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('367', '2', '1', null, '2018-12-11 21:02:04', null, '10排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('368', '2', '1', null, '2018-12-11 21:02:04', null, '10排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('369', '2', '1', null, '2018-12-11 21:02:04', null, '10排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('370', '2', '1', null, '2018-12-11 21:02:04', null, '10排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('371', '2', '1', null, '2018-12-11 21:02:04', null, '10排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('372', '2', '1', null, '2018-12-11 21:02:04', null, '10排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('373', '2', '1', null, '2018-12-11 21:02:04', null, '10排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('374', '2', '1', null, '2018-12-11 21:02:04', null, '10排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('375', '2', '1', null, '2018-12-11 21:02:04', null, '11排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('376', '2', '1', null, '2018-12-11 21:02:04', null, '11排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('377', '2', '1', null, '2018-12-11 21:02:04', null, '11排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('378', '2', '1', null, '2018-12-11 21:02:04', null, '11排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('379', '2', '1', null, '2018-12-11 21:02:04', null, '11排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('380', '2', '1', null, '2018-12-11 21:02:04', null, '11排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('381', '2', '1', null, '2018-12-11 21:02:04', null, '11排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('382', '2', '1', null, '2018-12-11 21:02:04', null, '11排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('383', '2', '1', null, '2018-12-11 21:02:04', null, '11排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('384', '2', '1', null, '2018-12-11 21:02:04', null, '11排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('385', '2', '1', null, '2018-12-11 21:02:04', null, '11排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('386', '2', '1', null, '2018-12-11 21:02:04', null, '11排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('387', '2', '1', null, '2018-12-11 21:02:04', null, '11排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('388', '2', '1', null, '2018-12-11 21:02:04', null, '11排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('389', '2', '1', null, '2018-12-11 21:02:04', null, '11排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('390', '2', '1', null, '2018-12-11 21:02:04', null, '11排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('391', '2', '1', null, '2018-12-11 21:02:04', null, '11排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('392', '2', '1', null, '2018-12-11 21:02:04', null, '12排1列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('393', '2', '1', null, '2018-12-11 21:02:04', null, '12排2列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('394', '2', '1', null, '2018-12-11 21:02:04', null, '12排3列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('395', '2', '1', null, '2018-12-11 21:02:04', null, '12排4列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('396', '2', '1', null, '2018-12-11 21:02:04', null, '12排5列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('397', '2', '1', null, '2018-12-11 21:02:04', null, '12排6列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('398', '2', '1', null, '2018-12-11 21:02:04', null, '12排7列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('399', '2', '1', null, '2018-12-11 21:02:04', null, '12排8列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('400', '2', '1', null, '2018-12-11 21:02:04', null, '12排9列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('401', '2', '1', null, '2018-12-11 21:02:04', null, '12排10列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('402', '2', '1', null, '2018-12-11 21:02:04', null, '12排11列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('403', '2', '1', null, '2018-12-11 21:02:04', null, '12排12列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('404', '2', '1', null, '2018-12-11 21:02:04', null, '12排13列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('405', '2', '1', null, '2018-12-11 21:02:04', null, '12排14列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('406', '2', '1', null, '2018-12-11 21:02:04', null, '12排15列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('407', '2', '1', null, '2018-12-11 21:02:04', null, '12排16列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('408', '2', '1', null, '2018-12-11 21:02:04', null, '12排17列', '2号厅', '2018-12-11 21:02:04', '2018-12-11 23:25:04');
INSERT INTO `ticket` VALUES ('409', '2', '1', null, '2018-12-18 21:00:53', null, '1排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('410', '2', '1', null, '2018-12-18 21:00:53', null, '1排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('411', '2', '1', null, '2018-12-18 21:00:53', null, '1排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('412', '2', '1', null, '2018-12-18 21:00:53', null, '1排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('413', '2', '1', null, '2018-12-18 21:00:53', null, '1排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('414', '2', '1', null, '2018-12-18 21:00:53', null, '1排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('415', '2', '1', null, '2018-12-18 21:00:53', null, '1排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('416', '2', '1', null, '2018-12-18 21:00:53', null, '1排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('417', '2', '1', null, '2018-12-18 21:00:53', null, '1排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('418', '2', '1', null, '2018-12-18 21:00:53', null, '1排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('419', '2', '1', null, '2018-12-18 21:00:53', null, '1排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('420', '2', '1', null, '2018-12-18 21:00:53', null, '1排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('421', '2', '1', null, '2018-12-18 21:00:53', null, '1排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('422', '2', '1', null, '2018-12-18 21:00:53', null, '1排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('423', '2', '1', null, '2018-12-18 21:00:53', null, '1排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('424', '2', '1', null, '2018-12-18 21:00:53', null, '1排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('425', '2', '1', null, '2018-12-18 21:00:53', null, '1排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('426', '2', '1', null, '2018-12-18 21:00:53', null, '2排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('427', '2', '1', null, '2018-12-18 21:00:53', null, '2排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('428', '2', '1', null, '2018-12-18 21:00:53', null, '2排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('429', '2', '1', null, '2018-12-18 21:00:53', null, '2排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('430', '2', '1', null, '2018-12-18 21:00:53', null, '2排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('431', '2', '1', null, '2018-12-18 21:00:53', null, '2排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('432', '2', '1', null, '2018-12-18 21:00:53', null, '2排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('433', '2', '1', null, '2018-12-18 21:00:53', null, '2排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('434', '2', '1', null, '2018-12-18 21:00:53', null, '2排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('435', '2', '1', null, '2018-12-18 21:00:53', null, '2排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('436', '2', '1', null, '2018-12-18 21:00:53', null, '2排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('437', '2', '1', null, '2018-12-18 21:00:53', null, '2排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('438', '2', '1', null, '2018-12-18 21:00:53', null, '2排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('439', '2', '1', null, '2018-12-18 21:00:53', null, '2排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('440', '2', '1', null, '2018-12-18 21:00:53', null, '2排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('441', '2', '1', null, '2018-12-18 21:00:53', null, '2排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('442', '2', '1', null, '2018-12-18 21:00:53', null, '2排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('443', '2', '1', null, '2018-12-18 21:00:53', null, '3排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('444', '2', '1', null, '2018-12-18 21:00:53', null, '3排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('445', '2', '1', null, '2018-12-18 21:00:53', null, '3排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('446', '2', '1', null, '2018-12-18 21:00:53', null, '3排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('447', '2', '1', null, '2018-12-18 21:00:53', null, '3排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('448', '2', '1', null, '2018-12-18 21:00:53', null, '3排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('449', '2', '1', null, '2018-12-18 21:00:53', null, '3排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('450', '2', '1', null, '2018-12-18 21:00:53', null, '3排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('451', '2', '1', null, '2018-12-18 21:00:53', null, '3排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('452', '2', '1', null, '2018-12-18 21:00:53', null, '3排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('453', '2', '1', null, '2018-12-18 21:00:53', null, '3排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('454', '2', '1', null, '2018-12-18 21:00:53', null, '3排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('455', '2', '1', null, '2018-12-18 21:00:53', null, '3排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('456', '2', '1', null, '2018-12-18 21:00:53', null, '3排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('457', '2', '1', null, '2018-12-18 21:00:53', null, '3排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('458', '2', '1', null, '2018-12-18 21:00:53', null, '3排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('459', '2', '1', null, '2018-12-18 21:00:53', null, '3排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('460', '2', '1', null, '2018-12-18 21:00:53', null, '4排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('461', '2', '1', null, '2018-12-18 21:00:53', null, '4排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('462', '2', '1', null, '2018-12-18 21:00:53', null, '4排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('463', '2', '1', null, '2018-12-18 21:00:53', null, '4排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('464', '2', '1', null, '2018-12-18 21:00:53', null, '4排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('465', '2', '1', null, '2018-12-18 21:00:53', null, '4排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('466', '2', '1', null, '2018-12-18 21:00:53', null, '4排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('467', '2', '1', null, '2018-12-18 21:00:53', null, '4排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('468', '2', '1', null, '2018-12-18 21:00:53', null, '4排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('469', '2', '1', null, '2018-12-18 21:00:53', null, '4排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('470', '2', '1', null, '2018-12-18 21:00:53', null, '4排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('471', '2', '1', null, '2018-12-18 21:00:53', null, '4排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('472', '2', '1', null, '2018-12-18 21:00:53', null, '4排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('473', '2', '1', null, '2018-12-18 21:00:53', null, '4排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('474', '2', '1', null, '2018-12-18 21:00:53', null, '4排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('475', '2', '1', null, '2018-12-18 21:00:53', null, '4排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('476', '2', '1', null, '2018-12-18 21:00:53', null, '4排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('477', '2', '1', null, '2018-12-18 21:00:53', null, '5排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('478', '2', '1', null, '2018-12-18 21:00:53', null, '5排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('479', '2', '1', null, '2018-12-18 21:00:53', null, '5排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('480', '2', '1', null, '2018-12-18 21:00:53', null, '5排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('481', '2', '1', null, '2018-12-18 21:00:53', null, '5排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('482', '2', '1', null, '2018-12-18 21:00:53', null, '5排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('483', '2', '1', null, '2018-12-18 21:00:53', null, '5排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('484', '2', '1', null, '2018-12-18 21:00:53', null, '5排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('485', '2', '1', null, '2018-12-18 21:00:53', null, '5排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('486', '2', '1', null, '2018-12-18 21:00:53', null, '5排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('487', '2', '1', null, '2018-12-18 21:00:53', null, '5排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('488', '2', '1', null, '2018-12-18 21:00:53', null, '5排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('489', '2', '1', null, '2018-12-18 21:00:53', null, '5排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('490', '2', '1', null, '2018-12-18 21:00:53', null, '5排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('491', '2', '1', null, '2018-12-18 21:00:53', null, '5排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('492', '2', '1', null, '2018-12-18 21:00:53', null, '5排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('493', '2', '1', null, '2018-12-18 21:00:53', null, '5排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('494', '2', '1', null, '2018-12-18 21:00:53', null, '6排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('495', '2', '1', null, '2018-12-18 21:00:53', null, '6排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('496', '2', '1', null, '2018-12-18 21:00:53', null, '6排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('497', '2', '1', null, '2018-12-18 21:00:53', null, '6排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('498', '2', '1', null, '2018-12-18 21:00:53', null, '6排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('499', '2', '1', null, '2018-12-18 21:00:53', null, '6排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('500', '2', '1', null, '2018-12-18 21:00:53', null, '6排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('501', '2', '1', null, '2018-12-18 21:00:53', null, '6排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('502', '2', '1', null, '2018-12-18 21:00:53', null, '6排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('503', '2', '1', null, '2018-12-18 21:00:53', null, '6排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('504', '2', '1', null, '2018-12-18 21:00:53', null, '6排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('505', '2', '1', null, '2018-12-18 21:00:53', null, '6排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('506', '2', '1', null, '2018-12-18 21:00:53', null, '6排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('507', '2', '1', null, '2018-12-18 21:00:53', null, '6排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('508', '2', '1', null, '2018-12-18 21:00:53', null, '6排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('509', '2', '1', null, '2018-12-18 21:00:53', null, '6排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('510', '2', '1', null, '2018-12-18 21:00:53', null, '6排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('511', '2', '1', null, '2018-12-18 21:00:53', null, '7排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('512', '2', '1', null, '2018-12-18 21:00:53', null, '7排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('513', '2', '1', null, '2018-12-18 21:00:53', null, '7排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('514', '2', '1', null, '2018-12-18 21:00:53', null, '7排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('515', '2', '1', null, '2018-12-18 21:00:53', null, '7排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('516', '2', '1', null, '2018-12-18 21:00:53', null, '7排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('517', '2', '1', null, '2018-12-18 21:00:53', null, '7排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('518', '2', '1', null, '2018-12-18 21:00:53', null, '7排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('519', '2', '1', null, '2018-12-18 21:00:53', null, '7排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('520', '2', '1', null, '2018-12-18 21:00:53', null, '7排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('521', '2', '1', null, '2018-12-18 21:00:53', null, '7排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('522', '2', '1', null, '2018-12-18 21:00:53', null, '7排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('523', '2', '1', null, '2018-12-18 21:00:53', null, '7排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('524', '2', '1', null, '2018-12-18 21:00:53', null, '7排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('525', '2', '1', null, '2018-12-18 21:00:53', null, '7排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('526', '2', '1', null, '2018-12-18 21:00:53', null, '7排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('527', '2', '1', null, '2018-12-18 21:00:53', null, '7排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('528', '2', '1', null, '2018-12-18 21:00:53', null, '8排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('529', '2', '1', null, '2018-12-18 21:00:53', null, '8排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('530', '2', '1', null, '2018-12-18 21:00:53', null, '8排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('531', '2', '1', null, '2018-12-18 21:00:53', null, '8排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('532', '2', '1', null, '2018-12-18 21:00:53', null, '8排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('533', '2', '1', null, '2018-12-18 21:00:53', null, '8排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('534', '2', '1', null, '2018-12-18 21:00:53', null, '8排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('535', '2', '1', null, '2018-12-18 21:00:53', null, '8排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('536', '2', '1', null, '2018-12-18 21:00:53', null, '8排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('537', '2', '1', null, '2018-12-18 21:00:53', null, '8排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('538', '2', '1', null, '2018-12-18 21:00:53', null, '8排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('539', '2', '1', null, '2018-12-18 21:00:53', null, '8排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('540', '2', '1', null, '2018-12-18 21:00:53', null, '8排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('541', '2', '1', null, '2018-12-18 21:00:53', null, '8排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('542', '2', '1', null, '2018-12-18 21:00:53', null, '8排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('543', '2', '1', null, '2018-12-18 21:00:53', null, '8排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('544', '2', '1', null, '2018-12-18 21:00:53', null, '8排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('545', '2', '1', null, '2018-12-18 21:00:53', null, '9排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('546', '2', '1', null, '2018-12-18 21:00:53', null, '9排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('547', '2', '1', null, '2018-12-18 21:00:53', null, '9排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('548', '2', '1', null, '2018-12-18 21:00:53', null, '9排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('549', '2', '1', null, '2018-12-18 21:00:53', null, '9排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('550', '2', '1', null, '2018-12-18 21:00:53', null, '9排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('551', '2', '1', null, '2018-12-18 21:00:53', null, '9排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('552', '2', '1', null, '2018-12-18 21:00:53', null, '9排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('553', '2', '1', null, '2018-12-18 21:00:53', null, '9排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('554', '2', '1', null, '2018-12-18 21:00:53', null, '9排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('555', '2', '1', null, '2018-12-18 21:00:53', null, '9排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('556', '2', '1', null, '2018-12-18 21:00:53', null, '9排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('557', '2', '1', null, '2018-12-18 21:00:53', null, '9排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('558', '2', '1', null, '2018-12-18 21:00:53', null, '9排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('559', '2', '1', null, '2018-12-18 21:00:53', null, '9排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('560', '2', '1', null, '2018-12-18 21:00:53', null, '9排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('561', '2', '1', null, '2018-12-18 21:00:53', null, '9排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('562', '2', '1', null, '2018-12-18 21:00:53', null, '10排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('563', '2', '1', null, '2018-12-18 21:00:53', null, '10排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('564', '2', '1', null, '2018-12-18 21:00:53', null, '10排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('565', '2', '1', null, '2018-12-18 21:00:53', null, '10排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('566', '2', '1', null, '2018-12-18 21:00:53', null, '10排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('567', '2', '1', null, '2018-12-18 21:00:53', null, '10排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('568', '2', '1', null, '2018-12-18 21:00:53', null, '10排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('569', '2', '1', null, '2018-12-18 21:00:53', null, '10排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('570', '2', '1', null, '2018-12-18 21:00:53', null, '10排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('571', '2', '1', null, '2018-12-18 21:00:53', null, '10排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('572', '2', '1', null, '2018-12-18 21:00:53', null, '10排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('573', '2', '1', null, '2018-12-18 21:00:53', null, '10排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('574', '2', '1', null, '2018-12-18 21:00:53', null, '10排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('575', '2', '1', null, '2018-12-18 21:00:53', null, '10排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('576', '2', '1', null, '2018-12-18 21:00:53', null, '10排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('577', '2', '1', null, '2018-12-18 21:00:53', null, '10排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('578', '2', '1', null, '2018-12-18 21:00:53', null, '10排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('579', '2', '1', null, '2018-12-18 21:00:53', null, '11排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('580', '2', '1', null, '2018-12-18 21:00:53', null, '11排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('581', '2', '1', null, '2018-12-18 21:00:53', null, '11排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('582', '2', '1', null, '2018-12-18 21:00:53', null, '11排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('583', '2', '1', null, '2018-12-18 21:00:53', null, '11排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('584', '2', '1', null, '2018-12-18 21:00:53', null, '11排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('585', '2', '1', null, '2018-12-18 21:00:53', null, '11排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('586', '2', '1', null, '2018-12-18 21:00:53', null, '11排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('587', '2', '1', null, '2018-12-18 21:00:53', null, '11排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('588', '2', '1', null, '2018-12-18 21:00:53', null, '11排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('589', '2', '1', null, '2018-12-18 21:00:53', null, '11排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('590', '2', '1', null, '2018-12-18 21:00:53', null, '11排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('591', '2', '1', null, '2018-12-18 21:00:53', null, '11排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('592', '2', '1', null, '2018-12-18 21:00:53', null, '11排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('593', '2', '1', null, '2018-12-18 21:00:53', null, '11排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('594', '2', '1', null, '2018-12-18 21:00:53', null, '11排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('595', '2', '1', null, '2018-12-18 21:00:53', null, '11排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('596', '2', '1', null, '2018-12-18 21:00:53', null, '12排1列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('597', '2', '1', null, '2018-12-18 21:00:53', null, '12排2列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('598', '2', '1', null, '2018-12-18 21:00:53', null, '12排3列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('599', '2', '1', null, '2018-12-18 21:00:53', null, '12排4列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('600', '2', '1', null, '2018-12-18 21:00:53', null, '12排5列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('601', '2', '1', null, '2018-12-18 21:00:53', null, '12排6列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('602', '2', '1', null, '2018-12-18 21:00:53', null, '12排7列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('603', '2', '1', null, '2018-12-18 21:00:53', null, '12排8列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('604', '2', '1', null, '2018-12-18 21:00:53', null, '12排9列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('605', '2', '1', null, '2018-12-18 21:00:53', null, '12排10列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('606', '2', '1', null, '2018-12-18 21:00:53', null, '12排11列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('607', '2', '1', null, '2018-12-18 21:00:53', null, '12排12列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('608', '2', '1', null, '2018-12-18 21:00:53', null, '12排13列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('609', '2', '1', null, '2018-12-18 21:00:53', null, '12排14列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('610', '2', '1', null, '2018-12-18 21:00:53', null, '12排15列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('611', '2', '1', null, '2018-12-18 21:00:53', null, '12排16列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');
INSERT INTO `ticket` VALUES ('612', '2', '1', null, '2018-12-18 21:00:53', null, '12排17列', '4号厅', '2018-12-18 21:00:53', '2018-12-18 23:23:53');

-- ----------------------------
-- Table structure for `userloginrecord`
-- ----------------------------
DROP TABLE IF EXISTS `userloginrecord`;
CREATE TABLE `userloginrecord` (
  `ulrId` int(11) NOT NULL AUTO_INCREMENT,
  `ulrLoginTime` datetime DEFAULT NULL,
  `ulrLoginIp` varchar(255) DEFAULT NULL,
  `ulrUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ulrId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userloginrecord
-- ----------------------------
INSERT INTO `userloginrecord` VALUES ('1', '2018-12-06 21:00:36', '192.168.15.50', '1');
INSERT INTO `userloginrecord` VALUES ('2', '2018-12-06 21:15:06', '0:0:0:0:0:0:0:1', '1');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `userAccount` varchar(255) DEFAULT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `userPwd` varchar(255) DEFAULT NULL,
  `userCreateTime` datetime DEFAULT NULL,
  `userTel` varchar(255) DEFAULT NULL,
  `userAddr` varchar(255) DEFAULT NULL,
  `userStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '杨鑫虎', 'yangxinhu', 'naivestruggle@126.com', 'e10adc3949ba59abbe56e057f20f883e', '2018-12-06 20:21:21', '15570906290', null, '1');
