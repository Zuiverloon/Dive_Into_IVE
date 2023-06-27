# Dive_Into_IVE


## how to run in the container?
1. run in a container equipped with maven/jdk8/git
```shell
docker run -it -v /Users/zjy/Desktop/IVE:/file/IVE --network host b70d1adce28d /bin/bash
```
2. git pull

3. mvn spring-boot:run