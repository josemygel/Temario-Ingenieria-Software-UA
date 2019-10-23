#include <iostream>
#include "../include/tabbcalendario.h"
#include "../include/tavlcalendario.h"
#include "../include/tcalendario.h"
using namespace std;


int
main(void)
{
	// DECLARACIONES
	TABBCalendario arbol, abbvacio;
	TAVLCalendario arbolAVL, avlvacio;
	TListaCalendario lista, listavacia, listaca, listaca2, listaca3, listanodos1, listanodos2, listanodos3, listanodos4, carlista;
	TVectorCalendario vectornodos(3);
	int* vector;
	TCalendario a1(1,05,2015, (char*) "mensaje 1");
	TCalendario a2(2,05,2015, (char*) "mensaje 2");
	TCalendario a3(3,05,2015, (char*) "mensaje 3");
	TCalendario a8(8,05,2015, (char*) "mensaje 8");
	TCalendario a9(9,05,2015, (char*) "mensaje 9");
	TCalendario a12(12,05,2015, (char*) "mensaje 12");
	TCalendario a14(14,05,2015, (char*) "mensaje 14");
	TCalendario a15(15,05,2015, (char*) "mensaje 15");
	TCalendario a19(19,05,2015, (char*) "mensaje 19");
	TCalendario a20(20,05,2015, (char*) "mensaje 20");

	// INSERCIONES
	arbol.Insertar(a15);
	arbol.Insertar(a12);
	arbol.Insertar(a20);
	arbol.Insertar(a8);
	arbol.Insertar(a1);
	arbol.Insertar(a9);

	lista.Insertar(a8);
	lista.Insertar(a12);
	lista.Insertar(a20);

	carlista.Insertar(a1);
	carlista.Insertar(a8);
	carlista.Insertar(a9);
	carlista.Insertar(a12);
	carlista.Insertar(a15);
	carlista.Insertar(a20);

	vectornodos[1]=a1;
	vectornodos[2]=a8;
	vectornodos[3]=a12;

	arbolAVL.Insertar(a15);
	arbolAVL.Insertar(a12);
	arbolAVL.Insertar(a20);
	arbolAVL.Insertar(a8);
	arbolAVL.Insertar(a14);
	arbolAVL.Insertar(a19);
	arbolAVL.Insertar(a9);
	arbolAVL.Insertar(a1);

	listanodos1.Insertar(a8);
	listanodos1.Insertar(a12);
	listanodos1.Insertar(a20);

	listanodos2.Insertar(a8);
	listanodos2.Insertar(a12);
	listanodos2.Insertar(a15);

	listanodos3.Insertar(a19);
	listanodos3.Insertar(a15);

	listanodos4.Insertar(a9);
	listanodos4.Insertar(a12);

	cout << "*****************" << endl;
	cout << "*** SUPER TAD ***" << endl;
	cout << "*****************" << endl;

	cout << "-------------------------------------------------------------\n";
	cout << "Para pasar este tad debes implementar las siguientes funciones:\n";
	cout << "examen2013, examen2014, examenJunio2014 y examen2008a\n";
	cout << "Compara tus resultados (-), con los que deberías obtener (+)\n";
	cout << "- PABLO REQUENA Y MARCOS GONZÁLEZ - 2015\n";
	cout << "-------------------------------------------------------------\n\n";

	cout << "*** Examen Junio 2013 ***\n";

	vector = arbol.examen2013(lista);

	cout << "Vector y Árbol con elementos:\n";
	cout << "+ Vector: [2, 1, 0]" << endl;
	cout << "- Vector: [";
	for(int i = 0; i < lista.Longitud(); i++)
	{
		cout << vector[i];

		if(i < lista.Longitud() - 1)
			cout << ", ";
	}
	cout << "]\n-------------------------------------------------------------\n";

	vector = abbvacio.examen2013(lista);

	cout << "Árbol Vacío:\n";
	cout << "+ Vector: [0, 0, 0]" << endl;
	cout << "- Vector: [";
	for(int i = 0; i < lista.Longitud(); i++)
	{
		cout << vector[i];

		if(i < lista.Longitud() - 1)
			cout << ", ";
	}
	cout << "]\n-------------------------------------------------------------\n";

	vector = arbol.examen2013(listavacia);

	cout << "Lista Vacía:\n";
	cout << "+ Vector: []" << endl;
	cout << "- Vector: [";
	for(int i = 0; i < listavacia.Longitud(); i++)
	{
		cout << vector[i];

		if(i < lista.Longitud() - 1)
			cout << ", ";
	}
	cout << "]\n-------------------------------------------------------------\n\n";

	cout << "*** Julio 2014 ***\n";

	cout << "Lista con elementos:\n";
	listaca=arbolAVL.examen2014(lista);
	cout << "+ Lista: <12/05/2015 \"mensaje 12\" 14/05/2015 \"mensaje 14\" 20/05/2015 \"mensaje 20\">\n";
	cout << "- Lista: " << listaca << endl;
	cout << "-------------------------------------------------------------\n";

	cout << "Lista Vacía:\n";
	listaca2=arbolAVL.examen2014(listavacia);
	cout << "+ Lista: <>\n";
	cout << "- Lista: " << listaca2 << endl;
	cout << "-------------------------------------------------------------\n";

	cout << "Arbol Vacío:\n";
	listaca3=avlvacio.examen2014(lista);
	cout << "+ Lista: <>\n";
	cout << "- Lista: " << listaca3 << endl;
	cout << "-------------------------------------------------------------\n\n";

	cout << "*** Junio 2014 ***\n";

	cout << "Lista 8-20:" << endl;
	cout << "+ No hay camino" << endl << "- ";
	if(arbol.examenJunio2014(listanodos1))
		cout << "Hay camino" << endl;
	else
		cout <<"No hay camino" << endl;
	cout << "-------------------------------------------------------------\n";

	cout << "Lista 8-15:" << endl;
	cout << "+ Hay camino" << endl << "- ";
	if(arbol.examenJunio2014(listanodos2))
		cout << "Hay camino" << endl;
	else
		cout <<"No hay camino" << endl;
	cout << "-------------------------------------------------------------\n";

	cout << "Lista 15-19:" << endl;
	cout << "+ No hay camino" << endl << "- ";
	if(arbol.examenJunio2014(listanodos3))
		cout << "Hay camino" << endl;
	else
		cout <<"No hay camino" << endl;
	cout << "-------------------------------------------------------------\n";

	cout << "Lista 9-12:" << endl;
	cout << "+ Hay camino" << endl << "- ";
	if(arbol.examenJunio2014(listanodos4))
		cout << "Hay camino" << endl;
	else
		cout <<"No hay camino" << endl;
	cout << "-------------------------------------------------------------\n";

	cout << "Lista vacía:" << endl;
	cout << "+ No hay camino" << endl << "- ";
	if(arbol.examenJunio2014(listavacia))
		cout << "Hay camino" << endl;
	else
		cout <<"No hay camino" << endl;
	cout << "-------------------------------------------------------------\n";

	cout << "Árbol vacío:" << endl;
	cout << "+ No hay camino" << endl << "- ";
	if(abbvacio.examenJunio2014(listanodos1))
		cout << "Hay camino" << endl;
	else
		cout <<"No hay camino" << endl;
	cout << "-------------------------------------------------------------\n\n";

	cout << "*** Febrero 2008 (turno 1) ***\n";

	vector=carlista.examen2008a(vectornodos);
	cout << "+ Vector: [1, 1, 0]" << endl;
	cout << "- Vector: [";
	for(int i = 0; i < lista.Longitud(); i++)
	{
		cout << vector[i];

		if(i < lista.Longitud() - 1)
			cout << ", ";
	}
	cout << "]\n-------------------------------------------------------------\n";
}
