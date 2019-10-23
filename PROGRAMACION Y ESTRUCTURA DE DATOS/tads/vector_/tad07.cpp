#include <iostream>

using namespace std;

#include "tcalendario.h"
#include "tvectorcalendario.h"


int
main(void)
{
   TVectorCalendario a(3);
   TCalendario ca1(1,1,2006, (char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");

   a[1]=ca1;
   a[2]=ca2;
   a[3]=ca3;

   cout<<"a="<<a<<endl;

   ca3 = a[1];
   a[3]= ca3;

   cout<<"a="<<a<<endl;

   return 0;

}
