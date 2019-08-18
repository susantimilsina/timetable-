-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2019 at 12:18 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `timetable`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminID` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(500) NOT NULL,
  `securityKey` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminID`, `username`, `password`, `securityKey`) VALUES
(1, 'sudarshan', 'admin', 'adminKey'),
(2, 'anish', 'admin', 'adminKey'),
(3, 'susan', 'admin', 'adminKey');

-- --------------------------------------------------------

--
-- Table structure for table `analysisparameter`
--

CREATE TABLE `analysisparameter` (
  `population_size` varchar(10) NOT NULL,
  `crossover_rate` varchar(10) NOT NULL,
  `mutation_rate` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `analysisparameter`
--

INSERT INTO `analysisparameter` (`population_size`, `crossover_rate`, `mutation_rate`) VALUES
('25', '0.9', '0.01'),
('50', '0.8', '0.02'),
('75', '0.7', '0.03'),
('100', '0.6', '0.04');

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `classNumber` int(11) NOT NULL,
  `classFloor` int(11) NOT NULL,
  `classCapacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`classNumber`, `classFloor`, `classCapacity`) VALUES
(101, 1, 35),
(102, 1, 50),
(103, 1, 40),
(104, 1, 45),
(105, 1, 30);

-- --------------------------------------------------------

--
-- Table structure for table `classtime`
--

CREATE TABLE `classtime` (
  `timeID` int(11) NOT NULL,
  `day` varchar(15) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classtime`
--

INSERT INTO `classtime` (`timeID`, `day`, `startTime`, `endTime`) VALUES
(1, 'sunday', '06:30:00', '07:30:00'),
(2, 'sunday', '07:30:00', '08:30:00'),
(3, 'sunday', '08:30:00', '09:30:00'),
(4, 'sunday', '09:30:00', '10:30:00'),
(5, 'sunday', '10:30:00', '11:30:00'),
(6, 'sunday', '11:30:00', '12:30:00'),
(7, 'Sunday', '12:30:00', '13:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `facultyName` varchar(20) NOT NULL,
  `facultyDescription` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`facultyName`, `facultyDescription`) VALUES
('BCA', 'f;oijsdaf'),
('bim', 'bim is 4 year program for management students to study\n it'),
('csit', 'Csit is 4 year bachelor program');

-- --------------------------------------------------------

--
-- Table structure for table `professor`
--

CREATE TABLE `professor` (
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(500) NOT NULL,
  `phone` varchar(14) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `professor`
--

INSERT INTO `professor` (`firstName`, `lastName`, `username`, `password`, `phone`, `email`) VALUES
('Astha', 'karki', 'astha1', 'astha', '9815948437', 'astha@gmail.com'),
('Bijay', 'Mishra', 'bijay', 'bijay', '9812345670', 'bijay@gmail.com'),
('Deepraj', 'Bhujel', 'deepraj', 'deepraj', '9812345676', 'deepraj@gmail.com'),
('Dhiraj', 'Jha', 'dhiraj', 'dhiraj', '9812345671', 'dhiraj@gmail.com'),
('Gauri', 'Shrestha', 'gauri', 'gauri', '9812345672', 'gauri@gmail.com'),
('Krishna', 'Khadka', 'krishna', 'krishna', '9812345675', 'krishna@gmail.com'),
('Mohan', 'Bhandari', 'mohan', 'mohan', '9812345678', 'mohan@gmail.com'),
('Nipun', 'Thapa', 'nipun', 'nipun', '9812345677', 'nipun@gmail.com'),
('ram', 'bhatta', 'rambhatta', 'ram', '9812398981', 'ram@gmail.com'),
('Sherin', 'Joshi', 'sherin', 'sherin', '9812345674', 'sherin@gmail.com'),
('Surendra', 'Pathak', 'surendra', 'surendra', '9812345673', 'surendra@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `scheduleID` int(11) NOT NULL,
  `classNumber` int(11) NOT NULL,
  `classTimeID` int(11) NOT NULL,
  `subjectCode` varchar(50) NOT NULL,
  `professorUsername` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`scheduleID`, `classNumber`, `classTimeID`, `subjectCode`, `professorUsername`) VALUES
(2, 102, 1, 'csc102', 'dhiraj'),
(4, 102, 2, 'sta103', 'gauri'),
(5, 102, 3, 'sta108', 'gauri'),
(3, 102, 4, 'mth104', 'surendra'),
(1, 102, 5, 'Csc101', 'bijay'),
(6, 104, 1, 'csc151', 'nipun'),
(9, 104, 2, 'mth155', 'surendra'),
(7, 104, 3, 'csc152', 'krishna'),
(10, 104, 4, 'sta159', 'gauri'),
(8, 104, 5, 'csc154', 'sherin');

-- --------------------------------------------------------

--
-- Table structure for table `studentgroup`
--

CREATE TABLE `studentgroup` (
  `faculty` varchar(20) NOT NULL,
  `semester` varchar(20) NOT NULL,
  `NumberOfStudents` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentgroup`
--

INSERT INTO `studentgroup` (`faculty`, `semester`, `NumberOfStudents`, `username`, `password`) VALUES
('csit', 'Sem I', 48, 'csit-Sem I', 'csit-Sem I'),
('csit', 'Sem II', 43, 'csit-Sem II', 'csit-Sem II'),
('csit', 'Sem III', 38, 'csit-Sem III', 'csit-Sem III'),
('csit', 'Sem IV', 33, 'csit-Sem IV', 'csit-Sem IV');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `name` varchar(50) NOT NULL,
  `code` varchar(20) NOT NULL,
  `faculty` varchar(20) NOT NULL,
  `semester` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`name`, `code`, `faculty`, `semester`) VALUES
('CPP', 'cpp', 'BCA', 'Sem I'),
('IT', 'Csc101', 'csit', 'Sem I'),
('C', 'csc102', 'csit', 'Sem I'),
('Digital Logic', 'csc151', 'csit', 'Sem II'),
('Discrete Structure', 'csc152', 'csit', 'Sem II'),
('DSA', 'csc154', 'csit', 'Sem II'),
('Computer Architecture', 'csc201', 'csit', 'Sem III'),
('OOP', 'csc202', 'csit', 'Sem III'),
('Operating System', 'csc203', 'csit', 'Sem III'),
('Numerical Method', 'csc204', 'csit', 'Sem III'),
('SAD', 'csc252', 'csit', 'Sem IV'),
('DBMS', 'csc253', 'csit', 'Sem IV'),
('Computer Graphics', 'csc254', 'csit', 'Sem IV'),
('Cognitive Science', 'csc255', 'csit', 'Sem IV'),
('Technical Writing', 'csc256', 'csit', 'Sem IV'),
('Management', 'mgt205', 'csit', 'Sem III'),
('Calculus', 'mth104', 'csit', 'Sem I'),
('Linear Algebra', 'mth155', 'csit', 'Sem II'),
('Probability', 'sta103', 'csit', 'Sem I'),
('Statistics', 'sta108', 'csit', 'Sem I'),
('Statistics II', 'sta159', 'csit', 'Sem II');

-- --------------------------------------------------------

--
-- Table structure for table `teaches`
--

CREATE TABLE `teaches` (
  `subjectCode` varchar(20) NOT NULL,
  `professorUsername` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teaches`
--

INSERT INTO `teaches` (`subjectCode`, `professorUsername`) VALUES
('cpp', 'dhiraj'),
('Csc101', 'bijay'),
('csc102', 'dhiraj'),
('csc151', 'nipun'),
('csc152', 'krishna'),
('csc154', 'sherin'),
('csc201', 'bijay'),
('csc202', 'dhiraj'),
('csc203', 'rambhatta'),
('csc204', 'deepraj'),
('csc252', 'bijay'),
('csc253', 'dhiraj'),
('csc254', 'nipun'),
('csc255', 'mohan'),
('csc256', 'astha1'),
('mgt205', 'astha1'),
('mth104', 'surendra'),
('mth155', 'surendra'),
('sta103', 'gauri'),
('sta108', 'gauri'),
('sta159', 'gauri');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`);

--
-- Indexes for table `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`classNumber`);

--
-- Indexes for table `classtime`
--
ALTER TABLE `classtime`
  ADD PRIMARY KEY (`timeID`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`facultyName`);

--
-- Indexes for table `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `phone` (`phone`,`email`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`scheduleID`),
  ADD KEY `classNumber` (`classNumber`,`classTimeID`,`subjectCode`,`professorUsername`),
  ADD KEY `classTimeID` (`classTimeID`),
  ADD KEY `subjectCode` (`subjectCode`),
  ADD KEY `professorUsername` (`professorUsername`);

--
-- Indexes for table `studentgroup`
--
ALTER TABLE `studentgroup`
  ADD PRIMARY KEY (`faculty`,`semester`),
  ADD KEY `faculty` (`faculty`,`semester`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`code`),
  ADD KEY `faculty` (`faculty`);

--
-- Indexes for table `teaches`
--
ALTER TABLE `teaches`
  ADD PRIMARY KEY (`subjectCode`,`professorUsername`),
  ADD KEY `subjectCode` (`subjectCode`),
  ADD KEY `teaches_ibfk_1` (`professorUsername`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `classtime`
--
ALTER TABLE `classtime`
  MODIFY `timeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `scheduleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`classNumber`) REFERENCES `classroom` (`classNumber`),
  ADD CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`classTimeID`) REFERENCES `classtime` (`timeID`),
  ADD CONSTRAINT `schedule_ibfk_3` FOREIGN KEY (`subjectCode`) REFERENCES `subject` (`code`),
  ADD CONSTRAINT `schedule_ibfk_4` FOREIGN KEY (`professorUsername`) REFERENCES `professor` (`username`);

--
-- Constraints for table `studentgroup`
--
ALTER TABLE `studentgroup`
  ADD CONSTRAINT `studentgroup_ibfk_1` FOREIGN KEY (`faculty`) REFERENCES `faculty` (`facultyName`);

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`faculty`) REFERENCES `faculty` (`facultyName`) ON DELETE CASCADE;

--
-- Constraints for table `teaches`
--
ALTER TABLE `teaches`
  ADD CONSTRAINT `teaches_ibfk_1` FOREIGN KEY (`professorUsername`) REFERENCES `professor` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `teaches_ibfk_2` FOREIGN KEY (`subjectCode`) REFERENCES `subject` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
