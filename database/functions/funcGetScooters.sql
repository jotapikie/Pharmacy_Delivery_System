CREATE OR REPLACE FUNCTION funcGetScooters(p_pharmacy pharmacy.pharmacy_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_scooter    SYS_REFCURSOR;

BEGIN
    OPEN cur_scooter FOR
        SELECT a.nr,a.status, a.max_battery, a.current_battery
        FROM vehicle a, scooter b
        WHERE a.nr = b.vehicle_nr AND a.pharmacy_id = p_pharmacy;
    RETURN cur_scooter;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20037, 'Pharmacy with id ' || p_pharmacy || ' does not have any available scooter!');
        RETURN NULL;
END;