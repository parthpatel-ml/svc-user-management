## Data Base Design and Insta App design Videos

- Data base: https://www.youtube.com/watch?v=i_1CbyzzlDk
- App: https://www.youtube.com/watch?v=a-_WARUKcYs
- App: https://www.youtube.com/watch?v=YoS5cp0cirM

## Use Case of DTO Pattern & Interview Questions

https://medium.com/@ksaquib/leveraging-the-data-transfer-object-dto-pattern-in-spring-boot-enhancing-data-transfer-efficiency-5bd2dc488d0d

## Enum Mappings with Hibernate – The Complete Guide

https://medium.com/quick-code/all-about-using-enums-in-hibernate-96b72b469836

https://thorben-janssen.com/hibernate-enum-mappings/#:~:text=By%20default%2C%20Hibernate%20maps%20an,to%201%20and%20so%20on.

## @MappedSuperclass

- Consider using @MappedSuperclass for common attributes that should be inherited by multiple entities.

## spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

- This property specifies the Hibernate dialect to be used for communicating with the MySQL database.

## spring.jpa.hibernate.ddl-auto=update

- This property determines how Hibernate handles the database schema during application startup. The value “update”
  indicates that Hibernate should try to update the database schema based on the entities defined in your application.

## Repository interface

- The generic parameters <Employee, Long> indicate that the repository will be dealing with objects of type Employee and
  their unique identifier will be of type Long.
- When we extend the JpaRepository interface in our EmployeeRepository interface, Spring Data JPA automatically creates
  an instance of SimpleJpaRepository and assigns it to the EmployeeRepository bean at runtime. This means that when we
  use the EmployeeRepository interface in our code, the methods defined in the JpaRepository interface (including the
  ones inherited from CrudRepository and PagingAndSortingRepository) will be implemented by the SimpleJpaRepository
  class.

## Why we need to serialized Model classes in Hibernate, Java, Spring Boot project?

In Hibernate, Java, and Spring Boot projects, model classes (also known as entities or domain objects) are often *
*serializable** for several reasons, related to both the specific requirements of the frameworks involved and best
practices in object-oriented programming. Serialization is the process of converting an object into a byte stream that
can be persisted to a storage medium (e.g., file system, database) or transferred over a network, and it helps in
various contexts in these projects.

### Key Reasons for Serialization of Model Classes:

#### 1. **Hibernate Caching Mechanisms**

- **Hibernate Level 2 Cache**: Hibernate's second-level cache can cache entities between sessions, making it possible to
  avoid hitting the database repeatedly for frequently accessed data. Since caches typically store data outside of the
  JVM memory (in external cache providers like Ehcache or Hazelcast), objects need to be serializable to be properly
  stored and retrieved.
- **Entity Serialization for Distributed Caching**: When a Hibernate entity is cached in a distributed cache, it might
  be stored in an external cache provider. For this reason, entities must implement `Serializable` so they can be
  converted into byte streams and transmitted across different systems or retrieved from external caches.

#### 2. **Session Replication in Distributed Systems**

- In a Spring Boot project deployed in a distributed environment (like multiple instances in a microservices
  architecture), entities might need to be serialized to replicate session data across different instances of an
  application.
- If an entity is stored in an HTTP session, and that session is replicated across multiple nodes, the entity must be
  serializable to ensure it can be transferred across different instances of the application.

#### 3. **Object Serialization for Remote Communication (RPC)**

- In cases where model objects are passed between different services or systems (e.g., using RMI, JMS, or other remote
  procedure call mechanisms), they need to be serialized to be transferred over the network. This is particularly
  relevant in distributed architectures like microservices.
- **REST and Microservices**: Although REST primarily uses JSON for object transmission, there are some situations where
  Java object serialization might still be needed, such as when entities are sent to other JVM-based services using
  other communication protocols.

#### 4. **Java Bean Convention**

- By default, Java Beans (which are often used as model classes in Spring applications) are expected to be serializable.
  While this isn't a strict requirement, following this convention can provide better integration with certain Java
  libraries and frameworks that expect beans to be serializable.

#### 5. **Performance Optimization in Stateful EJB or HTTP Sessions**

- In enterprise applications using stateful Enterprise JavaBeans (EJB) or HTTP sessions, the application might need to
  serialize objects to persist the session state. In Spring-based web applications, model classes might need to be
  serializable if they're stored in the session scope.
- For example, if a user session contains an entity (like a `User` object), and that session is passivated or
  persisted (e.g., written to disk or moved to a different server), the entity will need to be serialized to maintain
  state.

#### 6. **Spring Framework Requirements**

- **Distributed Systems**: In distributed applications, the ability to serialize objects is crucial for inter-service
  communication, especially when using Spring Boot with cloud-based infrastructures (like AWS, GCP) or microservices.
- **Exception Handling & Transaction Management**: When a model class is involved in remote exceptions or rollback
  scenarios during transaction management, having serializable entities helps in properly handling and logging the
  exception state across services.

### Example of Making a Model Serializable:

```java
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    // Getters and setters
}
```

### Important Notes:

- **Serializable Interface**: The `Serializable` interface in Java is a **marker interface**, which means it doesn't
  have any methods to implement. Its purpose is simply to indicate that the class can be serialized.

- **Transient Fields**: If certain fields in the model class (like passwords or sensitive data) shouldn't be serialized,
  you can mark them as `transient`, which will exclude them from the serialization process:

    ```java
    private transient String password;
    ```

### Conclusion:

Making model classes serializable in Hibernate, Java, and Spring Boot projects is essential for distributed caching,
session replication, and remote communication in distributed systems. Even though not all applications require
serialization for every model class, doing so ensures compatibility with various frameworks and improves the flexibility
of your application, especially in large-scale and distributed systems.

##  