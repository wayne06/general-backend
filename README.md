# General backend based on SpringBoot

> by [Wayne Zhong](https://github.com/wayne06)

Initial template for projects base on Java & SpringBoot, integrating common frameworks and sample code for common business.
Only 1 minute to build the back-end of the content site. You can also quickly develop your own projects based on it.

[toc]

## Features

### Frameworks

- Spring Boot 2.7.x
- Spring MVC
- MyBatis + MyBatis Plus
- Spring Boot Debugging tools and Project Processors
- Spring AOP
- Spring Scheduler
- Spring Transaction Annotations

### Data Storage

- MySQL
- Redis
- Elasticsearch
- Tencent Cloud COS Object storage

### Tools

- Easy Excel
- Hutool
- Apache Commons Lang3
- Lombok

### Business Feature

- Spring Session Redis(Distributed login)
- Global request-response interceptor(logging)
- Global exception handler
- Customized Error Codes
- Generic response class
- Swagger + Knife4j
- Customized Permission annotations + Global checksums
- Global Cross-domain handling
- Long integer loss of precision
- Multi-environment configuration

## Business Functions

- Provide sample SQL (users, posts, likes, favorites)
- User login, registration, logout, update, search, permission management.
- Post creation, deletion, editing, updating, database search, ES flexible search.
- Post Likes, Unlikes
- Post Favorite, UnFavorite, Search Favorite Posts
- Full synchronization of posts with ES, incremental synchronization of ES timed tasks.
- Support WeChat open platform login
- Support WeChat public number subscription, sending/receiving messages, setting menu.
- Support uploading files by business.

### Unit Test

- JUnit5
- Example unit test classes

### Architecture

- Layering

## Getting Started

> All the changes that need to be made are marked with a `todo` to make it easier to find where they should be made.

### MySQL

1. Modify the database configuration to your own in `application.yml`.

    ```yml
        spring:
          datasource:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://localhost:3306/my_db
            username: root
            password: 123456
    ```

2. Execute the database statement in `sql/create_table.sql` to create the tables.

3. Run the project, visit `http://localhost:8101/api/doc.html` to open the interface documentation, you do not need to write the front-end to debug the interface online.

![](doc/swagger.png)

### Redis Distributed Login

1. Modify the Redis configuration to your own in `application.yml`.

    ```yml
        spring:
          redis:
            database: 1
            host: localhost
            port: 6379
            timeout: 5000
            password: 123456
    ```

2. Modify the session store-type in `application.yml`.

    ```yml
        spring:
          session:
            store-type: redis
    ```

3）Remove the exclude parameter in the `@SpringBootApplication` annotation at the beginning of the `MainApplication` class.

Before:
    ```java
        @SpringBootApplication(exclude = {RedisAutoConfiguration.class})
    ```

After:
    ```java
        @SpringBootApplication
    ```

### Elasticsearch search engine

1. Modify the Elasticsearch configuration to your own in `application.yml`.
    ```yml
        spring:
          elasticsearch:
            uris: http://localhost:9200
            username: root
            password: 123456
    ```

2. Copy the contents of the `sql/post_es_mapping.json` file and create the index by calling Elasticsearch interface or the Kibana Dev Tools(equivalent to creating tables in a database).
    ```
        PUT post_v1
        {
         (See `sql/post_es_mapping.json` file for parameters.)
        }
    ```

3. Start a synchronized task to synchronize the posts from database to elasticsearch. Find the `FullSyncPostToEs` and `IncSyncPostToEs` files in directory `job`, uncomment the `@Component`, run the program again to trigger the synchronization.

    ```java
        // todo 取消注释开启任务
        //@Component
    ```
