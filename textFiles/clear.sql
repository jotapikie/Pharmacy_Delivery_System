DROP TABLE platform_user              	CASCADE CONSTRAINTS PURGE;
DROP TABLE super_admin  		        CASCADE CONSTRAINTS PURGE;
DROP TABLE platform_client              CASCADE CONSTRAINTS PURGE;
DROP TABLE credit_card                  CASCADE CONSTRAINTS PURGE;
DROP TABLE administrator       	        CASCADE CONSTRAINTS PURGE;
DROP TABLE courier                      CASCADE CONSTRAINTS PURGE;
DROP TABLE pharmacy   	            	CASCADE CONSTRAINTS PURGE;
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
DROP TABLE pharmacy_product             CASCADE CONSTRAINTS PURGE;
DROP TABLE pathway                      CASCADE CONSTRAINTS PURGE;
DROP TABLE geographical_point           CASCADE CONSTRAINTS PURGE;
DROP TABLE cart_product                 CASCADE CONSTRAINTS PURGE;
DROP TABLE vehicle_category             CASCADE CONSTRAINTS PURGE;
DROP TABLE road_category                CASCADE CONSTRAINTS PURGE;
DROP TABLE drone                        CASCADE CONSTRAINTS PURGE;

CREATE TABLE platform_user(
    user_email varchar(255) CONSTRAINT pk_user_email PRIMARY KEY,
    user_password varchar(255) NOT NULL, 
    user_name varchar(255) NOT NULL
);

CREATE TABLE pharmacy(
    pharmacy_id INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_pharmacy_id PRIMARY KEY,
    phone_number int NOT NULL,
    designation varchar(255) NOT NULL,
    administrator_email varchar(255) NOT NULL CONSTRAINT uk_pharmacy_administrator UNIQUE,
    longitude NUMERIC(8,5) NOT NULL,
    latitude NUMERIC(8,5) NOT NULL
);

CREATE TABLE administrator(
    email varchar(255) CONSTRAINT pk_administrator_email PRIMARY KEY
);

CREATE TABLE platform_client(
    email varchar(255) CONSTRAINT pk_client_email PRIMARY KEY, 
    phone_number int NOT NULL, 
    credits float NOT NULL, 
    longitude NUMERIC(8,5) NOT NULL, 
    latitude NUMERIC(8,5) NOT NULL,
    nif int NOT NULL
);

CREATE TABLE courier(
    email varchar(255) CONSTRAINT pk_courier_email PRIMARY KEY, 
    nif int NOT NULL, 
    nss int NOT NULL, 
    weight float NOT NULL, 
    pharmacy_id int NOT NULL
);

CREATE TABLE credit_card(
    nr VARCHAR(16) CONSTRAINT pk_credit_card_nr PRIMARY KEY, 
    validity_date varchar(10) NOT NULL, 
    cvv int NOT NULL,
    owner_email varchar(255)
);

CREATE TABLE address(
    longitude NUMERIC(8,5)  NOT NULL,
    latitude NUMERIC(8,5)   NOT NULL,
    street varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    port_number int,
    zip_code varchar(255) NOT NULL, 
    CONSTRAINT pk_address_longitude_latitude PRIMARY KEY (longitude, latitude)
);

CREATE TABLE product(
    product_id INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_product_id PRIMARY KEY,
    designation varchar(255) NOT NULL,
    weight NUMERIC(4,2) NOT NULL,
    price NUMERIC(5,2) NOT NULL
);

CREATE TABLE super_admin(
    email varchar(255) CONSTRAINT pk_super_admin_email PRIMARY KEY
);

CREATE TABLE vehicle(
    nr INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_vehicle_nr PRIMARY KEY,
    weight NUMERIC(5,2) NOT NULL,
    status varchar(255) NOT NULL,
    max_battery int NOT NULL,
    current_battery int NOT NULL,
    motor int NOT NULL,
    max_weight NUMERIC(4,2) NOT NULL,
    pharmacy_id int NOT NULL,
    vehicle_category varchar(255) NOT NULL
);

CREATE TABLE scooter(
    vehicle_nr int CONSTRAINT pk_scooter_nr PRIMARY KEY,
    aero_coef NUMERIC(4,2) NOT NULL,
    frontal_area NUMERIC(4,2) NOT NULL
);

CREATE TABLE drone(
    vehicle_nr int CONSTRAINT pk_drone_nr PRIMARY KEY,
    frontal_area NUMERIC(4,2) NOT NULL,
    top_area NUMERIC(4,2) NOT NULL,
    lift_drag NUMERIC (4,2) NOT NULL,
    eletrical_consume NUMERIC(4,2) NOT NULL,
    power_transfer NUMERIC(4,2) NOT NULL,
    aero_coef NUMERIC(4,2) NOT NULL
);
    


CREATE TABLE park(
    park_id INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_park_id PRIMARY KEY,
    max_vehicles int NOT NULL,
    max_energy float NOT NULL,
    current_energy float NOT NULL,
    pharmacy_id int NOT NULL,
    vehicle_category varchar(255) NOT NULL
);

CREATE TABLE park_slot(
     slot_id INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_park_slot_id PRIMARY KEY, 
     able_to_charge int NOT NULL,
     park_id int NOT NULL,
     vehicle_nr int
);

CREATE TABLE delivery_order(
    order_id INTEGER GENERATED ALWAYS AS IDENTITY  CONSTRAINT pk_order_id PRIMARY KEY,
    begin_date timestamp NOT NULL,
    end_date timestamp NULL,
    status varchar(255) NOT NULL,
    price NUMERIC(5,2),
    client_email varchar(255),
    delivery_run_id int,
    pharmacy_id int NOT NULL,
    associated_order int
);

CREATE TABLE invoice(
    order_id int NOT NULL CONSTRAINT pk_invoice_order PRIMARY KEY,
    inv_date timestamp NOT NULL,
    total_price NUMERIC(5,2) NOT NULL,
    price_paid NUMERIC(5,2) NOT NULL,
    credits_spent int NOT NULL,
    credits_won int NOT NULL,
    nif int NOT NULL
);

CREATE TABLE order_product(
    product_id int NOT NULL,
    order_id int NOT NULL,
    quantity int NOT NULL, 
    CONSTRAINT pk_order_product PRIMARY KEY(product_id, order_id)
);

CREATE TABLE pharmacy_product(
    pharmacy_id int NOT NULL,
    product_id int NOT NULL,
    quantity int NOT NULL,
    CONSTRAINT pk_pharmacy_product PRIMARY KEY(pharmacy_id, product_id)
);

CREATE TABLE delivery_run(
    delivery_run_id INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_delivery_run_id PRIMARY KEY,

    distance NUMBER(6,2),
    energy NUMBER(4,2),
    start_date timestamp,
    end_date timestamp NULL,
    courier_email varchar(255),
    vehicle_nr int,
    vehicle_category varchar(255) NOT NULL
);

CREATE TABLE pathway(
    longitude1 NUMERIC(8,5)  NOT NULL,
    latitude1 NUMERIC(8,5)  NOT NULL,
    longitude2 NUMERIC(8,5)  NOT NULL,
    latitude2 NUMERIC(8,5)   NOT NULL,
    distance float NOT NULL,
    street varchar(255),
    wind_x NUMERIC(5,2) NOT NULL,
    wind_y NUMERIC(5,2) NOT NULL,
    wind_z NUMERIC(5,2) NOT NULL,
    road_category varchar(255),
    vehicle_category varchar(255) NOT NULL,
    CONSTRAINT pk_path_address1_address2 PRIMARY KEY(longitude1, latitude1, longitude2, latitude2, vehicle_category)
);

CREATE TABLE geographical_point(
    longitude NUMERIC(8,5)   NOT NULL,
    latitude NUMERIC(8,5)   NOT NULL,
    elevation float NOT NULL,
    description varchar(255),
    CONSTRAINT pk_geo_point_longitude_latitude PRIMARY KEY (longitude, latitude)
);

CREATE TABLE cart_product(
    client_email varchar(255),
    product_id int,
    quantity int NOT NULL,
    CONSTRAINT pk_cart_product PRIMARY KEY (client_email, product_id)
);

CREATE TABLE vehicle_category(
    category_name varchar(255) CONSTRAINT pk_category_name PRIMARY KEY
);

CREATE TABLE road_category(
    category_name varchar(255) CONSTRAINT pk_road_name PRIMARY KEY,
    kinetic_coef float NOT NULL
);




ALTER TABLE administrator ADD CONSTRAINT fk_administrator_email FOREIGN KEY (email) REFERENCES platform_user (user_email);
ALTER TABLE platform_client ADD CONSTRAINT fk_platform_client_email FOREIGN KEY (email) REFERENCES platform_user (user_email);
ALTER TABLE courier ADD CONSTRAINT fk_courier_email FOREIGN KEY (email) REFERENCES platform_user (user_email);
ALTER TABLE courier ADD CONSTRAINT fk_courier_pharmacy FOREIGN KEY (pharmacy_id) REFERENCES pharmacy (pharmacy_id);
ALTER TABLE pharmacy ADD CONSTRAINT fk_pharmacy_address FOREIGN KEY (longitude, latitude) REFERENCES address (longitude, latitude);
ALTER TABLE platform_client ADD CONSTRAINT fk_client_address FOREIGN KEY (longitude, latitude) REFERENCES address (longitude, latitude);
ALTER TABLE super_admin ADD CONSTRAINT fk_super_admin_email FOREIGN KEY (email) REFERENCES platform_user (user_email);
ALTER TABLE pharmacy ADD CONSTRAINT fk_pharmacy_administrator FOREIGN KEY (administrator_email) REFERENCES administrator (email);
ALTER TABLE credit_card ADD CONSTRAINT fk_credit_card_owner FOREIGN KEY (owner_email) REFERENCES platform_client (email);
ALTER TABLE scooter ADD CONSTRAINT fk_scooter_nr FOREIGN KEY (vehicle_nr) REFERENCES vehicle (nr);
ALTER TABLE drone ADD CONSTRAINT fk_drone_nr FOREIGN KEY (vehicle_nr) REFERENCES vehicle(nr);
ALTER TABLE vehicle ADD CONSTRAINT fk_vehicle_pharmacy FOREIGN KEY (pharmacy_id) REFERENCES pharmacy (pharmacy_id);
ALTER TABLE park ADD CONSTRAINT fk_park_pharmacy FOREIGN KEY (pharmacy_id) REFERENCES pharmacy (pharmacy_id);
ALTER TABLE park_slot ADD CONSTRAINT fk_park_slot_id FOREIGN KEY (park_id) REFERENCES park (park_id);
ALTER TABLE park_slot ADD CONSTRAINT fk_park_slot_vehicle_parked FOREIGN KEY (vehicle_nr) REFERENCES vehicle (nr);
ALTER TABLE delivery_order ADD CONSTRAINT fk_delivery_order_client FOREIGN KEY (client_email) REFERENCES platform_client (email);
ALTER TABLE invoice ADD CONSTRAINT fk_invoice_order FOREIGN KEY (order_id) REFERENCES delivery_order (order_id);
ALTER TABLE order_product ADD CONSTRAINT fk_order_product_pid FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE order_product ADD CONSTRAINT fk_order_product_oid FOREIGN KEY (order_id) REFERENCES delivery_order (order_id);
ALTER TABLE delivery_run ADD CONSTRAINT fk_delivery_run_courier FOREIGN KEY (courier_email) REFERENCES courier (email);
ALTER TABLE delivery_run ADD CONSTRAINT fk_delivery_run_vehicle FOREIGN KEY (vehicle_nr) REFERENCES vehicle (nr);
ALTER TABLE delivery_order ADD CONSTRAINT fk_delivery_order_run FOREIGN KEY (delivery_run_id) REFERENCES delivery_run (delivery_run_id);
ALTER TABLE pharmacy_product ADD CONSTRAINT fk_pharmacy_product_phaid FOREIGN KEY (pharmacy_id) REFERENCES pharmacy (pharmacy_id);
ALTER TABLE pharmacy_product ADD CONSTRAINT fk_pharmacy_product_prdid FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE delivery_order ADD CONSTRAINT fk_delivery_order_pharmacy FOREIGN KEY (pharmacy_id) REFERENCES pharmacy (pharmacy_id);
ALTER TABLE pathway ADD CONSTRAINT fk_path_point1 FOREIGN KEY (longitude1, latitude1) REFERENCES geographical_point (longitude, latitude);
ALTER TABLE pathway ADD CONSTRAINT fk_path_point22 FOREIGN KEY (longitude2, latitude2) REFERENCES geographical_point (longitude, latitude);
ALTER TABLE address ADD CONSTRAINT fk_address_geo_point FOREIGN KEY (longitude, latitude) REFERENCES geographical_point(longitude, latitude);
ALTER TABLE delivery_order ADD CONSTRAINT fk_order_associated_order FOREIGN KEY (associated_order) REFERENCES delivery_order(order_id);
ALTER TABLE cart_product ADD CONSTRAINT fk_cart_owner FOREIGN KEY (client_email) REFERENCES platform_client (email);
ALTER TABLE cart_product ADD CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product (product_id);
ALTER TABLE vehicle ADD CONSTRAINT fk_vehicle_category_name FOREIGN KEY (vehicle_category) REFERENCES vehicle_category(category_name);
ALTER TABLE park ADD CONSTRAINT fk_park_category FOREIGN KEY (vehicle_category) REFERENCES vehicle_category(category_name);
ALTER TABLE delivery_run ADD CONSTRAINT fk_run_category FOREIGN KEY (vehicle_category) REFERENCES vehicle_category(category_name);
ALTER TABLE pathway ADD CONSTRAINT fk_path_road_category FOREIGN KEY (road_category) REFERENCES road_category(category_name);
ALTER TABLE pathway ADD CONSTRAINT fk_path_vehicle_category FOREIGN KEY (vehicle_category) REFERENCES vehicle_category(category_name);

INSERT INTO platform_user VALUES('superadmin1@lapr', 'sadimn1', 'Tiago Pais');
INSERT INTO super_admin VALUES('superadmin1@lapr');
INSERT INTO road_category VALUES ('Asphalt', 0.2);
INSERT INTO road_category VALUES ('Off-Road', 0.2);
INSERT INTO road_category VALUES ('Sidewalk', 0.2);
INSERT INTO vehicle_category VALUES ('Scooter');
INSERT INTO vehicle_category VALUES ('Drone');