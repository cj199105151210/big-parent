FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-https

WORKDIR /bigdata-https

EXPOSE 5006

ADD ./bigdata-module/bigdata-https/target/bigdata-https.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-https.jar
