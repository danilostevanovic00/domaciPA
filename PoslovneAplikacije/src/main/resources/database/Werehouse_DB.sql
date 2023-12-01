-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Warehouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Warehouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Warehouse` DEFAULT CHARACTER SET utf8 ;
USE `Warehouse` ;

-- -----------------------------------------------------
-- Table `Warehouse`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse`.`customer` (
  `customer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `contact_person` VARCHAR(64) NOT NULL,
  `address` VARCHAR(64) NOT NULL,
  `city` VARCHAR(64) NOT NULL,
  `post_code` INT NOT NULL,
  `country` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Warehouse`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse`.`employee` (
  `employee_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `lastname` VARCHAR(64) NOT NULL,
  `firstname` VARCHAR(64) NOT NULL,
  `birth_date` DATE NOT NULL,
  PRIMARY KEY (`employee_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Warehouse`.`supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse`.`supplier` (
  `supplier_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `contact_person` VARCHAR(64) NOT NULL,
  `address` VARCHAR(64) NOT NULL,
  `city` VARCHAR(64) NOT NULL,
  `country` VARCHAR(64) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`supplier_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Warehouse`.`shipper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse`.`shipper` (
  `shipper_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`shipper_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Warehouse`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse`.`product` (
  `product_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `product_category` VARCHAR(64) NOT NULL,
  `price_per_unit` INT NOT NULL,
  `supplier_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `fk_product_supplier_id_idx` (`supplier_id` ASC) ,
  CONSTRAINT `fk_product_supplier_id`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `Warehouse`.`supplier` (`supplier_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Warehouse`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse`.`order` (
  `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_date` DATE NOT NULL ,
  `customer_id` INT UNSIGNED NOT NULL,
  `employee_id` INT UNSIGNED NOT NULL,
  `shipper_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_order_customer_id_idx` (`customer_id` ASC),
  INDEX `fk_order_employee_id_idx` (`employee_id` ASC),
  INDEX `fk_order_shipper_id_idx` (`shipper_id` ASC),
  CONSTRAINT `fk_order_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `Warehouse`.`customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_employee_id`
    FOREIGN KEY (`employee_id`)
    REFERENCES `Warehouse`.`employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_shipper_id`
    FOREIGN KEY (`shipper_id`)
    REFERENCES `Warehouse`.`shipper` (`shipper_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Warehouse`.`order_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse`.`order_detail` (
  `order_detail_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` INT UNSIGNED NOT NULL,
  `product_id` INT UNSIGNED NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  INDEX `fk_order_detail_order_id_idx` (`order_id` ASC),
  INDEX `fk-order_detail_product_id_idx` (`product_id` ASC),
  CONSTRAINT `fk_order_detail_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `Warehouse`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk-order_detail_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `Warehouse`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
