INSERT INTO platform_user VALUES('admin1@lapr3', 'admin1', 'Jose Gome');
INSERT INTO administrator VALUES('admin1@lapr3');
INSERT INTO geographical_point VALUES(42.5657,45.32323, 200, 'Aliados');
INSERT INTO address VALUES(42.5657,45.32323,'Rua dos Aliados', 'Porto', 5, '4346-456');
INSERT INTO pharmacy(phone_number, designation, administrator_email, longitude, latitude) VALUES(923445542, 'Farmacia 2', 'admin1@lapr3', 42.5657, 45.32323);
INSERT INTO platform_user VALUES('clien1@lapr3', 'client1', 'Paula Gomes');
INSERT INTO platform_client VALUES ('clien1@lapr3', 912541737, 23, 42.5657, 45.32323); 