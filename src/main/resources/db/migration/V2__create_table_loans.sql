CREATE TABLE authors (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- 2. Cria a tabela de Usuários (Caso ainda não tenha)
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

-- 3. Arruma a tabela BOOKS (Adiciona o autor)
ALTER TABLE books
ADD COLUMN author_id BIGINT;

ALTER TABLE books
ADD CONSTRAINT fk_books_author
FOREIGN KEY (author_id) REFERENCES authors(id);

-- 4. Cria a tabela LOANS (É aqui que ficam as chaves estrangeiras!)
CREATE TABLE loans (
    id BIGSERIAL PRIMARY KEY,
    loan_price NUMERIC(10,2),
    loan_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    return_date DATE,
    
    -- Chaves Estrangeiras (Quem pega emprestado e O que é emprestado)
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,

    -- Constraints (Regras)
    CONSTRAINT fk_loans_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_loans_book FOREIGN KEY (book_id) REFERENCES books(id),
    CONSTRAINT check_loan_price_positive CHECK (loan_price >= 0),
    CONSTRAINT check_expiration_future CHECK (expiration_date > CURRENT_DATE)
);