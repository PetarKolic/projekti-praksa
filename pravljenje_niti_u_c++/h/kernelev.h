/*
 * kernelev.h
 *
 *  Created on: Aug 19, 2019
 *      Author: OS1
 */

#ifndef KERNELEV_H_
#define KERNELEV_H_

#include "event.h"

class PCB;
class KernelEv
{
private:
	int value;
	PCB *owner;
	IVTNo ivtNo;
public:
	KernelEv(IVTNo ivtNo);
	~KernelEv();
	void wait();
	void signal();
};





#endif /* KERNELEV_H_ */
