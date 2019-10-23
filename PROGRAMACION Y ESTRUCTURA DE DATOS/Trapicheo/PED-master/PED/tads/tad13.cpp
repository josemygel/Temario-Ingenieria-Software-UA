#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS1 = 14, kDIAS2 = 10;

int main()
{
	int diasA[kDIAS1] = {4, 2, 13, 1, 3, 12, 14, 8, 6, 10, 5, 9, 7, 11};
	int diasB[kDIAS2] = {4, 13, 1, 12, 8, 5, 22, 21, 7, 11};
	
	TCalendario relleno(1, 1, 2000, NULL);
	TCalendario miembro(1, 1, 2000, NULL);
	TABBCalendario dispuestoA;
	TABBCalendario pavo;

	// relleno el pavo
	for(int i = 0; i < kDIAS1; i++)
	{
		relleno.ModFecha(diasA[i], relleno.Mes(), relleno.Anyo());
		pavo.Insertar(relleno);	
	}

	// ehem
	for(int i = 0; i < kDIAS2; i++)
	{
		miembro.ModFecha(diasB[i], miembro.Mes(), miembro.Anyo());
		dispuestoA.Insertar(miembro);	
	}

	cout << "A: " << pavo.Inorden() << endl;
	cout << "B: " << dispuestoA.Inorden() << endl;
	cout << "RESTA(A - B): " << endl;
	cout << (pavo - dispuestoA).Inorden() << endl;
	cout << (pavo - dispuestoA).Preorden() << endl;	
	cout << "RESTA(B - A): " << endl;
	cout << (dispuestoA - pavo).Inorden() << endl;
	cout << (dispuestoA - pavo).Preorden() << endl;
	
	return 0;
}
