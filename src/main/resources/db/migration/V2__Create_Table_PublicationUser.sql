CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tb_publication_user
(
    publication_user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    text VARCHAR(1000),
    publication_date TIMESTAMP NOT NULL,
    publication_img VARCHAR(1000),
    comments_count INTEGER DEFAULT 0,
    user_id UUID NOT NULL,

    CONSTRAINT fk_publication_user_users FOREIGN KEY (user_id) REFERENCES tb_users (user_id) ON DELETE CASCADE
);