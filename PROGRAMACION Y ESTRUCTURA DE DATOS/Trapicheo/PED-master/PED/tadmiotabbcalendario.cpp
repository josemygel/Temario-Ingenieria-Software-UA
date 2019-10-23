#include <iostream>
#include "tabbcalendario.h"
using namespace std;

int
main(void)
{
/***************************************************/
/***************** RESTA, INORDEN 	           */
/***************************************************/
  TABBCalendario a,b, resta;
  TVectorCalendario vec, vec2, vec3;

  TCalendario c1(1,1,2011,"Fecha40");
  TCalendario c2(2,1,2011,"Fecha41");
  TCalendario c3(3,1,2011,"Fecha42");
  TCalendario c4(4,1,2011,"Fecha43");
  TCalendario c5(5,1,2011,"Fecha44");
  TCalendario c6(6,1,2011,"Fecha45");
  TCalendario c7(7,1,2011,"Fecha46");
  TCalendario c8(8,1,2011,"Fecha50");
  TCalendario c9(9,1,2011,"Fecha60");
  TCalendario c10(10,1,2011,"Fecha61");


  a.Insertar(c8);
  a.Insertar(c9);
  a.Insertar(c10);
  a.Insertar(c1);
  a.Insertar(c2);
  a.Insertar(c3);
  a.Insertar(c7);
  a.Insertar(c5);
  a.Insertar(c4);
  a.Insertar(c6);


  b.Insertar(c7);
  b.Insertar(c9);
  b.Insertar(c10);
  b.Insertar(c1);
  b.Insertar(c2);
  b.Insertar(c3);
  b.Insertar(c5);
  b.Insertar(c4);
  b.Insertar(c6);


  vec = a.Niveles();
  vec2 = b.Niveles();
  a.Borrar(c8);
  vec3 = a.Niveles();
  cout << "Va BIEN si vec 2 == vec 3"<< endl;
  cout <<"Niveles vec: "<< vec << endl;
  cout <<"Niveles vec2: "<< vec2 << endl;
  cout <<"Niveles vec3: "<< vec3 << endl;
  if(vec2==vec3){
	  cout << "Va BIEN"<< endl;
  }



  return 0; 

}

