/* Prueba:
     EsVacia, Obtener
*/
#include <iostream>
#include "tlistacom.h"

using namespace std;

int
main(void)
{
  TComplejo a(-3, -3);
  TListaCom l1;
  TListaPos p1;
  
  if(l1.EsVacia())
    cout << "VACIA" << endl;
  else
    cout << "NO VACIA" << endl;

  l1.InsCabeza(a);
  p1=l1.Ultima();

  if(l1.EsVacia())
    cout << "VACIA" << endl;
  else
    cout<<l1.Obtener(p1)<<endl;

  return 0;
}
