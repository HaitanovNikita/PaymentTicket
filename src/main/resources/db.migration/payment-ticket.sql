-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Мар 09 2021 г., 23:31
-- Версия сервера: 10.4.11-MariaDB
-- Версия PHP: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `payment-ticket`
--
CREATE DATABASE IF NOT EXISTS `payment-ticket` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `payment-ticket`;

-- --------------------------------------------------------


--
-- Структура таблицы `route`
--

CREATE TABLE IF NOT EXISTS `route`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT,
    `route_number` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb4;

--
-- Дамп данных таблицы `route`
--

INSERT INTO `route` (`id`, `route_number`)
VALUES (1, 'someRoute1'),
       (2, 'someRoute2'),
       (3, 'someRoute3'),
       (4, 'someRoute4'),
       (5, 'someRoute5'),
       (6, 'someRoute6');

-- --------------------------------------------------------

--
-- Структура таблицы `status`
--

CREATE TABLE IF NOT EXISTS `status`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT,
    `status_name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4;

--
-- Дамп данных таблицы `status`
--

INSERT INTO `status` (`id`, `status_name`)
VALUES (1, 'processed'),
       (2, 'error'),
       (3, 'posted');

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `application`
--


--
-- Структура таблицы `application`
--

CREATE TABLE IF NOT EXISTS `application`
(
    `id`                 bigint   NOT NULL AUTO_INCREMENT,
    `route`              bigint   NOT NULL DEFAULT 1,
    `date_time_dispatch` datetime NOT NULL,
    `status`             bigint   NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)/*,*/
    KEY `FKk2wr7er791lwqiwoysgw5mvns` (`route`),
    KEY `FKeaf0wt64o9ji49300k14s2vph` (`status`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4;

--
-- Дамп данных таблицы `application`
--

INSERT INTO `application` (`id`, `route`, `date_time_dispatch`, `status`)
VALUES (1, 1, '2021-01-02 12:12:12', 1),
       (2, 3, '2021-02-15 08:34:45', 1),
       (3, 6, '2021-03-10 03:45:59', 1),
       (4, 5, '2021-01-02 10:15:29', 2),
       (5, 6, '2020-12-02 11:15:29', 3);

-- --------------------------------------------------------

ALTER TABLE `application`
  ADD CONSTRAINT `FKeaf0wt64o9ji49300k14s2vph` FOREIGN KEY (`status`) REFERENCES `status` (`id`),
  ADD CONSTRAINT `FKk2wr7er791lwqiwoysgw5mvns` FOREIGN KEY (`route`) REFERENCES `route` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
