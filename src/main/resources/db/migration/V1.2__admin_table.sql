-- domestic_service_market.admin_table definition

CREATE TABLE `admin_table` (
  `admin_id` binary(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- domestic_service_market.authorities definition

CREATE TABLE `authorities` (
  `id` binary(16) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `admin_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj2st99badkeu4s8ovxmbnfd0c` (`admin_id`),
  CONSTRAINT `FKj2st99badkeu4s8ovxmbnfd0c` FOREIGN KEY (`admin_id`) REFERENCES `admin_table` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;