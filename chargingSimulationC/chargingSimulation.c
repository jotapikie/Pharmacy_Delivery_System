#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <pcre.h>
#include "asm.h"
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <sys/stat.h>
char *path= "/media/partilha/pls/LAPR3/files";


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





void writeEstimatedFile( const int scooterID, const int result){
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
    sprintf(fileContent, "%d %d",scooterID,result);
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

void readValidatedFile(const char *filePath){
	FILE *file=NULL;
	file=fopen(filePath,"r");
	int i=0,j;
    int scooterID;
    int var;
    int input[2];
    if (file == NULL){
        exit(1);
    }
    fscanf (file, "%d", &scooterID);
    for (j = 0; j < 2; j++)
	{
		fscanf (file, "%d", &var);
		input[i++] = var;
	}
	fclose(file);
    if((input[0]==0) || (input[1]>input[0])){ 
		writeFailFile(scooterID);
    
    }else{
        int result= calculateEstimated(input[0],input[1]);
        writeEstimatedFile(scooterID, result);
		
	
	
}
}







int main(void) {
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
				readValidatedFile(lockPath);
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
