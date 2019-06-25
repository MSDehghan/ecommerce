FROM openjdk:8-jdk-alpine

MAINTAINER MohammadSadegh Dehghan <msdn2013@gmail.com>

ENV ENV_NAME dev
ENV BOOTAPP_JAVA_OPTS -Xms256m -Xmx256m
ENV LOG_PATH "/var/log/casa"
ENV BOOTAPP_USR="root" BOOTAPP_GROUP="root" BOOTAPP_PATH="/app.jar"
ENV SERVER_PORT 8080

EXPOSE $SERVER_PORT

COPY wrapper.sh /wrapper.sh

RUN chmod 555 /wrapper.sh

USER root
COPY maven/application.jar $BOOTAPP_PATH
RUN chmod 555 $BOOTAPP_PATH && \
            touch $BOOTAPP_PATH
RUN mkdir $LOG_PATH
RUN chmod 777 $LOG_PATH && touch $LOG_PATH

USER $BOOTAPP_USR
RUN apk add --no-cache bash

ENTRYPOINT ["/wrapper.sh"]