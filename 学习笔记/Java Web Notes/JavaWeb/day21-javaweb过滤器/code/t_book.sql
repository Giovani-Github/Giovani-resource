CREATE TABLE t_book(
  bid CHAR(32) PRIMARY KEY,
  bname VARCHAR(100),
  price NUMERIC(10,2),
  category INT
);

INSERT INTO t_book VALUES('b1', 'JavaSE_1', 10, 1);
INSERT INTO t_book VALUES('b2', 'JavaSE_2', 15, 1);
INSERT INTO t_book VALUES('b3', 'JavaSE_3', 20, 1);
INSERT INTO t_book VALUES('b4', 'JavaSE_4', 25, 1);

INSERT INTO t_book VALUES('b5', 'JavaEE_1', 30, 2);
INSERT INTO t_book VALUES('b6', 'JavaEE_2', 35, 2);
INSERT INTO t_book VALUES('b7', 'JavaEE_3', 40, 2);

INSERT INTO t_book VALUES('b8', 'Java_framework_1', 45, 3);
INSERT INTO t_book VALUES('b9', 'Java_framework_2', 50, 3);

SELECT * FROM t_book;