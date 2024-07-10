### Foro Hub
<p>
Este proyecto es una API REST para un foro de dudas donde los usuarios pueden crear, listar, actualizar y eliminar tópicos.
</p>

#### Funciones:
- **Registro de Usuarios**: Los usuarios pueden registrarse en la plataforma. 
- **Autenticación**: Los usuarios deben autenticarse (iniciar sesión) para interactuar con la API utilizando tokens JWT.
- **Crear Tópicos**: Los usuarios autenticados pueden crear nuevos tópicos. 
- **Listar Tópicos**: Los usuarios pueden ver todos los tópicos creados. 
- **Actualizar Tópicos**: Los usuarios pueden actualizar sus tópicos. 
- **Eliminar Tópicos**: Los usuarios pueden eliminar sus tópicos.

#### Uso
### Registro de Usuario

- **Endpoint**: `/usuarios/registrar`
- **Método**: `POST`
- **Body**:
    ```json
    {
        "nombre": "usuario1",
        "email": "usuario1@mail.com",
        "contraseña": "123456"
    }
    ```

### Autenticación

- **Endpoint**: `/auth/login`
- **Método**: `POST`
- **Body**:
    ```json
    {
        "nombre": "usuario1",
        "contraseña": "123456"
    }
    ```
### Crear Tópico

- **Endpoint**: `/topicos`
- **Método**: `POST`
- **Headers**:
    ```http
    Authorization: Bearer jwt_token_aquí
    ```
- **Body**:
    ```json
    {
        "titulo": "Duda",
        "mensaje": "Tengo una duda con HTTP",
        "fechaDeCreacion": "2024-06-27",
        "estatus": "SIN_RESPUESTA",
        "idUsuario": 1,
        "curso": "Curso de Spring"
    }
    ```

### Listar Tópicos

- **Endpoint**: `/topicos`
- **Método**: `GET`
- **Headers**:
    ```http
    Authorization: Bearer jwt_token_aquí
    ```

### Actualizar Tópico

- **Endpoint**: `/topicos/{id}`
- **Método**: `PUT`
- **Headers**:
    ```http
    Authorization: Bearer jwt_token_aquí
    ```
- **Body**:
    ```json
    {
        "estatus": "SOLUCIONADO"
    }
    ```

### Eliminar Tópico

- **Endpoint**: `/topicos/{id}`
- **Método**: `DELETE`
- **Headers**:
    ```http
    Authorization: Bearer jwt_token_aquí
    ```


  

