# Functionality/Overview
MyRetail project provides REST API's for the applications to view/add/update/delete products and its prices .

# Retail - Design 
My Retail REST Service API

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions: 
•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
Example product IDs: 13860428, 54456119, 13264003, 12954218) 
•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 
•	Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics
•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  
•	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

# Development Environment
IDE - IDEA Intellij
Version Control - Git
Dependency Management - Gradle 6.6.1
Database - MongoDb NoSql
RESTful Application framework - Spring Boot
Programming language - Java 1.8
Unit Testing library - JUnit, Mockito
API Integration Testing - PostMan ,Swagger
Operating System - Windows 10 10.0 amd64

# MongoDB
1. Start your MongoDB server - use studio3T 
2. create a collection by name Products
3. Insert data using the scripts Products.js

# Running myRetail Application
Run application via gradle task run
Use Swagger to access endpoints or other tools like Postman
URL : http://localhost:8129/target/swagger-ui.html#/

# API's
GET /products/{id}
GET /products/{id}/price
PUT /products/{id}/product
DELETE /products/{id}/product
GET /products/findAll
DELETE /products/products


# Swagger
API documentation is done through Swagger. Endpoints can be viewed and used through the Swagger-UI by accessing the page http://localhost:8129/target/v2/api-docs

#Future Enhancements
Add  performance and error handling and logging
Add Auth tokens 

#Gradle
Gradle 6.6.1
------------------------------------------------------------
Kotlin:       1.3.72
Groovy:       2.5.12
Ant:          Apache Ant(TM) version 1.10.8 compiled on May 10 2020
JVM:          1.8.0_202 (Oracle Corporation 25.202-b08)
OS:           Windows 10 10.0 amd64

# Testing
Unit tests
com.target.myretail.service.ProductServiceImplTest.java
![testReults](https://user-images.githubusercontent.com/68809796/110689472-b8fefd00-8208-11eb-8b6e-55b5d37beb35.JPG)

