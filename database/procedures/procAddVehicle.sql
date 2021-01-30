CREATE or replace PROCEDURE procAddVehicle (p_weight vehicle.WEIGHT%TYPE,
											p_state	vehicle.STATUS%TYPE,
											p_maxBattery vehicle.MAX_BATTERY%TYPE,
											p_currentBattery vehicle.CURRENT_BATTERY%TYPE,
                                            p_motor vehicle.motor%type,
                                            p_maxWeight vehicle.max_weight%type,
											p_idPharmacy vehicle.PHARMACY_ID%TYPE,
                                            p_category vehicle.vehicle_category%type,
                                            p_aero_coef drone.aero_coef%type,
                                            p_frontal_area drone.frontal_area%type,
                                            p_top_area drone.top_area%type,
                                            p_lift drone.lift_drag%type,
                                            p_consume drone.eletrical_consume%type,
                                            p_power drone.power_transfer%type) IS
                                            r_id int;
BEGIN

    INSERT INTO VEHICLE(weight, status, max_battery, current_battery, max_weight, pharmacy_id, vehicle_category,motor) VALUES (p_weight, p_state, p_maxBattery, p_currentBattery,p_maxWeight, p_idPharmacy, p_category,p_motor);
    SELECT MAX(v.nr) INTO r_id
    FROM vehicle v;
    
    IF(p_category = 'Scooter') THEN
        INSERT INTO scooter(vehicle_nr, frontal_area, aero_coef) VALUES (r_id, p_frontal_area, p_aero_coef);
    ELSE
        INSERT INTO drone(vehicle_nr, frontal_area, top_area, aero_coef, lift_drag, eletrical_consume, power_transfer) VALUES (r_id, p_frontal_area, p_top_area, p_aero_coef, p_lift, p_consume, p_power);
    END IF;

END;