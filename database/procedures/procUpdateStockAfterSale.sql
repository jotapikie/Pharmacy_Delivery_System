CREATE OR REPLACE PROCEDURE procUpdateStockAfterSale(p_pharmacy pharmacy_product.pharmacy_id%type,p_product pharmacy_product.product_id%type, p_quantity pharmacy_product.quantity%type)
AS
    r_id    cart_product.quantity%TYPE;

BEGIN
    
    SELECT a.quantity INTO r_id
    FROM pharmacy_product a
    WHERE a.pharmacy_id = p_pharmacy AND a.product_id = p_product;
    
    
   UPDATE pharmacy_product
   SET quantity = r_id - p_quantity
   WHERE pharmacy_id = p_pharmacy AND product_id = p_product;
        
        
END;