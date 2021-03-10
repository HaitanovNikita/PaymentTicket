-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Мар 10 2021 г., 12:19
-- Версия сервера: 10.4.11-MariaDB
-- Версия PHP: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `paymentticket`
--

-- --------------------------------------------------------

--
-- Структура таблицы `application`
--

CREATE TABLE `application` (
                               `id` bigint(20) NOT NULL,
                               `route` bigint(20) NOT NULL DEFAULT 1 REFERENCES  route(id),
                               `date_time_dispatch` datetime NOT NULL,
                               `status` bigint(20) NOT NULL DEFAULT 1 REFERENCES  status(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `application`
--

INSERT INTO `application` (`id`, `route`, `date_time_dispatch`, `status`) VALUES
(1, 1, '2021-01-02 12:12:12', 1),
(2, 3, '2021-02-15 08:34:45', 1),
(3, 6, '2021-03-10 03:45:59', 1),
(4, 5, '2021-01-02 10:15:29', 2),
(5, 6, '2020-12-02 11:15:29', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `route`
--

CREATE TABLE `route` (
                         `id` int(11) NOT NULL,
                         `route_number` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `route`
--

INSERT INTO `route` (`id`, `route_number`) VALUES
(1, 'someRoute1'),
(2, 'someRoute2'),
(3, 'someRoute3'),
(4, 'someRoute4'),
(5, 'someRoute5'),
(6, 'someRoute6');

-- --------------------------------------------------------

--
-- Структура таблицы `status`
--

CREATE TABLE `status` (
                          `id` bigint(20) NOT NULL,
                          `status_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `status`
--

INSERT INTO `status` (`id`, `status_name`) VALUES
(1, 'processed'),
(2, 'error'),
(3, 'posted');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `application`
--
ALTER TABLE `application`
    ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `route`
--
ALTER TABLE `route`
    ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `status`
--
ALTER TABLE `status`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `application`
--
ALTER TABLE `application`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `route`
--
ALTER TABLE `route`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `status`
--
ALTER TABLE `status`
    MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
