/**
    Paquete de pruebas unitarias para las clases:

        TCalendario
        TVectorCalendario
        TListaCalendario

    Author: Jose Vicente Lozano
            Cristina Rivera

    Fecha:  18 / 03 / 2015

    Universidad de Alicante
    Programación y estructuras de datos
*/


#include <iostream>
#include "tvectorcalendario.h"
#include "tlistacalendario.h"

using namespace std;

int main()
{
   cout << "***********************" << endl;
    cout << "**    TCalendario    **" << endl;
    cout << "***********************" << endl;
    cout << endl << endl;

    char fe[] = "Mensaje del calendario2";

//Construccion de un calendario sin parametros
    TCalendario calendario1;
    cout << "Constructor por defecto"<<endl;
    cout << "-----------------------"<<endl;
    cout << "Calendario 1: se espera: (01/01/1900 \"\") " <<endl;
    cout << "               Obtenido: ("<<calendario1 << ")"<<endl << endl;

//Nuevo calendario con parametros
    cout << "Constructor con parametros"<<endl;
    cout << "--------------------------"<<endl;
    TCalendario calendario2(12, 4, 2015, fe);
    cout << "Calendario 2: se espera: (12/04/2015 \"Mensaje del calendario2\")" << endl;
    cout << "               Obtenido: ("<<calendario2 <<")"<< endl << endl;;
//Constructor de copia
    TCalendario calendario3(calendario2);
    cout << "Constructor de copia"<<endl;
    cout << "--------------------"<<endl;
    cout << "Calendario 3: se espera: (12/04/2015 \"Mensaje del calendario2\")" << endl;
    cout << "               Obtenido: ("<<calendario3 <<")"<< endl<<endl;

    cout << "Pruebas de operadores"<<endl;
    cout << "---------------------"<<endl;

//Se verifica el operator de igualdad
//    cout << calendario1+10 <<endl;

    if (calendario1 == calendario2)
    { cout << "El operador de igualdad == no funciona correctamente" << endl; }
    else { if (calendario2 == calendario3)
            { cout << "==   --------------------------- ok" << endl; }
            else
            { cout << "El operador de igualdad == no funciona correctamente" << endl; };
         };

//Probando el operador de "distinto"
    if (calendario1 != calendario2)
    {if (calendario2 != calendario3)
     {  cout << "El operador de desigualdad != no funciona correctamente" << endl;}
     else
     {  cout << "!=   --------------------------- ok" << endl; };}
    else
    { cout << "El operador de desigualdad != no funciona correctamente" << endl; };


//Es vacio
    if (calendario1.EsVacio())
    {if (calendario2.EsVacio())
     {cout << "La funcion esVacio no funciona bien" << endl;}
     else
     {cout << "esVacio ------------------------ ok" << endl;}
    }
    else
    {cout << "La funcion esVacio no funciona bien" << endl;}

//operador <
    if (calendario1 < calendario2)
    {if (calendario2 < calendario1)
     {cout << "El operador < menor que funciona mal" << endl;}
      else
     {cout << "<  ----------------------------- ok" << endl; }
    }
    else
    {cout << "El operador < (menor que) funciona mal" << endl; }


//Operador >
    if (calendario2 > calendario1)
    {
        if (calendario1 > calendario2)
        { cout << "El operador > mayor que funciona mal" << endl; }
        else
        { cout << ">  ----------------------------- ok" << endl;  }
    }
    else
    {cout << "El operador > (mayor que) funciona mal" << endl;}

//Operador +

//Operador -

//modFecha
calendario1.ModFecha(15, 10, 2000);

    if (calendario1.Dia() == 15 && calendario1.Mes() == 10 && calendario1.Anyo() == 2000)
    {
        calendario1.ModFecha(29, 2, 2015); //Esta fecha no es posible, no es bisiesto

        if (calendario1.Dia() == 29 && calendario1.Mes() == 2 && calendario1.Anyo() == 2015)
        {
            cout << "No se verifica correctamente la fecha -> " << calendario1 << " es imposible"<< endl;
        }
        else
        {
            calendario1.ModFecha(29, 2, 2016); //Esta si puede ser

            if (calendario1.Dia() == 29 && calendario1.Mes() == 2 && calendario1.Anyo() == 2016)
            {
                cout << "modFecha ----------------------- ok" << endl;
            }
            else
            {
                cout << "el Calendario ha rechazado una fecha bisiesta que deberia darse por correcta 29/02/2016" << endl;
            }
        }
    }
    else
    {
        cout << "ModFecha no modifica la fecha correctamente" << endl;
    };


//Probamos el Calendario++
    calendario1.ModFecha(28, 2, 2016); //Esta si puede ser
    calendario1++;

    if (calendario1.Dia() == 29 && calendario1.Mes() == 2 && calendario1.Anyo() == 2016)
    {
        cout << "Operador ++ Prueba1 ------------ ok"<< endl;;
    }
    else
    {
        cout << "Operador ++ Prueba1 ------------ Fallo"<< endl;;
    }

    calendario1.ModFecha(28, 2, 2015); //Esta si puede ser
    calendario1++;

    if (calendario1.Dia() == 1 && calendario1.Mes() == 3 && calendario1.Anyo() == 2015)
    {
        cout << "Operador ++ Prueba2 ------------ ok"<< endl;;
    }
    else
    {
        cout << "Operador ++ Prueba2 ------------ Fallo"<< endl;;
    }

    calendario1.ModFecha(31, 12, 2016); //Esta si puede ser
    calendario1++;

    if (calendario1.Dia() == 1 && calendario1.Mes() == 1 && calendario1.Anyo() == 2017)
    {
        cout << "Operador ++ Prueba3 ------------ ok" << endl;;
    }
    else
    {
        cout << "Operador ++ Prueba3 ------------ Fallo"<< endl;;
    }

    calendario1.ModFecha(15, 11, 2010); //Esta si puede ser
    calendario1++;

    if (calendario1.Dia() == 16 && calendario1.Mes() == 11 && calendario1.Anyo() == 2010)
    {
        cout << "Operador ++ Prueba4 ------------ ok"<< endl;;
    }
    else
    {
        cout << "Operador ++ Prueba4 ------------ Fallo Esperaba (16/11/2010) Obtengo "<< calendario1<< endl;;
    }

    //Pruebas de Calendario + Int
    calendario1.ModFecha(1, 1, 2015); //Año normal
    calendario2.ModFecha(1, 1, 2016); //Año bisiesto

    calendario1 = calendario1+(365);  //Resultado correcto, justo un año despues 01/01/2016
    calendario2 = calendario2+(365);  //Resultado correcto, un año despues menos un dia 12/12/2016

    if (calendario1.Dia() == 1 && calendario1.Mes() == 1 && calendario1.Anyo() == 2016)
        cout << "Operador +(int) Prueba1 ---------ok"<< endl;
    else
        cout << "Operador +(int) Prueba1 ---------Fallo Esperaba (1/1/2016) Obtengo "<< calendario1<< endl;

    if (calendario2.Dia() == 31 && calendario2.Mes() == 12 && calendario2.Anyo() == 2016)
        cout << "Operador +(int) Prueba2 ---------ok"<< endl;
    else
        cout << "Operador +(int) Prueba2 ---------Fallo Esperaba (31/12/2016) Obtengo "<< calendario2<< endl;


//************************************************************************
//************************************************************************
    cout << "\nPruebas de operadores de asignacion (a = b + int)"<<endl;

    calendario1.ModFecha(1, 1, 2015);
    calendario2.ModFecha(2, 2, 2016);
    calendario2 = calendario1+3;

    if (calendario1.Dia() !=1 || calendario1.Mes()!=1 || calendario1.Anyo()!=2015)
    {
        cout << "ERROR! No se ha respetado el valor de b"<<endl;
    }
    else
    {
        cout << "prueba 1 ----> OK" <<endl;
    }

    if (calendario2.Dia()!=4 || calendario2.Mes()!=1 || calendario2.Anyo()!=2015)
    {
        cout << "ERROR! El valor de \"a\" no se ha asignado correctamente"<<endl;
    }
    else
    {
        cout << "prueba 2 ----> OK "<<endl;
    }

//************************************************************************
//************************************************************************
    cout << "\nPruebas de operacion a=b=c"<<endl;

    (calendario3=calendario2)=calendario1;

    if (calendario2.Dia()!=4 || calendario2.Mes()!=1 || calendario2.Anyo()!=2015)
    {
        cout << "ERROR! El valor de b ha cambiado y no deberia"<<endl;
    }
    else
    {
        cout << "prueba 1 ----> OK" <<endl;
    }

    if (calendario3.Dia() !=1 || calendario3.Mes()!=1 || calendario3.Anyo()!=2015)
    {
        cout << "ERROR! a debería haber tomado el valor de c y no ha sido asi"<<endl;
    }
    else
    {
        cout << "prueba 2 ----> OK" <<endl;
    }


//*************************************************************************************************************
//*************************************************************************************************************
//*                                     PRUEBAS  PARA TVECTORCALENDARIO
//*************************************************************************************************************
//*************************************************************************************************************

    cout << endl << endl;
    cout << "***********************" << endl;
    cout << "** TVectorCalendario **" << endl;
    cout << "***********************" << endl;
    cout << endl << endl;


   TVectorCalendario a(3), b(3), c;

   TCalendario ca1(1,1,2006, (char*) "uno"), ca2(1,2,2006, (char*) "dos"), ca3(1,3,2006, (char*) "tres");

   a[1] = ca1;
   a[2] = ca2;
   a[3] = ca3;

    cout << "Prueba 1" << endl;
    cout << "--------" << endl;
    cout << "[01/02/2006 \"dos\", 01/03/2006 \"tres\"]    <--- Cadena esperada" << endl;
    a.MostrarMensajes(10, 1, 2006);
    cout << "    <--- Cadena obtenida" << endl;


   b[1] = ca1;
   b[2] = ca2;
   b[3] = ca3;


    cout <<endl<< "Prueba 2" << endl;
    cout << "--------" << endl;
    cout << "[]    <--- Cadena esperada" << endl;
    a.MostrarMensajes(1, 1, 1899);
    cout << "    <--- Cadena obtenida" << endl;

    cout <<endl<< "Prueba 3 - Ocupadas()" << endl;
    cout << "--------" << endl;
    TVectorCalendario d(10);
    d[2] = ca1;
    d[4] = ca2;
    d[6] = ca3;
    if (d.Ocupadas() == 3)
    {
            cout << "Prueba ------- ok" << endl;
    }
    else
    {
            cout << "ERROR: esperaba (3) y obtengo (" << d.Ocupadas() <<")"  << endl;
    }


    cout <<endl<< "Prueba 3 - Existecal()" << endl;
    cout       << "----------------------" << endl;
    ca1 = ca2;
    TVectorCalendario e(10);
    e[2] = ca2;
    e[4] = ca2;
    e[6] = ca3;

    if (e.ExisteCal(ca1))
    {
        cout << "Prueba --- OK" << endl;
    }
    else
    {
        cout << "ERROR: Existecal no funciona bien" << endl;
    }

    TCalendario ca4(11,11,1979, (char*) "Mi cumple");
    if (!e.ExisteCal(ca4))
    {
        cout << "Prueba --- OK" << endl;
    }
    else
    {
        cout << "ERROR: Existecal no funciona bien" << endl;
    }

    cout <<endl<< "Prueba 4 - operator ==" << endl;
    cout       << "----------------------" << endl;

   //Creo dos vectores exactamente iguales
   TVectorCalendario p4_1(3); p4_1[1]=ca1;p4_1[2]=ca2;p4_1[3]=ca3;
   TVectorCalendario p4_2(3); p4_2[1]=ca1;p4_2[2]=ca2;p4_2[3]=ca3;

   if (p4_1==p4_2)
   { cout << "Prueba 1 --- ok" << endl;}
    else
   { cout << "ERROR: Prueba 1: Se han comparado dos vectores iguales y he obtenido FALSE"<<endl;}

   //Modifico el primer vector
   p4_1[2]=ca3;
   if (p4_1==p4_2)
   { cout << "ERROR: Prueba 2: Se han comparado dos vectores diferentes y he obtenido TRUE"<<endl;}
    else
   { cout << "Prueba 2 --- ok" << endl;}

    cout <<endl<< "Prueba 5 - operator !=" << endl;
    cout       << "----------------------" << endl;

   TVectorCalendario p5_1(3); p5_1[1]=ca1;p5_1[2]=ca2;p5_1[3]=ca3;
   TVectorCalendario p5_2(3); p5_2[1]=ca1;p5_2[2]=ca2;p5_2[3]=ca3;

   if (p5_1!=p5_2)
   { cout << "ERROR: Prueba 1: Se han comparado dos vectores IGUALES y he obtenido TRUE"<<endl;}
    else
   { cout << "Prueba 1 --- ok" << endl;}

   //Modifico el primer vector
   p5_1[2]=ca3;
   if (p5_1!=p5_2)
   { cout << "Prueba 2 --- ok" << endl;}
    else
   { cout << "ERROR: Prueba 2: Se han comparado dos vectores DIFERENTES y he obtenido FALSE"<<endl;}



    cout <<endl<< "Prueba 6 - Redimensionar()" << endl;
    cout       << "----------------------" << endl;

   TVectorCalendario f(10) ;
   f[1]  = ca4;
   f[2]  = ca4;
   f[3]  = ca4;
   f[4]  = ca4;
   f[5]  = ca4;
   f[6]  = ca4;
   f[7]  = ca4;
   f[8]  = ca4;
   f[9]  = ca4;
   f[10] = ca4;
   f.Redimensionar(5);


   if (f.Tamano() == 5 && f[5].Dia() == 11 && f[6].Dia() == 1)
   {
       cout << "Redimension a valor menor -----> OK"<< endl;
   }
   else
   {
       cout << "ERROR: Reduciendo el tamaño del vector"<< endl;
   }

   f.Redimensionar(15);
    f[13] = ca4;
   if (f.Tamano() == 15 && f[13].Dia() == 11 && f[15].Dia() == 1)
   {
       cout << "Redimension a valor mayor -----> OK"<< endl;
   }
   else
   {
       cout << "ERROR: Aumentando el tamaño del vector"<< endl;
   }

//**************************************************************************************************************
//**************************************************************************************************************
//*                                     PRUEBAS  PARA TLISTACALENDARIO                                         *
//**************************************************************************************************************
//**************************************************************************************************************

    cout << endl << endl;
    cout << "***********************" << endl;
    cout << "**  TListaCalendario **" << endl;
    cout << "***********************" << endl;
    cout << endl << endl;

    cout << "Constructor por defecto" << endl;
    cout << "-----------------------" << endl;

    TListaCalendario lis;

    if (lis.Longitud()==0){cout << "Prueba 1 --- ok" << endl;}
    else{cout << "Error en el constructor por defecto: " << lis.Longitud() << endl;}


    cout <<endl<< "TListaCalendario.EsVacia()" << endl;
    cout << "------------------------" << endl;
    if (lis.EsVacia()){cout << "Prueba 1 --- ok"<<endl;}
    else {cout << "La funcion TListaCalendario.EsVacia() no funciona correctamente"<<endl;}


    cout <<endl<< "TListaCalendario.Insertar()" << endl;
    cout << "------------------------" << endl;
    calendario1.ModFecha(4, 6, 2018);
    calendario2.ModFecha(1, 6, 2018);
    calendario3.ModFecha(15, 6, 2018);
    calendario1.ModMensaje((char*)"Segundo");
    calendario2.ModMensaje((char*)"Primero");
    calendario3.ModMensaje((char*)"Tercero");

    if (!lis.Insertar(calendario1) || !lis.Insertar(calendario2) || !lis.Insertar(calendario3))
    {cout << "Una o varias inserciones retornan FALSE y deberian ser TRUE"<<endl;}
    else
    {cout << "prueba 1 --- ok" << endl;}

    if (lis.Insertar(calendario1))
    {cout << "Se ha tratado de insertar un elemento repetido y obtengo TRUE deberia ser FALSE"<<endl;}
    else
    {cout << "Prueba 2 --- ok"<<endl;}

    if (lis.EsVacia()){cout << "Error, no se han insertado calendarios" << endl;}
    else{cout << "Prueba 3 --- ok" << endl;}

    if (lis.Longitud()!=3){cout << "Error, se esperaban 3 elementos en la lista, se han insertado "<< lis.Longitud()<<endl;}
    else {cout << "Prueba 4 --- ok" << endl;}

    TCalendario p1(1, 1, 2000, (char*)"p1");
    TCalendario p2(2, 1, 2001, (char*)"p2");
    TCalendario p3(3, 1, 2002, (char*)"p3");
    TCalendario p4(4, 1, 2003, (char*)"p4");
    TCalendario p5(5, 1, 2004, (char*)"p5");
    TCalendario p6(5, 1, 2005, (char*)"p6");
    TCalendario p7;

    lis.~TListaCalendario();
    if (!lis.EsVacia()){cout << "ERROR: Prueba5: La llamada al destructor no ha eliminado los calendarios de la lista"<< endl;}
    else {cout << "Prueba 5 --- ok"<<endl;}


    lis.Insertar(p3);
    if (lis.Obtener(lis.Primera()) == p3)
    {
        lis.Insertar(p1);
        if (lis.Obtener(lis.Primera())==p1)
        {
            cout << "Prueba 6 --- ok"<<endl;
        }
        else
        {
            cout << "ERROR: Prueba 6: Los calendarios no se han ordenado correctamente"<<endl;
        }
    }
    else
    {
        cout << "ERROR: Prueba 6: No se ha insertado el calendario"<<endl;
    }




    cout <<endl<< "buscar(c)" << endl;
    cout <<       "---------" << endl;

    lis.~TListaCalendario();
    lis.Insertar(p1);  lis.Insertar(p2);  lis.Insertar(p3);
    lis.Insertar(p4);  lis.Insertar(p5);

    //Busco un elemento que se que esta en la lista
    if (lis.Buscar(p2)){cout << "Prueba 1 --- ok" << endl;}
    else {cout << "Buscar debia dar true y ha dado false" << endl;}
    //Busco un elemento que se que esta en la lista
    if (lis.Buscar(p5)){cout << "Prueba 2 --- ok" << endl;}
    else{cout << "Buscar debia dar true y ha dado false" << endl;}
    //Busco un elemento que NO esta en la lista
    if (lis.Buscar(p6)==false){cout << "Prueba 3 --- ok" << endl;}
    else {cout << "Buscar debia dar false y ha dado true" << endl;}
    //Busco un calendario vacio
    if (lis.Buscar(p7)==false){cout << "Prueba 4 --- ok" << endl;}
    else {cout << "Buscar debia dar false y ha dado true, se paso un calendario vacio" << endl;}

//Constructor de copa
    cout <<endl<< "Constructor de copia" << endl;
    cout << "--------------------" << endl;

    TListaCalendario lis2(lis);
    if (lis2.Longitud()==lis.Longitud()
        && lis2.Buscar(p1)
        && lis2.Buscar(p2)
        && lis2.Buscar(p3)
        && lis2.Buscar(p4)
        && lis2.Buscar(p5))
        {cout << "Prueba 1 --- ok" << endl;}
    else
    {
        cout << "ERROR: No se ha creado una copia exacta"<<endl;
    }

    //Borro un elemento de l1 y l2 debe mantenerse igual
    lis.Borrar(p1);
    if (lis2.Longitud()!=lis.Longitud()
        && lis2.Buscar(p1)
        && lis2.Buscar(p2)
        && lis2.Buscar(p3)
        && lis2.Buscar(p4)
        && lis2.Buscar(p5))
        {cout << "Prueba 2 --- ok" << endl;}
    else
    {
        cout << "ERROR: se borra en lista1 y se borra tambien de la lista 2 (¿mismos punteros?)"<<endl;
    }


//Metodo Borrar
    cout <<endl<< "TListaCalendario.Borrar(Calendario)" << endl;
    cout <<       "----------------------------------" << endl;
    //reinicio datos
    lis.~TListaCalendario();
    lis.Insertar(p1);lis.Insertar(p2);lis.Insertar(p3);lis.Insertar(p4);lis.Insertar(p5);
    lis2.~TListaCalendario();
    lis2.Insertar(p1);lis2.Insertar(p2);lis2.Insertar(p3);lis2.Insertar(p4);lis2.Insertar(p5);

    if (lis.Borrar(p1) && lis.Borrar(p2) && lis.Borrar(p3) && lis.Borrar(p4))
    { cout << "Prueba 1 --- ok" << endl; }
    else
    { cout << "Se esperaba true de las operaciones de borrado" << endl;}
    if (lis2.Borrar(p5) && lis2.Borrar(p2) && lis2.Borrar(p3) && lis2.Borrar(p4))
    {cout << "Prueba 2 --- ok" << endl;}
    else
    {cout << "Se esperaba true de las operaciones de borrado" << endl; }


    if (lis.Longitud()!=1)
    {   cout << "ERROR: Se esperaba <05/01/2004 \"p5\"> y se obtiene " <<lis<<endl;  }
    else
    {   //Se que tiene un elemento, verifico que es el esperado
        if (lis.Obtener(lis.Primera())==p5)
        { cout << "Prueba 3 --- ok" << endl; }
        else
        {cout << "ERROR: Se esperaba <05/01/2004 \"p5\"> y se obtiene " <<lis<<endl;  }
    }

    if (lis2.Longitud()!=1)
    {   cout << "ERROR: Se esperaba <01/01/2000 \"p1\"> y se obtiene " <<lis2<<endl;  }
    else
    {   //Se que tiene un elemento, verifico que es el esperado
        if (lis2.Obtener(lis2.Primera())==p1)
        { cout << "Prueba 4 --- ok" << endl; }
        else
        {cout << "ERROR: Se esperaba <01/01/2000 \"p1\"> y se obtiene " <<lis2<<endl;  }
    }

    if (lis2.Borrar(p6))
    { cout << "Se esperaba false de la operacion de borrado" << endl; }
    else
    { cout << "Prueba 5 --- ok" << endl; }

//Borrado por tlistapos
    cout <<endl<< "TListaCalendario.Borrar(TListaPos)" << endl;
    cout <<       "----------------------------------" << endl;
    //Reinicio datos para hacer mas pruebas
    lis2.~TListaCalendario();   lis2.Insertar(p1); lis2.Insertar(p2); lis2.Insertar(p3); lis2.Insertar(p4);

    TListaPos aux;
    aux = lis2.Primera();
    aux = aux.Siguiente();
    //El puntero debe estar situado en p2, lo borro
    bool boltemporal=false;
    boltemporal = lis2.Borrar(aux);

    if (boltemporal==true)
    { cout << "Prueba 1 --- ok" << endl;}
    else
    {cout << "La operacion de borrado debia retornar TRUE y ha dado FALSE" << endl;}

    if (lis2.Longitud()==3)
    {
        //Bien, solo hay 3, veririco que el calendario 2 no esta
        if (!lis2.Buscar(p2))
        { cout << "Prueba 2 --- ok"<< endl;}
        else
        {cout << "ERROR: Se ha borrado un elemento distinto del que se pretendia"<<endl;}
    }
    else
    {cout << "El numero de elementos no coincide, se esparaban 3" << endl;}

//Borrado con (int, int , int)
    cout <<endl<< "TListaCalendario.Borrar(int, int, int)" << endl;
    cout <<       "--------------------------------------" << endl;
    //Primero llamo al destructor para borrar los datos
    lis.~TListaCalendario();    lis2.~TListaCalendario();
    //Y meto valores nuevos
    lis.Insertar(p1);lis.Insertar(p2);lis.Insertar(p3);lis.Insertar(p4);lis.Insertar(p5);

//    TCalendario p1(1, 1, 2000, (char*)"p1");
//    TCalendario p2(2, 1, 2001, (char*)"p2");
//    TCalendario p3(3, 1, 2002, (char*)"p3");
//    TCalendario p4(4, 1, 2003, (char*)"p4");
//    TCalendario p5(5, 1, 2004, (char*)"p5");
//    TCalendario p6(5, 1, 2005, (char*)"p6");


    boltemporal = lis.Borrar(1, 1, 1999); //No hay ningun elemento menor que la fecha, debe dar falsa
    if (boltemporal)
    {cout << "ERROR: prueba 1: Se esperaba false y obtengo TRUE"<< endl;}
    else
    {cout << "Prueba 1 --- ok"<<endl;};

    if (lis.Longitud()!=5)
    {cout << "ERROR: prueba 2: Se han borrado elementos y deberia haberse borrado nada"<<endl;}
    else
    {cout << "Prueba 2 --- ok"<<endl;};

    boltemporal = lis.Borrar(1, 1, 2002); //deben borrarse los calendarios p1 y p2
    if (boltemporal)
    {
        //Se supone que se han borrado calendarios, a ver si faltan p1 y p2
        if (!lis.Buscar(p1) && !lis.Buscar(p2) && lis.Buscar(p3) && lis.Buscar(p4) && lis.Buscar(p5))
        {
            cout << "Prueba 3 --- ok " << endl;
        }
        else
        {cout << "ERROR: Prueba 3: Se han borrado los calendarios que no se debia" << endl;}
    }
    else
    {cout << "ERROR: prueba 3: Esperaba TRUE y he obtenido FALSE" << endl;}



//Operador +
    cout <<endl<< "TListaCalendario operator+(lista)" << endl;
    cout <<       "------------------" << endl;

    //Primero llamo al destructor para borrar los datos
    lis.~TListaCalendario();    lis2.~TListaCalendario();
    //Y meto valores nuevos
    lis.Insertar(p5);    lis2.Insertar(p1);

    TListaCalendario lis3 = lis + lis2;

    if (lis3.Longitud()!=2)
    { cout <<"Las listas no se han sumado correctamente"<<endl; }
    else
    { cout << "Prueba 1 --- ok" << endl;}
    if (lis.Longitud()!=1)
    {cout << "ERROR: el operador (A=B+C) ha modificado el valor de B"<<endl;}
    else
    {cout << "Prueba 2 --- ok"<<endl;}
    if (lis2.Longitud()!=1)
    {cout << "ERROR: el operador (A=B+C) ha modificado el valor de C"<<endl;}
    else
    {cout << "Prueba 3 --- ok"<<endl;}


//Operador +
    cout <<endl<< "TListaCalendario operator-(lista)" << endl;
    cout <<       "------------------" << endl;

    //Primero llamo al destructor para borrar los datos
    lis.~TListaCalendario();    lis2.~TListaCalendario();    lis3.~TListaCalendario();
    //Y meto valores nuevos
    lis.Insertar(p1);    lis.Insertar(p2);
    lis.Insertar(p3);    lis2.Insertar(p1);
    lis2.Insertar(p3);

    //Resto las lista
    lis3 = lis - lis2;

    //En este punto, lis3 solo deberia tener el calendario p2
    if (lis3.Longitud() != 1)
    {
        cout << "El operador (l1 = l2 - l3) no funciona correctamente"<< endl<<endl;
        cout << lis << endl;
        cout << "              - "<< lis2 << endl;
        cout << "--------------------------------------------------"<<endl;
        cout << "                               <02/01/2001 \"p2\">    <- Esperado"<<endl;
        cout << lis3 << "    <- Obtenido"<< endl;
    }
    else
    {
        cout << "Prueba 1 --- ok"<<endl;

        //Vale, solo hay uno, vamos a probar si es el correcto
        if (lis3.Obtener(lis3.Primera()) == p2)
        {cout << "Prueba 2 --- ok"<< endl;}
        else
        {
            cout << "El operador (l1 = l2 - l3) no funciona correctamente"<< endl<<endl;
            cout << lis << endl;
            cout << "              - "<< lis2 << endl;
            cout << "--------------------------------------------------"<<endl;
            cout << "                               <02/01/2001 \"p2\">    <- Esperado"<<endl;
            cout << lis3 << "    <- Obtenido"<< endl;
        }
    }

    cout <<endl<< "TListaCalendario ExtraerRango" << endl;
    cout <<       "-----------------------------" << endl;
    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();
    lis.Insertar(p1);lis.Insertar(p2);lis.Insertar(p3);lis.Insertar(p4);lis.Insertar(p5);

    //Prueba 1, de 5 elementos, extrae de 1 a 3 (p1, p2, p3)
    lis2 = lis.ExtraerRango(1, 3);
    if (lis2.Longitud()==3 && lis2.Buscar(p1)&& lis2.Buscar(p2)&& lis2.Buscar(p3))
    {

            if (lis.Longitud()!=2 || !lis.Buscar(p4) || !lis.Buscar(p5))
            {
                cout << "ERROR: Prueba 1: La lista destino se ha generado bien, pero la lista de origen" <<endl;
                cout << "                 no ha borrado los elementos transferidos correctamente"<<endl;
            }
            else
            {
                cout << "Prueba 1 --- ok"<<endl;
            }
    }
    else
    {
        cout << "ERROR: Prueba 1: No se ha extraido el rango correctamente"<<endl;
        cout << "     Esperaba : <" << p1 << " "<< p2 << " "<< p3 << ">"<< endl;
        cout << "     Obtengo  : " << lis2 << endl;
    }

    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();
    lis.Insertar(p1);lis.Insertar(p2);lis.Insertar(p3);lis.Insertar(p4);lis.Insertar(p5);
    //Se pasa un numero negativo como origen
    lis2 = lis.ExtraerRango(-8, 3);
    if (lis2.Longitud()==3 && lis2.Buscar(p1)&& lis2.Buscar(p2)&& lis2.Buscar(p3))
    {
            if (lis.Longitud()!=2 || !lis.Buscar(p4) || !lis.Buscar(p5))
            {
                cout << "ERROR: Prueba 2: La lista destino se ha generado bien, pero la lista de origen" <<endl;
                cout << "                 no ha borrado los elementos transferidos correctamente"<<endl;
            }
            else
            {
                cout << "Prueba 2 --- ok"<<endl;
            }
    }
    else
    {
        cout << "ERROR: Prueba 2: No se ha extraido el rango correctamente"<<endl;
        cout << "     Esperaba : <" << p1 << " "<< p2 << " "<< p3 << ">"<< endl;
        cout << "     Obtengo  : " << lis2 << endl;
    }

    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();
    lis.Insertar(p1);lis.Insertar(p2);lis.Insertar(p3);lis.Insertar(p4);lis.Insertar(p5);
    //Se excede el rango
    lis2 = lis.ExtraerRango(3, 10);
    if (lis2.Longitud()==3 && lis2.Buscar(p3)&& lis2.Buscar(p4)&& lis2.Buscar(p5))
    {
            if (lis.Longitud()!=2 || !lis.Buscar(p1) || !lis.Buscar(p2))
            {
                cout << "ERROR: Prueba 3: La lista destino se ha generado bien, pero la lista de origen" <<endl;
                cout << "                 no ha borrado los elementos transferidos correctamente"<<endl;
            }
            else
            {
                cout << "Prueba 3 --- ok"<<endl;
            }
    }
    else
    {
        cout << "ERROR: Prueba 3: No se ha extraido el rango correctamente"<<endl;
        cout << "     Esperaba : <" << p3 << " "<< p4 << " "<< p5 << ">"<< endl;
        cout << "     Obtengo  : " << lis2 << endl;
    }

    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();
    lis.Insertar(p1);lis.Insertar(p2);lis.Insertar(p3);lis.Insertar(p4);lis.Insertar(p5);
    //Se pasa el mismo numero como origen y final
    lis2 = lis.ExtraerRango(3, 3);
    if (lis2.Longitud()==1 && lis2.Buscar(p3))
    {
            if (lis.Longitud()!=4 || !lis.Buscar(p1)|| !lis.Buscar(p2)|| !lis.Buscar(p4) || !lis.Buscar(p5))
            {
                cout << "ERROR: Prueba 4: La lista destino se ha generado bien, pero la lista de origen" <<endl;
                cout << "                 no ha borrado los elementos transferidos correctamente"<<endl;
            }
            else
            {
                cout << "Prueba 4 --- ok"<<endl;
            }
    }
    else
    {
        cout << "ERROR: Prueba 4: No se ha extraido el rango correctamente"<<endl;
        cout << "     Esperaba : <" << p1 << " "<< p2 << " "<< p3 << ">"<< endl;
        cout << "     Obtengo  : " << lis2 << endl;
    }

    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();
    lis.Insertar(p1);lis.Insertar(p2);lis.Insertar(p3);lis.Insertar(p4);lis.Insertar(p5);
    //Se pasa un numero negativo como origen
    lis2 = lis.ExtraerRango(10, 1);
    if (lis2.EsVacia())
    {
            if (lis.Longitud()!=5)
            {
                cout << "ERROR: Prueba 5: No se ha extraido ningun elemento, la lista de destino " <<endl;
                cout << "                 es correcta <> pero se han eliminado elementos de la "<<endl;
                cout << "                 lista de origen y esto no es correcto"<<endl;
            }
            else
            {
                cout << "Prueba 5 --- ok"<<endl;
            }
    }
    else
    {
        cout << "ERROR: Prueba 5: n1 es mayor que n2, se esperaba una lista vacia"<<endl;
        cout << "     Esperaba : <>"<< endl;
        cout << "     Obtengo  : " << lis2 << endl;
    }



    cout <<endl<< "TListaCalendario SumarSubl" << endl;
    cout <<       "--------------------------" << endl;

    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();

    lis.Insertar(p1);                   lis.Insertar(p3);  lis.Insertar(p4); lis.Insertar(p5);
    lis2.Insertar(p1);lis2.Insertar(p2);                   lis2.Insertar(p4); lis2.Insertar(p5);

    lis3 = lis.SumarSubl(0, 6, lis2, 0, 6);

    if (lis3.Longitud()==5)
    {
        cout << "Prueba 1 --- ok" << endl;
    }
    else
    {
        cout << "ERROR: La lista resultante no conincide con lo esperado"<<endl;
    }

    if (lis.Longitud()!=4 || lis2.Longitud()!=4)
    {
        cout << "ERROR: Las listas de origen no deben ser modificadas"<<endl;
    }
    else
    {
        cout << "Prueba 2 --- ok"<<endl;
    }

	TListaCalendario lis4;
    lis4 = lis.SumarSubl(2,3, lis2, 2,4);

    if (lis4.Longitud()==4 && lis4.Buscar(p2)&&lis4.Buscar(p3)&&lis4.Buscar(p4)&&lis4.Buscar(p5))
    {
        cout << "Prueba 3 --- ok"<<endl;
    }
    else
    {
        cout << "ERROR: Prueba 3: La lista no coincide con lo esperado"<<endl;
        cout << "<02/01/2001 \"p2\" 03/01/2002 \"p3\" 04/01/2003 \"p4\" 05/01/2004 \"p5\"> < \"------- cadena esperada"<<endl;
        cout << lis4 << "< \"------- cadena obtenida"<<endl;
    }

    //reinicio datos
    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();lis4.~TListaCalendario();
    lis.Insertar(p1);   lis.Insertar(p2);   lis.Insertar(p3);   lis.Insertar(p4);   lis.Insertar(p5);
    lis2.Insertar(p1);  lis2.Insertar(p2);  lis2.Insertar(p3);  lis2.Insertar(p4);  lis2.Insertar(p5);

    lis3 = lis.SumarSubl(-3, -2, lis2, -5, -2);
    //Deberia resultar en una lista vacia

    if (lis3.EsVacia())
    {
        cout << "Prueba 4 --- ok"<<endl;
    }
    else
    {
        cout << "ERROR: Prueba 5: Se han pasado todo numeros negativos, deberia obtener una lista vacia"<<endl;
        cout << "    Obtengo -> "<<lis3<<endl;
    }

    //reinicio datos
    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();lis4.~TListaCalendario();
    lis.Insertar(p1);   lis.Insertar(p2);   lis.Insertar(p3);   lis.Insertar(p4);   lis.Insertar(p5);
    lis2.Insertar(p1);  lis2.Insertar(p2);  lis2.Insertar(p3);  lis2.Insertar(p4);  lis2.Insertar(p5);

    lis3 = lis.SumarSubl(3, 2, lis2, 5, 5);
    //Deberia resultar en una lista vacia

    if (lis3.Longitud()==1 && lis3.Buscar(p5))
    {
        cout << "Prueba 5 --- ok"<<endl;
    }
    else
    {
        cout << "ERROR: Prueba 5: Se paso n1>n2 y n3,n3, deberia obtener solo el calendario 5"<<endl;
        cout << "    Obtengo -> "<<lis3<<endl;
    }

//Paso dos listas vacias
    //reinicio datos
    lis.~TListaCalendario();lis2.~TListaCalendario();lis3.~TListaCalendario();lis4.~TListaCalendario();

    lis3 = lis.SumarSubl(3, 4, lis2, 2, 3);
    //Deberia resultar en una lista vacia

    if (lis3.EsVacia())
    {
        cout << "Prueba 6 --- ok"<<endl;
    }
    else
    {
        cout << "ERROR: Prueba 6: Se pasaron dos listas vacias, esperaba una lista vacia"<<endl;
        cout << "    Obtengo -> "<<lis3<<endl;
    }

    return 0;
}
