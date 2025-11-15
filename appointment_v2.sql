-- ==============================================================
-- CONFIG DATABASE
-- ==============================================================
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- ==============================================================
-- DROP AND CREATE DATABASE
-- ==============================================================
DROP DATABASE IF EXISTS `appointment_springmvc`;
CREATE DATABASE `appointment_springmvc` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `appointment_springmvc`;

-- ==============================================================
-- TABLE FACULTIES
-- ==============================================================
CREATE TABLE IF NOT EXISTS `faculties` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE ROLES
-- ==============================================================
CREATE TABLE IF NOT EXISTS `roles` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE HOSPITAL
-- ==============================================================
CREATE TABLE IF NOT EXISTS `hospital` (
    `id` VARCHAR(255) NOT NULL UNIQUE,
    `name` VARCHAR(165),
    `type` TINYINT COMMENT '1: Bệnh viện công, 2: Tư nhân, 3: Quốc tế',
    `director_name` VARCHAR(255),
    `tax_code` VARCHAR(255),
    `website` VARCHAR(255),
    `email` VARCHAR(255),
    `contact_phone` VARCHAR(255),
    `license_number` VARCHAR(255),
    `fax` VARCHAR(255),
    `established_date` DATE,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `is_updated` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE BRANCH_HOSPITAL
-- ==============================================================
CREATE TABLE IF NOT EXISTS `branch_hospital` (
    `id` VARCHAR(255) NOT NULL UNIQUE,
    `branch_name` VARCHAR(255),
    `hospital_id` VARCHAR(255),
    `director_branch` VARCHAR(255),
    `code` VARCHAR(255),
    `contact_phone` VARCHAR(255),
    `type` VARCHAR(255) COMMENT 'Loại chi nhánh: tổng quát, chuyên khoa, xét nghiệm...',
    `status` TINYINT DEFAULT 1 COMMENT '1: Hoạt động, 2: Tạm ngưng, 3: Ngừng hoạt động',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `is_updated` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE ACCOUNTS
-- ==============================================================
CREATE TABLE IF NOT EXISTS `accounts` (
    `id` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `email` VARCHAR(255) UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `gender` TINYINT,
    `dob` DATE,
    `faculty_id` INT,
    `branch_hospital_id` VARCHAR(255),
    `security_code` VARCHAR(255) NOT NULL,
    `status` BOOLEAN COMMENT 'TRUE: active, FALSE: inactive',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `is_updated` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE ROLE_ACCOUNT
-- ==============================================================
CREATE TABLE IF NOT EXISTS `role_account` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `account_id` VARCHAR(255) NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE ADDRESS
-- ==============================================================
CREATE TABLE IF NOT EXISTS `address` (
    `id` VARCHAR(255) NOT NULL,
    `account_id` VARCHAR(255),
    `branch_hospital_id` VARCHAR(255),
    `hospital_id` VARCHAR(255),
    `province` VARCHAR(255),
    `district` VARCHAR(255),
    `ward` VARCHAR(255),
    `address_number` VARCHAR(255),
    `street` VARCHAR(255),
    `type` TINYINT COMMENT '1: Người dùng, 2: Bệnh viện, 3: Chi nhánh',
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE PHONES
-- ==============================================================
CREATE TABLE IF NOT EXISTS `phones` (
    `id` VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(20),
    `account_id` VARCHAR(255),
    `hospital_id` VARCHAR(255),
    `branch_hospital_id` VARCHAR(255),
    `type` TINYINT COMMENT '1: Người dùng, 2: Bệnh viện, 3: Chi nhánh',
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE IMAGES
-- ==============================================================
CREATE TABLE IF NOT EXISTS `images` (
    `id` VARCHAR(255) NOT NULL,
    `account_id` VARCHAR(255),
    `path_name` VARCHAR(255),
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE MESSAGES
-- ==============================================================
CREATE TABLE IF NOT EXISTS `messages` (
    `id` VARCHAR(255) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE CHATS
-- ==============================================================
CREATE TABLE IF NOT EXISTS `chats` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `sender_id` VARCHAR(255),
    `reciever_id` VARCHAR(255),
    `message_id` VARCHAR(255),
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE APPOINTMENTS
-- ==============================================================
CREATE TABLE IF NOT EXISTS `appointments` (
    `id` VARCHAR(255) NOT NULL,
    `doctor_id` VARCHAR(255),
    `patient_id` VARCHAR(255),
    `faculty_id` INT,
    `branch_hospital_id` VARCHAR(255),
    `date` DATE,
    `time` TIME,
    `content` VARCHAR(255),
    `status` TINYINT COMMENT '1: Reject, 2: Accepted, 3: Done',
    `appointment_type` VARCHAR(255),
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `is_updated` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE ALERTS
-- ==============================================================
CREATE TABLE IF NOT EXISTS `alerts` (
    `id` VARCHAR(255) NOT NULL,
    `doctor_id` VARCHAR(255),
    `patient_id` VARCHAR(255),
    `content` VARCHAR(255),
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE NOTES
-- ==============================================================
CREATE TABLE IF NOT EXISTS `notes` (
    `id` VARCHAR(255) NOT NULL,
    `doctor_id` VARCHAR(255),
    `appointment_id` VARCHAR(255),
    `content` TEXT,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `is_updated` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE REPLIES
-- ==============================================================
CREATE TABLE IF NOT EXISTS `replies` (
    `id` VARCHAR(255) NOT NULL,
    `doctor_id` VARCHAR(255),
    `appointment_id` VARCHAR(255),
    `content` TEXT,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- TABLE INVOICE
-- ==============================================================
CREATE TABLE IF NOT EXISTS `invoice` (
    `id` VARCHAR(255) NOT NULL UNIQUE,
    `patient_id` VARCHAR(255),
    `doctor_id` VARCHAR(255),
    `amount` DECIMAL(12,2) NOT NULL,
    `status` BOOLEAN DEFAULT FALSE COMMENT 'TRUE: Completed, FALSE: InCompleted',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `is_updated` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`id`)
    );

-- ==============================================================
-- TABLE PRESCRIPTION
-- ==============================================================
CREATE TABLE IF NOT EXISTS `prescription` (
    `id` VARCHAR(255) NOT NULL UNIQUE,
    `name` VARCHAR(100) NOT NULL,
    `doctor_id` VARCHAR(255) NOT NULL,
    `patient_id` VARCHAR(255) NOT NULL,
    `dosage` DECIMAL(6,2) NOT NULL COMMENT 'Liều lượng thuốc',
    `frequency` DECIMAL(6,2) NOT NULL COMMENT 'Tần suất uống trong ngày',
    `note` VARCHAR(255),
    `status` BOOLEAN DEFAULT TRUE,
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `is_updated` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ==============================================================
-- FOREIGN KEYS
-- ==============================================================

ALTER TABLE `branch_hospital`
    ADD FOREIGN KEY(`hospital_id`) REFERENCES `hospital`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE `accounts`
    ADD FOREIGN KEY(`faculty_id`) REFERENCES `faculties`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`branch_hospital_id`) REFERENCES `branch_hospital`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE `role_account`
    ADD FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`role_id`) REFERENCES `roles`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `address`
    ADD FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`branch_hospital_id`) REFERENCES `branch_hospital`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`hospital_id`) REFERENCES `hospital`(`id`)
           ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `phones`
    ADD FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`hospital_id`) REFERENCES `hospital`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`branch_hospital_id`) REFERENCES `branch_hospital`(`id`)
           ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `images`
    ADD FOREIGN KEY(`account_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `chats`
    ADD FOREIGN KEY(`sender_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`reciever_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`message_id`) REFERENCES `messages`(`id`)
           ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `appointments`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`faculty_id`) REFERENCES `faculties`(`id`)
           ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`branch_hospital_id`) REFERENCES `branch_hospital`(`id`)
              ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE `alerts`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE `notes`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`appointment_id`) REFERENCES `appointments`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `replies`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`appointment_id`) REFERENCES `appointments`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `invoice`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE `prescription`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

-- ==============================================================
-- SEED DATA
-- ==============================================================
INSERT INTO `roles` (name) VALUES
                               ('ROLE_SUPER_ADMIN'),
                               ('ROLE_ADMIN'),
                               ('ROLE_DOCTOR'),
                               ('ROLE_PATIENT');

INSERT INTO `faculties` (name) VALUES
                                   ('KHOA NỘI'),
                                   ('KHOA NGOẠI'),
                                   ('KHOA SẢN'),
                                   ('KHOA HỒI SỨC');

INSERT INTO `Accounts` (id, first_name,last_name,
                        email,password,gender,faculty_id,
                        security_code,status,
                        created_at,updated,isDeleted)
VALUES ('f2777f35-0c9e-4832-bf98-dd2d9f1be1e9',
        'Tran', 'Dai',
        'admin@gmail.com', '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i',1,
        '1', 'f2777f35-0c9e-4832-bf98-dd2d9f1be1e9',
        true, NOW(), 'NULL', false);

COMMIT;
