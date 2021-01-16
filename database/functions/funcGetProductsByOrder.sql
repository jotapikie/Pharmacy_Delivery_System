create or replace FUNCTION funcGetProductsByOrder(p_order delivery_order.order_id%type) RETURN SYS_REFCURSOR
IS
    cur_products    SYS_REFCURSOR;
    ex_no_products   EXCEPTION;
BEGIN
    OPEN cur_products FOR
        SELECT a.product_id,b.designation, b.weight, b.price, a.quantity
        FROM order_product a, product b
        WHERE a.product_id = b.product_id AND a.order_id = p_order;
    IF(cur_products IS NULL) THEN
        RAISE ex_no_products;
    END IF;
    return cur_products;

EXCEPTION
    WHEN ex_no_products THEN
        RAISE_APPLICATION_ERROR(-20779, 'The order has no products');
END;