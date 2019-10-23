#include <cstring>
#include <iostream>
#include <fstream>
#include <cstdlib>
//#include "Matriz.h"

#include <math.h>
#include <chrono>

using namespace std::chrono;
using namespace std;



//c[i][j] = functionParalela(aux,matrices[matriz],i,j)
unsigned long long funcionParalela(unsigned long long** a, unsigned long long**b, unsigned long long i, unsigned long long j,unsigned long long n){
    unsigned long long result = 0;

    for(int k=0;k<n;k++)
        result += a[i][k] * b[k][j];
    
    return result;
}

unsigned long long** multimatrizcuadrada(unsigned long long*** matrices, unsigned long long count, unsigned long long n, bool paralelo = false)
{
    unsigned long long i,j,k,matriz; //(1)
    unsigned long long** c = new unsigned long long*[n]; //(2)
    unsigned long long** aux = new unsigned long long*[n]; //(3)

    for(i=0; i<n; i++) //(4)
    {
        c[i] = new unsigned long long[n]; //(5)
        aux[i] = new unsigned long long[n]; //(6)

        for(j=0; j<n; j++) //(7)
        {
            c[i][j] = 0; //(8)
        }
    }

//C[i][j] = C[i][k] * matriz[k][j]

    for(matriz=0; matriz<count;matriz++) //(9)
    {
        
        for(i=0;i<n;i++) //(10)
            for(j=0;j<n;j++){ //(11)
                aux[i][j] = c[i][j]; //(12)
                c[i][j]=0; //(13)
            }

        for(i=0;i<n;i++) //(14)
            for(j=0;j<n;j++) //(15)
                for(k=0;k<n;k++) //(16)
                {
                    if(matriz==0) //(17)
                    {
                        c[i][j] = matrices[matriz][i][j]; //(18)
                    }/*
                    else if(paralelo)
                    {
                        if(c[i][j] == 0)
                            c[i][j] = funcionParalela(aux,matrices[matriz],i,j,n);
                    }*/
                    else
                    {
                        c[i][j] += aux[i][k] * matrices[matriz][k][j]; //(19)
                    }
                }
    }

    for (int i = 0; i < n; i++)
    {
        delete[] aux[i];
    }

    delete[] aux;

    return c; //(f)
}


int main(int  argc, char* argv[])
{

    cout << "TAMAÑO DE MATRICES\tTIEMPO MEDIO\n";

    unsigned long long count = 3;
    unsigned long long*** matrices;

    for(int n = 100; n <= 2000; n+=100)
    {
        float tiempo = 0;
   
        for(int repet = 1; repet <= 10; repet++)
        {
            //Inicializamos la cantidad de matrices
            matrices = new unsigned long long**[count];
   
            srand(time(NULL));   // should only be called once

            for(unsigned long long matriz=0; matriz<count; matriz++){
                //Inicializamos las matrices una a una a su tamaño necesario
                matrices[matriz] = new unsigned long long*[n];

                for(unsigned long long i=0; i<n; ++i)
                {
                    //Inicializamos la segunda parte de la matriz
                    matrices[matriz][i] = new unsigned long long[n];
                    
                    //Rellenamos matriz
                    for(unsigned long long j=0; j<n; ++j)
                    {
                        matrices[matriz][i][j] =  rand() % 5 + 1;
                    }
                }
            }

        /*
            for(unsigned long long matriz=0; matriz<count; matriz++)
            {
                cout << "\n\nMatriz " << matriz << ":";

                for(unsigned long long i=0; i<n; ++i)
                {
                    cout << "\n";
                    for(unsigned long long j=0; j<n; ++j)
                    {
                        cout << matrices[matriz][i][j] << '\t';
                    }
                }
            }

            cout << endl << endl;*/

            // Using time pounsigned long long and system_clock
            std::chrono::time_point<std::chrono::system_clock> start, end;

            //REALIZAMOS EL PROBLEMA CRONOMETRADAMENTE
            start = std::chrono::system_clock::now();
          //  cout << "\nEl resultado es:";
          //OMITIMOS EL GUARDADO DEL RESULTADO PARA EVITAR MOLESTIAS DE CONSUMO RAM
            unsigned long long** resultado = multimatrizcuadrada(matrices,count,n);
            end = std::chrono::system_clock::now();
        /*
            for(unsigned long long i=0; i<n; i++)
            {
                cout << endl;

                for(unsigned long long j=0; j<n; j++)
                    cout << resultado[i][j] << '\t';
            }
            cout << endl;*/

            //Cogemos el tiempo en segundos
            std::chrono::duration<float> elapsed_seconds = end - start;
            std::time_t end_time = std::chrono::system_clock::to_time_t(end);

            tiempo += elapsed_seconds.count();

            //LIBERAMOS MEMORIA
            for (int i = 0; i < count; i++)   // Paso 1: Borrar columnas
            {
                for(int j = 0; j < n; j++)
                {
                    delete[] matrices[i][j];
                }
                delete[] matrices[i];
            }
            delete[] matrices;

            for(int i =0; i<n; i++)
                delete[] resultado[i];
            delete[] resultado;

        }

        tiempo = tiempo/10;
        string strTime = "";
        
        if(tiempo > 3600) //mayor de 100 minutos
        {
            tiempo = tiempo/3600;
            strTime = "hours";
        }
        else if(tiempo > 120)
        {
            tiempo = tiempo/60;
            strTime = " minutes";
        }
        else if(tiempo < 0.005)
        {
            tiempo = tiempo*1000;
            strTime = " miliseconds";
        }
        else
        {
            tiempo = tiempo;
            strTime = " seconds";
        }
        
        cout << n << "\t\t\t";
        printf("%.3f", tiempo);
        cout << "\t" << strTime << "\n";
    }
    return 0;
}
