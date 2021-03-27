/*
 * ivtentry.h
 *
 *  Created on: Aug 19, 2019
 *      Author: OS1
 */

#ifndef IVTENTRY_H_
#define IVTENTRY_H_


#define PREPAREENTRY(ivtno, callOld)\
	void interrupt stubRoutine##ivtno(...);\
	IVTEntry entry##ivtno(ivtno, stubRoutine##ivtno);\
	void interrupt stubRoutine##ivtno(...){\
		if (entry##ivtno.event())\
			entry##ivtno.event()->signal();\
		if (callOld) entry##ivtno.oldRoutine()();\
	}

class KernelEv;
typedef unsigned char IVTNo;
typedef void interrupt (*InterruptRoutine)(...);
class IVTEntry
{
private:
	InterruptRoutine _oldRoutine;
	KernelEv *ev;
	IVTNo ivtNo;

	static IVTEntry* tbl[256];
public:
	IVTEntry(IVTNo, InterruptRoutine ir);
	~IVTEntry();

	void attach(KernelEv*);
	void detach();
	KernelEv* event() const {return ev;}
	InterruptRoutine oldRoutine() const { return _oldRoutine;}
	static IVTEntry* get(IVTNo ivtno) { return tbl[ivtno];}
};

#endif /* IVTENTRY_H_ */
