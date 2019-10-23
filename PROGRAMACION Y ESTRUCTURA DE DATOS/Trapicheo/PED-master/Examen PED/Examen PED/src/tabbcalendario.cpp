/*
 * tabbcalendario.cpp
 *
 *  Created on: 21/4/2015
 *      Author: Alejandro Reyes Albillar
 */

#include "tabbcalendario.h"

//Clas TNodoABB

TNodoABB::TNodoABB():item(), iz(),de() {//Layering
}

TNodoABB::TNodoABB(const TNodoABB& tnabb):item(tnabb.item), iz(tnabb.iz), de(tnabb.de) {//Layering
}

TNodoABB::~TNodoABB() {//Llama a los destructores por defecto gracias al layering y no hay que añadir nada dentro
}

TNodoABB& TNodoABB::operator =(const TNodoABB& tnabb) {
	if(this!= &tnabb){
		(*this).~TNodoABB();
		iz=tnabb.iz;
		de=tnabb.de;
		item=tnabb.item;
	}
	return *this;
}

//Clase TABBCalendario

TABBCalendario::TABBCalendario() {
	raiz=NULL;
}

void TABBCalendario::copiar(const TABBCalendario& abbc){
	TNodoABB* aux=new TNodoABB();
	if(abbc.raiz!=NULL){
		aux->item=abbc.raiz->item;
		raiz=aux;
		raiz->de.copiar(abbc.raiz->de);
		raiz->iz.copiar(abbc.raiz->iz);
	}
}

TABBCalendario::TABBCalendario(const TABBCalendario& abbc) {
		raiz=NULL;
		copiar(abbc);
}

TABBCalendario::~TABBCalendario() {
	if(raiz!=NULL){
		delete raiz;
		raiz=NULL;
	}
	else{
		raiz=NULL;
	}
}

TABBCalendario& TABBCalendario::operator =(const TABBCalendario& abbc) {
	if(this!= &abbc){
		(*this).~TABBCalendario();
		copiar(abbc);
	}
	return *this;
}

bool TABBCalendario::operator ==(const TABBCalendario& abbc) {
	TABBCalendario aux(abbc);
	if(Inorden() == aux.Inorden()){
			return true;
	}
	else{
		return false;
	}
}

bool TABBCalendario::EsVacio() {
	if(raiz==NULL){
		return true;
	}
	else{
		return false;
	}
}

bool TABBCalendario::Insertar(const TCalendario& c) {
	if(!Buscar(c)){
		if(EsVacio()){
			TNodoABB* aux=new TNodoABB;
			raiz=aux;
			raiz->item=c;
			return true;
		}
		else{
			if(Raiz() > c){
				return raiz->iz.Insertar(c);
			}
			else{
				return raiz->de.Insertar(c);
			}
		}
	}
	return false;
}

//Me devuelve el último hijo de la derecha, y si ese hijo tiene hijos a la izquierda, cambia la raiz por este y devuelve la copia temporal del hijo
TABBCalendario* TABBCalendario::MayorArbol() {
	TABBCalendario* temp=new TABBCalendario(*this);
	if(raiz->de.EsVacio()){//Si no hay hijos a la derecha
		if(raiz->iz.EsVacio()){//Si el último hijo no tiene hijos
			return this;
		}
		else{
			raiz=raiz->iz.raiz;
			return temp;
		}
	}
	else{
		return raiz->de.MayorArbol();
	}
}

bool TABBCalendario::Borrar(const TCalendario& c) {//Falla
	TABBCalendario temp(*this);
	if(Buscar(c)){
		if(Raiz()==c){
			//Borrar
			if(raiz->iz.EsVacio()  && raiz->de.EsVacio()){//Es un nodo
				raiz=NULL;
				return true;
			}
			if(!raiz->de.EsVacio() && !raiz->iz.EsVacio()){
				if(temp.raiz->iz.MayorArbol()->raiz->iz.EsVacio()){
					raiz->item=raiz->iz.MayorArbol()->Raiz();
					raiz->iz.MayorArbol()->~TABBCalendario();
					return true;
				}
				else{
					raiz->item=raiz->iz.MayorArbol()->Raiz();
					return true;
				}
				//cambiar por el mayor de la izq
			}
			if(!raiz->de.EsVacio()){
				raiz=raiz->de.raiz;
				//Cambiamos raiz por derecha
				return true;
			}
			if(!raiz->iz.EsVacio()){
				raiz=raiz->iz.raiz;
				//Cambiamos raiz por izquierda
				return true;
			}
		}
		else{
			if(raiz->iz.Borrar(c)){
				return true;
			}
			else{
				return raiz->de.Borrar(c);
			}
		}
	}
	return false;
}

bool TABBCalendario::Buscar(const TCalendario& c) {
	if(!EsVacio()){
		if(Raiz()==c){
			return true;
		}
		else{
			if(raiz->de.Buscar(c) || raiz->iz.Buscar(c)){
				return true;
			}
			else{
				return false;
			}
		}
	}
	else{
		return false;
	}
}

TCalendario TABBCalendario::Raiz() {
	if(EsVacio()){
		return TCalendario();
	}
	else{
		return raiz->item;
	}
}

int TABBCalendario::Altura() {
	if(EsVacio()){
		return 0;
	}
	else{
		return max(raiz->iz.Altura(), raiz->de.Altura())+1;
	}
}

int TABBCalendario::Nodos() {
	if(EsVacio()){
		return 0;
	}
	return raiz->de.Nodos()+ raiz->iz.Nodos()+1;
}

int TABBCalendario::NodosHoja() {
	if(!EsVacio()){
		if(raiz->de.EsVacio()&& raiz->iz.EsVacio()){
			return 1;
		}
		else{
			return raiz->iz.NodosHoja()+raiz->de.NodosHoja();
		}
	}
	else{
		return 0;
	}
}

TVectorCalendario TABBCalendario::Inorden() {
	TVectorCalendario vc(Nodos());
	int pos=1;
	InordenAux(vc, pos);
	return vc;
}

TVectorCalendario TABBCalendario::Preorden() {
	TVectorCalendario vc(Nodos());
	int pos=1;
	PreordenAux(vc, pos);

	return vc;
}

TVectorCalendario TABBCalendario::Postorden() {
	TVectorCalendario vc(Nodos());
	int pos=1;
	PostordenAux(vc, pos);
	return vc;
}

/*algoritmo niveles ( a : arbin )
var c: cola de arbin;
aux: arbin;
fvar encolar(c, a)
	mientras no esvacia(c) hacer
		aux := cabeza(c)
		escribe (raiz(aux))
		desencolar(c)
		si no esvacio(hijoiz(aux))
		entonces encolar(c, hijoiz(aux))
		si no esvacio(hijode(aux))
		entonces encolar(c, hijode(aux))
	fmientras
falgoritmo */
TVectorCalendario TABBCalendario::Niveles(){
	TVectorCalendario vc(Nodos());
	TABBCalendario temp(*this);
	TABBCalendario* aux;
	queue<TABBCalendario*> cola;
	int i=1;
	if(!temp.EsVacio()){
		cola.push(&temp);
		while(!cola.empty()){
			aux=cola.front();
			vc[i]=aux->raiz->item;
			i++;
			cola.pop();
			if(!aux->raiz->iz.EsVacio()){
				cola.push(&(aux->raiz->iz));
			}
			if(!aux->raiz->de.EsVacio()){
				cola.push(&(aux->raiz->de));
			}
		}
	}
	return vc;
}

TABBCalendario TABBCalendario::operator +(const TABBCalendario& abbc) {
	TABBCalendario aux(*this);
	TABBCalendario aux1(abbc);
	TVectorCalendario auxder=aux1.Inorden();
	for(int i=0;i< auxder.Tamano();i++){
		aux.Insertar(auxder[i+1]);
	}
	return aux;
}

TABBCalendario TABBCalendario::operator -(const TABBCalendario& abbc) {
	TABBCalendario aux;
	TABBCalendario aux1(abbc);
	TVectorCalendario inorizq=this->Inorden();
	TVectorCalendario inorder=aux1.Inorden();
	for(int i=0;i<inorizq.Tamano();i++){
		if(!inorder.ExisteCal(inorizq[i+1])){
			aux.Insertar(inorizq[i+1]);
		}
	}
	return aux;
}

/**algoritmo inorden ( a : arbin )
	si ( no esvacio( a ) ) entonces

	inorden ( hijoiz ( a ) )
	escribe ( raiz ( a ) )
	inorden ( hijode ( a ) )

	fsi
	falgoritmo */
void TABBCalendario::InordenAux(TVectorCalendario& vc, int& pos) {
	if(!EsVacio()){
		raiz->iz.InordenAux(vc, pos);
		vc[pos]=Raiz();
		pos++;
		raiz->de.InordenAux(vc, pos);
	}
}

/**algoritmo preorden ( a : arbin )
	si ( no esvacio( a ) ) entonces

	escribe ( raiz ( a ) )
	preorden ( hijoiz ( a ) )
	preorden ( hijode ( a ) )

	fsi
	falgoritmo */
void TABBCalendario::PreordenAux(TVectorCalendario& vc, int& pos) {
	if(!EsVacio()){
		vc[pos]=Raiz();
		pos++;
		raiz->iz.PreordenAux(vc, pos);
		raiz->de.PreordenAux(vc, pos);
	}
}

/**algoritmo postden ( a : arbin )
	si ( no esvacio( a ) ) entonces

	postden ( hijoiz ( a ) )
	postden ( hijode ( a ) )
	escribe ( raiz ( a ) )

	fsi
	falgoritmo */
void TABBCalendario::PostordenAux(TVectorCalendario& vc, int& pos) {
	if(!EsVacio()){
		raiz->iz.PostordenAux(vc, pos);
		raiz->de.PostordenAux(vc, pos);
		vc[pos]=Raiz();
		pos++;
	}
}

ostream & operator<<(ostream & o,const TABBCalendario &abbc){
	TABBCalendario auxabbc(abbc);
	TVectorCalendario aux=auxabbc.Niveles();
	o<< aux;
	return o;
}

TListaCalendario TABBCalendario::Aplana() {
	TListaCalendario nueva;
	TVectorCalendario nuevo= Inorden();
	for(int i=0;i<nuevo.Tamano();i++){
		nueva.Insertar(nuevo[i]);
	}
	return nueva;
}

bool TABBCalendario::EsCamino(const TListaCalendario& lp) {
	TListaCalendario aux(lp);
	TListaPos pos;
	bool valor;
	if(!EsVacio()){
		if(aux.EsVacia() && !EsVacio()){
			return true;
		}
		if(aux.Obtener(aux.Primera()) == Raiz()){
			//Si el primer calendario de la lista es igual a la raiz quitamos el calendario de la lista y del arbol y ,miramos el resto
			pos=aux.Primera();
			aux.Borrar(pos);
			valor= EsCamino(lp);
		}
		if(aux.Obtener(aux.Primera()) < Raiz()){
			valor= raiz->iz.EsCamino(lp);
		}
		if(aux.Obtener(aux.Primera()) > Raiz()){
			valor= raiz->de.EsCamino(lp);
		}
		return valor;
	}
	else{
		return false;
	}
}

TListaCalendario TABBCalendario::examen(const TListaCalendario &l) {
	TListaCalendario nuevalista;
	TListaCalendario aux(l);//Lista Auxiliar para que puedan utilizarse los metodos no const
	TListaPos pos;
	pos=aux.Primera();
	if(Buscar(aux.Obtener(pos))){//Si el primer elemento de la lista existe
		if(EsCamino(l)){
			while(aux.EsVacia()){
				pos=aux.Primera();//Obtengo la primera posicion de la lista
				Borrar(aux.Obtener(pos));//Borro el calendario del arbol
				aux.Borrar(pos);//Borro el calendario de la lista
			}
			nuevalista=Aplana();
		}
	}
	else{//Si no existe el primer elemento de la lista quiere decir que no existe el camino
			nuevalista=Aplana();
	}
	return nuevalista;
}

