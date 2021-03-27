#ifndef _KERNELSEM_H_
#define _KERNELSEM_H_

#include"Semaphor.h"
#include "ListaSem.h"
#include "Lista.h"
#include "Pcb.h"

class KernelSem {
	friend class Thread;
	friend class ListaSem;
public:
	KernelSem(int v);	//uradio

	int signal(int n = 0);

	int wait(Time maxTimeToWait);


	int val() const;	//uradio

	static ListaSem semLista;
protected:
	int block(Time maxTimeToWait);
	void deblock();
private:
	int value;
	Lista blokirane;
};



#endif
