/*
 * event.h
 *
 *  Created on: Aug 19, 2019
 *      Author: OS1
 */

#ifndef EVENT_H_
#define EVENT_H_

#include "ivtentry.h"
#include "kernelev.h"

typedef unsigned char IVTNo;
class KernelEv;
class Event
{
private:
	KernelEv* myImpl;

public:
	Event(IVTNo ivtNo);
	~Event();
	void wait();
	void signal();
};



#endif /* EVENT_H_ */
