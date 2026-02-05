# 

# ficheros
    # importante convertir los GB a bytes para que pueda manejar los archivos antes de subir el archivo - esto sucede si he querido verificar el tamaño de un archivo, habrá que validarlo antes de subirlo 

# try-catch-finally
    # en el finally se podría cerrar el archivo tras haberlo subido 

# try-catch con RECURSOS
    # en el try con recursos no habría que poner el finally para que cierre el archivo
        /* 
        * try ([RECURSOS]) {
        *
        * }  catch (Exception e) {
        *
        * } //no hace falta el finally en el de recursos SI ABRO ENTRE LOS PARÉNTESIS X RECURSO ya lo cierra automáticamente .. si a mayores he abierto dentro del try otro recurso SÍ que tendría que poner el finally para cerrarlo
        */