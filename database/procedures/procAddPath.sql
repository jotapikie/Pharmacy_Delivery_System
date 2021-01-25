CREATE or replace PROCEDURE procAddPath(p_lon1 geographical_point.longitude%type, p_lat1 geographical_point.latitude%type, p_lon2 geographical_point.longitude%type, p_lat2 geographical_point.latitude%type, p_distance pathway.distance%type,p_street pathway.street%type, p_road pathway.road_category%type, 
                                        p_wind_x pathway.wind_x%type, p_wind_y pathway.wind_y%type, p_wind_z pathway.wind_z%type, p_vehicle pathway.vehicle_category%type) as
BEGIN
    INSERT INTO pathway(longitude1, latitude1, longitude2, latitude2, distance, street, road_category, wind_x, wind_y, wind_z, vehicle_category)
    VALUES(p_lon1, p_lat1, p_lon2, p_lat2, p_distance, p_street, p_road, p_wind_x, p_wind_y, p_wind_z, p_vehicle);
END;