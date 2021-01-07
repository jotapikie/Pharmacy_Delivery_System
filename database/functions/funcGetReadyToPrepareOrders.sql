CREATE OR REPLACE FUNCTION funcGetReadyToPrepareOrders(p_administrator_email delivery_order.client_email%TYPE) RETURN SYS_REFCURSOR
IS
    cur_orders    SYS_REFCURSOR;
    ex_no_orders  EXCEPTION;
BEGIN
    OPEN cur_orders FOR
            SELECT o.order_id, o.begin_date, o.end_date, o.status, o.price, o.client_email, o.delivery_run_id, o.phamarcy_id
            FROM delivery_order o
            WHERE o.status = 'Processed' AND o.phamarcy_id = (SELECT p.phamarcy_id FROM phamarcy p WHERE p.administrator_email = p_administrator_email);
    IF(cur_orders IS NULL) THEN
        RAISE ex_no_orders;
    END IF;
    return cur_orders;

EXCEPTION
    WHEN ex_no_orders THEN
        RAISE_APPLICATION_ERROR(-20989, 'No ready to prepare orders in database!');
END;