-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 11 Kas 2023, 16:08:42
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `hotel`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotel`
--

CREATE TABLE `hotel` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `star` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `adress` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotel`
--

INSERT INTO `hotel` (`id`, `name`, `star`, `property`, `adress`, `phone`, `email`) VALUES
(4, 'Anemon', '*****', 'Spa	\nÜcretsiz Otopark', 'Eskişehir/Tepebaşı', '05552332212', 'AnemonHotel@anemonhotel.com'),
(11, 'Leto City', '****', 'Ücretsiz Otopark\nÜcretsiz Wifi\n', 'Eskişehir/Odunpazarı	', '05522332212', 'letocity@letocity.com'),
(12, 'at', '*****', 'a\na\n', 'at', '5555555', 'at@at.com'),
(13, 'Abra', '****', 'Ücretsiz Otopark\nÜcretsiz SPa	', 'Ankara/Kızılay	', '05523233221', 'abra@abra.com'),
(14, 't', '*****', 't', 't	', 't', 't');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation_info`
--

CREATE TABLE `reservation_info` (
  `id` int NOT NULL,
  `c_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `c_phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `c_email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `c_note` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `check_in` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `check_out` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `adultN` int NOT NULL,
  `childN` int NOT NULL,
  `price` int NOT NULL,
  `room_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `reservation_info`
--

INSERT INTO `reservation_info` (`id`, `c_name`, `c_phone`, `c_email`, `c_note`, `check_in`, `check_out`, `adultN`, `childN`, `price`, `room_id`) VALUES
(4, 'Ahmet Vey', '0552223322', 'ahmetvey@ahmet.com', 'Yok.', '01/05/2024', '05/05/2024', 1, 1, 3200, 9),
(5, 'Elif Bağ', '0552223321', 'elif@bag.com', 'Yok', '10/01/2025', '15/01/2025', 2, 1, 6500, 6),
(6, 'Veli Cefa', '055222123', 'veli@cefa.com', 'Yok.', '13/10/2024', '16/10/2024', 2, 2, 4200, 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `room_type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `stock` int NOT NULL,
  `season_id` int NOT NULL,
  `adult_price` int NOT NULL,
  `child_price` int NOT NULL,
  `hotel_type_id` int NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room`
--

INSERT INTO `room` (`id`, `room_type`, `stock`, `season_id`, `adult_price`, `child_price`, `hotel_type_id`, `hotel_id`) VALUES
(4, 'Single', 5, 11, 500, 300, 17, 11),
(5, 'Double', 4, 12, 500, 200, 19, 12),
(6, 'Single', 3, 13, 500, 300, 20, 13),
(9, 'Double', 9, 14, 500, 300, 22, 14);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `room_properties`
--

CREATE TABLE `room_properties` (
  `id` int NOT NULL,
  `property` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `room_id` int NOT NULL,
  `bed` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `area` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `room_properties`
--

INSERT INTO `room_properties` (`id`, `property`, `room_id`, `bed`, `area`) VALUES
(5, 'Televizyon\nMinibar', 4, 'Single-1', 20),
(6, 'Televizyon\nMinibar', 5, 'Double-2', 30),
(7, 'Televizyon', 6, 'Single-1', 25),
(9, 'Televizyon\nOyun Konsolu', 9, 'Double-1', 25);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `season`
--

CREATE TABLE `season` (
  `id` int NOT NULL,
  `season_start` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `season_end` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `season`
--

INSERT INTO `season` (`id`, `season_start`, `season_end`, `hotel_id`) VALUES
(11, '01/01/2024', '30/10/2024', 11),
(12, '10/10/2024', '30/05/2025', 12),
(13, '01/01/2025', '01/10/2025', 13),
(14, '01/05/2024', '30/12/2024', 14);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `type_hotel`
--

CREATE TABLE `type_hotel` (
  `id` int NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `type_hotel`
--

INSERT INTO `type_hotel` (`id`, `type`, `hotel_id`) VALUES
(16, 'Her Şey Dahil', 11),
(17, 'Oda Kahvaltı', 11),
(18, 'Oda Kahvaltı', 12),
(19, 'Tam Pansiyon', 12),
(20, 'Her Şey Dahil', 13),
(21, 'Yarım Pansiyon', 13),
(22, 'Ultra Her Şey Dahil', 14),
(23, 'Her Şey Dahil', 14);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `uname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `type` enum('admin','employee') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `name`, `uname`, `pass`, `type`) VALUES
(1, 'irfan', 'irfan', 'irfan', 'admin'),
(3, 'ee', 'ee', 'ee', 'employee');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `reservation_info`
--
ALTER TABLE `reservation_info`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `room_properties`
--
ALTER TABLE `room_properties`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `type_hotel`
--
ALTER TABLE `type_hotel`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Tablo için AUTO_INCREMENT değeri `reservation_info`
--
ALTER TABLE `reservation_info`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `room_properties`
--
ALTER TABLE `room_properties`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `season`
--
ALTER TABLE `season`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Tablo için AUTO_INCREMENT değeri `type_hotel`
--
ALTER TABLE `type_hotel`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Tablo için AUTO_INCREMENT değeri `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
