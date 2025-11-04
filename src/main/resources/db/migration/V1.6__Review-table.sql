CREATE TABLE `reviews`(
    `id` binary(16) NOT NULL,

    `rating` double DEFAULT NULL,

    `comment` VARCHAR(512) DEFAULT NULL,

    `provider_response` VARCHAR(512) DEFAULT NULL,

    `created_at` DATE DEFAULT NULL,

    `booking_id` binary(16) NOT NULL,

    `client_id` binary(16) NOT NULL,

    `provider_id` binary(16) NOT NULL,

    FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
    FOREIGN KEY (`provider_id`) REFERENCES `service_providers` (`id`),
    FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)

)