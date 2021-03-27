#ifndef _PCB_H_

#define _PCB_H_



#include "Defs.h"
#include "Lista.h"
#include "ListaSem.h"



class Thread;
class PCB {


public:
	PCB(StackSize stack_size_in_bytes, Time time_slice, Thread *thread);
	~PCB();

	void create_and_initialize_stack(void);	//ne treba nam odma alokacija


	//blocked= 0
	enum Stanje { BLOCKED, READY, UNINITIALIZED, DONE };
	Stanje stanje() const;
	void stanje(Stanje stanje);

	void add_to_waiting_list(Thread *thread);

	static Thread* getId(ID id);	//koji osh id


	ID getId();	//svoj id


	static PCB* current_pcb;
	static PCB* idlePCB;

	Thread   *my_thread;
	unsigned ss, sp;
	Time      time_slice;
	Time time_slice_cnt;

	int semWaitVal;
	KernelSem *sem;
protected:

	static int zauzeto(int id);
private:
	StackSize stack_size_in_bytes;
	unsigned *stack; // alocirani prostor za stek niti
	Stanje _stanje;
	ID id;
	Lista waitLista;

	static unsigned counter;
	static int flag;

	static Lista lista;
	static void thread_run_wrapper();
};


#endif
