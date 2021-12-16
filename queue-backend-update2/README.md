#Welcome! This is the Customer Service Queue System's backend-side document
The customer service queuing system of this project can solve the problem of the imbalance between customer service resources and demands and improve the efficiency of the customer service system and the user experience. 

The customer service queuing system is divided into two parts, web application and server, using the separation of client and server design theory, based on Java programming language and Vue and Spring Boot framework, successfully implemented a queuing system that can be used in multiple scenarios. This project is characterized using Redis as a cache to improve the queuing system under a large number of frequent requests to maintain good performance, and to achieve priority for different levels of customers in the queue, the details of the waiting queue query and customers take the initiative to leave the queue and other main requirements of the queuing system.
########### Environment dependency

Spring Boot v2.5.5

Redis v5.0.14

fastjson 1.2.54

mybatis.generator v1.3.7

swagger-ui 2.6.1

swagger2 2.6.1

jquery 3.4.1

velocity 1.7

sa-token 1.28.0

mybatis 3.4.1

Example pom.xml:

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.5</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>uofg.zehuilyu</groupId>
    <artifactId>queue-api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>queue-api</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>


        <snippetsDirectory>${project.build.directory}/generated-snippets</snippetsDirectory>

        <asciidoctor.input.directory>${project.basedir}/src/docs/asciidoc</asciidoctor.input.directory>
        <generated.asciidoc.directory>${project.build.directory}/asciidoc</generated.asciidoc.directory>
        <asciidoctor.html.output.directory>${project.build.directory}/asciidoc/html</asciidoctor.html.output.directory>
        <asciidoctor.pdf.output.directory>${project.build.directory}/asciidoc/pdf</asciidoctor.pdf.output.directory>
        <swagger.version>2.6.1</swagger.version>

    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.1</version>
        </dependency>
        <dependency>
            <groupId>cn.dev33</groupId>
            <artifactId>sa-token-spring-boot-starter</artifactId>
            <version>1.28.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.velocity</groupId>
            <artifactId>velocity</artifactId>
            <version>1.7</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.54</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.6.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
        </dependency>

        <dependency>
            <groupId>org.webjars.npm</groupId>
            <artifactId>jquery</artifactId>
            <version>3.4.1</version>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-java8time</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>




    </dependencies>


    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.7</version>
            </plugin>

            <plugin>
                <groupId>org.asciidoctor</groupId>
                <artifactId>asciidoctor-maven-plugin</artifactId>
                <version>1.5.3</version>

                <dependencies>

                </dependencies>

            </plugin>
        </plugins>
    </build>


    </project>


########### Deployment Procedure
1. Configure project dependencies

   
2. Deployment Redis and MySQL in Server

   
3. Configure Spring (database, redis, server)

For Example:
   
    spring:
        datasource:
            driver-class-name: com.mysql.cj.jdbc.Driver
            username: root
            password: 123456
            url: jdbc:mysql://127.0.0.1:3306/queue?characterEncoding=utf-8&useSSL=false&&serverTimezone=GMT%2B8
        redis:
            database: 0
            host: "192.168.75.128"
            port: 6379
            password: "12345"
    server:
        port: 8080
        servlet:
            context-path: /queue
   


4. Sa-Token configuration (For account auth)

Example:

    # Sa-Token configuration
    sa-token:
    # Token name (also cookie name)
    token-name: satoken
    # Token validity period (unit: s) 30 days by default. -1 indicates that the token will never expire
    timeout: 2592000
    # Token validity period (If no operation is performed within the specified period, the token expires.) Unit: second
    activity-timeout: -1
    # Whether to allow concurrent logins with the same account (true allows simultaneous logins, false allows new logins to crowd out old logins)
    is-concurrent: true
    # Whether to share a token when multiple users log in to the same account (If the value is true, all logins share one token; if the value is false, one token is created for each login)
    is-share: false
    # token style
    token-style: uuid
    # Whether to output operation logs
    is-log: false


5. mybatis plus settings

Example:
    
    #mybatis plus settings
    mybatis-plus:
        mapper-locations: classpath*:uofg/zehuilyu/queueapi/**/xml/*Mapper.xml
        global-config:
            # close MP3.0's banner
            banner: false
        db-config:
            #Primary key type 0:" database ID increment ",1:" This type is not set primary key type ", 2:" user input ID",3:" globally unique ID (number type unique ID)", 4:" globally unique ID UUID",5:" string globally unique ID (idWorker string representation)";
            id-type: ASSIGN_ID
            # The default database table is named with an underscore
            table-underline: true
        configuration:
        # This configuration prints out the EXECUTED SQL for use during development or testing
        # log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
        # The return type is Map, and the field corresponding to NULL is displayed
            call-setters-on-nulls: true
   
   


########### API Document Getting
/queue/swagger-ui.html
For example, in port 8080 of localhost: http://localhost:8080/queue/swagger-ui.html

###########Test Data (SQL)
This project provides a set of test data. Please see:

\src\main\resources\static\testSQL\test.sql


########### How to Invoking Queue System API

This guide use pop interface as example:

    Queue Pop: /api/v1/queue/pop 
    Description: the first customer of the waiting queue exits the queue for the next step to get the service. 
    Request type: PATCH. The parameter description: the String type, the id of the service window. 
    Request URL Example: http://localhost:8080/api/v1/queue/pop?storeId=UofG 
    Response body: the customer's queuing information mainly includes the customer's id, the time the customer starts waiting, the customer's number and the name of the waiting window. Interact with the client in JSON. 
    For example: 
        { "queryUserId": "100004", 
        "queryTime": "2021-11-20 13:19:16",
        "queryName": "query-zset-UofG", 
        "queueNo": 200000}

