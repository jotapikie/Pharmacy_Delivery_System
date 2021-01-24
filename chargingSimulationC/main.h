#ifndef MAIN_H
#define MAIN_H
int exists(const char *fname);
void readValidatedFile(const char *fname, park *parks);
void writeFailFile( const int scooterID);
void writeEstimatedFile( const int scooterID, const int parkID, const int slotID, const int result);
void saveOnStruct(const int scooterID, const int parkID, const int slotID, const int maxBat, const int actualBat, park* parks);
#endif
