/*
 * TCalendario.cpp
 *
 *  Created on: 3/2/2015
 *      Author: alejandro
 */

#include "tcalendario.h"

TCalendario::TCalendario() {
	this->dia = 1;
	this->mes = 1;
	this->anyo = 1990;
	this->mensaje = NULL;
}

TCalendario::TCalendario(int dia, int mes, int anyo, char* mens) {
	//Comprobar que los datos sean correctos
	if(anyo>=1990){
		this->anyo = anyo;
		if (mens != NULL) {
			this->mensaje = new char[strlen(mens) + 1];
			strcpy(mensaje, mens);
		} else {
			mensaje = NULL;
		}

		if(compruebaFecha(dia, mes, anyo)){
			this->dia=dia;
			this->mes=mes;
			this->anyo=anyo;
		}
		else{
			this->dia = 1;
			this->mes = 1;
			this->anyo = 1990;
			this->mensaje = NULL;
		}

	}
	else{
		this->dia = 1;
		this->mes = 1;
		this->anyo = 1990;
		this->mensaje = NULL;
	}
}

TCalendario::TCalendario(TCalendario& c) {
	this->dia = c.dia;
	this->mes = c.mes;
	this->anyo = c.anyo;
	if (c.mensaje != NULL) {
		this->mensaje = new char[strlen(c.mensaje) + 1];
		strcpy(this->mensaje, c.mensaje);
	} else {
		this->mensaje = NULL;
	}
}

TCalendario::~TCalendario() {
	this->dia = 1;
	this->mes = 1;
	this->anyo = 1990;
	if (mensaje != NULL) {
		delete[] mensaje;
	} else {
		mensaje = NULL;
	}
}

TCalendario& TCalendario::operator =(const TCalendario& c) {
	if(this!= &c){
		(*this).~TCalendario();
		dia=c.dia;
		mes=c.mes;
		anyo=c.anyo;
		mensaje=c.mensaje;
	}
		return *this;

}

TCalendario TCalendario::operator +(int int1) {
	TCalendario temp;
	if(int1>=0){
		while(int1!=0){
			temp++;
			int1--;
		}
	}
	return temp;
}

TCalendario TCalendario::operator -(int int1) {
	TCalendario temp;
	while(int1!=0){
		temp--;
		int1--;
	}

	if(!compruebaFecha(dia, mes, anyo)){
		temp.dia = 1;
		temp.mes = 1;
		temp.anyo = 1990;
		temp.mensaje = NULL;
	}
	return temp;
}

TCalendario TCalendario::operator ++(int int1) {//Postincremento a++
	TCalendario temp(*this);
	switch(mes){
		case 2://Febrero
			if(dia==29 && bisiesto()){
				mes++;
				dia=1;
			}
			else if(dia==28 && !bisiesto()){
				mes++;
				dia=1;
			}
			else{
				dia++;
			}
			break;
		case 4://Abril
			if(dia==30){
				mes++;
				dia=1;
			}
			break;
		case 6://Junio
			if(dia==30){
				mes++;
				dia=1;
			}
			else{
				dia++;
			}
			break;
		case 9://Septiembre
			if(dia==30){
				mes++;
				dia=1;
			}
			else{
				dia++;
			}
			break;
		case 11://Noviembre
			if(dia==30){
				mes++;
				dia=1;
			}
			else{
				dia++;
			}
			break;
		default:
			if(dia==31){
				if(mes==12){
					dia=1;
					mes=1;
					anyo++;
				}
				else{
					mes++;
					dia=1;
				}
			}
			else{dia++;}
	}
	return temp;
}

TCalendario& TCalendario::operator ++() { //Preincremento ++a
	switch(mes){
	case 2://Febrero
		if(dia==29 && bisiesto()){
			mes++;
			dia=1;
		}
		else if(dia==28 && !bisiesto()){
			mes++;
			dia=1;
		}
		else{
			dia++;
		}
		break;
	case 4://Abril
		if(dia==30){
			mes++;
			dia=1;
		}
		else{
			dia++;
		}
		break;
	case 6://Junio
		if(dia==30){
			mes++;
			dia=1;
		}
		else{
			dia++;
		}
		break;
	case 9://Septiembre
		if(dia==30){
			mes++;
			dia=1;
		}
		else{
			dia++;
		}
		break;
	case 11://Noviembre
		if(dia==30){
			mes++;
			dia=1;
		}
		else{
			dia++;
		}
		break;
	default:
		if(dia==31){
			if(mes==12){
				dia=1;
				mes=1;
				anyo++;
			}
			else{
				mes++;
				dia=1;
			}
		}
		else{
			dia++;
		}
	}
	return *this;
}

TCalendario TCalendario::operator --(int int1) { //Postdercemento a--
	TCalendario temp(*this);
	switch(mes){
			case 3://Marzo
				if(dia==1 && bisiesto()){
					mes--;
					dia=29;
				}
				else if(dia==1 && !bisiesto()){
					mes--;
					dia=28;
				}
				else{
					dia--;
				}
				break;
			case 5://Mayo
				if(dia==1){
					mes--;
					dia=30;
				}
				else{
					dia--;
				}
				break;
			case 7://Julio
				if(dia==1){
					mes--;
					dia=30;
				}
				else{
					dia--;
				}
				break;
			case 10://Octubre
				if(dia==1){
					mes--;
					dia=30;
				}
				else{
					dia--;
				}
				break;
			case 12://Diciembre
				if(dia==1){
					mes--;
					dia=30;
				}
				else{
					dia--;
				}
				break;
			default:
				if(dia==1){
					if(mes==1){
						dia=31;
						mes=12;
						anyo--;
					}
					else{
						mes--;
						dia=31;
					}
				}
				else{
					dia--;
				}
				break;
		}

		if(!compruebaFecha(dia, mes, anyo)){
			temp.dia = 1;
			temp.mes = 1;
			temp.anyo = 1990;
			temp.mensaje = NULL;
		}
		return temp;
}

TCalendario& TCalendario::operator --() { //Predecremento --a
	switch(mes){
		case 3://Marzo
			if(dia==1 && bisiesto()){
				mes--;
				dia=29;
			}
			else if(dia==1 && !bisiesto()){
				mes--;
				dia=28;
			}
			else{
				dia--;
			}
			break;
		case 5://Mayo
			if(dia==1){
				mes--;
				dia=30;
			}
			else{
				dia--;
			}
			break;
		case 7://Julio
			if(dia==1){
				mes--;
				dia=30;
			}
			else{
				dia--;
			}
			break;
		case 10://Octubre
			if(dia==1){
				mes--;
				dia=30;
			}
			else{
				dia--;
			}
			break;
		case 12://Diciembre
			if(dia==1){
				mes--;
				dia=30;
			}
			else{
				dia--;
			}
			break;
		default:
			if(dia==1){
				if(mes==1){
					dia=31;
					mes=12;
					anyo--;
				}
				else{
					mes--;
					dia=31;
				}
			}
			else{
				dia--;
			}
			break;
	}

	if(!compruebaFecha(dia, mes, anyo)){
		dia = 1;
		mes = 1;
		anyo = 1990;
		mensaje = NULL;
	}

	return *this;
}

bool TCalendario::ModFecha(int dia, int mes, int anyo) {
	if(compruebaFecha(dia, mes, anyo)){
		return true;
		this->dia=dia;
		this->mes=mes;
		this->anyo=anyo;
	}
	else
		return false;
}

bool TCalendario::ModMensaje(char* mens) {
	if (mens != NULL) {
		this->mensaje = new char[strlen(mens) + 1];
		strcpy(mensaje, mens);
	} else {
		mensaje = NULL;
	}
	return true;
}

bool TCalendario::operator ==(TCalendario& c) {
	if(this->dia==c.dia && this->mes==c.mes && this->anyo==c.anyo){
		if(this->mensaje==NULL && c.mensaje==NULL){
			return true;
		}
		else if(strcmp(this->mensaje, c.mensaje)==0){
			return true;
		}
		else
			return false;
	}
	else
		return false;
}

bool TCalendario::operator !=(TCalendario& c) {
	if(this->dia!=c.dia || this->mes!=c.mes || this->anyo!=c.anyo){
		if(this->mensaje==NULL && c.mensaje==NULL){
			return false;
		}
		else if(strcmp(this->mensaje, c.mensaje)==0){
			return false;
		}
		else
			return true;
	}
	else
		return false;
}

bool TCalendario::operator >(TCalendario& c) {
	if(anyo>c.anyo){
			return true;
	}
	else{
		if(anyo==c.anyo){
			if(mes>c.mes){
					return true;
			}
			else{
				if(mes==c.mes){
					if(dia>c.dia){
						return true;
					}
					else{
						if(dia==c.dia){
							if(mensaje==NULL && c.mensaje==NULL){//NULL>NULL-->FALSE
								return false;
							}
							else if(mensaje==NULL){//NULL>X-->FALSE
								return false;
							}
							else if(c.mensaje==NULL){//X>NULL-->TRUE
								return true;
							}
							else{
								if(strcmp(mensaje, c.mensaje)==0){//X>X-->FALSE
									return false;
								}
								else if(strcmp(mensaje, c.mensaje)>0){//X>Y-->TRUE
									return true;
								}
								else{//Y>X-->FALSE
									return false;
								}

							}
						}
						return false;
					}
				}
			return false;
			}
		}
		return false;
	}
}

bool TCalendario::operator <(TCalendario& c) {
	if(anyo<c.anyo){
				return true;
		}
		else{
			if(anyo==c.anyo){
				if(mes<c.mes){
						return true;
				}
				else{
					if(mes==c.mes){
						if(dia<c.dia){
							return true;
						}
						else{
							if(dia==c.dia){
								if(mensaje==NULL && c.mensaje==NULL){//NULL<NULL-->FALSE
									return false;
								}
								else if(mensaje==NULL){//NULL<X-->TRUE
									return true;
								}
								else if(c.mensaje==NULL){//X<NULL-->FALSE
									return false;
								}
								else{
									if(strcmp(mensaje, c.mensaje)==0){//X<X-->FALSE
										return false;
									}
									else if(strcmp(mensaje, c.mensaje)<0){//X<Y-->TRUE
										return true;
									}
									else{//Y<X-->FALSE
										return false;
									}

								}
							}
							return false;
						}
					}
					return false;
				}
			}
			return false;
		}
}

bool TCalendario::EsVacio() {
	if (dia == 1 && mes == 1 && anyo == 1900 && mensaje == NULL) {
		return true;
	}
	return false;
}

bool TCalendario::bisiesto() {
	//control de años bisiestos (es decir: los años que sean divisibles por 4 serán bisiestos;
	//aunque no serán bisiestos si son divisibles entre 100 (como los años 1700, 1800, 1900 y 2100)
	//a no ser que sean divisibles por 400 (como los años 1600, 2000 ó 2400) .
	if (anyo % 4 == 0) {
		if (anyo % 100 == 0) {
			if (anyo % 400 == 0) {
				return true;
			}
			return false;
		}
		return true;
	}
	return false;
}

bool TCalendario::compruebaFecha(int dia, int mes, int anyo) {
	//Comprobar que los datos sean correctos
	if(anyo>=1990 && mes >=1 && dia>=1 && dia<=31 && mes<=12){
		switch(mes){
		case 2://Febrero
			if(bisiesto() && dia<=29){
				return true;
			}
			else if(!bisiesto() && dia<=28){
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

ostream & operator<<(ostream & o,const TCalendario &c){
	if(c.dia<10){
		if(c.mes<10){
			if(c.mensaje==NULL){
				o <<"0"<<c.dia<<"/0"<<c.mes<<"/"<<c.anyo<< " \"\"";
			}
			else{
				o <<"0"<<c.dia<<"/0"<<c.mes<<"/"<<c.anyo<< " "<<c.mensaje;
			}
		}
		else if(c.mensaje==NULL){
			o <<"0"<<c.dia<<"/"<<c.mes<<"/"<<c.anyo<< " \"\"";
		}
		else{
			o <<"0"<<c.dia<<"/"<<c.mes<<"/"<<c.anyo<< " "<<c.mensaje;
		}
	}
	else if(c.mes<10){
		if(c.mensaje==NULL){
			o <<c.dia<<"/0"<<c.mes<<"/"<<c.anyo<< " \"\"";
		}
		else{
			o <<c.dia<<"/0"<<c.mes<<"/"<<c.anyo<< " "<<c.mensaje;
		}
	}
	else{
		if(c.mensaje==NULL){
			o <<c.dia<<"/"<<c.mes<<"/"<<c.anyo<< " \"\"";
		}
		else{
			o <<c.dia<<"/"<<c.mes<<"/"<<c.anyo<< " "<<c.mensaje;
		}
	}
	return o;

}
