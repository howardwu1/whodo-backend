FROM mcr.microsoft.com/devcontainers/base:ubuntu
SHELL ["/bin/bash", "-c"]

RUN apt-get update \
    && apt-get install -y git vim curl unzip zip sudo  \
    && apt-get -y install --no-install-recommends postgresql-client

USER vscode

ARG JAVA_VERSION=21.0.2-tem
ARG GRADLE_VERSION=8.5   

RUN curl -s "https://get.sdkman.io" | bash \ 
    && source "$HOME/.sdkman/bin/sdkman-init.sh" \
    && sdk install java $JAVA_VERSION \
    && sdk install gradle $GRADLE_VERSION

# This environment is only set on the app side:
ENV PGHOST localhost

# Too soon to be able to talk to postgres:
# (don't) RUN creatdb -U postgres --createdb vscode
# Instead: just use the superuser: fewer variables to track,
# especially when configuring the SQLTools extension:
ENV PGUSER postgres
