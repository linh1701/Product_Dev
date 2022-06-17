CREATE DATABASE IF NOT EXISTS `db_asm_network`;

USE `db_asm_network`;

CREATE TABLE IF NOT EXISTS `tbl_Users`(
    `id` int(11) not null AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(50) not NULL UNIQUE,
    `password` VARCHAR(60) not NULL
);

CREATE TABLE IF NOT EXISTS `tbl_ResetPassword` (
    `id` int(11) not null AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) not NULL,
    `token` VARCHAR(60) not NULL UNIQUE,
    `createdAt` DATETIME NOT NULL DEFAULT NOW(),
    `available` BIT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS `tbl_Categories` (
    `id` int(11) not null AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) not NULL UNIQUE,
    `image` VARCHAR(250) not NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS `tbl_Products`(
    `id` int(11) not null AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) not NULL UNIQUE,
    `price` DECIMAL(5,2) not NULL,
    `quantity` int(11) not NULL,
    `image` VARCHAR(255) not NULL DEFAULT '',
    `category_id` int(11) not null,
    FOREIGN KEY(`category_id`) REFERENCES tbl_Categories(`id`)
);