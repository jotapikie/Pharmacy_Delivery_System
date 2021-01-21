create or replace procedure procDeleteOrderFromQueue(id_order number) as 

ex_no_order exception;

begin

    UPDATE delivery_order
    SET status='delivering'
    WHERE order_id=id_order;
 
    
    exception
        when ex_no_order then
        RAISE_APPLICATION_ERROR(-20988, 'No orders with that id in database!');
 
end;