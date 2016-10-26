-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 26-10-2016 a las 12:05:10
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `MUSICA`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Album`
--

CREATE TABLE `Album` (
  `id` int(3) NOT NULL,
  `grupo` varchar(30) COLLATE utf8_bin NOT NULL,
  `titulo` varchar(50) COLLATE utf8_bin NOT NULL,
  `genero` varchar(20) COLLATE utf8_bin NOT NULL,
  `dispositivos` varchar(10) COLLATE utf8_bin NOT NULL,
  `observaciones` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `Album`
--

INSERT INTO `Album` (`id`, `grupo`, `titulo`, `genero`, `dispositivos`, `observaciones`) VALUES
(1, 'Bob Marley', 'Legen', 'rock', 'LP', 'buen album'),
(4, 'Mozart', 'La flauta mÃ¡gica', 'clasica', 'Otro', ''),
(6, 'The Doors', 'Strange Days', 'rock', 'LP', 'Sin observaciones'),
(7, 'Michael Jackson', 'Bad', 'pop', 'LP', 'Sin observaciones'),
(8, 'Michael Jackson', 'Thriller', 'pop', 'CD', 'Sin observaciones');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Album`
--
ALTER TABLE `Album`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Album`
--
ALTER TABLE `Album`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
