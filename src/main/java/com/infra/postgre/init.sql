-- Cria o schema correto
CREATE SCHEMA IF NOT EXISTS ordefin;

-- Define o schema para a sessão atual
SET search_path TO ordefin;

-- Agora crie as tabelas dentro do schema "ordefin"
CREATE TABLE IF NOT EXISTS users (
                                     user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                     name VARCHAR(256) NOT NULL,
                                     email VARCHAR(256) UNIQUE NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     cpf VARCHAR(11) UNIQUE NOT NULL,
                                     birthday DATE NOT NULL,
                                     CONSTRAINT cpf_length CHECK (char_length(cpf) = 11)
);

CREATE TABLE IF NOT EXISTS transaction_category (
                                                    category_id INT PRIMARY KEY,
                                                    name VARCHAR(255) NOT NULL
);

CREATE TYPE IF NOT EXISTS transaction_type AS ENUM ('INCOME', 'EXPENSE');

CREATE TABLE IF NOT EXISTS transaction (
                                           transaction_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                           amount DOUBLE PRECISION NOT NULL,
                                           date DATE NOT NULL,
                                           type transaction_type NOT NULL,
                                           category_id INT NOT NULL REFERENCES transaction_category(category_id) ON DELETE CASCADE,
                                           user_id UUID NOT NULL REFERENCES users(user_id),
                                           description VARCHAR(255)
);
