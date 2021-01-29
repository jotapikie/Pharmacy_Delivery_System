CREATE or replace PROCEDURE procUpdateVehicle (p_number vehicle.nr%TYPE,
											p_weight vehicle.weight%TYPE,
											p_state	vehicle.status%TYPE,
											p_maxBattery vehicle.max_battery%TYPE,
											p_currentBattery vehicle.current_battery%TYPE) as
BEGIN

    UPDATE vehicle v
    SET v.weight = p_weight, v.status = p_state, v.max_battery = p_maxBattery, v.current_battery = p_currentBattery
    WHERE v.nr = p_number;

END;