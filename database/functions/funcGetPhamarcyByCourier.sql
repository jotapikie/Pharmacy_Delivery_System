CREATE OR REPLACE FUNCTION funcGetPhamarcyByCourier(p_email phamarcy.administrator_email%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_phamarcy    SYS_REFCURSOR;

BEGIN
    OPEN cur_phamarcy FOR
        SELECT a.phamarcy_id, a.phone_number, a.designation , d.*,b.*
        FROM phamarcy a, address b, administrator c, platform_user d, courier e
        WHERE a.administrator_email = c.email AND a.longitude = b.longitude AND a.latitude = b.latitude AND c.email = d.user_email AND e.phamarcy_id = a.phamarcy_id AND e.phamarcy_id = p_email;
    RETURN cur_phamarcy;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20011, 'Phamarcy with courier: ' || p_email || ' does not exist!');
        RETURN NULL;
END;