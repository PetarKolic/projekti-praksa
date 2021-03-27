/*
 * kernelev.cpp
 *
 *  Created on: Aug 19, 2019
 *      Author: OS1
 */

#include "kernelev.h"
#include "ivtentry.h"
#include "Defs.h"
#include "pcb.h"

KernelEv::KernelEv(IVTNo ivtNo)
{
	LOCK_CPU

	// inicijalizacija polja
	this->ivtNo = ivtNo;
	owner = PCB::current_pcb;
	value = 0;

	// registracija na dogadjaj
	IVTEntry::get(ivtNo)->attach(this);
	UNLOCK_CPU
}


KernelEv::~KernelEv()
{
	LOCK_CPU

	IVTEntry::get(ivtNo)->detach();

	UNLOCK_CPU
}

void KernelEv::wait()
{
	if (PCB::current_pcb == owner)
	{
		LOCK_CPU

		if (value == 0)
		{
			PCB::current_pcb->stanje(PCB::BLOCKED);
			value = 0;
			dispatch();
		}

		UNLOCK_CPU
	}
}


void KernelEv::signal()
{
	if (value == 0)
	{

			owner->stanje(PCB::READY);
			Scheduler::put(owner);
	}

	value = 1;
}



