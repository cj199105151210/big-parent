FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-upms

WORKDIR /bigdata-upms

EXPOSE 6000

ADD ./bigdata-upms/bigdata-upms-biz/target/bigdata-upms-biz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-upms-biz.jar
