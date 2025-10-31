CREATE TABLE `provider_poster`(
    `poster_id` binary(16) NOT NULL,
    `topic` VARCHAR(255) DEFAULT NULL,
    `poster_img` VARCHAR(255) DEFAULT NULL,
    `hourly_rate` DOUBLE DEFAULT NULL,
    `provider_id` int DEFAULT NULL,
    PRIMARY KEY(`poster_id`),
    FOREIGN KEY(`provider_id`) REFERENCES `service_providers` (`id`)
    )