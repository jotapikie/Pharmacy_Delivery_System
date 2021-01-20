CREATE OR REPLACE PROCEDURE procStartDeliveryRun(p_delivery_id delivery_run.delivery_run_id%type,p_email delivery_run.courier_email%type, p_distance delivery_run.distance%type, p_energy delivery_run.energy%type, p_date delivery_run.start_date%type, p_vehicle vehicle.nr%type)
AS
   

BEGIN

    UPDATE vehicle
    SET status = 'Active'
    WHERE nr = p_vehicle;
    
    UPDATE delivery_run
    SET distance = p_distance, energy = p_energy, start_date = p_date, courier_email = p_email, vehicle_nr = p_vehicle
    WHERE delivery_run_id = p_delivery_id;
    
    UPDATE delivery_order
    SET status = 'Delivering'
    WHERE delivery_run_id = p_delivery_id;
END;