CREATE OR REPLACE FUNCTION funcGetGeographicalPointByPharmacy(p_pharmacy pharmacy.pharmacy_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_point    SYS_REFCURSOR;

BEGIN
    OPEN cur_point FOR
        SELECT a.*
        FROM geographical_point a, pharmacy b
        WHERE b.longitude = a.longitude AND b.latitude = a.latitude AND b.pharmacy_id = p_pharmacy;
    RETURN cur_point;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN NULL;
END;