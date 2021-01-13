CREATE or replace PROCEDURE procSaveOrderProduct(p_order delivery_order.order_id%type, p_product product.product_id%type, p_quantity order_product.quantity%type) as
BEGIN
    INSERT INTO order_product(order_id, product_id, quantity) VALUES (p_order, p_product, p_quantity);
END;