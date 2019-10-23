#CMAKELISTS.txt
#Project define el nombre que tendra el proyecto/ejecutable
cmake_minimum_required(VERSION 3.12)
project (recreg)
#Declaramos los recuros a utilizar
#set(recursos )
#Añadimos el nombre del ejecutable que procede del package_source
add_executable ( recreg recta_regresion_paralela.cpp )

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O3 -fopenmp")
#set(CMAKE_CXX_FLAGS "-O3 -fopenmp")

#Añadimos el GENERADOR DE RECURSOS de cpack
set(CPACK_SOURCE_GENERATOR TGZ)
#Añadimos el GENERADOR de CPACK
set(CPACK_GENERATOR TGZ)

SET(package_source distribuir.tgz)

add_custom_target(dist COMMAND ${CMAKE_MAKE_PROGRAM} package_source)
include(CPack)
