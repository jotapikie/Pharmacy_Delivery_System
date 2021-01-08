
CREATE or replace PROCEDURE procAddVehicle (p_number vehicle.NUMBER%TYPE,
											p_weight vehicle.WHEIGHT%TYPE,
											p_state	vehicle.STATE%TYPE,
											p_maxBattery vehicle.MAX_BATTERY%TYPE,
											p_currentBattery vehicle.CURRENT_BATTERY%TYPE) as

BEGIN

    UPDATE VEHICLE
    SET WHEIGHT = p_weight, STATE = p_frontal_area, MAX_BATTERY = p_maxBattery, CURRENT_BATTERY = p_currentBattery, 
    where NUMBER = p_id;

end;