create database swingapp;
use swingapp;
CREATE TABLE employee(
    id int not null primary key auto_increment,
    email varchar(55) not null,
    password varchar(55) not null
);

INSERT INTO employee(email, password) VALUES('bushasn', '12345');
INSERT INTO employee(email, password) VALUES('bharath', '12345');
INSERT INTO employee(email, password) VALUES('HoangCongLap', '123');