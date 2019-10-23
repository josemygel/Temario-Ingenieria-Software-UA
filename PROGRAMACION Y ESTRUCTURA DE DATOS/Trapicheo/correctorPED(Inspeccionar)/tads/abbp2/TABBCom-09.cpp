/* Prueba:
    - INSERCION Y BORRADO DE DIVERSOS CASOS 
*/

#include <iostream>
#include "tabbcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
  
  TABBCom a;
  TVectorCom v;

  TComplejo c20(20, 0);
  TComplejo c10(10, 0);
  TComplejo c30(30, 0);
  TComplejo c40(40, 0);
  TComplejo c5(5, 0);
  TComplejo c15(15, 0);
  TComplejo c50(50, 0);
  TComplejo c22(22, 0);
  TComplejo c25(25, 0);
  TComplejo c24(24, 0);
  TComplejo c26(26, 0);
  TComplejo c3(3, 0);
  TComplejo c35(35, 0);
  TComplejo c38(38, 0);
  TComplejo c39(39, 0);
  TComplejo c37(37, 0);


/* Insertar los siguientes elementos: 20, 10, 30, 40, 5, 15, 50, 22, 25, 24, 26, 3, 35, 38, 39, 37  */
  
  a.Insertar(c20);
  a.Insertar(c10);
  a.Insertar(c30);
  a.Insertar(c40);
  a.Insertar(c5);
  a.Insertar(c15);
  a.Insertar(c50);
  a.Insertar(c22);
  a.Insertar(c25);
  a.Insertar(c24);
  a.Insertar(c26);
  a.Insertar(c3);
  a.Insertar(c35);
  a.Insertar(c38);
  a.Insertar(c39);
  a.Insertar(c37);

/* Borrado sobre el árbol anterior de:  10, 22, 39 y 40 */

  a.Borrar(c10);
  a.Borrar(c22);
  a.Borrar(c39);
  a.Borrar(c40);


  v = a.Niveles(); 

  cout<<"Niveles="<< v <<endl;


  return 1;
}


