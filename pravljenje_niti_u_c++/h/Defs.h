#ifndef _DEFS_H_

#define _DEFS_H_

#define PSWI 0x0200
#define LOCK_CPU {asm pushf; asm cli;}	// ove makroe ne diram za sada
#define UNLOCK_CPU asm popf;

#define VELIKIBROJ ((1 << 15) - 2) // gotovo maximalni broj uint

typedef unsigned long StackSize;
const StackSize defaultStackSize = 4096;
typedef unsigned int Time;
const Time defaultTimeSlice = 2;
typedef int ID;


#endif



// Zasto koristimo move ss zato sto se sa drugih stackova skidaju druge stvari
// mainPCB ce se nalazi u scheduleru sve vreme ali se nece izvrsiti do kraja zbog wait to complete funkcije
// waitToComplete u destruktoru maina poziva za nit n1 ona mora da saceka da se izvrsi n1,n2 koristi se waitToComplete

// u slucaju da main brise mainThrd nece imati efekta jer je markiran kao DONE

// waitToComplete
// Main ide i zavrsi se do kraja i nema nikakve promene kontexta sve vreme nastace problem
// jer se nece obrisati nista(ako sto je u delete znaci mora doci do promene kontexta

// ako main promeni kontext

// MainThrd pravi stack koji nece koristiti main, vec ce on na ulazu u prekidnu rutinu
// na svom main_sp ostavljati sve vrednosti i kasnije ih kupiti za naknadno koriscenje

// waitToComplete 
// main ih iskljucivo poziva
// nece poceti da ceka kad ih obelezi kao done

// this.waitToComplete(currentPCB)

// u destruktoru kad se pozove

// destruktor izgleda treba da bude public hmph

// KernelEv

// signal<0 bez dejstva
// n>=0 odblokiraj kolko mozes

// Liste
// PCB
// waitLista - lista blokiranih zbog wait to complete 	//odblokirati se mogu jedino preko
// static Lista- lista svih niti ikada napravljenih

// Sem
// lista blokiranih //zbog wait
// static ListaSem semLista // lista svih koji cekaju maxVreme

// PCB::semWaitValue dal je otkljucan zbog maxVremena ili zbog signal();


// PCB::sem postoji u slucaju
// isteka vremena kako bi pri odblokiranju te niti znala da poveca
// vrednost value++ kako bi se znalo kolko ukupno ceka
// I kako bi izbacila iz liste blokiranih




// Samo ona nit koja je napravila semaphor moze da radi
// wait








// ivtEntry


// #define PREPAREENTRY(ivtno, callOld)\
	// void interrupt stubRoutine##ivtno(...);\			//deklaracija prekidne rutine
	// IVTEntry entry##ivtno(ivtno, stubRoutine##ivtno);\	//pravimo objekat klase IVTEntry
	// void interrupt stubRoutine##ivtno(...){\			//definicija prekidne rutine
		// if (entry##ivtno.event())\						//ako se neko registrovao na prekidnu rutinu ovog ulaza  signal
			// entry##ivtno.event()->signal();\
		// if (callOld) entry##ivtno.oldRoutine()();\
	// }

// u konstruktoru ubacujemo
// napravljenu rutinu preko PrEAPerenttry 
// i pamtimo staru i pamtimo u koji ulaz ide