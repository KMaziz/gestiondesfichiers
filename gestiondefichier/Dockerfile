# choose base image to build off of
FROM  node:14.16.0
# set the current working directory for all commands
RUN mkdir /usr/src/app

WORKDIR /usr/src/app

RUN npm install -g @angular/cli

COPY . .
