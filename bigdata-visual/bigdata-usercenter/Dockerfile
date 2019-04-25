FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-usercenter

WORKDIR /bigdata-usercenter

EXPOSE 6006

ADD ./bigdata-visual/bigdata-usercenter-biz/target/bigdata-usercenter-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-usercenter-biz.jar
