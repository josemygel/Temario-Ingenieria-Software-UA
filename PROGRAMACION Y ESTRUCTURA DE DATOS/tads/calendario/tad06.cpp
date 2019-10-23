#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   char fi[] = "Fecha incorrecta";
   char fc[] = "Fecha correcta";
      
   TCalendario c1(14,12,1966, fc);
   TCalendario c2(19,11,1994, fc);
   TCalendario c3(01,01,1900, fc);
   c2=c1--;
   cout<<c2<<endl;
   cout<<c1<<endl;
   
   c2=--c1;
   cout<<c2<<endl;
   cout<<c1<<endl;
   
   c2=--c3;
   cout<<c2<<endl;
   
}
