/* Prueba:
     prueba de metodos con una TListaCom vacia
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a, b(1), c(2, 3), d(3,4);
  TListaCom l1;
  
  l1.Borrar(a);
  l1.BorrarTodos(a);
  cout << l1.Longitud() << endl;
  
  TListaCom l2(l1);
  cout << (l1==l2) << endl;
    
  cout<<"l1 = "<<l1<<endl;
   
  return 0;
}
