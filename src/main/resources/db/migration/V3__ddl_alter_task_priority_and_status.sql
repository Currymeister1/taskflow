ALTER TABLE tasks ALTER COLUMN status TYPE INTEGER USING status::integer;

ALTER TABLE tasks ALTER COLUMN status TYPE INTEGER USING priority::integer;