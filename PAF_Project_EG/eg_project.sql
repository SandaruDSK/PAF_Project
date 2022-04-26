-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2022 at 09:10 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eg_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `B_ID` int(11) NOT NULL,
  `B_Cus_ID` varchar(20) NOT NULL,
  `B_Cus_Name` varchar(50) NOT NULL,
  `B_Cus_Email` varchar(50) NOT NULL,
  `B_Cus_Contact` varchar(10) NOT NULL,
  `B_Use_Points` double NOT NULL,
  `B_Use_Val` double NOT NULL,
  `B_Add_Charge` double NOT NULL,
  `B_Prev_Outsand` double NOT NULL,
  `B_Tot_Amt_Pay` double NOT NULL,
  `B_Red_Nor` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`B_ID`, `B_Cus_ID`, `B_Cus_Name`, `B_Cus_Email`, `B_Cus_Contact`, `B_Use_Points`, `B_Use_Val`, `B_Add_Charge`, `B_Prev_Outsand`, `B_Tot_Amt_Pay`, `B_Red_Nor`) VALUES
(1, '10000', 'Mr.Sandaruwan', 'sandaruwan@gmail.com', '0112345678', 500, 10000, 500, 5000, 15000, 'Red'),
(2, '2500', 'Mr.Nimal Karunarathne', 'nimal@gmail.com', '0776789862', 150, 3000, 300, 0, 3300, 'Normal'),
(3, '3000', 'Mr.Aloka Prabath', 'aloka@gmail.com', '0776723452', 200, 4000, 300, 3000, 7300, 'Red'),
(4, '3500', 'Mr.Upul Priyankara', 'upul@gmail.com', '0756723672', 250, 5000, 300, 4000, 9300, 'Red');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`B_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing`
--
ALTER TABLE `billing`
  MODIFY `B_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1004;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
