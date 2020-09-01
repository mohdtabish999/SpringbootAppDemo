############################################################################
# 
# Prepare stable image layers to be cached and reused below, as referenced
# via "--from=compile".
#
FROM kabanero/ubi8-maven:0.9.0 as compile

USER root

# Ensure up to date / patched OS
COPY ./update.sh /update.sh
RUN  /update.sh

RUN  groupadd --gid 1000 java_group \
  && useradd --uid 1000 --gid java_group --shell /bin/bash --create-home java_user \
  && mkdir -p /mvn/repository \
  && chown -R java_user:java_group /mvn

USER java_user

# Labels for Intermediate image used for layer caching
LABEL "stack.appsody.dev/id"="kabanero/java-spring-boot2"
LABEL "stack.appsody.dev/version"="0.3.29"

# Layer caching for stack dependencies
COPY --chown=java_user:java_group appsody-boot2-snowdrop-pom.xml /mvn/appsody-boot2-snowdrop-pom.xml
RUN mvn --no-transfer-progress -B dependency:go-offline install -f /mvn/appsody-boot2-snowdrop-pom.xml

# Layer caching for application dependencies
COPY --chown=java_user:java_group user-app/pom.xml /mvn/pom.xml
RUN mvn --no-transfer-progress -B dependency:go-offline -f /mvn/pom.xml

# Copy and build project
COPY --chown=java_user:java_group . /project

WORKDIR /project/user-app

RUN /project/util/check_version build \
 && /project/java-spring-boot2-build.sh package \
 && /project/java-spring-boot2-build.sh createStartScript

############################################################################
# 
# Begin build of final application image
#
FROM adoptopenjdk/openjdk8-openj9:ubi-jre

USER root

RUN groupadd --gid 1000 java_group \
 && useradd --uid 1000 --gid java_group --shell /bin/bash --create-home java_user

USER java_user

ENV JVM_ARGS=""

# Need to find a way to pass version in during appsody build, from the application project source
ARG version=1.0-SNAPSHOT
LABEL org.opencontainers.image.version="${version}"

COPY --chown=java_user:java_group --from=compile /project/user-app/target/start.sh /start.sh

ARG DEPENDENCY=/project/user-app/target/dependency
COPY --chown=java_user:java_group --from=compile ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --chown=java_user:java_group --from=compile ${DEPENDENCY}/META-INF /app/META-INF
COPY --chown=java_user:java_group --from=compile ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 8080
EXPOSE 8443
ENTRYPOINT [ "/start.sh" ]
