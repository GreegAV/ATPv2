CREATE SCHEMA `atp` ;

CREATE TABLE `atp`.`bus` (
  `busID` INT NOT NULL,
  `busName` VARCHAR(45) NOT NULL,
  `driverID` INT NULL,
  `routeID` INT NULL,
  PRIMARY KEY (`busID`));

CREATE TABLE `atp`.`route` (
  `routeID` INT NOT NULL,
  `routeName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`routeID`));

CREATE TABLE `atp`.`driver` (
  `userID` INT NOT NULL,
  `driverName` VARCHAR(45) NOT NULL,
  `routeID` INT NOT NULL,
  `busID` INT NOT NULL,
  `confirmed` TINYINT NULL,
  PRIMARY KEY (`userID`));

ALTER TABLE `atp`.`driver` 
CHANGE COLUMN `confirmed` `confirmed` TINYINT(4) NULL DEFAULT 0 ;

ALTER TABLE `atp`.`driver` 
ADD INDEX `routeID_idx` (`routeID` ASC);
ALTER TABLE `atp`.`driver` 
ADD CONSTRAINT `routeID`
  FOREIGN KEY (`routeID`)
  REFERENCES `atp`.`route` (`routeID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `atp`.`driver` 
ADD INDEX `busID_idx` (`busID` ASC);
ALTER TABLE `atp`.`driver` 
ADD CONSTRAINT `busID`
  FOREIGN KEY (`busID`)
  REFERENCES `atp`.`bus` (`busID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

