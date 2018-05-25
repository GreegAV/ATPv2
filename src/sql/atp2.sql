drop database IF EXISTS  atp2;
CREATE SCHEMA IF NOT EXISTS `atp2`;
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
AUTO_INCREMENT = 0;


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
    REFERENCES `atp2`.`bus` (`busID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0;


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
    REFERENCES `atp2`.`bus` (`busID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0;

INSERT INTO `atp2`.`bus` (`busID`, `busName`) VALUES ('0', 'Admin bus');
UPDATE `atp2`.`bus` SET `busID`='0' WHERE `busID`='1';

INSERT INTO `atp2`.`route` (`routeID`, `routeName`, `bus_busID`) VALUES ('0', 'Admin route', '0');
UPDATE `atp2`.`route` SET `routeID`='0' WHERE `routeID`='1';

INSERT INTO `atp2`.`driver` (`userID`, `driverName`, `driverPassword`, `bus_busID`, `confirmed`) VALUES ('0', 'Admin', 'nimda', '0',  '1');
UPDATE `atp2`.`driver` SET `userID`='0' WHERE `userID`='1';

