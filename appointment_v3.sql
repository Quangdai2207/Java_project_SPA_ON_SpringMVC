-- ==============================================================
-- CONFIG DATABASE
-- ==============================================================
SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";
-- ==============================================================
-- DROP AND CREATE DATABASE
-- ==============================================================
DROP
DATABASE IF EXISTS `appointment_springmvc`;
CREATE
DATABASE `appointment_springmvc` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE
`appointment_springmvc`;

-- ==============================================================
-- CREATE TABLES
-- ==============================================================

CREATE TABLE Faculties
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) UNIQUE,
    representative VARCHAR(255),
    created_at     DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted     BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Hospital
(
    id               VARCHAR(255) PRIMARY KEY,
    name             VARCHAR(255),
    type             TINYINT COMMENT '1: Công, 2: Tư, 3: Quốc tế',
    director_name    VARCHAR(255),
    tax_code         VARCHAR(255),
    website          VARCHAR(255),
    email            VARCHAR(255),
    contact_phone    VARCHAR(255),
    license_number   VARCHAR(255),
    fax              VARCHAR(255),
    established_date DATE,
    created_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated       DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted       BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Branch_hospital
(
    id              VARCHAR(255) PRIMARY KEY,
    branch_name     VARCHAR(255),
    hospital_id     VARCHAR(255),
    director_branch VARCHAR(255),
    code            VARCHAR(255),
    type            VARCHAR(255),
    status          TINYINT  DEFAULT 1,
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted      BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Accounts
(
    id                 VARCHAR(255) PRIMARY KEY,
    branch_hospital_id VARCHAR(255),
    first_name         VARCHAR(255),
    last_name          VARCHAR(255),
    email              VARCHAR(255) UNIQUE,
    dob                DATE,
    password           VARCHAR(65),
    gender             TINYINT,
    faculty_id         INT,
    security_code      VARCHAR(255),
    status             BOOLEAN,
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted         BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Roles
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN  DEFAULT FALSE
);

CREATE TABLE role_account
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    account_id VARCHAR(255),
    role_id    INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Address
(
    id                 VARCHAR(255) PRIMARY KEY,
    account_id         VARCHAR(255),
    province           VARCHAR(255),
    district           VARCHAR(255),
    ward               VARCHAR(255),
    address_number     VARCHAR(255),
    street             VARCHAR(255),
    branch_hospital_id VARCHAR(255),
    hospital_id        VARCHAR(255),
    type               TINYINT COMMENT '1: user, 2: branch, 3: hospital',
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted         BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Phones
(
    id                 VARCHAR(255) PRIMARY KEY,
    phone_number       VARCHAR(20) UNIQUE,
    account_id         VARCHAR(255),
    hospital_id        VARCHAR(255),
    branch_hospital_id VARCHAR(255),
    type               TINYINT COMMENT '1: user, 2: branch, 3: hospital',
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted         BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Images
(
    id         VARCHAR(255) PRIMARY KEY,
    account_id VARCHAR(255),
    path_name  VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Appointments
(
    id                 VARCHAR(255) PRIMARY KEY,
    doctor_id          VARCHAR(255),
    patient_id         VARCHAR(255),
    faculty_id         INT,
    date               DATETIME,
    time               TIME,
    content            VARCHAR(255),
    status             TINYINT,
    branch_hospital_id VARCHAR(255),
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted         BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Notes
(
    id             VARCHAR(255) PRIMARY KEY,
    doctor_id      VARCHAR(255),
    appointment_id VARCHAR(255),
    content        TEXT,
    created_at     DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted     BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Replies
(
    id             VARCHAR(255) PRIMARY KEY,
    doctor_id      VARCHAR(255),
    appointment_id VARCHAR(255),
    content        TEXT,
    created_at     DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted     BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Alerts
(
    id         VARCHAR(255) PRIMARY KEY,
    doctor_id  VARCHAR(255),
    patient_id VARCHAR(255),
    content    VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Messages
(
    id         VARCHAR(255) PRIMARY KEY,
    title      VARCHAR(255),
    content    TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Chats
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    sender_id   VARCHAR(255),
    receiver_id VARCHAR(255),
    message_id  VARCHAR(255),
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Medical_record
(
    id         VARCHAR(255) PRIMARY KEY,
    patient_id VARCHAR(255),
    doctor_id  VARCHAR(255),
    status     TINYINT  DEFAULT 1,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN  DEFAULT FALSE
);

CREATE TABLE General_medical_info
(
    id                   VARCHAR(255) PRIMARY KEY,
    patient_id           VARCHAR(255),
    blood_type           VARCHAR(255),
    height DOUBLE,
    weight DOUBLE,
    past_medical_history VARCHAR(255),
    allergies            VARCHAR(255),
    lifestyle_habits     VARCHAR(255),
    created_at           DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated           DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted           BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Medical_content
(
    id                VARCHAR(255) PRIMARY KEY,
    medical_record_id VARCHAR(255),
    description       TEXT,
    disease_type      VARCHAR(255),
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted        BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Prescription
(
    id                VARCHAR(255) PRIMARY KEY,
    name              VARCHAR(255),
    medical_record_id VARCHAR(255),
    dosage            DECIMAL(10, 2),
    frequency         DECIMAL(10, 2),
    duration          INT,
    note              VARCHAR(255),
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted        BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Invoice
(
    id         VARCHAR(255) PRIMARY KEY,
    patient_id VARCHAR(255),
    doctor_id  VARCHAR(255),
    amount     DECIMAL(12, 2) NOT NULL,
    status     BOOLEAN  DEFAULT FALSE COMMENT 'TRUE: Completed, FALSE: Incompleted',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Keywords
(
    id            VARCHAR(255) PRIMARY KEY,
    keyword       VARCHAR(255) NOT NULL UNIQUE,
    search_count  BIGINT      DEFAULT 0 COMMENT 'Tổng số lượt tìm kiếm',
    unique_users  INT         DEFAULT 0 COMMENT 'Số người dùng duy nhất tìm kiếm',
    last_searched DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT 'Lần cuối được tìm kiếm',
    created_at    DATETIME    DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    trend_score   FLOAT       DEFAULT 0 COMMENT 'Điểm xu hướng',
    is_trending   BOOLEAN     DEFAULT FALSE COMMENT 'Đang là xu hướng nổi bật',
    category      VARCHAR(255) NULL COMMENT 'Nhóm chủ đề',
    language      VARCHAR(10) DEFAULT 'vi' COMMENT 'Ngôn ngữ',
    region        VARCHAR(50) NULL COMMENT 'Khu vực'
);

CREATE TABLE Reviews
(
    id          VARCHAR(255) PRIMARY KEY,
    reviewer_id VARCHAR(255) NOT NULL,                          -- người đánh giá
    target_id   VARCHAR(255) NOT NULL,                          -- id của đối tượng được đánh giá
    target_type ENUM('doctor', 'hospital', 'service') NOT NULL, -- loại đối tượng
    rating      TINYINT CHECK (rating BETWEEN 1 AND 5),
    content     TEXT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN  DEFAULT FALSE
);


CREATE TABLE Keyword_Logs
(
    id          VARCHAR(255) PRIMARY KEY,
    keyword_id  VARCHAR(255),
    searched_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    account_id  VARCHAR(255) NULL,
    device      VARCHAR(100) NULL COMMENT 'nguồn truy cập: mobile, web, app...',
    region      VARCHAR(50) NULL
);

CREATE TABLE Comments
(
    id             VARCHAR(255) PRIMARY KEY,
    parent_id      VARCHAR(255) NULL,
    appointment_id VARCHAR(255),
    doctor_id      VARCHAR(255),
    patient_id     VARCHAR(255),
    account_id     VARCHAR(255),
    content        TEXT,
    rating         TINYINT CHECK (rating BETWEEN 1 AND 5),
    level          INT      DEFAULT 0 COMMENT '0: comment gốc, 1+: cấp phản hồi',
    created_at     DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted     BOOLEAN  DEFAULT FALSE
);

CREATE TABLE Blogs
(
    id         VARCHAR(255) PRIMARY KEY,
    title      VARCHAR(65) UNIQUE,
    admin_id   VARCHAR(255) NOT NULL,
    content    TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN  DEFAULT FALSE
);


-- ===========================================================
-- FOREIGN KEYS
-- ===========================================================

ALTER TABLE Branch_hospital
    ADD FOREIGN KEY (hospital_id) REFERENCES Hospital (id)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE Blogs
    ADD FOREIGN KEY (account_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL

ALTER TABLE Accounts
    ADD FOREIGN KEY (faculty_id) REFERENCES Faculties (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (branch_hospital_id) REFERENCES Branch_hospital(id)
    ON
UPDATE CASCADE
ON
DELETE
SET NULL;

ALTER TABLE role_account
    ADD FOREIGN KEY (account_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    ADD FOREIGN KEY (role_id) REFERENCES Roles (id)
    ON
UPDATE CASCADE
ON
DELETE
CASCADE;

ALTER TABLE Address
    ADD FOREIGN KEY (account_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (branch_hospital_id) REFERENCES Branch_hospital(id)
    ON
UPDATE CASCADE
ON
DELETE
SET NULL, ADD FOREIGN KEY (hospital_id) REFERENCES Hospital(id)
ON
UPDATE CASCADE
ON
DELETE
SET NULL;

ALTER TABLE Phones
    ADD FOREIGN KEY (account_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (hospital_id) REFERENCES Hospital(id)
    ON
UPDATE CASCADE
ON
DELETE
SET NULL, ADD FOREIGN KEY (branch_hospital_id) REFERENCES Branch_hospital(id)
ON
UPDATE CASCADE
ON
DELETE
SET NULL;

ALTER TABLE Images
    ADD FOREIGN KEY (account_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE Appointments
    ADD FOREIGN KEY (doctor_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (patient_id) REFERENCES Accounts(id)
    ON
UPDATE CASCADE
ON
DELETE
SET NULL, ADD FOREIGN KEY (faculty_id) REFERENCES Faculties(id)
ON
UPDATE CASCADE
ON
DELETE
SET NULL, ADD FOREIGN KEY (branch_hospital_id) REFERENCES Branch_hospital(id)
ON
UPDATE CASCADE
ON
DELETE
SET NULL;

ALTER TABLE Notes
    ADD FOREIGN KEY (doctor_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (appointment_id) REFERENCES Appointments(id)
    ON
UPDATE CASCADE
ON
DELETE
CASCADE;

ALTER TABLE Replies
    ADD FOREIGN KEY (doctor_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (appointment_id) REFERENCES Appointments(id)
    ON
UPDATE CASCADE
ON
DELETE
CASCADE;

ALTER TABLE Alerts
    ADD FOREIGN KEY (doctor_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (patient_id) REFERENCES Accounts(id)
    ON
UPDATE CASCADE
ON
DELETE
SET NULL;

ALTER TABLE Chats
    ADD FOREIGN KEY (sender_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (receiver_id) REFERENCES Accounts(id)
    ON
UPDATE CASCADE
ON
DELETE
SET NULL, ADD FOREIGN KEY (message_id) REFERENCES Messages(id)
ON
UPDATE CASCADE
ON
DELETE
CASCADE;

ALTER TABLE Medical_record
    ADD FOREIGN KEY (doctor_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (patient_id) REFERENCES Accounts(id)
    ON
UPDATE CASCADE
ON
DELETE
SET NULL;

ALTER TABLE General_medical_info
    ADD FOREIGN KEY (patient_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE Medical_content
    ADD FOREIGN KEY (medical_record_id) REFERENCES Medical_record (id)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Prescription
    ADD FOREIGN KEY (medical_record_id) REFERENCES Medical_record (id)
        ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Invoice
    ADD FOREIGN KEY (doctor_id) REFERENCES Accounts (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY (patient_id) REFERENCES Accounts(id)
    ON
UPDATE CASCADE
ON
DELETE
SET NULL;

ALTER TABLE Comments
    ADD CONSTRAINT fk_comment_parent
        FOREIGN KEY (parent_id) REFERENCES Comments (id)
            ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Comments
    ADD CONSTRAINT fk_comment_appointment
        FOREIGN KEY (appointment_id) REFERENCES Appointments (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE Comments
    ADD CONSTRAINT fk_comment_doctor
        FOREIGN KEY (doctor_id) REFERENCES Accounts (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE Comments
    ADD CONSTRAINT fk_comment_patient
        FOREIGN KEY (patient_id) REFERENCES Accounts (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE Comments
    ADD CONSTRAINT fk_comment_account
        FOREIGN KEY (account_id) REFERENCES Accounts (id)
            ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE Keyword_Logs
    ADD CONSTRAINT fk_keyword_logs_keyword
        FOREIGN KEY (keyword_id)
            REFERENCES Keywords (id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

ALTER TABLE Reviews
    ADD CONSTRAINT fk_reviews_reviewer
        FOREIGN KEY (reviewer_id) REFERENCES Accounts (id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

-- ===========================
-- SEED 10 RECORDS PER TABLE (corrected: only 4 roles)
-- ===========================
SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
USE
`appointment_springmvc`;

-- disable FK check for safe insert
SET
FOREIGN_KEY_CHECKS = 0;

-- (optionally) truncate existing tables in safe order
-- COMMENT/UNCOMMENT as you need (be careful on prod)
TRUNCATE TABLE role_account;
TRUNCATE TABLE Comments;
TRUNCATE TABLE Keyword_Logs;
TRUNCATE TABLE Reviews;
TRUNCATE TABLE Keywords;
TRUNCATE TABLE Invoice;
TRUNCATE TABLE Prescription;
TRUNCATE TABLE Medical_content;
TRUNCATE TABLE General_medical_info;
TRUNCATE TABLE Medical_record;
TRUNCATE TABLE Alerts;
TRUNCATE TABLE Replies;
TRUNCATE TABLE Notes;
TRUNCATE TABLE Appointments;
TRUNCATE TABLE Chats;
TRUNCATE TABLE Messages;
TRUNCATE TABLE Images;
TRUNCATE TABLE Phones;
TRUNCATE TABLE Address;
TRUNCATE TABLE Accounts;
TRUNCATE TABLE Branch_hospital;
TRUNCATE TABLE Hospital;
TRUNCATE TABLE Faculties;
TRUNCATE TABLE Roles;

-- -------------------------
-- Roles (exactly 4)
-- -------------------------
INSERT INTO Roles (name)
VALUES ('ROLE_SUPER_ADMIN'),
       ('ROLE_ADMIN'),
       ('ROLE_DOCTOR'),
       ('ROLE_PATIENT');

-- -------------------------
-- Faculties (4)
-- -------------------------
INSERT INTO Faculties (name, representative)
VALUES ('Khoa Nội', 'Nguyen Huu Linh'),
       ('Khoa Ngoại', 'Pham Minh Khang'),
       ('Khoa Thần Kinh', 'Tran Bao Huan'),
       ('Khoa Sản', 'Vu Quoc Tuan');

-- -------------------------
-- Hospital (10)
-- -------------------------
INSERT INTO Hospital (id, name, type, director_name, tax_code, website, email, contact_phone, license_number, fax,
                      established_date)
VALUES ('hosp-1', 'Bệnh viện Trung ương 1', 1, 'Nguyễn Văn A', 'TAX001', 'http://hosp1.local', 'contact@hosp1.local',
        '0901000001', 'LIC-001', '04-1111', '2000-01-15'),
       ('hosp-2', 'Bệnh viện Đa khoa 2', 1, 'Trần Văn B', 'TAX002', 'http://hosp2.local', 'contact@hosp2.local',
        '0901000002', 'LIC-002', '04-2222', '2001-02-20'),
       ('hosp-3', 'Bệnh viện Quốc tế 3', 3, 'Lê Thị C', 'TAX003', 'http://hosp3.local', 'contact@hosp3.local',
        '0901000003', 'LIC-003', '04-3333', '2005-03-12'),
       ('hosp-4', 'Bệnh viện Tỉnh 4', 1, 'Phạm Văn D', 'TAX004', 'http://hosp4.local', 'contact@hosp4.local',
        '0901000004', 'LIC-004', '04-4444', '1998-06-01'),
       ('hosp-5', 'Bệnh viện Tư nhân 5', 2, 'Võ Thị E', 'TAX005', 'http://hosp5.local', 'contact@hosp5.local',
        '0901000005', 'LIC-005', '04-5555', '2010-09-30'),
       ('hosp-6', 'Bệnh viện Chuyên khoa 6', 2, 'Ngô Văn F', 'TAX006', 'http://hosp6.local', 'contact@hosp6.local',
        '0901000006', 'LIC-006', '04-6666', '2012-11-11'),
       ('hosp-7', 'Bệnh viện Phòng khám 7', 2, 'Đặng Thị G', 'TAX007', 'http://hosp7.local', 'contact@hosp7.local',
        '0901000007', 'LIC-007', '04-7777', '2015-05-05'),
       ('hosp-8', 'Bệnh viện Đa khoa 8', 1, 'Bùi Văn H', 'TAX008', 'http://hosp8.local', 'contact@hosp8.local',
        '0901000008', 'LIC-008', '04-8888', '1995-12-12'),
       ('hosp-9', 'Bệnh viện Quốc tế 9', 3, 'Hoàng Thị I', 'TAX009', 'http://hosp9.local', 'contact@hosp9.local',
        '0901000009', 'LIC-009', '04-9999', '2003-04-04'),
       ('hosp-10', 'Bệnh viện Đa khoa 10', 1, 'Phan Văn K', 'TAX010', 'http://hosp10.local', 'contact@hosp10.local',
        '0901000010', 'LIC-010', '04-1010', '2008-07-07');

-- -------------------------
-- Branch_hospital (10)
-- -------------------------
INSERT INTO Branch_hospital (id, branch_name, hospital_id, director_branch, code, type, status)
VALUES ('branch-1', 'Chi nhánh A', 'hosp-1', 'Nguyễn Văn A', 'BRC-001', 'Regional', 1),
       ('branch-2', 'Chi nhánh B', 'hosp-1', 'Nguyễn Văn A', 'BRC-002', 'Regional', 1),
       ('branch-3', 'Chi nhánh C', 'hosp-2', 'Trần Văn B', 'BRC-003', 'Regional', 1),
       ('branch-4', 'Chi nhánh D', 'hosp-3', 'Lê Thị C', 'BRC-004', 'Regional', 1),
       ('branch-5', 'Chi nhánh E', 'hosp-4', 'Phạm Văn D', 'BRC-005', 'Regional', 1),
       ('branch-6', 'Chi nhánh F', 'hosp-5', 'Võ Thị E', 'BRC-006', 'Regional', 1),
       ('branch-7', 'Chi nhánh G', 'hosp-6', 'Ngô Văn F', 'BRC-007', 'Regional', 1),
       ('branch-8', 'Chi nhánh H', 'hosp-7', 'Đặng Thị G', 'BRC-008', 'Regional', 1),
       ('branch-9', 'Chi nhánh I', 'hosp-8', 'Bùi Văn H', 'BRC-009', 'Regional', 1),
       ('branch-10', 'Chi nhánh J', 'hosp-9', 'Hoàng Thị I', 'BRC-010', 'Regional', 1);

-- -------------------------
-- Accounts (10) - using VARCHAR ids
-- faculty_id must be 1..4 to match Faculties
-- -------------------------
INSERT INTO Accounts (id, branch_hospital_id, first_name, last_name, email, dob, password, gender, faculty_id,
                      security_code, status)
VALUES ('acc-1', 'branch-1', 'Tran', 'Dai', 'admin@gmail.com', '1988-07-22',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 1, 1,
        'sec-1', TRUE),
       ('acc-2', 'branch-1', 'Nguyen', 'An', 'user1@example.com', '1990-01-01',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 1, 1, 'sec-2', TRUE),
       ('acc-3', 'branch-1', 'Tran', 'Binh', 'user2@example.com', '1985-02-02',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 1, 2, 'sec-3', TRUE),
       ('acc-4', 'branch-2', 'Le', 'Cuong', 'user3@example.com', '1992-03-03',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 1, 3, 'sec-4', TRUE),
       ('acc-5', 'branch-3', 'Pham', 'Dung', 'user4@example.com', '1980-04-04',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 0, 4, 'sec-5', TRUE),
       ('acc-6', 'branch-4', 'Vu', 'Hung', 'user5@example.com', '1995-05-05',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 1, 1, 'sec-6', TRUE),
       ('acc-7', 'branch-5', 'Hoang', 'Giang', 'user6@example.com', '1993-06-06',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 0, 2, 'sec-7', TRUE),
       ('acc-8', 'branch-6', 'Bui', 'Ha', 'user7@example.com', '1991-07-07',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 1, 3, 'sec-8', TRUE),
       ('acc-9', 'branch-7', 'Ngo', 'I', 'user8@example.com', '1989-08-08',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 1, 4, 'sec-9', TRUE),
       ('acc-10', 'branch-8', 'Dang', 'Khanh', 'user9@example.com', '1998-09-09',
        '$2a$10$.XNjYaTzRNxNNic0ip.J7eBHklG8lDPuMVMFEY.2vDQ28TdnWzs9i', 0, 1, 'sec-10', TRUE);

-- -------------------------
-- role_account (linking using only 4 roles)
-- role ids: 1-ROLE_SUPER_ADMIN,2-ROLE_ADMIN,3-ROLE_DOCTOR,4-ROLE_PATIENT
-- -------------------------
INSERT INTO role_account (account_id, role_id)
VALUES ('acc-1', 1),
       ('acc-2', 4),
       ('acc-3', 3),
       ('acc-4', 3),
       ('acc-5', 4),
       ('acc-6', 2),
       ('acc-7', 4),
       ('acc-8', 3),
       ('acc-9', 4),
       ('acc-10', 2);

-- -------------------------
-- Address (10)
-- -------------------------
INSERT INTO Address (id, account_id, province, district, ward, address_number, street, branch_hospital_id, hospital_id,
                     type)
VALUES ('addr-1', 'acc-1', 'Hanoi', 'Ba Dinh', 'Phuc Xa', '12A', 'Lien Tri', 'branch-1', 'hosp-1', 1),
       ('addr-2', 'acc-2', 'Hanoi', 'Dong Da', 'Cat Linh', '34B', 'Hang Bong', 'branch-1', 'hosp-1', 1),
       ('addr-3', 'acc-3', 'HCMC', 'District 1', 'Ben Nghe', '56C', 'Nguyen Hue', 'branch-2', 'hosp-1', 1),
       ('addr-4', 'acc-4', 'HCMC', 'Thu Duc', 'Linh Chi', '78D', 'Xa Lo', 'branch-3', 'hosp-2', 1),
       ('addr-5', 'acc-5', 'Da Nang', 'Hai Chau', 'Thach Thang', '90E', 'Pham Van Dong', 'branch-4', 'hosp-3', 1),
       ('addr-6', 'acc-6', 'Hai Phong', 'Le Chan', 'Hai An', '21F', 'Le Loi', 'branch-5', 'hosp-4', 1),
       ('addr-7', 'acc-7', 'Can Tho', 'Ninh Kieu', 'Tran Quoc Toan', '43G', 'Nguyen Trai', 'branch-6', 'hosp-5', 1),
       ('addr-8', 'acc-8', 'Hue', 'Hue', 'Vinh Ninh', '65H', 'Le Loi', 'branch-7', 'hosp-6', 1),
       ('addr-9', 'acc-9', 'Vinh', 'Vinh City', 'Lam Son', '87I', 'Tran Phu', 'branch-8', 'hosp-7', 1),
       ('addr-10', 'acc-10', 'Nha Trang', 'Cam Ranh', 'Phuoc Tan', '10J', 'Le Loi', 'branch-9', 'hosp-8', 1);

-- -------------------------
-- Phones (10)
-- -------------------------
INSERT INTO Phones (id, phone_number, account_id, hospital_id, branch_hospital_id, type)
VALUES ('phone-1', '0901000001', 'acc-1', NULL, 'branch-1', 1),
       ('phone-2', '0901000002', 'acc-2', NULL, 'branch-1', 1),
       ('phone-3', '0901000003', 'acc-3', NULL, 'branch-2', 1),
       ('phone-4', '0901000004', 'acc-4', NULL, 'branch-3', 1),
       ('phone-5', '0901000005', 'acc-5', NULL, 'branch-4', 1),
       ('phone-6', '0901000006', 'acc-6', NULL, 'branch-5', 1),
       ('phone-7', '0901000007', 'acc-7', NULL, 'branch-6', 1),
       ('phone-8', '0901000008', 'acc-8', NULL, 'branch-7', 1),
       ('phone-9', '0901000009', 'acc-9', NULL, 'branch-8', 1),
       ('phone-10', '0901000010', 'acc-10', NULL, 'branch-9', 1);

-- -------------------------
-- Images (10)
-- -------------------------
INSERT INTO Images (id, account_id, path_name)
VALUES ('img-1', 'acc-1', '/images/admin/avatar1.png'),
       ('img-2', 'acc-2', '/images/user/avatar2.png'),
       ('img-3', 'acc-3', '/images/user/avatar3.png'),
       ('img-4', 'acc-4', '/images/user/avatar4.png'),
       ('img-5', 'acc-5', '/images/user/avatar5.png'),
       ('img-6', 'acc-6', '/images/user/avatar6.png'),
       ('img-7', 'acc-7', '/images/user/avatar7.png'),
       ('img-8', 'acc-8', '/images/user/avatar8.png'),
       ('img-9', 'acc-9', '/images/user/avatar9.png'),
       ('img-10', 'acc-10', '/images/user/avatar10.png');

-- -------------------------
-- Messages (10)
-- -------------------------
INSERT INTO Messages (id, title, content)
VALUES ('msg-1', 'Thông báo bảo trì', 'Hệ thống sẽ bảo trì vào cuối tuần.'),
       ('msg-2', 'Lịch khám mới', 'Lịch khám đã được cập nhật.'),
       ('msg-3', 'Khuyến mại', 'Giảm giá khám tim mạch tháng này.'),
       ('msg-4', 'Cập nhật hồ sơ', 'Vui lòng cập nhật thông tin hồ sơ.'),
       ('msg-5', 'Hẹn tái khám', 'Bạn có hẹn tái khám vào tuần sau.'),
       ('msg-6', 'Cảnh báo kết quả', 'Kết quả xét nghiệm đã có.'),
       ('msg-7', 'Thông báo lễ', 'Bệnh viện nghỉ lễ theo lịch.'),
       ('msg-8', 'Hướng dẫn', 'Hướng dẫn đăng ký khám online.'),
       ('msg-9', 'Xác nhận thanh toán', 'Thanh toán của bạn đã thành công.'),
       ('msg-10', 'Phản hồi', 'Cảm ơn bạn đã phản hồi.');

-- -------------------------
-- Chats (10)
-- -------------------------
INSERT INTO Chats (sender_id, receiver_id, message_id)
VALUES ('acc-2', 'acc-3', 'msg-1'),
       ('acc-3', 'acc-2', 'msg-2'),
       ('acc-4', 'acc-5', 'msg-3'),
       ('acc-5', 'acc-4', 'msg-4'),
       ('acc-6', 'acc-7', 'msg-5'),
       ('acc-7', 'acc-6', 'msg-6'),
       ('acc-8', 'acc-9', 'msg-7'),
       ('acc-9', 'acc-8', 'msg-8'),
       ('acc-10', 'acc-1', 'msg-9'),
       ('acc-1', 'acc-2', 'msg-10');

-- -------------------------
-- Appointments (10)
-- -------------------------
INSERT INTO Appointments (id, doctor_id, patient_id, faculty_id, date, time, content, status, branch_hospital_id)
VALUES ('appt-1', 'acc-3', 'acc-2', 2, '2025-11-10 09:00:00', '09:00:00', 'Khám tổng quát', 1, 'branch-1'),
       ('appt-2', 'acc-4', 'acc-5', 3, '2025-11-11 10:00:00', '10:00:00', 'Khám tim', 1, 'branch-2'),
       ('appt-3', 'acc-8', 'acc-7', 1, '2025-11-12 11:00:00', '11:00:00', 'Khám mắt', 1, 'branch-3'),
       ('appt-4', 'acc-3', 'acc-9', 4, '2025-11-13 14:00:00', '14:00:00', 'Khám răng', 1, 'branch-4'),
       ('appt-5', 'acc-4', 'acc-10', 2, '2025-11-14 15:00:00', '15:00:00', 'Khám nhi', 1, 'branch-5'),
       ('appt-6', 'acc-8', 'acc-6', 3, '2025-11-15 16:00:00', '16:00:00', 'Tư vấn', 1, 'branch-6'),
       ('appt-7', 'acc-3', 'acc-4', 1, '2025-11-16 08:00:00', '08:00:00', 'Theo dõi', 1, 'branch-7'),
       ('appt-8', 'acc-4', 'acc-2', 2, '2025-11-17 09:30:00', '09:30:00', 'Xét nghiệm', 1, 'branch-8'),
       ('appt-9', 'acc-8', 'acc-5', 4, '2025-11-18 13:00:00', '13:00:00', 'Khám lại', 1, 'branch-9'),
       ('appt-10', 'acc-3', 'acc-10', 1, '2025-11-19 10:30:00', '10:30:00', 'Tư vấn sức khỏe', 1, 'branch-10');

-- -------------------------
-- Notes (10)
-- -------------------------
INSERT INTO Notes (id, doctor_id, appointment_id, content)
VALUES ('note-1', 'acc-3', 'appt-1', 'Ghi chú: bệnh nhân cần kiểm tra huyết áp.'),
       ('note-2', 'acc-4', 'appt-2', 'Ghi chú: theo dõi tim mạch.'),
       ('note-3', 'acc-8', 'appt-3', 'Ghi chú: kiểm tra thị lực.'),
       ('note-4', 'acc-3', 'appt-4', 'Ghi chú: cạo vôi răng.'),
       ('note-5', 'acc-4', 'appt-5', 'Ghi chú: tiêm chủng.'),
       ('note-6', 'acc-8', 'appt-6', 'Ghi chú: xét nghiệm máu.'),
       ('note-7', 'acc-3', 'appt-7', 'Ghi chú: theo dõi vết mổ.'),
       ('note-8', 'acc-4', 'appt-8', 'Ghi chú: làm mẫu xét nghiệm.'),
       ('note-9', 'acc-8', 'appt-9', 'Ghi chú: kiểm tra kết quả.'),
       ('note-10', 'acc-3', 'appt-10', 'Ghi chú: tư vấn dinh dưỡng.');

-- -------------------------
-- Replies (10)
-- -------------------------
INSERT INTO Replies (id, doctor_id, appointment_id, content)
VALUES ('rep-1', 'acc-3', 'appt-1', 'Phản hồi: Đã tiếp nhận.'),
       ('rep-2', 'acc-4', 'appt-2', 'Phản hồi: Hẹn lịch lại.'),
       ('rep-3', 'acc-8', 'appt-3', 'Phản hồi: Kết quả khả quan.'),
       ('rep-4', 'acc-3', 'appt-4', 'Phản hồi: Cần theo dõi.'),
       ('rep-5', 'acc-4', 'appt-5', 'Phản hồi: Hoàn tất.'),
       ('rep-6', 'acc-8', 'appt-6', 'Phản hồi: Chờ kết quả.'),
       ('rep-7', 'acc-3', 'appt-7', 'Phản hồi: Đã xử lý.'),
       ('rep-8', 'acc-4', 'appt-8', 'Phản hồi: Đã gửi mẫu.'),
       ('rep-9', 'acc-8', 'appt-9', 'Phản hồi: Đã lập báo cáo.'),
       ('rep-10', 'acc-3', 'appt-10', 'Phản hồi: Hẹn tái khám.');

-- -------------------------
-- Alerts (10)
-- -------------------------
INSERT INTO Alerts (id, doctor_id, patient_id, content)
VALUES ('alert-1', 'acc-3', 'acc-2', 'Nhắc lịch khám ngày mai.'),
       ('alert-2', 'acc-4', 'acc-5', 'Nhắc tái khám.'),
       ('alert-3', 'acc-8', 'acc-7', 'Nhắc uống thuốc.'),
       ('alert-4', 'acc-3', 'acc-9', 'Nhắc làm xét nghiệm.'),
       ('alert-5', 'acc-4', 'acc-10', 'Nhắc thanh toán.'),
       ('alert-6', 'acc-8', 'acc-6', 'Nhắc kiểm tra.'),
       ('alert-7', 'acc-3', 'acc-4', 'Nhắc giờ khám.'),
       ('alert-8', 'acc-4', 'acc-8', 'Nhắc kết quả.'),
       ('alert-9', 'acc-8', 'acc-5', 'Nhắc hồ sơ.'),
       ('alert-10', 'acc-3', 'acc-10', 'Nhắc lịch họp.');

-- -------------------------
-- Medical_record (10)
-- -------------------------
INSERT INTO Medical_record (id, patient_id, doctor_id, status)
VALUES ('mr-1', 'acc-2', 'acc-3', 1),
       ('mr-2', 'acc-5', 'acc-4', 1),
       ('mr-3', 'acc-7', 'acc-8', 1),
       ('mr-4', 'acc-9', 'acc-3', 1),
       ('mr-5', 'acc-10', 'acc-4', 1),
       ('mr-6', 'acc-6', 'acc-8', 1),
       ('mr-7', 'acc-4', 'acc-3', 1),
       ('mr-8', 'acc-3', 'acc-4', 1),
       ('mr-9', 'acc-8', 'acc-3', 1),
       ('mr-10', 'acc-5', 'acc-8', 1);

-- -------------------------
-- General_medical_info (10)
-- -------------------------
INSERT INTO General_medical_info (id, patient_id, blood_type, height, weight, past_medical_history, allergies,
                                  lifestyle_habits)
VALUES ('gmi-1', 'acc-2', 'A', 170, 65, 'Không', 'Không', 'Không hút thuốc'),
       ('gmi-2', 'acc-3', 'B', 165, 60, 'Tiểu đường', 'Penicillin', 'Tập thể thao'),
       ('gmi-3', 'acc-4', 'O', 172, 70, 'Cao huyết áp', 'Không', 'Uống rượu ít'),
       ('gmi-4', 'acc-5', 'AB', 160, 55, 'Không', 'Thuốc lá', 'Ăn chay'),
       ('gmi-5', 'acc-6', 'A', 175, 80, 'Mổ trước đó', 'Aspirin', 'Ngủ sớm'),
       ('gmi-6', 'acc-7', 'B', 168, 62, 'Không', 'Không', 'Ít vận động'),
       ('gmi-7', 'acc-8', 'O', 180, 85, 'Không', 'Không', 'Tập gym'),
       ('gmi-8', 'acc-9', 'A', 158, 50, 'Hen', 'Không', 'Khói thuốc'),
       ('gmi-9', 'acc-10', 'AB', 166, 63, 'Không', 'Không', 'Ăn uống bình thường'),
       ('gmi-10', 'acc-1', 'A', 175, 75, 'Không', 'Không', 'Vận động vừa phải');

-- -------------------------
-- Medical_content (10)
-- -------------------------
INSERT INTO Medical_content (id, medical_record_id, description, disease_type)
VALUES ('mc-1', 'mr-1', 'Mô tả bệnh 1', 'loet'),
       ('mc-2', 'mr-2', 'Mô tả bệnh 2', 'tim'),
       ('mc-3', 'mr-3', 'Mô tả bệnh 3', 'mat'),
       ('mc-4', 'mr-4', 'Mô tả bệnh 4', 'rang'),
       ('mc-5', 'mr-5', 'Mô tả bệnh 5', 'nhi'),
       ('mc-6', 'mr-6', 'Mô tả bệnh 6', 'than kinh'),
       ('mc-7', 'mr-7', 'Mô tả bệnh 7', 'tieu hoa'),
       ('mc-8', 'mr-8', 'Mô tả bệnh 8', 'ho hap'),
       ('mc-9', 'mr-9', 'Mô tả bệnh 9', 'co xuong khop'),
       ('mc-10', 'mr-10', 'Mô tả bệnh 10', 'da lieu');

-- -------------------------
-- Prescription (10)
-- -------------------------
INSERT INTO Prescription (id, name, medical_record_id, dosage, frequency, duration, note)
VALUES ('pr-1', 'Paracetamol', 'mr-1', 500, 3, 5, 'Uống sau ăn'),
       ('pr-2', 'Amoxicillin', 'mr-2', 250, 2, 7, 'Uống theo đơn'),
       ('pr-3', 'Ibuprofen', 'mr-3', 200, 3, 3, 'Không dùng khi đói'),
       ('pr-4', 'Vitamin C', 'mr-4', 1000, 1, 10, 'Bổ sung'),
       ('pr-5', 'Omeprazole', 'mr-5', 20, 1, 14, 'Trước ăn'),
       ('pr-6', 'Metformin', 'mr-6', 500, 2, 30, 'Kiểm soát đường'),
       ('pr-7', 'Atenolol', 'mr-7', 50, 1, 15, 'Hạ huyết áp'),
       ('pr-8', 'Cetirizine', 'mr-8', 10, 1, 7, 'Dị ứng'),
       ('pr-9', 'Salbutamol', 'mr-9', 100, 4, 5, 'Hen phế quản'),
       ('pr-10', 'Prednisone', 'mr-10', 10, 1, 5, 'Giảm viêm');

-- -------------------------
-- Invoice (10)
-- -------------------------
INSERT INTO Invoice (id, patient_id, doctor_id, amount, status)
VALUES ('inv-1', 'acc-2', 'acc-3', 150.00, TRUE),
       ('inv-2', 'acc-3', 'acc-4', 200.50, FALSE),
       ('inv-3', 'acc-4', 'acc-5', 320.00, TRUE),
       ('inv-4', 'acc-5', 'acc-6', 75.00, FALSE),
       ('inv-5', 'acc-6', 'acc-7', 640.00, TRUE),
       ('inv-6', 'acc-7', 'acc-8', 100.00, FALSE),
       ('inv-7', 'acc-8', 'acc-9', 450.00, TRUE),
       ('inv-8', 'acc-9', 'acc-10', 55.00, FALSE),
       ('inv-9', 'acc-10', 'acc-1', 120.00, TRUE),
       ('inv-10', 'acc-1', 'acc-2', 220.00, TRUE);

-- -------------------------
-- Keywords (10)
-- -------------------------
INSERT INTO Keywords (id, keyword, search_count, unique_users, last_searched, trend_score, is_trending, category,
                      language, region)
VALUES ('kw-1', 'khám tim', 120, 80, '2025-11-01 10:00:00', 8.5, TRUE, 'tim', 'vi', 'VN'),
       ('kw-2', 'khám răng', 90, 70, '2025-10-25 09:00:00', 6.3, FALSE, 'rang', 'vi', 'VN'),
       ('kw-3', 'khám mắt', 60, 50, '2025-10-30 08:30:00', 5.2, FALSE, 'mat', 'vi', 'VN'),
       ('kw-4', 'khám nhi', 45, 40, '2025-11-02 11:00:00', 4.1, FALSE, 'nhi', 'vi', 'VN'),
       ('kw-5', 'xét nghiệm', 300, 150, '2025-11-03 12:00:00', 10.0, TRUE, 'xet nghiem', 'vi', 'VN'),
       ('kw-6', 'khám thai', 30, 25, '2025-10-20 14:00:00', 3.0, FALSE, 'san', 'vi', 'VN'),
       ('kw-7', 'tiêm chủng', 75, 60, '2025-11-04 16:00:00', 5.5, FALSE, 'tieu hoa', 'vi', 'VN'),
       ('kw-8', 'phẫu thuật', 15, 12, '2025-09-10 08:00:00', 2.0, FALSE, 'phau thuat', 'vi', 'VN'),
       ('kw-9', 'tư vấn sức khỏe', 50, 40, '2025-11-05 09:30:00', 4.8, FALSE, 'tu van', 'vi', 'VN'),
       ('kw-10', 'khám nam khoa', 20, 18, '2025-10-01 10:00:00', 1.5, FALSE, 'nam khoa', 'vi', 'VN');

-- -------------------------
-- Keyword_Logs (10)
-- -------------------------
INSERT INTO Keyword_Logs (id, keyword_id, searched_at, account_id, device, region)
VALUES ('kwlog-1', 'kw-1', '2025-11-01 10:01:00', 'acc-2', 'web', 'Hanoi'),
       ('kwlog-2', 'kw-2', '2025-10-25 09:05:00', 'acc-3', 'mobile', 'HCMC'),
       ('kwlog-3', 'kw-3', '2025-10-30 08:35:00', 'acc-4', 'web', 'Da Nang'),
       ('kwlog-4', 'kw-4', '2025-11-02 11:10:00', 'acc-5', 'app', 'Hue'),
       ('kwlog-5', 'kw-5', '2025-11-03 12:05:00', 'acc-6', 'web', 'Hanoi'),
       ('kwlog-6', 'kw-6', '2025-10-20 14:15:00', 'acc-7', 'mobile', 'Can Tho'),
       ('kwlog-7', 'kw-7', '2025-11-04 16:10:00', 'acc-8', 'web', 'Hai Phong'),
       ('kwlog-8', 'kw-8', '2025-09-10 08:05:00', 'acc-9', 'app', 'Vinh'),
       ('kwlog-9', 'kw-9', '2025-11-05 09:35:00', 'acc-10', 'web', 'Nha Trang'),
       ('kwlog-10', 'kw-10', '2025-10-01 10:10:00', 'acc-1', 'mobile', 'Hanoi');

-- -------------------------
-- Reviews (10)
-- -------------------------
INSERT INTO Reviews (id, reviewer_id, target_id, target_type, rating, content)
VALUES ('rev-1', 'acc-2', 'acc-3', 'doctor', 5, 'Rất tốt, bác sĩ tận tâm.'),
       ('rev-2', 'acc-3', 'hosp-1', 'hospital', 4, 'Dịch vụ tốt.'),
       ('rev-3', 'acc-4', 'acc-5', 'doctor', 3, 'Khá tốt.'),
       ('rev-4', 'acc-5', 'hosp-2', 'hospital', 5, 'Cơ sở hiện đại.'),
       ('rev-5', 'acc-6', 'acc-7', 'doctor', 2, 'Chờ lâu.'),
       ('rev-6', 'acc-7', 'hosp-3', 'hospital', 4, 'Hài lòng.'),
       ('rev-7', 'acc-8', 'acc-9', 'service', 5, 'Dịch vụ tuyệt vời.'),
       ('rev-8', 'acc-9', 'hosp-4', 'hospital', 3, 'Khá ổn.'),
       ('rev-9', 'acc-10', 'acc-2', 'doctor', 4, 'Tốt.'),
       ('rev-10', 'acc-1', 'hosp-5', 'hospital', 5, 'Xuất sắc.');

-- -------------------------
-- Comments (10)
-- -------------------------
INSERT INTO Comments (id, parent_id, appointment_id, doctor_id, patient_id, account_id, content, rating, level)
VALUES ('cmt-1', NULL, 'appt-1', 'acc-3', 'acc-2', 'acc-2', 'Bác sĩ nhiệt tình', 5, 0),
       ('cmt-2', NULL, 'appt-2', 'acc-4', 'acc-5', 'acc-3', 'Phòng khám sạch sẽ', 4, 0),
       ('cmt-3', NULL, 'appt-3', 'acc-8', 'acc-7', 'acc-4', 'Thái độ phục vụ tốt', 5, 0),
       ('cmt-4', NULL, 'appt-4', 'acc-3', 'acc-9', 'acc-5', 'Chờ lâu nhưng ổn', 3, 0),
       ('cmt-5', 'cmt-1', 'appt-1', 'acc-3', 'acc-2', 'acc-6', 'Cảm ơn phản hồi', 4, 1),
       ('cmt-6', NULL, 'appt-5', 'acc-2', 'acc-10', 'acc-7', 'Đặt lịch dễ dàng', 4, 0),
       ('cmt-7', NULL, 'appt-6', 'acc-8', 'acc-4', 'acc-8', 'Nhân viên thân thiện', 5, 0),
       ('cmt-8', NULL, 'appt-7', 'acc-4', 'acc-6', 'acc-9', 'Hài lòng', 4, 0),
       ('cmt-9', NULL, 'appt-8', 'acc-7', 'acc-8', 'acc-10', 'Rất chuyên nghiệp', 5, 0),
       ('cmt-10', NULL, 'appt-9', 'acc-9', 'acc-2', 'acc-1', 'Cần cải tiến', 3, 0);

-- All done — re-enable FK checks
SET
FOREIGN_KEY_CHECKS = 1;
COMMIT;
