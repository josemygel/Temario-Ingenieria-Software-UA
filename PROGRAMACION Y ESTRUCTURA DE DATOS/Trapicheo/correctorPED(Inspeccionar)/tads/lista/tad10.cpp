/* Prueba:
     La sobrecarga de la operacion - para TListaCom
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
  l1.InsertarD(c, l1.Primera());
  l2=l1;
  l3.InsCabeza(a); 
  
  l1=l1+l2+l3;
  cout<<"l1 = "<<l1<<endl;
  l2=l1 - l3;
  cout<<"l2 = "<<l2<<endl;
  l2=l2 - l1;
  cout<<"l2 = "<<l2<<endl;
   
  return 0;
}
