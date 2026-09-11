CREATE TABLE task_tags
(
    task_id BIGINT       NOT NULL,
    tag     VARCHAR(255) NOT NULL,

    CONSTRAINT fk_task_tags_task
        FOREIGN KEY (task_id)
            REFERENCES tasks (id)
            ON DELETE CASCADE
);