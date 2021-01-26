CREATE or replace PROCEDURE procRegisterClient(p_name platform_user.user_name%TYPE,
                                               p_email platform_user.user_email%TYPE,
                                               p_pwd platform_user.user_password%TYPE,
                                               p_nif platform_client.nif%TYPE,
                                               p_phone platform_client.phone_number%TYPE,
                                               p_card_date credit_card.validity_date%TYPE,
                                               p_card_nr credit_card.nr%TYPE,
                                               p_ccv credit_card.cvv%TYPE,
                                               p_street address.street%TYPE,
                                               p_longitude address.longitude%TYPE,
                                               p_latitude address.latitude%TYPE,
                                               p_elevation geographical_point.elevation%TYPE,
                                               p_city address.city%TYPE,
                                               p_port address.port_number%TYPE,
					                           p_zip address.zip_code%TYPE,
                                               p_desc geographical_point.description%type)
AS
BEGIN
    INSERT INTO PLATFORM_USER(user_name, user_password, user_email) VALUES(p_name, p_pwd, p_email);
    INSERT INTO GEOGRAPHICAL_POINT(longitude, latitude, elevation, description) VALUES (p_longitude, p_latitude, p_elevation, p_desc);
    INSERT INTO ADDRESS (longitude, latitude, street, city, port_number, zip_code) VALUES (p_longitude, p_latitude, p_street, p_city, p_port, p_zip);
    INSERT INTO PLATFORM_CLIENT (email, phone_number, credits, longitude, latitude, nif) VALUES (p_email, p_phone, 0, p_longitude, p_latitude, p_nif);
    INSERT INTO CREDIT_CARD(nr, validity_date, cvv, owner_email) VALUES (p_card_nr, p_card_date, p_ccv, p_email);

END;