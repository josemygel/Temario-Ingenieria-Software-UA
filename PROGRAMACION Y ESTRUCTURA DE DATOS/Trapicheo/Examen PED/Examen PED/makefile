.PHONY= doc clean

CC=g++
OPTIONS= -g
EXE= tad01
SRC= src/$(EXE).cpp
DEBUG= #-D DEBUG
LIBDIR=lib
INCLUDEDIR=include
_OBJ= tcalendario.o tvectorcalendario.o tlistacalendario.o tabbcalendario.o tavlcalendario.o
OBJ = $(patsubst %,$(LIBDIR)/%,$(_OBJ))

tad:    $(SRC) $(OBJ)
	$(CC) $(OPTIONS) $(DEBUG) -I$(INCLUDEDIR) $(SRC) $(OBJ) -o $(EXE)

$(LIBDIR)/%.o : $(LIBDIR)/%.cpp $(INCLUDEDIR)/%.h
	$(CC) $(OPTIONS) $(DEBUG) -c -I$(INCLUDEDIR) -o $@ $<

doc:
	doxygen

clean:
	rm -f $(OBJ) 
