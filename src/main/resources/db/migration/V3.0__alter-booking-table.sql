ALTER TABLE `booking` DROP COLUMN `payment_id`;
ALTER TABLE `booking` ADD UNIQUE(`starting_time`);
ALTER TABLE `booking` ADD UNIQUE(`starting_date`);