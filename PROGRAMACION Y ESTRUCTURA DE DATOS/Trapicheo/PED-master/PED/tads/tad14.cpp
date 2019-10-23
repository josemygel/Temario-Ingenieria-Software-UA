#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS1 = 14, kDIAS2 = 10;

int main()
{
	TABBCalendario a, b;
	TCalendario jus(1, 1, 2000, NULL);

	cout << (a - b).Inorden() << endl;
	b.Insertar(jus);

	cout << (a - b).Inorden() << endl;
	cout << (b - a).Inorden() << endl;


	return 0;
}
