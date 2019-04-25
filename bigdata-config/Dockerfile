FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-config

WORKDIR /bigdata-config

EXPOSE 8888

ADD ./bigdata-config/target/bigdata-config.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-config.jar
