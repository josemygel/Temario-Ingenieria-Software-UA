#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{

   TCalendario c1(14,12,1966, (char*) "Fecha correcta");
   TCalendario c2(02,01,1900, (char*) "Fecha correcta");
   c1=c2--;
   cout<<c1<<endl;
   cout<<c2<<endl;
   
   c1=--c2;
   cout<<c1<<endl;
   cout<<c2<<endl;
   
}
