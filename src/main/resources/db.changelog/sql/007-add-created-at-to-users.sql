-- liquibase formatted sql

-- changeset nemanja:007-add-created-at-to-users
ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;