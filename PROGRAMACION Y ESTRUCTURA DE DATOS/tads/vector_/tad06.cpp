#include <iostream>

using namespace std;

#include "tcalendario.h"
#include "tvectorcalendario.h"


int
main(void)
{
   TVectorCalendario a(6);
   TCalendario ca1(1,1,2006, (char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");
   a[1]=ca1;
   a[2]=ca2;
   a[3]=ca3;
   a[4]=ca1;
   a[5]=ca2;
   a[6]=ca3;
   a.Redimensionar(0);
   cout<<a<<endl;
   a.Redimensionar(10);
   cout<<a<<endl;
   a.Redimensionar(2);
   cout<<a<<endl;
   return 0;
}
