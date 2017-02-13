-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 13-02-2017 a las 10:15:37
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE VOTOS;
USE VOTOS;

--
-- Base de datos: `VOTOS`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `candidates`
--

CREATE TABLE `candidates` (
  `id` int(11) NOT NULL,
  `first_name` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `last_name` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `votes` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `curse` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `candidates`
--

INSERT INTO `candidates` (`id`, `first_name`, `last_name`, `votes`, `category`, `curse`) VALUES
(1, 'Manuela', 'Gomez', 2, 1, '2016-2017'),
(3, 'Fernando', 'Rodriguez', 0, 2, '2016-2017'),
(4, 'Jose', 'Martinez', 4, 1, '2016-2017'),
(6, 'Roberta', 'Belgrado', 0, 3, '2016-2017'),
(7, 'Marta', 'Garcia', 2, 1, '2016-2017'),
(8, 'Manuel', 'Garrido', 2, 1, '2016-2017'),
(9, 'Lucia', 'Alvarez', 1, 1, '2016-2017'),
(10, 'Natalia', 'Cuesta', 0, 1, '2016-2017'),
(11, 'Emilio', 'Delgado', 1, 1, '2016-2017'),
(12, 'Belen', 'Lopez', 0, 1, '2016-2017'),
(13, 'Roberto', 'Alonso', 1, 1, '2016-2017'),
(14, 'Nieves', 'Cuesta', 0, 3, '2016-2017'),
(15, 'Vicenta', 'Benito', 0, 3, '2016-2017'),
(16, 'Alicia', 'Sanz', 0, 2, '2016-2017'),
(17, 'Pablo', 'Guerra', 1, 2, '2016-2017'),
(18, 'Jose Miguel', 'Cuesta', 1, 2, '2016-2017'),
(19, 'Maria Luisa', 'Benito', 0, 2, '2016-2017'),
(21, 'Juan', 'Cuesta', 0, 4, '2016-2017'),
(22, 'Andrés', 'Guerra', 0, 4, '2016-2017'),
(23, 'Mauricio', 'Hidalgo', 0, 4, '2016-2017'),
(24, 'Concepcion', 'de la Fuente', 0, 4, '2016-2017'),
(25, 'Maria Jesus', 'Vazquez', 0, 4, '2016-2017'),
(26, 'Beatriz', 'Villarejo', 0, 4, '2016-2017'),
(27, 'Mariona', 'Bieto', 0, 2, '2016-2017');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(40) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Profesorado'),
(2, 'Alumnado'),
(3, 'Personal Administración y Servicios'),
(4, 'Madres, Padres y Tutores');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `candidates`
--
ALTER TABLE `candidates`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category` (`category`);

--
-- Indices de la tabla `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `candidates`
--
ALTER TABLE `candidates`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT de la tabla `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `candidates`
--
ALTER TABLE `candidates`
  ADD CONSTRAINT `candidates_ibfk_1` FOREIGN KEY (`category`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
