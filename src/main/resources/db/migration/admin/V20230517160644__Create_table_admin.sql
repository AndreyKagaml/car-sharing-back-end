CREATE TABLE IF NOT EXISTS `admin`
(
    `id`         INT(11)      NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50)  NOT NULL,
    `last_name`  VARCHAR(50)  NOT NULL,
    `email`      VARCHAR(100) NOT NULL UNIQUE,
    `password`   VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
);
