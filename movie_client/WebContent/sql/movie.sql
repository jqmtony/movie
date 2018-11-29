/*
Navicat MySQL Data Transfer

Source Server         : user
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : movie

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2018-11-29 18:54:02
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminloginrecord
-- ----------------------------
INSERT INTO `adminloginrecord` VALUES ('1', '2018-11-26 20:27:00', '0:0:0:0:0:0:0:1', null, '失败');
INSERT INTO `adminloginrecord` VALUES ('2', '2018-11-26 20:28:10', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('3', '2018-11-26 20:37:54', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('4', '2018-11-26 20:58:27', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('5', '2018-11-26 20:59:19', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('6', '2018-11-26 21:01:11', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('7', '2018-11-26 21:01:30', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('8', '2018-11-26 21:05:24', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('9', '2018-11-26 21:07:28', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('10', '2018-11-26 21:20:01', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('11', '2018-11-26 21:20:57', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('12', '2018-11-26 21:21:22', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('13', '2018-11-26 21:25:33', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('14', '2018-11-26 21:27:37', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('15', '2018-11-26 21:42:37', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('16', '2018-11-26 21:52:17', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('17', '2018-11-29 14:42:13', '0:0:0:0:0:0:0:1', null, '失败');
INSERT INTO `adminloginrecord` VALUES ('18', '2018-11-29 14:42:16', '0:0:0:0:0:0:0:1', null, '失败');

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
-- Table structure for `classifys`
-- ----------------------------
DROP TABLE IF EXISTS `classifys`;
CREATE TABLE `classifys` (
  `classifyId` int(11) NOT NULL AUTO_INCREMENT,
  `classifyMovieId` int(11) DEFAULT NULL,
  `classifyTeleplayId` int(11) DEFAULT NULL,
  `classifyName` varchar(255) DEFAULT NULL,
  `classifyDescribe` varchar(255) DEFAULT NULL,
  `classifyParentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`classifyId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classifys
-- ----------------------------
INSERT INTO `classifys` VALUES ('1', '1', null, '历史', '这是关于历史的影片', null);
INSERT INTO `classifys` VALUES ('2', '1', null, '地理', '这是关于地理的影片', null);
INSERT INTO `classifys` VALUES ('3', '1', null, '人文', '这是关于人文的影片', null);
INSERT INTO `classifys` VALUES ('4', '2', null, '文化', '这是关于文化的影片', null);
INSERT INTO `classifys` VALUES ('5', '2', null, '历史', '这是关于文化的影片', null);
INSERT INTO `classifys` VALUES ('6', '3', null, '历史', '这是关于历史的影片', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('1', '1', null, null, null, null, null, null, '封面', 'images/11.jpg');
INSERT INTO `images` VALUES ('2', '2', null, null, null, null, null, null, '封面', 'images/m1.jpg');
INSERT INTO `images` VALUES ('3', '3', null, null, null, null, null, null, '封面', 'images/m1.jpg');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of integral
-- ----------------------------

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
  PRIMARY KEY (`merId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES ('1', '杨鑫虎', '15570906290', 'naivestruggle@126.com', '湖南省', '123456', '15570906290', '1');

-- ----------------------------
-- Table structure for `movies`
-- ----------------------------
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `movieId` int(11) NOT NULL AUTO_INCREMENT,
  `movieMerId` int(11) DEFAULT NULL,
  `movieIntegralNum` int(11) DEFAULT NULL,
  `movieName` varchar(255) DEFAULT NULL,
  `movieGradeNum` double(4,2) DEFAULT NULL,
  `movieVisitCount` int(11) DEFAULT NULL,
  `movieDescribe` varchar(500) DEFAULT NULL,
  `moviePath` varchar(500) DEFAULT NULL,
  `moviePrice` decimal(7,2) DEFAULT NULL,
  `movieStatus` varchar(255) DEFAULT NULL,
  `movieCreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`movieId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movies
-- ----------------------------
INSERT INTO `movies` VALUES ('1', '1', '0', '《江南》', '8.00', '100', '江南电影', 'http://www.baidu.com', '15.00', '1', '2018-11-27 18:04:07');
INSERT INTO `movies` VALUES ('2', '1', null, '《style》', '7.00', '5', '这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述', 'http://www.baidu.com', '17.00', '1', '2018-11-28 11:19:17');
INSERT INTO `movies` VALUES ('3', '1', null, '《一千零二夜》', '6.00', '12', '这是描述', 'http://www.baidu.com', '16.00', '1', '2018-11-28 21:15:20');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of protagonists
-- ----------------------------

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
  PRIMARY KEY (`ticketId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userloginrecord
-- ----------------------------
INSERT INTO `userloginrecord` VALUES ('1', '2018-11-29 17:16:41', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('2', '2018-11-29 17:16:49', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('3', '2018-11-29 17:18:42', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('4', '2018-11-29 17:20:54', '0:0:0:0:0:0:0:1', '2');
INSERT INTO `userloginrecord` VALUES ('5', '2018-11-29 17:21:04', '0:0:0:0:0:0:0:1', '1');

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
  `userPayNum` varchar(255) DEFAULT NULL,
  `userPayPwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '杨鑫虎', 'yangxinhu', 'naivestruuglee@126.com', 'e10adc3949ba59abbe56e057f20f883e', null, '15570906290', null, null);
INSERT INTO `users` VALUES ('2', '杨鑫虎', null, 'naivestruggle@126.com', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null);
