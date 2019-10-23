
#include<omp.h>
#include<thread>
#include<iostream>
#include<chrono>
using namespace std;
using namespace std::chrono;


const int NCORES = thread::hardware_concurrency();;

float inline myFor(float* a1, float* a2, int n)
{
  //int hilos = (NCORES>4) ? NCORES/4 : 1;

  float resultado = 0;
  int i;
  //#pragma omp parallel for schedule(runtime) private(tid) 
  //#pragma omp parallel for schedule(runtime) shared(resultado,a1,a2,n) num_threads(4)
/*    #pragma omp parallel
      #pragma omp for*/
   //#pragma omp parallel for
      for (i=0; i < n; i++)
      {
        resultado += a1[i] * a2[i]; //CALCULO DE MAYOR PESO!!
      }
    
  return resultado;
}

float inline Media(float array[], int n)
{
  int i;
  float resultado = 0;
//  #pragma omp parallel for schedule(runtime) shared(resultado,array,n) num_threads(4)

/*
    #pragma omp parallel
      #pragma omp for*/
   //#pragma omp parallel for
    for (i = 0; i < n; i++)
    {
      resultado += array[i]; //CALCULO DE PESO GRANDE
    }
  
  resultado = resultado / n;
  return resultado;
}

void Recta_Regresion_X_sobre_Y(float* x, float* y,int n, float &m, float &t)
{
  float mx,my,dataCov,dataVar;

    //Calculo de medias
//    omp_set_num_threads(NCORES);
    #pragma omp parallel sections
    {
      #pragma omp section
        mx = Media(x,n);
      #pragma omp section
        my = Media(y,n);

      //Calculo de datos para Covarianza y Varianza
      #pragma omp section
        dataCov = myFor(x,y,n);
      #pragma omp section
        dataVar =  myFor(x,x,n);
    }

  //Obtebemos los resultados deseados
  t = mx - my;
  //COVARIANZA/VARIANZA
  m = dataCov - (mx * my)/(dataVar - mx);
}

void Recta_Regresion_Y_sobre_X(float x[], float y[], int n, float &m, float &t)
{
  /*
  X SOBRE Y

  m = Covarianza(x, y, n)/Varianza(x,n);
  t = (Media(x, n) - Media(y, n));
  */
   float mx,my,dataCov,dataVar;

    //Calculo de medias
//    omp_set_num_threads(NCORES);
    mx = Media(x,n);
    my = Media(y,n);

    //Calculo de datos para Covarianza y Varianza
    dataCov = myFor(x,y,n);
    dataVar =  myFor(y,y,n);
    

  //Obtebemos los resultados deseados
  t = my - mx;
  //COVARIANZA/VARIANZA
  m = dataCov - (mx * my)/(dataVar - my);
}

int main()
{
  int n = 0;
  typedef std::chrono::duration<int> seconds_type;
  typedef std::chrono::duration<int,std::milli> milliseconds_type;

//TOPE CON LA RAM PARA 12 GB (llega a 11 GB)
 //n = (1459938304/1.5)*1.3;
 //TOPE CON LA RAM PARA 8 GB (llega a 6.6 GB)
 n = (1459938304/1.5)*0.7;
 //TOPE CON LA RAM PARA 8 GB (llega a 3.4 GB)
 //n = 1459938304*0.2;
 //TOPE CON LA RAM PARA 8 GB (llega a 1.7 GB)
 //n = 1459938304*0.1; 

 //DESPRECIABLE
  //n = 200000;
 float *x = new float[n];
 float *y = new float[n];

 srand(time(NULL));
 for (int i = 0; i < n; i++) {
  x[i] = rand() % 50+1;
  y[i] = rand() % 50+1;
 }


 float Mxy = 0, Txy = 0, Myx = 0, Tyx = 0;
 float Mensxy = 0, Tensxy = 0, Mensyx = 0, Tensyx = 0;

  cout << "INIT"<<endl;

  high_resolution_clock::time_point t1 = high_resolution_clock::now();
  Recta_Regresion_X_sobre_Y(x, y, n, Mxy, Txy);
  high_resolution_clock::time_point t2 = high_resolution_clock::now();
  duration<double> secsparal = duration_cast<duration<double>>(t2 - t1);

  t1 = high_resolution_clock::now();
  Recta_Regresion_Y_sobre_X(x, y, n, Myx, Tyx);
  t2 = high_resolution_clock::now();
  duration<double> secsnormal = duration_cast<duration<double>>(t2 - t1);

 if (Txy < 0)
 {
  cout << "X = " << Mxy << "Y " << Txy << endl;
 }
 else if(Txy == 0)
 {
  cout << "X = " << Mxy << "Y" << endl;
 }
 else
 {
  cout << "X = " << Mxy << "Y + " << Txy << endl;
 }

 if (Tyx < 0)
 {
  cout << "Y = " << Myx << "X " << Tyx << endl;
 }
 else if (Tyx == 0)
 {
  cout << "Y = " << Myx << "X" << endl;
 }
 else
 {
  cout << "Y = " << Myx << "X + " << Tyx << endl;
 }

 float ganancia = secsnormal.count()/secsparal.count()-1;
 float porcentaje = 100+100*ganancia;

 cout << "\n=====================================\n";
 cout << "==============RESULTADO==============\n";
 cout << "=====================================\n";
 cout << "Tiempo normal: " << secsnormal.count() << " seconds."  << endl;
 cout << "Tiempo paralelo: " << secsparal.count() << " seconds."  << endl;
 cout << "Ganancia: x" << ganancia << "  || Porcentaje: " << porcentaje << " %."  << endl;
 cout << "=====================================\n";

 return 0;
}
