# Challenge nouveau développeur Notarius - URL Shortener

Technologies utilisées:
> Spring boot 2.5.3
> 
> Java JDK 16 (AdoptOpenJDK)
> 
> MongoDB 5.0.2
> 
> Angular 12.2.0
> 
> Docker 20.10.17
> 

> Docker compose 1.29.2


## Lancer l'application 

> Se placer dans le dossier de l'application backend et la compiler

```
cd {CHEMIN_CLONE_REPOS}/urlshortener/urlshortener-backend
./mvnw package
```

> Contruire les images docker depuis le dossier parent du projet

```
cd {CHEMIN_CLONE_REPOS}/urlshortener/
docker-compose --env-file "./DB/mongodb/.env" build
```

> Deployer le conteneur multiple contenant ces images

```
docker-compose --env-file "./DB/mongodb/.env" up -d
```

Vous pouvez désormais accéder à l'application via le lien : http://localhost:8081/


### Consommer l'API Rest sans docker (nécessite serveur Mongodb en local)

> Lancer l'application backend

```
cd {CHEMIN_CLONE_REPOS}/urlshortener/urlshortener-backend
.\mvnw.cmd spring-boot:run
```

Les APIs sont disponibles depuis : 

> http://localhost:8080/url/shortenUrl
> Method GET avec pour clé "originalUrl" et valeur "{URL}"

> http://localhost:8080/url/retrieveUrl
> Method GET avec pour clé "hashedUrl" et valeur "{URL_RACCOURCIE}"
