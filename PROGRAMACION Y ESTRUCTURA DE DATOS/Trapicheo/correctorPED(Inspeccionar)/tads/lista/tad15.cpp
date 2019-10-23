/* Prueba:
    - La sobrecarga de la operacion = y constructor copia para TListaCom
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a(1, -7), b(1, -7);
  TListaCom lc1, lc2, lc3;
  
  for (int i=0; i<5; i++) {
         a = a + double(i);
         lc1.InsCabeza(a);
  }
  
  lc2=lc1;
  lc2.~TListaCom();
  cout << "lc1 = " << lc1 << endl;
  
  for (int i=0; i<5; i++) {
         b = b + double(i);
         lc3.InsCabeza(b);
  }
  TListaCom lc4(lc3);

  lc4.~TListaCom();
  cout << "lc3 = " << lc3 << endl;
  
   lc1.~TListaCom();
   lc3.~TListaCom();
   
   cout<< "Todo destruido" << endl; 
    
  return 0;
}
