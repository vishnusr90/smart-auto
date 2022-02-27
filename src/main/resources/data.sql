CREATE TABLE IF NOT EXISTS CAR (
    id varchar(225) primary key,
    brand varchar(10) not null,
    model varchar(10) not null,
    color varchar(10) not null,
    price bigint not null,
    year varchar(10), 
    created_on timestamp,
    modified_on timestamp
);

INSERT INTO CAR (id, brand, model, color, price, year, created_on) 
VALUES (1, 'BMW', 'X1', 'White', 350000, '2022', now());

INSERT INTO CAR (id, brand, model, color, price, year, created_on) 
VALUES (2, 'Audi', 'Q8', 'Red', 150000, '2020', now());

INSERT INTO CAR (id, brand, model, color, price, year, created_on) 
VALUES (3, 'Benz', 'GLE', 'Blue', 200000, '2021', now());

INSERT INTO CAR (id, brand, model, color, price, year, created_on) 
VALUES (4, 'Maruti', 'Celerio', 'White', 100000, '1999', now());

INSERT INTO CAR (id, brand, model, color, price, year, created_on) 
VALUES (5, 'BMW', 'X2', 'Black', 450000, '2022', now());

-----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CAR_INVENTORY (
    id varchar(225) primary key,
    remaining int default 0,
    car_id varchar(225)
);

ALTER TABLE CAR_INVENTORY
ADD FOREIGN KEY (car_id)
REFERENCES CAR(id);

INSERT INTO CAR_INVENTORY (ID, REMAINING, CAR_ID)
VALUES (1, 3, SELECT ID FROM CAR WHERE BRAND = 'BMW' and model = 'X1' and color = 'White' and year = '2022');

INSERT INTO CAR_INVENTORY (ID, REMAINING, CAR_ID)
VALUES (2, 2, SELECT ID FROM CAR WHERE BRAND = 'Audi' and model = 'Q8' and color = 'Red' and year = '2020');

INSERT INTO CAR_INVENTORY (ID, REMAINING, CAR_ID)
VALUES (3, 1, SELECT ID FROM CAR WHERE BRAND = 'Benz' and model = 'GLE' and color = 'Blue' and year = '2021');

INSERT INTO CAR_INVENTORY (ID, REMAINING, CAR_ID)
VALUES (4, 5, SELECT ID FROM CAR WHERE BRAND = 'Maruti' and model = 'Celerio' and color = 'White' and year = '1999');

INSERT INTO CAR_INVENTORY (ID, REMAINING, CAR_ID)
VALUES (5, 4, SELECT ID FROM CAR WHERE BRAND = 'BMW' and model = 'X2' and color = 'Black' and year = '2022');

------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS USER (
    id varchar(225) primary key,
    username varchar(10) not null,
    password varchar(20) not null,
    firstname varchar(20) not null,
    created_on timestamp
);

INSERT INTO USER (id, username, password, firstname, created_on) 
VALUES (1, 'admin','admin', 'Admin', now());

INSERT INTO USER (id, username, password, firstname, created_on) 
VALUES (2, 'user', 'pass', 'User', now());

INSERT INTO USER (id, username, password, firstname, created_on) 
VALUES (3, 'user2', 'pass', 'User 2', now());

INSERT INTO USER (id, username, password, firstname, created_on) 
VALUES (4, 'user3', 'pass', 'User 3', now());

--------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS SALES (
    id varchar(225) primary key,
    user_id varchar(225) not null,
    car_id varchar(2225) not null,
    sold_on timestamp
);

ALTER TABLE SALES
ADD FOREIGN KEY (user_id) REFERENCES USER(id);

ALTER TABLE SALES
ADD FOREIGN KEY (car_id) REFERENCES CAR(id);

----------------------------------------------------------------------------------------
