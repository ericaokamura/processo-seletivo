DROP DATABASE IF EXISTS app_logs;
CREATE DATABASE app_logs;
DROP TABLE IF EXISTS LOG;
CREATE TABLE LOG(id VARCHAR(100) PRIMARY KEY NOT NULL, date TIMESTAMP NOT NULL, ip VARCHAR(15) NOT NULL, request VARCHAR(50) NOT NULL, status INT NOT NULL, user_agent VARCHAR(250) NOT NULL);