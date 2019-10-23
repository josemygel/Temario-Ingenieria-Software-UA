/* Prueba:
     "==", "!="
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{

  TComplejo a;
  TListaCom l1,l2;

  l1.InsCabeza(a);

  if(l1 == l2)
    cout << "SI" << endl;
  else
    cout << "NO" << endl;

  if(l1 != l2)
    cout << "SI" << endl;
  else
    cout << "NO" << endl;

  return 0;

}


