CREATE OR REPLACE FUNCTION funcGetProduct(p_product_id product.product_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_product    SYS_REFCURSOR;

BEGIN
    OPEN cur_product FOR
            SELECT p.product_id, p.designation, p.weight, p.price
            FROM product p
            WHERE p.product_id = p_product_id;
    RETURN cur_product;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Product with ID: ' || p_product_id || ' does not exist!');
        RETURN NULL;
END;