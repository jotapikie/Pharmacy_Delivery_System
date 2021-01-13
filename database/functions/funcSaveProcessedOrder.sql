CREATE OR REPLACE FUNCTION funcSaveProcessedOrder(p_begin_date delivery_order.begin_date%type, p_status delivery_order.status%type, p_price delivery_order.price%type, p_email delivery_order.client_email%type, p_pharmacy delivery_order.pharmacy_id%type) RETURN INT
    IS
    r_id    delivery_order.order_id%TYPE;

BEGIN
    INSERT INTO delivery_order(begin_date, status, price, client_email, pharmacy_id) VALUES (p_begin_date, p_status, p_price, p_email, p_pharmacy);
    SELECT order_id INTO r_id
    FROM (SELECT * FROM delivery_order ORDER BY 1 DESC)
    WHERE ROWNUM <2;
    RETURN r_id;
END;