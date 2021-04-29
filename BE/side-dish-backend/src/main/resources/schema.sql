drop table IF EXISTS `item`;
drop table IF EXISTS `category`;

CREATE TABLE IF NOT EXISTS `sidedish`.`category` (
                                                     `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                                                     `name` VARCHAR(45) NOT NULL,
                                                     `best` TINYINT(1)
);

CREATE TABLE IF NOT EXISTS `sidedish`.`item` (
		`id` BIGINT PRIMARY KEY AUTO_INCREMENT,
		`title` VARCHAR(45) NOT NULL,
		`description` VARCHAR(200) NOT NULL,
		`normal_price` INT NOT NULL,
		`sale_price` INT NOT NULL,
		`badges` VARCHAR(45) NULL,
		`delivery_types` VARCHAR(200) NULL,
		`images` VARCHAR(2000) NULL,
		`item_id` BIGINT,
		`category` INT REFERENCES category(`id`)
);
