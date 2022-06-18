CREATE DATABASE IF NOT EXISTS `db_sanpham`;

USE `db_sanpham`;


CREATE TABLE IF NOT EXISTS `tbl_Products`(
    `id` int(11) not null AUTO_INCREMENT  PRIMARY KEY,
    `name` VARCHAR(255) not NULL UNIQUE,
    `price` DECIMAL(5,2) not NULL,
    `quantity` int(11) not NULL,
);