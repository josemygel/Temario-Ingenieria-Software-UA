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
   TCalendario cal4;
 
   cout<<cal1<<endl;
   cout<<cal2<<endl;
   cout<<cal1+79<<endl;
   cal4=cal2-10;
   cout<<cal2-10<<endl;
   cout<<cal2<<endl;
   cout<<cal4<<endl;
}
