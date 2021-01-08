CREATE OR REPLACE FUNCTION funcGetPhamarcyByAdministrator(p_email phamarcy.administrator_email%TYPE) RETURN SYS_REFCURSOR
    IS
    cur_phamarcy    SYS_REFCURSOR;

BEGIN
    OPEN cur_phamarcy FOR
        SELECT a.* , b.*, c.*, d.user_password, d.user_name
        FROM phamarcy a, address b, administrator c, platform_user d
        WHERE a.administrator_email = c.email AND a.longitude = b.longitude AND a.latitude = b.latitude AND c.email = d.user_email AND a.administrator_email = p_email;
    RETURN cur_phamarcy;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20006, 'Phamarcy with adminstrator: ' || p_email || ' does not exist!');
        RETURN NULL;
END;