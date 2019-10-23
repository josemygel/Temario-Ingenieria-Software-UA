#!/bin/bash

fallos=0;

dir="../Cuad2"

echo "ABB 1 ..." 

for tad in `ls tads/abbp2`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ tads/abbp2/${tad} ${dir}/lib/tcomplejo.cpp ${dir}/lib/tvectorcom.cpp ${dir}/lib/tlistacom.cpp ${dir}/lib/tabbcom.cpp -I./${dir}/include -o main 
  ./main > salida/abbp2/${tad}.sal
 
  comp=$(diff -b salida/abbp2/${tad}.sal salida_ok/abbp2/${tad}.sal)
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
echo "ABB 2 ..." 

for tad in `ls tads/abbp3`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ tads/abbp3/${tad} ${dir}/lib/tcomplejo.cpp ${dir}/lib/tvectorcom.cpp ${dir}/lib/tlistacom.cpp ${dir}/lib/tabbcom.cpp -I./${dir}/include -o main 
  ./main > salida/abbp3/${tad}.sal
 
  comp=$(diff -b salida/abbp3/${tad}.sal salida_ok/abbp3/${tad}.sal)
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
