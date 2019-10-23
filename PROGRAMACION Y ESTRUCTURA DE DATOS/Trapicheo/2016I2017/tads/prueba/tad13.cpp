/* Prueba:
     - Borrar
*/

#include <iostream>
#include "tavlcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;
int
main(void)
{
  TComplejo d1(1, 1), d2(2, 2), d3(3, 3), d4(4, 4);
  TAVLCom a;

  a.Insertar(d1);
  a.Insertar(d2);
  a.Insertar(d3);
  a.Insertar(d4);

  if(a.Borrar(d1))
    cout << "Si" << endl;
  else
    cout << "No" << endl;

  if(a.Borrar(d2))
    cout << "Si" << endl;
  else
    cout << "No" << endl;

  if(a.Borrar(d3))
    cout << "Si" << endl;
  else
    cout << "No" << endl;

  if(a.Borrar(d4))
    cout << "Si" << endl;
  else
    cout << "No" << endl;
}
