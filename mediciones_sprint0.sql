-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-10-2025 a las 17:20:44
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mediciones_sprint0`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mediciones`
--

CREATE TABLE `mediciones` (
  `id_medicion` int(11) NOT NULL,
  `id_sensor` int(11) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `rssi` int(11) NOT NULL,
  `major` smallint(5) UNSIGNED NOT NULL,
  `minor` smallint(5) UNSIGNED NOT NULL,
  `medicionCo2` smallint(6) NOT NULL,
  `latitud` decimal(9,6) NOT NULL,
  `longitud` decimal(9,6) NOT NULL,
  `timestamp` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mediciones`
--

INSERT INTO `mediciones` (`id_medicion`, `id_sensor`, `uuid`, `rssi`, `major`, `minor`, `medicionCo2`, `latitud`, `longitud`, `timestamp`) VALUES
(80, 1, '', 0, 0, 0, 1234, 39.469900, -0.376300, '2025-10-03 20:42:23'),
(81, 1, '\0ﾣAﾤﾐAVr\0\0\0\0\0\0\0\0', -37, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:43'),
(82, 1, 'ￖ￱5k(DESKTOP-GP3', -52, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:43'),
(83, 1, '\0￁ￏ\0\0\0\0\0\0\0\0', -66, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:43'),
(84, 1, 'ￖ￱5k(DESKTOP-GP3', -52, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:43'),
(85, 1, 'ￖ￱5k(DESKTOP-GP3', -52, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:44'),
(86, 1, '\0￁ￏ\0\0\0\0\0\0\0\0', -69, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:44'),
(87, 1, '\0>￼ￃﾀﾥ\0\0\0\0\0\0\0', -42, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:45'),
(88, 1, 'BLEDDM       	E', -88, 19531, 11586, 1234, 40.000000, -3.123456, '2025-10-04 17:15:45'),
(89, 1, 'ￖ￱5k(DESKTOP-GP3', -52, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:45'),
(90, 1, 'BLEDDM       	E', -89, 19531, 11586, 1234, 40.000000, -3.123456, '2025-10-04 17:15:46'),
(91, 1, '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0', -77, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:46'),
(92, 1, '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0', -37, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:46'),
(93, 1, 'ￖ￱5k(DESKTOP-GP3', -50, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:46'),
(94, 1, '\0>￼ￃﾀﾥ\0\0\0\0\0\0\0', -35, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:46'),
(95, 1, 'BLEDDM       	E', -89, 19531, 11586, 1234, 40.000000, -3.123456, '2025-10-04 17:15:47'),
(96, 1, 'ￖ￱5k(DESKTOP-GP3', -52, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:47'),
(97, 1, 'BLEDDM       	E', -88, 19531, 11586, 1234, 40.000000, -3.123456, '2025-10-04 17:15:47'),
(98, 1, '\0>￼ￃﾀﾥ\0\0\0\0\0\0\0', -43, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:47'),
(99, 1, 'ￖ￱5k(DESKTOP-GP3', -50, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:47'),
(100, 1, '\0:}Jￚ\0\0\0\0\0\0', -88, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:47'),
(101, 1, '\0￁ￏ\0\0\0\0\0\0\0\0', -69, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:48'),
(102, 1, 'ￖ￱5k(DESKTOP-GP3', -51, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:48'),
(103, 1, 'ￖ￱5k(DESKTOP-GP3', -47, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:48'),
(104, 1, 'ￖ￱5k(DESKTOP-GP3', -50, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:49'),
(105, 1, 'BLEDDM       	E', -86, 19531, 11586, 1234, 40.000000, -3.123456, '2025-10-04 17:15:49'),
(106, 1, '\0>￼ￃﾀﾥ\0\0\0\0\0\0\0', -40, 0, 0, 1234, 40.000000, -3.123456, '2025-10-04 17:15:49'),
(107, 1, 'ￖ￱5k(DESKTOP-GP3', -52, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:49'),
(108, 1, 'BLEDDM       	E', -89, 19531, 11586, 1234, 40.000000, -3.123456, '2025-10-04 17:15:49'),
(109, 1, 'ￖ￱5k(DESKTOP-GP3', -51, 17990, 13122, 1234, 40.000000, -3.123456, '2025-10-04 17:15:51');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sensores`
--

CREATE TABLE `sensores` (
  `id_sensor` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `nombre_sensor` varchar(100) NOT NULL,
  `uuid` char(36) NOT NULL,
  `major` smallint(6) NOT NULL,
  `minor` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sensores`
--

INSERT INTO `sensores` (`id_sensor`, `id_usuario`, `nombre_sensor`, `uuid`, `major`, `minor`) VALUES
(1, 0, 'SensorCO2-01', '123e4567-e89b-12d3-a456-426614174000', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre`, `apellidos`, `correo`, `telefono`) VALUES
(0, 'Ferran', 'Sansaloni Prats', 'ferransansaloni@gmail.com', '644475837');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mediciones`
--
ALTER TABLE `mediciones`
  ADD PRIMARY KEY (`id_medicion`),
  ADD KEY `mediciones_ibfk_1` (`id_sensor`);

--
-- Indices de la tabla `sensores`
--
ALTER TABLE `sensores`
  ADD PRIMARY KEY (`id_sensor`),
  ADD KEY `sensores_ibfk_1` (`id_usuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mediciones`
--
ALTER TABLE `mediciones`
  MODIFY `id_medicion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT de la tabla `sensores`
--
ALTER TABLE `sensores`
  MODIFY `id_sensor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mediciones`
--
ALTER TABLE `mediciones`
  ADD CONSTRAINT `mediciones_ibfk_1` FOREIGN KEY (`id_sensor`) REFERENCES `sensores` (`id_sensor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sensores`
--
ALTER TABLE `sensores`
  ADD CONSTRAINT `sensores_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
