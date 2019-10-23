/* Prueba:
    - Insertar(), "=="
*/

#include <iostream>
#include "tabbcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
  TABBCom a,b;
  TComplejo c1(1, 1);
  TComplejo c2(2, 2);
  TComplejo c3(3, 3);
  TComplejo c4(4, 4);
  TComplejo c5(5, 5);
  TComplejo c6(6, 6);
  TComplejo c7(7, 7);

  a.Insertar(c1);
  a.Insertar(c2);
  b.Insertar(c1);
  b.Insertar(c2);
  if (a==b)
	cout << "IGUALES" << endl;

  return 1;

}

