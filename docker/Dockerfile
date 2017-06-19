FROM apsl/java6
MAINTAINER luis.perez<luis.perez@neodoo.es>

RUN apt-get update && apt-get install -y curl

ARG MAVEN_VERSION=2.2.1
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://archive.apache.org/dist/maven/binaries

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && mkdir -p $MAVEN_CONFIG \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-$MAVEN_VERSION-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

RUN ln -s /usr/lib/jvm/java-6-oracle /usr/lib/jvm/java

RUN apt-get install -y git ssh tar gzip ca-certificates

COPY settings.xml $MAVEN_CONFIG/settings.xml
ENV MAVEN_OPTS="-Xmx512m -XX:MaxPermSize=128m"

RUN locale-gen es_ES.UTF-8 && dpkg-reconfigure locales
ENV LC_ALL=es_ES.UTF-8
ENV LANG=es_ES.UTF-8

VOLUME "$USER_HOME_DIR/.m2"
WORKDIR /sigm
