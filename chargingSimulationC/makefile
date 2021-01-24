chargingSimulation: chargingSimulation.o save_park_slot.o save_park.o calculateEstimated.o
	gcc -Wall -g chargingSimulation.o save_park.o save_park_slot.o calculateEstimated.o -o chargingSimulation -lpcre

chargingSimulation.o: chargingSimulation.c
	gcc -Wall -g -c chargingSimulation.c

calculateEstimated.o: calculateEstimated.s
	gcc -Wall -g -c calculateEstimated.s
	
save_park.o: save_park.s
	gcc -Wall -g -c save_park.s
	
save_park_slot.o: save_park_slot.s
	gcc -Wall -g -c save_park_slot.s

run: chargingSimulation
	./chargingSimulation

clean:
	rm -f *.o chargingSimulation
