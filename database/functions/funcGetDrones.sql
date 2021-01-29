create or replace FUNCTION funcGetDrones(p_pharmacy pharmacy.pharmacy_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_drone    SYS_REFCURSOR;

BEGIN
    OPEN cur_drone FOR
        SELECT a.nr,a.status, a.max_battery, a.current_battery
        FROM vehicle a, drone b
        WHERE a.nr = b.vehicle_nr AND a.pharmacy_id = p_pharmacy;
    RETURN cur_drone;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20037, 'Pharmacy with id ' || p_pharmacy || ' does not have any available drone!');
        RETURN NULL;
END;