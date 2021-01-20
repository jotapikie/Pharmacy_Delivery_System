CREATE or replace PROCEDURE procAddPath(p_lon1 geographical_point.longitude%type, p_lat1 geographical_point.latitude%type, p_lon2 geographical_point.longitude%type, p_lat2 geographical_point.latitude%type, p_distance pathway.distance%type,p_street pathway.street%type, p_kinetic pathway.kinetic_coef%type, p_wind pathway.wind%type) as
BEGIN
    INSERT INTO pathway(longitude1, latitude1, longitude2, latitude2, distance, street, kinetic_coef, wind)
    VALUES(p_lon1, p_lat1, p_lon2, p_lat2, p_distance, p_street, p_kinetic, p_wind);
END;