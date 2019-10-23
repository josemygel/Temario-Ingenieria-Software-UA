/* Prueba:
     - BORRADO COMPLEJO: 2 ROTACIONES CON EL BORRADO DE 1 ELEMENTO
     - POSTORDEN
*/

#include <iostream>
#include "tavlcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)



{
/*
Insertar: 40 30 50 20 35 45 55 10 25 32 42 5
Borrar: 55
BORRADO COMPLEJO: 2 ROTACIONES CON EL BORRADO DE 1 ELEMENTO
*/

  TAVLCom a;
  TComplejo c40(40, 0), c30(30, 0), c50(50, 0),c20(20, 0), c35(35, 0), c45(45, 0),c55(55, 0), c10(10, 0), c25(25, 0),c32(32, 0), c42(42, 0), c5(5, 0);

  a.Insertar(c40);  a.Insertar(c30);  a.Insertar(c50);  a.Insertar(c20);  a.Insertar(c35);  a.Insertar(c45);  a.Insertar(c55);  a.Insertar(c10);  a.Insertar(c25);  a.Insertar(c32);
  a.Insertar(c42);  a.Insertar(c5);  
  
  a.Borrar(c55);

  cout << a.Postorden() << endl;
//POSTORDEN 5 10 25 20 32 35 42 50 45 40 30

}
