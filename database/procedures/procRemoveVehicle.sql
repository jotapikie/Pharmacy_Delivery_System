CREATE or replace PROCEDURE procRemoveVehicle(p_number vehicle.nr%TYPE, p_pharmacy pharmacy.pharmacy_id%type) as
BEGIN
	
    update VEHICLE set status = 'Inactive'
    where VEHICLE.nr = p_number AND VEHICLE.pharmacy_id = p_pharmacy;

EXCEPTION
    When NO_DATA_FOUND then
        raise_application_error(-20006, 'Error: NO_DATA_FOUND on procRemoveVehicle');
end;