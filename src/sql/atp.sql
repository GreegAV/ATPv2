drop database atp;
CREATE SCHEMA `atp` ;

CREATE TABLE IF NOT EXISTS `atp`.`driver` (
  `userID` INT(11) NOT NULL AUTO_INCREMENT,
  `driverName` VARCHAR(45) NULL DEFAULT NULL,
  `driverPassword` VARCHAR(45) NULL DEFAULT NULL,
  `routeID` INT(11) NULL DEFAULT NULL,
  `busID` INT(11) NULL DEFAULT NULL,
  `confirmed` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `userID` (`userID` ASC))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0;

INSERT INTO `atp`.`driver` (`userID`, `driverName`, `driverPassword`, `routeID`, `busID`, `confirmed`) VALUES ('0', 'Admin', 'nimda', '0', '0', '1');
UPDATE `atp`.`driver` SET `userID`='0' WHERE `userID`='1';

CREATE TABLE IF NOT EXISTS `atp`.`bus` (
  `busID` INT(11) NOT NULL AUTO_INCREMENT,
  `busName` VARCHAR(45) NULL DEFAULT NULL,
  `assignedDriver` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`busID`),
  UNIQUE INDEX `busID` (`busID` ASC),
  INDEX `driverID_idx` (`assignedDriver` ASC),
  CONSTRAINT `driverID`
  FOREIGN KEY (`assignedDriver`)
  REFERENCES `atp`.`driver` (`userID`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0;

INSERT INTO `atp`.`bus` (`busID`, `busName`, `assignedDriver`) VALUES ('0', 'Admin bus', '0');
UPDATE `atp`.`bus` SET `busID`='0' WHERE `busID`='1';


CREATE TABLE IF NOT EXISTS `atp`.`route` (
  `routeID` INT(11) NOT NULL AUTO_INCREMENT,
  `routeName` VARCHAR(45) NULL DEFAULT NULL,
  `assignedBus` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`routeID`),
  UNIQUE INDEX `routeID` (`routeID` ASC),
  INDEX `busID_idx` (`assignedBus` ASC),
  CONSTRAINT `busID`
  FOREIGN KEY (`assignedBus`)
  REFERENCES `atp`.`bus` (`busID`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 0;

INSERT INTO `atp`.`route` (`routeID`, `routeName`, `assignedBus`) VALUES ('0', 'Admin route', '0');
UPDATE `atp`.`route` SET `routeID`='0' WHERE `routeID`='1';