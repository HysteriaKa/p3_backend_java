

CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(200),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(1000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(1000),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE `USERS` ADD FOREIGN KEY (`id`) REFERENCES `RENTALS` (`owner_id`);

ALTER TABLE `USERS` ADD FOREIGN KEY (`id`) REFERENCES `MESSAGES` (`user_id`);

ALTER TABLE `RENTALS` ADD FOREIGN KEY (`id`) REFERENCES `MESSAGES` (`rental_id`);
