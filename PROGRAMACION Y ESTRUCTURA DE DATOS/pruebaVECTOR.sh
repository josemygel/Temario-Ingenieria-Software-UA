#!/bin/bash

fallos=0;
aciertos=0;

dir="./cuad3"

echo "====VECTOR===="


for tad in `ls tads/vector`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/vector/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp -I./${dir}/include -o main 
  ./main > salida/vector/${tad}.sal
 
  comp=$(diff -b salida/vector/${tad}.sal salida_ok/vector/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "=>ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "=>OK"
    let aciertos++;
	fi

	echo ""
done

for tad in `ls tads/vector_`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/vector_/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp -I./${dir}/include -o main 
  ./main > salida/vector_/${tad}.sal
 
  comp=$(diff -b salida/vector_/${tad}.sal salida_ok/vector_/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "=>ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "=>OK"
    let aciertos++;
	fi

	echo ""
done

echo "====VECTOR====";
echo "ACIERTOS: $aciertos";
echo "FALLOS: $fallos";
