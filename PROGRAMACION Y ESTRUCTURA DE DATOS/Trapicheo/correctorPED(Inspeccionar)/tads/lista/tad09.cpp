/* Prueba:
    - La sobrecarga de la operacion + para TListaCom
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a(1, -7);
  TListaCom lc1, lc2;
  
  for (int i=0; i<5; i++) {
         a = a + double(i);
         lc1.InsCabeza(a);
  }
  
  lc2=lc1;
  lc1=lc1 + lc2;
  cout << "lc1 = " << lc1 << endl;
  
  return 0;
}
