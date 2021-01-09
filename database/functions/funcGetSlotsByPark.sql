CREATE OR REPLACE FUNCTION funcGetSlotsByPark(p_park_id park.park_id%TYPE) RETURN SYS_REFCURSOR
IS
    cur_parks    SYS_REFCURSOR;
    ex_no_parks  EXCEPTION;
BEGIN
    OPEN cur_parks FOR
        SELECT ps.slot_id, ps.able_to_charge, v.nr, v.weight, v.status, v.max_battery, v.current_battery, v.motor, s.aero_coef, s.frontal_area
        FROM park_slot ps, vehicle v, scooter s
        WHERE ps.vehicle_nr = v.nr AND s.vehicle_nr = v.nr AND ps.park_id = p_park_id;
    IF(cur_parks IS NULL) THEN
        RAISE ex_no_parks;
    END IF;
    return cur_parks;

EXCEPTION
    WHEN ex_no_parks THEN
        RAISE_APPLICATION_ERROR(-20986, 'The park has no slots yet!');
END;