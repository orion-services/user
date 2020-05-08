FROM open-liberty:20.0.0.3-full-java8-openj9

LABEL \
    org.opencontainers.image.authors="Guilherme Moreira" \
    description="This image for Orion User Service"

# Waiting a new server.xml feature to use a better solution
# https://github.com/OpenLiberty/ci.maven/issues/705
COPY --chown=1001:0 src/main/liberty/config/resources/mysql-connector-java-8.0.19.jar /config/resources

# Sending Liberty configuration 
COPY --chown=1001:0 src/main/liberty/config /config/

# Deploy the application
COPY --chown=1001:0 target/orion-user-service.war /config/dropins/

# Executing 
RUN configure.sh