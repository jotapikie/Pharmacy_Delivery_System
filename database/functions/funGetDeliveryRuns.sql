create or replace FUNCTION funcGetDeliveryRuns(p_pharmacy pharmacy.pharmacy_id%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_deliveries   SYS_REFCURSOR;

BEGIN
    OPEN cur_deliveries FOR
        SELECT a.delivery_run_id
        FROM delivery_run a, delivery_order b
        WHERE a.delivery_run_id = b.delivery_run_id AND b.pharmacy_id = p_pharmacy AND b.status = 'Prepared';
    RETURN cur_deliveries;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20080, 'The pharmacy with id ' || p_pharmacy|| ' has no delivery runs assigned to scooters.');
        RETURN NULL;
END;