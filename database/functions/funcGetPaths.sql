CREATE OR REPLACE FUNCTION funcGetPaths RETURN SYS_REFCURSOR
IS
    cur_paths    SYS_REFCURSOR;
    ex_no_paths   EXCEPTION;
BEGIN
    OPEN cur_paths FOR
        SELECT b.*, c.*, a.distance, a.road_category, a.wind_x, a.wind_y, a.wind_z,a.vehicle_category, a.street
        FROM pathway a, geographical_point b, geographical_point c
        WHERE b.longitude = a.longitude1 AND b.latitude = a.latitude1 AND c.longitude = a.longitude2 AND c.latitude = a.latitude2;
    IF(cur_paths IS NULL) THEN
        RAISE ex_no_paths;
    END IF;
    return cur_paths;

EXCEPTION
    WHEN ex_no_paths THEN
        RAISE_APPLICATION_ERROR(-20349, 'No paths in database');
END;
