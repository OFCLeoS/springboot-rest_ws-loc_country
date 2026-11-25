# Spring Boot Coordinates Web Service
RESTful Web Service created with SpringBoot. On input of a latitude and a longitude, returns the country and the country code of that location.

# Table of Contents
### 1. **[Running the Web Service Guide](#guide-on-running-the-web-service)**
### 2. **[Usage Guide](#usage-guide)**
### 3. **[Docker Image Size](#docker-image-size)**
### 4. **[Videos](#videos)**
---------

# Guide on running the Web Service
NOTE: All of the commands should be ran in the same directory as this file unless stated otherwise!

This guide contains optional commands that are used for debugging.
## Requirements
### Using host environment
- `Java 17+`
- `Maven`
### Using Docker
- `Docker`
### Using Kubernetes with Kind
- `Docker`
- `Kind`
- `Kubernetes`

## Maven
1. Build the Web Service .jar
```bash
mvn clean package
```
2. Run the .jar file inside the [target](target/) folder. (SHOULD be named "springboot-rest_ws-loc_country-0.0.1-SNAPSHOT.jar")
```bash
java -jar [FILENAME]
```
**Ready to use!**

## Docker
**Make sure Docker is running!**
1. Create the Docker Image
```bash
docker build -t country-grabber .
```
- (OPTIONAL) Check if image was created
```bash
docker images
```
2. Run the container
```bash
docker run -d -p 8080:8080 --name [CUSTOM_NAME] country-grabber
```
- (OPTIONAL) Check if container is running
```bash
docker ps
```
**Ready to use!**

## Kubernetes with Kind (2 replicas)
**Make sure to build the Docker Image first!**
1. Create cluster
```bash
kind create cluster --name country-grabber-cluster
```
- (OPTIONAL) Inspect cluster
```bash
kubectl cluster-info
```
```bash
kubectl get nodes
```
2. Load Docker Image to cluster
```bash
kind load docker-image country-grabber --name country-grabber-cluster
```
3. Apply both the Deployment AND Service settings:
```bash
kubectl apply -f kubernetes/deployment.yaml
```
```bash
kubectl apply -f kubernetes/service.yaml
```
- (OPTIONAL) Check deployment and Pods
```bash
kubectl get deployments
```
```bash
kubectl get pods
```
4. Forward our Computer's port 8080 to the Kubernetes Service
```bash
kubectl port-forward service/country-grabber-service 8080:8080
```

**Ready to use!**

# Usage Guide
After starting the Web Service, we can obtain the Country and Country code of a set of coordinates via a GET request to http://localhost:8080/?lat=[LATITUDE]&lon=[LONGITUDE], where LATITUDE is between -90 and 90, and LONGITUDE is between -180 and 180.

These two numbers must represent the coordinates inside a **country**.

(Note: Oceans are not countries)

## Example
**(49.504344217237836, 5.9493317687883644)** represents the coordinates of a very cool University. You can use the Web Service to find out where it is located!

http://localhost:8080/?lat=49.504344217237836&lon=5.9493317687883644

# Docker Image Size
Because of the Java Virtual Machine system, "compression" of the final Docker Image is very limited. I managed to find a container image (distroless) from Google that allowed me to bring the Docker Image content size to under 100 MB.

The resulting Docker Image has a content size of `77.6 MB`.

# Videos
I apologize for the microphone not working
- [Running the WS with Maven](https://youtu.be/Jm0__6hvN9c)
- [Running the WS within a Docker container](https://youtu.be/LBh3nUrY5kU)
- [Running the WS within a Kubernetes cluster](https://youtu.be/UVxPXP888J4)