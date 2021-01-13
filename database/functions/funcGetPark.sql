CREATE OR REPLACE FUNCTION funcGetPark(p_park_id park.parkt_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_park    SYS_REFCURSOR;

BEGIN
    OPEN cur_park FOR
            SELECT p.slot_id, p.able_to_charge, p.park_id, p.vehicle_nr
            FROM park p
            WHERE p.park_id = p_park_id;
    RETURN cur_park;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Park with ID: ' || p_park_id || ' does not exist!');
        RETURN NULL;
END;