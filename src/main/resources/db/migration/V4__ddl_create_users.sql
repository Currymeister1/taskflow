CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(255) NOT null UNIQUE,
    password	VARCHAR(255) NOT null
);