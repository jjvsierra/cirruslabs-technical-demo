# CirrusLabs
### This repository is a technical demo of an API microservice to simulate user registration using Spring Boot.

---
Running on local:
```html
Execute the following commands:
mvn clean install
mvn spring-boot:run

You should be able to access at http://localhost:8080/
You should be able to access the documentation of the API at http://localhost:8080/swagger-ui.html 
``` 

---
Technologies used: 
```html
* Java 11
* Spring Boot
* Lombok 
* OpenAPI 3.0
``` 

---
Test description:
```html
* Expose REST API to accept a payload of username, password, and IP address.
* All parameters must not be blank (!= empty and null). Return error messages if not valid.
* Password need to be greater than 8 characters, containing at least 1 number, 1
Capitalized letter, 1 special character in this set “_ # $ % .”. Return error messages if not
valid.
* Call this end point to get geolocation for the provided IP: IP-API.com - Geolocation API -
Documentation - JSON. If the IP is not in Canada, return error message that user is not
eligible to register.
* When all validation is passed, return a random uuid and a welcome message with his
username and his City Name (resolved using ip-geolocation api).
* The API need to have OpenAPI specification, no matter what your approach is code first
or design first.
* Project must use Maven or Gradle to build. Generate a spring boot project here: Spring
Initializr. 
* Need to have JUnit Tests.
``` 

