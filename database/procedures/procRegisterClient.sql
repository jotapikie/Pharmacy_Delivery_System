-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- procRegisterClient: procedure to register a client in the database, he is added to the table app_users and then to the table clients. Used in UC1 RegisterClient
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE or replace PROCEDURE procRegisterClient(v_username user.USERNAME%TYPE,
                                               v_password user.USER_PASSWORD%TYPE,
                                               v_email platform_client.EMAIL%TYPE,
                                               v_name platform_client.NAME%TYPE,
                                               v_phoneNumber client.PHONE_NUMBER%TYPE,
                                               v_longitude address.LONGITUDE%TYPE,
                                               v_latitude address.LATITUDE%TYPE,
                                               v_street address.STREET%TYPE,
                                               v_city address.CITY%TYPE,
                                               v_portNumber address.PORT_NUMBER%TYPE,
                                               v_zipCode address.ZIP_CODE%TYPE,
                                               v_number credit_card.NUMBER%TYPE,
                                               v_validityDate credit_card.VALIDITY_DATE%TYPE,
                                               v_cvv credit_card.CVV%TYPE)
IS
    v_client_initial_points NUMBER DEFAULT 0;
BEGIN
    INSERT INTO USER(username, password)
        VALUES(v_username, v_password);
    INSERT INTO ADDRESS(longitude, latitude, street, city, port_number, zip_code)
    	VALUES(v_longitude, v_latitude, v_street, v_city, v_portNumber, v_zipCode);
    INSERT INTO CREDIT_CARD(number, validity_date, cvv)
    	VALUES(v_number, v_validityDate, v_cvv);
    -- Insert the client in the table clients
    INSERT INTO CLIENT(username, email, phone_number, credits, longitude, latitude, number_creditCard)
        VALUES(v_username, v_email, v_phoneNumber, v_client_initial_points, v_longitude, v_latitude, v_number);
END;
