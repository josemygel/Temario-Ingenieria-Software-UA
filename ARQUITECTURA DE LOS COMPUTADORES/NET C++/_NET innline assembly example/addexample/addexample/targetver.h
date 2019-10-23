#pragma once

// Las siguientes macros definen la plataforma mínima requerida. Esta plataforma es la primera
// versión de Windows, Internet Explorer, etc. que tiene las características necesarias para ejecutar 
// la aplicación. Las macros funcionan habilitando todas las características disponibles 
// en las versiones de la plataforma hasta la versión especificada, incluida esta.

// Modifique las siguientes definiciones si tiene que seleccionar como destino una plataforma antes que las especificadas a continuación.
// Consulte la referencia MSDN para obtener la información más reciente sobre los valores correspondientes a las diferentes plataformas.
#ifndef _WIN32_WINNT            // Especifica que la plataforma mínima requerida es Windows Vista.
#define _WIN32_WINNT 0x0600     // Cambiar al valor apropiado correspondiente a otras versiones de Windows.
#endif

