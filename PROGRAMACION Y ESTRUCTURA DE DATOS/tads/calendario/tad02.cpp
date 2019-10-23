#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   char fi[] = "Fecha incorrecta";
   char fc[] = "Fecha correcta";

   TCalendario cal1(29,2,2006, fi);
   TCalendario cal2(cal1);
   TCalendario cal3(1,12,2006, fc);
 
   cout<<cal1<<endl;
   cout<<cal2<<endl;
   cal2=cal3;
   cout<<cal2<<endl;
   cout<<cal3<<endl;
}
