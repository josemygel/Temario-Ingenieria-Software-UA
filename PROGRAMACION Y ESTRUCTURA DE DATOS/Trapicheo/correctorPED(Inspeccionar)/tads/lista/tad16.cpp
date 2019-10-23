/* Prueba:
     InsertarI, Borrar y Obtener con posiciones vacias, para TListaCom
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a, b(1), c(2, 3), d(3,4);
  TListaCom l1;
  
  l1.InsCabeza(a);
  l1.InsertarI(b, l1.Ultima());
  l1.InsertarI(c, l1.Primera());
  l1.InsertarI(d, l1.Primera());
  
  cout<<"l1 = "<<l1<<endl;
  
  l1.InsertarI(d, TListaPos());
  
  TListaPos q;
  l1.Borrar(q);
  q.~TListaPos();
  
  l1.Obtener(TListaPos());
  
  cout<<"l1 = "<<l1<<endl;
   
  return 0;
}
