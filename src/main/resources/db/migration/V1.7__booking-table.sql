ALTER TABLE booking ADD COLUMN `ending_date` date;
ALTER TABLE booking RENAME COLUMN date To starting_date;