/************************************************************
** CONSTRUCTOR COPIA, =
*************************************************************/
#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a,b;
  TCalendario cal1(1,1,2011,(char *)"uno");
  TCalendario cal2(2,1,2011,(char *)"dos");
  TCalendario cal3(3,1,2011,(char *)"tres");
  TCalendario cal4(4,1,2011,(char *)"cuatro");
  TCalendario cal5(5,1,2011,(char *)"cinco");

  a.Insertar(cal1);
  a.Insertar(cal2);
  a.Insertar(cal3);
  a.Insertar(cal4);
  TAVLCalendario c(a);
  
  b.Insertar(cal1);
  b.Insertar(cal2);
  b.Insertar(cal3);
  b.Insertar(cal4);

  if ( a==c )
  	cout << "IGUALES" << endl;
  else 
	cout << "DISTINTOS" << endl;

  if ( c==b )
  	cout << "IGUALES" << endl;
  else 
	cout << "DISTINTOS" << endl;

  b = a;
cout << b.Preorden() <<endl;
  a.Insertar(cal5);
  if ( a==b )
  	cout << "IGUALES" << endl;
  else 
	cout << "DISTINTOS" << endl;

  cout << "Altura: " << b.Altura() << endl;
  cout << b.Preorden() <<endl;
 return 0;
}
