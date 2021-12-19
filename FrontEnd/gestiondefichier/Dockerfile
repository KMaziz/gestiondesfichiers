#stage 1
FROM node:latest as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod
#stage 2
FROM nginx:alpine
COPY dist/crud/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
