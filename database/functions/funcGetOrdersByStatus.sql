create or replace FUNCTION funcGetOrdersByStatus(p_pharmacy_id pharmacy.pharmacy_id%TYPE, p_status delivery_order.status%TYPE) RETURN SYS_REFCURSOR
IS
    cur_orders    SYS_REFCURSOR;
    ex_no_orders  EXCEPTION;
BEGIN
    OPEN cur_orders FOR
            SELECT o.order_id, o.begin_date, o.end_date, o.status, o.price, a.street, g.longitude, g.latitude, g.elevation, a.city, a.zip_code, a.port_number
            FROM delivery_order o, platform_client c, address a, geographical_point g
            WHERE o.client_email = c.email AND c.longitude = g.longitude AND c.latitude = g.latitude AND a.longitude = g.longitude AND a.latitude = g.latitude AND o.status = p_status AND o.pharmacy_id = p_pharmacy_id AND o.delivery_run_id = null;
    IF(cur_orders IS NULL) THEN
        RAISE ex_no_orders;
    END IF;
    return cur_orders;

EXCEPTION
    WHEN ex_no_orders THEN
        RAISE_APPLICATION_ERROR(-20988, 'No orders with that status in database!');
END;