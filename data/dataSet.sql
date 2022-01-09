CREATE TABLE `codeexdemo`.`users` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `codeexdemo`.`task` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `Item` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_task_1` FOREIGN KEY `FK_task_1` (`id`)
    REFERENCES `users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)

ALTER TABLE `codeexdemo`.`task` CHANGE COLUMN `Item` `subject` VARCHAR(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
 ADD COLUMN `date` VARCHAR(45) NOT NULL AFTER `user_id`;

ALTER TABLE `codeexdemo`.`users` RENAME TO `codeexdemo`.`user`;
ALTER TABLE `codeexdemo`.`task` CHANGE COLUMN `user_id` `userId` VARCHAR(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;