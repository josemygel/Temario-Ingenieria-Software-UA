/* Prueba:
    - ASIGNACION , "<<" ; posible problema de CONST 
*/

#include <iostream>
#include "tabbcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
/*******************************************************************/
/********** 	   */
/*******************************************************************/
  
  TABBCom a,b,c;

  TComplejo c1(1, 0);

  b.Insertar(c1);


  cout << (a = b).Inorden() << endl;

  cout << (a = c).Inorden() << endl;


  return 1;
}
