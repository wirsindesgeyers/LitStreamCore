
-- 1. Criando autor generico
INSERT INTO authors (name, birth_date) 
VALUES ('Autor Desconhecido', '1900-01-01');

-- 2. Inserindo o ID desse novo autor nos livros nulos
UPDATE books
SET author_id = (SELECT id FROM authors WHERE name = 'Autor Desconhecido' LIMIT 1)
WHERE author_id IS NULL;

-- 3. Definindo como não-nulo
ALTER TABLE books
ALTER COLUMN author_id SET NOT NULL;
