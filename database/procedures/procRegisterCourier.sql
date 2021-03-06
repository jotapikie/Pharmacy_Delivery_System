CREATE or replace PROCEDURE procRegisterCourier(p_email platform_user.user_email%TYPE, p_pwd platform_user.user_password%TYPE, 
                                                p_name platform_user.user_name%TYPE, p_nif courier.nif%TYPE, p_nss courier.nss%TYPE,
                                                p_max_weight courier.weight%TYPE, p_pharmacy courier.pharmacy_id%TYPE)
AS

   BEGIN
        INSERT INTO platform_user(user_email, user_password, user_name) VALUES (p_email, p_pwd, p_name);
        INSERT INTO courier(email, nif, nss, weight, pharmacy_id) VALUES (p_email, p_nif, p_nss, p_max_weight, p_pharmacy);
END;