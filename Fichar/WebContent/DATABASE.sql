-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 28-11-2016 a las 08:50:51
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `FICHAR`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE `estados` (
  `id` int(1) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`id`, `name`) VALUES
(0, 'FIN JORNADA'),
(1, 'TRABAJANDO'),
(2, 'PAUSA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `time` time NOT NULL,
  `date` date NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `state` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `historial`
--

INSERT INTO `historial` (`id`, `user`, `time`, `date`, `latitude`, `longitude`, `state`) VALUES
(1, 2, '10:37:58', '2016-11-24', 37.2134, -7.40472, 1),
(2, 2, '10:38:52', '2016-11-24', 37.2134, -7.40472, 2),
(3, 2, '10:39:04', '2016-11-24', 37.2134, -7.40472, 1),
(4, 3, '10:59:59', '2016-11-24', 37.2134, -7.40473, 1),
(5, 3, '11:18:29', '2016-11-24', 37.2135, -7.40474, 0),
(23, 2, '16:06:00', '2016-11-24', 37.2134, -7.40473, 0),
(24, 4, '16:06:41', '2016-11-24', 37.2134, -7.40474, 1),
(25, 5, '08:17:01', '2016-11-25', 37.2764, -6.93513, 1),
(26, 5, '08:17:18', '2016-11-25', 37.2764, -6.93513, 0),
(27, 5, '08:18:36', '2016-11-22', 37.2764, -6.9352, 1),
(28, 5, '08:18:40', '2016-11-22', 37.2764, -6.9352, 2),
(29, 5, '08:18:53', '2016-11-22', 37.2764, -6.9352, 1),
(30, 5, '08:19:06', '2016-11-22', 37.2764, -6.9352, 0),
(31, 5, '08:23:36', '2016-11-22', 0, 0, 1),
(32, 5, '08:25:31', '2016-11-22', 0, 0, 0),
(33, 5, '08:25:54', '2016-11-22', 0, 0, 1),
(34, 5, '08:30:08', '2016-11-22', 0, 0, 0),
(37, 3, '10:58:20', '2016-11-26', 37.2136, -7.40449, 1),
(45, 3, '11:53:44', '2016-11-26', 37.2136, -7.40448, 2),
(46, 3, '11:53:47', '2016-11-26', 37.2136, -7.40448, 1),
(47, 3, '11:54:32', '2016-11-26', 37.2136, -7.40448, 0),
(54, 3, '12:00:02', '2016-11-26', 0, 0, 2),
(55, 3, '12:00:52', '2016-11-26', 0, 0, 1),
(56, 3, '12:01:10', '2016-11-26', 0, 0, 0),
(57, 2, '12:03:50', '2016-11-26', 37.2134, -7.40471, 1),
(58, 2, '12:12:58', '2016-11-26', 37.2135, -7.40466, 2),
(59, 2, '12:13:09', '2016-11-26', 37.2134, -7.40466, 1),
(60, 2, '12:55:02', '2016-11-26', 37.2135, -7.40472, 2),
(61, 2, '12:57:40', '2016-11-26', 37.2134, -7.40469, 1),
(62, 4, '04:52:11', '2016-11-26', 37.2136, -7.40448, 0),
(63, 2, '07:33:08', '2016-11-26', 37.2136, -7.40449, 0),
(64, 2, '08:39:31', '2016-11-28', 37.2764, -6.93512, 1),
(65, 2, '08:39:34', '2016-11-28', 37.2764, -6.93498, 2),
(66, 2, '08:39:37', '2016-11-28', 37.2764, -6.93498, 1),
(67, 2, '08:39:39', '2016-11-28', 37.2764, -6.93498, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jornada`
--

CREATE TABLE `jornada` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `date` date NOT NULL,
  `hours` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `jornada`
--

INSERT INTO `jornada` (`id`, `user`, `date`, `hours`) VALUES
(1, 3, '2016-11-24', '00:40:31'),
(10, 2, '2016-11-24', '05:26:56'),
(11, 5, '2016-11-25', '00:00:17'),
(12, 5, '2016-11-22', '00:00:04'),
(13, 5, '2016-11-22', '00:00:13'),
(14, 5, '2016-11-22', '00:01:55'),
(15, 5, '2016-11-22', '00:04:14'),
(19, 3, '2016-11-26', '00:55:24'),
(20, 3, '2016-11-26', '00:00:45'),
(21, 3, '2016-11-26', '00:05:22'),
(22, 3, '2016-11-26', '00:00:18'),
(26, 2, '2016-11-28', '00:00:03'),
(27, 2, '2016-11-28', '00:00:02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(30) COLLATE utf8_bin NOT NULL,
  `state` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `name`, `password`, `email`, `state`) VALUES
(1, 'admin', 'admin', 'admin@ejemplo.com', 0),
(2, 'Pepe', '123', 'pepe@ejemplo.com', 0),
(3, 'Marta', '123', 'marta@ejemplo.com', 0),
(4, 'Fernando', '123', 'fernando@ejemplo.com', 0),
(5, 'user1', 'DISABLED', 'user1', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`),
  ADD KEY `state` (`state`);

--
-- Indices de la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `state` (`state`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `historial`
--
ALTER TABLE `historial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
--
-- AUTO_INCREMENT de la tabla `jornada`
--
ALTER TABLE `jornada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historial`
--
ALTER TABLE `historial`
  ADD CONSTRAINT `historial_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `historial_ibfk_2` FOREIGN KEY (`state`) REFERENCES `estados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD CONSTRAINT `jornada_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`state`) REFERENCES `estados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
