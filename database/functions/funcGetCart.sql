CREATE OR REPLACE FUNCTION funcGetCart(p_email cart_product.client_email%type) RETURN SYS_REFCURSOR
IS
    cur_cart   SYS_REFCURSOR;
    ex_no_cart   EXCEPTION;
BEGIN
    OPEN cur_cart FOR
        SELECT b.*, a.quantity
        FROM cart_product a, product b
        WHERE a.product_id = b.product_id AND a.client_email = p_email;
    IF(cur_cart IS NULL) THEN
        RAISE ex_no_cart;
    END IF;
    return cur_cart;

EXCEPTION
    WHEN ex_no_cart THEN
        RAISE_APPLICATION_ERROR(-209239, 'The cart is empty!');
END;