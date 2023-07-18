-- -----------------------------------------------------
-- Set User Privilege
-- -----------------------------------------------------

DROP USER 'c21127478'@'localhost';
CREATE USER 'c21127478'@'localhost' IDENTIFIED BY 'comsc';
GRANT SELECT, UPDATE, INSERT ON webhook.* TO 'c21127478'@'localhost';

-- -----------------------------------------------------
-- Initialization
-- -----------------------------------------------------

DROP TABLE IF EXISTS webhook;

-- -----------------------------------------------------
-- Table webhook
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS webhook
(
    id int(11) NOT NULL AUTO_INCREMENT,
    object_kind VARCHAR(64),
    event_name VARCHAR(64),
    before_hash VARCHAR(64),
    after_hash VARCHAR(64),
    ref VARCHAR(64),
    checkout_sha VARCHAR(64),
    user_id INT(11),
    user_username VARCHAR(64),
    project_id INT(11),
    total_commits_count INT(11),
    time_stamp VARCHAR(64),
    PRIMARY KEY (`id`)
    )
    engine = InnoDB;
