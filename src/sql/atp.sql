-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema atp2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema atp2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `atp2` DEFAULT CHARACTER SET utf8mb4 ;
USE `atp2` ;

-- -----------------------------------------------------
-- Table `atp2`.`bus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atp2`.`bus` (
  `busID` INT(11) NOT NULL AUTO_INCREMENT,
  `busName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`busID`),
  UNIQUE INDEX `busID` (`busID` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `atp2`.`driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atp2`.`driver` (
  `userID` INT(11) NOT NULL AUTO_INCREMENT,
  `driverName` VARCHAR(45) NULL DEFAULT NULL,
  `driverPassword` VARCHAR(45) NULL DEFAULT NULL,
  `bus_busID` INT(11) NOT NULL,
  `confirmed` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `userID` (`userID` ASC),
  INDEX `fk_driver_bus1_idx` (`bus_busID` ASC),
  CONSTRAINT `fk_driver_bus1`
    FOREIGN KEY (`bus_busID`)
    REFERENCES `atp2`.`bus` (`busID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `atp2`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atp2`.`route` (
  `routeID` INT(11) NOT NULL AUTO_INCREMENT,
  `routeName` VARCHAR(45) NULL DEFAULT NULL,
  `bus_busID` INT(11) NOT NULL,
  PRIMARY KEY (`routeID`),
  UNIQUE INDEX `routeID` (`routeID` ASC),
  INDEX `fk_route_bus_idx` (`bus_busID` ASC),
  CONSTRAINT `fk_route_bus`
    FOREIGN KEY (`bus_busID`)
    REFERENCES `atp2`.`bus` (`busID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
