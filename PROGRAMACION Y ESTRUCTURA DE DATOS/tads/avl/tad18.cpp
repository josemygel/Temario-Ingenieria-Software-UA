#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a;
  TCalendario c1(1,1,2000,(char *)"fecha"),c2(2,2,2000,(char *)"fecha"),c3(3,3,2000,(char *)"fecha"),c4(4,4,2000,(char *)"fecha"),c5(5,5,2000,(char *)"fecha"),c6(6,6,2000,(char *)"fecha"),c7(7,7,2000,(char *)"fecha"),c8(8,8,2000,(char *)"fecha"),c9(9,9,2000,(char *)"fecha"),c10(10,10,2000,(char *)"fecha");
  TCalendario c11(11,11,2000,(char *)"fecha"),c12(12,12,2000,(char *)"fecha"),c13(13,12,2000,(char *)"fecha"),c14(14,12,2000,(char *)"fecha"),c15(15,12,2000,(char *)"fecha"),c16(16,12,2000,(char *)"fecha");
  TCalendario c17(17,12,2000,(char *)"fecha"),c18(18,12,2000,(char *)"fecha"),c19(19,12,2000,(char *)"fecha"),c20(20,12,2000,(char *)"fecha");

  if (a.Borrar(c1))
	cout<<"Elemento borrado"<<endl;
  else
	cout<<"Elemento no borrado"<<endl;

  
  a.Insertar(c1);
  
  if (a.Borrar(c2))
	cout<<"Elemento borrado"<<endl;
  else
	cout<<"Elemento no borrado"<<endl;
	
   if (a.Borrar(c1))
	cout<<"Elemento borrado"<<endl;
  else
	cout<<"Elemento no borrado"<<endl;
	
  cout <<"preorden="<< a.Preorden()<< endl;
  return 0;
}
