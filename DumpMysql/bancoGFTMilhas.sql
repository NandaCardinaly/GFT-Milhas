-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.30 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para gft_milhas
CREATE DATABASE IF NOT EXISTS `gft_milhas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gft_milhas`;

-- Copiando estrutura para tabela gft_milhas.atividades
CREATE TABLE IF NOT EXISTS `atividades` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_entrega` datetime(6) DEFAULT NULL,
  `data_inicio` datetime(6) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `id_evento` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2e9uxo353wqi5hu58rxf01lcm` (`id_evento`),
  CONSTRAINT `FK2e9uxo353wqi5hu58rxf01lcm` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela gft_milhas.atividades: ~3 rows (aproximadamente)
INSERT INTO `atividades` (`id`, `data_entrega`, `data_inicio`, `nome`, `id_evento`) VALUES
	(5, '2022-11-08 00:00:00.000000', '2022-11-08 00:00:00.000000', 'Cadastro das atividades do evento', 4),
	(6, '2022-11-07 00:00:00.000000', '2022-11-07 00:00:00.000000', 'Cadastro de grupos de pessoas que participará dos eventos', 4),
	(7, '2022-11-09 00:00:00.000000', '2022-11-09 00:00:00.000000', 'Página de marcação de cumprimento de cada atividade por membro de cada grupo', 4);

-- Copiando estrutura para tabela gft_milhas.atividades_grupos
CREATE TABLE IF NOT EXISTS `atividades_grupos` (
  `atividade_id` bigint NOT NULL,
  `grupos_id` bigint NOT NULL,
  KEY `FK6du1biqvhegkdmpxmjbd8khak` (`grupos_id`),
  KEY `FKbcv65x5bok03rllfa2u36engb` (`atividade_id`),
  CONSTRAINT `FK6du1biqvhegkdmpxmjbd8khak` FOREIGN KEY (`grupos_id`) REFERENCES `grupos` (`id`),
  CONSTRAINT `FKbcv65x5bok03rllfa2u36engb` FOREIGN KEY (`atividade_id`) REFERENCES `atividades` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela gft_milhas.atividades_grupos: ~30 rows (aproximadamente)
INSERT INTO `atividades_grupos` (`atividade_id`, `grupos_id`) VALUES
	(5, 7),
	(5, 8),
	(5, 9),
	(5, 10),
	(5, 11),
	(5, 12),
	(5, 13),
	(5, 14),
	(5, 15),
	(5, 16),
	(6, 7),
	(6, 8),
	(6, 9),
	(6, 10),
	(6, 11),
	(6, 12),
	(6, 13),
	(6, 14),
	(6, 15),
	(6, 16),
	(7, 7),
	(7, 8),
	(7, 9),
	(7, 10),
	(7, 11),
	(7, 12),
	(7, 13),
	(7, 14),
	(7, 15),
	(7, 16);

-- Copiando estrutura para tabela gft_milhas.eventos
CREATE TABLE IF NOT EXISTS `eventos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_final` datetime(6) DEFAULT NULL,
  `data_inicio` datetime(6) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela gft_milhas.eventos: ~3 rows (aproximadamente)
INSERT INTO `eventos` (`id`, `data_final`, `data_inicio`, `nome`) VALUES
	(4, '2022-11-10 00:00:00.000000', '2022-11-07 00:00:00.000000', 'Desafio MVC '),
	(5, '2022-11-18 00:00:00.000000', '2022-11-14 00:00:00.000000', 'Desafio API'),
	(6, '2022-12-06 00:00:00.000000', '2022-12-05 00:00:00.000000', 'Desafio Angular');

-- Copiando estrutura para tabela gft_milhas.grupos
CREATE TABLE IF NOT EXISTS `grupos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `qtd_pessoas` int NOT NULL,
  `id_evento` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK45lhtuayx34na68mpqivpvwck` (`id_evento`),
  CONSTRAINT `FK45lhtuayx34na68mpqivpvwck` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela gft_milhas.grupos: ~10 rows (aproximadamente)
INSERT INTO `grupos` (`id`, `nome`, `qtd_pessoas`, `id_evento`) VALUES
	(7, 'Grupo 1', 0, 4),
	(8, 'Grupo 2', 0, 4),
	(9, 'Grupo 3', 0, 4),
	(10, 'Grupo 4', 0, 4),
	(11, 'Grupo 5', 0, 4),
	(12, 'Grupo 6', 0, 4),
	(13, 'Grupo 7', 0, 4),
	(14, 'Grupo 8', 0, 4),
	(15, 'Grupo 9', 0, 4),
	(16, 'Grupo 10', 0, 4);

-- Copiando estrutura para tabela gft_milhas.participantes
CREATE TABLE IF NOT EXISTS `participantes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nivel` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `quatro_letras` varchar(4) DEFAULT NULL,
  `id_grupo` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24d7rhdq5yyvq1tq7jxm629p9` (`id_grupo`),
  CONSTRAINT `FK24d7rhdq5yyvq1tq7jxm629p9` FOREIGN KEY (`id_grupo`) REFERENCES `grupos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela gft_milhas.participantes: ~18 rows (aproximadamente)
INSERT INTO `participantes` (`id`, `email`, `nivel`, `nome`, `quatro_letras`, `id_grupo`) VALUES
	(5, 'alice@gmail.com', 'L0', 'Alice Alves', 'alic', 7),
	(6, 'ana@gmail.com', 'L0', 'Ana Andrade', 'anaa', 7),
	(7, 'larah@gmail.com', 'L0', 'Larah Araujo', 'lara', 7),
	(8, 'edyane@gmail.com', 'L0', 'Edyane Araujo', 'edya', 8),
	(9, 'bruna@gmail.com', 'L0', 'Bruna Arquino', 'brun', 8),
	(10, 'camila@gmail.com', 'L0', 'Camila Campos', 'cami', 8),
	(11, 'ingrid@outlook.com', 'L0', 'Ingrid Santos', 'ingr', 9),
	(12, 'caroline@gmail.com', 'L0', 'Caroline', 'cami', 9),
	(13, 'gabi@gmail.com', 'L0', 'Gabriela Domingues', 'gabi', 9),
	(14, 'karen@gmail.com', 'L0', 'Karen Franco', 'kneo', 10),
	(15, 'lilian@gmail.com', 'L0', 'Lilian Fabiano', 'lili', 10),
	(16, 'angela@gmail.com', 'L0', 'Angela Giampaoli', 'ange', 10),
	(17, 'julie@gmail.com', 'L0', 'Julie Santos', 'juli', 11),
	(18, 'isabela@gmail.com', 'L0', 'Isabela Suto', 'isab', 12),
	(19, 'louise@gmail.com', 'L0', 'Louise Siqueira', 'loui', 14),
	(20, 'fernanda@gmail.com', 'L0', 'Fernanda Silva', 'fern', 15),
	(21, 'ariana@gmail.com', 'L0', 'Ariana Russo', 'aria', 16),
	(22, 'jessica@gmail.com', 'L0', 'Jessica Nagata', 'jess', 13);

-- Copiando estrutura para tabela gft_milhas.participantesporevento
CREATE TABLE IF NOT EXISTS `participantesporevento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `atraso` int NOT NULL DEFAULT '0',
  `pontuacao_geral` int NOT NULL,
  `presenca` int NOT NULL,
  `qtde_atividades_feitas` int NOT NULL,
  `id_evento` bigint DEFAULT NULL,
  `id_participantes` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8qm4ha9edtn7q8hgtkx29g7oj` (`id_evento`),
  KEY `FKh63wiyv3b4ptfj52jmrhabem1` (`id_participantes`),
  CONSTRAINT `FK8qm4ha9edtn7q8hgtkx29g7oj` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`),
  CONSTRAINT `FKh63wiyv3b4ptfj52jmrhabem1` FOREIGN KEY (`id_participantes`) REFERENCES `participantes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela gft_milhas.participantesporevento: ~18 rows (aproximadamente)
INSERT INTO `participantesporevento` (`id`, `atraso`, `pontuacao_geral`, `presenca`, `qtde_atividades_feitas`, `id_evento`, `id_participantes`) VALUES
	(22, 1, 0, 3, 3, 4, 5),
	(23, 1, 0, 3, 3, 4, 6),
	(24, 0, 0, 3, 3, 4, 7),
	(25, 0, 0, 3, 3, 4, 8),
	(26, 1, 0, 3, 3, 4, 9),
	(27, 0, 0, 3, 3, 4, 10),
	(28, 1, 0, 3, 3, 4, 11),
	(29, 0, 0, 3, 3, 4, 12),
	(30, 0, 0, 3, 3, 4, 13),
	(31, 0, 0, 3, 3, 4, 14),
	(32, 0, 0, 3, 3, 4, 15),
	(33, 1, 0, 3, 3, 4, 16),
	(34, 0, 0, 3, 3, 4, 17),
	(35, 0, 0, 3, 3, 4, 18),
	(36, 0, 0, 3, 3, 4, 22),
	(37, 0, 0, 3, 3, 4, 19),
	(38, 1, 0, 3, 3, 4, 20),
	(39, 0, 0, 3, 3, 4, 21);

-- Copiando estrutura para tabela gft_milhas.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `quatro_letras` varchar(4) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Copiando dados para a tabela gft_milhas.usuario: ~1 rows (aproximadamente)
INSERT INTO `usuario` (`id`, `email`, `nome`, `quatro_letras`, `senha`) VALUES
	(1, 'bira@gmail.com', 'Ubiratan Cardoso', 'bira', '1234');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
