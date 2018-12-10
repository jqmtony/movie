/*
Navicat MySQL Data Transfer

Source Server         : user
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : movie

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2018-12-09 19:17:29
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classifys
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of images
-- ----------------------------

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
  `indentPrice` decimal(7,2) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movies
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
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
  `ticketMerId` int(11) DEFAULT NULL,
  `ticketStatus` varchar(255) DEFAULT NULL,
  `ticketBuyBy` int(11) DEFAULT NULL,
  `ticketStartTime` datetime DEFAULT NULL,
  `ticketIndentId` int(11) DEFAULT NULL,
  `ticketLocation` varchar(255) DEFAULT NULL,
  `ticketMovieTheater` varchar(255) DEFAULT NULL,
  `ticketMovieStartTime` datetime DEFAULT NULL,
  `ticketMovieEndTime` datetime DEFAULT NULL,
  `ticketLocationNum` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userloginrecord
-- ----------------------------

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
  `userBirthday` date DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '杨鑫虎', 'yangxinhu', 'naivestruugle@126.com', 'e10adc3949ba59abbe56e057f20f883e', '2018-12-09 14:05:42', '15570906290', '中国 湖南省 衡阳市 珠晖区 湖南工学院', null, '1998-07-14', '20');
