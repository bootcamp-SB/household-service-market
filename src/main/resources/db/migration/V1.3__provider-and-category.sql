CREATE TABLE `provider_category`(
    `provider_id` binary(16) NOT NULL,
    `category_id` binary(16) NOT NULL,
    PRIMARY KEY (`provider_id`,`category_id`),
    FOREIGN KEY (`provider_id`) REFERENCES `service_providers` (`id`),
    FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
)