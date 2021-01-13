CREATE OR REPLACE FUNCTION funcPharmacyHasProduct(p_product pharmacy_product.product_id%type, p_quantity pharmacy_product.quantity%type, p_pharmacy pharmacy_product.pharmacy_id%type) RETURN INT
IS
    r_quantity    pharmacy_product.quantity%TYPE;

BEGIN

    SELECT p.quantity INTO r_quantity
    FROM pharmacy_product p
    WHERE p.pharmacy_id = p_pharmacy AND p.product_id = p_product AND (p.quantity >= p_quantity);


EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
END;