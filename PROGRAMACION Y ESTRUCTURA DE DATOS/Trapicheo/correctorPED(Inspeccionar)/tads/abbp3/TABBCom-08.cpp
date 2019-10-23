/* Prueba:
    - ALTURA, NODOS, NODOSHOJA   
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
 
  TComplejo c100(100, 1);
  TComplejo c50(50, 1);
  TComplejo c20(20, 1);
  TComplejo c110(110, 1);
 

  cout <<"Altura: "<<a.Altura()<<endl;
  cout <<"Nodos: "<<a.Nodos()<<endl;
  cout <<"NodosHoja: "<<a.NodosHoja()<<endl;
  cout <<"Raiz: "<<a.Raiz()<<endl;

  a.Insertar(c100);
  cout <<"Altura: "<<a.Altura()<<endl;
  cout <<"Nodos: "<<a.Nodos()<<endl;
  cout <<"NodosHoja: "<<a.NodosHoja()<<endl;
  cout <<"Raiz: "<<a.Raiz()<<endl;

  a.Insertar(c50);
  cout <<"Altura: "<<a.Altura()<<endl;
  cout <<"Nodos: "<<a.Nodos()<<endl;
  cout <<"NodosHoja: "<<a.NodosHoja()<<endl;
  cout <<"Raiz: "<<a.Raiz()<<endl;

  a.Insertar(c20);
  cout <<"Altura: "<<a.Altura()<<endl;
  cout <<"Nodos: "<<a.Nodos()<<endl;
  cout <<"NodosHoja: "<<a.NodosHoja()<<endl;
  cout <<"Raiz: "<<a.Raiz()<<endl;

  a.Insertar(c110);
  cout <<"Altura: "<<a.Altura()<<endl;
  cout <<"Nodos: "<<a.Nodos()<<endl;
  cout <<"NodosHoja: "<<a.NodosHoja()<<endl;
  cout <<"Raiz: "<<a.Raiz()<<endl;

  a.Borrar(c20);
  cout <<"Altura: "<<a.Altura()<<endl;
  cout <<"Nodos: "<<a.Nodos()<<endl;
  cout <<"NodosHoja: "<<a.NodosHoja()<<endl;
  cout <<"Raiz: "<<a.Raiz()<<endl;


  return 1; 

}
