build:
	docker compose build

start:
	docker compose up -d

stop:
	docker compose down

rebuild:
	docker compose build --no-cache
	docker compose up -d

run:
	./mvnw spring-boot:run

package:
	./mvnw clean package

clean:
	./mvnw clean

test-db:
	docker compose up -d postgres

start-containers:
	docker compose up -d postgres redis
