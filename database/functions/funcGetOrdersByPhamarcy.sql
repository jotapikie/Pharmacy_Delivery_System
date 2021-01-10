CREATE OR REPLACE FUNCTION funcGetOrdersByPhamarcy(p_phamarcy_id phamarcy.phamarcy_id%TYPE) RETURN SYS_REFCURSOR
IS
    cur_orders    SYS_REFCURSOR;
    ex_no_orders  EXCEPTION;
BEGIN
    OPEN cur_orders FOR
            SELECT o.order_id, o.begin_date, o.end_date, o.status, o.price
            FROM delivery_order o
            WHERE o.phamarcy_id = p_phamarcy_id;
    IF(cur_orders IS NULL) THEN
        RAISE ex_no_orders;
    END IF;
    return cur_orders;

EXCEPTION
    WHEN ex_no_orders THEN
        RAISE_APPLICATION_ERROR(-2095, 'The phamarcy has no orders assigned!');
END;
