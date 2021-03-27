#ifndef _LISTA_H_

#define _LISTA_H_

#include "Thread.h"

class Thread;
class Lista
{
private:
	struct Elem
	{
	
		Thread *nit;
		Elem *sled;
		Elem(Thread* nit, Elem * sled = 0)
		:nit(nit),sled(sled){}
	};

public:

	Lista();

	void dodaj(Thread* nit);
	void izbaci(Thread* nit);
	Thread* vrati(ID nit)const;
	void izbaciSve();

	Thread* izbaciPrvi();

	//Thread* izbaciPosl();

	~Lista();

private:
	Elem *prvi, *posl;
	
};



#endif 
