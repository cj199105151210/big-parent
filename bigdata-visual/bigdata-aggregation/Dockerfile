FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-aggregation

WORKDIR /bigdata-aggregation

EXPOSE 6005

ADD ./bigdata-visual/bigdata-aggregation-biz/target/bigdata-aggregation-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-aggregation-biz.jar
