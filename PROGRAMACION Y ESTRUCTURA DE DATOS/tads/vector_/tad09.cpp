#include <iostream>

using namespace std;

#include "tcalendario.h"
#include "tvectorcalendario.h"


int
main(void)
{

   TVectorCalendario a(3);
   TCalendario ca1(1,1,2006, (char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");
   cout<<a.Tamano()<<endl;
   a[1]=ca1;
   cout<<a.Ocupadas()<<endl;
   a[2]=ca1;
   cout<<a.Ocupadas()<<endl;
   a[3]=ca1;
   cout<<a.Ocupadas()<<endl;

   return 0;

}
