CREATE OR REPLACE FUNCTION funcGetPark(p_park_id park.park_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_park    SYS_REFCURSOR;

BEGIN
    OPEN cur_park FOR
            SELECT ps.slot_id, ps.able_to_charge, ps.park_id, ps.vehicle_nr
            FROM park_slot ps
            WHERE ps.park_id = p_park_id;
    RETURN cur_park;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Park with ID: ' || p_park_id || ' does not exist!');
        RETURN NULL;
END;