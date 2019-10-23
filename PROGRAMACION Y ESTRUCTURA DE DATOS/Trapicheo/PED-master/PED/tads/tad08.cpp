#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS = 7;

int main()
{
	int dias[kDIAS] = {3, 2, 6, 1, 4, 7, 5};
	TCalendario c(1, 1, 2000, NULL);
	TABBCalendario ab;
	
	for(int i = 0; i < kDIAS; i++)
	{
		c.ModFecha(dias[i], c.Mes(), c.Anyo());
		ab.Insertar(c);	
	}
	
	TABBCalendario ac(ab);
	TABBCalendario ad;
	ad = ab;

	if(ac == ad)
		cout << "IGUALES" << endl;

	ac.Borrar(c);
	if(!(ac == ab) && !(ac == ad))
		cout << "NO IGUALES" << endl;

	ad.Borrar(c);
	if(ad == ac)
		cout << "IGUALES" << endl;
	
	TCalendario x(4, 1, 2000, NULL);
	ad.Borrar(x);
	if(!(ac == ad))
		cout << "NO IGUALES" << endl;

	return 0;
}
