/* Prueba:
    - SOBRECARGA OPERADOR SALIDA
	(El operador SALIDA muestra el recorrido por Niveles del ABB, con el formato pedido en el Cuadernillo1 para la clase TVectorCom)
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

  TComplejo c20(20, 20);
  TComplejo c10(10, 10);
  TComplejo c30(30, 30);
  TComplejo c40(40, 40);
  TComplejo c5(5, 5);
  TComplejo c15(15, 15);
  TComplejo c50(50, 50);
  TComplejo c22(22, 22);
  TComplejo c25(25, 25);
  TComplejo c24(24, 24);
  TComplejo c26(26, 26);
  TComplejo c3(3, 3);
  TComplejo c35(35, 35);
  TComplejo c38(38, 38);
  TComplejo c39(39, 39);
  TComplejo c37(37, 37);

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


  cout<<"SALIDA de ABB por Niveles="<< a <<endl; 

  return 1;

}


