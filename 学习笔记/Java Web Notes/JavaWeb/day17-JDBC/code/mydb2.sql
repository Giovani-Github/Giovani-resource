/*创建数据库*/
DROP DATABASE mydb2;
CREATE DATABASE mydb2;
/*切换数据库*/
USE mydb2;
/*创建表，列名加小撇是避免与关键字重复，创建表之后，小撇不会保留*/
CREATE TABLE `user` (
  username VARCHAR(50),
  `password` VARCHAR(50)
);


