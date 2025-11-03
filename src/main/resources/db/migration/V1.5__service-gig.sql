CREATE TABLE `gigs`(

    `id` binary(16) NOT NULL,

    `title` VARCHAR(255) DEFAULT NULL,

    `short_description` VARCHAR(512) DEFAULT NULL,

    `base_price` double DEFAULT NULL,

    `price_type` VARCHAR(255) DEFAULT NULL,

    `duration_by_hours` double DEFAULT NULL,

    `currency` CHAR(3) DEFAULT 'LKR',

    `is_active` Boolean DEFAULT TRUE,

    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `category_id` binary(16) NOT NULL,
    `provider_id` binary(16) NOT NULL,
     CONSTRAINT `FK_category_gigs` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
     CONSTRAINT `Fk_providers_gigs` FOREIGN KEY (`provider_id`) REFERENCES `service_providers` (`id`)

)
