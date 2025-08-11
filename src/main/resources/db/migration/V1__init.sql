CREATE TABLE `client_profile_pic` (
  `id` binary(16) NOT NULL,
  `profile_pic_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- domestic_service_market.client definition

CREATE TABLE `client` (
  `id` binary(16) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `profile_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKlkd3e78xqx99cu3djqyua2561` (`profile_id`),
  CONSTRAINT `FK3qf7bne3y3qfpkx7me32e0yxu` FOREIGN KEY (`profile_id`) REFERENCES `client_profile_pic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- domestic_service_market.service_providers definition

CREATE TABLE `service_providers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contact_no` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expertise` varchar(255) DEFAULT NULL,
  `hourly_rate` double DEFAULT NULL,
  `is_verified` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- domestic_service_market.service definition

CREATE TABLE `service` (
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `provider_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKndvit4lpw4u2gnii4pxdlr5nj` (`provider_id`),
  CONSTRAINT `FKndvit4lpw4u2gnii4pxdlr5nj` FOREIGN KEY (`provider_id`) REFERENCES `service_providers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- domestic_service_market.payment definition

CREATE TABLE `payment` (
  `id` binary(16) NOT NULL,
  `amount` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `time_stamp` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- domestic_service_market.booking definition

CREATE TABLE `booking` (
  `id` binary(16) NOT NULL,
  `date` date DEFAULT NULL,
  `ending_time` time(6) DEFAULT NULL,
  `starting_time` time(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `payment_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKxcv4bjb631pysj91ybp40vpo` (`payment_id`),
  CONSTRAINT `FK70t92vvx289ayx2hq2v4hdcjl` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;