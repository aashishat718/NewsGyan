-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 08, 2021 at 09:37 AM
-- Server version: 5.7.23
-- PHP Version: 7.1.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `newsgyan`
--

-- --------------------------------------------------------

--
-- Table structure for table `blogs`
--

CREATE TABLE `blogs` (
  `id` int(11) NOT NULL,
  `author` varchar(50) NOT NULL,
  `timestamp` varchar(50) NOT NULL,
  `title` text NOT NULL,
  `content` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `upvotes_info`
--

CREATE TABLE `upvotes_info` (
  `unique_video_name` varchar(80) NOT NULL,
  `voter` varchar(50) NOT NULL,
  `voting_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `upvotes_info`
--

INSERT INTO `upvotes_info` (`unique_video_name`, `voter`, `voting_time`) VALUES
('paulo_1620482310125', 'paulo', '2021-05-08 13:59:55');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `pref1` varchar(20) NOT NULL,
  `pref2` varchar(20) NOT NULL,
  `politics_f` int(8) NOT NULL,
  `sports_f` int(8) NOT NULL,
  `business_f` int(8) NOT NULL,
  `entertainment_f` int(8) NOT NULL,
  `health_f` int(8) NOT NULL,
  `science_f` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`user_id`, `user_name`, `full_name`, `pref1`, `pref2`, `politics_f`, `sports_f`, `business_f`, `entertainment_f`, `health_f`, `science_f`) VALUES
(1, 'paulo', 'Paulo D', 'Politics', 'Sports', 3, 2, 1, 0, 0, 0),
(4, 'gyro', 'Gyroman', 'Sports', 'Business', 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `full_name`, `password`) VALUES
(1, 'paulo', 'Paulo D', 'XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg='),
(4, 'gyro', 'Gyroman', 'XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=');

-- --------------------------------------------------------

--
-- Table structure for table `vlogs`
--

CREATE TABLE `vlogs` (
  `id` int(11) NOT NULL,
  `reporter` varchar(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `extension` varchar(10) NOT NULL,
  `upvotes` int(10) NOT NULL DEFAULT '0',
  `upload_timestamp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vlogs`
--

INSERT INTO `vlogs` (`id`, `reporter`, `title`, `description`, `extension`, `upvotes`, `upload_timestamp`) VALUES
(1, 'paulo', 'Motivation', 'General', '.mp4', 1, '1620482310125');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blogs`
--
ALTER TABLE `blogs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `vlogs`
--
ALTER TABLE `vlogs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blogs`
--
ALTER TABLE `blogs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vlogs`
--
ALTER TABLE `vlogs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
