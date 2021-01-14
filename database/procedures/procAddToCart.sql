CREATE OR REPLACE PROCEDURE procAddToCart(p_email cart_product.client_email%type,p_product cart_product.product_id%type, p_quantity cart_product.quantity%type)
AS
    r_id    cart_product.quantity%TYPE;

BEGIN
    
    SELECT a.quantity INTO r_id
    FROM cart_product a
    WHERE a.client_email = p_email AND a.product_id = p_product;
    
    
        UPDATE cart_product
        SET quantity = r_id + p_quantity
        WHERE client_email = p_email AND product_id = p_product;
    
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        INSERT INTO cart_product VALUES (p_email, p_product, p_quantity);
        
END;
