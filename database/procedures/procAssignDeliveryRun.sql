create or replace procedure procAssignDeliveryRun(id_o delivery_order.order_id%type, id_r delivery_run.delivery_run_id%type) as 
begin
  
  UPDATE delivery_order SET delivery_run_id=id_r WHERE order_id=id_o;
  
end;