CREATE OR REPLACE FUNCTION funcGetAssociatedOrder(p_order_id delivery_order.order_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_order    SYS_REFCURSOR;

BEGIN
    OPEN cur_order FOR
	    SELECT o.order_id, o.begin_date, o.end_date, o.status, o.price, a.street, g.longitude, g.latitude, g.elevation,g.description, a.city, a.zip_code, a.port_number
            FROM delivery_order o, address a, geographical_point g, pharmacy p ,delivery_order ass
            WHERE o.order_id = p_order_id AND o.associated_order = ass.order_id AND p.pharmacy_id = ass.pharmacy_id AND g.longitude = p.longitude AND g.latitude = p.latitude AND a.longitude = g.longitude AND a.latitude = g.latitude;
    RETURN cur_order;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20002, 'Order with ID: ' || p_order_id || ' does not exist!');
        RETURN NULL;
END;