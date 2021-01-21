-- INSERTS TO TEST FUNNCTIONS
INSERT INTO platform_user VALUES('superadmin1@lapr', 'sadimn1', 'Tiago Pais');
INSERT INTO super_admin VALUES('superadmin1@lapr');
INSERT INTO platform_user VALUES('admin1@lapr3.com', 'admin1', 'Jose Gome');
INSERT INTO administrator VALUES('admin1@lapr3.com');
INSERT INTO geographical_point VALUES(42.5657,45.32323, 200, 'Aliados');
INSERT INTO address VALUES(42.5657,45.32323,'Rua dos Aliados', 'Porto', 5, '4346-456');
INSERT INTO pharmacy(phone_number, designation, administrator_email, longitude, latitude) VALUES(923445542, 'Farmacia 2', 'admin1@lapr3.com', 42.5657, 45.32323);
INSERT INTO platform_user VALUES('clien1@lapr3.com', 'client1', 'Paula Gomes');
INSERT INTO platform_client VALUES ('clien1@lapr3.com', 912541737, 23, 42.5657, 45.32323, 4565); 
INSERT INTO platform_user VALUES('courier1@lapr3.com', '123', 'Tiago Pereira');
Insert INTO courier VALUES('courier1@lapr3.com', 123, 232, 65.7, 1);
INSERT INTO vehicle_category VALUES ('Scooter');
INSERT INTO vehicle_category VALUES ('Drone');
INSERT INTO vehicle (weight, status, max_battery, current_battery, motor, max_weight, pharmacy_id, vehicle_category) VALUES (35, 'Locked', 200, 200, 200, 10, 1,'Scooter');
INSERT INTO scooter VALUES (1, 0.7, 0.5);
INSERT INTO DELIVERY_RUN(vehicle_category) VALUES ('Scooter');
INSERT INTO geographical_point(longitude, latitude, elevation) VALUES (42.34, 56.3, 0.6);
INSERT INTO geographical_point(longitude, latitude, elevation) VALUES (69.4, 52.6, 0.8);