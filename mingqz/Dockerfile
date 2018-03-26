FROM docker.io/fabric8/java-alpine-openjdk8-jdk
MAINTAINER "ming"
ENV MY_ENV=dev
ENV JVM_PARAMS=""
# copy app.jar
COPY build/libs/mingqz-1.0.jar /workspace
# open port
EXPOSE 8080
# 运行jar
CMD  java ${JVM_PARAMS} -jar /workspace/mingqz-1.0.jar env=${MY_ENV}