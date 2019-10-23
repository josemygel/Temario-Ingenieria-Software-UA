#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS = 14;

int main()
{
	int dias[kDIAS] = {4, 2, 13, 1, 3, 12, 14, 8, 6, 10, 5, 9, 7, 11};
	TCalendario relleno(1, 1, 2000, NULL);
	TABBCalendario pavo;

	// relleno el pavo
	for(int i = 0; i < kDIAS; i++)
	{
		relleno.ModFecha(dias[i], relleno.Mes(), relleno.Anyo());
		pavo.Insertar(relleno);	
	}
	
	cout << pavo.Inorden() << endl;
	cout << pavo.Preorden() << endl;

	relleno.ModFecha(6, relleno.Mes(), relleno.Anyo());
	if(pavo.Buscar(relleno))
		cout << ".ENCONTRADO" << endl;
	
	relleno.ModFecha(11, relleno.Mes(), relleno.Anyo());
	if(pavo.Buscar(relleno))
		cout << "..ENCONTRADO" << endl;
	
	relleno.ModFecha(15, relleno.Mes(), relleno.Anyo());
	if(!pavo.Buscar(relleno))
		cout << "...NO ENCONTRADO" << endl;

	relleno.ModFecha(11, relleno.Mes(), relleno.Anyo());
	
	int diasDeCine[6] = {8, 7, 6, 5, 8, 10};
	// glu glu glu
	for(int i = 0; i < 6; i++)
	{
		relleno.ModFecha(diasDeCine[i], relleno.Mes(), relleno.Anyo());
		if(pavo.Borrar(relleno))
		{
			cout << "borrado: " << relleno << endl;
			cout << "INORDEN: " << pavo.Inorden() << endl;
			cout << "PREORDEN: " << pavo.Preorden() << endl;
		}
		else
			cout << "no borrado: " << relleno << endl;
	}
	
	return 0;
}
