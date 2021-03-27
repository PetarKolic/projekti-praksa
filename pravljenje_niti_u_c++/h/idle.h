/*
 * idle.h
 *
 *  Created on: Aug 16, 2019
 *      Author: OS1
 */

#ifndef IDLE_H_
#define IDLE_H_

#include "Thread.h"
class IdleThread: public Thread
{
protected:
	void run() { while(1);}
};

#endif /* IDLE_H_ */
