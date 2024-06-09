# BP-api-client
Prueba BP

# Archivo Collections Postman
[Evaluacion-cuentas.postman_collection.json](https://github.com/user-attachments/files/15754456/Evaluacion-cuentas.postman_collection.json)

# Base de datos .sql

CREATE SCHEMA IF NOT EXISTS `BPcuenta2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `BPcuenta2` ;

-- -----------------------------------------------------
-- Table `BPcuenta2`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BPcuenta2`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `account_number` VARCHAR(255) NOT NULL,
  `client_id` INT NOT NULL,
  `initial_balance` DOUBLE NOT NULL,
  `status` BIT(1) NOT NULL,
  `type_account` ENUM('AHORROS', 'CORRIENTE') NOT NULL,
  PRIMARY KEY (`account_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `BPcuenta2`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BPcuenta2`.`client` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NOT NULL,
  `age` INT NOT NULL,
  `generic` ENUM('FEMENINO', 'MASCULINO') NOT NULL,
  `identification` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `status` BIT(1) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `UK_powwvjq5dtrded35jufhbmcsd` (`identification` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `BPcuenta2`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BPcuenta2`.`transaction` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `balance` DOUBLE NOT NULL,
  `date` DATETIME(6) NOT NULL,
  `type_transaction` ENUM('DEPOSITO', 'RETIRO') NOT NULL,
  `value` DOUBLE NOT NULL,
  `account_account_id` INT NOT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `FKb2k3fuaawcfaq6a3kncjhdsr4` (`account_account_id` ASC) VISIBLE,
  CONSTRAINT `FKb2k3fuaawcfaq6a3kncjhdsr4`
    FOREIGN KEY (`account_account_id`)
    REFERENCES `BPcuenta2`.`account` (`account_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
 BPcuenta2.sql…]()


