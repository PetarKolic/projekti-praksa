#ifndef _THREAD_H_
#define _THREAD_H_

#include "Defs.h"
#include "SCHEDULE.H"

	typedef unsigned long StackSize;
	typedef unsigned int Time; //time, x 55ms
	typedef int ID;



	class PCB;
	class Thread {

		public:
	
			void start();
			void waitToComplete();

			virtual ~Thread();

			ID getId();

			static ID getRunningId();

			static Thread *getThreadById(ID id);



		protected:

			friend class PCB; //dozovljen  pristup private clanovima objekta i static clanovima
			friend class KernelSem;
			friend class ListaSem;
			Thread(StackSize stackSize = defaultStackSize, Time timeSlice = defaultTimeSlice); //
			virtual void run(){}


		private:
			PCB* myPCB;


	};

	void dispatch();




	

#endif
