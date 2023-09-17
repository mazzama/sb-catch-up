create table if not exists notes (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title varchar(255),
    text varchar(255),
    created_at timestamp,
    updated_at timestamp
);