CREATE OR REPLACE FUNCTION funcGetClientsByDeliveryRun(p_run delivery_run.delivery_run_id%type) RETURN SYS_REFCURSOR
IS
    cur_clients    SYS_REFCURSOR;
    ex_no_clients   EXCEPTION;
BEGIN
    OPEN cur_clients FOR
        SELECT D.USER_NAME, D.USER_PASSWORD, D.USER_EMAIL, A.PHONE_NUMBER, A.CREDITS, A.NIF, B.STREET, C.LONGITUDE, C.LATITUDE, C.ELEVATION,C.DESCRIPTION, B.CITY, B.PORT_NUMBER, B.ZIP_CODE
        FROM PLATFORM_CLIENT A, ADDRESS B, GEOGRAPHICAL_POINT C, PLATFORM_USER D, DELIVERY_RUN E, DELIVERY_ORDER F
        WHERE A.LONGITUDE = B.LONGITUDE AND A.LATITUDE = B.LATITUDE AND B.LONGITUDE = C.LONGITUDE AND B.LATITUDE = C.LATITUDE AND D.USER_EMAIL = A.EMAIL AND A.EMAIL=F.CLIENT_EMAIL AND F.DELIVERY_RUN_ID = E.DELIVERY_RUN_ID AND E.DELIVERY_RUN_ID = p_run;
    IF(cur_clients IS NULL) THEN
        RAISE ex_no_clients;
    END IF;
    return cur_clients;

EXCEPTION
    WHEN ex_no_clients THEN
        RAISE_APPLICATION_ERROR(-20239, 'No clients were found for that delivery run');
END;