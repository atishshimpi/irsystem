-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2018 at 09:23 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `irsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `content_attribute`
--

CREATE TABLE IF NOT EXISTS `content_attribute` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `probability` float DEFAULT NULL,
  `QUERY_TEXT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_6ebeibimvi4f0a579n2qg9g5c` (`QUERY_TEXT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `csp`
--

CREATE TABLE IF NOT EXISTS `csp` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(200) DEFAULT NULL,
  `FIRST_NAME` varchar(100) DEFAULT NULL,
  `LAST_NAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_4miyark9wftoai4sqmf1c41eh` (`FIRST_NAME`,`LAST_NAME`,`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `fileshare`
--

CREATE TABLE IF NOT EXISTS `fileshare` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DOCUMENT_ID` bigint(20) DEFAULT NULL,
  `LOGIN_USER_ID` bigint(20) DEFAULT NULL,
  `SHARED_FILE_USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `LOCATION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOCATION_NAME` varchar(255) DEFAULT NULL,
  `SUB_LOCATION_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LOCATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `query_attribute`
--

CREATE TABLE IF NOT EXISTS `query_attribute` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `probability` float DEFAULT NULL,
  `QUERY_TEXT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_tg2mk62fkmm7g0ssjsy0o46x4` (`QUERY_TEXT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `query_text`
--

CREATE TABLE IF NOT EXISTS `query_text` (
  `QUERY_TEXT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` longtext,
  PRIMARY KEY (`QUERY_TEXT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `recommend`
--

CREATE TABLE IF NOT EXISTS `recommend` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DOCUMENT_ID` bigint(20) DEFAULT NULL,
  `NO_oF_VISITS` int(11) DEFAULT NULL,
  `RATING` int(11) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_h4ml1kk7o6c0qs66lqnj4ak7r` (`USER_ID`,`DOCUMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE IF NOT EXISTS `type` (
  `TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `PHONE` bigint(20) NOT NULL,
  `ROLE` varchar(255) NOT NULL,
  `SSO_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `PHONE`, `ROLE`, `SSO_ID`) VALUES
(1, 'priya@gmail.com', 'Priya', 'Khairnar', '123', 1234567890, 'user', NULL),
(2, 'admin@gmail.com', 'Priya', 'Khairnar', '123', 1234567890, 'authority', NULL),
(3, 'admin@gmail.com', 'admin', 'fddf', 'admin', 1234567890, 'authority', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_document`
--

CREATE TABLE IF NOT EXISTS `user_document` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longblob NOT NULL,
  `dealer_location` longtext,
  `name` longtext NOT NULL,
  `noOfVisit` int(11) DEFAULT NULL,
  `description` longtext,
  `product_name` longtext,
  `product_price` float DEFAULT NULL,
  `product_rating` int(11) DEFAULT NULL,
  `product_type` longtext,
  `type` longtext NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lcfjscnnac0sqjjevhlk7b7rd` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_document_token`
--

CREATE TABLE IF NOT EXISTS `user_document_token` (
  `tokenId` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `document_id` bigint(20) NOT NULL,
  PRIMARY KEY (`tokenId`),
  KEY `FK_2ilnegpj6bedesgnaia93xhws` (`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_group`
--

CREATE TABLE IF NOT EXISTS `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` longtext,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `content_attribute`
--
ALTER TABLE `content_attribute`
  ADD CONSTRAINT `FK_6ebeibimvi4f0a579n2qg9g5c` FOREIGN KEY (`QUERY_TEXT_ID`) REFERENCES `query_text` (`QUERY_TEXT_ID`);

--
-- Constraints for table `query_attribute`
--
ALTER TABLE `query_attribute`
  ADD CONSTRAINT `FK_tg2mk62fkmm7g0ssjsy0o46x4` FOREIGN KEY (`QUERY_TEXT_ID`) REFERENCES `query_text` (`QUERY_TEXT_ID`);

--
-- Constraints for table `user_document`
--
ALTER TABLE `user_document`
  ADD CONSTRAINT `FK_lcfjscnnac0sqjjevhlk7b7rd` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_document_token`
--
ALTER TABLE `user_document_token`
  ADD CONSTRAINT `FK_2ilnegpj6bedesgnaia93xhws` FOREIGN KEY (`document_id`) REFERENCES `user_document` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
