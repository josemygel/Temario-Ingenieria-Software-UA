/* Prueba:
     metodos de TListaPos
*/
#include <iostream>

using namespace std;

#include "tlistacom.h"

int
main(void)
{
  TComplejo a, b(1), c(2, 3), d(3,4);
  TListaCom l1;
  
  l1.InsCabeza(a);
  
  TListaPos p=l1.Primera();
  TListaPos q;
  
  cout << (p==q) << endl;
  cout<<"l1 = "<<l1<<endl;
  
  p.~TListaPos();
  cout<<"l1 = "<<l1<<endl;
  
  q=q.Siguiente();
  q=q.Anterior();
  
  q=(l1.Ultima()).Siguiente();
  q=q.Siguiente();
  q=q.Anterior();
  
   
  return 0;
}
