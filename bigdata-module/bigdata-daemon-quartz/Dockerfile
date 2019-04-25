FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-daemon-quartz

WORKDIR /bigdata-daemon-quartz

EXPOSE 5007

ADD ./bigdata-module/bigdata-daemon-quartz/target/bigdata-daemon-quartz.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-daemon-quartz.jar
