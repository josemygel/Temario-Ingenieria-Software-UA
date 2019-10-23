/* Prueba:
     - Buscar, Nodos
*/

#include <iostream>
#include "tavlcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
  TAVLCom a;
  TComplejo c;
  TComplejo d33(33, 33), d78(78, 78);

  for(int i = 1; i <= 100; i++)
  {
    c.Re(i);
    c.Im(i);
    a.Insertar(c);
  }

  cout << "Nodos: " << a.Nodos() << endl;

  a.Borrar(d33);
  cout << "Nodos: " << a.Nodos() << endl;

  a.Borrar(d78);
  cout << "Nodos: " << a.Nodos() << endl;

  if(a.Buscar(d33))
    cout << "Si" << endl;
  else
    cout << "No" << endl;

  if(a.Buscar(d78))
    cout << "Si" << endl;
  else
    cout << "No" << endl;
}
