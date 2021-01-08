CREATE OR REPLACE FUNCTION funcGetClientPoints(p_email platform_client.email%TYPE) RETURN INT
    IS
    r_points    platform_client.credits%TYPE;

BEGIN
    SELECT p.credits INTO r_points
    FROM platform_client p
    WHERE p.email= p_email;
    RETURN r_points;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20003, 'Client with email: ' || p_email || ' does not exist!');
        RETURN NULL;
END;