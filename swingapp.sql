create database swingapp;
use swingapp;
CREATE TABLE account(
    id int not null primary key auto_increment,
    username varchar(55) not null,
    password varchar(55) not null
);
select *from account;
INSERT INTO account(username, password) VALUES('bushasn', '12345');
INSERT INTO account(username, password) VALUES('bharath', '12345');
INSERT INTO account(username, password) VALUES('HoangCongLap', '123');

CREATE TABLE customer(
    idCustomer int,
    name varchar(55) not null,
    gender varchar(10) not null,
	cardMoney int,
	moneyTotal int 
);
select *from customer;
INSERT INTO customer(idCustomer, name,gender,cardMoney,moneyTotal) VALUES(01, 'Hoang Cong Lap','male',10000,500);
INSERT INTO customer(idCustomer, name,gender,cardMoney,moneyTotal) VALUES(02, 'Surri','male',2000,600);

CREATE TABLE rooms(
    idPhong varchar(55) not null,
    tenPhong varchar(55) not null,
    soNguoi varchar(10) not null 
);
select *from rooms;
INSERT INTO rooms(idPhong, tenPhong,soNguoirooms) VALUES('0111', 'AAAA','4');
INSERT INTO rooms(idPhong, tenPhong,soNguoi) VALUES('0222', 'BBBB','2');
INSERT INTO rooms(idPhong, tenPhong,soNguoi) VALUES('0333', 'CCCC','3');


SELECT * FROM customer WHERE idCustomer = 01 and name = 'Hoang Cong Lap';


select * from rooms;








