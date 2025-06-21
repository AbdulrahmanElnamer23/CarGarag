
DROP DATABASE IF EXISTS car_db;
CREATE DATABASE car_db;
USE car_db;

-- Create Users Table for Cashiers & Admins
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,  -- Store hashed passwords
                       role ENUM('Admin', 'Cashier') NOT NULL
);

CREATE TABLE inventory (
    inventory_id INT PRIMARY KEY,
    supplier_name VARCHAR(100),
    restock_date DATE,
    quantity_added INT
);

CREATE TABLE products (
    product_id INT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    tyype VARCHAR(100),
    expiry_date DATE,
    warranty_date DATE,
    price DECIMAL(10,2),
    inventory_id INT

);

CREATE TABLE customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    address TEXT,
    phone VARCHAR(20)
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY ,
    order_date DATE,
    total_amount DECIMAL(10,2),
    status VARCHAR(50),
    customer_id INT

);

