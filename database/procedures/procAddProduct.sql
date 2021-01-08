CREATE or replace PROCEDURE procAddProduct(p_id product.product_id%TYPE,p_designation product.designation%TYPE, p_weight product.weight%TYPE, p_price product.price%TYPE) as
BEGIN
    INSERT INTO product(product_id, designation, weight, price)
    VALUES (p_id, p_designation, p_weight, p_price);
END;