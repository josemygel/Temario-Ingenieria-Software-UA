/* Prueba:
     - BORRADOS SENCILLOS: DISTINTAS ROTACIONES
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
Insertar: 30 20 40 10 25 35 45 5 32 42 50
Borrar: 32 40 30
BORRADOS SENCILLOS: DISTINTAS ROTACIONES
*/

  TAVLCom a;
  TComplejo c40(40, 0), c30(30, 0), c50(50, 0),c20(20, 0), c35(35, 0), c45(45, 0),c10(10, 0), c25(25, 0),c32(32, 0), c42(42, 0), c5(5, 0);

  a.Insertar(c30);  a.Insertar(c20);  a.Insertar(c40);  a.Insertar(c10);  a.Insertar(c25);  a.Insertar(c35);  a.Insertar(c45);  a.Insertar(c5);  a.Insertar(c32);  a.Insertar(c42); a.Insertar(c50);
  
  a.Borrar(c32);
  a.Borrar(c40);
  a.Borrar(c30);

  cout << a.Preorden() << endl;
//PREORDEN 25 10 5 20 45 35 42 50

}
