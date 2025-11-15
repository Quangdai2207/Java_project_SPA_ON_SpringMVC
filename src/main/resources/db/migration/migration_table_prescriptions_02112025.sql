CREATE TABLE IF NOT EXISTS `Prescription` (
    `id` varchar(255) primary key unique,
    `name` varchar(100) not null,
    `doctor_id` varchar(255) not null,
    `patient_id` varchar(255) not null,
    `dosage` decimal not null,
    `frequence` decimal not null,
    `note` varchar(255) default null,
    `created_at` datetime default current_timestamp,
    `is_updated` datetime default current_timestamp,
    `is_deleted` boolean default false
    )

-- ** Prescription with Accounts
ALTER TABLE `Prescription`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,