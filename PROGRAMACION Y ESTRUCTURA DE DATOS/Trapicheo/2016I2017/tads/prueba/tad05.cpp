/* Prueba:
     ==, !=
*/

#include <iostream>
#include "tavlcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
  TAVLCom a,b;
  TComplejo c4(4,4),c8(8,8),c9(9,9),c10(10,10),c11(11,11),c12(12,12),c14(14,14);
  
  a.Insertar(c8);
  a.Insertar(c12);
  a.Insertar(c4);
  a.Insertar(c9);
  a.Insertar(c14);
  a.Insertar(c10);

  b.Insertar(c8);
  b.Insertar(c12);
  b.Insertar(c4);
  b.Insertar(c9);
  b.Insertar(c14);
  b.Insertar(c11);
 
  if (a==b)
    cout<<"iguales"<<endl;
  else
    cout<<"diferentes"<<endl;

  if (a!=b)
    cout<<"diferentes"<<endl;
  else
    cout<<"iguales"<<endl;

}
