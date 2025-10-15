-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2025 at 11:10 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_elektronik`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventori`
--

CREATE TABLE `inventori` (
  `id` varchar(51) NOT NULL,
  `nama` varchar(51) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(51) NOT NULL,
  `kondisi` enum('Baru','Bekas') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventori`
--

INSERT INTO `inventori` (`id`, `nama`, `harga`, `kategori`, `kondisi`) VALUES
('AC-001', 'Samsung Galaxy S24 Ultra 12GB/256GB', 18500000, 'HP', 'Baru'),
('HP-001', 'Samsung Galaxy S24 Ultra 12GB/256GB', 18500000, 'HP', 'Baru'),
('KLK-001', 'Sharp SJ-IF85PB-SL Inverter 2 Pintu', 5500000, 'Kulkas', 'Baru'),
('LAP-001', 'Asus ROG Strix G15 RTX 3060', 18500000, 'Laptop', 'Baru'),
('TV-001', 'LG OLED evo 55 inch C3 Series', 18000000, 'TV', 'Baru');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventori`
--
ALTER TABLE `inventori`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
