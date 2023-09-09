# Running a Spring Boot Application with Gradle Wrapper and Testing with cURL

This README provides step-by-step instructions for running a Spring Boot application using Gradle Wrapper and testing it with a cURL client. The Spring Boot application in this example exposes an HTTP endpoint at `http://localhost:8081/github/{username}/repos` to fetch GitHub repositories for a given username.

## Prerequisites

Before you begin, make sure you have the following prerequisites installed on your system:

- Java Development Kit (JDK) 8 or higher
- Gradle Wrapper (included with the project)
- cURL command-line tool

## Getting Started

1. Clone the repository:

   ```shell
   git clone <repository_url>
   cd <project_directory>

2. Build the Spring Boot application using Gradle Wrapper:
   ```shell
   ./gradlew build #This command will download Gradle if necessary and build the project.
3. Run the Spring Boot application:
   ```shell
   ./gradlew bootRun #This will start the Spring Boot application locally, and it will be accessible at http://localhost:8081.
4. Run tests of Spring boot application
    ```shell
   .\gradlew test
   
### Testing with cURL
You can use cURL to test the endpoint by making HTTP GET requests. Replace <username> with the GitHub username you want to query
   ```shell
   curl -H "Accept: application/json" http://localhost:8081/github/<username>/repos