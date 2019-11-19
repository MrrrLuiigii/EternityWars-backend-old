-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Gegenereerd op: 19 nov 2019 om 13:24
-- Serverversie: 5.7.24
-- PHP-versie: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eternitywars`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `GetUserById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetUserById` (IN `pId` INT)  BEGIN
	
    select id, google_id, email, username, status, gold, pack_amount
    from user
    where id = pId;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `card`
--

DROP TABLE IF EXISTS `card`;
CREATE TABLE IF NOT EXISTS `card` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `health` int(50) NOT NULL,
  `attack` int(50) NOT NULL,
  `blue_mana` int(50) NOT NULL,
  `death_essence` int(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `card`
--

INSERT INTO `card` (`id`, `name`, `health`, `attack`, `blue_mana`, `death_essence`) VALUES
(1, 'Dreadlord von Reemer', 10, 5, 5, 1),
(2, 'yeet', 1, 1, 1, 0),
(3, 'yaat', 2, 2, 2, 0),
(4, 'yoot', 3, 3, 3, 0);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `collection`
--

DROP TABLE IF EXISTS `collection`;
CREATE TABLE IF NOT EXISTS `collection` (
  `user_id` int(11) NOT NULL,
  `card_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `deck`
--

DROP TABLE IF EXISTS `deck`;
CREATE TABLE IF NOT EXISTS `deck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `card_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `friend`
--

DROP TABLE IF EXISTS `friend`;
CREATE TABLE IF NOT EXISTS `friend` (
  `user_one_id` int(50) NOT NULL,
  `user_two_id` int(50) NOT NULL,
  `status` enum('Pending','Accepted','Blocked') NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `lobby`
--

DROP TABLE IF EXISTS `lobby`;
CREATE TABLE IF NOT EXISTS `lobby` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `user_id` int(50) NOT NULL,
  `user_ready` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `pack`
--

DROP TABLE IF EXISTS `pack`;
CREATE TABLE IF NOT EXISTS `pack` (
  `user_id` int(50) NOT NULL,
  `amount` int(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `quest`
--

DROP TABLE IF EXISTS `quest`;
CREATE TABLE IF NOT EXISTS `quest` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `objective` enum('PlayCards','DealDamage','Etc') NOT NULL,
  `completion` int(50) NOT NULL,
  `award` int(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `google_id` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `account_status` enum('InGame','Online','Offline','InLobby') NOT NULL,
  `gold` int(50) NOT NULL,
  `pack_amount` int(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `user`
--

INSERT INTO `user` (`id`, `google_id`, `email`, `username`, `account_status`, `gold`, `pack_amount`) VALUES
(1, 'agsthyiktluykutjyq5u7364i876o9', 'yoink@doink.nl', 'yoink', 'Online', 10000, 10000),
(2, 't45yu5tpo4qiw0vyn9rjfasdgdhr', 'doink@yoink.nl', 'doink', 'InLobby', 1, 1),
(3, '24y6rtol9uiykjartfkliukyjatrhzgn', 'cool@boy.nl', 'cool', 'Offline', 2, 2),
(4, 'asghetj76u75yjtrwyjthrg', 'boy@cool.nl', 'boy', 'InGame', 78, 4567);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user_quest`
--

DROP TABLE IF EXISTS `user_quest`;
CREATE TABLE IF NOT EXISTS `user_quest` (
  `user_id` int(11) NOT NULL,
  `quest_id` int(11) NOT NULL,
  `completion` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
