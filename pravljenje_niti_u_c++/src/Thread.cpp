#include "Thread.h"
#include "Pcb.h"
#include "SCHEDULE.h"
#include "defs.h"
#include "isr.h"

Thread::Thread(StackSize stackSize, Time timeSlice)
{
	myPCB = new PCB(stackSize, timeSlice, this);
}

void Thread::start()
{
	LOCK_CPU
	if (myPCB->stanje() == PCB::UNINITIALIZED)
	{
		myPCB->create_and_initialize_stack();
		myPCB->stanje(PCB::READY);
		Scheduler::put(myPCB);
	}
	UNLOCK_CPU
}

Thread::~Thread()
{
	// waitToComplete();
	delete myPCB;

	myPCB = 0;
}

ID Thread::getId()
{
	return myPCB->getId();
}

ID Thread::getRunningId()
{
	return PCB::current_pcb->getId();
}

Thread * Thread::getThreadById(ID id)
{
	return PCB::getId(id);
}

void Thread::waitToComplete()
{
	LOCK_CPU
	if (myPCB->stanje() != PCB::DONE)
	{
		myPCB->add_to_waiting_list(PCB::current_pcb->my_thread);
		dispatch();
	}
	UNLOCK_CPU
}

void dispatch()
{
	LOCK_CPU

	dispatchFlag = 1;
	timerRoutine();

	UNLOCK_CPU
}


