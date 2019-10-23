/* Prueba:
     - INSERCION y preorden
*/

#include <iostream>
#include "tavlcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
  TAVLCom a;
  TComplejo c4(4,4),c6(6,6),c1(1,1),c2(2,2),c3(3,3),c5(5,5),c7(7,7);
  TVectorCom l1,l2,l3;
  
  a.Insertar(c4);
  a.Insertar(c5);
  a.Insertar(c7);
  a.Insertar(c2);
  a.Insertar(c1);
  a.Insertar(c3);
  a.Insertar(c6);


  cout<<"preorden="<<a.Preorden()<<endl;

  
  return 1;
}
