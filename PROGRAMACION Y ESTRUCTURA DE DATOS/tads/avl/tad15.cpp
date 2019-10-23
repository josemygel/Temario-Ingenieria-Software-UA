#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a;
  TCalendario *vectorc;
  
  vectorc=new TCalendario[2000];
  for (int i=0; i<2000; i++)
  {
     vectorc[i].ModFecha(1,1,2000+i);
     a.Insertar(vectorc[i]);
  }

  cout<<"Inorden="<<a.Inorden()<<endl;
  return 0;
}
