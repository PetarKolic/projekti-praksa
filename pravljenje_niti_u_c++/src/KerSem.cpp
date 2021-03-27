#include "KerSem.h"
#include "pcb.h"
#include <stdio.h>

ListaSem KernelSem::semLista;

KernelSem::KernelSem(int v) : value(v) {}

int KernelSem::signal(int n) {
	if (n < 0)
		return n;

	if (n == 0) {

		if (value++ < 0)
			deblock();

		return 0;
	}

	if (n > 0) {
		if (value >= 0) {
			value += n;
			return 0;
		}

		int min = value * (-1);
		if (min > n)
			min = n;

		for (int i = 0; i < min; ++i) {
			deblock();
		}

		value += n;

		return min;
	}

	return 0;
}

int KernelSem::wait(Time maxTimeToWait) {

	PCB::current_pcb->semWaitVal = 1;
	if (--value < 0)
		return block(maxTimeToWait);
	else
		return 1;

	return PCB::current_pcb->semWaitVal;
}

int KernelSem::block(Time maxTimeToWait) {
	blokirane.dodaj(PCB::current_pcb->my_thread);

	PCB::current_pcb->sem = this;
	PCB::current_pcb->stanje(PCB::BLOCKED);

//	printf("Semaddr: %p\n", PCB::current_pcb->sem);

	if (maxTimeToWait > 0)
		semLista.dodaj(PCB::current_pcb->my_thread, maxTimeToWait);
	dispatch();

	return PCB::current_pcb->semWaitVal;
}

void KernelSem::deblock() {
	Thread* t = blokirane.izbaciPrvi();
	semLista.izbaci(t);

	t->myPCB->semWaitVal = 1;
	t->myPCB->stanje(PCB::READY);
	Scheduler::put(t->myPCB);
}

int KernelSem::val() const {
	return value;
}
