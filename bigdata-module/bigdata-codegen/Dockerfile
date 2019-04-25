FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-codegen

WORKDIR /bigdata-codegen

EXPOSE 5003

ADD ./bigdata-module/bigdata-codegen/target/bigdata-codegen.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-codegen.jar
