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


DROP TABLE IF EXISTS project;

-- -----------------------------------------------------
-- Table project
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS project
(
    webhookId int(11) NOT NULL,
    id int(11) NOT NULL,
    homepage VARCHAR(64),
    default_branch VARCHAR(64),
    name VARCHAR(64),
    ref VARCHAR(64),
    description VARCHAR(64),
    web_url VARCHAR(64),
    git_ssh_url VARCHAR(64),
    git_http_url VARCHAR(64),
    visibility_level INT(11),
    url VARCHAR(64),
    ssh_url VARCHAR(64),
    http_url VARCHAR(64),
    PRIMARY KEY (`webhookId`)
    )
    engine = InnoDB;
