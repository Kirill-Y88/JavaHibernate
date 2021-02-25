BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
('bread', 10),
('milk', 20),
('cheese', 100),
('orange', 50),
('beer', 500),
('potato', 550),
('onion', 400);

COMMIT;