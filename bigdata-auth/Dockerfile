FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-auth

WORKDIR /bigdata-auth

EXPOSE 7000

ADD ./bigdata-auth/target/bigdata-auth.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-auth.jar
