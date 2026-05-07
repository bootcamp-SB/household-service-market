ALTER TABLE `gigs` ADD COLUMN `description` VARCHAR(512);
ALTER TABLE `gigs` DROP COLUMN `full_description`;
ALTER TABLE `gigs` DROP COLUMN `short_description`;
ALTER TABLE `gigs` ADD COLUMN `total_booking_count` BIGINT;
ALTER TABLE `gigs` ADD COLUMN `service_location` VARCHAR(255);