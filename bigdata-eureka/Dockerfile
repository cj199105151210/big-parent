FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER bigdata

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /bigdata-eureka

WORKDIR /bigdata-eureka

EXPOSE 8761

ADD ./bigdata-eureka/target/bigdata-eureka.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar bigdata-eureka.jar
