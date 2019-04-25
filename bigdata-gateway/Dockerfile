FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-gateway

WORKDIR /bigdata-gateway

EXPOSE 2999

ADD ./bigdata-gateway/target/bigdata-gateway.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bosx-gateway.jar
