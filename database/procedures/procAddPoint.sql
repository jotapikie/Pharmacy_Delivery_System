CREATE or replace PROCEDURE procAddPoint(p_lon geographical_point.longitude%type, p_lat geographical_point.latitude%type, p_ele geographical_point.elevation%type, p_desc geographical_point.description%type) as
BEGIN
    INSERT INTO geographical_point(longitude, latitude, elevation, description)
    VALUES (p_lon, p_lat, p_ele, p_desc);
END;