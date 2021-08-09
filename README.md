# Kata Twitter
## MySql on Docker
* [Documento Referencia](https://towardsdatascience.com/connect-to-mysql-running-in-docker-container-from-a-local-machine-6d996c574e55)
* Run MySql Docker image.
```
docker run --name mysqldb -p 3306:3306 -v $(pwd)/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=adminadmin -d mysql:5.6
```
El nombre de la DB es mysqldb, el puerto donde correrá es 3306(defecto de mysql)
se construirá un volumen que apuntará al directorio "data" de este proyecto. Esto porque cualquier cosa que afecte
al container provocará que se pierdan los datos de la db. Con -d le decimos que el volumen corra en segundo plano.
Establemcemos el password y la imagen que utilizarmos es la que tiene tag "mysql:5.6"

* Acces to MySql console
  connect to the container’s interactive bash shell with the following command:
  
```
docker exec -it mysqldb bash
mysql -u root -p
```
## App on Docker
* Build image
```
docker build -t katatwitter .
```
* Run image
```
docker run -p 80:80 -v $(pwd):/var/opt/project katatwitter
```

