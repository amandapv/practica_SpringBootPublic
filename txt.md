//inyección SQL: Usar un parametro de la query para inyectar con ese parametro código SQL - Ej: bobby drop tables SQL
  //en una caja de texto en el que se supone que solo se introduce el nombre de una persona a buscar, hicieras: ROBERT'); DROP TABLE Students;-- Esto borraría la tabla 
  // para lo anterior tendría que ser administrador

    //Las consultas parametrizadas, al pasar de forma separada el valor de entrada y la query SIRVEN PARA EVITAR LA INYECCIÓN SQL, también mejora la eficiencia (la fase de análisis solo se ejecuta una vez).

// para codigo mas eficiente, se podría pasar en un .csv o .txt o ficheros de parámetros la sentencia SQL a hacer y los datos que se quieren usar serán variables
  //ejemplo: en unas rebajas del Corte Inglés para hacer un 'UPDATE precio FROM articulos WHERE cod_art=xxx' esa QUERY se pasaría, por ejemplo, en un CSV 


//Como funcionan las consultas parametrizadas:
  //1. @GetMapping - presenta el formulario
  //2. Usuario escribe datos - el de la consulta parametrizada
  //3. @PostMapping - recibe el parámetro del formulario - llama al servicio/s, que contiene la consulta a BBDD

//se puede lanzar una excepción (throws) sin decirle throw new, si no se usa un throw se puede poner un .orElse() que si no se cumple lanza eso.