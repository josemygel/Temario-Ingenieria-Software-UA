/* Prueba:
    - Buscar()               
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

  TComplejo c1(1, 1);
  TComplejo c2(2, 2);
  TComplejo c3(3, 3);
  TComplejo c4(4, 4);
  TComplejo c5(5, 5);
  TComplejo c6(6, 6);
  TComplejo c7(7, 7);

  a.Insertar(c5);
  a.Insertar(c2);
  a.Insertar(c1);
  a.Insertar(c3);
  a.Insertar(c6);

  bool busc = a.Buscar(c7);
  cout <<"Encontrado = "<<busc<<endl;
  bool ins = a.Insertar(c7);
  cout <<"Insertado = "<<ins<<endl;
  busc = a.Buscar(c7);
  cout <<"Encontrado = "<<busc<<endl;


  bool busc2 = a.Buscar(c1);
  cout <<"Encontrado = "<<busc2<<endl;
  bool ins2 = a.Insertar(c1);
  cout <<"Insertado = "<<ins2<<endl;
  bool bor2 = a.Borrar(c1);
  cout <<"Borrado = "<<bor2<<endl;
  busc2 = a.Buscar(c1);
  cout <<"Encontrado = "<<busc2<<endl;

  return 1; 
}
