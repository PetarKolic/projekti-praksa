#include<stdio.h>

#include "idle.h"
#include "pcb.h"
#include "MainThrd.h"
#include "isr.h"
#include "thread.h"
#include "Defs.h"
#include "Semaphor.h"

extern int userMain(int argc, char** argv);
//class TestThread : public Thread
//{
//public:
//	int flag;
//	void run();
//};
//
//void TestThread::run()
//{
//	flag = 1;
//
//	semA.signal(0);
//	semB.wait(0);
//}
//
//int userMain(int argc, char** argv)
//{
//	TestThread tt;
//
//	tt.start();
//
//	semA.wait(0);
//	semB.signal(0);
//
//	return 0;
//}
//
//
//void tick()
//{
//
//}

int main(int argc, char** argv)
{
	IdleThread idle;
	MainThread mainThread;

	PCB* idlePCB;

	idle.start();
	idlePCB = Scheduler::get();
	PCB::idlePCB = idlePCB;

	mainThread.start();
	PCB *mainPCB = Scheduler::get();
	PCB::current_pcb = mainPCB;

	timerRoutineSetup();

	userMain(argc, argv);

	timerRoutineCleanup();

	idlePCB->stanje(PCB::DONE);
	mainPCB->stanje(PCB::DONE);

	return 0;
}

