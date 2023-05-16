-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-05-2023 a las 13:19:38
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `soundBridge`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `album`
--

CREATE TABLE `album` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `releaseYear` year(4) NOT NULL,
  `cover` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `album`
--

INSERT INTO `album` (`id`, `name`, `releaseYear`, `cover`) VALUES
(1, 'Origin', '2018', 'img/cover/origin.png'),
(2, 'Un Verano Sin Ti', '2022', 'img/cover/unveranosinti.png'),
(3, 'Donde Quiero Estar', '2023', 'img/cover/dondequieroestar.png'),
(4, 'The Marshall Mathers LP', '2000', 'img/cover/themarshallmatherslp.png'),
(5, 'Corazón Cromado', '2021', 'img/cover/corazoncromado.png'),
(6, 'Multitude', '2022', 'img/cover/multitude.png'),
(7, 'Mejor Que El Silencio', '2011', 'img/cover/mejorqueelsilencio.png'),
(8, 'El último tour del mundo', '2020', 'img/cover/elultimotourdelmundo.png'),
(9, 'YHLQMDLG', '2020', 'img/cover/YHLQMDLG.png'),
(10, 'Inkebrantable', '2023', 'img/cover/Inkebrantable.png'),
(11, 'Dislike to False Metal', '2023', 'img/cover/disliketofalsemetal.png'),
(12, 'Back in Black', '1980', 'img/cover/backinblack.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artgroup`
--

CREATE TABLE `artgroup` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `description` varchar(300) NOT NULL,
  `image` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `artgroup`
--

INSERT INTO `artgroup` (`id`, `name`, `description`, `image`) VALUES
(1, 'Imagine Dragons', 'Imagine Dragons es una banda estadounidense de pop rock originaria de Las Vegas, Nevada.', 'img/artist/imaginedragons.png'),
(2, 'SFDK', 'SFDK es un grupo de hip hop sevillano formado por Zatu y Acción Sánchez.', 'img/artist/sfdk.png'),
(3, 'Nanowar of Steel', 'Nanowar of Steel es un grupo italiano y sanmarinense de heavy metal cómico o freak metal. Su nombre proviene de cambiar la primera letra de Manowar, de manera que representa su tendencia a ridiculizar y a satirizar.', 'img/artist/nanowarofsteel.png'),
(4, 'AC/DC', 'AC/DC es una banda de hard rock británica-australiana, formada en 1973 en Australia por los hermanos escoceses Malcolm Young y Angus Young.', 'img/artist/acdc.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artist`
--

CREATE TABLE `artist` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `lastname` varchar(60) DEFAULT NULL,
  `nationality` varchar(40) DEFAULT NULL,
  `gender` enum('Hombre','Mujer','Otro') NOT NULL,
  `birthDate` date DEFAULT NULL,
  `role` set('Cantante','Guitarrista','Bajista','Baterista') DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `image` varchar(150) DEFAULT NULL,
  `idGroup` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `artist`
--

INSERT INTO `artist` (`id`, `name`, `lastname`, `nationality`, `gender`, `birthDate`, `role`, `description`, `image`, `idGroup`) VALUES
(1, 'Dan', 'Reynolds', 'Estados Unidos', 'Hombre', '1987-07-14', 'Cantante', NULL, NULL, 1),
(2, 'Wayne', 'Sermon', 'Estados Unidos', 'Hombre', '1984-06-15', 'Guitarrista', NULL, NULL, 1),
(3, 'Ben', 'McKee', 'Estados Unidos', 'Hombre', '1985-04-07', 'Bajista', NULL, NULL, 1),
(4, 'Daniel', 'Platzman', 'Estados Unidos', 'Hombre', '1986-09-28', 'Baterista', NULL, NULL, 1),
(5, 'Bad Bunny', NULL, 'Puerto Rico', 'Hombre', '1994-03-10', 'Cantante', 'Benito Antonio Martínez Ocasio, conocido artísticamente como Bad Bunny, es un rapero, cantante, compositor y productor puertorriqueño.', 'img/artist/badbunny.png', NULL),
(6, 'Quevedo', NULL, 'España', 'Hombre', '2001-12-07', 'Cantante', 'Pedro Luis Domínguez Quevedo, conocido artísticamente como Quevedo, es un cantante español de reguetón, trap y pop rap.', 'img/artist/quevedo.png', NULL),
(7, 'Eminem', NULL, 'Estados Unidos', 'Hombre', '1972-10-17', 'Cantante', 'Marshall Bruce Mathers III, más conocido como Eminem es un rapero, cantautor, productor y actor estadounidense.', 'img/artist/eminem.png', NULL),
(8, 'Sen Senra', NULL, 'España', 'Hombre', '1995-12-17', 'Cantante', 'Christian Senra, conocido artísticamente como Sen Senra, es un músico gallego que define su estilo como “música libre”.', 'img/artist/sensenra.png', NULL),
(9, 'Stromae', NULL, 'Bélgica', 'Hombre', '1985-03-12', 'Cantante', 'Paul van Haver, más conocido por su nombre artístico Stromae, es un cantante, compositor y productor belga, que ha desarrollado su carrera en el hip hop y la música electrónica.', 'img/artist/stromae.png', NULL),
(10, 'Nach', NULL, 'España', 'Hombre', '1974-10-01', 'Cantante', 'Ignacio Fornés Olmo, más conocido como Nach, es un rapero y compositor español.', 'img/artist/nach.png', NULL),
(11, 'Saturnino', 'Rey', 'España', 'Hombre', '1977-05-16', 'Cantante', NULL, NULL, 2),
(12, 'Óscar', 'Sánchez', 'España', 'Hombre', '1977-02-24', 'Baterista', NULL, NULL, 2),
(13, 'Carlo Alberto', 'Fiaschi', 'Italia', 'Hombre', NULL, 'Cantante', NULL, NULL, 3),
(14, 'Raffaello', 'Venditti', 'Italia', 'Hombre', NULL, 'Cantante', NULL, NULL, 3),
(15, 'Valerio', 'Storch', 'Italia', 'Hombre', NULL, 'Guitarrista', NULL, NULL, 3),
(16, 'Edoardo', 'Carlesi', 'Italia', 'Hombre', NULL, 'Bajista', NULL, NULL, 3),
(17, 'Alessandro', 'Milone', 'Italia', 'Hombre', NULL, 'Baterista', NULL, NULL, 3),
(18, 'Brian', 'Johnson', 'Inglaterra', 'Hombre', '1947-10-05', 'Cantante', NULL, NULL, 4),
(19, 'Angus', 'Young', 'Australia', 'Hombre', '1955-03-31', 'Guitarrista', NULL, NULL, 4),
(20, 'Phil', 'Rudd', 'Australia', 'Hombre', '1954-05-19', 'Baterista', NULL, NULL, 4),
(21, 'Cliff', 'Williams', 'Inglaterra', 'Hombre', '1949-12-14', 'Bajista', NULL, NULL, 4),
(22, 'Stevie', 'Young', 'Australia', 'Hombre', '1956-12-11', 'Guitarrista', NULL, NULL, 4);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `averagestars`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `averagestars` (
`id` int(11)
,`name` varchar(40)
,`average` decimal(6,2)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `lastName` varchar(60) NOT NULL,
  `nationality` varchar(40) DEFAULT NULL,
  `gender` enum('Hombre','Mujer','Otro') NOT NULL,
  `birthDate` date NOT NULL,
  `personalId` char(9) NOT NULL,
  `telephone` char(12) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `type` enum('Basic','Premium','Premium Plus') NOT NULL DEFAULT 'Basic',
  `username` varchar(20) NOT NULL CHECK (octet_length(`username`) >= 5),
  `passwd` varchar(30) NOT NULL CHECK (octet_length(`passwd`) >= 10),
  `isBlocked` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`id`, `name`, `lastName`, `nationality`, `gender`, `birthDate`, `personalId`, `telephone`, `email`, `address`, `type`, `username`, `passwd`, `isBlocked`) VALUES
(1, 'Paula', 'Berasategui', 'España', 'Mujer', '1989-11-28', '19356723H', '+34693459012', 'paula_bera@gmail.com', 'Calle Aldai 4, 3C, 48901 Barakaldo, Bizkaia, España', 'Basic', 'paulabera', 'paulita1989', 0),
(2, 'Andrés', 'Rodriguez', 'España', 'Hombre', '1975-05-01', '12478901X', '+34690092378', 'andres_rodri@gmail.com', 'Calle Castillejo 12, 1B, 28001 Madrid, Madrid, España', 'Premium', 'andresrodri', 'andresito1975', 0),
(3, 'Juanjo', 'Cortés', 'Argentina', 'Hombre', '1971-09-08', '13412395N', '+54609866670', 'juanjo_cortes@gmail.com', 'Calle de Gracia 23, 4D, B1406 Buenos Aires, Argentina', 'Premium Plus', 'juanjocortes', 'juanjito1971', 0),
(4, 'Laura', 'Marquez', 'España', 'Otro', '2003-01-15', '20982629M', '+34688467261', 'laura_marquez@gmail.com', 'Avenida Los Llanos 10, 3D, B1406 Madrid, España', 'Premium', 'lauralamejor', 'laura2003!!', 0),
(5, 'Eneko', 'Lazaro', 'España', 'Hombre', '1999-12-20', '18260124P', '+34633374890', 'eneko_lazaro@gmail.com', 'Calle Carlos VII 3, 8A, Barcelona, España', 'Premium Plus', 'peneko', 'eneko1999!', 0),
(6, 'Leire', 'Lasa', 'España', 'Mujer', '1996-02-09', '16099121H', '+34649808865', 'leirelasa@gmail.com', 'Calle Aldai Azpi 29, 48630 Gorliz, Bizkaia, España', 'Premium Plus', 'leire', 'leirelasaamo', 0),
(7, 'Borja', 'Saiz', 'España', 'Hombre', '1980-11-20', '19356724H', '+34693459042', 'borja_saiz@gmail.com', 'Calle Aldai 6, 3C, 48901 San Inazio, Bizkaia, España', 'Premium Plus', 'borjita', 'bustamante', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientp`
--

CREATE TABLE `clientp` (
  `idClient` int(11) NOT NULL,
  `bankAccount` char(20) NOT NULL,
  `suscriptionDate` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientp`
--

INSERT INTO `clientp` (`idClient`, `bankAccount`, `suscriptionDate`) VALUES
(2, '20005709911307627771', '2020-09-21 00:00:00'),
(4, '20005709911307627771', '2029-03-11 00:00:00');

--
-- Disparadores `clientp`
--
DELIMITER $$
CREATE TRIGGER `add_fav_clientp` AFTER INSERT ON `clientp` FOR EACH ROW BEGIN
  	INSERT INTO playlist (name, idClientP)
	VALUES	('Favoritos', NEW.idClient);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `delete_playlists_clientp` AFTER DELETE ON `clientp` FOR EACH ROW BEGIN
  	DELETE FROM playlist 
	WHERE idClientP=OLD.idClient;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientpp`
--

CREATE TABLE `clientpp` (
  `idClient` int(11) NOT NULL,
  `bankAccount` char(20) NOT NULL,
  `suscriptionDate` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientpp`
--

INSERT INTO `clientpp` (`idClient`, `bankAccount`, `suscriptionDate`) VALUES
(3, '01388432777572465033', '2022-07-08 00:00:00'),
(5, '28346926384962893649', '2016-02-28 00:00:00'),
(6, '28346926384962893648', '2023-01-20 00:00:00'),
(7, '01388432777572465030', '2023-04-29 00:00:00');

--
-- Disparadores `clientpp`
--
DELIMITER $$
CREATE TRIGGER `add_fav_clientpp` AFTER INSERT ON `clientpp` FOR EACH ROW BEGIN
  	INSERT INTO playlist (name, idClientPP)
	VALUES	('Favoritos', NEW.idClient);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `delete_playlists_clientpp` AFTER DELETE ON `clientpp` FOR EACH ROW BEGIN
  	DELETE FROM playlist 
	WHERE idClientPP=OLD.idClient;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contain`
--

CREATE TABLE `contain` (
  `playlistId` int(11) NOT NULL,
  `songId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `contain`
--

INSERT INTO `contain` (`playlistId`, `songId`) VALUES
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 15),
(2, 16),
(7, 37),
(7, 38),
(8, 9),
(8, 10),
(8, 13),
(8, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `lastName` varchar(60) NOT NULL,
  `nationality` varchar(40) DEFAULT NULL,
  `gender` enum('Hombre','Mujer','Otro') NOT NULL,
  `birthDate` date NOT NULL,
  `personalId` char(9) NOT NULL,
  `telephone` char(12) NOT NULL,
  `email` varchar(60) NOT NULL,
  `address` varchar(200) NOT NULL,
  `username` varchar(20) NOT NULL CHECK (octet_length(`username`) >= 5),
  `passwd` varchar(30) NOT NULL CHECK (octet_length(`passwd`) >= 10),
  `SSNum` char(12) NOT NULL,
  `salary` smallint(5) UNSIGNED DEFAULT NULL,
  `hireDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `employee`
--

INSERT INTO `employee` (`id`, `name`, `lastName`, `nationality`, `gender`, `birthDate`, `personalId`, `telephone`, `email`, `address`, `username`, `passwd`, `SSNum`, `salary`, `hireDate`) VALUES
(1, 'Pedro', 'Lázaro', 'España', 'Hombre', '1998-01-27', '74257801J', '+34678250125', 'pedro_lazaro@soundbridge.com', 'Calle Itsasbide 13, 1A, 48630 Gorliz, Bizkaia, España', 'pedrosb', 'soundbridge', '124363072410', 1200, '2022-02-10');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `genresbygender`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `genresbygender` (
`gender` enum('Hombre','Mujer','Otro')
,`genre` varchar(40)
,`number` bigint(21)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `play`
--

CREATE TABLE `play` (
  `id` int(11) NOT NULL,
  `idClient` int(11) DEFAULT NULL,
  `idSong` int(11) DEFAULT NULL,
  `playDate` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `play`
--

INSERT INTO `play` (`id`, `idClient`, `idSong`, `playDate`) VALUES
(1, 1, 1, '2023-02-13 00:00:00'),
(2, 2, 1, '2023-03-01 00:00:00'),
(3, 3, 1, '2023-01-04 00:00:00'),
(4, 4, 1, '2023-01-09 00:00:00'),
(5, 5, 1, '2023-01-28 00:00:00'),
(6, 1, 1, '2023-02-22 00:00:00'),
(7, 1, 2, '2023-01-20 00:00:00'),
(8, 1, 3, '2023-02-09 00:00:00'),
(9, 1, 4, '2023-01-01 00:00:00'),
(10, 1, 5, '2023-01-03 00:00:00'),
(11, 1, 5, '2023-03-02 00:00:00'),
(12, 1, 5, '2023-01-15 00:00:00'),
(13, 1, 6, '2023-01-17 00:00:00'),
(14, 3, 7, '2023-02-01 00:00:00'),
(15, 3, 8, '2023-03-03 00:00:00'),
(16, 3, 8, '2023-01-05 00:00:00'),
(17, 3, 8, '2023-02-03 00:00:00'),
(18, 3, 9, '2023-01-29 00:00:00'),
(19, 3, 9, '2023-01-15 00:00:00'),
(20, 3, 10, '2023-03-04 00:00:00'),
(21, 3, 11, '2023-01-01 00:00:00'),
(22, 3, 12, '2023-02-05 00:00:00'),
(23, 4, 13, '2023-01-08 00:00:00'),
(24, 4, 14, '2023-03-12 00:00:00'),
(25, 4, 10, '2023-01-09 00:00:00'),
(26, 4, 12, '2023-01-17 00:00:00'),
(27, 4, 11, '2023-03-19 00:00:00'),
(28, 4, 1, '2023-01-01 00:00:00'),
(29, 4, 6, '2023-01-14 00:00:00'),
(30, 4, 8, '2023-02-24 00:00:00'),
(31, 4, 9, '2023-03-15 00:00:00'),
(32, 2, 12, '2023-01-01 00:00:00'),
(33, 2, 10, '2023-01-02 00:00:00'),
(34, 2, 14, '2023-02-05 00:00:00'),
(35, 2, 14, '2023-01-09 00:00:00'),
(36, 2, 13, '2023-01-08 00:00:00'),
(37, 2, 3, '2023-03-01 00:00:00'),
(38, 2, 1, '2023-01-17 00:00:00'),
(39, 2, 4, '2023-02-24 00:00:00'),
(40, 5, 3, '2023-01-29 00:00:00'),
(41, 5, 6, '2023-03-21 00:00:00'),
(42, 5, 1, '2023-01-10 00:00:00'),
(43, 5, 10, '2023-02-01 00:00:00'),
(44, 5, 2, '2023-01-23 00:00:00'),
(45, 5, 7, '2023-01-30 00:00:00'),
(46, 5, 9, '2023-03-31 00:00:00'),
(47, 5, 12, '2023-01-14 00:00:00'),
(48, 5, 10, '2023-02-01 00:00:00'),
(49, 7, 38, '2023-05-16 13:15:02'),
(50, 7, 38, '2023-05-16 13:15:03'),
(51, 7, 38, '2023-05-16 13:15:03'),
(52, 7, 38, '2023-05-16 13:15:03'),
(53, 7, 38, '2023-05-16 13:15:04'),
(54, 7, 38, '2023-05-16 13:15:04'),
(55, 7, 38, '2023-05-16 13:15:04'),
(56, 7, 38, '2023-05-16 13:15:04'),
(57, 7, 38, '2023-05-16 13:15:04'),
(58, 7, 38, '2023-05-16 13:15:05'),
(59, 7, 38, '2023-05-16 13:15:05'),
(60, 7, 38, '2023-05-16 13:15:05'),
(61, 7, 38, '2023-05-16 13:15:05'),
(62, 7, 38, '2023-05-16 13:15:05'),
(63, 7, 38, '2023-05-16 13:15:05'),
(64, 7, 38, '2023-05-16 13:15:06'),
(65, 7, 38, '2023-05-16 13:15:06'),
(66, 7, 37, '2023-05-16 13:15:06'),
(67, 7, 37, '2023-05-16 13:15:07'),
(68, 7, 37, '2023-05-16 13:15:07'),
(69, 7, 37, '2023-05-16 13:15:07'),
(70, 7, 37, '2023-05-16 13:15:07'),
(71, 7, 37, '2023-05-16 13:15:07'),
(72, 7, 37, '2023-05-16 13:15:07'),
(73, 7, 37, '2023-05-16 13:15:07'),
(74, 7, 37, '2023-05-16 13:15:07'),
(75, 7, 25, '2023-05-16 13:15:19'),
(76, 7, 25, '2023-05-16 13:15:19'),
(77, 7, 26, '2023-05-16 13:15:19'),
(78, 7, 26, '2023-05-16 13:15:20'),
(79, 7, 39, '2023-05-16 13:15:30'),
(80, 7, 39, '2023-05-16 13:15:30'),
(81, 7, 39, '2023-05-16 13:15:30'),
(82, 7, 40, '2023-05-16 13:15:31'),
(83, 7, 40, '2023-05-16 13:15:31'),
(84, 7, 40, '2023-05-16 13:15:31'),
(85, 7, 40, '2023-05-16 13:15:31'),
(86, 7, 37, '2023-05-16 13:15:51');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `playlist`
--

CREATE TABLE `playlist` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `creationDate` datetime NOT NULL DEFAULT current_timestamp(),
  `idClientP` int(11) DEFAULT NULL,
  `idClientPp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `playlist`
--

INSERT INTO `playlist` (`id`, `name`, `description`, `creationDate`, `idClientP`, `idClientPp`) VALUES
(1, 'Favoritos', NULL, '2022-07-08 00:00:00', NULL, 3),
(2, 'Verano', NULL, '2023-02-20 00:00:00', NULL, 3),
(3, 'Favoritos', NULL, '2020-09-21 00:00:00', 2, NULL),
(4, 'Favoritos', NULL, '2020-09-21 00:00:00', 4, NULL),
(5, 'Favoritos', NULL, '2020-09-21 00:00:00', NULL, 5),
(6, 'Favoritos', NULL, '2020-09-21 00:00:00', NULL, 6),
(7, 'Favoritos', NULL, '2020-09-21 00:00:00', NULL, 7),
(8, 'Metal', 'Lo que escucho cuando nadie me ve...', '2023-05-16 13:16:40', NULL, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `review`
--

CREATE TABLE `review` (
  `idClientPP` int(11) NOT NULL,
  `idAlbum` int(11) NOT NULL,
  `stars` tinyint(4) NOT NULL CHECK (`stars` between 1 and 5),
  `title` varchar(100) DEFAULT NULL,
  `opinion` varchar(500) DEFAULT NULL,
  `reviewDate` datetime DEFAULT current_timestamp(),
  `isValidated` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `review`
--

INSERT INTO `review` (`idClientPP`, `idAlbum`, `stars`, `title`, `opinion`, `reviewDate`, `isValidated`) VALUES
(3, 1, 5, 'Opinión sobre Origin', 'No sabría explicar por qué.Pero es de esos grupos que te engancha a la primera escucha.¿No os ha pasado que atiendes por vez primera a una canción y al rato la tarareas o empiezas a recordar un trozo de la melodía?.Pues bien, con Origins, vuelve a ocurrir.', '2023-02-01 00:00:00', 0),
(3, 2, 1, 'No se que acabo de escuchar', 'El conejo malo,nada más que añadir.', '2023-02-22 00:00:00', 1),
(3, 3, 2, 'Crítica álbum Quevedo', 'No entiendo como a la gente le puede gustar este estilo de música de letras vacías y algunas veces incluso machistas.Un chico que se hace conocido por 4 canciones que no son ni si quiera solo suyas y cree que puede sacar un buen disco.', '2023-01-10 00:00:00', 0),
(3, 4, 5, 'Eminem', 'The best album I have heard in my live', '2023-01-29 00:00:00', 0),
(5, 1, 1, ' Origin', 'Vaya basura de álbum', '2023-03-22 00:00:00', 0),
(5, 2, 5, 'Bad Bunny es el mejor', 'El álbum del año, sé que hay quien no lo quiera aceptar pero Benito es el artista número 1 a nivel mundial actualmente y en este proyecto obviamente lo demostró, Un Verano Sin Ti es el intro del 2022.Me encantó que este álbum se sintiera como una mezcla de todos sus estilos musicales, el proyecto combina una cantidad de géneros de una manera bastante curiosa, de una manera EFECTIVA', '2023-03-14 00:00:00', 1),
(5, 3, 5, 'Quevedo juega en otra liga', 'Que grande quevedo,desde abajo en las canarias hasta el rey de españa, futuro padre de la música urbana.', '2023-02-14 00:00:00', 0),
(5, 4, 2, 'Ñe', 'I expected more from this album', '2023-01-25 00:00:00', 0),
(6, 5, 5, 'Desde Colors', 'La primera vez que escuché a Sen Senra fue en una sesión de Colors Show. Desde entonces, no dejo de escuhar este disco.', '2023-04-25 00:00:00', 0),
(7, 11, 5, 'Esto es música', 'Que el reggaetón desaparezca del mapa, quiero más álbumes como este!', '2023-05-01 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `singles`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `singles` (
`id` int(11)
,`name` varchar(40)
,`releaseYear` year(4)
,`duration` smallint(6)
,`cover` varchar(150)
,`source` varchar(150)
,`genre` varchar(40)
,`lang` varchar(20)
,`idAlbum` int(11)
,`idArtist` int(11)
,`idGroup` int(11)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `song`
--

CREATE TABLE `song` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `releaseYear` year(4) NOT NULL,
  `duration` smallint(6) NOT NULL,
  `cover` varchar(150) DEFAULT NULL,
  `source` varchar(150) NOT NULL,
  `genre` varchar(40) DEFAULT NULL,
  `lang` varchar(20) DEFAULT NULL,
  `idAlbum` int(11) DEFAULT NULL,
  `idArtist` int(11) DEFAULT NULL,
  `idGroup` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `song`
--

INSERT INTO `song` (`id`, `name`, `releaseYear`, `duration`, `cover`, `source`, `genre`, `lang`, `idAlbum`, `idArtist`, `idGroup`) VALUES
(1, 'Natural', '2018', 190, NULL, 'music/natural.mp3', 'Pop Rock', 'Inglés', 1, NULL, 1),
(2, 'Machine', '2018', 181, NULL, 'music/machine.mp3', 'Pop Rock', 'Inglés', 1, NULL, 1),
(3, 'Bad Liar', '2018', 261, NULL, 'music/badliar.mp3', 'Pop Rock', 'Inglés', 1, NULL, 1),
(4, 'Zero', '2018', 213, NULL, 'music/zero.mp3', 'Pop Rock', 'Inglés', 1, NULL, 1),
(5, 'Digital', '2018', 201, NULL, 'music/digital.mp3', 'Pop Rock', 'Inglés', 1, NULL, 1),
(6, 'Moscow Mule', '2022', 246, NULL, 'music/moscowmule.mp3', 'Reggaetón', 'Español', 2, 5, NULL),
(7, 'Me porto bonito', '2022', 179, NULL, 'music/meportobonito.mp3', 'Reggaetón', 'Español', 2, 5, NULL),
(8, 'Tití Me Preguntó', '2022', 245, NULL, 'music/titimepregunto.mp3', 'Reggaetón', 'Español', 2, 5, NULL),
(9, 'Tarot', '2022', 237, NULL, 'music/tarot.mp3', 'Reggaetón', 'Español', 2, 5, NULL),
(10, 'Efecto', '2022', 213, NULL, 'music/efecto.mp3', 'Reggaetón', 'Español', 2, 5, NULL),
(11, 'Te Mudaste', '2020', 130, NULL, 'music/temudaste.mp3', 'Reggaetón', 'Español', 8, 5, NULL),
(12, 'Hoy Cobré', '2020', 163, NULL, 'music/hoycobre.mp3', 'Reggaetón', 'Español', 8, 5, NULL),
(13, 'Dakiti', '2020', 205, NULL, 'music/dakiti.mp3', 'Reggaetón', 'Español', 8, 5, NULL),
(14, 'La Difícil', '2020', 163, NULL, 'music/ladificil.mp3', 'Reggaetón', 'Español', 9, 5, NULL),
(15, 'La Santa', '2020', 206, NULL, 'music/lasanta.mp3', 'Reggaetón', 'Español', 9, 5, NULL),
(16, 'Yo Perreo Sola', '2020', 172, NULL, 'music/yoperreosola.mp3', 'Reggaetón', 'Español', 9, 5, NULL),
(17, 'Safaera', '2020', 296, NULL, 'music/safaera.mp3', 'Reggaetón', 'Español', 9, 5, NULL),
(18, 'La Jumpa (feat. Arcangel)', '2022', 256, 'img/cover/lajumpa.png', 'music/lajumpa.mp3', 'Reggaetón', 'Español', NULL, 5, NULL),
(19, 'Vista al Mar', '2023', 182, NULL, 'music/vistaalmar.mp3', 'Reggaetón', 'Español', 3, 6, NULL),
(20, 'Playa del Inglés', '2023', 238, NULL, 'music/playadelingles.mp3', 'Reggaetón', 'Español', 3, 6, NULL),
(21, 'Sin señal', '2023', 185, NULL, 'music/sinsenal.mp3', 'Reggaetón', 'Español', 3, 6, NULL),
(22, 'Punto G', '2023', 152, NULL, 'music/puntog.mp3', 'Reggaetón', 'Español', 3, 6, NULL),
(23, 'Wanda', '2023', 161, NULL, 'music/wanda.mp3', 'Reggaetón', 'Español', 3, 6, NULL),
(24, 'El Tonto (feat. Lola Indigo)', '2023', 189, 'img/cover/eltonto.png', 'music/eltonto.mp3', 'Reggaetón', 'Español', NULL, 6, NULL),
(25, 'The Real Slim Shady', '2000', 284, NULL, 'music/therealslimshady.mp3', 'Rap', 'Inglés', 4, 7, NULL),
(26, 'Stan', '2000', 404, NULL, 'music/stan.mp3', 'Rap', 'Inglés', 4, 7, NULL),
(27, 'Perfecto', '2021', 161, NULL, 'music/perfecto.mp3', 'Pop', 'Español', 5, 8, NULL),
(28, 'Wu Wu', '2021', 208, NULL, 'music/wuwu.mp3', 'Pop', 'Español', 5, 8, NULL),
(29, 'No Quiero Ser Un Cantante', '2023', 175, 'img/cover/noquieroseruncantante.png', 'music/noquieroseruncantante.mp3', 'Pop', 'Español', NULL, 8, NULL),
(30, 'Santé', '2022', 191, NULL, 'music/sante.mp3', 'Dance-Pop', 'Francés', 6, 9, NULL),
(31, 'Fils de joie', '2022', 195, NULL, 'music/filsdejoie.mp3', 'Dance-Pop', 'Francés', 6, 9, NULL),
(32, 'El Idioma de los Dioses', '2011', 253, NULL, 'music/elidiomadelosdioses.mp3', 'Rap', 'Español', 7, 10, NULL),
(33, 'Ni Estabas Ni Estarás', '2011', 213, NULL, 'music/niestabasniestaras.mp3', 'Rap', 'Español', 7, 10, NULL),
(34, 'Sin-Ceros', '2023', 235, NULL, 'music/sinceros.mp3', 'Rap', 'Español', 10, NULL, 2),
(35, 'Señores en el Brunch', '2023', 198, NULL, 'music/senoresenelbrunch.mp3', 'Rap', 'Español', 10, NULL, 2),
(36, 'Ringui Dingui (feat. Kase.O)', '2021', 213, 'img/cover/ringuidingui.png', 'music/ringuidingui.mp3', 'Rap', 'Español', NULL, NULL, 2),
(37, 'Sober', '2023', 232, NULL, 'music/sober.mp3', 'Metal', 'Inglés', 11, NULL, 3),
(38, 'Disco Metal', '2023', 272, NULL, 'music/discometal.mp3', 'Metal', 'Inglés', 11, NULL, 3),
(39, 'You Shook Me All Night Long', '1980', 210, NULL, 'music/youshookmeallnightlong.mp3', 'Rock', 'Inglés', 12, NULL, 4),
(40, 'Back in Black', '1980', 254, NULL, 'music/backinblack.mp3', 'Rock', 'Inglés', 12, NULL, 4);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `top20`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `top20` (
`id` int(11)
,`name` varchar(40)
,`plays` bigint(21)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `topartists`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `topartists` (
`name` varchar(40)
,`plays` bigint(21)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `averagestars`
--
DROP TABLE IF EXISTS `averagestars`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `soundbridge`.`averagestars`  AS SELECT DISTINCT `first`.`id` AS `id`, `first`.`name` AS `name`, ifnull(`second`.`average`,0) AS `average` FROM ((select `A`.`id` AS `id`,`A`.`name` AS `name` from (`soundbridge`.`album` `A` left join `soundbridge`.`review` `R` on(`R`.`idAlbum` = `A`.`id`))) `First` left join (select `soundbridge`.`review`.`idAlbum` AS `idAlbum`,ifnull(round(avg(`soundbridge`.`review`.`stars`),2),0) AS `average` from `soundbridge`.`review` where `soundbridge`.`review`.`isValidated` = 1 group by `soundbridge`.`review`.`idAlbum`) `Second` on(`first`.`id` = `second`.`idAlbum`)) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `genresbygender`
--
DROP TABLE IF EXISTS `genresbygender`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `soundbridge`.`genresbygender`  AS SELECT `C`.`gender` AS `gender`, `S`.`genre` AS `genre`, count(`S`.`genre`) AS `number` FROM ((`soundbridge`.`play` `P` join `soundbridge`.`client` `C` on(`P`.`idClient` = `C`.`id`)) join `soundbridge`.`song` `S` on(`P`.`idSong` = `S`.`id`)) GROUP BY `C`.`gender`, `S`.`genre` ORDER BY `C`.`gender` ASC, count(`S`.`genre`) DESC ;

-- --------------------------------------------------------

--
-- Estructura para la vista `singles`
--
DROP TABLE IF EXISTS `singles`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `soundbridge`.`singles`  AS SELECT `soundbridge`.`song`.`id` AS `id`, `soundbridge`.`song`.`name` AS `name`, `soundbridge`.`song`.`releaseYear` AS `releaseYear`, `soundbridge`.`song`.`duration` AS `duration`, `soundbridge`.`song`.`cover` AS `cover`, `soundbridge`.`song`.`source` AS `source`, `soundbridge`.`song`.`genre` AS `genre`, `soundbridge`.`song`.`lang` AS `lang`, `soundbridge`.`song`.`idAlbum` AS `idAlbum`, `soundbridge`.`song`.`idArtist` AS `idArtist`, `soundbridge`.`song`.`idGroup` AS `idGroup` FROM `soundbridge`.`song` WHERE `soundbridge`.`song`.`idAlbum` is null ;

-- --------------------------------------------------------

--
-- Estructura para la vista `top20`
--
DROP TABLE IF EXISTS `top20`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `soundbridge`.`top20`  AS SELECT `S`.`id` AS `id`, `S`.`name` AS `name`, count(`P`.`idSong`) AS `plays` FROM (`soundbridge`.`song` `S` join `soundbridge`.`play` `P` on(`S`.`id` = `P`.`idSong`)) GROUP BY `S`.`id`, `S`.`name` ORDER BY count(`P`.`idSong`) DESC LIMIT 0, 20 ;

-- --------------------------------------------------------

--
-- Estructura para la vista `topartists`
--
DROP TABLE IF EXISTS `topartists`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `soundbridge`.`topartists`  AS SELECT `listartists`.`name` AS `name`, `listartists`.`plays` AS `plays` FROM (select `A`.`name` AS `name`,count(`P`.`idSong`) AS `plays` from ((`soundbridge`.`artist` `A` join `soundbridge`.`song` `S` on(`A`.`id` = `S`.`idArtist`)) join `soundbridge`.`play` `P` on(`P`.`idSong` = `S`.`id`)) group by `A`.`name` union all select `G`.`name` AS `name`,count(`P`.`idSong`) AS `plays` from ((`soundbridge`.`artgroup` `G` join `soundbridge`.`song` `S` on(`S`.`idGroup` = `G`.`id`)) join `soundbridge`.`play` `P` on(`P`.`idSong` = `S`.`id`)) group by `G`.`name`) AS `listArtists` ORDER BY `listartists`.`plays` DESC ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `artgroup`
--
ALTER TABLE `artgroup`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `artGroupName` (`name`);

--
-- Indices de la tabla `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idGroup_artist` (`idGroup`),
  ADD KEY `artistName` (`name`);

--
-- Indices de la tabla `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personalId` (`personalId`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `clientPID` (`personalId`),
  ADD KEY `clientUsername` (`username`);

--
-- Indices de la tabla `clientp`
--
ALTER TABLE `clientp`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `clientPSuscriptionDate` (`suscriptionDate`);

--
-- Indices de la tabla `clientpp`
--
ALTER TABLE `clientpp`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `clientPpSuscriptionDate` (`suscriptionDate`);

--
-- Indices de la tabla `contain`
--
ALTER TABLE `contain`
  ADD PRIMARY KEY (`playlistId`,`songId`),
  ADD KEY `fk_songId_contain` (`songId`);

--
-- Indices de la tabla `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personalId` (`personalId`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `SSNum` (`SSNum`),
  ADD KEY `employeePID` (`personalId`),
  ADD KEY `employeeSSNum` (`SSNum`),
  ADD KEY `employeeUsername` (`username`);

--
-- Indices de la tabla `play`
--
ALTER TABLE `play`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idClient_play` (`idClient`),
  ADD KEY `fk_idSong_play` (`idSong`);

--
-- Indices de la tabla `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idClientp_Playlist` (`idClientP`),
  ADD KEY `fk_idClientpp_Playlist` (`idClientPp`),
  ADD KEY `playListCreationDate` (`creationDate`);

--
-- Indices de la tabla `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`idClientPP`,`idAlbum`);

--
-- Indices de la tabla `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk_idAlbum_song` (`idAlbum`),
  ADD KEY `fk_idArtist_song` (`idArtist`),
  ADD KEY `fk_idGroup_song` (`idGroup`),
  ADD KEY `songName` (`name`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `album`
--
ALTER TABLE `album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `artgroup`
--
ALTER TABLE `artgroup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `artist`
--
ALTER TABLE `artist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `play`
--
ALTER TABLE `play`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT de la tabla `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `song`
--
ALTER TABLE `song`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `artist`
--
ALTER TABLE `artist`
  ADD CONSTRAINT `fk_idGroup_artist` FOREIGN KEY (`idGroup`) REFERENCES `artGroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `clientp`
--
ALTER TABLE `clientp`
  ADD CONSTRAINT `fk_idClient_clientp` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `clientpp`
--
ALTER TABLE `clientpp`
  ADD CONSTRAINT `fk_idClient_clientpp` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `contain`
--
ALTER TABLE `contain`
  ADD CONSTRAINT `fk_playlistId_contain` FOREIGN KEY (`playlistId`) REFERENCES `Playlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_songId_contain` FOREIGN KEY (`songId`) REFERENCES `Song` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `play`
--
ALTER TABLE `play`
  ADD CONSTRAINT `fk_idClient_play` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idSong_play` FOREIGN KEY (`idSong`) REFERENCES `song` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `fk_idClientp_Playlist` FOREIGN KEY (`idClientP`) REFERENCES `client` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_idClientpp_Playlist` FOREIGN KEY (`idClientPp`) REFERENCES `client` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_idClientPP_review` FOREIGN KEY (`idClientPP`) REFERENCES `clientpp` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `song`
--
ALTER TABLE `song`
  ADD CONSTRAINT `fk_idAlbum_song` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idArtist_song` FOREIGN KEY (`idArtist`) REFERENCES `artist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idGroup_song` FOREIGN KEY (`idGroup`) REFERENCES `artGroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
