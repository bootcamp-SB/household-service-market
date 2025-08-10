CREATE TABLE `admin_table` (
  `admin_id` binary(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
)