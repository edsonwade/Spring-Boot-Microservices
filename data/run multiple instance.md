# Running Multiple Instances of a Spring Boot Application on Different Ports

## Introduction
In some scenarios, you may need to run multiple instances of your Spring Boot application, each serving requests on a different port. This guide explains how to achieve this.

## Configuration
### 1. Update Application Properties
Update the `application.properties` or `application.yml` file to specify the server port for each instance.

#### Example (application.properties):
```properties
# For the first instance (default port 8080)
server.port=8080

# For the second instance (port 8081)
# server.port=8081

# For the third instance (port 8082)
# server.port=8082

# And so on...
```
## 2- Start Instances
### Running from Command Line
If running from the command line, specify the port using the --server.port option.
````
java -jar your-application.jar --server.port=8081
```
