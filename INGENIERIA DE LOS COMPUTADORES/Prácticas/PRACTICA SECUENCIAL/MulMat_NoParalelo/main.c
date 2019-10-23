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
long long funcionParalela(long long** a, long long**b, long long i, long long j,long long n){
    long long result = 0;

    for(int k=0;k<n;k++)
        result += a[i][k] * b[k][j];
    
    return result;
}

long long** multimatrizcuadrada(long long*** matrices, long long count, long long n, bool paralelo = false)
{
    long long i,j,k,matriz; //(1)
    long long** c = new long long*[n]; //(2)
    long long** aux = new long long*[n]; //(3)

    for(i=0; i<n; i++) //(4)
    {
        c[i] = new long long[n]; //(5)
        aux[i] = new long long[n]; //(6)

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

    return c; //(f)
}


int main(int  argc, char* argv[])
{

    srand(time(NULL));   // should only be called once
    long long n, count;
    long long*** matrices;

    if(argc < 3)
    {
        cout << "Necesitas insertar al menos 2 parametros (n de NxN y count de cantidad de matrices).\nEj.: ./main 4 2" << endl;
        return 1;
    }

    n = atoi(argv[1]);
    count = atoi(argv[2]);

    //Inicializamos la cantidad de matrices
    matrices = new long long**[count];

    for(long long matriz=0; matriz<count; matriz++){
        //Inicializamos las matrices una a una a su tamaño necesario
        matrices[matriz] = new long long*[n];

        for(long long i=0; i<n; ++i)
        {
            //Inicializamos la segunda parte de la matriz
            matrices[matriz][i] = new long long[n];
            
            //Rellenamos matriz
            for(long long j=0; j<n; ++j)
            {
                matrices[matriz][i][j] =  rand() % 5 + 1;
            }
        }
    }

/*
    for(long long matriz=0; matriz<count; matriz++)
    {
        cout << "\n\nMatriz " << matriz << ":";

        for(long long i=0; i<n; ++i)
        {
            cout << "\n";
            for(long long j=0; j<n; ++j)
            {
                cout << matrices[matriz][i][j] << '\t';
            }
        }
    }

    cout << endl << endl;*/

    // Using time polong long and system_clock
    std::chrono::time_point<std::chrono::system_clock> start, end;

    //REALIZAMOS EL PROBLEMA CRONOMETRADAMENTE
    start = std::chrono::system_clock::now();
    cout << "\nEl resultado es:";
    long long** resultado = multimatrizcuadrada(matrices,count,n);
    end = std::chrono::system_clock::now();
/*
    for(long long i=0; i<n; i++)
    {
        cout << endl;

        for(long long j=0; j<n; j++)
            cout << resultado[i][j] << '\t';
    }
    cout << endl;*/

    /**
     * 
     * LIBERAMOS MEMORIA!
     * 
     */

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

    //Cogemos el tiempo en segundos
    std::chrono::duration<float> elapsed_seconds = end - start;
    std::time_t end_time = std::chrono::system_clock::to_time_t(end);

    string strTime = "";
    float tiempo;

    if(elapsed_seconds.count() > 100*60) //mayor de 100 minutos
    {
       tiempo = elapsed_seconds.count()/60;
       strTime = "hours";
       //" hours";
    }
    else if(elapsed_seconds.count() > 200)
    {
       tiempo = elapsed_seconds.count()/3600;
       strTime = " minutes";
    }
    else if(elapsed_seconds.count() < 0.0002)
    {
       tiempo = elapsed_seconds.count()*1000;
       strTime = " miliseconds\n\n";
    }
    else
    {
       tiempo = elapsed_seconds.count();
       strTime = " seconds";
    }
    cout << "\nResultado obtenido en " << tiempo << strTime << "\n\n";

    delete[] matrices;

    return 0;

}
