CREATE OR REPLACE FUNCTION funcGetParksByPhamarcy(p_phamarcy_id phamarcy.phamarcy_id%TYPE) RETURN SYS_REFCURSOR
IS
    cur_parks    SYS_REFCURSOR;
    ex_no_parks  EXCEPTION;
BEGIN
    OPEN cur_parks FOR
        SELECT p.park_id, p.max_vehicles, p.park_type
        FROM park p
        WHERE p.phamarcy_id = p_phamarcy_id;
    IF(cur_parks IS NULL) THEN
        RAISE ex_no_parks;
    END IF;
    return cur_parks;

EXCEPTION
    WHEN ex_no_parks THEN
        RAISE_APPLICATION_ERROR(-20987, 'The phamarcy has no parks yet');
END;