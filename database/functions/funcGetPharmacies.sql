CREATE OR REPLACE FUNCTION funcGetPharmacies RETURN SYS_REFCURSOR
IS
    cur_pharmacies    SYS_REFCURSOR;
    ex_no_pharmacies   EXCEPTION;
BEGIN
    OPEN cur_pharmacies FOR
        SELECT a.pharmacy_id, a.phone_number, a.designation, d.user_name, d.user_email, d.user_password, b.street, c.longitude, c.latitude, c.elevation, b.city, b.port_number, b.zip_code
        FROM pharmacy a, address b, geographical_point c, platform_user d
        WHERE a.longitude = b.longitude AND a.latitude = b.latitude AND b.longitude = c.longitude AND b.latitude = c.latitude AND a.administrator_email = d.user_email;
    IF(cur_pharmacies IS NULL) THEN
        RAISE ex_no_pharmacies;
    END IF;
    return cur_pharmacies;

EXCEPTION
    WHEN ex_no_pharmacies THEN
        RAISE_APPLICATION_ERROR(-20779, 'No pharmacies in database!');
END;