/*
 * isr.h
 *
 *  Created on: Aug 13, 2019
 *      Author: OS1
 */

#ifndef ISR_H_
#define ISR_H_


extern int dispatchFlag;
void interrupt timerRoutine(...);
void timerRoutineSetup();
void timerRoutineCleanup();

void tick(); //dodao

#endif /* ISR_H_ */