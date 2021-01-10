CREATE OR REPLACE FUNCTION funcGetOrder(p_order_id delivery_order.order_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_order    SYS_REFCURSOR;

BEGIN
    OPEN cur_order FOR
            SELECT o.order_id, o.begin_date, o.end_date, o.status, o.price
            FROM delivery_order o
            WHERE o.order_id = p_order_id;
    RETURN cur_order;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20002, 'Order with ID: ' || p_order_id || ' does not exist!');
        RETURN NULL;
END;