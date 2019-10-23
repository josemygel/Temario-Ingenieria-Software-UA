/* Prueba:
     InsCabeza, InsertarI, InsertarD
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a, b(1), c(2, 3);
  TListaCom l1;
  
  l1.InsCabeza(a);
  l1.InsertarI(b, l1.Ultima());
  l1.InsertarD(c, l1.Primera());

  cout<<"l1 = "<<l1<<endl;
   
  return 0;
}
