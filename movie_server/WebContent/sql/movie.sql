/*
Navicat MySQL Data Transfer

Source Server         : user
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : movie

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2018-11-26 20:18:41
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminloginrecord
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------

-- ----------------------------
-- Table structure for `companys`
-- ----------------------------
DROP TABLE IF EXISTS `companys`;
CREATE TABLE `companys` (
  `companyId` int(11) NOT NULL AUTO_INCREMENT,
  `companyNumberCard` varchar(255) DEFAULT NULL,
  `companyAddr` varchar(255) DEFAULT NULL,
  `companyPostcode` varchar(255) DEFAULT NULL,
  `companyCreateTime` datetime DEFAULT NULL,
  `companyEmail` varchar(255) DEFAULT NULL,
  `companyDescribe` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of companys
-- ----------------------------

-- ----------------------------
-- Table structure for `images`
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `imgId` int(11) NOT NULL AUTO_INCREMENT,
  `imgMovieId` int(11) DEFAULT NULL,
  `imgTeleplayId` int(11) DEFAULT NULL,
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
  `indentTicketId` int(11) DEFAULT NULL,
  `indentStatus` varchar(255) DEFAULT NULL,
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
  `integralGrade` int(11) DEFAULT NULL,
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
  ` merName` varchar(255) DEFAULT NULL,
  `merCompanyId` int(11) DEFAULT NULL,
  `merDescribe` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`merId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of merchant
-- ----------------------------

-- ----------------------------
-- Table structure for `movies`
-- ----------------------------
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `movieId` int(11) NOT NULL AUTO_INCREMENT,
  `movieMerId` int(11) DEFAULT NULL,
  `movieIntegralNum` int(11) DEFAULT NULL,
  `movieName` varchar(255) DEFAULT NULL,
  `movieProId` varchar(255) DEFAULT NULL,
  `movieGradeNum` int(11) DEFAULT NULL,
  `movieDescribe` varchar(500) DEFAULT NULL,
  `movieImgId` int(11) DEFAULT NULL,
  `moviePath` varchar(500) DEFAULT NULL,
  `moviePrice` decimal(7,2) DEFAULT NULL,
  `movieStatus` varchar(255) DEFAULT NULL,
  `movieCreateTime` datetime DEFAULT NULL,
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
-- Table structure for `teleplay`
-- ----------------------------
DROP TABLE IF EXISTS `teleplay`;
CREATE TABLE `teleplay` (
  `teleplayId` int(11) NOT NULL AUTO_INCREMENT,
  `teleplayMerId` int(11) DEFAULT NULL,
  `teleplayIntegralNum` int(11) DEFAULT NULL,
  `teleplayName` varchar(255) DEFAULT NULL,
  `teleplayProId` int(11) DEFAULT NULL,
  `teleplayGradeNum` int(11) DEFAULT NULL,
  `teleplayDescribe` varchar(500) DEFAULT NULL,
  `teleplayImgId` int(11) DEFAULT NULL,
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
  `ulrAdminId` int(11) DEFAULT NULL,
  `ulrStatus` varchar(255) DEFAULT NULL,
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
  `userEmail` varchar(255) DEFAULT NULL,
  `userPwd` varchar(255) DEFAULT NULL,
  `userCreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
