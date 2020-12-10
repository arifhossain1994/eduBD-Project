# eduBD-Project
This is an education platform

### Building this Project

To perform a build and execute all unit tests:
```
mvn clean install
```

To execute all component tests:
```
mvn clean -P test-component test
```

&nbsp;
---

### Using this Project

To run the RESTful services:
```
mvn spring-boot:run
```

or you can execute the JAR that is created by the install command above via:
```
java -jar target/*.jar
```

By default, application.properties configures a local instance of H2 so that anyone can use this project immediately.
See `service/src/main/resources` for available profiles.
