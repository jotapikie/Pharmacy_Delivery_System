CREATE OR REPLACE FUNCTION funcEndDeliveryRun(p_vehicle vehicle.nr%type, p_date delivery_run.end_date%type) RETURN SYS_REFCURSOR
    IS
    r_email sys_refcursor;
    r_dr    int;

BEGIN
    SELECT d.delivery_run_id INTO r_dr
    FROM delivery_run d
    WHERE d.vehicle_nr = p_vehicle AND d.end_date is null;
    
    UPDATE delivery_run SET end_date = p_date WHERE delivery_run_id = r_dr;
    UPDATE delivery_order SET end_date = p_date, status = 'Delivered' WHERE delivery_run_id = r_dr;
    
    UPDATE     (SELECT a.* FROM delivery_order o, delivery_order a WHERE o.delivery_run_id = r_dr AND o.associated_order = a.order_id) SET status='Processed';
    
OPEN r_email FOR
    SELECT d.courier_email
    FROM delivery_run d 
    WHERE d.delivery_run_id = r_dr;
RETURN r_email;
    
END;