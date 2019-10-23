/* Prueba:
     - inorden , preorden , postorden, Altura, Nodoshoja
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
  TComplejo c1(1,1),c2(2,2),c3(3,3),c4(4,4),c5(5,5),c6(6,6),c7(7,7),c8(8,8),c9(9,9),c10(10,10);
  TComplejo c11(11,11),c12(12,12),c13(13,13),c14(14,14),c15(15,15),c16(16,16);
  TComplejo c17(17,17),c18(18,18),c19(19,19);
  TVectorCom l1,l2,l3;
  
  a.Insertar(c8);
  a.Insertar(c12);
  a.Insertar(c3);
  a.Insertar(c4);
  a.Insertar(c9);
  a.Insertar(c14);
  a.Insertar(c16);
  a.Insertar(c11);
  a.Insertar(c7);
  a.Insertar(c6);
  a.Insertar(c15);
  a.Insertar(c1);
  a.Insertar(c13);
  a.Insertar(c10);
  a.Insertar(c2);
  a.Insertar(c5);
  a.Insertar(c17);
  a.Insertar(c18);
  a.Insertar(c19);

  l1=a.Inorden();
  l2=a.Postorden();
  l3=a.Preorden();

  cout<<"inorden="<<l1<<endl;
  cout<<"postorden="<<l2<<endl;
  cout<<"preorden="<<l3<<endl;
  cout<<"altura="<<a.Altura()<<endl;
  cout<<"nodos hoja="<<a.NodosHoja()<<endl;
}
