/* Prueba:
     "+", Primera, Ultima
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a, b(1), c(2, 3), d(3,4);
  TListaCom l1,l2,l3;
  
  l1.InsCabeza(a);
  l1.InsertarD(b, l1.Ultima());
  l2.InsCabeza(c);
  l2.InsertarI(d, l2.Primera());
  
  l3=l1+l2;
  cout<<"l3 = "<<l3<<endl;
   
  return 0;
}
