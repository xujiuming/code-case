FROM docker.io/fabric8/java-alpine-openjdk8-jdk
MAINTAINER "ming"

# create  workspace
RUN  mkdir /workspace
# copy app.jar
COPY target/configServer-1.0-SNAPSHOT.jar /workspace
# open port
EXPOSE 8888
# 运行jar
CMD  java -jar /workspace/configServer-1.0-SNAPSHOT.jar