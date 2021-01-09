CREATE OR REPLACE FUNCTION funcGetCourier(p_email courier.email%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_courier    SYS_REFCURSOR;

BEGIN
    OPEN cur_courier FOR
        SELECT u.*, c.nif, c.nss, c.max_weight
        FROM courier c, platform_user u
        WHERE c.email = u.user_email;
    RETURN cur_courier;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20007, 'Courier with email: ' || p_email || ' does not exist!');
        RETURN NULL;
END;