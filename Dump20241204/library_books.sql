-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `book_id` varchar(45) NOT NULL,
  `title` varchar(150) NOT NULL,
  `ISBN_13` varchar(45) DEFAULT NULL,
  `published_date` varchar(15) DEFAULT NULL,
  `image_preview` varchar(255) NOT NULL,
  `description` varchar(600) DEFAULT NULL,
  `ISBN_10` varchar(10) DEFAULT NULL,
  `web_reader_link` varchar(200) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('0-tqDwAAQBAJ','Then I will tell you','Unknown','Unknown','http://books.google.com/books/content?id=0-tqDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','Someone has told that the person you liked at the age of 17 was the person who left you most regret and torment. Because it is just a liking. It is not as difficult to understand as love, also not as simple as friendship. But liking, the feeling that neither comes forward nor steps backward, so, is restless in heart. [Then I will tell you - Cam Thuong] Read more at Awread: https://awread.vn','Unknown','http://play.google.com/books/reader?id=0-tqDwAAQBAJ&hl=&source=gbs_api','Cẩm Thương'),('BO8-DwAAQBAJ','What Can You Do?','Unknown','Unknown','http://books.google.com/books/content?id=BO8-DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','No description','Unknown','http://play.google.com/books/reader?id=BO8-DwAAQBAJ&hl=&source=gbs_api','Dino Lingo'),('cEjPEAAAQBAJ','i will never forget you','Unknown','2023-08-06','http://books.google.com/books/content?id=cEjPEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','anh nho em nhieu lam tai vi sao Prom? Vivien looked at him as if he had gone mad. They were sitting in the living room downstairs when he told her about the plan he had worked out with Mr. Ross. Though he sympathized with her anxiety, Grant clearly didn\'t give her a choice.','Unknown','http://play.google.com/books/reader?id=cEjPEAAAQBAJ&hl=&source=gbs_api','nguyen van hieu'),('DO8-DwAAQBAJ','Where Are You From?','Unknown','Unknown','http://books.google.com/books/content?id=DO8-DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','No description','Unknown','http://play.google.com/books/reader?id=DO8-DwAAQBAJ&hl=&source=gbs_api','Dino Lingo'),('Du8-DwAAQBAJ','Who Are You?','Unknown','Unknown','http://books.google.com/books/content?id=Du8-DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','No description','Unknown','http://play.google.com/books/reader?id=Du8-DwAAQBAJ&hl=&source=gbs_api','Dino Lingo'),('HnXJGJH-QTMC','Why don\'t you?','Unknown','1871','http://books.google.com/books/content?id=HnXJGJH-QTMC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','No description','Unknown','http://play.google.com/books/reader?id=HnXJGJH-QTMC&hl=&source=gbs_api','Unknown'),('jf-CAAAAQBAJ','I\'ll Catch You (Mills & Boon Kimani)','9781472019547','2013-08-28','http://books.google.com/books/content?id=jf-CAAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','Cedric Reeves has just been sidelined, and the bad-boy pro footballer suddenly finds himself without an agent or a prayer of getting back in the game. What he needs is someone pulling for him...someone like gorgeous go-getter Payton Mosely.','1472019547','http://play.google.com/books/reader?id=jf-CAAAAQBAJ&hl=&source=gbs_api','HarperCollins UK'),('RinqAgAAQBAJ','Can You Get Hooked On Lip Balm?','9781408937587','2011-04-01','http://books.google.com/books/content?id=RinqAgAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','Why does my shampoo stop working? Are my cosmetics poisoning me? What does hypoallergenic mean? Are organic products better? Every day thousands of people turn to the scientists at the popular blog TheBeautyBrains. com for answers to their most pressing beauty questions.','1408937581','http://play.google.com/books/reader?id=RinqAgAAQBAJ&hl=&source=gbs_api','HarperCollins UK'),('YHrrEAAAQBAJ','FOREIGN LANGUAGES ARE HARD, BUT YOU CAN SELF-LEARN THEM (bilingual)','9786047781843','2023-12-29','http://books.google.com/books/content?id=YHrrEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','This is an honest book about effective foreign language learning methods in practice, unlike the many advertisements out there where the theories sound good, but you don\'t know how to apply them I have been living and working in Singapore for more than 10 years. I, like you, used to scratch my head because I didn\'t know how to learn English effectively. However during my high school years, I found the right method to self-learn English, and made remarkable improvements in a short amount of time. Then, during my second year in university, I became the sole recipient of the Singapore Government ','6047781845','http://play.google.com/books/reader?id=YHrrEAAAQBAJ&hl=&source=gbs_api','Thế Giới Publishers (Nhà xuất bản Thế Giới)'),('zH2sAwAAQBAJ','I Love You, However!','9781105674938','2012-04-11','http://books.google.com/books/content?id=zH2sAwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api','This book is designed to assist the reader with understanding love, trust and faith.','1105674932','http://play.google.com/books/reader?id=zH2sAwAAQBAJ&hl=&source=gbs_api','Lulu.com');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 20:42:28
