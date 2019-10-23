#include <iostream>

using namespace std;

#include "tvectorcalendario.h"

int
main(void)
{
   TVectorCalendario a(3), b(3);
   TCalendario ca1(1,1,2006, (char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");
   
   b[1] = ca1;
   b[2] = ca2;
   b[3] = ca3;
   
   cout << "a=" << a << endl;
   cout << "b=" << b << endl;

   a = b;
   
   cout << "a=" << a << endl;
   cout << "b=" << b << endl;

   b.Redimensionar(1);

   cout << "a=" << a << endl;
   cout << "b=" << b << endl;
   
   return 0;
}
