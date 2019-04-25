FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER lucky

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-tx-manager

WORKDIR /bigdata-tx-manager

EXPOSE 5004
EXPOSE 9998

ADD ./bigdata-tx-manager/target/bigdata-tx-manager.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-tx-manager.jar
