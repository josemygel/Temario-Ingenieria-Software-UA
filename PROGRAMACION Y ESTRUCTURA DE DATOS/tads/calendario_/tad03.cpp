#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{

   TCalendario c1;

   if (c1.ModFecha(29,02,2006))  
	   cout<<c1<<endl;
   else
	   cout<<"ModFecha NO EJECUTA"<<endl;

   if (c1.ModFecha(1,1,1900))
	   cout<<c1<<endl;
   else
	   cout<<"ModFecha NO EJECUTA"<<endl;

   if (c1.ModFecha(31,12,1899))
	   cout<<c1<<endl;
   else
	   cout<<"ModFecha NO EJECUTA"<<endl;

   if (c1.ModFecha(40,10,1900))
	   cout<<c1<<endl;
   else
	   cout<<"ModFecha NO EJECUTA"<<endl;
   
}
