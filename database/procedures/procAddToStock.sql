CREATE OR REPLACE PROCEDURE procAddToStock(p_pharmacy pharmacy.pharmacy_id%type,p_product cart_product.product_id%type, p_quantity cart_product.quantity%type)
AS
    r_id    cart_product.quantity%TYPE;

BEGIN
    
    SELECT a.quantity INTO r_id
    FROM pharmacy_product a
    WHERE a.pharmacy_id = p_pharmacy AND a.product_id = p_product;
    
    
        UPDATE pharmacy_product
        SET quantity = r_id + p_quantity
        WHERE pharmacy_id = p_pharmacy AND product_id = p_product;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        INSERT INTO pharmacy_product VALUES (p_pharmacy, p_product, p_quantity);
        
END;