/* Prueba:
      - Función Longitud() de una TListaCom
      - Función Buscar(TComplejo) en una TListaCom
*/

#include <iostream>
#include "tlistacom.h"

using namespace std;

int
main(void)
{
  TComplejo a(-3, -3), b;
  TListaCom l1;
  
  for (int i=0; i<10; i++) {
         a = a + double(i);
         l1.InsCabeza(a);
  }
  cout<<l1.Longitud()<<endl;
  if (l1.Buscar(a)) cout<<"Encontrado c"<<endl;
  else cout<<"No encontrado c"<<endl;
  if (l1.Buscar(b)) cout<<"Encontrado b"<<endl;
  else cout<<"No encontrado c"<<endl;
  return 0;
}
