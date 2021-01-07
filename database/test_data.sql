INSERT INTO platform_user VALUES('admin1@lapr3', 'admin1', 'Jose Gome');
INSERT INTO administrator VALUES('admin1@lapr3');
INSERT INTO address VALUES(42.5657,45.32323,'Rua dos Aliados', 'Porto', 5, '4346-456');
INSERT INTO phamarcy VALUES(2, 923445542, 'Farmacia 2', 'admin1@lapr3', 42.5657, 45.32323);
INSERT INTO platform_user VALUES('clien1@lapr3', 'client1', 'Paula Gomes');
INSERT INTO platform_client VALUES ('clien1@lapr3', 912541737, 23, 42.5657, 45.32323);
INSERT INTO delivery_order(order_id, begin_date, end_date, status, price, client_email, phamarcy_id) VALUES (3, TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2014-08-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'), 'Processed', 34.95,'clien1@lapr3', 2); 