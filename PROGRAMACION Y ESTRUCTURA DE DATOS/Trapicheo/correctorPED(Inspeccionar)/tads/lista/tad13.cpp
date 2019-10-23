/*Prueba:
     - Funcion Siguiente() de TListaPos
     - Funcion Obtener(TListaPos)
     - Buscar(TComplejo) 
*/

#include <iostream>
#include "tlistacom.h"

using namespace std;

int
main(void)
{
  TComplejo a(-3, -3), b;
  TListaCom l1;
  TListaPos p1;
  
  for (int i=0; i<10; i++) {
         a = a + double(i);
         l1.InsCabeza(a);
  }

  for (TListaPos p1=l1.Primera(); !p1.EsVacia(); p1=p1.Siguiente())
          cout<<l1.Obtener(p1)<<" ";
  cout<<endl;
  if (l1.Buscar(a)) cout<<"Encontrado a"<<endl;
  if (!l1.Buscar(b)) cout<<"No existe b"<<endl; 
  return 0;
}
