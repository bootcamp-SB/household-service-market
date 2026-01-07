ALTER TABLE booking
DROP FOREIGN KEY FK70t92vvx289ayx2hq2v4hdcjl,
DROP INDEX UKxcv4bjb631pysj91ybp40vpo;

ALTER TABLE booking
ADD COLUMN `gig_id` BINARY(16) NOT NULL,
ADD CONSTRAINT `Fk_booking_gig`
FOREIGN KEY (`gig_id`) REFERENCES `gigs`(`id`);

ALTER TABLE booking
DROP COLUMN `ending_time`;

ALTER TABLE booking
DROP COLUMN `ending_date`