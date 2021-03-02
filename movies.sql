-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2020 at 07:53 PM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movies`
--

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id` int(11) NOT NULL,
  `runningTime` float NOT NULL,
  `title` varchar(255) NOT NULL,
  `director` varchar(255) NOT NULL,
  `ageRating` int(11) NOT NULL,
  `premiereDate` date NOT NULL,
  `is3D` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `runningTime`, `title`, `director`, `ageRating`, `premiereDate`, `is3D`) VALUES
(1, 2, 'Batman', 'Michael Keaton', 16, '2020-05-20', 0),
(2, 3, 'Spongebob Movie', 'Tom Kenny', 7, '2019-04-10', 0),
(3, 4, '2001: Space Odyssey', 'Stanley Kubrick', 12, '1985-02-11', 0),
(4, 2, 'Transformers', 'Michael Bay', 12, '2008-02-11', 1),
(5, 4, 'Hereditary', 'M.Night Shyamalan', 18, '2018-02-22', 0),
(6, 4.5, 'Interstellar', 'Christopher Nolan', 16, '2017-07-21', 1),
(7, 2, 'the simpsons movie', 'burt gringlesby', 2, '2020-01-02', 0),
(8, 2.2, '', 'michael bay', 18, '2020-04-29', 0),
(10, 2.5, 'test', 'test', 12, '2019-07-14', 0),
(11, 2, 'test', 'test', 18, '2020-05-10', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
