CREATE or replace PROCEDURE procAddVehicle (p_number vehicle.NR%TYPE,
											p_weight vehicle.WEIGHT%TYPE,
											p_state	vehicle.STATUS%TYPE,
											p_maxBattery vehicle.MAX_BATTERY%TYPE,
											p_currentBattery vehicle.CURRENT_BATTERY%TYPE,
											p_idPharmacy vehicle.PHARMACY_ID%TYPE) as
BEGIN

    INSERT INTO VEHICLE(nr, weight, status, max_battery, current_battery, pharmacy_id)
		VALUES (p_number, p_weight, p_state, p_maxBattery, p_currentBattery, p_idPharmacy);

    INSERT INTO SCOOTER(vehicle_nr)
    	VALUES (p_number);

end;