-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.1.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para proyectdeu
CREATE DATABASE IF NOT EXISTS `proyectdeu` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `proyectdeu`;

-- Volcando estructura para tabla proyectdeu.candidates
CREATE TABLE IF NOT EXISTS `candidates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_number` varchar(50) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '0',
  `politic_party` varchar(50) NOT NULL DEFAULT '0',
  `image` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_identificacion` (`id_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla proyectdeu.candidates: ~9 rows (aproximadamente)
INSERT INTO `candidates` (`id`, `id_number`, `name`, `politic_party`, `image`) VALUES
	(1, '205496321', 'Carlos Alvarado', 'PAC', '/View/Images/PAC.png'),
	(2, '402640056', 'Rodrigo Chávez', 'PSD', '/View/Images/PSD.png'),
	(3, '302670556', 'José María Villalta', 'PFA', '/View/Images/PFA.png'),
	(4, '208326391', 'José María Figueres', 'PLN', '/View/Images/PLN.png'),
	(5, '702136124', 'Lineth Saborío', 'PUSC', '/View/Images/PUSC.png'),
	(6, '203596487', 'Oscar López', 'PASE', '/View/Images/PASE.png'),
	(7, '405632147', 'Juan Diego Castro', 'PIN', '/View/Images/PIN.png'),
	(8, '106874321', 'Rodolfo Hernández', 'PRSC', '/View/Images/PRSC.png'),
	(9, '106425148', 'Eli Feinzaig', 'PLP', '/View/Images/PLP.png');

-- Volcando estructura para tabla proyectdeu.periods
CREATE TABLE IF NOT EXISTS `periods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `finish_date` date NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla proyectdeu.periods: ~1 rows (aproximadamente)
INSERT INTO `periods` (`id`, `start_date`, `finish_date`, `status`) VALUES
	(1, '2026-01-01', '2026-01-02', 'Activo');

-- Volcando estructura para tabla proyectdeu.results
CREATE TABLE IF NOT EXISTS `results` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voter_id` int(11) NOT NULL DEFAULT 0,
  `candidate_id` int(11) NOT NULL DEFAULT 0,
  `period_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK__votantes` (`voter_id`) USING BTREE,
  KEY `FK__candidatos` (`candidate_id`) USING BTREE,
  KEY `FK_results_periods` (`period_id`),
  CONSTRAINT `FK__candidatos` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__votantes` FOREIGN KEY (`voter_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_results_periods` FOREIGN KEY (`period_id`) REFERENCES `periods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla proyectdeu.results: ~9 rows (aproximadamente)
INSERT INTO `results` (`id`, `voter_id`, `candidate_id`, `period_id`) VALUES
	(1, 2, 2, 1),
	(2, 3, 1, 1),
	(3, 4, 8, 1),
	(4, 5, 6, 1),
	(5, 6, 4, 1),
	(6, 7, 4, 1),
	(7, 8, 5, 1),
	(8, 9, 4, 1),
	(9, 10, 8, 1);

-- Volcando estructura para tabla proyectdeu.rols
CREATE TABLE IF NOT EXISTS `rols` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla proyectdeu.rols: ~2 rows (aproximadamente)
INSERT INTO `rols` (`id`, `name`) VALUES
	(1, 'ADMIN'),
	(2, 'VOTER');

-- Volcando estructura para tabla proyectdeu.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_number` varchar(50) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '0',
  `email` varchar(50) NOT NULL DEFAULT '0',
  `age` int(11) NOT NULL DEFAULT 0,
  `telephone` int(11) NOT NULL DEFAULT 0,
  `code` int(11) NOT NULL DEFAULT 0,
  `rol_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_identificacion` (`id_number`) USING BTREE,
  KEY `FK_voters_rols` (`rol_id`),
  CONSTRAINT `FK_voters_rols` FOREIGN KEY (`rol_id`) REFERENCES `rols` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla proyectdeu.users: ~20 rows (aproximadamente)
INSERT INTO `users` (`id`, `id_number`, `name`, `email`, `age`, `telephone`, `code`, `rol_id`) VALUES
	(1, 'adminBravo0405', 'Carlos', 'bravo9386@gmail.com', 19, 70463777, 405, 1),
	(2, '208490055', 'Eithel', 'eithel123@gmail.com', 20, 85323346, 1234, 2),
	(3, '307210035', 'Juan', 'juan568@gmail.com', 23, 86321475, 8091, 2),
	(4, '403520097', 'Pedro', 'pedro635@gmail.com', 19, 91136479, 9229, 2),
	(5, '702590087', 'Luis', 'luis147@gmail.com', 26, 83264712, 3570, 2),
	(6, '306980012', 'Marcos', 'marcos853@gmail.com', 31, 36987425, 7734, 2),
	(7, '503260056', 'Jason', 'jasons273@gmail.com', 34, 29634782, 3410, 2),
	(8, '609860024', 'Erick', 'erick256@gmail.com', 46, 77821694, 5458, 2),
	(9, '402150013', 'Maikol', 'maikol125@gmail.com', 18, 66314528, 4824, 2),
	(10, '501390041', 'Ericka', 'ericka916@gmail.com', 29, 18956478, 9272, 2),
	(11, '702640024', 'Olga', 'olga818@gmail.com', 44, 26319854, 3355, 2),
	(12, '304170024', 'Jafet', 'jafet313@gmail.com', 25, 998412548, 7349, 2),
	(13, '502690017', 'Deisy', 'deisy358@gmail.com', 19, 95461235, 3647, 2),
	(14, '203690047', 'David', 'david496@gmail.com', 22, 96535487, 3062, 2),
	(15, '503150039', 'Daniel', 'daniel954@gmail.com', 34, 20471426, 1829, 2),
	(16, '508410017', 'Fabiola', 'fabiola362@gmail.com', 33, 85649214, 3191, 2),
	(17, '536460078', 'Maricel', 'maricel124@gmail.com', 53, 36212563, 4776, 2),
	(18, '203650049', 'Luisa', 'luisa396@gmail.com', 62, 84935168, 5323, 2),
	(19, '406950079', 'Berny', 'berny365@gmail.com', 40, 20325498, 1487, 2),
	(20, '609130072', 'Gavi', 'gavi563@gmail.com', 39, 20149563, 7129, 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
