#include <iostream>
#include "tabbcalendario.h"
using namespace std;

int
main(void)
{
/************************************************/
/***************** INORDEN, PREORDEN, POSTORDEN */
/************************************************/


TABBCalendario a;
TVectorCalendario vec1, vec2, vec3;

TCalendario c1(1,1,2011,"Fecha1");
TCalendario c2(2,1,2011,"Fecha2");
TCalendario c3(3,1,2011,"Fecha3");
TCalendario c4(4,1,2011,"Fecha4");
TCalendario c5(5,1,2011,"Fecha5");
TCalendario c6(6,1,2011,"Fecha6");

a.Insertar(c5);
a.Insertar(c2);
a.Insertar(c1);
a.Insertar(c3);
a.Insertar(c6);

vec1 = a.Inorden();
vec2 = a.Preorden();
vec3 = a.Postorden();

cout << "Inorden: " << vec1 << endl;
cout << "Preorden: " << vec2 << endl;
cout << "Postorden: " << vec3 << endl;

return 0; 
}
