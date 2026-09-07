CREATE TABLE tasks
(
    id          BIGSERIAL PRIMARY KEY,
    created_at  DATE         NOT NULL,
    description TEXT,
    due_date    DATE,
    priority    VARCHAR(50),
    status      VARCHAR(50),
    title       VARCHAR(255) NOT NULL
);