create or replace function funcGetPharmacyByID(id_p number) return sys_refcursor is

cur_pharmacy    sys_refcursor;

begin

     OPEN cur_pharmacy FOR
        SELECT a.pharmacy_id, a.phone_number, a.designation , d.*,b.*
        FROM pharmacy a, address b, administrator c, platform_user d, courier e
        WHERE a.pharmacy_id = id_p AND a.longitude = b.longitude AND a.latitude = b.latitude AND c.email = d.user_email AND e.pharmacy_id = a.pharmacy_id;
    return cur_pharmacy;

exception
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20011, 'Pharmacy with id: ' || id_p || ' does not exist!');
        return null;
end;