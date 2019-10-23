/* Prueba:
     - BorrarTodos (TComplejo) en una lista TListaCom
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a, b(1), c(2, 3), d(b);
  TListaCom l1;
  
  l1.InsCabeza(a);
  l1.InsCabeza(b);
  l1.InsCabeza(c);
  l1.InsCabeza(d);
  l1.BorrarTodos(d);
  
  cout<<"l1 = "<<l1<<endl;
   
  return 0;
}
