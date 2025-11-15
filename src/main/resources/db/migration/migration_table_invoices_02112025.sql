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

-- ** Invoice with Accounts
ALTER TABLE `Invoice`
    ADD FOREIGN KEY(`doctor_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,
    ADD FOREIGN KEY(`patient_id`) REFERENCES `Accounts`(`id`)
        ON UPDATE CASCADE ON DELETE SET NULL,