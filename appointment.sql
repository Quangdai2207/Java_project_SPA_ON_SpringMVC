-- ** CONFIG DATABASE:
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- ** DROP AND CREATE DATABASE:
DROP DATABASE IF EXISTS `appointment_springmvc`;
CREATE DATABASE `appointment_springmvc` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `appointment_springmvc`;

-- ** TABLE FACULTIES **
CREATE TABLE IF NOT EXISTS `Faculties` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(`id`)
    );

-- ** TABLE ACCOUNTS **
CREATE TABLE IF NOT EXISTS `Accounts` (
    `id` VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `email` VARCHAR(255) NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `gender` TINYINT NULL,
    `Dob` DATE NULL,
    `faculty_id` INT NULL,
    `branch_hospital_id` VARCHAR(255),
    `security_code` VARCHAR(255) NOT NULL,
    `status` BOOlEAN COMMENT 'TRUE: active FALSE: inactive',
    `created_at` DATETIME default current_timestamp,
    `is_updated` DATETIME default current_timestamp,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ** TABLE ROLES **
CREATE TABLE IF NOT EXISTS `Roles` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY(`id`)
    );

-- ** TABLE ROLE_ACCOUNT **
CREATE TABLE IF NOT EXISTS `Role_Account` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `account_id` VARCHAR(255) NOT NULL,
    `role_id` INT NOT NULL,
    PRIMARY KEY(`id`)
    );

-- ** TABLE ADDRESS **
CREATE TABLE IF NOT EXISTS `Address` (
    `id` VARCHAR(255) NOT NULL,
    `account_id` VARCHAR(255) NULL
    `branch_hospital_id` VARCHAR(255) NULL,
    `province` VARCHAR(255) NULL,
    `district` VARCHAR(255) NULL,
    `ward` VARCHAR(255) NULL,
    `address_number` VARCHAR(255) NULL,
    `street` VARCHAR(255) NULL,
    `type` TINYINT DEFAULT null COMMENT '1: Loại địa chỉ người dung 2: Loại địa chỉ bệnh viện',
    PRIMARY KEY(`id`)
    );

-- ** TABLE PHONES **
CREATE TABLE IF NOT EXISTS `Phones` (
    `id` VARCHAR(255) NOT NULL,
    `phone_number` VARCHAR(20),
    `account_id` VARCHAR(255),
    `type` TINYINT COMMENT '1: Dien thoai nguoi dung 2: Dien thoai benh vien 3: Dien thoai chi nhanh benh vien',
    PRIMARY KEY(`id`)
    );

-- ** TABLE IMAGES **
CREATE TABLE IF NOT EXISTS `Images` (
    `id` VARCHAR(255) NOT NULL,
    `account_id` VARCHAR(255),
    `path_name` VARCHAR(255),
    PRIMARY KEY(`id`)
    );

-- ** TABLE MESSAGES **
CREATE TABLE IF NOT EXISTS `Messages` (
    `id` VARCHAR(255) NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL,
    PRIMARY KEY(`id`)
    );

-- ** TABLE CHATS **
CREATE TABLE IF NOT EXISTS `Chats` (
    `id` INT NOT NULL AUTO_INCREMENT,
     `account_id` VARCHAR(255),
    `message_id` VARCHAR(255),
    `created_at` DATETIME default current_timestamp,
    PRIMARY KEY(`id`)
    );

-- ** TABLE APPOINTMENTS **
CREATE TABLE IF NOT EXISTS `Appointments` (
    `id` VARCHAR(255) NOT NULL,
    `doctor_id` VARCHAR(255),
    `patient_id` VARCHAR(255),
    `faculty_id` INT,
    `branch_hospital_id` VARCHAR(255),
    `date` DATETIME,
    `time` TIME,
    `content` VARCHAR(255),
    `status` TINYINT COMMENT '1: Reject, 2: Accepted, 3: Done',
    `created_at` DATETIME default current_timestamp,
    `is_updated` DATETIME default current_timestamp,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ** TABLE ALERTS **
CREATE TABLE IF NOT EXISTS `Alerts` (
    `id` VARCHAR(255) NOT NULL,
    `doctor_id` VARCHAR(255),
    `patient_id` VARCHAR(255),
    `content` VARCHAR(255),
    `created_at` DATETIME default current_timestamp,
    PRIMARY KEY(`id`)
    );

-- ** TABLE NOTES ** Ghi chu cho bác si khi gi chu tren cac lich khám bệnh
CREATE TABLE IF NOT EXISTS `Notes` (
    `id` VARCHAR(255) NOT NULL,
    `doctor_id` VARCHAR(255),
    `appointment_id` VARCHAR(255),
    `content` TEXT,
    `created_at` DATETIME default current_timestamp,
    `is_updated` DATETIME default current_timestamp,
    `is_deleted` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(`id`)
    );

-- ** TABLE REPLIES ** Dùng cho phản hồi email với khách hàng sau khi gửi lịch khám cho bác sĩ, bác sĩ tiếp nhật thông tin qua
-- ** websocket và phẩn hồi trực tiếp qua email cho bệnh nhân đặt lịch hẹn
CREATE TABLE IF NOT EXISTS `Replies` (
    `id` VARCHAR(255) NOT NULL,
    `doctor_id` VARCHAR(255),
    `appointment_id` VARCHAR(255),
    `content` TEXT,
    `created_at` DATETIME default current_timestamp,
    PRIMARY KEY(`id`)
    );

CREATE TABLE IF NOT EXISTS `invoice` (
    `id` VARCHAR(255) NOT NULL UNIQUE,
    `patient_id` VARCHAR(255),
    `doctor_id` VARCHAR(255),
    `amount` decimal NOT NULL,
    `status` BOOLEAN DEFAULT FALSE COMMENT 'TRUE: Completed, FALSE: InCompleted',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `is_updated` datetime default current_timestamp,
    `isDeleted` boolean default false
    PRIMARY KEY (`id`)
    );

CREATE TABLE IF NOT EXISTS `Prescription` (
    `id` varchar(255) primary key unique,
    `name` varchar(100) not null,
    `doctor_id` varchar(255) not null,
    `patient_id` varchar(255) not null,
    `dosage` decimal not null, -- lieu luong
    `frequence` decimal not null, -- tan suat uong trong ngay
    `note` varchar(255) default null,
    `status` boolean default true,
    `created_at` datetime default current_timestamp,
    `is_updated` datetime default current_timestamp,
    `is_deleted` boolean default false
)


CREATE TABLE IF NOT EXISTS `Hospital` (
    `id` VARCHAR(255) NOT NULL UNIQUE,
    `name` VARCHAR(165),
    `tyoe` TINYINT COMMENT '1:Benh vien cong 2: Benh vien tu 3: benh vien quoc te',
    `director_name` VARCHAR(255),
    `tax_code` VARCHAR(255),
    `website` VARCHAR(255),
    `email` VARCHAR(255),
    `contact_phone` VARCHAR(255),
    `license_number` VARCHAR(255),
    `fax` VARCHAR(255),
    `established_date` DATE,
    `created_at` DATETIME DEFAULT current_timestamp,
    `is_updated` DATETIME DEFAULT current_timestamp,
    `is_deleted` BOOLEAN DEFAULT false,
    PRIMARY KEY(`id`)
    );

CREATE TABLE IF NOT EXISTS `Branch_hospital` (
    `id` VARCHAR(255) NOT NULL UNIQUE,
    `branch_name` VARCHAR(255),
    `hospital_id` VARCHAR(255),
    `director_branch` VARCHAR(255),
    `code` VARCHAR(255),
    `type` VARCHAR(255) COMMENT 'Loại chi nhánh: Ví du; tổng quát, chuyên khoa,xét nghiệm',
    `status` TINYINT DEFAULT 1 COMMENT '1: Hoạt động 2: Tạm ngưng 3: Ngừng hoạt động',
    `created_at` DATETIME default current_timestamp,
    `is_updated` DATETIME default current_timestamp,
    `is_deleted` BOOLEAN default false,
    PRIMARY KEY(`id`)
    );


-- ** ==============================================================
-- ** GÁN KHÓA NGOẠI SAU KHI TẠO TOÀN BỘ BẢNG
-- ** ==============================================================

-- ** Invoice with Accounts
ALTER TABLE `Invoice`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE `Branch_hospital`
    ADD FOREIGN KEY(`hospital_id`) REFERENCES `Hostpital`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

-- ** Prescription with Accounts
ALTER TABLE `Prescription`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

-- ** Account with faculties
ALTER TABLE `Accounts`
    ADD FOREIGN KEY(`faculty_id`) REFERENCES `Faculties`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
ADD FOREIGN KEY(`branch_hostpital_id`) REFERENCES `Branch_hospital`(`id`)
           ON UPDATE CASCADE ON DELETE SET NULL;

-- ** Role And Account with Many-Many => Role_account One-many Accounts and Role_account One-many Roles
ALTER TABLE `Role_Account`
    ADD FOREIGN KEY(`account_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`role_id`) REFERENCES `Roles`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

-- ** Address with Account
ALTER TABLE `Address`
    ADD FOREIGN KEY(`account_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`branch_hospital_id`) REFERENCES `branch_hospital`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `Phones`
    ADD FOREIGN KEY(`account_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`hospital_id`) REFERENCES `hospital`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`branch_hostpital_id`) REFERENCES `Branch_hospital`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `Images`
    ADD FOREIGN KEY(`account_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `Chats`
    ADD FOREIGN KEY(`account_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY(`message_id`) REFERENCES `Messages`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `Appointments`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`faculty_id`) REFERENCES `Faculties`(`id`)
           ON UPDATE CASCADE ON DELETE SET NULL;
    ADD FOREIGN KEY(`branch_hospital_id`) REFERENCES `Branch_hospital`(`id`)
           ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE `Alerts`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE `Notes`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`appointment_id`) REFERENCES `Appointments`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `Replies`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`appointment_id`) REFERENCES `Appointments`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE;

-- ** ADD ROLE **
INSERT INTO `Roles` (name)
VALUES
    ('ROLE_SUPER_ADMIN'),
    ('ROLE_ADMIN'),
    ('ROLE_DOCTOR'),
    ('ROLE_PATIENT');

-- ** ADD FACULTIES **
INSERT INTO `Faculties` (name)
VALUES
    ('KHOA NOI'),
    ('KHOA NGOAI'),
    ('KHOA SAN'),
    ('KHOA HOI SUC');

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


