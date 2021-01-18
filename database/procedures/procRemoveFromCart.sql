CREATE OR REPLACE procedure procRemoveFromCart(p_product product.product_id%type, p_client platform_client.email%type)
AS

BEGIN
    DELETE FROM cart_product
    WHERE client_email = p_client AND product_id = p_product;
END;