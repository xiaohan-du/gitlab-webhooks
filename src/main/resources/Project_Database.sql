-- -----------------------------------------------------
-- Set User Privilege
-- -----------------------------------------------------

DROP USER 'c21127478'@'localhost';
CREATE USER 'c21127478'@'localhost' IDENTIFIED BY 'comsc';
GRANT SELECT, UPDATE, INSERT ON webhook.* TO 'c21127478'@'localhost';

-- -----------------------------------------------------
-- Initialization
-- -----------------------------------------------------
-- =====================================================
DROP TABLE IF EXISTS webhook;

-- -----------------------------------------------------
-- Table webhook
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS webhook
(
    id int(11) NOT NULL AUTO_INCREMENT,
    object_kind VARCHAR(255),
    time_stamp VARCHAR(255),
    PRIMARY KEY (`id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS project;

-- -----------------------------------------------------
-- Table project
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS project
(
    webhook_id int(11) NOT NULL,
    project_id int(11) NOT NULL,
    homepage VARCHAR(255),
    default_branch VARCHAR(255),
    name VARCHAR(255),
    ref VARCHAR(255),
    description VARCHAR(255),
    web_url VARCHAR(255),
    git_ssh_url VARCHAR(255),
    git_http_url VARCHAR(255),
    visibility_level INT(11),
    url VARCHAR(255),
    ssh_url VARCHAR(255),
    http_url VARCHAR(255),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS object_attributes;

-- -----------------------------------------------------
-- Table object_attributes
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS object_attributes
(
    webhook_id int(11) NOT NULL,
    object_attributes_id int(11) NOT NULL,
    created_at VARCHAR(255),
    updated_at VARCHAR(255),
    description VARCHAR(255),
    url TEXT,
    updated_by_id INT(11),
    author_id INT(11),
    resolved_by_push VARCHAR(255),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS author;

-- -----------------------------------------------------
-- Table author
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS author
(
    merge_request_id int(11) NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS last_commit;

-- -----------------------------------------------------
-- Table last_commit
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS last_commit
(
    merge_request_id int(11) NOT NULL,
    last_commit_id int(11),
    author VARCHAR(255),
    message VARCHAR(255),
    title VARCHAR(255),
    url VARCHAR(255),
    timestamp VARCHAR(255),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS merge_params;

-- -----------------------------------------------------
-- Table merge_params
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS merge_params
(
    merge_request_id int(11) NOT NULL,
    merge_params_id int(11),
    should_remove_source_branch TINYINT(1),
    force_remove_source_branch int(11),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS repository;

-- -----------------------------------------------------
-- Table repository
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS repository
(
    merge_request_id int(11) NOT NULL,
    repository_id int(11),
    name VARCHAR(255),
    description VARCHAR(255),
    url VARCHAR(255),
    homepage VARCHAR(255),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS source;

-- -----------------------------------------------------
-- Table source
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS source
(
    merge_request_id int(11) NOT NULL,
    source_id int(11),
    path_with_namespace VARCHAR(255),
    ssh_url VARCHAR(255),
    description VARCHAR(255),
    git_http_url VARCHAR(255),
    git_ssh_url VARCHAR(255),
    url VARCHAR(255),
    http_url VARCHAR(255),
    ci_config_path VARCHAR(255),
    web_url VARCHAR(255),
    avatar_url VARCHAR(255),
    name VARCHAR(255),
    namespace VARCHAR(255),
    visibility_level int(11),
    default_branch VARCHAR(255),
    homepage VARCHAR(255),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS target;

-- -----------------------------------------------------
-- Table target
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS target
(
    merge_request_id int(11) NOT NULL,
    target_id int(11),
    path_with_namespace VARCHAR(255),
    ssh_url VARCHAR(255),
    description VARCHAR(255),
    git_http_url VARCHAR(255),
    git_ssh_url VARCHAR(255),
    url VARCHAR(255),
    http_url VARCHAR(255),
    ci_config_path VARCHAR(255),
    web_url VARCHAR(255),
    avatar_url VARCHAR(255),
    name VARCHAR(255),
    namespace VARCHAR(255),
    visibility_level int(11),
    default_branch VARCHAR(255),
    homepage VARCHAR(255),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS user;

-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS user
(
    merge_request_id int(11) NOT NULL,
    user_id int(11),
    avatar_url VARCHAR(255),
    email VARCHAR(255),
    username VARCHAR(255),
    PRIMARY KEY (`webhook_id`)
)
engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS merge_request_object_attributes;

-- -----------------------------------------------------
-- Table merge_request_object_attributes
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS merge_request_object_attributes
(
    merge_request_id int(11) NOT NULL,
    merge_request_object_attributes_id int(11),
    merge_when_pipeline_succeeds TINYINT(1),
    last_commit_id int(11),
    detailed_merge_status VARCHAR(255),
    iid int(11),
    merge_user_id VARCHAR(255),
    milestone_id VARCHAR(255),
    created_at VARCHAR(255),
    description VARCHAR(255),
    source_id int(11),
    merge_params_id int(11),
    target_id int(11),
    PRIMARY KEY (`webhook_id`),
    FOREIGN KEY (`merge_request_object_attributes_id`) REFERENCES `object_attributes`(`webhook_id`),
    FOREIGN KEY (`last_commit_id`) REFERENCES `last_commit`(`webhook_id`),
    FOREIGN KEY (`source_id`) REFERENCES `source`(`webhook_id`),
    FOREIGN KEY (`merge_params_id`) REFERENCES `merge_params`(`webhook_id`),
    FOREIGN KEY (`target_id`) REFERENCES `target`(`webhook_id`)
    )
    engine = InnoDB;

-- =====================================================
DROP TABLE IF EXISTS merge_request;

-- -----------------------------------------------------
-- Table merge_request
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS merge_request
(
    webhook_id int(11) NOT NULL,
    merge_request_id int(11) NOT NULL,
    merge_request_object_attributes_id int(11) NOT NULL,
    changes VARCHAR(255),
    project_id int(11) NOT NULL,
    repository_id int(11) NOT NULL,
    user_id int(11) NOT NULL,
    object_kind VARCHAR(255),
    PRIMARY KEY (`webhook_id`),
    FOREIGN KEY (`merge_request_object_attributes_id`) REFERENCES `merge_request_object_attributes`(`webhook_id`),
    FOREIGN KEY (`project_id`) REFERENCES `project`(`webhook_id`),
    FOREIGN KEY (`repository_id`) REFERENCES `repository`(`webhook_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`webhook_id`)
)
engine = InnoDB;
