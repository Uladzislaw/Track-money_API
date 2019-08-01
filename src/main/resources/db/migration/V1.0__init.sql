use trackmoneydb;

CREATE TABLE `users` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `email` varchar(255) NOT NULL UNIQUE,
                         `password` varchar(255) NOT NULL,
                         `role` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`)
);

CREATE TABLE `categories` (
                              `id` INT NOT NULL AUTO_INCREMENT,
                              `name` varchar(50) NOT NULL UNIQUE,
                              `type` varchar(50) NOT NULL UNIQUE,
                              `is_ugly` BOOLEAN NOT NULL DEFAULT '0',
                              `u_id` INT NOT NULL DEFAULT '0',
                              PRIMARY KEY (`id`)
);

CREATE TABLE `reports` (
                           `id` INT NOT NULL AUTO_INCREMENT,
                           `income` DECIMAL NOT NULL,
                           `total_expenses` DECIMAL NOT NULL,
                           `expense_on_category` DECIMAL NOT NULL,
                           `period_id` INT NOT NULL,
                           `u_id` INT NOT NULL,
                           PRIMARY KEY (`id`)
);

CREATE TABLE `period` (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `beginning` DATE NOT NULL,
                          `end` DATE NOT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `currency` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `name` varchar(15) NOT NULL UNIQUE,
                            PRIMARY KEY (`id`)
);

CREATE TABLE `user_info` (
                             `id` INT NOT NULL AUTO_INCREMENT,
                             `u_id` INT NOT NULL,
                             `currency_id` INT NOT NULL,
                             PRIMARY KEY (`id`)
);

CREATE TABLE `consumption` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `amount` DECIMAL NOT NULL,
                               `addition_date` DATE NOT NULL,
                               `u_id` INT NOT NULL,
                               `category_id` INT NOT NULL,
                               PRIMARY KEY (`id`)
);

CREATE TABLE `notifications` (
                                 `id` INT NOT NULL AUTO_INCREMENT,
                                 `interval` INT NOT NULL,
                                 `message` varchar(255) NOT NULL UNIQUE,
                                 `category_id` INT NOT NULL,
                                 PRIMARY KEY (`id`)
);

ALTER TABLE `categories` ADD CONSTRAINT `categories_fk0` FOREIGN KEY (`u_id`) REFERENCES `users`(`id`);

ALTER TABLE `reports` ADD CONSTRAINT `reports_fk0` FOREIGN KEY (`period_id`) REFERENCES `period`(`id`);

ALTER TABLE `reports` ADD CONSTRAINT `reports_fk1` FOREIGN KEY (`u_id`) REFERENCES `users`(`id`);

ALTER TABLE `user_info` ADD CONSTRAINT `user_info_fk0` FOREIGN KEY (`u_id`) REFERENCES `users`(`id`);

ALTER TABLE `user_info` ADD CONSTRAINT `user_info_fk1` FOREIGN KEY (`currency_id`) REFERENCES `currency`(`id`);

ALTER TABLE `user_info` ADD CONSTRAINT `user_info_fk2` FOREIGN KEY (`current_period`) REFERENCES `period`(`id`);

ALTER TABLE `consumption` ADD CONSTRAINT `consumption_fk0` FOREIGN KEY (`u_id`) REFERENCES `users`(`id`);

ALTER TABLE `consumption` ADD CONSTRAINT `consumption_fk1` FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`);

ALTER TABLE `notifications` ADD CONSTRAINT `notifications_fk0` FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`);
