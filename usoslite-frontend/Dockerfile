# Stage 1: Build the Angular app
FROM node:16-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

# Stage 2: Serve the built Angular app
FROM node:16-alpine AS serve

WORKDIR /app

COPY --from=build /app/dist/usoslite-frontend .

RUN npm install -g http-server

EXPOSE 4200

CMD ["http-server", "-p", "4200"]
