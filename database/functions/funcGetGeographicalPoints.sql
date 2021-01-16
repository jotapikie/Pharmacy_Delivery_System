CREATE OR REPLACE FUNCTION funcGetGeographicalPoints RETURN SYS_REFCURSOR
IS
    cur_points    SYS_REFCURSOR;
    ex_no_points   EXCEPTION;
BEGIN
    OPEN cur_points FOR
        SELECT a.longitude, a.latitude, a.elevation
        FROM Geographical_point a;
    IF(cur_points IS NULL) THEN
        RAISE ex_no_points;
    END IF;
    return cur_points;

EXCEPTION
    WHEN ex_no_points THEN
        RAISE_APPLICATION_ERROR(-20999, 'No geographical points in database.');
END;
