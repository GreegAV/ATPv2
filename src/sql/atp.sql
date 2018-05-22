drop database atp;
CREATE SCHEMA `atp` ;

CREATE TABLE `atp`.`bus` (
  `busID` INT unique AUTO_INCREMENT,
  `busName` VARCHAR(45) NULL,
  `driverID` INT NULL,
  `routeID` INT NULL,
  PRIMARY KEY (`busID`));


CREATE TABLE `atp`.`route` (
  `routeID` int unique AUTO_INCREMENT,
  `routeName` VARCHAR(45) NULL,
  `assigned2bus` int null,  
  `assigned2driver` int null, 
  PRIMARY KEY (`routeID`));


CREATE TABLE `atp`.`driver` (
  `userID` INT unique AUTO_INCREMENT,
  `driverName` VARCHAR(45) NULL,
  `driverPassword` VARCHAR(45) NULL ,
  `routeID` INT NULL,
  `busID` INT NULL,
  `confirmed` INT NULL DEFAULT 0,
  PRIMARY KEY (`userID`));


INSERT INTO `atp`.`driver` (`userID`,`driverName`, `driverPassword`, `routeID`, `busID`, `confirmed`) VALUES ('0', 'Admin', 'nimda', '0', '0', '1');
UPDATE `atp`.`driver` SET `userID`='0' WHERE `userID`='1';
INSERT INTO `atp`.`bus` (`busID`, `busName`, `driverID`, `routeID`) VALUES ('0', 'Admin bus', '0', '0');
UPDATE `atp`.`bus` SET `busID`='0' WHERE `busID`='1';
INSERT INTO `atp`.`route` (`routeID`, `routeName`, `assigned2bus`, `assigned2driver`) VALUES ('0', 'Admin route', '0', '0');
UPDATE `atp`.`route` SET `routeID`='0' WHERE `routeID`='1';
