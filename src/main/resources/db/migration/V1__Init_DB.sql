-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cvbank_last
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cvbank_last
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cvbank_last` DEFAULT CHARACTER SET utf8 ;
USE `cvbank_last` ;

-- -----------------------------------------------------
-- Table `cvbank_last`.`activity_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`activity_type` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`position` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `post_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `post_name_UNIQUE` (`post_name` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`profile` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL DEFAULT ' ',
  `password` VARCHAR(255) NOT NULL,
  `usertype` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `position_id` BIGINT(20) NULL DEFAULT NULL,
  `company_name` VARCHAR(255) NULL DEFAULT NULL,
  `website` VARCHAR(255) NULL DEFAULT NULL,
  `country` VARCHAR(255) NULL DEFAULT NULL,
  `city_town` VARCHAR(255) NULL DEFAULT NULL,
  `street` VARCHAR(255) NULL DEFAULT NULL,
  `house_building` VARCHAR(255) NULL DEFAULT NULL,
  `postcode` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_profile__posit__position_id_idx` (`position_id` ASC),
  CONSTRAINT `fk_profile__posit__position_id`
  FOREIGN KEY (`position_id`)
  REFERENCES `cvbank_last`.`position` (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`template`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`template` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type` INT(11) NULL DEFAULT NULL,
  `color_scheme` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`cv`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`cv` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `profile_id` BIGINT(20) NOT NULL,
  `residence` VARCHAR(255) NULL DEFAULT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `summary` VARCHAR(255) NULL DEFAULT NULL,
  `about` VARCHAR(255) NULL DEFAULT NULL,
  `linkedin` VARCHAR(255) NULL DEFAULT NULL,
  `preferenced_area` VARCHAR(255) NULL DEFAULT NULL,
  `position_preference` VARCHAR(255) NULL DEFAULT NULL,
  `salary_from_preference` INT(11) NOT NULL,
  `salary_till_preference` INT(11) NOT NULL,
  `portfolio` VARCHAR(500) NULL DEFAULT NULL,
  `github` VARCHAR(255) NULL DEFAULT NULL,
  `recommendations` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `activated` TINYINT(1) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `template_id` BIGINT(20) NULL DEFAULT NULL,
  `views` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_cv__profile__profile_id_idx` (`profile_id` ASC),
  INDEX `fk_cv__templ__template_id_idx` (`template_id` ASC),
  CONSTRAINT `fk_cv__profile__profile_id`
  FOREIGN KEY (`profile_id`)
  REFERENCES `cvbank_last`.`profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cv__templ__template_id`
  FOREIGN KEY (`template_id`)
  REFERENCES `cvbank_last`.`template` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`languages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`languages` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name_lang` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_lang_UNIQUE` (`name_lang` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`cv_lang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`cv_lang` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cv_id` BIGINT(20) NULL DEFAULT NULL,
  `lang_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CV_id_idx_idx` (`cv_id` ASC),
  INDEX `FK_LANG_idx_idx` (`lang_id` ASC),
  CONSTRAINT `FK_CV_idx`
  FOREIGN KEY (`cv_id`)
  REFERENCES `cvbank_last`.`cv` (`id`),
  CONSTRAINT `FK_LANG_idx`
  FOREIGN KEY (`lang_id`)
  REFERENCES `cvbank_last`.`languages` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`skill_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`skill_group` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name_group` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`skills`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`skills` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name_skill` VARCHAR(255) NOT NULL,
  `skill_group_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_skills__skgroup__skill_group_id_idx` (`skill_group_id` ASC),
  CONSTRAINT `fk_skills__skgroup__skill_group_id`
  FOREIGN KEY (`skill_group_id`)
  REFERENCES `cvbank_last`.`skill_group` (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`cv_skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`cv_skill` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cv_id` BIGINT(20) NOT NULL,
  `skill_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cv_skil__cv__cv_id_idx` (`cv_id` ASC),
  INDEX `fk_cv_skill__skills_skill_id_idx` (`skill_id` ASC),
  CONSTRAINT `fk_cv_skill__cv__cv_id`
  FOREIGN KEY (`cv_id`)
  REFERENCES `cvbank_last`.`cv` (`id`),
  CONSTRAINT `fk_cv_skill__skills_skill_id`
  FOREIGN KEY (`skill_id`)
  REFERENCES `cvbank_last`.`skills` (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`cvactivity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`cvactivity` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `activity_type_id` BIGINT(20) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `position` VARCHAR(255) NULL DEFAULT NULL,
  `year_start` INT(11) NOT NULL,
  `year_end` INT(11) NOT NULL,
  `cv_id` BIGINT(20) NULL DEFAULT NULL,
  `back_front` INT(11) NULL DEFAULT NULL,
  `company` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cvactivity__cv__cv_id` (`cv_id` ASC),
  INDEX `fk_cvact__act_type__activity_type_id_idx` (`activity_type_id` ASC),
  CONSTRAINT `fk_cvact__act_type__activity_type_id`
  FOREIGN KEY (`activity_type_id`)
  REFERENCES `cvbank_last`.`activity_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cvactivity__cv__cv_id`
  FOREIGN KEY (`cv_id`)
  REFERENCES `cvbank_last`.`cv` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`education` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `institution` VARCHAR(255) NULL DEFAULT NULL,
  `degree` VARCHAR(100) NULL DEFAULT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL,
  `note` VARCHAR(500) NULL DEFAULT NULL,
  `cv_id` BIGINT(20) NULL DEFAULT NULL,
  `year_end` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_educ__cv__cv_id_idx` (`cv_id` ASC),
  CONSTRAINT `fk_educ__cv__cv_id`
  FOREIGN KEY (`cv_id`)
  REFERENCES `cvbank_last`.`cv` (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`experience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`experience` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `position_id` BIGINT(20) NULL DEFAULT NULL,
  `years` INT(11) NULL DEFAULT NULL,
  `total_years` INT(11) NULL DEFAULT NULL,
  `cv_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cv_id` (`cv_id` ASC),
  INDEX `fk_position_id` (`position_id` ASC),
  CONSTRAINT `fk_cv_id`
  FOREIGN KEY (`cv_id`)
  REFERENCES `cvbank_last`.`cv` (`id`),
  CONSTRAINT `fk_position_id`
  FOREIGN KEY (`position_id`)
  REFERENCES `cvbank_last`.`position` (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`folder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`folder` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name_folder` VARCHAR(255) NOT NULL,
  `profile_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_folder_profile__profile__profile_id_idx` (`profile_id` ASC),
  INDEX `fk_folder_profile__prof__profile_id_idx` (`profile_id` ASC),
  CONSTRAINT `fk_folder_prof__profile__profile_id`
  FOREIGN KEY (`profile_id`)
  REFERENCES `cvbank_last`.`profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cvbank_last`.`folder_cv`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cvbank_last`.`folder_cv` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `folder_id` BIGINT(20) NOT NULL,
  `cv_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_folder_cv__folder__folder_id_idx` (`folder_id` ASC),
  INDEX `fk_folder_cv__cv__cv_id_idx` (`cv_id` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
