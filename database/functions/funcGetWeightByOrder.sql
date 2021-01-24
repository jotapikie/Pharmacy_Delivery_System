create or replace function funcGetWeightByOrder(id_o order_product.order_id%type) return product.weight%type as
    
    weight_o product.weight%type;
    quantity_o order_product.quantity%type;
    result_o number;
    
begin
    
    SELECT op.quantity, p.weight INTO quantity_o, weight_o
    FROM order_product op
    INNER JOIN product p ON op.product_id=p.weight;
    
    result_o:=quantity_o*weight_o;
    
    return result_o;
    
    
    exception
        when NO_DATA_FOUND then
            RAISE_APPLICATION_ERROR(-20007, 'Order_product with id: ' || id_o || ' does not exist!');
            return null;
    
end;