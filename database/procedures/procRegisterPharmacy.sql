CREATE or replace PROCEDURE procRegisterCourier(p_phone pharmacy.phone_number%TYPE, 
                                                p_email pharmacy.administrator_email%TYPE,p_password administrator.user_email%TYPE,p_name administrator.user_name%TYPE, p_longitude pharmacy.longitude%TYPE, 											p_latitude pharmacy.latitude%TYPE,p_street address.street%TYPE, p_city address.city%TYPE, p_port address.port_number%TYPE, p_zip address.zip_code%TYPE, p_elevation geographical_point.elevation%TYPE)
AS

   BEGIN
	INSERT INTO platform_user(user_email, user_password, user_name) VALUES(p_email, p_password, p_name);
        INSERT INTO administrator(email) VALUES (p_email);
	INSERT INTO geographical_point(longitude,latitude,elevation,description) VALUES (p_longitude, p_latitude, p_elevation, 'Pharmacy');
        INSERT INTO address(longitude,latitude,street,city,port_number,zip_code) VALUES (p_longitude,p_latitude,p_street,p_city,p_port,p_zip);
	INSERT INTO pharmacy(phone_number,administrator_email,longitude,latitude) VALUES (p_phone,p_admin,p_longitude,p_latitude);
	
END;

