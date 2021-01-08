CREATE OR REPLACE procedure procSetOrderStatus(p_order_id delivery_order.order_id%TYPE, p_status delivery_order.status%TYPE, p_phamarcy_id phamarcy.phamarcy_id%TYPE)
AS


BEGIN
    UPDATE delivery_order o
    SET o.status = p_status
    WHERE o.order_id = p_order_id AND o.phamarcy_id = p_phamarcy_id;
END;