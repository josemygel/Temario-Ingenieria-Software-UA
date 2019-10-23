/* Prueba:
     - BORRADO COMPLEJO: 2 ROTACIONES CON EL BORRADO DE 1 ELEMENTO
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
Insertar: 60 30 90 10 50 85 95 40 82 94 96 97
Borrar: 10
BORRADO COMPLEJO: 2 ROTACIONES CON EL BORRADO DE 1 ELEMENTO
*/

  TAVLCom a;
  TComplejo c60(60, 0), c30(30, 0), c90(90, 0),c10(10, 0), c50(50, 0), c85(85, 0),c95(95, 0), c40(40, 0), c82(82, 0), c94(94, 0), c96(96, 0), c97(97, 0);

  a.Insertar(c60);  a.Insertar(c30);  a.Insertar(c90);  a.Insertar(c10);  a.Insertar(c50);  a.Insertar(c85);  a.Insertar(c95);  a.Insertar(c40);  a.Insertar(c82);  a.Insertar(c94); 
  a.Insertar(c96); a.Insertar(c97);
  
  a.Borrar(c10);

  cout << a.Preorden() << endl;

//PREORDEN 90 60 40 30 50 85 82 95 94 96 97
}
