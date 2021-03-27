/*
 * ivtentry.cpp
 *
 *  Created on: Aug 19, 2019
 *      Author: OS1
 */

#include "ivtentry.h"
#include "Defs.h"
#include <dos.h>
#include "kernelev.h"


IVTEntry* IVTEntry::tbl[256] = {0};
IVTEntry::IVTEntry(IVTNo ivtNo, InterruptRoutine ir)
{
	LOCK_CPU

	ev = 0;
	_oldRoutine = getvect(ivtNo);
	setvect(ivtNo, ir);
	this->ivtNo = ivtNo;
	tbl[ivtNo] = this;

	UNLOCK_CPU
}

IVTEntry::~IVTEntry()
{
	LOCK_CPU

	setvect(ivtNo, _oldRoutine);
	if (ev) ev->signal();

	UNLOCK_CPU
}

void IVTEntry::attach(KernelEv* ev)
{
	this->ev = ev;
}

void IVTEntry::detach()
{
	ev = 0;
}
