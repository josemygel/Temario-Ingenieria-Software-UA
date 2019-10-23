/* Prueba:
      =, !=
*/

#include <iostream>
#include "tavlcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
  TAVLCom a,b,c;
  TComplejo c3(3,3),c5(5,5),c9(9,9),c12(12,12),c10(10,10),c1(1,1);
  
  a.Insertar(c10);
  a.Insertar(c12);
  a.Insertar(c9);
  a.Insertar(c5);
  a.Insertar(c3);

  b=a;
  c=b;
  c.Insertar(c1);
  
  if (a!=c)
   cout<<"distintos"<<endl;
  else
   cout<<"iguales"<<endl;

  if (b!=(c=a))
    cout<<"distintos"<<endl;
  else
   cout<<"iguales"<<endl;
  

}
