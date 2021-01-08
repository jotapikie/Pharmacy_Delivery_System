CREATE OR REPLACE FUNCTION funcGetOrdersByStatus(p_phamarcy_id phamarcy.phamarcy_id%TYPE, p_status delivery_order.status%TYPE) RETURN SYS_REFCURSOR
IS
    cur_orders    SYS_REFCURSOR;
    ex_no_orders  EXCEPTION;
BEGIN
    OPEN cur_orders FOR
            SELECT o.order_id, o.begin_date, o.end_date, o.status, o.price, o.client_email, o.delivery_run_id, o.phamarcy_id
            FROM delivery_order o
            WHERE o.status = p_status AND o.phamarcy_id = p_phamarcy_id;
    IF(cur_orders IS NULL) THEN
        RAISE ex_no_orders;
    END IF;
    return cur_orders;

EXCEPTION
    WHEN ex_no_orders THEN
        RAISE_APPLICATION_ERROR(-20988, 'No orders with that status in database!');
END;