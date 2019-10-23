#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   char fi[] = "Fecha incorrecta";
   char fc[] = "Fecha correcta";
      
   TCalendario cal1(12,12,2006, fc);
   TCalendario cal2(05,01,1900, fc);
   
   cal2=cal1++;
   cout<<cal2<<endl;
   cout<<cal1<<endl;
   
   cal2=++cal1;
   cout<<cal2<<endl;
   cout<<cal1<<endl;
 
}
