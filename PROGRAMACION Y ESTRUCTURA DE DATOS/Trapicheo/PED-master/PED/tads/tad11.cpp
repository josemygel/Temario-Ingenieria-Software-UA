#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS = 7;

int main()
{
	int dias[kDIAS] = {4, 2, 15, 6, 10, 8};
	TCalendario c(1, 1, 2000, NULL);
	TABBCalendario yoyo, hulahop;

	// relleno yoyo	
	for(int i = 0; i < kDIAS; i++)
	{
		c.ModFecha(dias[i], c.Mes(), c.Anyo());
		yoyo.Insertar(c);	
	}
	
	// relleno hulahop
	dias[3] = 7;
	for(int i = 0; i < kDIAS; i++)
	{
		c.ModFecha(dias[i], c.Mes(), c.Anyo());
		yoyo.Insertar(c);	
	}
		
	if(yoyo == hulahop)
		cout << "EL OPERADOR == ESTA MAL" << endl;
	else
		cout << "NO IGUALES" << endl;
	return 0;
}
