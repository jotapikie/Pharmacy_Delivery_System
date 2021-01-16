chargingSimulation: chargingSimulation.o asm.o
	gcc -Wall -g chargingSimulation.o asm.o -o chargingSimulation -lpcre

chargingSimulation.o: chargingSimulation.c
	gcc -Wall -g -c chargingSimulation.c

asm.o: asm.s
	gcc -Wall -g -c asm.s

run: chargingSimulation
	./chargingSimulation

clean:
	rm -f *.o chargingSimulation
