/* Prueba:
    - INSERCION/BORRADO MULTIPLE
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

  int num;
  for(num=1; num<=100; num++)
  {
  	TComplejo c1( (double) num, 1);

  	if(!a.Insertar(c1))
		cout << "Error NO INSERCION" << endl;
	c1.~TComplejo();
  }

  cout << "CORRECTA INSERCION MULTIPLE" << endl;


  for(num=1; num<=100; num++)
  {
  	TComplejo c1( (double) num, 1);
  	if(!a.Borrar(c1))
		cout << "Error NO BORRADO" << endl;
	c1.~TComplejo ();
  }

  cout << "CORRECTO BORRADO MULTIPLE" << endl;

return 1;

}
