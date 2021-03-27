#ifndef _LISTASEM_H_
#define _LISTASEM_H_


#include "Thread.h"

class Thread;
class ListaSem
{
private:
	struct Elem
	{
	
		Thread *nit;
		int vreme;
		Elem *sled;
		Elem(Thread* nit, int vreme, Elem * sled = 0);
	};

public:

	ListaSem();

	void dodaj(Thread* nit,int vreme);
	//void izbaci(Thread* nit);
	//Thread* vrati(ID nit)const;
	void izbaciSve();

	void azuriraj();

	void izbaci(Thread* nit);

	int vratiTime(Thread* nit);

	~ListaSem();

private:
	Elem *prvi;
	
};



#endif 
