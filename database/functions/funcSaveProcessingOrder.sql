CREATE OR REPLACE FUNCTION funcSaveProcessingOrder(p_begin_date delivery_order.begin_date%type, p_status delivery_order.status%type,p_price delivery_order.price%type, p_pharmacy delivery_order.pharmacy_id%type, p_associated delivery_order.associated_order%type) RETURN INT
    IS
    r_id    delivery_order.order_id%TYPE;

BEGIN
    INSERT INTO delivery_order(begin_date, status,price,pharmacy_id, associated_order) VALUES (p_begin_date, p_status,p_price, p_pharmacy, p_associated);
    SELECT order_id INTO r_id
    FROM (SELECT * FROM delivery_order ORDER BY 1 DESC)
    WHERE ROWNUM <2;
    RETURN r_id;
END;