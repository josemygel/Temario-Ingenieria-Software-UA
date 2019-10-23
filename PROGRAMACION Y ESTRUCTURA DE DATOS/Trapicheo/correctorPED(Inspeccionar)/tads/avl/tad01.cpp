/* Prueba:
     - inorden , preorden , postorden, niveles, Altura, Nodoshoja
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
  TComplejo c4(4,4),c6(6,6),c8(8,8),c9(9,9),c10(10,10),c11(11,11),c12(12,12),c14(14,14),c16(16,16);
  TVectorCom l1,l2,l3;
  
  a.Insertar(c8);
  a.Insertar(c12);
  a.Insertar(c4);
  a.Insertar(c9);
  a.Insertar(c14);
  a.Insertar(c16);
  a.Insertar(c11);
  a.Insertar(c6);
  a.Insertar(c10);

  l1=a.Inorden();
  l2=a.Postorden();
  l3=a.Preorden();

  cout<<"inorden="<<l1<<endl;
  cout<<"postorden="<<l2<<endl;
  cout<<"preorden="<<l3<<endl;
  cout<<"altura="<<a.Altura()<<endl;
  cout<<"nodos hoja="<<a.NodosHoja()<<endl;
  
  return 1;
}
