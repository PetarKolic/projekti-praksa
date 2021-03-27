#include "ListaSem.h"
#include "pcb.h"
#include "SCHEDULE.H"
#include "KerSem.h"
#include <stdio.h>

ListaSem::ListaSem():
prvi(0)
{}

void ListaSem::dodaj(Thread * nit, int vreme)
{
	Elem* tek = prvi;
	Elem *novi = new Elem(nit,vreme);
	Elem *pret = 0;

	if (prvi == 0)
	{
		prvi = novi;
		return;
	}

	int novoVreme = vreme;
	while(tek && tek->vreme <= novoVreme)
	{
		pret = tek;
		tek = tek->sled;
		novoVreme -= pret->vreme;
	}

	novi->vreme = novoVreme;
	if (pret == 0)
	{
		novi->sled = prvi;
		prvi->vreme -= novoVreme;
		prvi = novi;
	}
	else
	{
		novi->sled = tek;
		pret->sled = novi;
		if (novi->sled) novi->sled->vreme -= novoVreme;
	}
}



void ListaSem::izbaciSve()
{
	Elem* tek = prvi;
	Elem* pret = 0;

	while (tek) {

		pret = tek;
		tek = tek->sled;

		delete(pret);
	}

	prvi = 0;
}

void ListaSem::azuriraj()
{

	if (prvi == 0)
		return;

	prvi->vreme--;
	while(prvi && prvi->vreme == 0)
	{
		Elem *tek = prvi;
		prvi = prvi->sled;

		tek->nit->myPCB->semWaitVal = 0;
		tek->nit->myPCB->stanje(PCB::READY);
		tek->nit->myPCB->sem->value++;
		tek->nit->myPCB->sem->blokirane.izbaci(tek->nit);
		Scheduler::put(tek->nit->myPCB);

//		printf("\nSem addr in semlist: %p\n", tek->nit->myPCB->sem);

		delete tek;
	}
}

void ListaSem::izbaci(Thread * nit)
{
	Elem* tek = prvi;
	Elem* pret = 0;

	while(tek && tek->nit != nit)
	{
		pret = tek;
		tek = tek->sled;
	}

	if (tek == 0)
		return;

	if (pret == 0)
		prvi = prvi->sled;
	else
		pret->sled = tek->sled;

	if (tek->sled)
		tek->sled->vreme += tek->vreme;

	delete tek;
}


ListaSem::~ListaSem()
{
	izbaciSve();
}

ListaSem::Elem::Elem(Thread * nit, int vreme, Elem * sled)
	:nit(nit), sled(sled){

		if (vreme == 0)this->vreme = -1; //flag za beskonacno
		else this->vreme = vreme;
}
