-- MySQL Script generated by MySQL Workbench
-- 10/27/14 10:53:30
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema dudente
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dudente` ;

-- -----------------------------------------------------
-- Schema dudente
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dudente` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `dudente` ;

-- -----------------------------------------------------
-- Table `Plano`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Plano` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Plano` (
  `id_Plano` INT NOT NULL,
  `nome_Plano` VARCHAR(60) NOT NULL,
  `dt_Val_Plano` DATETIME NOT NULL,
  PRIMARY KEY (`id_Plano`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Paciente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Paciente` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Paciente` (
  `id_Paciente` INT NOT NULL,
  `nome_Paciente` VARCHAR(45) NOT NULL,
  `idade_Paciente` INT NULL,
  `plano_Id_Plano` INT NOT NULL,
  PRIMARY KEY (`id_Paciente`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Medicamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Medicamento` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Medicamento` (
  `id_Medicamento` INT NOT NULL,
  `nome_Medicamento` VARCHAR(60) NOT NULL,
  `dt_Val_Medicamento` DATETIME NOT NULL,
  PRIMARY KEY (`id_Medicamento`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Aparelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Aparelho` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Aparelho` (
  `id_Aparelho` INT NOT NULL,
  `nome_Aparelho` VARCHAR(60) NOT NULL,
  `dt_Man_Aparelho` DATETIME NULL,
  PRIMARY KEY (`id_Aparelho`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Compra` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Compra` (
  `id_Compra` INT NOT NULL,
  `dt_Compra` DATETIME NOT NULL,
  `val_Compra` DOUBLE NULL,
  `medicamento_id_Medicamento` INT NOT NULL,
  `aparelho_id_Aparelho` INT NOT NULL,
  PRIMARY KEY (`id_Compra`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Funcionario` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Funcionario` (
  `id_Funcionario` INT NOT NULL,
  `nome_Funcionario` VARCHAR(60) NOT NULL,
  `salario_Funcionario` DOUBLE NULL,
  `cargo_Funcionario` VARCHAR(60) NULL,
  `bonificacao_Funcionario` DOUBLE NULL,
  `especializacao_Funcionario` VARCHAR(60) NULL,
  PRIMARY KEY (`id_Funcionario`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Procedimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Procedimento` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Procedimento` (
  `id_Procedimento` INT NOT NULL,
  `nome_Procedimento` VARCHAR(60) NOT NULL,
  `tipo_Procedimento` VARCHAR(1) NULL,
  `dc_Procedimento` VARCHAR(500) NULL,
  `dt_Procedimento` DATETIME NULL,
  `obs_Procedimento` VARCHAR(500) NULL,
  `diagnostico_Procedimento` VARCHAR(500) NULL,
  `tipo_Cir_Procedimento` VARCHAR(100) NULL,
  `tipo_Ort_Odont_Procedimento` VARCHAR(100) NULL,
  `resultado_Procedimento` VARCHAR(500) NULL,
  `` id_Paciente` INT NOT NULL,
  `id_FuncionarioINT NOT NULL,
  PRIMARY KEY (`id_Procedimento`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Clinica`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Clinica` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Clinica` (
  `id_Clinica` INT NOT NULL,
  `nome_Clinica` VARCHAR(60) NOT NULL,
  `endereco_Clinica` VARCHAR(60) NULL,
  `id_Compra` INT NOT NULL,
  `id_Procedimento` INT NOT NULL,
  `id_Paciente` INT NOT NULL,
  `id_Funcionario` INT NOT NULL,
  PRIMARY KEY (`id_Clinica`))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
