#include <iostream>

using namespace std;

#include "tvectorcalendario.h"

int
main(void)
{
   TVectorCalendario a(3), b(3), c;
   TCalendario ca1(1,1,2006,(char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");
   
   a[3] = ca3;
   
   b[1] = ca1;
   b[3] = ca3;
   
   cout << "a=" << a << endl;
   cout << a.Ocupadas() << endl;
   cout << "b=" << b << endl;
   cout << b.Ocupadas() << endl;
   cout << "c=" << c << endl;
   cout << c.Ocupadas() << endl;

   return 0;
}
