CREATE or replace PROCEDURE procRegisterCourier(p_id pharmacy.pharmacy_id%TYPE, p_phone pharmacy.phone_number%TYPE, 
                                                p_admin pharmacy.administrator_email%TYPE, p_longitude pharmacy.longitude%TYPE, p_latitude pharmacy.latitude%TYPE,
                                                p_street address.street%TYPE, p_city address.city%TYPE, p_port address.port_number%TYPE, p_zip address.zip_code%TYPE)
AS

   BEGIN
        INSERT INTO administrator(email) VALUES (p_email);
        INSERT INTO address(longitude,latitude,street,city,port_number,zip_code) VALUES (p_longitude,p_latitude,p_street,p_city,p_port,p_zip);
	INSERT INTO pharmacy(pharmacy_id,phone_number,administrator_email,longitude,latitude) VALUES (p_id,p_phone,p_admin,p_longitude,p_latitude);
END;
