/* Prueba:
     - Borrar, EsVacio
*/

#include <iostream>
#include "tavlcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
  TComplejo d1(1, 1), d2(2, 2);
  TAVLCom a;

  a.Insertar(d1);
  a.Insertar(d2);

  a.Borrar(d1);

  if(a.EsVacio())
    cout << "Si" << endl;
  else
    cout << "No" << endl;

  a.Borrar(d2);

  if(a.EsVacio())
    cout << "Si" << endl;
  else
    cout << "No" << endl;
}
