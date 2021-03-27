/*
 * isr.cpp
 *
 *  Created on: Aug 13, 2019
 *      Author: OS1
 */
#include "isr.h"
#include <dos.h>
#include "schedule.h"
#include "pcb.h"
#include "KerSem.h"

//idle nit
// inicijalizacija sistema i pokretanje main_moj->sistemski poziv->main korisnika
int dispatchFlag = 0;
static void interrupt (*oldTimerRoutine)(...);

static int hss, hsp;

void timerRoutineSetup()
{
	oldTimerRoutine = getvect(0x08);
	setvect(0x08, timerRoutine);
}

void interrupt timerRoutine(...)
{
	// da li se poziva stara prekid. rutina?
	// Ovaj IF ovde je da se proveri da li je prekid pozvan zbog generisanja prekida tajmera
	// ili zato sto smo pozvali ovu prekidnu rutinu preko funkcije dispatch
	if (!dispatchFlag)
	{
		tick(); //dodao
		oldTimerRoutine();
		PCB::current_pcb->time_slice_cnt++;
		KernelSem::semLista.azuriraj();
	}

	// promena konteksta
	// desava se kada neko eksplicitno to zatrazi preko dispatch-a
	// ili kada niti istekne vreme
	if (dispatchFlag || (PCB::current_pcb->time_slice_cnt == PCB::current_pcb->time_slice && PCB::current_pcb->time_slice))
	{
		dispatchFlag = 0;

		if (PCB::current_pcb->stanje() == PCB::READY)
		{
			Scheduler::put(PCB::current_pcb);
		}

		// Pamtimo stanje tekuce niti
		asm{
			mov hss, ss
			mov hsp, sp
		}
		PCB::current_pcb->sp = hsp;
		PCB::current_pcb->ss = hss;

		// Dohvatamo novu nit iz schedulera
		PCB::current_pcb = Scheduler::get();
		if (PCB::current_pcb == 0)
			PCB::current_pcb = PCB::idlePCB;

		PCB::current_pcb->time_slice_cnt = 0;
		// Postavljanje nove niti kao tekuce
		hsp = PCB::current_pcb->sp;
		hss = PCB::current_pcb->ss;
		asm{
			mov ss, hss
			mov sp, hsp
		}
	}
}


void timerRoutineCleanup()
{
	LOCK_CPU

	setvect(0x08, oldTimerRoutine);

	UNLOCK_CPU
}
