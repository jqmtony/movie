/*
Navicat MySQL Data Transfer

Source Server         : user
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : movie

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2018-12-04 19:08:33
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

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
INSERT INTO `classifys` VALUES ('16', '9', null, '7', null, null);
INSERT INTO `classifys` VALUES ('17', '9', null, '2', null, null);
INSERT INTO `classifys` VALUES ('18', '9', null, '3', null, null);
INSERT INTO `classifys` VALUES ('19', '10', null, '8', null, null);
INSERT INTO `classifys` VALUES ('20', '10', null, '2', null, null);
INSERT INTO `classifys` VALUES ('21', '10', null, '3', null, null);
INSERT INTO `classifys` VALUES ('22', '11', null, '1', null, null);
INSERT INTO `classifys` VALUES ('23', '11', null, '2', null, null);
INSERT INTO `classifys` VALUES ('24', '11', null, '3', null, null);
INSERT INTO `classifys` VALUES ('25', '12', null, '1', null, null);
INSERT INTO `classifys` VALUES ('26', '12', null, '2', null, null);
INSERT INTO `classifys` VALUES ('27', '12', null, '3', null, null);
INSERT INTO `classifys` VALUES ('28', '13', null, '1', null, null);
INSERT INTO `classifys` VALUES ('29', '13', null, '2', null, null);
INSERT INTO `classifys` VALUES ('30', '13', null, '3', null, null);
INSERT INTO `classifys` VALUES ('31', '14', null, '1', null, null);
INSERT INTO `classifys` VALUES ('32', '14', null, '2', null, null);
INSERT INTO `classifys` VALUES ('33', '14', null, '3', null, null);
INSERT INTO `classifys` VALUES ('34', '15', null, '1', null, null);
INSERT INTO `classifys` VALUES ('35', '15', null, '2', null, null);
INSERT INTO `classifys` VALUES ('36', '15', null, '3', null, null);
INSERT INTO `classifys` VALUES ('37', '16', null, '1', null, null);
INSERT INTO `classifys` VALUES ('38', '16', null, '2', null, null);
INSERT INTO `classifys` VALUES ('39', '16', null, '3', null, null);
INSERT INTO `classifys` VALUES ('40', '17', null, '1', null, null);
INSERT INTO `classifys` VALUES ('41', '17', null, '2', null, null);
INSERT INTO `classifys` VALUES ('42', '17', null, '3', null, null);
INSERT INTO `classifys` VALUES ('43', '18', null, '1', null, null);
INSERT INTO `classifys` VALUES ('44', '18', null, '2', null, null);
INSERT INTO `classifys` VALUES ('45', '18', null, '3', null, null);
INSERT INTO `classifys` VALUES ('46', '19', null, '1', null, null);
INSERT INTO `classifys` VALUES ('47', '19', null, '2', null, null);
INSERT INTO `classifys` VALUES ('48', '19', null, '3', null, null);
INSERT INTO `classifys` VALUES ('49', '20', null, '1', null, null);
INSERT INTO `classifys` VALUES ('50', '20', null, '2', null, null);
INSERT INTO `classifys` VALUES ('51', '20', null, '3', null, null);
INSERT INTO `classifys` VALUES ('52', '21', null, '1', null, null);
INSERT INTO `classifys` VALUES ('53', '21', null, '2', null, null);
INSERT INTO `classifys` VALUES ('54', '21', null, '3', null, null);
INSERT INTO `classifys` VALUES ('55', '22', null, '1', null, null);
INSERT INTO `classifys` VALUES ('56', '22', null, '2', null, null);
INSERT INTO `classifys` VALUES ('57', '22', null, '3', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

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
INSERT INTO `images` VALUES ('11', '9', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/EB08A3CB6E4E466CA5B397F8CB42D150_image.jpg');
INSERT INTO `images` VALUES ('12', '9', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/05A8E1A418874B018C96AF12DB03AA06_image.jpg');
INSERT INTO `images` VALUES ('13', '9', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/E031364573444BAF972F46F7C6D40579_image.jpg');
INSERT INTO `images` VALUES ('14', '10', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/3A0DFE1C67D94BF3AFCA515F9DBCC00A_image.jpg');
INSERT INTO `images` VALUES ('15', '10', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/85B8B1CE127E4A1FBAB165A37FA06B0E_image.jpg');
INSERT INTO `images` VALUES ('16', '10', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/A90E8C3997224CCEB4A43E6C17732AA7_image.jpg');
INSERT INTO `images` VALUES ('17', '11', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/25CD0F6D91FB46B18773754281E86072_image.jpg');
INSERT INTO `images` VALUES ('18', '11', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/086A4FFD95464E57BC195A182671959D_image.jpg');
INSERT INTO `images` VALUES ('19', '11', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/9ED23FF29DB14D239A846FD8196D87A1_image.jpg');
INSERT INTO `images` VALUES ('20', '12', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/2C1DC977706041C492C46EDF10BBAFB2_image.jpg');
INSERT INTO `images` VALUES ('21', '12', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/1CCEB8DCD3204062B126C26F71DB1040_image.jpg');
INSERT INTO `images` VALUES ('22', '12', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/17E452B9265440949F694EEB74A82ED8_image.jpg');
INSERT INTO `images` VALUES ('23', '13', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/D49789A49B9E4E9DAD42B0ED00F4A27A_image.jpg');
INSERT INTO `images` VALUES ('24', '13', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/4003773A73C34648BD9A075A2A7E91DD_image.jpg');
INSERT INTO `images` VALUES ('25', '13', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/F4DE5125FDDD46EA9DDFC4AED63C5254_image.jpg');
INSERT INTO `images` VALUES ('26', '14', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/983D83E41C934ED99845CB51114B7EBD_image.jpg');
INSERT INTO `images` VALUES ('27', '14', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/FA035A708B6744C589645E29453A6EA7_image.jpg');
INSERT INTO `images` VALUES ('28', '14', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/CEE422712EEB422C8470625289F29505_image.jpg');
INSERT INTO `images` VALUES ('29', '15', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/5E545478267540C9B5677BD57F214C0C_image.jpg');
INSERT INTO `images` VALUES ('30', '15', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/FB508B5A4175463081C06E561FD17ADC_image.jpg');
INSERT INTO `images` VALUES ('31', '15', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/29A97BC0D951407C9639DB83A622B602_image.jpg');
INSERT INTO `images` VALUES ('32', '16', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/46C340F4E17C446E812373BBD04D7EEE_image.jpg');
INSERT INTO `images` VALUES ('33', '16', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/DA7179D6BF7C4D7EB9012C79C360B1E2_image.jpg');
INSERT INTO `images` VALUES ('34', '16', null, null, null, null, null, null, null, '/merHeadCreateImage/c/b/58E199BA63A14374B8224D3BC42CABBA_image.jpg');
INSERT INTO `images` VALUES ('35', '17', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/5725268DDBB14931B77DBC0877616044_image_1.jpg');
INSERT INTO `images` VALUES ('36', '17', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/93D114390EB24889A02C66B774A75852_image_1.jpg');
INSERT INTO `images` VALUES ('37', '17', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/339D5EEEA53C497EB24BF94ED6ABF588_image_1.jpg');
INSERT INTO `images` VALUES ('38', '18', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/222B5567AF5D4A8EA36FE2C3706A518C_image_1.jpg');
INSERT INTO `images` VALUES ('39', '18', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/250FB0E274244971B74695722359B7E4_image_1.jpg');
INSERT INTO `images` VALUES ('40', '18', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/9EB52C3018AB4AA9BADC2603451F992D_image_1.jpg');
INSERT INTO `images` VALUES ('41', '19', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/A654BB16BF144A45B13D84596E26DD91_image_1.jpg');
INSERT INTO `images` VALUES ('42', '19', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/020C85DBE56448D2BA5ACCA34BCA4E4A_image_1.jpg');
INSERT INTO `images` VALUES ('43', '19', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/D4FD771D836549DC91D7DE893F64166A_image_1.jpg');
INSERT INTO `images` VALUES ('44', '20', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/E9DEBEDB25A7421198D4C5676324A15A_image_1.jpg');
INSERT INTO `images` VALUES ('45', '20', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/DD8C64C8A78246D78389430DA0EBDD92_image_1.jpg');
INSERT INTO `images` VALUES ('46', '20', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/63455F902A3D46F893FC22BE80A98C27_image_1.jpg');
INSERT INTO `images` VALUES ('47', '21', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/3269630DDDB045F6B66C8A56E953AFC3_image_1.jpg');
INSERT INTO `images` VALUES ('48', '21', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/9150715F826246E48EF9E2BBB247F347_image_1.jpg');
INSERT INTO `images` VALUES ('49', '21', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/195EE0E7F3134913A13F67FD9B94858A_image_1.jpg');
INSERT INTO `images` VALUES ('50', '22', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/14B8E66AE5694B628A37727405BA0016_image_1.jpg');
INSERT INTO `images` VALUES ('51', '22', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/81A16090DF2E4D8983DF23E27D400EF4_image_1.jpg');
INSERT INTO `images` VALUES ('52', '22', null, null, null, null, null, null, null, '/merHeadCreateImage/9/2/A11DD7379D6B4E989A7014890AF08559_image_1.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movies
-- ----------------------------
INSERT INTO `movies` VALUES ('1', '1', '0', '《江南》', '8.00', '100', '江南电影', 'http://www.baidu.com', '15.00', '1', '2018-11-27 18:04:07');
INSERT INTO `movies` VALUES ('2', '1', null, '《style》', '7.00', '5', '这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述', 'http://www.baidu.com', '17.00', '1', '2018-11-28 11:19:17');
INSERT INTO `movies` VALUES ('3', '1', null, '《一千零二夜》', '6.00', '12', '这是描述', 'http://www.baidu.com', '16.00', '1', '2018-11-28 21:15:20');
INSERT INTO `movies` VALUES ('6', '1', '12', '杨鑫虎', '0.00', null, '21342342342', null, '12.12', '1', '2018-12-02 20:12:04');
INSERT INTO `movies` VALUES ('7', '1', '12', '杨鑫虎', '0.00', null, 'ewqeqweqweqw', null, '12.12', '1', '2018-12-02 20:21:59');
INSERT INTO `movies` VALUES ('8', '1', '12', '杨鑫虎', '0.00', null, 'eqweqweqweqw', null, '12.12', '1', '2018-12-02 21:02:12');
INSERT INTO `movies` VALUES ('9', '1', '12', '《一场好戏》', '0.00', null, '这是一场好戏', null, '12.12', '1', '2018-12-04 15:58:52');
INSERT INTO `movies` VALUES ('10', '1', '12323', '《哈哈》', '0.00', null, '委屈翁群翁群无', null, '12.12', '1', '2018-12-04 16:02:11');
INSERT INTO `movies` VALUES ('11', '1', '12323', '《啦啦》', '0.00', null, '驱蚊器翁群翁群无', null, '12.12', '1', '2018-12-04 16:04:36');
INSERT INTO `movies` VALUES ('12', '1', '12', '杨鑫虎', '0.00', null, '计划规划局规划局', null, '12.12', '1', '2018-12-04 16:10:02');
INSERT INTO `movies` VALUES ('13', '1', '12323', '杨鑫虎', '0.00', null, '1243213423', null, '12.12', '1', '2018-12-04 16:18:06');
INSERT INTO `movies` VALUES ('14', '1', '12', '杨鑫虎', '0.00', null, '12312424', null, '12.12', '1', '2018-12-04 16:22:45');
INSERT INTO `movies` VALUES ('15', '1', '12', '123', '0.00', null, '675675564654', null, '12.12', '1', '2018-12-04 16:31:02');
INSERT INTO `movies` VALUES ('16', '1', '12', '杨鑫虎', '0.00', null, '12312312', null, '12.12', '1', '2018-12-04 16:34:17');
INSERT INTO `movies` VALUES ('17', '1', '12', '123', '0.00', null, '12312312', null, '123.00', '1', '2018-12-04 17:03:17');
INSERT INTO `movies` VALUES ('18', '1', '12', '杨鑫虎', '0.00', null, '312312312', null, '12.12', '1', '2018-12-04 17:09:34');
INSERT INTO `movies` VALUES ('19', '1', '12', '123123', '0.00', null, 'qeqweqwe', null, '12.12', '1', '2018-12-04 17:18:29');
INSERT INTO `movies` VALUES ('20', '1', '12', '杨鑫虎', '0.00', null, '23123123', null, '12.12', '1', '2018-12-04 17:25:50');
INSERT INTO `movies` VALUES ('21', '1', '12', '杨鑫虎', '0.00', null, '12312123123', null, '12.12', '1', '2018-12-04 17:28:32');
INSERT INTO `movies` VALUES ('22', '1', '12323', '12312', '0.00', null, '341231231', null, '21312.00', '1', '2018-12-04 17:36:16');

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

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
INSERT INTO `protagonists` VALUES ('11', '9', null, 'xxx', null);
INSERT INTO `protagonists` VALUES ('12', '9', null, '颜帅', null);
INSERT INTO `protagonists` VALUES ('13', '9', null, '吴涛', null);
INSERT INTO `protagonists` VALUES ('14', '10', null, '不知道', null);
INSERT INTO `protagonists` VALUES ('15', '10', null, '不知道', null);
INSERT INTO `protagonists` VALUES ('16', '11', null, 'xxx', null);
INSERT INTO `protagonists` VALUES ('17', '11', null, '颜帅', null);
INSERT INTO `protagonists` VALUES ('18', '11', null, '吴涛', null);
INSERT INTO `protagonists` VALUES ('19', '12', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('20', '13', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('21', '14', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('22', '15', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('23', '16', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('24', '17', null, 'xxx', null);
INSERT INTO `protagonists` VALUES ('25', '17', null, '颜帅', null);
INSERT INTO `protagonists` VALUES ('26', '17', null, '吴涛', null);
INSERT INTO `protagonists` VALUES ('27', '18', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('28', '19', null, '3123', null);
INSERT INTO `protagonists` VALUES ('29', '20', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('30', '21', null, '杨鑫虎', null);
INSERT INTO `protagonists` VALUES ('31', '22', null, '3123', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '1', '1', '2018-12-03 18:58:40', '这是回复啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦', null);
INSERT INTO `reply` VALUES ('2', '4', '4', '2018-12-04 03:55:24', '我是张三  我也同意这是一部好看的电影', null);
INSERT INTO `reply` VALUES ('3', '4', null, '2018-12-04 04:28:30', '我是张三', '1');
INSERT INTO `reply` VALUES ('4', '4', null, '2018-12-04 04:28:57', '我还是张三', '1');
INSERT INTO `reply` VALUES ('5', '4', null, '2018-12-04 04:34:09', '我是张三2', '3');
INSERT INTO `reply` VALUES ('6', '1', null, '2018-12-04 09:26:28', '我是杨鑫虎  我来回复你  我是第3级了', '5');
INSERT INTO `reply` VALUES ('7', '1', null, '2018-12-04 09:38:57', '123', '5');

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
) ENGINE=InnoDB AUTO_INCREMENT=851 DEFAULT CHARSET=utf8;

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
INSERT INTO `ticket` VALUES ('151', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('152', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('153', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('154', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('155', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('156', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('157', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('158', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('159', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('160', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('161', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('162', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('163', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('164', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('165', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('166', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('167', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('168', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('169', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('170', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('171', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('172', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('173', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('174', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('175', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('176', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('177', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('178', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('179', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('180', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('181', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('182', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('183', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('184', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('185', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('186', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('187', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('188', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('189', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('190', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('191', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('192', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('193', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('194', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('195', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('196', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('197', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('198', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('199', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('200', '9', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('201', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('202', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('203', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('204', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('205', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('206', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('207', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('208', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('209', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('210', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('211', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('212', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('213', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('214', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('215', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('216', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('217', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('218', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('219', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('220', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('221', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('222', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('223', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('224', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('225', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('226', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('227', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('228', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('229', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('230', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('231', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('232', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('233', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('234', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('235', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('236', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('237', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('238', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('239', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('240', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('241', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('242', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('243', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('244', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('245', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('246', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('247', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('248', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('249', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('250', '10', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('251', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('252', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('253', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('254', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('255', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('256', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('257', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('258', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('259', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('260', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('261', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('262', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('263', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('264', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('265', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('266', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('267', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('268', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('269', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('270', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('271', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('272', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('273', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('274', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('275', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('276', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('277', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('278', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('279', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('280', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('281', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('282', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('283', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('284', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('285', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('286', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('287', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('288', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('289', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('290', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('291', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('292', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('293', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('294', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('295', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('296', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('297', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('298', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('299', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('300', '11', '1', null, '2018-12-04 16:04:20', null);
INSERT INTO `ticket` VALUES ('301', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('302', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('303', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('304', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('305', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('306', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('307', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('308', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('309', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('310', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('311', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('312', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('313', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('314', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('315', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('316', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('317', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('318', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('319', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('320', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('321', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('322', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('323', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('324', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('325', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('326', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('327', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('328', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('329', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('330', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('331', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('332', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('333', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('334', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('335', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('336', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('337', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('338', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('339', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('340', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('341', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('342', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('343', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('344', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('345', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('346', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('347', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('348', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('349', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('350', '12', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('351', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('352', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('353', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('354', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('355', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('356', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('357', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('358', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('359', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('360', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('361', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('362', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('363', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('364', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('365', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('366', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('367', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('368', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('369', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('370', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('371', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('372', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('373', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('374', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('375', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('376', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('377', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('378', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('379', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('380', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('381', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('382', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('383', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('384', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('385', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('386', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('387', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('388', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('389', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('390', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('391', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('392', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('393', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('394', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('395', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('396', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('397', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('398', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('399', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('400', '13', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('401', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('402', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('403', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('404', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('405', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('406', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('407', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('408', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('409', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('410', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('411', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('412', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('413', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('414', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('415', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('416', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('417', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('418', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('419', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('420', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('421', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('422', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('423', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('424', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('425', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('426', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('427', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('428', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('429', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('430', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('431', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('432', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('433', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('434', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('435', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('436', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('437', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('438', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('439', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('440', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('441', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('442', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('443', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('444', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('445', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('446', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('447', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('448', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('449', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('450', '14', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('451', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('452', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('453', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('454', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('455', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('456', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('457', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('458', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('459', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('460', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('461', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('462', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('463', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('464', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('465', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('466', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('467', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('468', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('469', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('470', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('471', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('472', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('473', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('474', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('475', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('476', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('477', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('478', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('479', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('480', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('481', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('482', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('483', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('484', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('485', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('486', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('487', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('488', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('489', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('490', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('491', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('492', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('493', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('494', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('495', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('496', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('497', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('498', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('499', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('500', '15', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('501', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('502', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('503', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('504', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('505', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('506', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('507', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('508', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('509', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('510', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('511', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('512', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('513', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('514', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('515', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('516', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('517', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('518', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('519', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('520', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('521', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('522', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('523', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('524', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('525', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('526', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('527', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('528', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('529', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('530', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('531', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('532', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('533', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('534', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('535', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('536', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('537', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('538', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('539', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('540', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('541', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('542', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('543', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('544', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('545', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('546', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('547', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('548', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('549', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('550', '16', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('551', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('552', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('553', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('554', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('555', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('556', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('557', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('558', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('559', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('560', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('561', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('562', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('563', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('564', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('565', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('566', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('567', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('568', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('569', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('570', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('571', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('572', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('573', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('574', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('575', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('576', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('577', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('578', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('579', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('580', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('581', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('582', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('583', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('584', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('585', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('586', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('587', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('588', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('589', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('590', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('591', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('592', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('593', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('594', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('595', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('596', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('597', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('598', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('599', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('600', '17', '1', null, '2018-12-04 16:01:51', null);
INSERT INTO `ticket` VALUES ('601', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('602', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('603', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('604', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('605', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('606', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('607', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('608', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('609', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('610', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('611', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('612', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('613', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('614', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('615', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('616', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('617', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('618', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('619', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('620', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('621', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('622', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('623', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('624', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('625', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('626', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('627', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('628', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('629', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('630', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('631', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('632', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('633', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('634', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('635', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('636', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('637', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('638', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('639', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('640', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('641', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('642', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('643', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('644', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('645', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('646', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('647', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('648', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('649', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('650', '18', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('651', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('652', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('653', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('654', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('655', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('656', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('657', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('658', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('659', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('660', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('661', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('662', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('663', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('664', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('665', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('666', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('667', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('668', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('669', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('670', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('671', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('672', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('673', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('674', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('675', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('676', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('677', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('678', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('679', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('680', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('681', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('682', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('683', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('684', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('685', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('686', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('687', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('688', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('689', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('690', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('691', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('692', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('693', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('694', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('695', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('696', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('697', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('698', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('699', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('700', '19', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('701', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('702', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('703', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('704', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('705', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('706', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('707', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('708', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('709', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('710', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('711', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('712', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('713', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('714', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('715', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('716', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('717', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('718', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('719', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('720', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('721', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('722', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('723', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('724', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('725', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('726', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('727', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('728', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('729', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('730', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('731', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('732', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('733', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('734', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('735', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('736', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('737', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('738', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('739', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('740', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('741', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('742', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('743', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('744', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('745', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('746', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('747', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('748', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('749', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('750', '20', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('751', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('752', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('753', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('754', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('755', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('756', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('757', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('758', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('759', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('760', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('761', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('762', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('763', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('764', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('765', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('766', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('767', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('768', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('769', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('770', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('771', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('772', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('773', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('774', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('775', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('776', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('777', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('778', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('779', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('780', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('781', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('782', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('783', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('784', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('785', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('786', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('787', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('788', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('789', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('790', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('791', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('792', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('793', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('794', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('795', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('796', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('797', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('798', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('799', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('800', '21', '1', null, '2018-12-06 20:04:52', null);
INSERT INTO `ticket` VALUES ('801', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('802', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('803', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('804', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('805', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('806', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('807', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('808', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('809', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('810', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('811', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('812', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('813', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('814', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('815', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('816', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('817', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('818', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('819', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('820', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('821', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('822', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('823', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('824', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('825', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('826', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('827', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('828', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('829', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('830', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('831', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('832', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('833', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('834', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('835', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('836', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('837', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('838', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('839', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('840', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('841', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('842', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('843', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('844', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('845', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('846', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('847', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('848', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('849', '22', '1', null, '2018-12-25 17:35:57', null);
INSERT INTO `ticket` VALUES ('850', '22', '1', null, '2018-12-25 17:35:57', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

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
INSERT INTO `userloginrecord` VALUES ('57', '2018-12-04 02:52:56', '0:0:0:0:0:0:0:1', '3');
INSERT INTO `userloginrecord` VALUES ('58', '2018-12-04 03:26:25', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('59', '2018-12-04 03:54:53', '0:0:0:0:0:0:0:1', '4');
INSERT INTO `userloginrecord` VALUES ('60', '2018-12-04 04:28:15', '0:0:0:0:0:0:0:1', '4');
INSERT INTO `userloginrecord` VALUES ('61', '2018-12-04 09:25:53', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `userloginrecord` VALUES ('62', '2018-12-04 18:29:43', '0:0:0:0:0:0:0:1', '4');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '杨鑫虎', 'yangxinhu', 'naivestruggle@126.com', 'e10adc3949ba59abbe56e057f20f883e', null, '15570906290', null, null);
INSERT INTO `users` VALUES ('2', '杨鑫虎', null, 'naivestruggle@126.co', '123456', null, null, null, null);
INSERT INTO `users` VALUES ('3', null, 'yangxinhu2', '2550438618@qq.com', '4297f44b13955235245b2497399d7a93', '2018-11-30 01:42:48', null, null, null);
INSERT INTO `users` VALUES ('4', null, 'zhangsan', '155@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '2018-12-04 03:53:56', '15570906290', null, null);
