-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-10-2025 a las 23:32:52
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
  `nombre` varchar(50) NOT NULL,
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

INSERT INTO `mediciones` (`id_medicion`, `id_sensor`, `nombre`, `uuid`, `rssi`, `major`, `minor`, `medicionCo2`, `latitud`, `longitud`, `timestamp`) VALUES
(1, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -89, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:05'),
(2, 1, 'C29', '4a335006-0180-9909-ffef-f0287c33bd4a', -82, 7936, 0, 1234, 40.123456, -3.123456, '2025-10-06 19:05:06'),
(3, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -94, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:06'),
(4, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -93, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:06'),
(5, 1, 'C29', '4a335006-0180-9909-ffef-f0287c33bd4a', -90, 7936, 0, 1234, 40.123456, -3.123456, '2025-10-06 19:05:07'),
(6, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -90, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:08'),
(7, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -91, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:08'),
(8, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -84, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:08'),
(9, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -84, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:08'),
(10, 1, 'C29', '4a335006-0180-9909-ffef-f0287c33bd4a', -89, 7936, 0, 1234, 40.123456, -3.123456, '2025-10-06 19:05:10'),
(11, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -87, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:10'),
(12, 1, 'C29', '4a335006-0180-9909-ffef-f0287c33bd4a', -84, 7936, 0, 1234, 40.123456, -3.123456, '2025-10-06 19:05:10'),
(13, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -87, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:05:11'),
(14, 1, 'C29', '4a335006-0180-9909-ffef-f0287c33bd4a', -75, 7936, 0, 1234, 40.123456, -3.123456, '2025-10-06 19:09:28'),
(15, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -83, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:09:28'),
(16, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -85, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:09:28'),
(17, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -82, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:09:29'),
(18, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -83, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:09:29'),
(19, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -84, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:09:30'),
(20, 1, 'Hue white lamp', '0ffe0210-ffff-0202-0a0a-0f0948756520', -94, 30568, 26996, 1234, 40.123456, -3.123456, '2025-10-06 19:09:30'),
(21, 1, 'ELK-BLEDDM       ', '44444d20-2020-2020-2020-000000000000', -72, 0, 0, 1234, 40.123456, -3.123456, '2025-10-06 19:13:50'),
(22, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -71, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:50'),
(23, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -71, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:50'),
(24, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -71, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:50'),
(25, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -64, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:51'),
(26, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -68, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:51'),
(27, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -71, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:52'),
(28, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -65, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:53'),
(29, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -65, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:53'),
(30, 1, 'C29', '4a335006-0180-9909-ffef-f0287c33bd4a', -86, 7936, 0, 1234, 40.123456, -3.123456, '2025-10-06 19:13:54'),
(31, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -69, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:54'),
(32, 1, 'C29', '4a335006-0180-9909-ffef-f0287c33bd4a', -87, 7936, 0, 1234, 40.123456, -3.123456, '2025-10-06 19:13:54'),
(33, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -71, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:54'),
(34, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -72, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:54'),
(35, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -67, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:56'),
(36, 1, 'ELK-BLEDDM       ', '424c4544-444d-2020-2020-202020120945', -70, 19531, 11586, 1234, 40.123456, -3.123456, '2025-10-06 19:13:57');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sensores`
--

CREATE TABLE `sensores` (
  `id_sensor` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `nombre_sensor` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sensores`
--

INSERT INTO `sensores` (`id_sensor`, `id_usuario`, `nombre_sensor`, `descripcion`) VALUES
(1, 0, 'SensorCO2-01', 'Sensor de Ferran'),
(2, 0, 'Prueba', 'Este sensor se ha creado para realizar una prueba para la Logica de Negocio');

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
  MODIFY `id_medicion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `sensores`
--
ALTER TABLE `sensores`
  MODIFY `id_sensor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
