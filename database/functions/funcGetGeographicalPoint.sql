create or replace FUNCTION funcGetGeographicalPoint(p_lon geographical_point.longitude%TYPE, p_lat geographical_point.latitude%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_point    SYS_REFCURSOR;

BEGIN
    OPEN cur_point FOR
        SELECT a.*
        FROM geographical_point a
        WHERE a.longitude = p_lon AND a.latitude = p_lat;
    RETURN cur_point;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN NULL;
END;