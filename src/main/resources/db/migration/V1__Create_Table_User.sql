CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_users
(
    user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name  VARCHAR(100),
    email  VARCHAR(100),
    password_hash  VARCHAR(255),
    confirm_email SMALLINT DEFAULT 0,
    user_image  TIMESTAMP
);