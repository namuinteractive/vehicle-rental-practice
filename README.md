# Sistema de Gestión de Biblioteca

Este proyecto es una aplicación de gestión de biblioteca desarrollada en Java. Permite administrar información sobre bibliotecarios, estudiantes, libros y préstamos.

## Características Principales

* Gestión de bibliotecarios: creación, consulta y modificación de información de los bibliotecarios.
* Gestión de estudiantes: creación, consulta y modificación de información de los estudiantes.
* Gestión de libros: creación, consulta, modificación y reemplazo de información de los libros.
* Gestión de préstamos: creación, consulta, entrega y seguimiento de préstamos de libros.
* Generación de reportes:
    * Estudiante con más préstamos.
    * Total de dinero recaudado por préstamos.
    * Total a pagar a los bibliotecarios.

## Instrucciones de Instalación

1.  Asegúrate de tener Java Development Kit (JDK) instalado en tu sistema.
2.  Descarga el código fuente del proyecto.
3.  Compila el código fuente utilizando un IDE de Java o desde la línea de comandos.
4.  Crea una carpeta llamada `myUQResources` en tu directorio home.
5.  Ejecuta la aplicación.

## Instrucciones de Uso

1.  **Inicio de Sesión:**
    * En la pantalla de inicio de sesión, ingresa la cédula del bibliotecario como usuario y su número de teléfono como contraseña.
    * El usuario más facil de acceder es:
    * - Usuario: 123456789
    * - Contraseña: 987654321
2.  **Menú Principal:**
    * Una vez iniciada la sesión, se mostrará el menú principal con las siguientes opciones:
        * Crear Bibliotecario
        * Crear Estudiante
        * Administrar Libros
        * Administrar Préstamos
        * Generar Reportes
        * Salir
3.  **Navegación:**
    * Utiliza los botones para navegar entre las diferentes opciones del menú y realizar las acciones deseadas.

## Requisitos del Sistema

*   Java Development Kit (JDK) 8 o superior.

## Seguridad

*   Los datos de la biblioteca se almacenan en archivos JSON encriptados utilizando AES.
*   La clave de encriptación se encuentra en el archivo `JsonManager.java`.

## Notas

*   El programa utiliza la librería Lanterna para la interfaz de usuario y Gson para la gestión de datos JSON.
*   Se incluye un script Python `cipher.py` para encriptar y desencriptar los archivos JSON (ver `additional_resources/CIPHER.md` para más información).

## Licencia

*   Este proyecto está licenciado bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE.md) para más detalles.