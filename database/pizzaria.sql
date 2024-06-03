-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jun 03, 2024 at 04:30 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pizzaria`
--

-- --------------------------------------------------------

--
-- Table structure for table `commandes`
--

CREATE TABLE `commandes` (
  `id` bigint(20) NOT NULL,
  `num_commande` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `total` float NOT NULL,
  `status` int(11) DEFAULT 0,
  `date_commande` varchar(255) NOT NULL,
  `date_validation` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `commandes`
--

INSERT INTO `commandes` (`id`, `num_commande`, `user_id`, `total`, `status`, `date_commande`, `date_validation`, `adresse`, `phone`) VALUES
(1, 'commande-Utilisateur-2790459d-b83a-4c5d-99a8-0d8e71345dfa', 2, 56, 1, '2024-06-02 22:29:25', '2024-06-02 22:30:20', 'Sfax , Tunisie', '785423698'),
(2, 'commande-Utilisateur-5739182c-670a-4dbc-96a7-2f96310e6c58', 2, 56, 1, '2024-06-02 22:34:51', '2024-06-02 22:35:47', 'sfax', '785423698'),
(3, 'commande-SFAXI-be6ac62c-005e-4327-8e6b-670502579024', 4, 564, 1, '2024-06-03 00:26:53', '2024-06-03 00:27:28', 'Sfax , Tunisie', '55000000'),
(4, 'commande-DHOUIB-7699c7db-a43d-4bb5-93ea-2e63411789ad', 3, 12, 1, '2024-06-03 01:56:02', '2024-06-03 02:02:27', 'Sfax , Tunisie', '55201231');

-- --------------------------------------------------------

--
-- Table structure for table `pizzas`
--

CREATE TABLE `pizzas` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `categorie` varchar(50) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `qte_disponible` int(11) DEFAULT NULL,
  `photo` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pizzas`
--

INSERT INTO `pizzas` (`id`, `nom`, `categorie`, `prix`, `qte_disponible`, `photo`, `status`, `description`) VALUES
(1, 'Margherita', 'Pizza Napolitaine', 12, 10, '48478236-e10d-4baa-8ec9-7b8fc037c58b.jpg', 0, 'Sauce tomate, mozzarella di Bufala, huile d’olive extra vierge et basilic frais.'),
(2, 'Marinara', 'Pizza Napolitaine', 10, 20, 'd3f75882-6edd-4fce-bf72-e3c39b5168a7.jpg', 0, 'Sauce tomate, ail, origan, huile d’olive extra vierge et parfois des anchois.'),
(3, 'Pepperoni', 'Pizza New-Yorkaise', 15, 20, 'e8342f9f-f400-402b-80ac-4d322102f1fe.jpg', 0, 'Sauce tomate, fromage mozzarella et tranches de pepperoni épicées.'),
(4, 'Suprême', 'Pizza New-Yorkaise', 18, 15, 'c838c4bb-52f9-4685-a572-0bf0e5aad748.jpg', 0, 'Sauce tomate, mozzarella, champignons, poivrons, oignons et saucisse italienne.'),
(5, 'Pizza à la Palermitaine', 'Pizza Sicilienne', 14, 10, 'cc0c7b8b-3e31-4b17-b472-5d6a7346b30e.jpg', 0, 'Sauce tomate, fromage mozzarella, anchois, olives vertes et chapelure.'),
(6, 'Pizza à la Caponata', 'Pizza Sicilienne', 16, 3, 'c07d9079-5768-4bd2-a9d1-2a2bda0768f0.jpg', 0, 'Sauce tomate, aubergines grillées, oignons, câpres, olives et fromage pecorino.'),
(7, 'Pizza à la Feta', 'Pizza Grecque', 13, 2, 'cd0a9ac2-35d1-4582-b751-2438a932e6a4.jpg', 0, 'Sauce tomate, feta, olives noires, tomates cerises et origan.'),
(8, 'Pizza aux Épinards et à la Feta', 'Pizza Grecque', 14, 1, '2ecab19e-d667-4c63-83d5-0649b6ccfe39.jpg', 0, 'Sauce tomate, épinards, feta, oignons et olives.'),
(9, 'Pizza au Poulet BBQ', 'Pizza Californienne', 20, 5, 'dfa616f8-75c6-4442-a796-43dbc2176afb.jpg', 0, 'Sauce barbecue, poulet grillé, oignons rouges, fromage mozzarella et coriandre.'),
(10, 'Pizza aux Légumes Grillés', 'Pizza Californienne', 18, 2, 'abf6dcee-c7fb-48a4-b782-9c87bd44d13f.jpg', 0, 'Sauce tomate, courgettes, poivrons, aubergines, fromage de chèvre et basilic.');

-- --------------------------------------------------------

--
-- Table structure for table `pizza_commandes`
--

CREATE TABLE `pizza_commandes` (
  `id` bigint(20) NOT NULL,
  `num_commande` varchar(255) NOT NULL,
  `pizza_id` bigint(20) NOT NULL,
  `quantite` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pizza_commandes`
--

INSERT INTO `pizza_commandes` (`id`, `num_commande`, `pizza_id`, `quantite`) VALUES
(1, 'commande-Utilisateur-2790459d-b83a-4c5d-99a8-0d8e71345dfa', 1, 3),
(2, 'commande-Utilisateur-2790459d-b83a-4c5d-99a8-0d8e71345dfa', 2, 2),
(3, 'commande-Utilisateur-5739182c-670a-4dbc-96a7-2f96310e6c58', 1, 3),
(4, 'commande-Utilisateur-5739182c-670a-4dbc-96a7-2f96310e6c58', 2, 2),
(5, 'commande-SFAXI-be6ac62c-005e-4327-8e6b-670502579024', 5, 39),
(6, 'commande-SFAXI-be6ac62c-005e-4327-8e6b-670502579024', 10, 1),
(7, 'commande-DHOUIB-7699c7db-a43d-4bb5-93ea-2e63411789ad', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `profile` varchar(15) DEFAULT 'user',
  `email` varchar(128) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `profile`, `email`, `phone`, `status`, `password`) VALUES
(1, 'Administrateur', 'Administrateur', 'admin', 'administrateur@gmail.com', '785423698', 0, 'administrateur'),
(2, 'Utilisateur', 'Utilisateur', 'user', 'utilisateur@gmail.com', '785423698', 0, 'utilisateur'),
(3, 'DHOUIB', 'Sirine', 'user', 'sirine@gmail.com', '55201231', 0, 'sirine'),
(4, 'SFAXI', 'Mariem', 'user', 'mariem@gmail.com', '55000000', 0, 'mariem'),
(5, 'GUIDARA', 'Oumaima', 'user', 'oumaima@gmail.com', '41521235', 0, 'oumaima');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pizzas`
--
ALTER TABLE `pizzas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pizza_commandes`
--
ALTER TABLE `pizza_commandes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pizza_id` (`pizza_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commandes`
--
ALTER TABLE `commandes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pizzas`
--
ALTER TABLE `pizzas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `pizza_commandes`
--
ALTER TABLE `pizza_commandes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pizza_commandes`
--
ALTER TABLE `pizza_commandes`
  ADD CONSTRAINT `pizza_commandes_ibfk_1` FOREIGN KEY (`pizza_id`) REFERENCES `pizzas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
