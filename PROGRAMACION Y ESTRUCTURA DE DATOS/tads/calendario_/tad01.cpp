#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   TCalendario c1(14,12,1966, (char*) "");
   TCalendario c2(14,12,1966, (char*) "");
   
   if (c1==c2) cout<<"IGUALES"<<endl;
   if (c1>c2) cout<<"ERROR1"<<endl;
   if (c1<c2) cout<<"ERROR2"<<endl;
  
   c2.ModMensaje(NULL);
   if (c1==c2) cout<<"ERROR1"<<endl;
   if (c1>c2) cout<<"MAYOR"<<endl;
   if (c1<c2) cout<<"ERROR2"<<endl;
         
}
