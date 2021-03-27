/*
 * event.cpp
 *
 *  Created on: Aug 19, 2019
 *      Author: OS1
 */


#include "event.h"
#include "Defs.h"
#include "kernelev.h"

Event::Event(IVTNo ivtNo)
{
	LOCK_CPU
	myImpl = new KernelEv(ivtNo);
	UNLOCK_CPU
}

Event::~Event()
{
	LOCK_CPU
	delete myImpl;
	UNLOCK_CPU;
}

void Event::wait()
{
	myImpl->wait();
}

void Event::signal()
{
	myImpl->signal();
}
