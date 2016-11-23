-- phpMyAdmin SQL Dump
-- version 4.0.10.14
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Oct 27, 2016 at 09:54 AM
-- Server version: 5.6.31
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `davdan15_pj15`
--

-- --------------------------------------------------------

--
-- Table structure for table `Administrator`
--

CREATE TABLE IF NOT EXISTS `Administrator` (
  `ID` int(32) NOT NULL AUTO_INCREMENT,
  `Foedselsnummer` int(32) NOT NULL,
  `Rettighetsnivaa` enum('1','2','3') DEFAULT NULL,
  `Fornavn` varchar(100) NOT NULL,
  `Etternavn` varchar(100) NOT NULL,
  `Adresse` varchar(100) NOT NULL,
  `Postnummer` int(4) NOT NULL,
  `Mail` varchar(100) NOT NULL,
  `Telefon` int(8) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Foedselsnummer` (`Foedselsnummer`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Bankkonto`
--

CREATE TABLE IF NOT EXISTS `Bankkonto` (
  `Kontonummer` bigint(128) NOT NULL,
  `Kundenummer` int(32) NOT NULL,
  `Kontotype` enum('Brukskonto','Sparekonto') NOT NULL,
  `Kroner` bigint(128) DEFAULT '0',
  `Oere` int(2) DEFAULT '0',
  `Rente` double NOT NULL,
  PRIMARY KEY (`Kontonummer`),
  UNIQUE KEY `Kontonummer` (`Kontonummer`),
  KEY `kundenummer_fk` (`Kundenummer`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Bankkonto`
--

INSERT INTO `Bankkonto` (`Kontonummer`, `Kundenummer`, `Kontotype`, `Kroner`, `Oere`, `Rente`) VALUES
(80766645136, 0, 'Brukskonto', 630962, 0, 2.5),
(42441989136, 1, 'Sparekonto', 414259, 0, 2.5),
(72438691075, 2, 'Brukskonto', 934582, 0, 2.5),
(95207460912, 3, 'Sparekonto', 809716, 0, 2.5),
(94952374346, 4, 'Brukskonto', 719550, 0, 2.5),
(41468595029, 5, 'Sparekonto', 392604, 0, 2.5),
(24200264727, 6, 'Brukskonto', 769431, 0, 2.5),
(74430839815, 7, 'Sparekonto', 805849, 0, 2.5),
(87948826818, 8, 'Brukskonto', 621599, 0, 2.5),
(87503519119, 9, 'Sparekonto', 160446, 0, 2.5),
(63345299934, 10, 'Brukskonto', 686551, 0, 2.5),
(29349937376, 11, 'Sparekonto', 824539, 0, 2.5),
(99394877204, 12, 'Brukskonto', 260163, 0, 2.5),
(33169833989, 13, 'Sparekonto', 303562, 0, 2.5),
(78219148595, 14, 'Brukskonto', 129435, 0, 2.5),
(33486624820, 15, 'Sparekonto', 692357, 0, 2.5),
(86970636424, 16, 'Brukskonto', 18408, 0, 2.5),
(43806960429, 17, 'Sparekonto', 713385, 0, 2.5),
(85854025024, 18, 'Brukskonto', 387141, 0, 2.5),
(63815096079, 19, 'Sparekonto', 136464, 0, 2.5),
(59241792142, 20, 'Brukskonto', 606463, 0, 2.5),
(45479276353, 21, 'Sparekonto', 741278, 0, 2.5),
(26338864265, 22, 'Brukskonto', 801402, 0, 2.5),
(50068159594, 23, 'Sparekonto', 649538, 0, 2.5),
(68179267384, 24, 'Brukskonto', 177442, 0, 2.5),
(95284270671, 25, 'Sparekonto', 664199, 0, 2.5),
(53925295345, 26, 'Brukskonto', 792716, 0, 2.5),
(35254029154, 27, 'Sparekonto', 580810, 0, 2.5),
(87925181896, 28, 'Brukskonto', 346970, 0, 2.5),
(85613335483, 29, 'Sparekonto', 923012, 0, 2.5),
(98524421370, 30, 'Brukskonto', 392844, 0, 2.5),
(42461581061, 31, 'Sparekonto', 423453, 0, 2.5),
(70313549105, 32, 'Brukskonto', 163795, 0, 2.5),
(84132826372, 33, 'Sparekonto', 139119, 0, 2.5),
(41057077760, 34, 'Brukskonto', 660357, 0, 2.5),
(49124829373, 35, 'Sparekonto', 898825, 0, 2.5),
(96867719324, 36, 'Brukskonto', 146223, 0, 2.5),
(56658481178, 37, 'Sparekonto', 179093, 0, 2.5),
(38876872183, 38, 'Brukskonto', 993830, 0, 2.5),
(56935677372, 39, 'Sparekonto', 193272, 0, 2.5),
(14355962364, 40, 'Brukskonto', 798770, 0, 2.5),
(64268077939, 41, 'Sparekonto', 714389, 0, 2.5),
(27785728320, 42, 'Brukskonto', 483746, 0, 2.5),
(24673056964, 43, 'Sparekonto', 487485, 0, 2.5),
(80419075025, 44, 'Brukskonto', 477138, 0, 2.5),
(38518376835, 45, 'Sparekonto', 629635, 0, 2.5),
(28847833914, 46, 'Brukskonto', 484106, 0, 2.5),
(82782839139, 47, 'Sparekonto', 859580, 0, 2.5),
(80114349130, 48, 'Brukskonto', 83773, 0, 2.5),
(53973034884, 49, 'Sparekonto', 389099, 0, 2.5),
(27650555229, 50, 'Brukskonto', 171970, 0, 2.5),
(15705772224, 51, 'Sparekonto', 910214, 0, 2.5),
(25837842637, 52, 'Brukskonto', 158752, 0, 2.5),
(43447476311, 53, 'Sparekonto', 240846, 0, 2.5),
(78575012587, 54, 'Brukskonto', 440749, 0, 2.5),
(46107967797, 55, 'Sparekonto', 859981, 0, 2.5),
(95075862616, 56, 'Brukskonto', 892128, 0, 2.5),
(45213486822, 57, 'Sparekonto', 25897, 0, 2.5),
(71058044318, 58, 'Brukskonto', 948186, 0, 2.5),
(71490055017, 59, 'Sparekonto', 53102, 0, 2.5),
(54285987106, 60, 'Brukskonto', 366677, 0, 2.5),
(17303378077, 61, 'Sparekonto', 824034, 0, 2.5),
(46713826289, 62, 'Brukskonto', 990692, 0, 2.5),
(83005531180, 63, 'Sparekonto', 510730, 0, 2.5),
(27976463344, 64, 'Brukskonto', 815378, 0, 2.5),
(77198098362, 65, 'Sparekonto', 999184, 0, 2.5),
(64457547525, 66, 'Brukskonto', 778691, 0, 2.5),
(29653457733, 67, 'Sparekonto', 420985, 0, 2.5),
(99328753398, 68, 'Brukskonto', 770073, 0, 2.5),
(93613091093, 69, 'Sparekonto', 411201, 0, 2.5),
(41449156296, 70, 'Brukskonto', 822036, 0, 2.5),
(51347259711, 71, 'Sparekonto', 57582, 0, 2.5),
(75999843595, 72, 'Brukskonto', 158015, 0, 2.5),
(99946750948, 73, 'Sparekonto', 246537, 0, 2.5),
(63940821115, 74, 'Brukskonto', 339357, 0, 2.5),
(92845111762, 75, 'Sparekonto', 323302, 0, 2.5),
(15041544488, 76, 'Brukskonto', 785420, 0, 2.5),
(60080027624, 77, 'Sparekonto', 61908, 0, 2.5),
(48699734445, 78, 'Brukskonto', 16553, 0, 2.5),
(51050242690, 79, 'Sparekonto', 937235, 0, 2.5),
(26902564840, 80, 'Brukskonto', 652897, 0, 2.5),
(60405960748, 81, 'Sparekonto', 934200, 0, 2.5),
(42786552925, 82, 'Brukskonto', 201916, 0, 2.5),
(52086258908, 83, 'Sparekonto', 233939, 0, 2.5),
(36410257139, 84, 'Brukskonto', 25822, 0, 2.5),
(40426329716, 85, 'Sparekonto', 568703, 0, 2.5),
(36289969901, 86, 'Brukskonto', 548285, 0, 2.5),
(16198544996, 87, 'Sparekonto', 108259, 0, 2.5),
(86379753387, 88, 'Brukskonto', 457544, 0, 2.5),
(30244391230, 89, 'Sparekonto', 717564, 0, 2.5),
(71426075026, 90, 'Brukskonto', 7249, 0, 2.5),
(72874042928, 91, 'Sparekonto', 373056, 0, 2.5),
(27571726864, 92, 'Brukskonto', 52075, 0, 2.5),
(51698755285, 93, 'Sparekonto', 653900, 0, 2.5),
(97503995718, 94, 'Brukskonto', 972929, 0, 2.5),
(82792959537, 95, 'Sparekonto', 930100, 0, 2.5),
(31666346673, 96, 'Brukskonto', 610611, 0, 2.5),
(77310921587, 97, 'Sparekonto', 802049, 0, 2.5),
(89325224434, 98, 'Brukskonto', 786688, 0, 2.5),
(63454037373, 99, 'Sparekonto', 522604, 0, 2.5),
(77926693162, 100, 'Brukskonto', 991096, 0, 2.5),
(89169070246, 101, 'Sparekonto', 956611, 0, 2.5),
(64651574541, 102, 'Brukskonto', 787647, 0, 2.5),
(69346695801, 103, 'Sparekonto', 380930, 0, 2.5),
(22133231236, 104, 'Brukskonto', 697690, 0, 2.5),
(75504772849, 105, 'Sparekonto', 599795, 0, 2.5),
(65164910560, 106, 'Brukskonto', 184626, 0, 2.5),
(68520072107, 107, 'Sparekonto', 805003, 0, 2.5),
(54052512973, 108, 'Brukskonto', 322161, 0, 2.5),
(18281889924, 109, 'Sparekonto', 301735, 0, 2.5),
(86765241925, 110, 'Brukskonto', 222159, 0, 2.5),
(70617439243, 111, 'Sparekonto', 256458, 0, 2.5),
(69739691744, 112, 'Brukskonto', 523522, 0, 2.5),
(56191846479, 113, 'Sparekonto', 238050, 0, 2.5),
(41885534905, 114, 'Brukskonto', 683889, 0, 2.5),
(33493615879, 115, 'Sparekonto', 148683, 0, 2.5),
(64756322234, 116, 'Brukskonto', 975829, 0, 2.5),
(77095717613, 117, 'Sparekonto', 298167, 0, 2.5),
(92651671744, 118, 'Brukskonto', 323036, 0, 2.5),
(14633561846, 119, 'Sparekonto', 290954, 0, 2.5),
(10661919967, 120, 'Brukskonto', 939284, 0, 2.5),
(25212779819, 121, 'Sparekonto', 764729, 0, 2.5),
(13657117277, 122, 'Brukskonto', 396320, 0, 2.5),
(91694994240, 123, 'Sparekonto', 815963, 0, 2.5),
(70511056882, 124, 'Brukskonto', 363174, 0, 2.5),
(30017346026, 125, 'Sparekonto', 103623, 0, 2.5),
(88021525922, 126, 'Brukskonto', 13188, 0, 2.5),
(27866025316, 127, 'Sparekonto', 522737, 0, 2.5),
(13415951290, 128, 'Brukskonto', 204334, 0, 2.5),
(57587225223, 129, 'Sparekonto', 671085, 0, 2.5),
(40949431983, 130, 'Brukskonto', 759426, 0, 2.5),
(39500351123, 131, 'Sparekonto', 681798, 0, 2.5),
(91562752049, 132, 'Brukskonto', 713684, 0, 2.5),
(45720977365, 133, 'Sparekonto', 918967, 0, 2.5),
(66370573402, 134, 'Brukskonto', 482117, 0, 2.5),
(29526235505, 135, 'Sparekonto', 92542, 0, 2.5),
(33064126640, 136, 'Brukskonto', 652009, 0, 2.5),
(11645050704, 137, 'Sparekonto', 406174, 0, 2.5),
(92785535512, 138, 'Brukskonto', 365870, 0, 2.5),
(18866538201, 139, 'Sparekonto', 634320, 0, 2.5),
(65718044351, 140, 'Brukskonto', 913396, 0, 2.5),
(57300755726, 141, 'Sparekonto', 73688, 0, 2.5),
(87675526471, 142, 'Brukskonto', 87646, 0, 2.5),
(95102617851, 143, 'Sparekonto', 681667, 0, 2.5),
(49675517142, 144, 'Brukskonto', 431298, 0, 2.5),
(73275893406, 145, 'Sparekonto', 727871, 0, 2.5),
(47860059901, 146, 'Brukskonto', 980691, 0, 2.5),
(95227486206, 147, 'Sparekonto', 14623, 0, 2.5),
(77868463585, 148, 'Brukskonto', 670634, 0, 2.5),
(42296042541, 149, 'Sparekonto', 258275, 0, 2.5),
(18644213211, 150, 'Brukskonto', 646109, 0, 2.5),
(88769386364, 151, 'Sparekonto', 600063, 0, 2.5),
(23554179045, 152, 'Brukskonto', 949603, 0, 2.5),
(42050190254, 153, 'Sparekonto', 407528, 0, 2.5),
(70299213302, 154, 'Brukskonto', 893831, 0, 2.5),
(76565184080, 155, 'Sparekonto', 758824, 0, 2.5),
(73201786704, 156, 'Brukskonto', 392718, 0, 2.5),
(95113068856, 157, 'Sparekonto', 914842, 0, 2.5),
(62698486427, 158, 'Brukskonto', 592496, 0, 2.5),
(22199248552, 159, 'Sparekonto', 691092, 0, 2.5),
(61710184592, 160, 'Brukskonto', 741330, 0, 2.5),
(90972042592, 161, 'Sparekonto', 206306, 0, 2.5),
(43538566824, 162, 'Brukskonto', 839497, 0, 2.5),
(69907351231, 163, 'Sparekonto', 804163, 0, 2.5),
(58822026518, 164, 'Brukskonto', 397703, 0, 2.5),
(76524638658, 165, 'Sparekonto', 679423, 0, 2.5),
(75059987837, 166, 'Brukskonto', 736510, 0, 2.5),
(25894835699, 167, 'Sparekonto', 831761, 0, 2.5),
(68941031486, 168, 'Brukskonto', 853271, 0, 2.5),
(27434915833, 169, 'Sparekonto', 836340, 0, 2.5),
(88570361600, 170, 'Brukskonto', 460739, 0, 2.5),
(37093986851, 171, 'Sparekonto', 933954, 0, 2.5),
(54096029529, 172, 'Brukskonto', 809892, 0, 2.5),
(59507492411, 173, 'Sparekonto', 328383, 0, 2.5),
(88689423120, 174, 'Brukskonto', 819999, 0, 2.5),
(96380816500, 175, 'Sparekonto', 188493, 0, 2.5),
(95341365350, 176, 'Brukskonto', 395817, 0, 2.5),
(82943973423, 177, 'Sparekonto', 695671, 0, 2.5),
(35188286247, 178, 'Brukskonto', 895851, 0, 2.5),
(34394559290, 179, 'Sparekonto', 361627, 0, 2.5),
(45301115833, 180, 'Brukskonto', 383184, 0, 2.5),
(64194170288, 181, 'Sparekonto', 630157, 0, 2.5),
(16111225921, 182, 'Brukskonto', 661545, 0, 2.5),
(37448146335, 183, 'Sparekonto', 215454, 0, 2.5),
(32560351611, 184, 'Brukskonto', 757647, 0, 2.5),
(55966824708, 185, 'Sparekonto', 148925, 0, 2.5),
(42575524995, 186, 'Brukskonto', 695870, 0, 2.5),
(89950690920, 187, 'Sparekonto', 133296, 0, 2.5),
(30615957512, 188, 'Brukskonto', 630717, 0, 2.5),
(98494822688, 189, 'Sparekonto', 902330, 0, 2.5),
(64415933416, 190, 'Brukskonto', 292283, 0, 2.5),
(73462981314, 191, 'Sparekonto', 964577, 0, 2.5),
(19874916627, 192, 'Brukskonto', 338701, 0, 2.5),
(90939346458, 193, 'Sparekonto', 838981, 0, 2.5),
(11841372660, 194, 'Brukskonto', 832163, 0, 2.5),
(92392347527, 195, 'Sparekonto', 375861, 0, 2.5),
(34280737357, 196, 'Brukskonto', 456786, 0, 2.5),
(81897382507, 197, 'Sparekonto', 424891, 0, 2.5),
(49157825945, 198, 'Brukskonto', 110188, 0, 2.5),
(33986073767, 199, 'Sparekonto', 342240, 0, 2.5);

-- --------------------------------------------------------

--
-- Table structure for table `Bruker`
--

CREATE TABLE IF NOT EXISTS `Bruker` (
  `Kundenummer` int(32) NOT NULL AUTO_INCREMENT,
  `Foedselsnummer` bigint(20) NOT NULL,
  `Fornavn` varchar(100) NOT NULL,
  `Etternavn` varchar(100) NOT NULL,
  `Adresse` varchar(100) NOT NULL,
  `Postnummer` int(4) NOT NULL,
  `Mail` varchar(100) NOT NULL,
  `Telefon` int(8) NOT NULL,
  `Score` int(32) DEFAULT '0',
  PRIMARY KEY (`Kundenummer`),
  UNIQUE KEY `Foedselsnummer` (`Foedselsnummer`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=203 ;

--
-- Dumping data for table `Bruker`
--

INSERT INTO `Bruker` (`Kundenummer`, `Foedselsnummer`, `Fornavn`, `Etternavn`, `Adresse`, `Postnummer`, `Mail`, `Telefon`, `Score`) VALUES
(0, 8099242141, 'Daniel', 'Davidsen', 'Odvar Solbergsvei 198', 973, 'Danieldavidsen@gmail.com', 98024666, 444444),
(1, 9876543213, 'Henrik', 'Bragalund', 'Freserveien 6', 195, 'henrikbakkelund@gmail.com', 90559698, 444445),
(3, 95032063019, 'Sven', 'Nilsson', 'Norgegata 43', 7329, 'svennilsson@gmail.com', 19901637, NULL),
(4, 96062920881, 'Sven', 'Johansson', 'Norgegata 352', 8261, 'bibbijohansson@gmail.com', 15950537, NULL),
(5, 95111896027, 'Sven', 'Johansson', 'Norgegata 297', 1532, 'svenjohansson@gmail.com', 94363202, NULL),
(6, 98052878861, 'Nils', 'Johansson', 'Norgegata 128', 1668, 'gunillajohansson@gmail.com', 91880914, NULL),
(7, 96060248159, 'Björn', 'Gunnarsson', 'Norgegata 153', 7442, 'svenjohansson@gmail.com', 84585216, NULL),
(8, 95030581980, 'Mattias', 'Gunnarsson', 'Norgegata 241', 3628, 'bibbinilsson@gmail.com', 12578397, NULL),
(9, 97061216746, 'Mattias', 'Von Pluring', 'Norgegata 5', 2488, 'nilsjohansson@gmail.com', 82250056, NULL),
(10, 97080768541, 'Mattias', 'Anka', 'Norgegata 30', 6581, 'bibbinilsson@gmail.com', 58055744, NULL),
(11, 96031134275, 'Mattias', 'Nilsson', 'Norgegata 240', 8968, 'björngunnarsson@gmail.com', 59906834, NULL),
(12, 97022317776, 'Kent', 'Anka', 'Norgegata 113', 8357, 'gunillaolsson@gmail.com', 77830507, NULL),
(13, 96042311002, 'Nils', 'Von Pluring', 'Norgegata 335', 6004, 'mattiasgunnarsson@gmail.com', 75040348, NULL),
(14, 99081743951, 'Mattias', 'Johansson', 'Norgegata 181', 6917, 'evanilsson@gmail.com', 72446201, NULL),
(15, 98072610140, 'Erik', 'Olsson', 'Norgegata 143', 5050, 'mattiasvon_pluring@gmail.com', 55177604, NULL),
(16, 95050776562, 'Jens', 'Anka', 'Norgegata 250', 6620, 'evavon_pluring@gmail.com', 80414939, NULL),
(17, 82562744, 'Nils', 'Nilsson', 'Norgegata 84', 1333, 'mattiasanka@gmail.com', 60918843, NULL),
(18, 112623297, 'Sven', 'Anka', 'Norgegata 54', 9148, 'piavon_pluring@gmail.com', 14380988, NULL),
(19, 99040612669, 'Sven', 'Von Pluring', 'Norgegata 176', 2859, 'mattiasnilsson@gmail.com', 64504956, NULL),
(20, 99042283114, 'Jens', 'Nilsson', 'Norgegata 353', 7210, 'bibbigunnarsson@gmail.com', 56630010, NULL),
(21, 2092615996, 'Olle', 'Olsson', 'Norgegata 305', 7315, 'kentanka@gmail.com', 72463119, NULL),
(22, 98011173256, 'Sven', 'Johansson', 'Norgegata 96', 4419, 'citonaanka@gmail.com', 45904278, NULL),
(23, 2101927267, 'Arne', 'Nilsson', 'Norgegata 300', 8974, 'nilsvon_pluring@gmail.com', 60710188, NULL),
(24, 97050138424, 'Björn', 'Gunnarsson', 'Norgegata 59', 7350, 'malinvon_pluring@gmail.com', 19419744, NULL),
(25, 96082352189, 'Björn', 'Olsson', 'Norgegata 278', 9014, 'mattiasjohansson@gmail.com', 34769431, NULL),
(26, 99020677563, 'Olle', 'Von Pluring', 'Norgegata 201', 4283, 'malinjohansson@gmail.com', 33125882, NULL),
(27, 2111468413, 'Kent', 'Johansson', 'Norgegata 133', 6591, 'erikolsson@gmail.com', 21026026, NULL),
(28, 2021950518, 'Arne', 'Von Pluring', 'Norgegata 317', 3389, 'bibbigunnarsson@gmail.com', 22315543, NULL),
(29, 2010164836, 'Björn', 'Nilsson', 'Norgegata 77', 8775, 'jensanka@gmail.com', 23192640, NULL),
(30, 96042526949, 'Anders', 'Von Pluring', 'Norgegata 164', 2315, 'auroranilsson@gmail.com', 79373504, NULL),
(31, 97050982757, 'Olle', 'Anka', 'Norgegata 327', 2036, 'nilsnilsson@gmail.com', 11976138, NULL),
(32, 98111345423, 'Jens', 'Anka', 'Norgegata 292', 6352, 'evagunnarsson@gmail.com', 81180330, NULL),
(33, 2072782375, 'Nils', 'Von Pluring', 'Norgegata 76', 2611, 'svenanka@gmail.com', 83230963, NULL),
(34, 91454821, 'Arne', 'Von Pluring', 'Norgegata 50', 7683, 'citonaolsson@gmail.com', 53276199, NULL),
(35, 2052194874, 'Olle', 'Nilsson', 'Norgegata 215', 8104, 'svenvon_pluring@gmail.com', 46778392, NULL),
(36, 98060533233, 'Erik', 'Anka', 'Norgegata 355', 4935, 'gunillavon_pluring@gmail.com', 64141039, NULL),
(37, 96031082331, 'Anders', 'Von Pluring', 'Norgegata 267', 2125, 'jensnilsson@gmail.com', 19717753, NULL),
(38, 2090265336, 'Björn', 'Anka', 'Norgegata 70', 4538, 'malinvon_pluring@gmail.com', 45307620, NULL),
(39, 2011881739, 'Björn', 'Nilsson', 'Norgegata 103', 4523, 'olleolsson@gmail.com', 18197726, NULL),
(40, 98012538906, 'Björn', 'Johansson', 'Norgegata 229', 3432, 'beritgunnarsson@gmail.com', 71870882, NULL),
(41, 96060643298, 'Nils', 'Olsson', 'Norgegata 215', 8669, 'svenjohansson@gmail.com', 18026835, NULL),
(42, 97111656175, 'Björn', 'Johansson', 'Norgegata 115', 2687, 'gunillaolsson@gmail.com', 31664332, NULL),
(43, 90436029, 'Olle', 'Gunnarsson', 'Norgegata 176', 6732, 'arnenilsson@gmail.com', 45318749, NULL),
(44, 97051829179, 'Olle', 'Von Pluring', 'Norgegata 46', 4192, 'annikanilsson@gmail.com', 89436864, NULL),
(45, 50692717, 'Mattias', 'Von Pluring', 'Norgegata 21', 1624, 'björngunnarsson@gmail.com', 29320430, NULL),
(46, 99082540380, 'Jens', 'Johansson', 'Norgegata 57', 6735, 'citonagunnarsson@gmail.com', 13007415, NULL),
(47, 97010976102, 'Nils', 'Nilsson', 'Norgegata 223', 6201, 'björnolsson@gmail.com', 96224295, NULL),
(48, 98022082791, 'Björn', 'Olsson', 'Norgegata 111', 1827, 'annikaanka@gmail.com', 30002151, NULL),
(49, 2041542382, 'Jens', 'Von Pluring', 'Norgegata 74', 4321, 'ollevon_pluring@gmail.com', 39501983, NULL),
(50, 2081280584, 'Kent', 'Anka', 'Norgegata 172', 3024, 'annikavon_pluring@gmail.com', 64095001, NULL),
(51, 2090529814, 'Nils', 'Johansson', 'Norgegata 97', 5525, 'kentjohansson@gmail.com', 20929502, NULL),
(52, 91785725, 'Sven', 'Von Pluring', 'Norgegata 339', 4471, 'beritjohansson@gmail.com', 46948255, NULL),
(53, 2012017113, 'Björn', 'Gunnarsson', 'Norgegata 330', 2575, 'arnevon_pluring@gmail.com', 23469029, NULL),
(54, 95070519729, 'Sven', 'Von Pluring', 'Norgegata 2', 8456, 'susanneanka@gmail.com', 32234980, NULL),
(55, 97080418862, 'Arne', 'Anka', 'Norgegata 326', 5177, 'björnnilsson@gmail.com', 44584000, NULL),
(56, 95060934921, 'Kent', 'Olsson', 'Norgegata 289', 6209, 'gunillagunnarsson@gmail.com', 40436807, NULL),
(57, 99042654320, 'Arne', 'Gunnarsson', 'Norgegata 37', 9011, 'andersvon_pluring@gmail.com', 93779530, NULL),
(58, 96052796640, 'Sven', 'Von Pluring', 'Norgegata 25', 4823, 'evagunnarsson@gmail.com', 64309841, NULL),
(59, 2070622820, 'Olle', 'Von Pluring', 'Norgegata 160', 2937, 'olleanka@gmail.com', 88686906, NULL),
(60, 101345602, 'Nils', 'Von Pluring', 'Norgegata 280', 1498, 'citonajohansson@gmail.com', 18860132, NULL),
(61, 1041323035, 'Jens', 'Nilsson', 'Norgegata 349', 8606, 'jensanka@gmail.com', 91153125, NULL),
(62, 98010896123, 'Kent', 'Olsson', 'Norgegata 74', 2425, 'malinanka@gmail.com', 38665849, NULL),
(63, 96082695036, 'Mattias', 'Johansson', 'Norgegata 314', 4978, 'nilsvon_pluring@gmail.com', 78284451, NULL),
(64, 99091345846, 'Nils', 'Von Pluring', 'Norgegata 151', 2109, 'beritjohansson@gmail.com', 74519630, NULL),
(65, 96082296600, 'Sven', 'Johansson', 'Norgegata 135', 1500, 'arnevon_pluring@gmail.com', 77899007, NULL),
(66, 1072247217, 'Kent', 'Olsson', 'Norgegata 225', 5547, 'evajohansson@gmail.com', 53322666, NULL),
(67, 97082467612, 'Nils', 'Gunnarsson', 'Norgegata 135', 9463, 'ollenilsson@gmail.com', 25800646, NULL),
(68, 1082611537, 'Mattias', 'Von Pluring', 'Norgegata 117', 3101, 'citonaanka@gmail.com', 23730326, NULL),
(69, 99022376106, 'Arne', 'Johansson', 'Norgegata 339', 6466, 'erikanka@gmail.com', 14609478, NULL),
(70, 41439612, 'Jens', 'Olsson', 'Norgegata 122', 2667, 'bibbianka@gmail.com', 29831179, NULL),
(71, 96072252911, 'Kent', 'Anka', 'Norgegata 223', 9890, 'andersvon_pluring@gmail.com', 18350220, NULL),
(72, 95031294807, 'Anders', 'Gunnarsson', 'Norgegata 129', 6183, 'citonanilsson@gmail.com', 52354887, NULL),
(73, 2102196075, 'Kent', 'Nilsson', 'Norgegata 309', 2956, 'björnanka@gmail.com', 71355795, NULL),
(74, 2052992524, 'Björn', 'Johansson', 'Norgegata 97', 7583, 'citonavon_pluring@gmail.com', 16268868, NULL),
(75, 98021710836, 'Arne', 'Olsson', 'Norgegata 317', 9189, 'björnnilsson@gmail.com', 56380406, NULL),
(76, 51642098, 'Jens', 'Nilsson', 'Norgegata 277', 6602, 'auroravon_pluring@gmail.com', 60214102, NULL),
(77, 100761504, 'Arne', 'Anka', 'Norgegata 10', 5088, 'björnjohansson@gmail.com', 17428075, NULL),
(78, 92845810, 'Erik', 'Anka', 'Norgegata 270', 6013, 'malinvon_pluring@gmail.com', 37195636, NULL),
(79, 2062741437, 'Jens', 'Von Pluring', 'Norgegata 329', 4787, 'nilsolsson@gmail.com', 75677019, NULL),
(80, 97022284771, 'Olle', 'Olsson', 'Norgegata 347', 8671, 'malinjohansson@gmail.com', 72899890, NULL),
(81, 95111615430, 'Nils', 'Nilsson', 'Norgegata 95', 4379, 'björnjohansson@gmail.com', 51768022, NULL),
(82, 95110779230, 'Sven', 'Gunnarsson', 'Norgegata 50', 4770, 'evaolsson@gmail.com', 51837193, NULL),
(83, 95050873666, 'Nils', 'Anka', 'Norgegata 107', 2659, 'ollegunnarsson@gmail.com', 91810188, NULL),
(84, 2022488972, 'Sven', 'Gunnarsson', 'Norgegata 82', 9179, 'evagunnarsson@gmail.com', 99653856, NULL),
(85, 1092115183, 'Nils', 'Gunnarsson', 'Norgegata 324', 9333, 'ollevon_pluring@gmail.com', 92333732, NULL),
(86, 99111271510, 'Nils', 'Olsson', 'Norgegata 36', 2032, 'bibbivon_pluring@gmail.com', 85797146, NULL),
(87, 96022788859, 'Björn', 'Von Pluring', 'Norgegata 179', 4801, 'mattiasvon_pluring@gmail.com', 69546722, NULL),
(88, 81843069, 'Mattias', 'Nilsson', 'Norgegata 319', 1146, 'susannejohansson@gmail.com', 13151972, NULL),
(89, 97022069236, 'Nils', 'Gunnarsson', 'Norgegata 198', 6482, 'jensjohansson@gmail.com', 21412236, NULL),
(90, 99050294743, 'Olle', 'Johansson', 'Norgegata 341', 5551, 'beritnilsson@gmail.com', 12208462, NULL),
(91, 97042338554, 'Björn', 'Nilsson', 'Norgegata 102', 8075, 'nilsnilsson@gmail.com', 94417686, NULL),
(92, 52741840, 'Mattias', 'Olsson', 'Norgegata 134', 8550, 'annikaolsson@gmail.com', 80362933, NULL),
(93, 96071823208, 'Kent', 'Anka', 'Norgegata 331', 9405, 'björnolsson@gmail.com', 18022857, NULL),
(94, 2071685165, 'Nils', 'Von Pluring', 'Norgegata 153', 7137, 'gunillaanka@gmail.com', 23617242, NULL),
(95, 1040383056, 'Anders', 'Nilsson', 'Norgegata 83', 9319, 'jensvon_pluring@gmail.com', 64086023, NULL),
(96, 95052531534, 'Arne', 'Nilsson', 'Norgegata 263', 5982, 'susannenilsson@gmail.com', 94067862, NULL),
(97, 1030271787, 'Anders', 'Anka', 'Norgegata 119', 1482, 'kentanka@gmail.com', 67446933, NULL),
(98, 99052274231, 'Kent', 'Gunnarsson', 'Norgegata 352', 3652, 'gunillaanka@gmail.com', 68759652, NULL),
(99, 80968317, 'Olle', 'Von Pluring', 'Norgegata 280', 7752, 'nilsjohansson@gmail.com', 27395255, NULL),
(100, 99102051885, 'Mattias', 'Von Pluring', 'Norgegata 338', 5926, 'piaanka@gmail.com', 66724374, NULL),
(101, 99050544838, 'Erik', 'Von Pluring', 'Norgegata 97', 8303, 'svenvon_pluring@gmail.com', 33264398, NULL),
(102, 81749748, 'Björn', 'Anka', 'Norgegata 313', 6925, 'bibbiolsson@gmail.com', 57305796, NULL),
(103, 2050221637, 'Bibbi', 'Gunnarsson', 'Norgegata 171', 4080, 'björnanka@gmail.com', 59798394, NULL),
(104, 30634223, 'Berit', 'Anka', 'Norgegata 174', 8084, 'annikavon_pluring@gmail.com', 85775686, NULL),
(105, 95100369654, 'Aurora', 'Von Pluring', 'Norgegata 126', 6339, 'svennilsson@gmail.com', 29152082, NULL),
(106, 2071753072, 'Gunilla', 'Johansson', 'Norgegata 123', 9528, 'annikaolsson@gmail.com', 25204039, NULL),
(107, 96101494183, 'Pia', 'Anka', 'Norgegata 42', 4744, 'björnolsson@gmail.com', 45228483, NULL),
(108, 99011899081, 'Susanne', 'Gunnarsson', 'Norgegata 335', 6605, 'evavon_pluring@gmail.com', 17466495, NULL),
(109, 22038321, 'Bibbi', 'Olsson', 'Norgegata 333', 6072, 'andersolsson@gmail.com', 83851658, NULL),
(110, 95012649184, 'Malin', 'Anka', 'Norgegata 153', 9438, 'malinvon_pluring@gmail.com', 81212054, NULL),
(111, 111474890, 'Susanne', 'Anka', 'Norgegata 12', 3651, 'ollegunnarsson@gmail.com', 35125893, NULL),
(112, 95102921778, 'Berit', 'Olsson', 'Norgegata 50', 2566, 'bibbijohansson@gmail.com', 40269535, NULL),
(113, 95102999388, 'Malin', 'Von Pluring', 'Norgegata 150', 7406, 'jensanka@gmail.com', 32575458, NULL),
(114, 98112361457, 'Annika', 'Johansson', 'Norgegata 347', 2813, 'evaolsson@gmail.com', 19886303, NULL),
(115, 97061817029, 'Aurora', 'Nilsson', 'Norgegata 167', 4028, 'olleanka@gmail.com', 28207115, NULL),
(116, 97091615211, 'Berit', 'Olsson', 'Norgegata 342', 2423, 'malinolsson@gmail.com', 69531403, NULL),
(117, 97110724828, 'Pia', 'Von Pluring', 'Norgegata 267', 2281, 'björnolsson@gmail.com', 44435813, NULL),
(118, 99061521846, 'Aurora', 'Johansson', 'Norgegata 249', 2004, 'piavon_pluring@gmail.com', 99010693, NULL),
(119, 99050191578, 'Bibbi', 'Gunnarsson', 'Norgegata 98', 3503, 'kentvon_pluring@gmail.com', 50148682, NULL),
(120, 99022691019, 'Annika', 'Johansson', 'Norgegata 108', 9631, 'piavon_pluring@gmail.com', 85182075, NULL),
(121, 99111839111, 'Eva', 'Johansson', 'Norgegata 192', 8128, 'svengunnarsson@gmail.com', 49472495, NULL),
(122, 2050941823, 'Annika', 'Johansson', 'Norgegata 54', 1663, 'citonajohansson@gmail.com', 51427531, NULL),
(123, 95082527752, 'Gunilla', 'Johansson', 'Norgegata 58', 4823, 'olleolsson@gmail.com', 52035662, NULL),
(124, 96031575181, 'Annika', 'Anka', 'Norgegata 306', 8171, 'evajohansson@gmail.com', 59631030, NULL),
(125, 1080737234, 'Gunilla', 'Nilsson', 'Norgegata 101', 1157, 'arnejohansson@gmail.com', 88241340, NULL),
(126, 97101120971, 'Citona', 'Olsson', 'Norgegata 127', 9492, 'susanneanka@gmail.com', 82864159, NULL),
(127, 98080884180, 'Pia', 'Johansson', 'Norgegata 344', 6222, 'nilsvon_pluring@gmail.com', 23565806, NULL),
(128, 1030745934, 'Eva', 'Von Pluring', 'Norgegata 59', 4842, 'evavon_pluring@gmail.com', 62664440, NULL),
(129, 99102940748, 'Eva', 'Von Pluring', 'Norgegata 321', 2354, 'björnvon_pluring@gmail.com', 38205737, NULL),
(130, 98041661389, 'Annika', 'Von Pluring', 'Norgegata 292', 6983, 'malinvon_pluring@gmail.com', 66458525, NULL),
(131, 99071474555, 'Annika', 'Nilsson', 'Norgegata 128', 1977, 'björnanka@gmail.com', 62859890, NULL),
(132, 1062216277, 'Susanne', 'Olsson', 'Norgegata 148', 3658, 'citonavon_pluring@gmail.com', 19761350, NULL),
(133, 99032210249, 'Gunilla', 'Olsson', 'Norgegata 276', 2171, 'arneanka@gmail.com', 80304308, NULL),
(134, 1030515124, 'Susanne', 'Gunnarsson', 'Norgegata 185', 2593, 'auroranilsson@gmail.com', 71857373, NULL),
(135, 99111925632, 'Aurora', 'Olsson', 'Norgegata 119', 9740, 'arneolsson@gmail.com', 85404572, NULL),
(136, 99101199508, 'Pia', 'Gunnarsson', 'Norgegata 334', 6663, 'malinjohansson@gmail.com', 43156474, NULL),
(137, 1071133794, 'Gunilla', 'Anka', 'Norgegata 219', 3344, 'svenjohansson@gmail.com', 91656007, NULL),
(138, 96100650665, 'Eva', 'Anka', 'Norgegata 212', 1271, 'gunillaanka@gmail.com', 53326482, NULL),
(139, 1071358355, 'Berit', 'Olsson', 'Norgegata 244', 6541, 'andersolsson@gmail.com', 43067359, NULL),
(140, 2092611663, 'Aurora', 'Anka', 'Norgegata 162', 5566, 'bibbigunnarsson@gmail.com', 70176447, NULL),
(141, 97081382191, 'Gunilla', 'Von Pluring', 'Norgegata 77', 6237, 'kentjohansson@gmail.com', 21437652, NULL),
(142, 96032570025, 'Malin', 'Olsson', 'Norgegata 112', 4161, 'piaolsson@gmail.com', 89623245, NULL),
(143, 96012868990, 'Aurora', 'Johansson', 'Norgegata 138', 4801, 'mattiasgunnarsson@gmail.com', 16701436, NULL),
(144, 97060975663, 'Susanne', 'Anka', 'Norgegata 164', 5372, 'evaanka@gmail.com', 74090340, NULL),
(145, 99112831015, 'Eva', 'Anka', 'Norgegata 63', 8730, 'jensolsson@gmail.com', 24506600, NULL),
(146, 95052768448, 'Annika', 'Nilsson', 'Norgegata 88', 7152, 'evaolsson@gmail.com', 66860322, NULL),
(147, 99042233993, 'Eva', 'Olsson', 'Norgegata 352', 9756, 'mattiasvon_pluring@gmail.com', 40314700, NULL),
(148, 95071037334, 'Aurora', 'Anka', 'Norgegata 230', 9060, 'bibbiolsson@gmail.com', 38773304, NULL),
(149, 99022780890, 'Eva', 'Von Pluring', 'Norgegata 131', 7280, 'erikjohansson@gmail.com', 36550833, NULL),
(150, 95102110432, 'Eva', 'Anka', 'Norgegata 210', 7860, 'citonaolsson@gmail.com', 73357857, NULL),
(151, 96072387045, 'Gunilla', 'Nilsson', 'Norgegata 348', 9382, 'erikanka@gmail.com', 98981866, NULL),
(152, 2100915946, 'Eva', 'Gunnarsson', 'Norgegata 39', 6595, 'gunillaolsson@gmail.com', 78489144, NULL),
(153, 96050556863, 'Annika', 'Von Pluring', 'Norgegata 343', 7684, 'svenjohansson@gmail.com', 75273135, NULL),
(154, 98051485706, 'Annika', 'Olsson', 'Norgegata 312', 2320, 'auroraolsson@gmail.com', 27578854, NULL),
(155, 2092588614, 'Eva', 'Von Pluring', 'Norgegata 275', 2486, 'mattiasgunnarsson@gmail.com', 62303556, NULL),
(156, 99042011736, 'Malin', 'Von Pluring', 'Norgegata 251', 6661, 'beritvon_pluring@gmail.com', 95544740, NULL),
(157, 96020220318, 'Bibbi', 'Johansson', 'Norgegata 85', 6706, 'erikvon_pluring@gmail.com', 72122107, NULL),
(158, 97090366585, 'Eva', 'Olsson', 'Norgegata 319', 8792, 'susannevon_pluring@gmail.com', 29544984, NULL),
(159, 10633829, 'Malin', 'Olsson', 'Norgegata 211', 7197, 'mattiasgunnarsson@gmail.com', 46211412, NULL),
(160, 98080110186, 'Pia', 'Von Pluring', 'Norgegata 218', 5745, 'annikanilsson@gmail.com', 71898697, NULL),
(161, 96072781560, 'Pia', 'Von Pluring', 'Norgegata 252', 5509, 'ollejohansson@gmail.com', 96388155, NULL),
(162, 98061275167, 'Citona', 'Johansson', 'Norgegata 78', 4667, 'gunillaolsson@gmail.com', 21793721, NULL),
(163, 97102333817, 'Eva', 'Johansson', 'Norgegata 202', 8824, 'mattiasolsson@gmail.com', 94569670, NULL),
(164, 1062175465, 'Susanne', 'Anka', 'Norgegata 206', 8973, 'gunillavon_pluring@gmail.com', 82444861, NULL),
(165, 97071169525, 'Eva', 'Von Pluring', 'Norgegata 325', 8283, 'nilsvon_pluring@gmail.com', 67947389, NULL),
(166, 112545496, 'Malin', 'Von Pluring', 'Norgegata 254', 8676, 'annikaanka@gmail.com', 99388306, NULL),
(167, 96072476054, 'Citona', 'Von Pluring', 'Norgegata 300', 4052, 'svenolsson@gmail.com', 16874943, NULL),
(168, 99080451632, 'Aurora', 'Nilsson', 'Norgegata 3', 8291, 'bibbianka@gmail.com', 30336064, NULL),
(169, 20933998, 'Malin', 'Johansson', 'Norgegata 203', 4685, 'jensvon_pluring@gmail.com', 74433247, NULL),
(170, 97092384008, 'Gunilla', 'Anka', 'Norgegata 115', 2673, 'auroravon_pluring@gmail.com', 22924400, NULL),
(171, 99070613017, 'Bibbi', 'Gunnarsson', 'Norgegata 81', 7715, 'nilsanka@gmail.com', 18058617, NULL),
(172, 99062142280, 'Pia', 'Olsson', 'Norgegata 91', 8244, 'malinolsson@gmail.com', 94443526, NULL),
(173, 99082095556, 'Eva', 'Anka', 'Norgegata 247', 8792, 'nilsolsson@gmail.com', 60962747, NULL),
(174, 96061480957, 'Eva', 'Olsson', 'Norgegata 337', 1682, 'auroragunnarsson@gmail.com', 22509560, NULL),
(175, 97070868663, 'Bibbi', 'Olsson', 'Norgegata 15', 1802, 'arnenilsson@gmail.com', 95907107, NULL),
(176, 60193608, 'Citona', 'Olsson', 'Norgegata 57', 3452, 'bibbinilsson@gmail.com', 49011189, NULL),
(177, 42456459, 'Gunilla', 'Olsson', 'Norgegata 231', 5982, 'mattiasvon_pluring@gmail.com', 17447813, NULL),
(178, 95031020929, 'Aurora', 'Olsson', 'Norgegata 258', 9413, 'bibbijohansson@gmail.com', 22167039, NULL),
(179, 98071773437, 'Berit', 'Von Pluring', 'Norgegata 266', 6016, 'arnevon_pluring@gmail.com', 35008007, NULL),
(180, 97101213058, 'Susanne', 'Von Pluring', 'Norgegata 138', 4244, 'auroragunnarsson@gmail.com', 16103333, NULL),
(181, 95051750064, 'Annika', 'Nilsson', 'Norgegata 286', 6906, 'mattiasjohansson@gmail.com', 74504264, NULL),
(182, 97011551432, 'Gunilla', 'Olsson', 'Norgegata 53', 8336, 'gunillaanka@gmail.com', 97118308, NULL),
(183, 1020637534, 'Gunilla', 'Von Pluring', 'Norgegata 18', 3717, 'mattiasolsson@gmail.com', 93426109, NULL),
(184, 97052748482, 'Annika', 'Anka', 'Norgegata 71', 5125, 'auroraolsson@gmail.com', 82497673, NULL),
(185, 97031814456, 'Bibbi', 'Anka', 'Norgegata 259', 7371, 'erikgunnarsson@gmail.com', 44058916, NULL),
(186, 99040756970, 'Aurora', 'Von Pluring', 'Norgegata 130', 1841, 'malingunnarsson@gmail.com', 98221457, NULL),
(187, 96020139340, 'Malin', 'Olsson', 'Norgegata 16', 6290, 'mattiasnilsson@gmail.com', 51653881, NULL),
(188, 1042055191, 'Aurora', 'Gunnarsson', 'Norgegata 11', 9635, 'piavon_pluring@gmail.com', 46245687, NULL),
(189, 97092271247, 'Bibbi', 'Nilsson', 'Norgegata 194', 8935, 'eriknilsson@gmail.com', 84539060, NULL),
(190, 96071163000, 'Bibbi', 'Johansson', 'Norgegata 97', 7441, 'beritolsson@gmail.com', 46376367, NULL),
(191, 95071914550, 'Aurora', 'Gunnarsson', 'Norgegata 266', 7918, 'olleolsson@gmail.com', 58533077, NULL),
(192, 97040497206, 'Gunilla', 'Anka', 'Norgegata 64', 9324, 'evaanka@gmail.com', 84092375, NULL),
(193, 95021552910, 'Aurora', 'Olsson', 'Norgegata 271', 1181, 'nilsvon_pluring@gmail.com', 33420232, NULL),
(194, 99060821616, 'Malin', 'Gunnarsson', 'Norgegata 294', 5708, 'annikavon_pluring@gmail.com', 77973002, NULL),
(195, 97091799809, 'Pia', 'Von Pluring', 'Norgegata 72', 7865, 'mattiasgunnarsson@gmail.com', 48943003, NULL),
(196, 1012613397, 'Berit', 'Olsson', 'Norgegata 14', 1248, 'auroraanka@gmail.com', 60816664, NULL),
(197, 95081629331, 'Eva', 'Anka', 'Norgegata 107', 2854, 'nilsnilsson@gmail.com', 52837651, NULL),
(198, 96031255942, 'Annika', 'Von Pluring', 'Norgegata 110', 8192, 'bibbivon_pluring@gmail.com', 99694664, NULL),
(199, 95042454663, 'Aurora', 'Anka', 'Norgegata 254', 9646, 'nilsolsson@gmail.com', 72121826, NULL),
(200, 98070488393, 'Bibbi', 'Von Pluring', 'Norgegata 131', 5341, 'citonagunnarsson@gmail.com', 44732175, NULL),
(201, 98072594896, 'Citona', 'Gunnarsson', 'Norgegata 313', 6948, 'arnevon_pluring@gmail.com', 56913041, NULL),
(202, 98061493496, 'Malin', 'Von Pluring', 'Norgegata 26', 7311, 'malinvon_pluring@gmail.com', 53175571, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Foresatt`
--

CREATE TABLE IF NOT EXISTS `Foresatt` (
  `Kundenummer` int(32) NOT NULL AUTO_INCREMENT,
  `Foedselsnummer` int(32) NOT NULL,
  `Fornavn` varchar(100) NOT NULL,
  `Etternavn` varchar(100) NOT NULL,
  `Adresse` varchar(100) NOT NULL,
  `Postnummer` int(4) NOT NULL,
  `Mail` varchar(100) NOT NULL,
  `Telefon` int(8) NOT NULL,
  PRIMARY KEY (`Kundenummer`),
  UNIQUE KEY `Foedselsnummer` (`Foedselsnummer`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Foresatt_for_bruker`
--

CREATE TABLE IF NOT EXISTS `Foresatt_for_bruker` (
  `Kundenummer_Foresatt` int(32) NOT NULL DEFAULT '0',
  `Kundenummer_Bruker` int(32) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Kundenummer_Foresatt`,`Kundenummer_Bruker`),
  KEY `kundenummer_bruker_fk` (`Kundenummer_Bruker`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Passord_Administrator`
--

CREATE TABLE IF NOT EXISTS `Passord_Administrator` (
  `ID` int(32) NOT NULL DEFAULT '0',
  `Hash_av_passord` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Hash_av_passord` (`Hash_av_passord`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Passord_Bruker`
--

CREATE TABLE IF NOT EXISTS `Passord_Bruker` (
  `Kundenummer` int(32) NOT NULL DEFAULT '0',
  `Hash_av_passord` varchar(32) NOT NULL,
  PRIMARY KEY (`Kundenummer`),
  UNIQUE KEY `Hash_av_passord` (`Hash_av_passord`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Passord_Foresatt`
--

CREATE TABLE IF NOT EXISTS `Passord_Foresatt` (
  `Kundenummer` int(32) NOT NULL DEFAULT '0',
  `Hash_av_passord` varchar(32) NOT NULL,
  PRIMARY KEY (`Kundenummer`),
  UNIQUE KEY `Hash_av_passord` (`Hash_av_passord`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Transaksjon`
--

CREATE TABLE IF NOT EXISTS `Transaksjon` (
  `ID` int(32) NOT NULL AUTO_INCREMENT,
  `Tidspunkt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Kroner` bigint(32) DEFAULT '0',
  `Oere` int(2) DEFAULT '0',
  `Avsender_Kontonummer` int(32) NOT NULL,
  `Mottaker_Kontonummer` int(32) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `avsender_kontonummer_fk` (`Avsender_Kontonummer`),
  KEY `mottaker_kontonummer_fk` (`Mottaker_Kontonummer`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
