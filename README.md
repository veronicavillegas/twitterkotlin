# Kata Twitter
## MySql on Docker
* [Documento Referencia](https://towardsdatascience.com/connect-to-mysql-running-in-docker-container-from-a-local-machine-6d996c574e55)
* Run MySql Docker image
```
docker run --name katatwitter-mysql -e MYSQL_ROOT_PASSWORD=adminadmin -d mysql:5.7.35
```

* Acces to MySql console
```
mysql -u root -p;
```
## App on Docker
* Build image
```
docker build -t kata-twitter .
```
* Run image
```
docker run -p 80:80 kata-twitter
```

