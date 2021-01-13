CREATE OR REPLACE FUNCTION funcGetQuantity(p_pharmacy pharmacy.pharmacy_id%TYPE, p_product product.product_id%TYPE) RETURN INT
    IS
    r_quantity    pharmacy_product.quantity%TYPE;

BEGIN
    SELECT s.quantity INTO r_quantity
    FROM pharmacy_product s
    WHERE s.product_id = p_product AND s.pharmacy_id = p_pharmacy;
    RETURN r_quantity;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20011, 'The parmacy does not have the product with id ' || p_product || ' !');
        RETURN NULL;
END;