
-- CAR
--     - id
--     - brand
--     - year
--     - price


CREATE TABLE CAR (
    id varchar(225) primary key,
    brand varchar(10) not null,
    model varchar(10) not null,
    top_speed int,
    price bigint not null,
    year varchar(10), 
    sold_status varchar(5),
    created_on timestamp,
    modified_on timestamp,
    sold_on timestamp
);


INSERT INTO CAR (id, brand, model, top_speed, price, year, sold_status, created_on) 
VALUES (1, 'BMW', 'X1', 330, 350000, '2022', 'OPEN', now());
INSERT INTO CAR (id, brand, model, top_speed, price, year, sold_status, created_on) 
VALUES (2, 'Audi', 'Q8', 400, 150000, '2020', 'OPEN', now());
INSERT INTO CAR (id, brand, model, top_speed, price, year, sold_status, created_on) 
VALUES (3, 'Benz', 'GLE', 280, 200000, '2022', 'OPEN', now());
INSERT INTO CAR (id, brand, model, top_speed, price, year, sold_status, created_on) 
VALUES (4, 'Maruti', 'Celerio', 200, 100000, '1999', 'OPEN', now());



-- CREATE TABLE USER (
--     id varchar(225) primary key,
--     firstname varchar(20) not null,
--     lastname varchar(20),
--     username varchar(10) not null,
--     `password` varchar(20) not null,
-- );

-- CREATE TABLE ROLES (
--     id varchar(225) primary key auto_increment,
--     name varchar(10) not null
-- );

-- CREATE TABLE USER_ROLES (
    
-- )