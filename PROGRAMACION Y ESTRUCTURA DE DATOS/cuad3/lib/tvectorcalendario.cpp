#include "tvectorcalendario.h"
#include <stdio.h>
#include <string.h>

//FORMA CANÓNICA
// Constructor por defecto
TVectorCalendario::TVectorCalendario():error(){
    c = NULL;
    tamanyo = 0;
}
// Constructor a partir de un tamaño
TVectorCalendario::TVectorCalendario(int tam):error(){
    if(tam <= 0){
        c = NULL;
        tamanyo = 0;
    }else{
        tamanyo = tam;
        c = new TCalendario[tam];
    }

}
// Constructor de copia
TVectorCalendario::TVectorCalendario(const TVectorCalendario &vec):error(){
    this->clear();
    tamanyo = vec.Tamano();

    for (int i = 1; i <= tamanyo; i++){
        c[i-1]=vec[i];
    }
}
// Destructor
void
TVectorCalendario::clear(){
	if(c!=NULL)
		delete [] c;
    tamanyo = 0;    
}
// Destructor
TVectorCalendario::~TVectorCalendario(){
	if(c!=NULL)
		delete [] c;
    tamanyo = 0;    
}
// Sobrecarga del operador asignación
TVectorCalendario& 
TVectorCalendario::operator=(const TVectorCalendario &vec){
    
    this->~TVectorCalendario();
    tamanyo = vec.Tamano();
    c = new TCalendario[tamanyo]();
    
    for (int i = 1; i <= tamanyo; i++){
        c[i-1]=vec[i];
    }
}

//MÉTODOS
// Sobrecarga del operador igualdad
bool 
TVectorCalendario::operator==(const TVectorCalendario &vec)const{
    if(tamanyo != vec.Tamano())
        return false;

    for (int i = 1; i <= tamanyo; i++){
        if(c[i-1]!=vec[i])
            return false;
    }

    return true;
}
// Sobrecarga del operador desigualdad
bool 
TVectorCalendario::operator!=(const TVectorCalendario &vec)const{
    return !this->operator==(vec);
}
// Sobrecarga del operador corchete (parte IZQUIERDA) vector[num] = ...
TCalendario& 
TVectorCalendario::operator[](int num){
    if(num<=0)
        return error;
    return c!=NULL ? c[num-1] : error;
}
// Sobrecarga del operador corchete (parte DERECHA)   ... = vector[num];
TCalendario 
TVectorCalendario::operator[](int num) const{
    if(num<=0)
        return error;
    return c[num-1];
}
// Tamaño del vector (posiciones TOTALES)
int 
TVectorCalendario::Tamano() const{
    return tamanyo;
}
// Cantidad de posiciones OCUPADAS (no vacías) en el vector
int 
TVectorCalendario::Ocupadas() const{
    int count = 0;

    for (int i = 0; i < tamanyo; i++){
        if (!c[i].EsVacio())
            count++;
    }
    
    return count;
}
// Devuelve true si existe el calendario en el vector
bool 
TVectorCalendario::ExisteCal(const TCalendario &cal) const{
    for (int i = 1; i <= tamanyo; i++){
        if (c[i-1]==cal)
            return true;
    }
    return false;
}
// Mostrar por pantalla mensajes de TCalendario en el vector, de fecha IGUAL O POSTERIOR a la pasada
void 
TVectorCalendario::MostrarMensajes(int d,int m,int a) const{
    TCalendario* lot;
    TCalendario arg(d,m,a,NULL);
    int count = 0;

    for (int i = 1; i <= tamanyo; i++) {
        TCalendario aux = c[i-1];
        if(aux.Dia()==d && aux.Mes()==m && aux.Anyo()==a){
            count++;
        } else if (aux>arg) {
            count++;
        }
    }

    lot = new TCalendario[count]();
    count = 0;

    for (int i = 1; i <= tamanyo; i++) {
        TCalendario aux = c[i-1];
        if(aux.Dia()==d && aux.Mes()==m && aux.Anyo()==a){
            lot[count++]=aux;
        } else if (aux>arg) {
            lot[count++]=aux;
        }
    }

    bool init = false;
    std::cout << "[";
    for (int i = 0; i < count; i++){
        if (init){
            std::cout << ", ";
        } else {
            init = true;
        }
            std::cout << lot[i];
    }
    std::cout << "]";
}
// REDIMENSIONAR el vectorde TCalendario
bool 
TVectorCalendario::Redimensionar(int tam){
    if (tam == tamanyo || tam <= 0)
        return false;
        
    TCalendario* aux = new TCalendario[tam](); 
    int size;

    if(tam > tamanyo){
        size = tamanyo;
    }else{
        size = tam;
    }

    for (int i = 0; i < size; i++){
        aux[i] = c[i];
    }

    this->~TVectorCalendario();
    tamanyo=tam;
    c=aux;
    
    return true;
}

//FUNCIONES AMIGAS
// Sobrecarga del operador salida
std::ostream& 
operator<<(std::ostream &os, const TVectorCalendario &cal){
    os << "[";
    
    for (int i = 1; i <= cal.Tamano(); i++){
        os << "(" << i << ") ";

        if(i == cal.Tamano())
            os << cal[i];
        else
            os << cal[i] << ", ";
    }

    os << "]";

    return os;
}
