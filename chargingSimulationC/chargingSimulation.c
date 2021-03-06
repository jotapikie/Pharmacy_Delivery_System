#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <pcre.h>
#include "asm.h"
#include "main.h"
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <limits.h>
#include <sys/stat.h>
char *path= "/media/partilha/pls/LAPR3/files";
short nr_parks = 0;

int exists(const char *filePath)
{
    FILE *fptr = NULL;
    fptr= fopen(filePath, "r");
    // If file does not exists 
    if (fptr == NULL){
        perror("\nError:\n ");
        return 0;
    }
    // File exists hence close file and return true.
    fclose(fptr);
    return 1;
    
}



void writeFailFile( const int scooterID){
	char newText[20];
    time_t now= time(NULL);
    struct tm *t =localtime(&now);
    strftime(newText, sizeof(newText)-1,"%Y_%m_%d_%H_%M",t);
    
    char *name= (char*) calloc(50,sizeof(char));
    char *newFilePath= (char*) calloc(200,sizeof(char));
    char *newFlagPath= (char*) calloc(200,sizeof(char));
    sprintf(name, "estimated_%s_00.data", newText);
    sprintf(newFilePath,"%s",path);
    strcat(newFilePath,"/");
    strcat(newFilePath,name);
    strcpy(newFlagPath, newFilePath);
    strcat(newFlagPath, ".flag");
 
    
 
    FILE *fptr =NULL;
    fptr= fopen(newFilePath, "wb");
    if (fptr ==NULL)
	{
		exit(1);
	}
    fprintf(fptr, "%d", scooterID );
    fclose(fptr);
    
    
    fptr= fopen(newFlagPath, "wb");
    if (fptr ==NULL)
	{
		exit(1);
	}
	fclose(fptr);
	free(name);
    free(newFilePath);
    free(newFlagPath);

}



void writeEstimatedFile( const int scooterID, const int parkID, const int slotID, const int result){
	char newText[20];
    time_t now= time(NULL);
    struct tm *t =localtime(&now);
    strftime(newText, sizeof(newText)-1,"%Y_%m_%d_%H_%M",t);
    
    char *name= (char*) calloc(50,sizeof(char));
    char *newFilePath= (char*) calloc(200,sizeof(char));
    char *newFlagPath= (char*) calloc(200,sizeof(char));
    sprintf(name, "estimated_%s_34.data", newText);
    sprintf(newFilePath,"%s",path);
    strcat(newFilePath,"/");
    strcat(newFilePath,name);
    strcpy(newFlagPath, newFilePath);
    strcat(newFlagPath, ".flag");
    
    char *fileContent= (char*) calloc(20,sizeof(char));
    sprintf(fileContent, "%d %d %d %d ",scooterID, parkID, slotID,result);
    FILE *fptr =NULL;
    fptr= fopen(newFilePath, "wb");
    if (fptr ==NULL)
	{
		exit(1);
	}
	fprintf(fptr, "%s", fileContent );
    fclose(fptr);
    
    FILE *fp=NULL;	
    fp=fopen(newFlagPath, "w");
    if (fp ==NULL)
	{
		exit(1);
	}
    fclose(fp);
    free(name);
    free(newFilePath);
    free(newFlagPath);
    free(fileContent);
}



void readValidatedFile(const char *filePath, park *parks){
	FILE *file=NULL;
	file=fopen(filePath,"r");
    int scooterID;
    int parkID;
    int slotID;
    int input[2];
    if (file == NULL){
        exit(1);
    }
    fscanf (file, "%d", &scooterID);
    fscanf (file, "%d", &parkID);
    fscanf (file, "%d", &slotID);
    fscanf (file, "%d", &input[0]);
    fscanf (file, "%d", &input[1]);
	fclose(file);
    if((input[0]==0) || (input[1]>input[0])){ 
		writeFailFile(scooterID);
    
    }else{
        saveOnStruct(scooterID, parkID, slotID,input[0], input[1], parks);
}
}



void saveOnStruct(const int scooterID, const int parkID, const int slotID, const int maxBat, const int actualBat, park *parks){
	  printf("\n%d",nr_parks);
	   printf("\n%d", parks->id);
      long long temp = save_park(parks, nr_parks, parkID);
      printf("\n%d", parks->id);
      int check = (int) (temp & UINT_MAX);
      int i = (int) (temp >> 32);
      //Creates a pointer for the current park
      park *park_ptr = &parks[i];
      if(check == 2){
	  nr_parks++;
	  park *tmp_ptr = (park*) realloc(parks, sizeof(park) * (nr_parks + 1));
      if (tmp_ptr != NULL) {
      parks = tmp_ptr;
      tmp_ptr = NULL;
      }
      park *park_ptr = &parks[i];
	
	   park_ptr = &parks[i];
	   park_ptr -> slots = (park_slot*) calloc(1, sizeof(park_slot));
   }
       save_park_slot(park_ptr, slotID, scooterID, maxBat, actualBat);
       
       park_slot *tmp = (park_slot*) realloc(park_ptr->slots, sizeof(park_slot) * (park_ptr->occupancy +1));  
       if (tmp != NULL)
	   {
		   park_ptr->slots =tmp;
		   tmp = NULL;
	   }
	   short k;
	   for (k = 0; k < park_ptr->occupancy ; k++)
	   {
		park_slot *slot_ptr= &park_ptr->slots[k];
		printf("\n%d", park_ptr->power_output);
		int result= calculateEstimated(slot_ptr-> battery, slot_ptr-> actualBat, park_ptr-> power_output);
		writeEstimatedFile(slot_ptr->vehicleID,*(&park_ptr->id), slot_ptr->slotID, result);
		sleep(61);  
	   }
	   
	   


}



int main(void) {
	park *parks = (park*) calloc(2, sizeof(park));
     	DIR *dir_ptr = opendir(path);
	if ((dir_ptr ) == NULL){
		printf("ERROR\n");
		return -1;
	}else{
	
	struct dirent *dir_entery;
	int value;
	char pattern[]="^lock_([12][0-9]{3}_(0[1-9]|1[0-2])_(0[1-9]|[12][0-9]|3[01])?_([01]?[0-9]|2[0-3])_([0-5]?[0-9])_)?([0-5]?[0-9]).data$";
	pcre *re;
	const char *error;
	int erroffset;
	while(1){
	while ((dir_entery = readdir(dir_ptr))!= NULL){
		 
        char *name= (char*) calloc(50,sizeof(char));
        char *lockPath= (char*) calloc(200,sizeof(char));
        char *lockFlagPath= (char*) calloc(200,sizeof(char));
		strcpy(name,dir_entery-> d_name);
		strcpy(lockPath,path);
		strcat(lockPath, "/");
		strcat(lockPath, name);
		strcpy(lockFlagPath, lockPath);
		strcat(lockFlagPath, ".flag");
		re=pcre_compile(pattern,0,&error,&erroffset,NULL);
		value=pcre_exec(re, NULL,name,strlen(name), 0, 0,0,0);
		if (value>=0)
		{
			int isThere;
			isThere= exists(lockFlagPath);
			if (isThere==1)
			{
				readValidatedFile(lockPath, parks);
				remove(lockPath);
				remove(lockFlagPath);
			    
		
			}
			free(name);
			free(lockPath);
			free(lockFlagPath);
			
		}else{
			printf("\nFound 1 file but name doesnt match\n")
			;}	
		
	}
	}}
	return 0;
}
