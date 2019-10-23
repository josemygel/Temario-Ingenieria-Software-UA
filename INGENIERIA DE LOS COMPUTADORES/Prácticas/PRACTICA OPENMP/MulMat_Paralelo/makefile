#Esto sirve para que al poner make clean elimine los ejecutables
.PHONY= clean

#Compilador a utilizar
CC= g++

#Opciones adicionales
OPTIONS= -std=c++11

#Para añadir optimizadores
OPTIMIZERS= -O3 -faggressive-loop-optimizations -floop-block -floop-parallelize-all

#Modo DEBUG, para omitirlo añade # a la linea
DEBUG= -D DEBUG

#Para el main
FILEMAIN= main.c
OBJMAIN= main.o
OPTOBJMAIN= mainOPT.o

#Para el multiTest
FILETEST= multiTest.c
OBJTEST= multiTest.o
OPTOBJTEST= multiTestOPT.o

#Compilaremos estos dos y automaticamente eliminaremos los archivos .o
all: main mainOPT multiTest multiTestOPT
	rm -f *.o

#Creamos el mainOPT.o
$(OBJMAIN): $(FILEMAIN)
	$(CC) $(DEBUG) $(OPTIONS) -c $(FILEMAIN) -o $(OBJMAIN)

$(OPTOBJMAIN): $(FILEMAIN)
	$(CC) $(OPTIMIZERS) $(DEBUG) $(OPTIONS) -c $(FILEMAIN) -o $(OPTOBJMAIN)

#Creamos el multiTestOPT.c
$(OBJTEST): $(FILETEST)
	$(CC) $(DEBUG) $(OPTIONS) -c $(FILETEST) -o $(OBJTEST)

$(OPTOBJTEST): $(FILETEST)
	$(CC) $(OPTIMIZERS) $(DEBUG) $(OPTIONS) -c $(FILETEST) -o $(OPTOBJTEST)
run:
	time (./multiTest > resultadoSinOptimizaciones.txt)
	time (./multiTestOPT > resultadoConOptimizaciones.txt)

clean:
	rm -f multiTest main multiTestOPT mainOPT