/*
 * tlistacalendario.cpp
 *
 *  Created on: 11/3/2015
 *      Author: Alejandro Reyes Albillar
 */

#include "tlistacalendario.h"
#include "tcalendario.h"

//Clase TNodoCalendario

TNodoCalendario::TNodoCalendario():c() {
	siguiente=NULL;
}

TNodoCalendario::TNodoCalendario(const TNodoCalendario& nc):c(nc.c) {
		siguiente=NULL;
}

TNodoCalendario::~TNodoCalendario() {
	if(siguiente!=NULL){
		siguiente=NULL;
		c.~TCalendario();
	}
	else{
		siguiente=NULL;
		c.~TCalendario();
	}
}

TNodoCalendario& TNodoCalendario::operator =(const TNodoCalendario& nc) {
	if(this!= &nc){
		(*this).~TNodoCalendario();
		c=nc.c;
		siguiente=NULL;
	}
	return *this;
}

//Clase TListaPos

TListaPos::TListaPos() {
	pos=NULL;
}

TListaPos::TListaPos(const TListaPos& lp) {
	pos=lp.pos;
}

TListaPos::~TListaPos() {
	if(pos!=NULL){
		pos=NULL;
	}
}

TListaPos& TListaPos::operator =(const TListaPos& lp) {
	if(this!= &lp){
		(*this).~TListaPos();
		pos=lp.pos;
	}
	return *this;
}

bool TListaPos::operator ==(const TListaPos& lp) {
	if(pos==lp.pos){//Si apuntan a la misma posicion de memoria
		return true;
	}
	else{
		return false;
	}
}

bool TListaPos::operator !=(const TListaPos& lp) {
	return !(*this==lp);
}

TListaPos TListaPos::Siguiente(){
	TListaPos lp;
	lp.pos=pos->siguiente;
	/*if(!(lp.pos->siguiente==NULL)){
		lp.pos=pos->siguiente;
	}
	else{
		lp.pos=NULL;
	}*/
	return lp;
}

bool TListaPos::EsVacia(){
	if(pos==NULL){
		return true;
	}
	return false;
}

//Clase TListaCalendario

TListaCalendario::TListaCalendario() {
	primero=NULL;
}

TListaCalendario::TListaCalendario(const TListaCalendario& lc) {
	primero= lc.primero;
	TNodoCalendario * aux1, *aux2;

	if(lc.primero!=NULL){//Si el primero no es null
		for(int i=1;i< lc.Longitud();i++){//Miramos la lista
			aux1=primero;//ponemos aux1 en primero
			while(aux1!=NULL){//Recorremos los punteros y los copiamos
				aux2=aux1->siguiente;//A aux2 le asigno la siguiente a primero
				primero->c=lc.primero->c;//a primero le asigno el calendario del nuevo puntero
				aux1=aux2;//muevo aux1 a la posicion de aux2
			}
			aux1=NULL;
			aux2=NULL;
		}
	}
	else{
		primero=NULL;
	}
}

TListaCalendario::~TListaCalendario() {
	TListaPos lp;
	lp=Primera();
	if(primero!=NULL){
		for(int i=0; i<Longitud();i++){
			lp=lp.Siguiente();
		}
		primero=lp.pos;

		primero=NULL;
	}

}

TListaCalendario& TListaCalendario::operator =(const TListaCalendario& lc) {
	if(this!= &lc){
		(*this).~TListaCalendario();
		if(lc.primero!=NULL){
			primero=lc.primero;
		}
		else{
			primero=NULL;
		}
	}
	return *this;
}

bool TListaCalendario::operator ==(const TListaCalendario& lc) {
	TNodoCalendario *a=primero;//Nodo auxiliar con this
	TNodoCalendario *b=lc.primero;//Nodo auxiliar con el parametro

	if(Longitud()==lc.Longitud()){
		for(int i=0;i<Longitud();i++){
			if(a!=b){
				return false;
			}
			a=a->siguiente;
			b=b->siguiente;
		}
		return true;
	}

	return false;
}

TListaCalendario TListaCalendario::operator +(const TListaCalendario& lc) {
	TListaCalendario nueva;
	TListaPos lp(Primera());
	TListaPos lp2(lc.Primera());
	for(int i=0; i<Longitud();i++){
		nueva.Insertar(lp.pos->c);
		lp=lp.Siguiente();
	}
	for(int i=0; i<lc.Longitud();i++){
			nueva.Insertar(lp2.pos->c);
			lp2=lp2.Siguiente();
		}
	return nueva;
}

TListaCalendario TListaCalendario::operator -(const TListaCalendario& lc) {
	TListaCalendario nueva;
	TListaPos lp(Primera());
	TListaPos lp2(lc.Primera());
	for(int i=0; i<Longitud();i++){
		nueva.Insertar(lp.pos->c);
		lp=lp.Siguiente();
	}
	for(int i=0; i<lc.Longitud();i++){
			nueva.Borrar(lp2.pos->c);
			lp2=lp2.Siguiente();
		}
	return nueva;
}

bool TListaCalendario::Insertar(const TCalendario& c) {//Funciona Bien
	TListaPos lp;
	TListaPos ant;//Posicion anterior
	TNodoCalendario* aux=new TNodoCalendario();
	lp=Primera();
	ant=Primera();

	if(lp.EsVacia()){
		lp.pos=new TNodoCalendario();
		lp.pos->c=c;
		primero=lp.pos;
		return true;
	}

	if(Buscar(c)){//Si existe dentro de la lista no se incluye
		return false;
	}
	else{
		while(!lp.EsVacia()){//Si la lista no es vacía
			if(lp.pos->c>c){//Si la posicion es mayor
				if(lp==Primera()){
					aux->c=c;
					aux->siguiente=lp.pos;
					primero=aux;
					return true;
				}
			}
			else{//Si la posicion es menor
				if(lp.Siguiente().EsVacia()){//Es el último elemento
					aux->c=c;
					aux->siguiente=lp.Siguiente().pos;
					lp.pos->siguiente=aux;
					return true;
				}
				else if(lp.Siguiente().pos->c>c){//Esta en medio
					aux->c=c;
					aux->siguiente=lp.pos->siguiente;
					ant.pos->siguiente=aux;
					return true;
				}
			}
			lp=lp.Siguiente();
		}
	}
	return true;
}

bool TListaCalendario::Borrar(const TCalendario& c) {
	TListaPos lp;
	TListaPos ant;
	TNodoCalendario *aux1=new TNodoCalendario();
	TListaCalendario aux(*this);
	lp=aux.Primera();
	if(!lp.EsVacia()){
		if(Buscar(c)){
			for(int i=0;i<aux.Longitud();i++){
				if(lp.pos->c==c){
					if(i==0){//Si es la primera
						aux1=primero;
						primero=lp.Siguiente().pos;
					}
					else{
						aux1=lp.Siguiente().pos;
						ant.pos->siguiente=aux1;
					}
					return true;
				}
				ant=lp;
				lp=lp.Siguiente();
			}
		}
	}
	return false;
}

bool TListaCalendario::Borrar(const TListaPos& lp) {
	return Borrar(Obtener(lp));
}

bool TListaCalendario::Borrar(int dia, int mes, int anyo) {
	TListaPos lp;
	lp=Primera();
	if(anyo<primero->c.Anyo()){
		return false;
	}
	else{
		if(mes<primero->c.Mes()){
			return false;
		}
		else{
			if(dia<primero->c.Dia()){
				return false;
			}
			else{
				for(int i=0;i<Longitud();i++){
					if((lp.pos->c.Anyo()<anyo) || (lp.pos->c.Anyo()==anyo && lp.pos->c.Mes()<mes)
						|| (lp.pos->c.Anyo()==anyo && lp.pos->c.Mes()==mes && lp.pos->c.Dia()<dia)){

						Borrar(lp.pos->c);
						i=0;
						lp=Primera();
					}
					else{
						lp=lp.Siguiente();
					}
				}
				return true;
			}
		}
	}
}

bool TListaCalendario::EsVacia() {
	if(primero==NULL){
		return true;
	}
	return false;
}

TCalendario TListaCalendario::Obtener(const TListaPos& lp)const {
	TListaPos aux(lp);
	if(!aux.EsVacia()){
		return lp.pos->c;
	}
	TCalendario a;
	return a;
}

bool TListaCalendario::Buscar(const TCalendario& c) {//Devuelve true si el calendario existe en la lista actual
	TListaPos lp;
	TListaCalendario aux(*this);
	lp.pos=aux.primero;
	for(int i=0;i<aux.Longitud();i++){
		if(lp.pos->c==c){
			return true;
		}
		lp=lp.Siguiente();
	}
	return false;
}

int TListaCalendario::Longitud()const {
	int longi=0;
	TListaPos lp;
	lp=Primera();

	for(int i=0; !lp.EsVacia();i++){
		lp=lp.Siguiente();
		longi++;
	}
	return longi;
}

TListaPos TListaCalendario::Primera() const {
	TListaPos lp;
	lp.pos=primero;
	return lp;
}

TListaPos TListaCalendario::Ultima() const{
	TListaPos lp;
	lp=Primera();
	for(int i=1;i<Longitud();i++){//Empiezas en la primera posicion por lo que i se inicializa a 1
		lp=lp.Siguiente();
	}
	return lp;
}

TListaCalendario TListaCalendario::SumarSubl(int I_L1, int F_L1, const TListaCalendario& L2, int I_L2, int F_L2) {
	TListaCalendario nuevaL1;
	TListaCalendario nuevaL2;
	TListaCalendario auxL2(L2);
	if(I_L1<=F_L1 && I_L2<=F_L2){//Rango Coherente
		if(I_L1 <=0 || I_L2 <=0){
			if(I_L1<=0 && I_L2 <=0){//Si F_L1 y F_L2 son menores o iguales que 0
				nuevaL1=ExtraerRangoNM(1, F_L1);
				nuevaL2= auxL2.ExtraerRangoNM(1, F_L2);
			}
			else if(I_L1<=0){//Si I_L1 es menor o igual que 0
				nuevaL1=ExtraerRangoNM(1, F_L1);
				nuevaL2=auxL2.ExtraerRangoNM(I_L2, F_L2);
			}
			else if(I_L2<=0){//Si I_L2 es menor o igual que 0
				nuevaL1=ExtraerRangoNM(I_L1, F_L1);
				nuevaL2=auxL2.ExtraerRangoNM(1, F_L2);
			}
		}
		else if(F_L1>Longitud() || F_L2 > L2.Longitud()){
			if(F_L1>Longitud() && F_L2 > L2.Longitud()){//Si F_L1 y F_L2 son mayores que el tamaño de sus listas
					nuevaL1=ExtraerRangoNM(I_L1, Longitud());
					nuevaL2=auxL2.ExtraerRangoNM(I_L2, Longitud());
			}
			else if(F_L1>Longitud()){//Si F_L1 es mayor que el tamaño de la lista F1(*this)
				nuevaL1=ExtraerRangoNM(I_L1, Longitud());
				nuevaL2=auxL2.ExtraerRangoNM(I_L2, F_L2);
			}
			else if(F_L2>auxL2.Longitud()){//Si F_L2 es mayor que el tamaño de la lista F2
				nuevaL1=ExtraerRangoNM(I_L1, F_L1);
				nuevaL2=auxL2.ExtraerRangoNM(I_L2, Longitud());
			}
		}
		else{
			nuevaL1=ExtraerRangoNM(I_L1, F_L1);
			nuevaL2=auxL2.ExtraerRangoNM(I_L2, F_L2);
		}
	}
	else{
		if(I_L1>F_L1 && I_L2 > F_L2){//Si I_L1 y I_L2 son mayores que F_L1 Y F_L2
		}
		else if(I_L1>F_L1){//Si F_L1 es mayor que I_L1
			nuevaL2=auxL2.ExtraerRangoNM(I_L2, F_L2);
		}
		else if(I_L2>F_L2){//Si F_L2 es mayor que F_L2
			nuevaL1=ExtraerRangoNM(I_L1, F_L1);
		}
	}
	return nuevaL1+nuevaL2;
}

TListaCalendario TListaCalendario::ExtraerRango(int n1, int n2) {
	TListaCalendario nueva;
	TListaPos lp, aux;
	lp=Primera();
	if(n2>Longitud()){
		n2=Longitud();
	}
	if(n1<=0){
		n1=1;
	}
	if(n1<=n2){
		for(int i=1;i<=n2;i++){
			if(i>=n1){
				nueva.Insertar(lp.pos->c);
				aux=lp.Siguiente();
				Borrar(lp.pos->c);
				lp=aux;
			}
			else{
				lp=lp.Siguiente();
			}
		}
	}
	return nueva;
}

TListaCalendario TListaCalendario::ExtraerRangoNM(int n1, int n2) {
	TListaCalendario nueva;
	TListaPos lp, aux;
	lp=Primera();
	if(n2>Longitud()){
		n2=Longitud();
	}
	if(n1<=0){
		n1=1;
	}
	if(n1<=n2){
		for(int i=1;i<=n2;i++){
			if(i>=n1){
				nueva.Insertar(lp.pos->c);
				aux=lp.Siguiente();
				lp=aux;
			}
			else{
				lp=lp.Siguiente();
			}
		}
	}
	return nueva;
}

ostream & operator<<(ostream &o,const TListaCalendario &lc){
	TListaPos lp;
	lp=lc.Primera();
	if(lp.EsVacia()){
		o<<"<>";
	}
	else{
		o<<"<";
		for(int i=0;i<lc.Longitud();i++){
			if(i==0){
				o<< lc.Obtener(lp);
			}
			else{
				o<< " " << lc.Obtener(lp);
			}
			lp=lp.Siguiente();
		}
		o<<">";
	}
	return o;
}
