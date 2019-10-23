/* Prueba:
    - Insertar(), Borrar() 
*/

#include <iostream>
#include "tabbcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{  
  TABBCom a;

  TComplejo c1(1, 1);
  TComplejo c2(2, 2);
  TComplejo c3(3, 3);
  TComplejo c4(4, 4);
  TComplejo c5(5, 5);
  TComplejo c6(6, 6);
  TComplejo c7(7, 7);

  a.Insertar(c5);
  a.Insertar(c2);
  a.Insertar(c1);
  a.Insertar(c3);
  a.Insertar(c6);

  if(a.Insertar(c7))
        cout << "Insertado" << endl;
  else
	cout << "Error NO INSERCION" << endl;

  if(a.Insertar(c7))
        cout << "Error INSERCION" << endl;
  else
	cout << "No insertado" << endl;

  if(a.Borrar(c7))
        cout << "Borrado" << endl;
  else
	cout << "Error NO BORRADO" << endl;

  if(a.Borrar(c7))
        cout << "Error BORRADO" << endl;
  else
	cout << "No borrado" << endl;

  if(a.Insertar(c7))
        cout << "Insertado" << endl;
  else
	cout << "Error NO INSERCION" << endl;



  return 1;
}
