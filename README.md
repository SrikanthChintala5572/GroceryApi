# GroceryApi
An Api for grocery operations

This is another micro service project designed to perform the operations on the list of groceries returned from the gorcerydataprovider project. 
This project once created will get registered with the Serviceregistry project. We can communicate with the gorcerydataprovider project to get the excel data as list and can start performing operations
This project has been developed using Java 17, Spring-boot 3.1.4 and Maven.

All the entities for the grocery are mapped using the JPA.

Instructions run this project:

Clone this project

Import in any IDE as Maven project

Build : mvn clean install

Run: Run the application

Health check using Spring actuator url : http://localhost:8085/actuator/health

Swagger-ui url : http://localhost:8085/swagger-ui/index.html

Using the swagger url mentioned above we can test the services created

Note: We have to start service registry project first before starting this project then only this service will be registered.
