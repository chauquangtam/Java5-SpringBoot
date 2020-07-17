-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 17, 2020 lúc 11:51 AM
-- Phiên bản máy phục vụ: 10.4.13-MariaDB
-- Phiên bản PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `assignmentjava5mysql`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `departs`
--

CREATE TABLE `departs` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `departs`
--

INSERT INTO `departs` (`id`, `name`) VALUES
(1, 'a25'),
(7, 'a2'),
(9, 'thêm thành công1'),
(10, 'thêm thành công12'),
(11, 'thêm thành công12'),
(22, 'Châu Quang Tâm'),
(23, 'Châu Quang Tâm2'),
(24, 'Châu Quang Tâm2222'),
(28, 'p12'),
(29, 'phòng 99');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `records`
--

CREATE TABLE `records` (
  `id` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `date` date NOT NULL,
  `staff_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `records`
--

INSERT INTO `records` (`id`, `type`, `reason`, `date`, `staff_id`) VALUES
(1, 0, 'nghỉ', '2020-07-16', 16),
(6, 1, 'ngỉ', '2020-07-10', 13),
(11, 0, 'ngỉ', '2020-07-16', 18),
(12, 1, 'đi làm tốt', '2020-07-16', 16),
(13, 1, 'đi làm tốt', '2020-07-16', 16);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staffs`
--

CREATE TABLE `staffs` (
  `id` bigint(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `photo` varchar(100) NOT NULL,
  `depart_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `staffs`
--

INSERT INTO `staffs` (`id`, `name`, `birthday`, `photo`, `depart_id`) VALUES
(4, 'tamcqpd0280232', '2001-08-10', 'a3.jpg', 9),
(13, 'thêm thành công', '2000-08-10', 'đừng to ra thèm thuồng như thế.jpg', 7),
(14, 'thêm thành công', '2000-08-10', 'đừng to ra thèm thuồng như thế.jpg', 1),
(16, 'Châu Quang Tâm', '2000-08-10', 'a3.jpg', 1),
(17, 'tam 1', '2001-08-10', 'đừng to ra thèm thuồng như thế.jpg', 24),
(18, 'tam 2', '2000-08-10', 'a3.jpg', 28);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fullname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`username`, `password`, `fullname`) VALUES
('123', '$2a$10$VHgCmxjhCBiYbQv9iawcten8WRuALmwmiBHfAWvEl7cKO/KgNGp6u', '123'),
('admin', '$2a$10$i8JuQPEuU0obHOhoeNxQWe37ir.V129uIukTD4DZt6G88ZQAopTmC', 'chau tam'),
('admin1', '123', 'a'),
('admin15', '123', 'a6'),
('admin2', '123', 'a1'),
('admin2213', '123', '123'),
('admin3', '123', 'a3'),
('chauquangtam', '$2a$10$G3W5ezgKJmqDym3ojfjEQeexrIY5qv83DOmF9r8Z/hcLof3eB7GSO', 'chautam'),
('ctam', '123', 'chauquangtam'),
('tam12', '$2y$12$so4ehG93fTCvMErASiO1weyLQ9OXQKYtQupJA14vE4BgR5.7pm2Ia', 'tam5'),
('tam22', '123', 'tam52'),
('ten', '123', 'ten2');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `departs`
--
ALTER TABLE `departs`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `records`
--
ALTER TABLE `records`
  ADD PRIMARY KEY (`id`),
  ADD KEY `staff_id` (`staff_id`);

--
-- Chỉ mục cho bảng `staffs`
--
ALTER TABLE `staffs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `staffs_ibfk_1` (`depart_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `departs`
--
ALTER TABLE `departs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `records`
--
ALTER TABLE `records`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `staffs`
--
ALTER TABLE `staffs`
  MODIFY `id` bigint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `records`
--
ALTER TABLE `records`
  ADD CONSTRAINT `records_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `staffs` (`id`);

--
-- Các ràng buộc cho bảng `staffs`
--
ALTER TABLE `staffs`
  ADD CONSTRAINT `staffs_ibfk_1` FOREIGN KEY (`depart_id`) REFERENCES `departs` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
