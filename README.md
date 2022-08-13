# ficticia-java

URI base: www.example.com.ar/api/v1/

# ENDPOINT: article/
# MÉTODO GET:
(sin parámetros): Devuelve todos los registros paginados.
Parámetro (page): (int) Permite establecer la página a visualizar.
Parámetro (id): (Long) Permite definir el ID del artículo a visualizar.
Parámetro (q): (String) Permite realizar una búsqueda de artículos según la cadena de texto pasada.

# MÉTODO POST:
(sin paraámetros): Permite registrar un nuevo artículo en la base de datos.
Body JSON:
- Campos aceptados: title, description, publishedAt, url, urlToImage, content.
- Campos rechazados: id, author, source.

# MÉTODO PUT:
Parámetro (id): (Long) Permite definir el ID del artículo a modificar.
Body JSON:
- Campos aceptados: title, description, publishedAt, url, urlToImage, content.
- Campos rechazados: id, author, source.

# MÉTODO DELETE:
Parámetro (id): (Long) Permite definir el ID del artículo a eliminar.

# ----------------------------------------------------------------------------------------------------
# ENDPOINT: author/

# MÉTODO GET:
(sin parámetros): Devuelve todos los registros paginados.
Parámetro (page): (int) Permite establecer la página a visualizar.
Parámetro (id): (Long) Permite definir el ID del autor a visualizar.
Parámetro (fullName): (String) Permite realizar una búsqueda de autores según la cadena de texto pasada.

# MÉTODO POST:
(sin paraámetros): Permite registrar un nuevo autor en la base de datos.
Body JSON:
- Campos aceptados: firstName, lastName, createdAt.
- Campos rechazados: id.

# MÉTODO PUT:
Parámetro (id): (Long) Permite definir el ID del autor a modificar.
Body JSON:
- Campos aceptados: firstName, lastName.
- Campos no aceptados: id, createdAt.

# MÉTODO DELETE:
Parámetro (id): (Long) Permite definir el ID del autor a eliminar.

# ----------------------------------------------------------------------------------------------------
# ENDPOINT: source/

# MÉTODO GET:
(sin parámetros): Devuelve todos los registros paginados.
Parámetro (page): (int) Permite establecer la página a visualizar.
Parámetro (id): (Long) Permite definir el ID de la fuente a visualizar.
Parámetro (name): (String) Permite realizar una búsqueda de fuentes según la cadena de texto pasada.

# MÉTODO POST:
(sin paraámetros): Permite registrar un nueva fuente en la base de datos.
Body JSON:
- Campos aceptados: name, contenido, createdAt.
- Campos rechazados: id, code.

# MÉTODO PUT:
Parámetro (id): (Long) Permite definir el ID de la fuente a modificar.
Body JSON:
- Campos aceptados: name, contenido.
- Campos no aceptados: id, code, createdAt.

# MÉTODO DELETE:
Parámetro (id): (Long) Permite definir el ID de la fuente a eliminar.
