FROM openjdk:17-jdk-slim

# 한글화
#RUN apt-get update && apt-get install -y locales
#RUN locale-gen ko_KR.UTF-8
#ENV LC_ALL ko_KR.UTF-8

EXPOSE 3000
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
