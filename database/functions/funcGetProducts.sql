CREATE OR REPLACE FUNCTION funcGetProducts RETURN SYS_REFCURSOR
IS
    cur_products    SYS_REFCURSOR;
    ex_no_products   EXCEPTION;
BEGIN
    OPEN cur_products FOR
        SELECT p.product_id, p.designation, p.weight, p.price
        FROM product p;
    IF(cur_products IS NULL) THEN
        RAISE ex_no_products;
    END IF;
    return cur_products;

EXCEPTION
    WHEN ex_no_products THEN
        RAISE_APPLICATION_ERROR(-20999, 'No products in database!');
END;