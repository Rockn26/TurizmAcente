
user tablosu oluşturma sorgusu

CREATE TABLE `user` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`type` enum('admin','employee') COLLATE utf8mb4_general_ci NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

hotel tablosu oluşturma sorgusu

CREATE TABLE `hotel` (
`id` int NOT NULL AUTO_INCREMENT,
`name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`star` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`adress` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci


hotel tipi oluşturma sorgusu

CREATE TABLE `type_hotel` (
`id` int NOT NULL AUTO_INCREMENT,
`type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`hotel_id` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci



hotel seasonu oluşturma tablosu

CREATE TABLE `season` (
`id` int NOT NULL AUTO_INCREMENT,
`season_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`season_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`hotel_id` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci



oda oluşturma tablosu

CREATE TABLE `room` (
`id` int NOT NULL AUTO_INCREMENT,
`room_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`stock` int NOT NULL,
`season_id` int NOT NULL,
`adult_price` int NOT NULL,
`child_price` int NOT NULL,
`hotel_type_id` int NOT NULL,
`hotel_id` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci



oda özellikleri oluşturma tablosu

CREATE TABLE `room_properties` (
`id` int NOT NULL AUTO_INCREMENT,
`property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`room_id` int NOT NULL,
`bed` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`area` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci



rezervasyon infosu oluşturma tablosu

CREATE TABLE `reservation_info` (
`id` int NOT NULL AUTO_INCREMENT,
`c_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`c_phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`c_email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`c_note` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`check_in` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`check_out` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`adultN` int NOT NULL,
`childN` int NOT NULL,
`price` int NOT NULL,
`room_id` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci



user tablosu sql sorgusu yazdırma :

INSERT INTO `user`(`id`, `name`, `uname`, `pass`, `type`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]')

INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES (NULL, 'ahmet', 'ahmet', '123', 'admin');
INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES (NULL, 'test', 'test1', '123', 'employee');
INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES (NULL, 'test2', 'test2', '123', 'employee');


hotel tablosu sql sorgusu yazdırma :

INSERT INTO `hotel`(`id`, `name`, `star`, `property`, `adress`, `phone`, `email`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]','[value-6]','[value-7]')

INSERT INTO `hotel` (`id`, `name`, `star`, `property`, `adress`, `phone`, `email`) VALUES (NULL, 'Anatolia', '****', 'Spa\r\nÜcretsiz Otopark', 'Ankara/Çankaya', '05552223321', 'anatolia@anatolia.com');
INSERT INTO `hotel` (`id`, `name`, `star`, `property`, `adress`, `phone`, `email`) VALUES (NULL, 'Hilton Garden', '****', 'Spa', 'Ankara/Yenimahalle', '05522124609', 'hilton@garden.com');
INSERT INTO `hotel` (`id`, `name`, `star`, `property`, `adress`, `phone`, `email`) VALUES (NULL, 'Tasigo', '*****', 'Spa\r\nÜcretsiz Otopark', 'Eskişehir/Odunpazarı', '055220055221', 'tasigo@eskisehir.com');

hotel tipi tablosu sql sorgusu yazdırma

INSERT INTO `type_hotel`(`id`, `type`, `hotel_id`) VALUES ('[value-1]','[value-2]','[value-3]')

INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES (NULL, 'Her Şey Dahil', '15');
INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES (NULL, 'Oda Kahvaltı', '15');
INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES (NULL, 'Ultra Her Şey Dahil', '16');
INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES (NULL, 'Her Şey Dahil', '17');

hotel sezonu tablosu sql sorgusu yazdırma 

INSERT INTO `season`(`id`, `season_start`, `season_end`, `hotel_id`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]')

INSERT INTO `season` (`id`, `season_start`, `season_end`, `hotel_id`) VALUES (NULL, '10/05/2024', '30/09/2024', '15');
INSERT INTO `season` (`id`, `season_start`, `season_end`, `hotel_id`) VALUES (NULL, '01/10/2023', '30/04/2024', '16');
INSERT INTO `season` (`id`, `season_start`, `season_end`, `hotel_id`) VALUES (NULL, '30/05/2025', '30/10/2025', '17');

oda tablosu sql sorgusu yazdırma 

INSERT INTO `room`(`id`, `room_type`, `stock`, `season_id`, `adult_price`, `child_price`, `hotel_type_id`, `hotel_id`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]','[value-6]','[value-7]','[value-8]')

INSERT INTO `room` (`id`, `room_type`, `stock`, `season_id`, `adult_price`, `child_price`, `hotel_type_id`, `hotel_id`) VALUES (NULL, 'Single', '10', '15', '800', '400', '24', '15');
INSERT INTO `room` (`id`, `room_type`, `stock`, `season_id`, `adult_price`, `child_price`, `hotel_type_id`, `hotel_id`) VALUES (NULL, 'Double', '10', '16', '500', '300', '26', '16');
INSERT INTO `room` (`id`, `room_type`, `stock`, `season_id`, `adult_price`, `child_price`, `hotel_type_id`, `hotel_id`) VALUES (NULL, 'Double', '6', '17', '1000', '500', '27', '17');


oda özellikleri sql sorgusu yazdırma

INSERT INTO `room_properties`(`id`, `property`, `room_id`, `bed`, `area`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]')

INSERT INTO `room_properties` (`id`, `property`, `room_id`, `bed`, `area`) VALUES (NULL, 'Televizyon\r\nKasa', '10', 'Single-1', '25');
INSERT INTO `room_properties` (`id`, `property`, `room_id`, `bed`, `area`) VALUES (NULL, 'Televizyon\r\nMinibar', '11', 'Double-1', '30');
INSERT INTO `room_properties` (`id`, `property`, `room_id`, `bed`, `area`) VALUES (NULL, 'Televizyon\r\nKasa', '12', 'Single-1', '30');


rezervasyon info sql sorgusu yazdırma

INSERT INTO `reservation_info`(`id`, `c_name`, `c_phone`, `c_email`, `c_note`, `check_in`, `check_out`, `adultN`, `childN`, `price`, `room_id`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]','[value-6]','[value-7]','[value-8]','[value-9]','[value-10]','[value-11]')

INSERT INTO `reservation_info`(`id`, `c_name`, `c_phone`, `c_email`, `c_note`, `check_in`, `check_out`, `adultN`, `childN`, `price`, `room_id`) VALUES ('NULL','Veli Vefa','0552223212','veli@vefa.com','Yok.','11/05/2024','15/05/2024','1','1','4800','10');
INSERT INTO `reservation_info`(`id`, `c_name`, `c_phone`, `c_email`, `c_note`, `check_in`, `check_out`, `adultN`, `childN`, `price`, `room_id`) VALUES ('NULL','Merve Bol','0555222123','merve@bol.com','Temiz olsun.','02/10/2023','05/10/2023','2','1','3900','11');
INSERT INTO `reservation_info`(`id`, `c_name`, `c_phone`, `c_email`, `c_note`, `check_in`, `check_out`, `adultN`, `childN`, `price`, `room_id`) VALUES ('NULL','Zeynep Tok','05552212321','zeynep@tok.com','Yok.','01/06/2025','04/06/2025','1','1','4500','12');




