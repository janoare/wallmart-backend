generate-artifact:
	mvn clean install
build-docker-image:
	docker build -t wallmart-backend:latest .