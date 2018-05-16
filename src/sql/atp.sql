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


ALTER TABLE `atp`.`driver`
ADD COLUMN `driverPassword` VARCHAR(45) NOT NULL AFTER `driverName`;


ALTER TABLE `atp`.`driver`
CHANGE COLUMN `confirmed` `confirmed` INT NULL ;

INSERT INTO `atp`.`bus` (`busID`, `busName`, `driverID`, `routeID`) VALUES ('0', 'AdminBus', '0', '0');

INSERT INTO `atp`.`route` (`routeID`, `routeName`) VALUES ('0', 'AdminRoute');

INSERT INTO `atp`.`driver` (`userID`, `driverName`, `driverPassword`, `routeID`, `busID`, `confirmed`) VALUES ('0', 'admin', 'nimda', '0', '0', '0');

