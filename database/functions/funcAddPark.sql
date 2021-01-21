CREATE OR REPLACE FUNCTION funcAddPark(p_max_vehicles park.max_vehicles%type, p_category park.vehicle_category%type, p_max_energy park.max_energy%type, p_pharmacy pharmacy.pharmacy_id%type) RETURN INT
    IS
    r_id    park.park_id%TYPE;

BEGIN

    INSERT INTO PARK(max_vehicles, vehicle_category, max_energy, current_energy, pharmacy_id) VALUES (p_max_vehicles, p_category, p_max_energy, p_max_energy, p_pharmacy);
    
    SELECT p.park_id INTO r_id
    FROM park p
    WHERE p.pharmacy_id = p_pharmacy AND p.vehicle_category=p_category AND p.max_vehicles = p_max_vehicles AND p.max_energy = p_max_energy;
    
    RETURN r_id;
    
END;