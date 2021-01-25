CREATE OR REPLACE FUNCTION funcGetVehicle(v_vehicle_id VEHICLE.NR%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_vehicle    SYS_REFCURSOR;

BEGIN
    OPEN cur_vehicle FOR
            SELECT v.NR, v.STATUS, v.MAX_BATTERY, v.CURRENT_BATTERY
            FROM VEHICLE v
            WHERE v.NR = v_vehicle_id;
    RETURN cur_vehicle;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Scooter with ID: ' || v_vehicle_id || ' does not exist!');
        RETURN NULL;
END;