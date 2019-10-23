/*
 * tavlcalendario.cpp
 *
 *  Created on: 5/5/2015
 *      Author: p1
 */

#include "tavlcalendario.h"

//Clase TNodoAVL
TNodoAVL::TNodoAVL() :item(),iz(),de(){
	fe=0;
}

TNodoAVL::TNodoAVL(const TNodoAVL& navl): item(navl.item),iz(navl.iz), de(navl.de) {
	fe=navl.fe;
}

TNodoAVL::~TNodoAVL() {
	fe=0;
}

TNodoAVL& TNodoAVL::operator =(const TNodoAVL& navl) {
	if(this!=&navl){
		(*this).~TNodoAVL();
		item=navl.item;
		iz=navl.iz;
		de=navl.de;
		fe=navl.fe;
	}
	return *this;
}

// Clase TAVLCalendario

TAVLCalendario::TAVLCalendario() {
	raiz=NULL;
}

void TAVLCalendario::copiar(const TAVLCalendario& avlc){
	TNodoAVL* aux=new TNodoAVL();
	if(avlc.raiz!=NULL){
		aux->item=avlc.raiz->item;
		raiz=aux;
		raiz->de.copiar(avlc.raiz->de);
		raiz->iz.copiar(avlc.raiz->iz);
	}
}

TAVLCalendario::TAVLCalendario(const TAVLCalendario& avlc) {
	raiz=NULL;
	copiar(avlc);
}

TAVLCalendario::~TAVLCalendario() {
	if(raiz!=NULL){
		delete raiz;
		raiz=NULL;
	}
	else{
		raiz=NULL;
	}
}

TAVLCalendario& TAVLCalendario::operator =(const TAVLCalendario& avlc) {
	if(this!= &avlc){
		(*this).~TAVLCalendario();
		copiar(avlc);
	}
	return *this;
}

bool TAVLCalendario::operator ==(const TAVLCalendario& avlc) {
	TAVLCalendario aux(avlc);
	if(Inorden() == aux.Inorden()){
			return true;
	}
	else{
		return false;
	}
}

bool TAVLCalendario::operator !=(const TAVLCalendario& avlc) {
	return !(*this==avlc);
}

bool TAVLCalendario::EsVacio() {
	if(raiz==NULL){
		return true;
	}
	else{
		return false;
	}
}

/** ALGORITMO INSERTARAUX
 * ENTRADA/SALIDA I : Iterador;
 *  Crece: Integer; c : Item ;
 *   VAR CreceIz, CreceDe : Integer ;
 *    B : Arbol ;
 * METODO
 *    si EsVacioArbIt ( I ) entonces
 *      B = Enraizar ( c ) ;
 *      Mover ( I, B ) ;
 *      Crece = TRUE ;
 *      sino
 *        Crece = CreceIz = CreceDe = FALSE ;
 *        si ( c < Obtener ( I ) ) entonces
 *           INSERTARAUX ( HijoIzq ( I ), c, CreceIz ) ;
 *           Crece = CreceIz ;
 *        sino
 *           si ( c > Obtener ( I ) ) entonces
 *              INSERTARAUX ( HijoDer ( I ), c, CreceDe ) ;
 *              Crece = CreceDe ;
 *           fsi
 *        fsi
 *     	  si Crece entonces
 *     	     caso de:
 *     	        1) ( CreceIz y FE ( I ) = 1 ) ó ( CreceDe y FE ( I ) = -1 ) :
 *     	            Crece = FALSE ;
 *     	            FE ( I ) = 0 ;
 *     	        2) CreceIz y FE ( I ) = 0 :
 *     	        	FE ( I ) = -1 ;
 *     	        3) CreceDe y FE ( I ) = 0 :
 *     	        	FE ( I ) = 1 ;
 *     	        4) CreceIz y FE ( I ) = -1 :
 *     	        	EquilibrarIzquierda ( I, Crece ) ;
 *     	        5 ) CreceDe y FE ( I ) = 1 :
 *     	        	EquilibrarDerecha ( I, Crece ) ;
 *     	     fcaso
 *     	  fsi
 *      fsi
 * fMETODO */
bool TAVLCalendario::Insertar(const TCalendario& c) {
	bool crece=false;
	bool creceiz=false;
	bool crecede=false;
	if(!Buscar(c)){
		if(EsVacio()){
			TNodoAVL* aux=new TNodoAVL;
			raiz=aux;
			raiz->item=c;
			crece = true;
		}
		else{
			if(Raiz()>c){
				creceiz= raiz->iz.Insertar(c);
				crece=creceiz;
			}
			else{
				if(Raiz()<c){
					crecede= raiz->de.Insertar(c);
					crece=crecede;
				}
			}

			if(crece){
				if((creceiz && raiz->fe==1)||(crecede && raiz->fe==-1)){
					raiz->fe=0;
					return false;
				}else if(creceiz && raiz->fe==0){
					raiz->fe=-1;
				}else if(crecede && raiz->fe==0){
					raiz->fe=1;
				}
				else if(creceiz && raiz->fe==-1){
					crece=(*this).EquilibrarIzquierda();
				}
				else if(crecede && raiz->fe==1){
					crece=EquilibrarDerecha();
				}
			}

		}
		return crece;
	}
	else{
		return false;;
	}
}

TAVLCalendario* TAVLCalendario::MayorArbol() {
	TAVLCalendario* temp=new TAVLCalendario(*this);
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

bool TAVLCalendario::Borrar(const TCalendario& c) {
	TAVLCalendario temp(*this);
	if(Buscar(c)){
		if(Raiz()==c){
			if(raiz->iz.EsVacio() && raiz->de.EsVacio()){//Si es un nodo
				raiz=NULL;
				return true;
			}
			else if(!raiz->iz.EsVacio() && !raiz->de.EsVacio()){
				//Borrar Mayor de la izquierda
				if(temp.raiz->iz.MayorArbol()->raiz->iz.EsVacio()){
					raiz->item=raiz->iz.MayorArbol()->Raiz();
					raiz->iz.MayorArbol()->~TAVLCalendario();
				}
				else{
					raiz->item=raiz->iz.MayorArbol()->Raiz();
				}
			}
			else if(!raiz->iz.EsVacio()){
				raiz=raiz->iz.raiz;

			}
			else if(!raiz->de.EsVacio()){
				raiz=raiz->de.raiz;
			}
		}
		else if(Raiz()<c){
			raiz->de.Borrar(c);
		}
		else{
			raiz->iz.Borrar(c);
		}
		reequilibrarFE();
		return true;
	}
	else{
		return false;
	}
}

bool TAVLCalendario::Buscar(const TCalendario& c) {
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

int TAVLCalendario::Altura() {
	if(EsVacio()){
		return 0;
	}
	else{
		return max(raiz->iz.Altura(), raiz->de.Altura())+1;
	}
}

int TAVLCalendario::Nodos() {
	if(EsVacio()){
		return 0;
	}
	return raiz->de.Nodos()+ raiz->iz.Nodos()+1;
}

int TAVLCalendario::NodosHoja() {
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

TVectorCalendario TAVLCalendario::Inorden() {
	TVectorCalendario vc(Nodos());
	int pos=1;
	InordenAux(vc, pos);
	return vc;
}

TVectorCalendario TAVLCalendario::Preorden() {
	TVectorCalendario vc(Nodos());
	int pos=1;
	PreordenAux(vc, pos);

	return vc;
}

TVectorCalendario TAVLCalendario::Postorden() {
	TVectorCalendario vc(Nodos());
	int pos=1;
	PostordenAux(vc, pos);
	return vc;
}

TCalendario TAVLCalendario::Raiz() {
	if(EsVacio()){
		return TCalendario();
	}
	else{
		return raiz->item;
	}
}

/**algoritmo inorden ( a : arbin )
	si ( no esvacio( a ) ) entonces

	inorden ( hijoiz ( a ) )
	escribe ( raiz ( a ) )
	inorden ( hijode ( a ) )

	fsi
	falgoritmo */
void TAVLCalendario::InordenAux(TVectorCalendario& vc, int& pos) {
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
void TAVLCalendario::PreordenAux(TVectorCalendario& vc, int& pos) {
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
void TAVLCalendario::PostordenAux(TVectorCalendario& vc, int& pos) {
	if(!EsVacio()){
		raiz->iz.PostordenAux(vc, pos);
		raiz->de.PostordenAux(vc, pos);
		vc[pos]=Raiz();
		pos++;
	}
}

ostream & operator<<(ostream & o,const TAVLCalendario &avlc){
	TAVLCalendario auxavlc(avlc);
	TVectorCalendario aux=auxavlc.Inorden();
	o<< aux;
	return o;
}

void TAVLCalendario::Mover(TAVLCalendario& avlci,TAVLCalendario& avlcd) {
	avlci.~TAVLCalendario();
	avlci.raiz=avlcd.raiz;
	avlcd.raiz=NULL;
}

void TAVLCalendario::Mover(TAVLCalendario& avlc, TAVLCalendario*& avlcp) {
	avlc.~TAVLCalendario();
	avlc.raiz=avlcp->raiz;
	avlcp->raiz=NULL;
}

void TAVLCalendario::Mover(TAVLCalendario*& avlcp, TAVLCalendario& avlc) {
	avlcp->raiz=avlc.raiz;
}

void TAVLCalendario::Mover(TAVLCalendario*& avlcpi, TAVLCalendario*& avlcpd) {
	avlcpi->raiz=avlcpd->raiz;
	avlcpd->raiz=NULL;
}

/**
 *   ALGORITMO EQUILIBRARIZQUIERDA
 *   ENTRADA/SALIDA I : Iterador;
 *   Crece: Integer;
 *    VAR J, K: Iterador; int E2;
 *    METODO
 *    si ( FE (HijoIzq (I ) = -1 entonces //ROTACIÓN II
 *    		Mover (J, HijoIzq (I));
 *    		Mover (HijoIzq (I), HijoDer (J));
 *			Mover (HijoDer (J), I);
 *  		FE (J) = 0; FE (HijoDer (J)) = 0;
 *       	Mover (I,J);
 *    sino    //ROTACIÓN ID
 *		    Mover (J, HijoIzq (I));
 *   		 Mover (K, HijoDer (J));
 *    		E2 = FE (K);
 *	    	Mover (HijoIzq (I), HijoDer (K));
 *  	  	Mover (HijoDer (J), HijoIzq (K));
 *    		Mover (HijoIzq (K), J);
 *	    	Mover (HijoDer (K), I);
 *  	  	FE (K) = 0;
 *    		caso de E2
 *		    	-1:
 *  			  	FE (HijoIzq (K)) = 0;
 *  		  		FE (HijoDer (K)) = 1;
 *		    	+1:
 *  			  	FE (HijoIzq (K)) = -1;
 *	    			FE (HijoDer (K)) = 0;
 *  	  		0:
 *  			  	FE (HijoIzq (K)) = 0;
 *  	  			FE (HijoDer (K)) = 0;
 * 	   		fcaso
 *    Mover (I, K);
 *    fsi
 *    Crece = FALSE;
 *    fMETODO
 */
bool TAVLCalendario::EquilibrarIzquierda() {
	TAVLCalendario J, K;
	TAVLCalendario* aux=this;
	int facAux=0;
	if(raiz->iz.raiz->fe==-1 ){ //Rotación II
		Mover(J, raiz->iz);
		Mover(raiz->iz, J.raiz->de);
		Mover(J.raiz->de,aux);
		J.raiz->fe=J.raiz->de.Altura()-J.raiz->iz.Altura();
		J.raiz->de.raiz->fe=J.raiz->de.Altura()-J.raiz->iz.Altura();
		Mover(*this, J);
	}
	else{//Rotación ID
		Mover(J, raiz->iz);
		Mover(K, J.raiz->de);
		facAux=K.raiz->fe;
		Mover(raiz->iz, K.raiz->de);
		Mover(J.raiz->de, K.raiz->iz);
		Mover(K.raiz->iz, J);
		Mover(K.raiz->de, aux);
		K.raiz->fe=K.raiz->de.Altura()-K.raiz->iz.Altura();
		switch(facAux){
		case -1:
			K.raiz->iz.raiz->fe=0;
			K.raiz->de.raiz->fe=1;
			break;
		case 1:
			K.raiz->iz.raiz->fe=-1;
			K.raiz->de.raiz->fe=0;
			break;
		case 0:
			K.raiz->iz.raiz->fe=0;
			K.raiz->de.raiz->fe=0;
			break;
		}
		Mover(*this, K);
	}
	return false;
}

bool TAVLCalendario::EquilibrarDerecha() {
	TAVLCalendario J, K;
	TAVLCalendario* aux=this;
	int facAux=0;
	if(raiz->de.raiz->fe==1 || raiz->de.raiz->fe==0){ //Rotación DD
		Mover(J, raiz->de);
		Mover(raiz->de, J.raiz->iz);
		Mover(J.raiz->iz,aux);
		J.raiz->fe=J.raiz->de.Altura()-J.raiz->iz.Altura();
		J.raiz->iz.raiz->fe=J.raiz->iz.raiz->de.Altura()-J.raiz->iz.raiz->iz.Altura();
		Mover(*this, J);
	}
	else{//Rotación DI
		Mover(J, raiz->de);
		Mover(K, J.raiz->iz);
		facAux=K.raiz->fe;
		Mover(raiz->de, K.raiz->iz);
		Mover(J.raiz->iz, K.raiz->de);
		Mover(K.raiz->de, J);
		Mover(K.raiz->iz, aux);
		K.raiz->fe=K.raiz->de.Altura()-K.raiz->iz.Altura();
		switch(facAux){
		case 1:
			K.raiz->de.raiz->fe=1;
			K.raiz->iz.raiz->fe=0;
			break;
		case -1:
			K.raiz->de.raiz->fe=0;
			K.raiz->iz.raiz->fe=-1;
			break;
		case 0:
			K.raiz->de.raiz->fe=0;
			K.raiz->iz.raiz->fe=0;
			break;
		}
		Mover(*this, K);
	}
	return false;
}

void TAVLCalendario::reequilibrarFE() {
	int aux;
	if(raiz->iz.EsVacio()&& raiz->de.EsVacio()){
		raiz->fe=0;
	}
	else if(!raiz->iz.EsVacio()&& !raiz->de.EsVacio()){
		aux=raiz->de.Altura()-raiz->iz.Altura();
	}
	else if(!raiz->iz.EsVacio()){
		aux=0-raiz->iz.Altura();
	}
	else if(!raiz->de.EsVacio()){
		aux=raiz->de.Altura();
	}

	if(aux==0 || aux==-1 || aux==1){
		raiz->fe=aux;
	}
	else{
		if(aux==-2){
			EquilibrarIzquierda();
		}
		else if(aux==2){
			EquilibrarDerecha();
		}
	}

}
