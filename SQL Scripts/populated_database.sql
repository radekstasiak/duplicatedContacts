
-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 20, 2015 at 08:49 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `AdvanceIT_skill_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `Company`
--

CREATE TABLE IF NOT EXISTS `Company` (
`id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `marketId` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Company`
--

INSERT INTO `Company` (`id`, `name`, `address`, `marketId`) VALUES
(1, 'AdvanceIT', 'Canada', 1),
(2, 'Misys', 'Poland', 2),
(3, 'Artklikk', 'Hungary', 3);

-- --------------------------------------------------------

--
-- Table structure for table `Contact`
--

CREATE TABLE IF NOT EXISTS `Contact` (
`id` int(11) NOT NULL,
  `companyId` int(11) NOT NULL,
  `contactDetailId` int(11) NOT NULL,
  `createDate` date NOT NULL,
  `lastContactDate` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Contact`
--

INSERT INTO `Contact` (`id`, `companyId`, `contactDetailId`, `createDate`, `lastContactDate`) VALUES
(1, 1, 1, '2015-01-18', '2014-11-11'),
(2, 3, 2, '2015-01-18', '2015-01-20'),
(3, 2, 3, '2015-01-16', '2015-01-19'),
(4, 3, 4, '2013-01-15', '2015-01-14'),
(5, 1, 5, '2015-01-19', '2015-01-14'),
(6, 3, 6, '2015-01-15', NULL),
(7, 2, 7, '2015-01-15', NULL),
(8, 3, 8, '2015-01-09', NULL),
(9, 3, 9, '2015-01-20', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ContactDetails`
--

CREATE TABLE IF NOT EXISTS `ContactDetails` (
`id` int(11) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `isDuplicate` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ContactDetails`
--

INSERT INTO `ContactDetails` (`id`, `firstName`, `lastName`, `phoneNumber`, `isDuplicate`) VALUES
(1, 'Michael', 'Jordan', '793385537', 0),
(2, 'Kobe', 'Bryant', '793385537', 0),
(3, 'John', 'Doe', '793385537', 0),
(4, 'Jane', 'Doe', '506203476', 0),
(5, 'Radek', 'Stasiak', '506203476', 0),
(6, 'Lebron', 'James', '601921691', 0),
(7, 'Kevin', 'Durant', '726491421', 0),
(8, 'Shaq', 'Oneil', '726491421', 0),
(9, 'Phil', 'Jackson', '726491421', 0);

-- --------------------------------------------------------

--
-- Table structure for table `Market`
--

CREATE TABLE IF NOT EXISTS `Market` (
`id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Market`
--

INSERT INTO `Market` (`id`, `name`) VALUES
(1, 'Business'),
(2, 'Banking'),
(3, 'Entertainment');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Company`
--
ALTER TABLE `Company`
 ADD PRIMARY KEY (`id`), ADD KEY `marketId_idx` (`marketId`);

--
-- Indexes for table `Contact`
--
ALTER TABLE `Contact`
 ADD PRIMARY KEY (`id`), ADD KEY `companie_key_idx` (`companyId`), ADD KEY `clients_key_idx` (`contactDetailId`);

--
-- Indexes for table `ContactDetails`
--
ALTER TABLE `ContactDetails`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Market`
--
ALTER TABLE `Market`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Company`
--
ALTER TABLE `Company`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `Contact`
--
ALTER TABLE `Contact`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `ContactDetails`
--
ALTER TABLE `ContactDetails`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `Market`
--
ALTER TABLE `Market`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Company`
--
ALTER TABLE `Company`
ADD CONSTRAINT `market_key` FOREIGN KEY (`marketId`) REFERENCES `Market` (`id`);

--
-- Constraints for table `Contact`
--
ALTER TABLE `Contact`
ADD CONSTRAINT `clients_key` FOREIGN KEY (`contactDetailId`) REFERENCES `ContactDetails` (`id`),
ADD CONSTRAINT `companies_key` FOREIGN KEY (`companyId`) REFERENCES `Company` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
