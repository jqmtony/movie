/*
Navicat MySQL Data Transfer

Source Server         : user
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : movie

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2018-12-03 21:50:12
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

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
INSERT INTO `adminloginrecord` VALUES ('19', '2018-11-30 18:13:22', '0:0:0:0:0:0:0:1', null, '失败');
INSERT INTO `adminloginrecord` VALUES ('20', '2018-11-30 18:13:47', '0:0:0:0:0:0:0:1', null, '失败');
INSERT INTO `adminloginrecord` VALUES ('21', '2018-11-30 18:13:51', '0:0:0:0:0:0:0:1', null, '失败');
INSERT INTO `adminloginrecord` VALUES ('22', '2018-11-30 18:13:58', '0:0:0:0:0:0:0:1', null, '失败');
INSERT INTO `adminloginrecord` VALUES ('23', '2018-11-30 18:14:15', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('24', '2018-11-30 18:16:34', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('25', '2018-11-30 18:16:48', '0:0:0:0:0:0:0:1', null, '失败');
INSERT INTO `adminloginrecord` VALUES ('26', '2018-11-30 18:27:38', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('27', '2018-12-01 15:44:06', '0:0:0:0:0:0:0:1', '1', '成功');
INSERT INTO `adminloginrecord` VALUES ('28', '2018-12-02 11:49:37', '0:0:0:0:0:0:0:1', '1', '成功');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classifys
-- ----------------------------
INSERT INTO `classifys` VALUES ('1', '1', null, '1', '这是关于历史的影片', null);
INSERT INTO `classifys` VALUES ('2', '1', null, '2', '这是关于地理的影片', null);
INSERT INTO `classifys` VALUES ('3', '1', null, '3', '这是关于人文的影片', null);
INSERT INTO `classifys` VALUES ('4', '2', null, '4', '这是关于文化的影片', null);
INSERT INTO `classifys` VALUES ('5', '2', null, '5', '这是关于文化的影片', null);
INSERT INTO `classifys` VALUES ('6', '3', null, '6', '这是关于历史的影片', null);
INSERT INTO `classifys` VALUES ('7', '6', null, '1', null, null);
INSERT INTO `classifys` VALUES ('8', '6', null, '2', null, null);
INSERT INTO `classifys` VALUES ('9', '6', null, '3', null, null);
INSERT INTO `classifys` VALUES ('10', '7', null, '7', null, null);
INSERT INTO `classifys` VALUES ('11', '7', null, '2', null, null);
INSERT INTO `classifys` VALUES ('12', '7', null, '3', null, null);
INSERT INTO `classifys` VALUES ('13', '8', null, '1', null, null);
INSERT INTO `classifys` VALUES ('14', '8', null, '2', null, null);
INSERT INTO `classifys` VALUES ('15', '8', null, '3', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '1', '2', null, '这是我给《style》的评论这是我给《style》的评论这是我给《style》的评论这是我给《style》的评论这是我给《style》的评论这是我给《style》的评论这是我给《style》的评论这是我给《style》的评论这是我给《style》的评论这是我给《style》的评论', '2018-12-03 18:58:25');
INSERT INTO `comment` VALUES ('2', '1', null, '2', null, '312312312', '2018-12-03 20:53:15');
INSERT INTO `comment` VALUES ('3', '1', null, '2', null, '123123123', '2018-12-03 20:56:39');
INSERT INTO `comment` VALUES ('4', '1', null, '2', null, '这是一部好看的电影', '2018-12-03 21:47:39');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('1', '1', null, null, null, null, null, null, '封面', 'images/11.jpg');
INSERT INTO `images` VALUES ('2', '2', null, null, null, null, null, null, '封面', 'images/m1.jpg');
INSERT INTO `images` VALUES ('3', null, null, '1', null, null, null, null, '封面', 'images/11.jpg');
INSERT INTO `images` VALUES ('4', null, null, null, '1', null, null, null, '封面', 'image.jpg');
INSERT INTO `images` VALUES ('5', '7', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/3D25A5870B7D45859FA33D729BCCBE41_image.jpg');
INSERT INTO `images` VALUES ('6', '7', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/B67E98A630824AF19B782B00BA62C589_image.jpg');
INSERT INTO `images` VALUES ('7', '7', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/5C008BA8C21C477499E601E6D666F924_image.jpg');
INSERT INTO `images` VALUES ('8', '8', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/F6E93C15BCEE4ECDA3FA90492B8EB0D9_image.jpg');
INSERT INTO `images` VALUES ('9', '8', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/71769A8333EB40DBA3EA7C1E59EA29C9_image.jpg');
INSERT INTO `images` VALUES ('10', '8', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/FC0728613EFA48579C41DB94AEE5BB2C_image.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of integral
-- ----------------------------
INSERT INTO `integral` VALUES ('1', null, '0');

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
INSERT INTO `merchant` VALUES ('1', null, null, 'naivestruggle@126.com', null, 'e10adc3949ba59abbe56e057f20f883e', null, '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movies
-- ----------------------------
INSERT INTO `movies` VALUES ('1', '1', '0', '《江南》', '8.00', '100', '江南电影', 'http://www.baidu.com', '15.00', '1', '2018-11-27 18:04:07');
INSERT INTO `movies` VALUES ('2', '1', null, '《style》', '7.00', '5', '这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述', 'http://www.baidu.com', '17.00', '1', '2018-11-28 11:19:17');
INSERT INTO `movies` VALUES ('3', '1', null, '《一千零二夜》', '6.00', '12', '这是描述', 'http://www.baidu.com', '16.00', '1', '2018-11-28 21:15:20');
INSERT INTO `movies` VALUES ('6', '1', '12', '杨鑫虎', '0.00', null, '21342342342', null, '12.12', '1', '2018-12-02 20:12:04');
INSERT INTO `movies` VALUES ('7', '1', '12', '杨鑫虎', '0.00', null, 'ewqeqweqweqw', null, '12.12', '1', '2018-12-02 20:21:59');
INSERT INTO `movies` VALUES ('8', '1', '12', '杨鑫虎', '0.00', null, 'eqweqweqweqw', null, '12.12', '1', '2018-12-02 21:02:12');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of protagonists
-- ----------------------------
INSERT INTO `protagonists` VALUES ('1', '2', null, '主演1', null);
INSERT INTO `protagonists` VALUES ('2', '2', null, '主演2', null);
INSERT INTO `protagonists` VALUES ('3', '2', null, '主演3', null);
INSERT INTO `protagonists` VALUES ('4', '6', null, 'xxx', null);
INSERT INTO `protagonists` VALUES ('5', '6', null, '颜帅', null);
INSERT INTO `protagonists` VALUES ('6', '6', null, '吴涛', null);
INSERT INTO `protagonists` VALUES ('7', '7', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('8', '8', null, 'xxx', null);
INSERT INTO `protagonists` VALUES ('9', '8', null, '颜帅', null);
INSERT INTO `protagonists` VALUES ('10', '8', null, '吴涛', null);

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
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '1', '1', '2018-12-03 18:58:40', '这是回复啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦');

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
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('1', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('2', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('3', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('4', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('5', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('6', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('7', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('8', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('9', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('10', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('11', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('12', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('13', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('14', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('15', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('16', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('17', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('18', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('19', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('20', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('21', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('22', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('23', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('24', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('25', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('26', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('27', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('28', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('29', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('30', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('31', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('32', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('33', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('34', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('35', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('36', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('37', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('38', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('39', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('40', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('41', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('42', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('43', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('44', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('45', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('46', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('47', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('48', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('49', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('50', '6', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('51', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('52', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('53', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('54', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('55', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('56', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('57', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('58', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('59', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('60', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('61', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('62', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('63', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('64', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('65', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('66', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('67', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('68', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('69', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('70', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('71', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('72', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('73', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('74', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('75', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('76', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('77', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('78', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('79', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('80', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('81', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('82', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('83', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('84', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('85', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('86', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('87', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('88', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('89', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('90', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('91', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('92', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('93', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('94', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('95', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('96', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('97', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('98', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('99', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('100', '7', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('101', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('102', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('103', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('104', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('105', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('106', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('107', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('108', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('109', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('110', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('111', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('112', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('113', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('114', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('115', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('116', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('117', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('118', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('119', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('120', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('121', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('122', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('123', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('124', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('125', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('126', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('127', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('128', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('129', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('130', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('131', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('132', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('133', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('134', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('135', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('136', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('137', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('138', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('139', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('140', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('141', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('142', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('143', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('144', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('145', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('146', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('147', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('148', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('149', '8', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('150', '8', '1', null, '2018-12-06 20:04:52', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userloginrecord
-- ----------------------------
INSERT INTO `userloginrecord` VALUES ('1', '2018-11-29 17:16:41', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('2', '2018-11-29 17:16:49', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('3', '2018-11-29 17:18:42', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('4', '2018-11-29 17:20:54', '0:0:0:0:0:0:0:1', '2');
INSERT INTO `userloginrecord` VALUES ('5', '2018-11-29 17:21:04', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('6', '2018-11-29 19:01:23', '0:0:0:0:0:0:0:1', '2');
INSERT INTO `userloginrecord` VALUES ('7', '2018-11-29 19:01:36', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('8', '2018-11-29 19:01:58', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('9', '2018-11-29 23:10:48', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('10', '2018-11-29 23:46:14', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('11', '2018-11-29 23:54:20', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('12', '2018-11-29 23:57:32', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('13', '2018-11-29 23:59:26', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('14', '2018-11-30 01:55:51', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('15', '2018-11-30 02:05:22', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('16', '2018-11-30 02:07:50', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('17', '2018-11-30 09:45:23', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('18', '2018-11-30 09:49:22', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('19', '2018-11-30 10:26:13', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('20', '2018-11-30 12:54:51', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('21', '2018-11-30 12:56:52', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('22', '2018-11-30 13:03:43', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('23', '2018-11-30 13:27:13', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('24', '2018-11-30 13:28:56', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('25', '2018-11-30 13:33:29', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('26', '2018-11-30 13:34:59', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('27', '2018-11-30 14:00:54', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('28', '2018-11-30 14:09:57', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('29', '2018-11-30 14:10:00', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('30', '2018-11-30 14:10:44', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('31', '2018-11-30 14:11:48', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('32', '2018-11-30 14:12:12', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('33', '2018-11-30 14:15:39', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('34', '2018-11-30 14:15:59', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('35', '2018-11-30 14:16:55', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('36', '2018-11-30 14:17:08', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('37', '2018-12-01 15:47:41', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('38', '2018-12-01 19:34:47', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('39', '2018-12-02 21:22:38', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('40', '2018-12-02 21:24:01', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('41', '2018-12-02 21:36:34', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('42', '2018-12-03 19:48:57', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('43', '2018-12-03 19:51:54', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('44', '2018-12-03 19:54:22', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('45', '2018-12-03 19:55:09', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('46', '2018-12-03 20:00:21', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('47', '2018-12-03 20:01:52', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('48', '2018-12-03 20:07:04', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('49', '2018-12-03 20:17:33', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('50', '2018-12-03 20:51:03', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('51', '2018-12-03 20:53:10', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('52', '2018-12-03 20:56:34', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('53', '2018-12-03 20:57:57', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('54', '2018-12-03 20:59:13', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('55', '2018-12-03 21:17:41', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('56', '2018-12-03 21:46:53', '0:0:0:0:0:0:0:1', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '杨鑫虎', 'yangxinhu', 'naivestruggle@126.com', 'e10adc3949ba59abbe56e057f20f883e', null, '15570906290', null, null);
INSERT INTO `users` VALUES ('2', '杨鑫虎', null, 'naivestruggle@126.co', '123456', null, null, null, null);
INSERT INTO `users` VALUES ('3', null, 'yangxinhu2', '2550438618@qq.com', '4297f44b13955235245b2497399d7a93', '2018-11-30 01:42:48', null, null, null);
