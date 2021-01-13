CREATE or replace PROCEDURE procSaveInvoice(p_date invoice.inv_date%type, p_total_price invoice.total_price%type, p_price_paid invoice.price_paid%type, p_credits_spent invoice.credits_spent%type, p_credits_won invoice.credits_won%type, p_nif invoice.nif%type, p_order invoice.order_id%type) as
BEGIN
    INSERT INTO invoice(order_id, inv_date, total_price, price_paid, credits_spent, credits_won, nif) VALUES (p_order, p_date, p_total_price, p_price_paid, p_credits_spent, p_credits_won, p_nif);
END;