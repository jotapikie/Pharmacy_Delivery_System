create or replace procedure procRegisterPark(id_park number, max_v number, category_p varchar2, id_pharm number) as 
begin

  INSERT INTO park(park_id, max_vehicles,vehicle_category,pharmacy_id)
  VALUES(id_park,max_v,category_p,id_pharm);
  
end;