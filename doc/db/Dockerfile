FROM mysql:5.7

MAINTAINER lucky

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./doc/db/1schema.sql /docker-entrypoint-initdb.d

COPY ./doc/db/2bigdata.sql /docker-entrypoint-initdb.d
