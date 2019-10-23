/************************************************************
** BORRAR CON ROTACION II
*************************************************************/
#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main()
{
  TAVLCalendario arb1, arb2;
  TVectorCalendario vec, vec2;

  TCalendario c1(1,1,2011,(char *)"uno");
  TCalendario c2(2,2,2011,(char *)"dos");
  TCalendario c3(3,3,2011,(char *)"tres");
  TCalendario c4(4,4,2011,(char *)"cuatro");
  TCalendario c5(5,5,2011,(char *)"cinco");
  TCalendario c6(6,6,2011,(char *)"seis");

  arb1.Insertar(c6);
  arb1.Insertar(c4);
  arb1.Insertar(c2);
  arb1.Insertar(c1);
  arb1.Insertar(c3);
 cout<<arb1.Preorden()<<endl;
 if (arb1.Borrar(c5))
  	cout<<"Elemento borrado"<<endl;
 else cout<<"Elemento no borrado"<<endl;
 
 if (arb1.Borrar(c6))
  	cout<<"Elemento borrado"<<endl;
  else cout<<"Elemento no borrado"<<endl;
 
 cout<<arb1.Raiz()<<endl;
 cout<<arb1.Preorden()<<endl;
 cout<<arb1.Inorden()<<endl;
 cout<<arb1.Postorden()<<endl;
  
  return 0;
}
