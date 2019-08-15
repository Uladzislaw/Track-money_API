use trackmoneydb;

CREATE TABLE `users`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `email`    varchar(255) NOT NULL UNIQUE,
    `password` varchar(64)  NOT NULL,
    `state`    varchar(20)  NOT NULL
        check ( `state` in ('ACTIVE', 'INACTIVE', 'DELETED', 'BANNED')),
    `role`     varchar(50)  NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `categories`
(
    `id`      INT         NOT NULL AUTO_INCREMENT,
    `name`    varchar(50) NOT NULL UNIQUE,
    `type`    varchar(50) NOT NULL check ( `type` in ('CONSUMPTION', 'INCOME')),
    `is_ugly` BOOLEAN     NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
);

CREATE TABLE `consumption`
(
    `id`            INT            NOT NULL AUTO_INCREMENT,
    `amount`        numeric(19, 4) NOT NULL,
    `addition_date` DATE           NOT NULL,
    `currency_id`   INT            NOT NULL,
    `u_id`          INT            NOT NULL,
    `category_id`   INT            NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `categories_users`
(
    `u_id`        INT,
    `category_id` INT
);

CREATE TABLE `period`
(
    `id`        INT  NOT NULL AUTO_INCREMENT,
    `beginning` DATE NOT NULL,
    `end`       DATE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `currency`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` varchar(15) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

CREATE TABLE `notifications`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `interval`    INT          NOT NULL,
    `message`     varchar(255) NOT NULL UNIQUE,
    `category_id` INT          NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `consumption`
    ADD CONSTRAINT `consumption_fk0` FOREIGN KEY (`u_id`) REFERENCES `users` (`id`);

ALTER TABLE `consumption`
    ADD CONSTRAINT `consumption_fk1` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`);

ALTER TABLE `consumption`
    ADD CONSTRAINT `consumption_fk2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

ALTER TABLE `notifications`
    ADD CONSTRAINT `notifications_fk0` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

ALTER TABLE `categories_users`
    ADD CONSTRAINT `categories_users_fk0` FOREIGN KEY (`u_id`) REFERENCES `users` (`id`);

ALTER TABLE `categories_users`
    ADD CONSTRAINT `categories_users_fk1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);
