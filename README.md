# wallmart-backend
Backend para soportar la busqueda de productos

Requerimientos

- Base de datos mongodb con los productos a buscar https://github.com/walmartdigital/products-db
- Java 8 
- Tener instalado localmente docker

Ejecucion Local:

1) Compilacion y creacion del artefacto (.jar)
```sh
$ make generate-artifact
```
2) Creacion de la imagen docker
```sh
$ make build-docker-image
```

3) Para levantar un contenedor se puede usar el siguiente comando:
```sh
docker run -e "MONGO_HOST=<Host BD mongo>" -e "MONGO_PORT=<Puerto BD mongo>" -e "SPRING_PROFILES_ACTIVE=local" -p  8080:8080 wallmart-backend 
```





