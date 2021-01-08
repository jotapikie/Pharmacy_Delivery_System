DROP TABLE platform_user              	CASCADE CONSTRAINTS PURGE;
DROP TABLE super_admin  		        CASCADE CONSTRAINTS PURGE;
DROP TABLE platform_client              CASCADE CONSTRAINTS PURGE;
DROP TABLE credit_card                  CASCADE CONSTRAINTS PURGE;
DROP TABLE administrator       	        CASCADE CONSTRAINTS PURGE;
DROP TABLE courier                      CASCADE CONSTRAINTS PURGE;
DROP TABLE phamarcy   	            	CASCADE CONSTRAINTS PURGE;
DROP TABLE vehicle     		            CASCADE CONSTRAINTS PURGE;
DROP TABLE scooter       	            CASCADE CONSTRAINTS PURGE;
DROP TABLE product         	            CASCADE CONSTRAINTS PURGE;
DROP TABLE park   	 	                CASCADE CONSTRAINTS PURGE;
DROP TABLE park_slot      	            CASCADE CONSTRAINTS PURGE;
DROP TABLE address       	            CASCADE CONSTRAINTS PURGE;
DROP TABLE invoice       	            CASCADE CONSTRAINTS PURGE;
DROP TABLE delivery_order       		CASCADE CONSTRAINTS PURGE;
DROP TABLE delivery_run       	        CASCADE CONSTRAINTS PURGE;
DROP TABLE order_product       	        CASCADE CONSTRAINTS PURGE;
DROP TABLE phamarcy_product             CASCADE CONSTRAINTS PURGE;
DROP TABLE path                         CASCADE CONSTRAINTS PURGE;

CREATE TABLE platform_user(
    user_email varchar(255) CONSTRAINT pk_user_email PRIMARY KEY,
    user_password varchar(255) NOT NULL, 
    user_name varchar(255) NOT NULL
);

CREATE TABLE phamarcy(
    phamarcy_id int CONSTRAINT pk_phamarcy_id PRIMARY KEY,
    phone_number int NOT NULL,
    designation varchar(255) NOT NULL,
    administrator_email varchar(255) NOT NULL CONSTRAINT uk_phamarcy_administrator UNIQUE,
    longitude float NOT NULL,
    latitude float NOT NULL
);

CREATE TABLE administrator(
    email varchar(255) CONSTRAINT pk_administrator_email PRIMARY KEY
);

CREATE TABLE platform_client(
    email varchar(255) CONSTRAINT pk_client_email PRIMARY KEY, 
    phone_number int NOT NULL, 
    credits float NOT NULL, 
    longitude float NOT NULL, 
    latitude float NOT NULL
);

CREATE TABLE courier(
    email varchar(255) CONSTRAINT pk_courier_email PRIMARY KEY, 
    nif int NOT NULL, 
    nss int NOT NULL, 
    max_weight float NOT NULL, 
    phamarcy_id int NOT NULL
);

CREATE TABLE credit_card(
    nr int CONSTRAINT pk_credit_card_nr PRIMARY KEY, 
    validity_date date NOT NULL, 
    cvv int NOT NULL,
    owner_email varchar(255)
);

CREATE TABLE address(
    longitude float NOT NULL,
    latitude float NOT NULL,
    street varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    port_number int,
    zip_code varchar(255) NOT NULL, 
    CONSTRAINT pk_address_longitude_latitude PRIMARY KEY (longitude, latitude)
);

CREATE TABLE product(
    product_id int CONSTRAINT pk_product_id PRIMARY KEY,
    designation varchar(255) NOT NULL,
    weight float NOT NULL,
    price float NOT NULL
);

CREATE TABLE super_admin(
    email varchar(255) CONSTRAINT pk_super_admin_email PRIMARY KEY
);

CREATE TABLE vehicle(
    nr int CONSTRAINT pk_vehicle_nr PRIMARY KEY,
    weight float NOT NULL,
    status varchar(255) NOT NULL,
    max_battery int NOT NULL,
    current_battery int NOT NULL,
    motor int NOT NULL,
    phamarcy_id int NOT NULL
);

CREATE TABLE scooter(
    vehicle_nr int CONSTRAINT pk_scooter_nr PRIMARY KEY,
    aero_coef float NOT NULL,
    frontal_area float NOT NULL
);

CREATE TABLE park(
    park_id int CONSTRAINT pk_park_id PRIMARY KEY,
    max_vehicles int NOT NULL,
    phamarcy_id int NOT NULL
);

CREATE TABLE park_slot(
     slot_id int CONSTRAINT pk_park_slot_id PRIMARY KEY, 
     able_to_charge NUMBER NOT NULL,
     park_id int NOT NULL,
     vehicle_nr int
);

CREATE TABLE delivery_order(
    order_id int CONSTRAINT pk_order_id PRIMARY KEY,
    begin_date timestamp NOT NULL,
    end_date timestamp NULL,
    status varchar(255) NOT NULL,
    price float NOT NULL,
    client_email varchar(255) NOT NULL,
    delivery_run_id int,
    phamarcy_id int NOT NULL
);

CREATE TABLE invoice(
    client_email varchar(255) NOT NULL,
    order_id int NOT NULL,
    nif int NOT NULL, 
    CONSTRAINT pk_invoice_order_client PRIMARY KEY(client_email, order_id)
);

CREATE TABLE order_product(
    product_id int NOT NULL,
    order_id int NOT NULL,
    quantity int NOT NULL, 
    CONSTRAINT pk_order_product PRIMARY KEY(product_id, order_id)
);

CREATE TABLE phamarcy_product(
    phamarcy_id int NOT NULL,
    product_id int NOT NULL,
    quantity int NOT NULL,
    CONSTRAINT pk_phamarcy_product PRIMARY KEY(phamarcy_id, product_id)
);

CREATE TABLE delivery_run(
    delivery_run_id int CONSTRAINT pk_delivery_run_id PRIMARY KEY,
    distance float NOT NULL,
    energy float NOT NULL,
    start_date timestamp NOT NULL,
    end_dat timestamp NULL,
    courier_email varchar(255) NOT NULL,
    vehicle_nr int NOT NULL
);

CREATE TABLE path(
    longitude1 float NOT NULL,
    latitude1 float NOT NULL,
    longitude2 float NOT NULL,
    latitude2 float NOT NULL,
    distance float NOT NULL,
    street varchar(255),
    CONSTRAINT fk_path_address1_address2 PRIMARY KEY(longitude1, latitude1, longitude2, latitude2)
);

ALTER TABLE administrator ADD CONSTRAINT fk_administrator_email FOREIGN KEY (email) REFERENCES platform_user (user_email);
ALTER TABLE platform_client ADD CONSTRAINT fk_platform_client_email FOREIGN KEY (email) REFERENCES platform_user (user_email);
ALTER TABLE courier ADD CONSTRAINT fk_courier_email FOREIGN KEY (email) REFERENCES platform_user (user_email);
ALTER TABLE courier ADD CONSTRAINT fk_courier_phamarcy FOREIGN KEY (phamarcy_id) REFERENCES phamarcy (phamarcy_id);
ALTER TABLE phamarcy ADD CONSTRAINT fk_phamarcy_address FOREIGN KEY (longitude, latitude) REFERENCES address (longitude, latitude);
ALTER TABLE platform_client ADD CONSTRAINT fk_client_address FOREIGN KEY (longitude, latitude) REFERENCES address (longitude, latitude);
ALTER TABLE super_admin ADD CONSTRAINT fk_super_admin_email FOREIGN KEY (email) REFERENCES platform_user (user_email);
ALTER TABLE phamarcy ADD CONSTRAINT fk_phamarcy_administrator FOREIGN KEY (administrator_email) REFERENCES administrator (email);
ALTER TABLE credit_card ADD CONSTRAINT fk_credit_card_owner FOREIGN KEY (owner_email) REFERENCES platform_client (email);
ALTER TABLE scooter ADD CONSTRAINT fk_scooter_nr FOREIGN KEY (vehicle_nr) REFERENCES vehicle (nr);
ALTER TABLE vehicle ADD CONSTRAINT fk_vehicle_phamarcy FOREIGN KEY (phamarcy_id) REFERENCES phamarcy (phamarcy_id);
ALTER TABLE park ADD CONSTRAINT fk_park_phamarcy FOREIGN KEY (phamarcy_id) REFERENCES phamarcy (phamarcy_id);
ALTER TABLE park_slot ADD CONSTRAINT fk_park_slot_id FOREIGN KEY (park_id) REFERENCES park (park_id);
ALTER TABLE park_slot ADD CONSTRAINT fk_park_slot_vehicle_parked FOREIGN KEY (vehicle_nr) REFERENCES vehicle (nr);
ALTER TABLE delivery_order ADD CONSTRAINT fk_delivery_order_client FOREIGN KEY (client_email) REFERENCES platform_client (email);
ALTER TABLE invoice ADD CONSTRAINT fk_invoice_client FOREIGN KEY (client_email) REFERENCES platform_client (email);
ALTER TABLE invoice ADD CONSTRAINT fk_invoice_order FOREIGN KEY (order_id) REFERENCES delivery_order (order_id);
ALTER TABLE order_product ADD CONSTRAINT fk_order_product_pid FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE order_product ADD CONSTRAINT fk_order_product_oid FOREIGN KEY (order_id) REFERENCES delivery_order (order_id);
ALTER TABLE delivery_run ADD CONSTRAINT fk_delivery_run_courier FOREIGN KEY (courier_email) REFERENCES courier (email);
ALTER TABLE delivery_run ADD CONSTRAINT fk_delivery_run_vehicle FOREIGN KEY (vehicle_nr) REFERENCES vehicle (nr);
ALTER TABLE delivery_order ADD CONSTRAINT fk_delivery_order_run FOREIGN KEY (delivery_run_id) REFERENCES delivery_run (delivery_run_id);
ALTER TABLE phamarcy_product ADD CONSTRAINT fk_phamarcy_product_phaid FOREIGN KEY (phamarcy_id) REFERENCES phamarcy (phamarcy_id);
ALTER TABLE phamarcy_product ADD CONSTRAINT fk_phamarcy_product_prdid FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE delivery_order ADD CONSTRAINT fk_delivery_order_phamarcy FOREIGN KEY (phamarcy_id) REFERENCES phamarcy (phamarcy_id);
ALTER TABLE path ADD CONSTRAINT fk_path_address1 FOREIGN KEY (longitude1, latitude1) REFERENCES address (longitude, latitude);
ALTER TABLE path ADD CONSTRAINT fk_path_address2 FOREIGN KEY (longitude2, latitude2) REFERENCES address (longitude, latitude);