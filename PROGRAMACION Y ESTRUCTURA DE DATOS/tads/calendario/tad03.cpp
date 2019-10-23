#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   char fi[] = "Fecha incorrecta";
   char fc[] = "Fecha correcta";
      
   TCalendario cal1(12,12,2006, fc);
   TCalendario cal2(cal1);
 
   cout<<cal1<<endl;
   cout<<cal2<<endl;
   cal1.~TCalendario();
   cout<<cal1<<endl;
   cout<<cal2<<endl;
}
