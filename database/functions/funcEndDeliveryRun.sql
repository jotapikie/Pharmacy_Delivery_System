CREATE OR REPLACE FUNCTION funcEndDeliveryRun(p_vehicle vehicle.nr%type, p_date delivery_run.end_date%type) RETURN INT
    IS
    r_id    int;
    r_dr    int;
    r_associated delivery_order.associated_order%type;

BEGIN
    SELECT d.delivery_run_id INTO r_dr
    FROM delivery_run d
    WHERE d.vehicle_nr = p_vehicle AND d.end_date = null;
    
    UPDATE delivery_run SET end_date = p_date WHERE delivery_run_id = r_dr;
    UPDATE delivery_order SET end_date = p_date, status = 'Delivered' WHERE delivery_run_id = r_dr;
    
    SELECT o.associated_order INTO r_associated
    FROM delivery_order o
    WHERE o.delivery_run_id = r_dr;
    
    
    RETURN 1;
    
END;