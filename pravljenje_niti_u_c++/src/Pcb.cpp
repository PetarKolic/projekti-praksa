#include "Pcb.h"
#include "Thread.h"
#include <dos.h>


PCB* PCB::idlePCB = 0;
PCB* PCB::current_pcb = 0;
Lista PCB::lista;
unsigned PCB::counter = 0;
int PCB::flag = 0;

//ListaSem PCB::semaforiList;

PCB::PCB(StackSize _stack_size_in_bytes, Time _time_slice, Thread *thread):
	stack_size_in_bytes(_stack_size_in_bytes),
	time_slice(_time_slice),
	time_slice_cnt(0),
	my_thread(thread),
	stack(0),
	ss(0),
	sp(0),
	_stanje(UNINITIALIZED),
	sem(0)
{
	
	//if (!original(my_thread)) return;


	//ovako bi mogao da napravi max 2^32 niti za vreme svog postojanja jer zavrsene 
	//niti ne bi gubile jedinstven nit cak ni posle brisanja istih
	
	if (flag || counter > VELIKIBROJ) {

		LOCK_CPU	//NE MORA unutar jer situacija moze samo da se popravi
					// ako je usao usao je

		flag = 1;
		while (zauzeto(counter))
			counter++;

		id = counter;

		UNLOCK_CPU

	}
	else
		id = counter++;

	

	lista.dodaj(my_thread);	//dodao
}


PCB::~PCB()
{
	lista.izbaci(my_thread);	//dodao
	delete[] stack;
}


void PCB::create_and_initialize_stack()
{
	unsigned stack_len = stack_size_in_bytes / sizeof(unsigned);
	
	stack			      = new unsigned[stack_len];	// novi stack
	stack[stack_len - 1]  = PSWI;	//maskirajuci prekidi
	stack[stack_len - 2]  = FP_SEG(thread_run_wrapper);	//povratna adresa
	stack[stack_len - 3]  = FP_OFF(thread_run_wrapper);
	// stack[stack_len - 12] = FP_OFF(stack + stack_len - 1); // podesavamo bp
	ss = FP_SEG(stack);
	sp = FP_OFF(stack + stack_len - 12);
}

void PCB::add_to_waiting_list(Thread *thread)
{
	thread->myPCB->stanje(BLOCKED);
	waitLista.dodaj(thread);
}

Thread * PCB::getId(ID id)
{
	return lista.vrati(id);
}

ID PCB::getId()
{
	return id;
}

int PCB::zauzeto(int id)
{
	if (PCB::getId(id) == 0)return 0;

	return 1;
}


//ID

//
//
//
void PCB::thread_run_wrapper()
{
	current_pcb->my_thread->run();


	Thread *t = 0;
	LOCK_CPU
	while((t = PCB::current_pcb->waitLista.izbaciPrvi()) != 0)
	{
		PCB* pcb = t->myPCB;
		pcb->stanje(PCB::READY);
		Scheduler::put(pcb);
	}
	UNLOCK_CPU

	PCB::current_pcb->_stanje = DONE;
	dispatch();
}


PCB::Stanje PCB::stanje() const	// nzm sto se buni
{
	return _stanje;
}


void PCB::stanje(PCB::Stanje state)	//ovde isto
{
	_stanje = state;
}



//prilikom poziva push PSW push pc
//interrupt
//push ax,bx,cx,dx,es,ds,si,di,bp 
//mov bp,sp

