ALTER TABLE `booking`
ADD COLUMN `client_id` binary(16) NOT NULL,
ADD CONSTRAINT `fk_client_booking`
FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)