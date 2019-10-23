/* Prueba:
     - Función Anterior() de TListaPos;
*/
#include <iostream>
#include "tlistacom.h"

using namespace std;

int
main(void)
{
  TComplejo a(-3, -3);
  TListaCom l1;
  TListaPos p1;
  
  for (int i=0; i<10; i++) {
         a = a + double(i);
         l1.InsCabeza(a);
  }

  for (TListaPos p1=l1.Ultima(); !p1.EsVacia(); p1=p1.Anterior())
          cout<<l1.Obtener(p1)<<" ";
  cout<<endl;

  return 0;
}
