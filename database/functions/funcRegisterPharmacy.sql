CREATE OR REPLACE FUNCTION funcRegisterPharmacy(p_name pharmacy.designation%type, p_phone pharmacy.phone_number%type, p_email platform_user.user_email%type, p_user_name platform_user.user_name%type,
                                                p_pwd platform_user.user_password%type, p_street address.street%type, p_longitude geographical_point.longitude%type, p_latitude geographical_point.latitude%type,
                                                p_elevation geographical_point.elevation%type, p_city address.city%type, p_zip address.zip_code%type, p_port address.port_number%type, p_max_vehicles park.max_vehicles%type,
                                                p_category park.vehicle_category%type, p_max_energy park.max_energy%type,p_desc geographical_point.description%type) RETURN INT
    IS
    r_id    park.park_id%TYPE;
    r_ph    pharmacy.pharmacy_id%TYPE;

BEGIN


    INSERT INTO PLATFORM_USER(user_email, user_name, user_password) VALUES (p_email, p_user_name, p_pwd);
    INSERT INTO ADMINISTRATOR VALUES (p_email);
    INSERT INTO GEOGRAPHICAL_POINT(longitude, latitude, elevation, description) VALUES (p_longitude, p_latitude, p_elevation, p_desc);
    INSERT INTO ADDRESS(longitude, latitude, street, city, port_number, zip_code) VALUES (p_longitude, p_latitude, p_street, p_city, p_port, p_zip);
    INSERT INTO PHARMACY(designation, phone_number, administrator_email, longitude, latitude) VALUES (p_name, p_phone, p_email, p_longitude, p_latitude);
    
    SELECT p.pharmacy_id INTO r_ph
    FROM pharmacy p
    WHERE p.administrator_email = p_email;
    
    INSERT INTO PARK(max_vehicles, max_energy, current_energy, vehicle_category, pharmacy_id) VALUES (p_max_vehicles, p_max_energy, p_max_energy, p_category,r_ph); 
    
    SELECT p.park_id INTO r_id
    FROM park p
    WHERE p.pharmacy_id = r_ph AND p.vehicle_category=p_category AND p.max_vehicles = p_max_vehicles AND p.max_energy = p_max_energy;
    
    RETURN r_id;
    
END;