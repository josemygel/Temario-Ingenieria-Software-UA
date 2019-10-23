/* Prueba:
     CONST. COPIA
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a(1);
  
  TListaCom l1;
  l1.InsCabeza(a);

  TListaCom l2(l1),l3(l2);

  cout<<"l1 = "<<l1<<endl;
  cout<<"l2 = "<<l2<<endl;
  cout<<"l3 = "<<l3<<endl;

  return 0;
}


