#ifndef ASM_H
#define ASM_H
typedef struct{
	int slotID;
	int vehicleID;
	int battery;
	int actualBat;
}park_slot;

typedef struct{
	int id;
	int occupancy;
	int power_output;
	int original_power_output;
	park_slot *slots;
}park;
int calculateEstimated(const int maxBat, const int actualBat, const int output);
long long save_park(park *parks_ptr, const int nr_parks,const int parkID);
void save_park_slot(park *park_ptr, const int slotID, const int scooterID, const int maxBat, const int actualBat );
#endif

