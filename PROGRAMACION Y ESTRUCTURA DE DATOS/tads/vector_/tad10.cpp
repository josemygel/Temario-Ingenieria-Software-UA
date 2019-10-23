#include <iostream>

using namespace std;

#include "tcalendario.h"
#include "tvectorcalendario.h"


int
main(void)
{
   TVectorCalendario a(3);
   TCalendario ca1(1,1,2006, (char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");
   TCalendario ca1b(1,1,2006,NULL), ca2b(1,2,2006, (char*) ""), ca3b(1,3,2006, (char*) " tres");

   a[1]=ca1;
   a[2]=ca2;
   a[3]=ca3;
   
   if (a.ExisteCal(ca1b))
      cout<<"Existe"<<endl;
   else
      cout<<"No existe"<<endl;
   
   if (a.ExisteCal(ca2b))
      cout<<"Existe"<<endl;
   else
      cout<<"No existe"<<endl;

   if (a.ExisteCal(ca3b))
      cout<<"Existe"<<endl;
   else
      cout<<"No existe"<<endl;
   
   return 0;
}
