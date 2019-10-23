#!/bin/bash

fallos=0;

dir="../Cuad2"

echo "ABB PROFESORES ..." 

for tad in `ls tads/abbp`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall tads/abbp/${tad} ${dir}/lib/tcomplejo.cpp ${dir}/lib/tvectorcom.cpp ${dir}/lib/tlistacom.cpp ${dir}/lib/tabbcom.cpp -I./${dir}/include -o main 
  ./main > salida/abbp/${tad}.sal
 
  comp=$(diff -b salida/abbp/${tad}.sal salida_ok/abbp/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "OK"
	fi

	echo ""
done

echo "NUM. FALLOS: $fallos";

echo "ABB ..." 

for tad in `ls tads/abb`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall tads/abb/${tad} ${dir}/lib/tcomplejo.cpp ${dir}/lib/tvectorcom.cpp ${dir}/lib/tlistacom.cpp ${dir}/lib/tabbcom.cpp -I./${dir}/include -o main 
  ./main > salida/abb/${tad}.sal
 
  comp=$(diff -b salida/abb/${tad}.sal salida_ok/abb/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "OK"
	fi

	echo ""
done

echo "NUM. FALLOS: $fallos";

echo "Complejo ..." 

for tad in `ls tads/complejo`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall tads/complejo/${tad} ${dir}/lib/tcomplejo.cpp ${dir}/lib/tvectorcom.cpp ${dir}/lib/tlistacom.cpp -I./${dir}/include -o main 
  ./main > salida/complejo/${tad}.sal
 
  comp=$(diff -b salida/complejo/${tad}.sal salida_ok/complejo/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "OK"
	fi

	echo ""
done

echo "NUM. FALLOS: $fallos";

echo "Vector ..." 

for tad in `ls tads/vector`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall tads/vector/${tad} ${dir}/lib/tcomplejo.cpp ${dir}/lib/tvectorcom.cpp ${dir}/lib/tlistacom.cpp -I./${dir}/include -o main 
  ./main > salida/vector/${tad}.sal
 
  comp=$(diff -b salida/vector/${tad}.sal salida_ok/vector/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "OK"
	fi

	echo ""
done

echo "NUM. FALLOS: $fallos";

echo "Lista ..." 

for tad in `ls tads/lista`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall tads/lista/${tad} ${dir}/lib/tcomplejo.cpp ${dir}/lib/tvectorcom.cpp ${dir}/lib/tlistacom.cpp -I./${dir}/include -o main 
  ./main > salida/lista/${tad}.sal
 
  comp=$(diff -b salida/lista/${tad}.sal salida_ok/lista/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "OK"
	fi

	echo ""
done

echo "NUM. FALLOS: $fallos";
