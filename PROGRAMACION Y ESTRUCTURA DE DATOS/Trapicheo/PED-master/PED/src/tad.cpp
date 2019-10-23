#include <iostream>

using namespace std;

#include "tcalendario.h"
/*#include "tvectorcalendario.h"
#include "tlistacalendario.h"*/


int main(){
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
/*
{
   TVectorCalendario a(3), b(3), c;
   TCalendario ca1(1,1,2006, (char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");
   a[1] = ca1;
   a[2] = ca2;
   a[3] = ca3;
   
   b[1] = ca1;
   b[2] = ca2;
   b[3] = ca3;
   
   cout << "a=" << a << endl;
   a = b;
   
   cout << "a=" << a << endl;
   cout << "b=" << b << endl;
   
   a = c;
   
   cout << "a=" << a << endl;
   cout << "b=" << b << endl;
   cout << "c=" << c << endl;
}
{
   TListaCalendario l1;
   TCalendario c1(14, 12, 1966, (char*) "Fecha Correcta");
   TCalendario c2(1, 1, 1954, (char*) "Fecha Correcta");
   
   cout << l1 << endl;
   l1.Insertar(c1);
   cout << l1 << endl;
   l1.Insertar(c2);
   cout << l1 << endl;

}*/
return 0;

}
