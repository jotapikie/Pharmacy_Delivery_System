create or replace procedure procSaveDeliveryRun(id_d number, b_date delivery_run.start_date%type, vehicle_c delivery_run.vehicle_category%type) as
begin

    INSERT INTO delivery_run(delivery_run_id,start_date,vehicle_category)
    VALUES(id_d,b_date,vehicle_c);

end;