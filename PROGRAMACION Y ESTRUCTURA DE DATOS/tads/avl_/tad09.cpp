/************************************************************
** ESVACIO, BORRAR EN UN ARBOL DE 1 ELEMENTO
*************************************************************/
#include <iostream>
#include "tavlcalendario.h"

using namespace std;


int
main()
{
  TAVLCalendario arb1;
  TVectorCalendario vec;

  TCalendario b1(1,1,2011,(char *)"uno");
 
  if (arb1.EsVacio())
	cout<<"Arbol vacio"<<endl;
  else cout<<"Arbol no vacio"<<endl;
 
  if (arb1.Borrar(b1))
  	cout<<"Elemento borrado"<<endl;
  else cout<<"Elemento no borrado"<<endl;

  arb1.Insertar(b1);
 
  if (arb1.EsVacio())
	cout<<"Arbol vacio"<<endl;
  else cout<<"Arbol no vacio"<<endl;

  if (arb1.Borrar(b1))
  	cout<<"Elemento borrado"<<endl;
  else cout<<"Elemento no borrado"<<endl;
    
  if (arb1.EsVacio())
	cout<<"Arbol vacio"<<endl;
  else cout<<"Arbol no vacio"<<endl;

  return 0; 
}
