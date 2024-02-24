FROM openjdk:21-ea-jdk-slim

WORKDIR /app/stackPuzzle

# COPY만 docker-compose 파일의 위치를 기반으로 작동함
COPY . .
EXPOSE 8080

# 개행문자 오류 해결 [unix와 window 시스템 차이]
RUN sed -i 's/\r$//' gradlew

# RUN은 현재 파일을 위치를 기반으로 작동함
RUN chmod +x ./gradlew
RUN ./gradlew clean build

ARG JAR_PATH=../build/libs/
COPY ${JAR_PATH}/*.jar /app/stackPuzzle/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]