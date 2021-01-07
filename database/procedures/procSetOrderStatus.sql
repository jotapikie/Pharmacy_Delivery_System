CREATE OR REPLACE procedure procSetOrderStatus(p_order_id delivery_order.order_id%TYPE, p_status delivery_order.status%TYPE)
AS


BEGIN
    UPDATE delivery_order o
    SET o.status = p_status
    WHERE o.order_id = p_order_id;
END;