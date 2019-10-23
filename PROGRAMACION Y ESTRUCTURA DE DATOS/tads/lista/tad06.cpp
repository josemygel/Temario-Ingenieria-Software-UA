#include <iostream>

using namespace std;

#include "tvectorcalendario.h"

int
main(void)
{
   TVectorCalendario a(3), b(3);
   TCalendario ca1(1,1,2006, (char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");
   a[3] = ca3;
   
   b[1] = ca1;
   b[3] = ca3;
   
   cout << "a=" << a << endl;
   cout << "b=" << b << endl;
   
   if(a.ExisteCal(ca1))
      cout << "Existe" << endl;
   else
      cout << "No existe" << endl;
   
   if(b.ExisteCal(ca1))
      cout << "Existe" << endl;
   else
      cout << "No existe" << endl;
   
   return 0;
}
