/*
 * tvectorcalendario.cpp
 *
 *  Created on: 25/2/2015
 *      Author: Alejandro Reyes Albillar
 */

#include "tvectorcalendario.h"

TVectorCalendario::TVectorCalendario():error() {
	tamano=0;
	c=NULL;
}

TVectorCalendario::TVectorCalendario(int int1):error() {
	if(int1<1){
		tamano=0;
		c=NULL;
	}
	else{
		tamano=int1;
		c = new TCalendario[tamano];
	}
}

TVectorCalendario::TVectorCalendario(const TVectorCalendario& v):error(v.error) {
	if(v.tamano<1){
		tamano=0;
		c=NULL;
	}
	else{
		tamano=v.tamano;
		if(tamano==0){
			c=NULL;
		}
		else{
			c=new TCalendario[tamano];
			for (int i=0; i<tamano;i++){
				c[i]=v[i+1];
			}
		}
	}
}

TVectorCalendario::~TVectorCalendario() {
	if(c!=NULL){
		delete[] c;
		c=NULL;
	}

}

//La asignación es igual al constructor de copia pero destruyendo el actual y sobreescribiendolo
TVectorCalendario& TVectorCalendario::operator =(const TVectorCalendario& v) {
	if(this!= &v){
			(*this).~TVectorCalendario();
			if(v.tamano<1){
				tamano=0;
				c=NULL;
			}
			else{
				tamano=v.tamano;
				if(tamano==0){
					c=NULL;
				}
				else{
					c=new TCalendario[tamano];
					for (int i=0; i<tamano;i++){
						c[i]=v[i+1];
					}
				}
			}
	}
			return *this;
}

//Dos vectores serán iguales si tienen el mismo tamaño y los mismos elementos en las mismas posiciones
bool TVectorCalendario::operator ==(const TVectorCalendario& v) {
	if(tamano==v.tamano){
		for(int i=0;i<tamano;i++){
			if(c[i]!=v.c[i]){
				return false;
			}
		}
		return true;
	}
	else{
		return false;
	}
}

bool TVectorCalendario::operator !=(const TVectorCalendario& v) {
	return !(*this==v);
}

//No se invoca al cnstructor de copia
TCalendario& TVectorCalendario::operator [](int int1) {//Es parte izquierda
	if(int1<1 || int1>tamano){
		return error;
	}
	else{
		return c[int1-1];
	}
}

//Se crea un constructor de copia sin modificar el contenido del vector
TCalendario TVectorCalendario::operator [](int int1) const {//Es parte derecha
	if(int1<1 || int1>tamano){
		return error;
	}
	else{
		return c[int1-1];
	}
}

//Devuelve el tamaño del vector
int TVectorCalendario::Tamano() {
	return tamano;
}

//Devuelve el número de posiciones que devuelven false cuando se les pasa el método EsVacio() de TCalendario
int TVectorCalendario::Ocupadas() {
	int ocupadas=0;
	for(int i=0;i<tamano;i++){
		if(!c[i].EsVacio()){
			ocupadas++;
		}
	}
	return ocupadas;
}

//Devuelve true si existe un calendario en TVectorCalendario, es decir si c no es null
bool TVectorCalendario::ExisteCal(TCalendario& cal) {
	for(int i=0;i<tamano;i++){
		if(c[i]==cal){
			return true;
		}
	}
	return false;
}

//Se muestran las fechas iguales o posteriores que se encuentran en el vector
void TVectorCalendario::MostrarMensajes(int dia, int mes, int anyo) {
	int count=0;
	if(!compruebaFecha(dia, mes, anyo)){
		cout << "[]";
	}
	else{
		cout<<"[";
		for(int i=0; i<=tamano; i++){
			if(c[i].Anyo()> anyo){
				if(count>0){
					cout<< ", ";
				}
				cout<< c[i];
				count++;
			}
			else if(c[i].Anyo()==anyo){
				if(c[i].Mes()> mes){
					if(count>0){
						cout<< ", ";
					}
					cout<< c[i];
					count++;
				}
				else if(c[i].Mes()==mes){
					if(c[i].Dia()> dia){
						if(count>0){
							cout<< ", ";
						}
						cout<< c[i];
						count++;
					}
					else if(c[i].Dia()==dia){
						if(count>0){
							cout<< ", ";
						}
						cout<< c[i];
						count++;
					}
				}
			}
		}
		cout<<"]";
	}

}

bool TVectorCalendario::Redimensionar(int int1) {
	if(int1<=0 || int1==tamano){
		return false;
	}
	else{
		TCalendario *q= new TCalendario[int1];
		if(tamano>int1){
			for(int i=0;i<int1;i++){
				q[i]=c[i];
			}
		}
		else{
			for(int i=0;i<tamano;i++){
				q[i]=c[i];
			}
		}
		delete[] c;
		c=q;
		tamano=int1;
		return true;
	}
}

ostream & operator<<(ostream &o,const TVectorCalendario & v){
	if(v.tamano<=0){
		o<<"[]";
	}
	else{
		o<<"[";
		for(int i=1; i<=v.tamano;i++){
			if(i<=1){
				o<<"("<<i<<") "<< v[i];
			}
			else{
				o<<", ("<<i<<") "<< v[i];
			}
		}
		o<<"]";
	}
	return o;
}

//Definición de año bisiesto, copy/paste de TCalendario por ser funcion privada
bool TVectorCalendario::bisiesto(int anyo){
	//control de años bisiestos (es decir: los años que sean divisibles por 4 serán bisiestos;
	//aunque no serán bisiestos si son divisibles entre 100 (como los años 1700, 1800, 1900 y 2100)
	//a no ser que sean divisibles por 400 (como los años 1600, 2000 ó 2400).
	if (anyo % 4 == 0) {
		if (anyo % 100 == 0) {
			if (anyo % 400 == 0) {
				return true;
			}
			return false;
		}
		return true;
	}
	else{
		return false;
	}
}

//Definición de fecha correcta, copy/paste de TCalendario por ser funcion privada
bool TVectorCalendario::compruebaFecha(int dia, int mes, int anyo) {
	//Comprobar que los datos sean correctos
	if(anyo>=1900 && mes >=1 && dia>=1 && dia<=31 && mes<=12){
		switch(mes){
		case 2://Febrero
			if(!bisiesto(anyo) && dia<=28){
				return true;
			}
			else if(bisiesto(anyo) && dia<=29){
				return true;
			}
			else
				return false;
			break;
		case 11://Noviembre
			if (dia <= 30) {
				return true;
			}
			else
				return false;
			break;
		case 4 ://Abril
			if (dia <= 30) {
				return true;
			}
			else
				return false;
			break;
		case 6://Junio
			if (dia <= 30) {
				return true;
			}
			else
				return false;
			break;
		case 9: //Septiembre
			if (dia <= 30) {
				return true;
			}
			else
				return false;
		break;

		default:
			if (dia <= 31) {
				return true;
			}
			else
				return false;
			break;
		}

	}
	else{
		return false;
	}

}

