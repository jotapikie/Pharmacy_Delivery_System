CREATE OR REPLACE FUNCTION funcGetPharmaciesWAdress RETURN SYS_REFCURSOR
IS
    cur_pharmacies    SYS_REFCURSOR;
    ex_no_pharmacies   EXCEPTION;
BEGIN
    OPEN cur_pharmacies FOR
        SELECT a.pharmacy_id, a.phone_number, a.designation, b.street, b.city, b.zip_code, c.longitude, c.latitude, c.elevation
        FROM pharmacy a, address b, geographical_point c
        WHERE a.longitude = b.longitude AND a.latitude = b.latitude AND b.longitude = c.longitude AND b.latitude = c.latitude;
    IF(cur_pharmacies IS NULL) THEN
        RAISE ex_no_pharmacies;
    END IF;
    return cur_pharmacies;

EXCEPTION
    WHEN ex_no_pharmacies THEN
        RAISE_APPLICATION_ERROR(-20779, 'No pharmacies in database!');
END;