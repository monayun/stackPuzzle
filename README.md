### 도커 컴포즈 실행

1. Dockerfile 생성하기
```cmd
FROM mysql:8.0.33

ENV MYSQL_USER=<USER>
ENV MYSQL_PASSWORD=<PASSWORD>
ENV MYSQL_ROOT_PASSWORD=<PASSWORD>
ENV MYSQL_DATABASE=stack
ENV MYSQL_HOST=%

CMD ["--character-set-server=utf8mb4","--collation-server=utf8mb4_unicode_ci"]
```
2. 도커 빌드하기
Dockerfile이 있는 그 위치에서 생성
```cmd
docker build -t mysql-image .  
docker images
```

3. 도커 실행하기
```cmd
docker run -d -p 3307:3306 mysql-images
docker ps
```

4. 도커 컴포즈 생성
5. 도커 컴포즈 실행
```cmd
docker-compose up -d
```

----

### 도커 전부 삭제하기
```cmd
docker stop $(docker ps -q)
docker rm $(docker ps -a -q)
docker rmi $(docker images -q)
```
