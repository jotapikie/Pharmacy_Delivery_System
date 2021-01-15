CREATE OR REPLACE FUNCTION funcStartDeliveryRun(p_delivery_id delivery_run.delivery_run_id%type,p_email delivery_run.courier_email%type, p_distance delivery_run.distance%type, p_energy delivery_run.energy%type, p_date delivery_run.start_date%type) RETURN INT
    IS
   
    r_vehicle    delivery_run.vehicle_nr%TYPE;

BEGIN
    SELECT a.nr INTO r_vehicle
    FROM vehicle a
    WHERE (a.status = 'Locked' OR a.status = 'Charging') AND ROWNUM < 2;
    
    UPDATE vehicle
    SET status = 'Active'
    WHERE nr = r_vehicle;
    
    UPDATE delivery_run
    SET distance = p_distance, energy = p_energy, start_date = p_date, courier_email = p_email, vehicle_nr = r_vehicle
    WHERE delivery_run_id = p_delivery_id;
    
    UPDATE delivery_order
    SET status = 'Delivering'
    WHERE delivery_run_id = p_delivery_id;
    
    RETURN r_vehicle;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
END;