-- Schema for My Book Store
CREATE DATABASE IF NOT EXISTS BookStore;
USE BookStore;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS sales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT NOT NULL,
    quantity_sold INT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sales_book FOREIGN KEY (book_id) REFERENCES books(id)
);

-- Seed demo user
INSERT INTO users (username, password)
VALUES ('admin', 'admin123') AS new_user
ON DUPLICATE KEY UPDATE username = new_user.username;

-- Seed sample books
INSERT INTO books (title, author, price, quantity) VALUES
('The Pragmatic Programmer', 'Andrew Hunt', 4500, 5),
('Clean Code', 'Robert C. Martin', 5000, 4),
('Effective Java', 'Joshua Bloch', 6000, 3) AS new_book
ON DUPLICATE KEY UPDATE
    title = new_book.title,
    author = new_book.author,
    price = new_book.price,
    quantity = new_book.quantity;

