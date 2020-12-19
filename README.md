# eduBD Project - RESTful Service

&nbsp;
----

The provided codebase is a [spring-boot](https://projects.spring.io/spring-boot/) based project that requires [git](https://git-scm.com/downloads), 
[java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) _(or greater)_ and 
[apache maven](https://maven.apache.org/download.cgi) be installed on your machine.

&nbsp;
---

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

If you want to persist your data between service restarts, you will need to use MySQL.  
You can use MySQL in one of two ways:
- Use docker (preferred!)
  - Install [Docker for Windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows) or [Docker for Mac](https://hub.docker.com/editions/community/docker-ce-desktop-mac)
  - Using a terminal inside this project, run `docker-compose up -d`
  - You're ready to go!
- Manually install MySQL on your local machine (there are some pitfalls, beware!)
  - Download [MySQL](https://dev.mysql.com/downloads/mysql/5.7.html)
  - Start MySQL on port 3306
  - Create a schema called `capstone`

Whether you use docker to run MySQL, or manually install and start it, the command is still the same:
```
mvn spring-boot:run -D spring.profiles.active=localmysql
```

Once the application is running locally Swagger based REST documentation and testing will be available at:
- [http://localhost:8333/eduBD-Project/swaggerui](http://localhost:8333/eduBD-Project/swaggerui)

To run the project for HTML:
- [http://localhost:8333/eduBD-Project](http://localhost:8333/eduBD-Project)


