CREATE OR REPLACE procedure procUpdateClientAfterOrder(p_email platform_client.email%type, p_credits platform_client.credits%type)
AS


BEGIN
    UPDATE platform_client c
    SET c.credits = p_credits
    WHERE c.email= p_email;
    
    DELETE FROM cart_product WHERE client_email = p_email;
END;