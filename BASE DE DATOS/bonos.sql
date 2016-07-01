-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-09-2015 a las 04:59:45
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `bonos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bono`
--

CREATE TABLE IF NOT EXISTS `bono` (
  `Serial` varchar(30) NOT NULL,
  `Valor` double NOT NULL,
  `Estado` varchar(2) NOT NULL,
  `Fecha_pago` date DEFAULT NULL,
  `Codigo_entidad` int(11) NOT NULL,
  `Usuario_creacion` int(11) NOT NULL,
  `Usuario_pago` int(11) DEFAULT NULL,
  `Fecha_creacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entidad`
--

CREATE TABLE IF NOT EXISTS `entidad` (
  `Codigo_entidad` int(11) NOT NULL,
  `Nombre_entidad` varchar(45) NOT NULL,
  `Sufijo` varchar(15) NOT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `Telefono` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `Nuip` int(11) NOT NULL,
  `Tipo` int(11) NOT NULL,
  `Nombres` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `Password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Nuip`, `Tipo`, `Nombres`, `Apellidos`, `Password`) VALUES
(1, 1, 'CARLOS ALFREDO ', 'TORRES MEDINA', '1'),
(2, 0, 'JOSE ALEJANDRO', 'ramirez ', '2');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bono`
--
ALTER TABLE `bono`
  ADD PRIMARY KEY (`Serial`),
  ADD KEY `fk_Bono_Entidad_idx` (`Codigo_entidad`),
  ADD KEY `fk_Bono_Usuario1_idx` (`Usuario_creacion`),
  ADD KEY `fk_Bono_Usuario2_idx` (`Usuario_pago`);

--
-- Indices de la tabla `entidad`
--
ALTER TABLE `entidad`
  ADD PRIMARY KEY (`Codigo_entidad`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Nuip`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bono`
--
ALTER TABLE `bono`
  ADD CONSTRAINT `fk_Bono_Entidad` FOREIGN KEY (`Codigo_entidad`) REFERENCES `entidad` (`Codigo_entidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Bono_Usuario1` FOREIGN KEY (`Usuario_creacion`) REFERENCES `usuario` (`Nuip`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Bono_Usuario2` FOREIGN KEY (`Usuario_pago`) REFERENCES `usuario` (`Nuip`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
