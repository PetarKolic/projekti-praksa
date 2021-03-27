#include "Lista.h"

Lista::Lista()
{
	prvi = 0;
	posl = 0;
}


Thread* Lista::izbaciPrvi()
{
	if (prvi == 0)
		return 0;

	Elem* t = prvi;
	Thread* thrd = t->nit;
	prvi = prvi->sled;

	if (prvi == 0)
		prvi = posl = 0;

	delete t;
	return thrd;
}

void Lista::dodaj(Thread * nit)
{
	Elem *novi = new Elem(nit);

	if (!prvi)
		prvi = novi;
	else
		posl->sled = novi;
	posl = novi;
}

void Lista::izbaci(Thread * nit)
{
	if (prvi == 0) return;

	if (prvi == posl && prvi->nit==nit) {
		
		//delete prvi->nit;
		delete prvi;
		prvi = posl = 0;
		return;
	}

	Elem* pret = 0;
	Elem* tek = prvi;
	while (tek) {


		if (tek->nit == nit)
		{

			if (tek == prvi) {

				pret = tek;
				prvi = tek->sled;
				delete(pret);
				return;
				
			}
			if (tek == posl) {

				posl = pret;
				posl->sled = 0;
				delete(tek);

				return;
			}
			pret->sled = tek->sled;
			delete(tek);
			return;

		}

		pret = tek;
		tek = tek->sled;
	}


}

Thread * Lista::vrati(ID nit) const
{
	

	for(Elem* tek = prvi; tek; tek=tek->sled )
	{
		if (tek->nit->getId() == nit)return tek->nit;
	}

	return 0;
}

void Lista::izbaciSve()
{
	Elem* tek = prvi;
	Elem* pret = 0;

	while (tek) {

		pret = tek;
		tek = tek->sled;

		delete(pret);
	}

	prvi = posl = 0;
}

Lista::~Lista()
{
	izbaciSve();
}
