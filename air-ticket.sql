-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2017 at 01:28 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `air-ticket`
--

-- --------------------------------------------------------

--
-- Table structure for table `airline`
--

CREATE TABLE IF NOT EXISTS `airline` (
  `airline_ID` varchar(20) NOT NULL,
  `airline_name` varchar(15) NOT NULL,
  `origin` varchar(15) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`airline_ID`),
  UNIQUE KEY `airline_name` (`airline_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `airport`
--

CREATE TABLE IF NOT EXISTS `airport` (
  `airportID` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `city` varchar(20) NOT NULL,
  `apstate` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`airportID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `assigned_to`
--

CREATE TABLE IF NOT EXISTS `assigned_to` (
  `leg_no` varchar(25) NOT NULL,
  `flight_no` varchar(20) NOT NULL,
  PRIMARY KEY (`leg_no`,`flight_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE IF NOT EXISTS `flight` (
  `flight_no` varchar(20) NOT NULL,
  `max_seats` int(11) NOT NULL,
  `airline_ID` varchar(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`flight_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `flight_leg`
--

CREATE TABLE IF NOT EXISTS `flight_leg` (
  `leg_no` varchar(25) NOT NULL,
  `leg_type` char(2) NOT NULL,
  PRIMARY KEY (`leg_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fromap`
--

CREATE TABLE IF NOT EXISTS `fromap` (
  `leg_no` varchar(25) NOT NULL DEFAULT '',
  `airportID` varchar(10) NOT NULL DEFAULT '',
  `departure_time` datetime NOT NULL,
  `arival_time` datetime NOT NULL,
  PRIMARY KEY (`leg_no`,`airportID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `passenger`
--

CREATE TABLE IF NOT EXISTS `passenger` (
  `tel` varchar(12) NOT NULL,
  `paddress` varchar(30) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `pass_no` char(10) NOT NULL,
  `dob` date NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`pass_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE IF NOT EXISTS `tickets` (
  `ticket_no` varchar(10) NOT NULL,
  `seat_no` int(11) NOT NULL,
  `fair` float NOT NULL,
  `pass_no` char(10) NOT NULL,
  `leg_no` varchar(25) NOT NULL,
  PRIMARY KEY (`ticket_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `toap`
--

CREATE TABLE IF NOT EXISTS `toap` (
  `leg_no` varchar(25) NOT NULL DEFAULT '',
  `airportID` varchar(10) NOT NULL DEFAULT '',
  `departure_time` datetime NOT NULL,
  `arival_time` datetime NOT NULL,
  PRIMARY KEY (`leg_no`,`airportID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
