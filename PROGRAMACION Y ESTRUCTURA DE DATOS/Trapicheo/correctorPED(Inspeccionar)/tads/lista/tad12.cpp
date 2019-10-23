/* Prueba:
     - Funcion Siguiente() de TListaPos;
     - Funcion Borrar (TListaPos) de TListaCom
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a, b(1), c(2, 3), d(b);
  TListaCom l1;
  TListaPos p1, p2;
  
  l1.InsCabeza(a);
  l1.InsertarD(b,l1.Ultima());
  l1.InsertarD(c,l1.Ultima());
  l1.InsertarD(d,l1.Ultima());
  p1=l1.Primera();
  p2=(p1.Siguiente()).Siguiente();
  
  l1.Borrar(p1);
  l1.Borrar(p2);
  cout<<"l1 = "<<l1<<endl;
   
  return 0;
}
