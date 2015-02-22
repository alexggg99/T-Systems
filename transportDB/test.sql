-- MySQL Script generated by MySQL Workbench
-- Пт. 20 февр. 2015 16:17:12
-- Model: New Model    Version: 1.0
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema transportDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `transportDB` DEFAULT CHARACTER SET latin1 ;
USE `transportDB` ;

-- -----------------------------------------------------
-- Table `transportDB`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportDB`.`roles` (
  `roles_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`roles_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `transportDB`.`passengers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportDB`.`passengers` (
  `passenger_id` INT(11) NOT NULL,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `middleName` VARCHAR(45) NULL DEFAULT NULL,
  `lastName` VARCHAR(45) NULL DEFAULT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  `role_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`passenger_id`),
  INDEX `fk_role_id_idx` (`role_id` ASC),
  CONSTRAINT `fk_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `transportDB`.`roles` (`roles_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `transportDB`.`routes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportDB`.`routes` (
  `route_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `train_train_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`route_id`),
INDEX `fk_train_train_id_idx` (`train_train_id` ASC),
  CONSTRAINT `fk_train_id`
    FOREIGN KEY (`train_train_id`)
    REFERENCES `transportDB`.`trains` (`train_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `transportDB`.`stations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportDB`.`stations` (
  `station_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`station_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `transportDB`.`routeEntity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportDB`.`routeEntity` (
  `routeEntity_id` INT(11) NOT NULL,
  `route_id` INT(11) NULL DEFAULT NULL,
  `station_id` INT(11) NULL DEFAULT NULL,
  `arrival_time` DATETIME NULL DEFAULT NULL,
  `depature_time` DATETIME NULL DEFAULT NULL,
  `seqNumber` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`routeEntity_id`),
  INDEX `fk_route_id_idx` (`route_id` ASC),
  INDEX `fk_station_id_idx` (`station_id` ASC),
  CONSTRAINT `fk_route_id`
    FOREIGN KEY (`route_id`)
    REFERENCES `transportDB`.`routes` (`route_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_station_id`
    FOREIGN KEY (`station_id`)
    REFERENCES `transportDB`.`stations` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `transportDB`.`trains`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportDB`.`trains` (
  `train_id` INT(11) NOT NULL,
  `seats` INT(11) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`train_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `transportDB`.`tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transportDB`.`tickets` (
  `ticket_id` INT(11) NOT NULL,
  `passenger_id` INT(11) NOT NULL,
  `train_id` INT(11) NOT NULL,
  `route_id` INT(11) NOT NULL,
  `depature_time` DATETIME NOT NULL,
  `station_from` INT(11) NOT NULL,
  `station_to` INT(11) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  INDEX `fk_tickets_1_idx` (`station_from` ASC),
  INDEX `fk_tickets_1_idx1` (`station_to` ASC),
  INDEX `fk_route_id_idx` (`route_id` ASC),
  INDEX `fk_passenger_id_idx` (`passenger_id` ASC),
  INDEX `fk_train_id_idx` (`train_id` ASC),
  CONSTRAINT `fk_passenger_id`
    FOREIGN KEY (`passenger_id`)
    REFERENCES `transportDB`.`passengers` (`passenger_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_1`
    FOREIGN KEY (`train_id`)
    REFERENCES `transportDB`.`trains` (`train_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_2`
    FOREIGN KEY (`route_id`)
    REFERENCES `transportDB`.`routes` (`route_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_3`
    FOREIGN KEY (`station_from`)
    REFERENCES `transportDB`.`stations` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tickets_4`
    FOREIGN KEY (`station_to`)
    REFERENCES `transportDB`.`stations` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE TABLE IF NOT EXISTS `transportDB`.`SEQUENCE_TABLE` (
  `SEQ_NAME` VARCHAR(45) NULL,
  `SEQ_COUNT` INT NULL);
INSERT INTO SEQUENCE_TABLE (SEQ_NAME, SEQ_COUNT) VALUES ("ST_SEQ", 0);
INSERT INTO SEQUENCE_TABLE (SEQ_NAME, SEQ_COUNT) VALUES ("RL_SEQ", 0);
INSERT INTO SEQUENCE_TABLE (SEQ_NAME, SEQ_COUNT) VALUES ("PS_SEQ", 0);
INSERT INTO SEQUENCE_TABLE (SEQ_NAME, SEQ_COUNT) VALUES ("RT_SEQ", 0);
INSERT INTO SEQUENCE_TABLE (SEQ_NAME, SEQ_COUNT) VALUES ("EN_SEQ", 0);
INSERT INTO SEQUENCE_TABLE (SEQ_NAME, SEQ_COUNT) VALUES ("TK_SEQ", 0);
INSERT INTO SEQUENCE_TABLE (SEQ_NAME, SEQ_COUNT) VALUES ("TR_SEQ", 0);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
