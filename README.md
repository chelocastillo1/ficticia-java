# FICTICIA-JAVA

#### Desarollado por [Ficticia Software](https://chelocastillo1.github.io/informatorioPortfolio/)
#### Profesor a cargo: Eduardo Ortiz

Proyecto final para el informatorio 2022 de Java.
Este proyecto es una API REST desarrollada en el lenguaje de programación Java y permite gestionar la publicación de noticias online.

# Acerca de la API

URI_BASE: www.example.com.ar/api/v1/

## Endpoints
  * #### ENDPOINT: Article
    * URI: "{URI_BASE}article/"
    * Método GET:
      * (sin parámetros): Devuelve todos los registros paginados, acepta el parámetro page.
      * Parámetro (page): (int) Permite establecer la página a visualizar.
      * Parámetro (id): (Long) Permite definir el ID del artículo a visualizar.
      * Parámetro (q): (String) Permite realizar una búsqueda de artículos según la cadena de texto pasada, acepta el parámetro page.
    * Método POST:
      * Crear / Guardar:
        * (sin parámetros): Permite registrar un nuevo artículo en la base de datos.
        * Body JSON:
          * Campos aceptados: title, description, publishedAt, url, urlToImage, content.
          * Campos rechazados: id, author, source.
      * Asignar autor a artículo:
        * URI: "{URI_BASE}article/{idArticle}/author/{idAuthor}"
        * Variable de ruta (idArticle): (Long) Permite definir el ID del artículo al cual se le asignará el autor.
        * Variable de ruta (idAuthor): (Long) Permite definir el ID del autor que se asignará.
      * Asignar fuente a artículo:
        * URI: "{URI_BASE}article/{idArticle}/source/{idSource}"
        * Variable de ruta (idArticle): (Long) Permite definir el ID del artículo al cual se le asignará la fuente.
        * Variable de ruta (idSource): (Long) Permite definir el ID de la fuente que se asignará.
    * Método PUT:
      * Variable de ruta (id): (Long) Permite definir el ID del artículo a modificar.
      * Body JSON:
        * Campos aceptados: title, description, publishedAt, url, urlToImage, content.
        * Campos rechazados: id, author, source.
    * Método DELETE:
      * Variable de ruta (id): (Long) Permite definir el ID del artículo a eliminar.
      
  * #### ENDPOINT: Author
    * URI: "{URI_BASE}author/"
    * Método GET:
      * (sin parámetros): Devuelve todos los registros paginados, acepta el parámetro page.
      * Parámetro (page): (int) Permite establecer la página a visualizar.
      * Parámetro (id): (Long) Permite definir el ID del autor a visualizar.
      * Parámetro (fullName): (String) Permite realizar una búsqueda de autores según la cadena de texto pasada, acepta el parámetro page.
    * Método POST:
      * (sin paraámetros): Permite registrar un nuevo autor en la base de datos.
      * Body JSON:
       * Campos aceptados: firstName, lastName, createdAt.
       * Campos rechazados: id.
    * Método PUT:
      * Variable de ruta (id): (Long) Permite definir el ID del autor a modificar.
      * Body JSON:
       * Campos aceptados: firstName, lastName.
       * Campos no aceptados: id, createdAt.
    * Método DELETE:
      * Variable de ruta (id): (Long) Permite definir el ID del autor a eliminar.

  * #### ENDPOINT: Source
    * URI: "{URI_BASE}source/"
    * Método GET:
      * (sin parámetros): Devuelve todos los registros paginados, acepta el parámetro page.
      * Parámetro (page): (int) Permite establecer la página a visualizar.
      * Parámetro (id): (Long) Permite definir el ID de la fuente a visualizar.
      * Parámetro (name): (String) Permite realizar una búsqueda de fuentes según la cadena de texto pasada, acepta el parámetro page.
    * Método POST:
      * (sin parámetros): Permite registrar un nueva fuente en la base de datos.
      * Body JSON:
       * Campos aceptados: name, contenido, createdAt.
       * Campos rechazados: id, code.
    * Método PUT:
      * Variable de ruta (id): (Long) Permite definir el ID de la fuente a modificar.
      * Body JSON:
       * Campos aceptados: name, contenido.
       * Campos no aceptados: id, code, createdAt.
    * Método DELETE:
      * Variable de ruta (id): (Long) Permite definir el ID de la fuente a eliminar.
