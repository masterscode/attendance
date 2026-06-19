.PHONY: all build clean start-java start-jar stop-jar restart-jar start-docker stop-containers rebuild-docker

all: build

build:
	./mvnw clean install

start-java: build
	./mvnw spring-boot:run


start-docker:
	docker compose up -d

start: start-docker

stop-containers:
	docker compose down

rebuild-docker: stop-containers
	docker-compose build --no-cache
	docker-compose up -d

clean:
	./mvnw clean
	rm -f target/*.jar
