
ALTER TABLE `reviews`
ADD COLUMN `service_gig_id` BINARY(16) NOT NULL,
ADD CONSTRAINT `Fk_reviews_gigs`
FOREIGN KEY (`service_gig_id`) REFERENCES `gigs`(`id`);

ALTER TABLE `reviews`
DROP FOREIGN KEY `reviews_ibfk_3`;
