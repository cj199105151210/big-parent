#! /bin/shell

#======================================================================
# mvn package script
# default local profile
# 打包助手
# author: lucky
# date: 2019-03-06
#======================================================================

PROFILE=$1
if [[ -z "$PROFILE" ]]; then
    PROFILE=dev
fi
echo "profile:${PROFILE}"
mvn clean package -P${PROFILE} -DskipTests
echo "profile:${PROFILE}"