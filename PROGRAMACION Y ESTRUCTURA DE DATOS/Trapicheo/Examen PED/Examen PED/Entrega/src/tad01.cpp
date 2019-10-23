//============================================================================
// Name        : Examen.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "tabbcalendario.h"
#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tlistacalendario.h"
using namespace std;

int main() {
	TABBCalendario tabb;
	TABBCalendario tabb2;
	TCalendario c20(20,1,1995,(char*)"Calen20");
	TCalendario c15(15,1,1995,(char*)"Calen15");
	TCalendario c13(13,1,1995,(char*)"Calen13");
	TCalendario c11(11,1,1995,(char*)"Calen11");
	TCalendario c14(14,1,1995,(char*)"Calen14");
	TCalendario c12(12,1,1995,(char*)"Calen12");
	TCalendario c16(16,1,1995,(char*)"Calen16");
	TCalendario c17(17,1,1995,(char*)"Calen17");
	TCalendario c21(21,1,1995,(char*)"Calen21");

	tabb.Insertar(c20);
	tabb.Insertar(c15);
	tabb.Insertar(c13);
	tabb.Insertar(c11);
	tabb.Insertar(c14);
	tabb.Insertar(c12);
	tabb.Insertar(c16);
	tabb.Insertar(c17);
	tabb.Insertar(c21);

	tabb2.Insertar(c20);
	tabb2.Insertar(c15);
	tabb2.Insertar(c13);
	tabb2.Insertar(c14);
	tabb2.Insertar(c12);
	tabb2.Insertar(c16);
	tabb2.Insertar(c17);
	tabb2.Insertar(c21);

	TListaCalendario l1;
	TListaCalendario l2;
	TListaCalendario lsi;
	TListaCalendario lno;

	TListaCalendario lborra;// Lista a buscar

	lborra.Insertar(c13);
	lborra.Insertar(c11);
	lborra.Insertar(c12);

	l1.Insertar(c20);
	l1.Insertar(c15);
	l1.Insertar(c13);
	l1.Insertar(c11);
	l1.Insertar(c14);
	l1.Insertar(c12);
	l1.Insertar(c16);
	l1.Insertar(c17);
	l1.Insertar(c21);

	lsi.Insertar(c20);
	lsi.Insertar(c15);
	lsi.Insertar(c14);
	lsi.Insertar(c16);
	lsi.Insertar(c17);
	lsi.Insertar(c21);

	lno.Insertar(c20);
	lno.Insertar(c15);
	lno.Insertar(c13);
	lno.Insertar(c14);
	lno.Insertar(c12);
	lno.Insertar(c16);
	lno.Insertar(c17);
	lno.Insertar(c21);

	if(tabb.examen(lborra)==lsi){
		cout << "Va benne 1."<< endl;
	}

	if(tabb2.examen(lborra)==lno){
		cout << "Va benne 2."<< endl;
	}

	return 0;
}
