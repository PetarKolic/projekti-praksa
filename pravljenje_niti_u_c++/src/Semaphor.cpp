#include"Semaphor.h"


Semaphore::Semaphore(int init) {

	LOCK_CPU
	myImpl = new KernelSem(init);
	UNLOCK_CPU
}

Semaphore::~Semaphore()
{
	LOCK_CPU
	delete myImpl;
	UNLOCK_CPU
}

int Semaphore::wait(Time maxTimeToWait)
{
	LOCK_CPU
	int retVal = myImpl->wait(maxTimeToWait);
	UNLOCK_CPU

	return retVal;
}

int Semaphore::signal(int n)
{
	LOCK_CPU
	int retVal = myImpl->signal(n);
	UNLOCK_CPU

	return retVal;
}

int Semaphore::val() const
{
	LOCK_CPU
	int retVal = myImpl->val();
	UNLOCK_CPU

	return retVal;
}
