## Data Base Design and Insta App design Videos

- Data base: https://www.youtube.com/watch?v=i_1CbyzzlDk
- App: https://www.youtube.com/watch?v=a-_WARUKcYs
- App: https://www.youtube.com/watch?v=YoS5cp0cirM

## Use Case of DTO Pattern & Interview Questions

https://medium.com/@ksaquib/leveraging-the-data-transfer-object-dto-pattern-in-spring-boot-enhancing-data-transfer-efficiency-5bd2dc488d0d

## Enum Mappings with Hibernate – The Complete Guide

https://medium.com/quick-code/all-about-using-enums-in-hibernate-96b72b469836

https://thorben-janssen.com/hibernate-enum-mappings/#:~:text=By%20default%2C%20Hibernate%20maps%20an,to%201%20and%20so%20on.

| No     | Topic Name                                                                                                                                                                                          |
|--------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1.0.1  | @MappedSuperclass                                                                                                                                                                                   |
| 1.0.2  | spring.jpa.properties.hibernate.dialect                                                                                                                                                             |
| 1.0.3  | spring.jpa.hibernate.ddl-auto                                                                                                                                                                       |
| 1.0.4  | Repository interface                                                                                                                                                                                |
| 1.0.5  | Why we need to serialized Model classes in Hibernate, Java, Spring Boot project?                                                                                                                    |
| 1.0.6  | Why in Model class or Entity Class primary key parameter ID has type of Wrapper class object? And Why we need to use serialVersionUID in Entity Class?                                              |
| 1.0.7  | Programming to an Interface Design Principle in Spring Boot Application                                                                                                                             |
| 1.0.8  | What happen if we have same value for serialVersionId in all entity class                                                                                                                           | 
| 1.0.9  | I ran the Spring Boot application. got log like this, Starting Application using Java 21.0.2 with PID 27272  What is PID here?                                                                      
| 1.0.10 | Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable                                                                                                       |
| 1.0.11 | what is the meaning of below log,HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default) |
| 1.0.12 | @Converter use for? jakarta.persistence.AttributeConverter.                                                                                                                                         |
| 1.0.13 |                                                                                                                                                                                                     |

## 1.0.1 @MappedSuperclass

- Consider using @MappedSuperclass for common attributes that should be inherited by multiple entities.

## 1.0.2 spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

- This property specifies the Hibernate dialect to be used for communicating with the MySQL database.

## 1.0.3 spring.jpa.hibernate.ddl-auto=update

- This property determines how Hibernate handles the database schema during application startup. The value “update”
  indicates that Hibernate should try to update the database schema based on the entities defined in your application.

## 1.0.4 Repository interface

- The generic parameters <Employee, Long> indicate that the repository will be dealing with objects of type Employee and
  their unique identifier will be of type Long.
- When we extend the JpaRepository interface in our EmployeeRepository interface, Spring Data JPA automatically creates
  an instance of SimpleJpaRepository and assigns it to the EmployeeRepository bean at runtime. This means that when we
  use the EmployeeRepository interface in our code, the methods defined in the JpaRepository interface (including the
  ones inherited from CrudRepository and PagingAndSortingRepository) will be implemented by the SimpleJpaRepository
  class.

## 1.0.5 Why we need to serialized Model classes in Hibernate, Java, Spring Boot project?

In Hibernate, Java, and Spring Boot projects, model classes (also known as entities or domain objects) are often
**serializable** for several reasons, related to both the specific requirements of the frameworks involved and best
practices in object-oriented programming. **Serialization is the process of converting an object into a byte stream that
can be persisted to a storage medium (e.g., file system, database) or transferred over a network, and it helps in
various contexts in these projects.**

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

## 1.0.6 Why in Model class or Entity Class primary key parameter ID has type of Wrapper class object? And Why we need to use serialVersionUID in Entity Class?

### 1. **Why is the Primary Key (`ID`) in Model/Entity Class a Wrapper Class (`Long` or `Integer`) instead of a

Primitive Type?**

In Java, it is common practice to use wrapper classes (`Long`, `Integer`) instead of primitive types (`long`, `int`) for
primary key fields (`ID`) in entity classes. There are several reasons for this choice, especially when working with
Hibernate, JPA, and Spring Boot:

#### Key Reasons for Using Wrapper Classes (`Long`, `Integer`) for Primary Key Fields:

#### a. **Nullable Values (Handling Nulls)**

- **Primitive types (`long`, `int`)**: Primitives cannot be `null`. By default, primitive types have a value (e.g., `0`
  for `int` or `long`).
- **Wrapper classes (`Long`, `Integer`)**: These can be `null`. This is important because, in JPA/Hibernate, before the
  entity is persisted to the database, the primary key value may not be set (i.e., it can be `null`). Using a wrapper
  class allows you to represent the uninitialized state of the primary key before Hibernate assigns a value (usually
  via `@GeneratedValue`).

**Example:**

   ```java

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Can be null before persistence
}
   ```

**Why this is useful**: When a new entity is created but has not yet been persisted, it doesn't have a
database-generated primary key (`id`). Using a `Long` (wrapper) allows `id` to be `null`, representing an uninitialized
state.

#### b. **ORM Compatibility**

- Hibernate, JPA, and other ORMs (Object Relational Mapping tools) often rely on nullability for primary keys before
  persisting objects. For example, when you persist an entity, the primary key field might remain `null` until the
  insert operation is completed and the database assigns the actual ID.
- Using primitive types like `int` or `long` would create issues because `null` is often needed to signify that the
  entity is new and has not yet been saved.

#### c. **Consistency with Databases**

- In most databases, primary keys are often `nullable` before being set by auto-increment, sequences, or custom
  generation strategies. Using a `Long` (or `Integer`) provides a cleaner mapping between the Java entity and the
  database.

#### d. **Generic Methods and Frameworks**

- Many libraries and frameworks are designed to work with the `Object` type rather than primitives. If you use `Long`
  or `Integer`, it provides better compatibility with methods that use generics or collections, which rely on objects (
  e.g., `List<Long>`).

#### e. **Better Compatibility with Hibernate Proxies**

- Hibernate uses proxy objects internally for lazy loading and managing entities. Proxying often works better with
  wrapper types (`Long`, `Integer`) because these objects may need to handle a `null` state during lazy initialization.

### 2. **Why Do We Use `serialVersionUID` in Entity Classes?**

The `serialVersionUID` is a unique identifier used in Java's serialization process. If a class
implements `Serializable`, it is recommended to define a `serialVersionUID` field, especially for entity classes. Here's
why it is useful in Hibernate and Spring Boot projects:

#### What is `serialVersionUID`?

- `serialVersionUID` is a long value that serves as a **version control identifier** for classes that implement
  the `Serializable` interface. When an object is serialized (converted into a byte stream) and then deserialized (
  reconstructed from that byte stream), Java ensures that the class version (based on `serialVersionUID`) matches
  between the serialized and deserialized object.

#### Key Reasons for Using `serialVersionUID` in Entity Classes:

#### a. **Serialization in Distributed Systems**

- In applications that use distributed caching (e.g., Hibernate’s second-level cache, session replication), entities may
  need to be serialized to be transferred or stored. A mismatch in `serialVersionUID` can cause `InvalidClassException`
  during deserialization if the class definition changes between the time the object was serialized and deserialized.

**Example Scenario:**
If your application serializes objects (e.g., session replication between servers in a microservices setup), having a
consistent `serialVersionUID` ensures that the deserialization process works correctly even if minor class changes
occur.

#### b. **Class Version Control**

- If you modify an entity class after it has been serialized (e.g., add or remove fields), the deserialization process
  can fail unless the `serialVersionUID` is explicitly defined. Java automatically generates a `serialVersionUID` based
  on class structure, but this can change with even minor modifications to the class.
- Defining a `serialVersionUID` allows you to maintain backward compatibility across versions of the class, so minor
  changes do not break deserialization.

#### c. **Preventing Deserialization Failures**

- If `serialVersionUID` is not explicitly defined, Java generates one automatically based on the class structure. Any
  small change in the structure (e.g., adding/removing a field) can change the generated `serialVersionUID`, causing
  deserialization failures (`InvalidClassException`) in distributed or serialized environments.
- By explicitly setting `serialVersionUID`, you can prevent such issues and ensure that your application can deserialize
  objects even after minor changes to the class.

#### d. **Best Practice for Serializable Classes**

- Even though an entity class is not always explicitly serialized (unless used in specific contexts like session
  replication or caching), it’s a good practice to include `serialVersionUID` in any class that
  implements `Serializable` to ensure proper handling of serialization.

### Example with `serialVersionUID`:

```java
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;  // Unique identifier for the class version

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

### Conclusion:

- **Wrapper Classes for Primary Keys**: `Long` or `Integer` are used for primary keys because they allow null values,
  which is important for the lifecycle of entities (e.g., before they are persisted). This also aligns well with ORM
  tools like Hibernate, which rely on nullability to determine new or existing records.

- **`serialVersionUID`**: It’s a best practice to include `serialVersionUID` in entity classes that
  implement `Serializable`. This helps ensure backward compatibility, prevent deserialization issues, and
  avoid `InvalidClassException` when working with caching, session replication, or distributed systems.

## 1.0.7 Programming to an Interface Design Principle in Spring Boot Application |

### **Programming to an Interface Design Principle in Spring Boot Application**

The "Programming to an Interface" design principle is a key concept in object-oriented programming (OOP). It suggests
that you should depend on abstractions (interfaces) rather than concrete implementations. This principle helps achieve
loose coupling, better flexibility, and easier testing in applications.

In a Spring Boot application, this principle is commonly used to write more maintainable and testable code. When you
program to an interface, your code depends on an abstract interface, and the actual implementation can be changed or
extended without affecting the rest of the application.

### **Benefits of Programming to an Interface:**

1. **Loose Coupling**: The application becomes loosely coupled, meaning changes in one part (implementation) do not
   affect other parts (the interface users).
2. **Flexibility**: You can easily change or extend the implementation without changing the rest of the system.
3. **Testability**: Interfaces make it easier to test because mock implementations can be provided during unit tests.
4. **Maintainability**: Interfaces allow for better code organization, making it easier to maintain and update.

### **Example in Spring Boot:**

Let’s build a simple Spring Boot application where we apply this principle using a `PaymentService` interface, with two
different implementations: `CreditCardPaymentService` and `PayPalPaymentService`.

#### 1. **Define the Interface (Abstraction):**

```java
public interface PaymentService {
    String processPayment(double amount);
}
```

The `PaymentService` interface defines the contract, which is a `processPayment` method. Any class that implements this
interface must provide an implementation for this method.

#### 2. **Create Implementations of the Interface:**

- **First Implementation (`CreditCardPaymentService`):**

```java
import org.springframework.stereotype.Service;

@Service("creditCardPaymentService")
public class CreditCardPaymentService implements PaymentService {

    @Override
    public String processPayment(double amount) {
        return "Payment of $" + amount + " processed via Credit Card.";
    }
}
```

- **Second Implementation (`PayPalPaymentService`):**

```java
import org.springframework.stereotype.Service;

@Service("payPalPaymentService")
public class PayPalPaymentService implements PaymentService {

    @Override
    public String processPayment(double amount) {
        return "Payment of $" + amount + " processed via PayPal.";
    }
}
```

Both `CreditCardPaymentService` and `PayPalPaymentService` classes implement the `PaymentService` interface. The client
using the service will only depend on the `PaymentService` interface, without knowing which implementation is used.

#### 3. **Controller Using the Interface (Programming to an Interface):**

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(@Qualifier("creditCardPaymentService") PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public String pay(@RequestParam double amount) {
        return paymentService.processPayment(amount);
    }
}
```

Here:

- **Dependency Injection (DI)**: Spring Boot injects the implementation of `PaymentService` into `PaymentController`
  using constructor injection.
- **`@Qualifier`**: We use `@Qualifier` to specify which implementation to inject (in this
  case, `creditCardPaymentService`). If we wanted to switch to the `PayPalPaymentService`, we could simply change the
  qualifier to `"payPalPaymentService"`.

#### 4. **Switching the Implementation Dynamically:**

To demonstrate how flexible this design is, let’s modify the controller to dynamically switch between payment methods
based on a request parameter.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    @Qualifier("creditCardPaymentService")
    private PaymentService creditCardPaymentService;

    @Autowired
    @Qualifier("payPalPaymentService")
    private PaymentService payPalPaymentService;

    @GetMapping("/pay")
    public String pay(@RequestParam double amount, @RequestParam String method) {
        if ("paypal".equalsIgnoreCase(method)) {
            return payPalPaymentService.processPayment(amount);
        } else {
            return creditCardPaymentService.processPayment(amount);
        }
    }
}
```

In this example:

- The client can specify a payment method (`paypal` or `creditcard`), and the application will choose the
  appropriate `PaymentService` implementation dynamically at runtime.
- The **controller** still programs to the `PaymentService` interface and does not depend directly on the concrete
  implementation.

#### 5. **Unit Testing with Mocks:**

Since the `PaymentController` depends on the `PaymentService` interface, it's easy to mock the `PaymentService` for unit
testing without relying on real implementations.

```java
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @Test
    public void testPay() {
        MockitoAnnotations.openMocks(this);

        when(paymentService.processPayment(100.0)).thenReturn("Mock Payment Processed");

        String response = paymentController.pay(100.0, "creditcard");
        assertEquals("Mock Payment Processed", response);

        verify(paymentService).processPayment(100.0);
    }
}
```

In this test:

- **Mocking the Interface**: We mock the `PaymentService` interface, so we don't need to rely on any real payment logic.
- **Injecting the Mock**: Using `@InjectMocks`, the mocked `PaymentService` is injected into the `PaymentController`.
- This makes it easier to test the behavior of the controller without depending on the actual `PaymentService`
  implementation.

### **Conclusion:**

- **Programming to an Interface** in a Spring Boot application is a key design principle that promotes loose coupling,
  flexibility, and easier testing.
- The application depends on abstract interfaces (`PaymentService`) rather than concrete
  implementations (`CreditCardPaymentService` or `PayPalPaymentService`).
- This approach makes it easier to swap implementations, add new functionality, and write unit tests using mocks,
  resulting in cleaner and more maintainable code.

## 1.0.8 | What happen if we have same value for serialVersionId in all entity class

If you have 100 entity classes in a Spring Boot and Hibernate application, and all of them share the same value
for `serialVersionUID`, this can lead to issues related to serialization compatibility, especially when your application
needs to serialize and deserialize those entities, either for caching, session replication, or other purposes.

Let's explore what happens when all your entity classes have the same `serialVersionUID` value and how it might affect
the behavior of your application.

### 1. **What is `serialVersionUID`?**

The `serialVersionUID` is a unique identifier for each serializable class in Java. It is used during the deserialization
process to ensure that the sender and receiver of a serialized object are compatible with respect to the object
structure.

When an object is serialized, its `serialVersionUID` is included in the byte stream. During deserialization,
the `serialVersionUID` of the class is compared to the `serialVersionUID` embedded in the serialized data. If they
match, the object is deserialized; if they don't match, an `InvalidClassException` is thrown.

### 2. **Same `serialVersionUID` Across Multiple Classes:**

If you assign the same `serialVersionUID` to all 100 entity classes, here's what happens:

- **Serialization Process**: During serialization, Java does not check whether `serialVersionUID` values are unique
  across classes. It simply stores the class’s `serialVersionUID` along with the serialized data.
- **Deserialization Process**: When the serialized data is deserialized, Java will compare the `serialVersionUID` from
  the byte stream with the class’s `serialVersionUID`. If they match, deserialization proceeds normally, even if the two
  classes are different but have the same `serialVersionUID`.

### 3. **Potential Issues**:

Having the same `serialVersionUID` across different classes can cause the following issues:

- **Serialization Confusion**: During deserialization, if Java encounters an object stream with a `serialVersionUID`
  that matches a different class with the same `serialVersionUID`, the JVM might attempt to deserialize the object into
  the wrong class, resulting in unexpected behavior or exceptions.

- **Class Mismatch**: Since the `serialVersionUID` is the only identifier used during deserialization, if two different
  classes share the same `serialVersionUID`, Java could incorrectly deserialize an object into the wrong class. This
  could lead to `ClassCastException`, incorrect data, or logical errors in your application.

- **InvalidClassException**: If the class structure changes, and you expect Java to throw an `InvalidClassException` due
  to version mismatch, that might not happen. Java will assume the classes are compatible because the `serialVersionUID`
  is the same, leading to potential data corruption.

### 4. **Example of a Problematic Scenario**:

Consider the following scenario where two different entity classes share the same `serialVersionUID`:

```java

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String name;
    private String email;
}

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String productName;
    private Double price;
}
```

If you serialize an instance of the `User` class and later deserialize it into the `Product` class (because both have
the same `serialVersionUID`), you could run into unexpected behavior, such as:

- **ClassCastException**: Attempting to cast a `Product` object to `User` after deserialization.
- **Incorrect Field Population**: Since the object structure differs, fields in the `User` class may map incorrectly or
  not at all to fields in the `Product` class, leading to incorrect data processing.

### 5. **Why Unique `serialVersionUID` is Important:**

Each class should have its own unique `serialVersionUID` to:

- Ensure that during deserialization, Java correctly matches the serialized data to the appropriate class.
- Prevent unexpected behaviors, such as attempting to deserialize an object into the wrong class or accepting an
  incompatible class structure during deserialization.
- Enable Java to detect incompatible changes between class versions and throw an `InvalidClassException` when necessary.

### 6. **Automatic `serialVersionUID` Generation:**

If you do not explicitly define a `serialVersionUID`, the Java compiler will automatically generate one based on the
class’s structure. However, this approach has risks:

- If you make changes to the class (like adding fields, changing method signatures, etc.), the
  generated `serialVersionUID` will change, potentially making previously serialized objects incompatible with the new
  class version.

Therefore, it's a good practice to manually define a unique `serialVersionUID` for each class to ensure compatibility
across different versions of the class.

### 7. **Best Practices for `serialVersionUID` in Large Applications:**

- **Unique `serialVersionUID`**: Each class should have its own unique `serialVersionUID` to ensure that different
  classes are not confused during the serialization/deserialization process.
- **Version Control**: Manually assign `serialVersionUID` values to your entities. When making backward-compatible
  changes to a class, you can keep the same `serialVersionUID`. When making incompatible changes, change
  the `serialVersionUID` to avoid deserialization issues.
- **Use IDEs for Generation**: Most modern IDEs like IntelliJ IDEA or Eclipse can automatically generate a
  unique `serialVersionUID` for each class.

### Conclusion:

If all 100 entity classes in your Spring Boot, Hibernate application share the same `serialVersionUID`, it could lead to
significant issues during serialization and deserialization, especially if objects of different classes are mixed up.
Each class should have its own unique `serialVersionUID` to ensure proper serialization compatibility, data integrity,
and prevent potential runtime exceptions.

## 1.0.9 I ran the Spring Boot application. got log like this, Starting Application using Java 21.0.2 with PID 27272  What is PID here?

The **PID** in the log (`Starting Application using Java 21.0.2 with PID 27272`) stands for **Process ID**. It is a
unique identifier assigned by the operating system to every running process, including your Spring Boot application.

In this context:

- **Java 21.0.2**: The version of Java you're using to run the Spring Boot application.
- **PID 27272**: The process ID of your Spring Boot application.

### Why PID is Important:

- **Monitoring**: You can use the PID to monitor the application’s resource usage, such as CPU and memory.
- **Process Management**: If you need to stop or debug the application, you can refer to the PID. For example, you can
  kill the process using the `kill` command (on Unix-like systems) or use Task Manager (on Windows) to stop the process
  by its PID.
- **Logging**: In large systems or environments where multiple instances of the same application might be running, the
  PID can help identify which specific instance the logs are referring to.

### Example Commands:

- On **Linux/Mac**: You can find more details about the process using the command:
  ```bash
  ps -p 27272
  ```
- On **Windows**: You can find the process using the PID in Task Manager (under the "Details" tab).

The PID is automatically generated by the operating system when you run your Spring Boot application, and it's helpful
for system administration and troubleshooting.

## 1.0.10 Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable

The log message:

```
Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
```

indicates that **Spring Boot DevTools** is active in your application. DevTools is a developer tool that enhances the
development experience by providing features like automatic restarts, live reloading, and property configuration
defaults tailored for development environments.

### Explanation of the Log:

- **"Devtools property defaults active"**: This means that Spring Boot's DevTools is applying certain property defaults
  that are optimized for a faster and more convenient development process.

- **spring.devtools.add-properties**: This property controls whether DevTools should automatically add certain default
  properties to enhance the development experience. These properties are typically:
    - Faster reloads for code changes (e.g., `spring.thymeleaf.cache=false`, `spring.h2.console.enabled=true`).
    - Enhanced error pages (development-friendly error pages).
    - Automatic H2 Console activation (if using an in-memory database).

- **Set 'spring.devtools.add-properties' to 'false' to disable**: If you do **not** want DevTools to apply these default
  properties, you can set this property to `false` in your `application.properties` or `application.yml` file, as shown:

  ```properties
  spring.devtools.add-properties=false
  ```

### Why This Happens:

- **Spring Boot DevTools** is typically included in the `pom.xml` or `build.gradle` as a development dependency.

- It automatically activates some useful configurations that are intended to improve productivity during development.
  These settings help speed up development but might not be suitable for production.

### Example Use Case:

- During development, the auto-restart feature of Spring Boot DevTools helps by automatically restarting the application
  when there are changes in the code. These restarts will pick up the changes without needing to manually stop and start
  the application.

- The above log notifies you that such properties have been activated by default and provides the option to disable them
  if not needed.

### Conclusion:

The log message informs you that DevTools has activated its default settings to enhance the development experience. If
you don’t want these settings or are preparing the application for production, you can disable them by
setting `spring.devtools.add-properties` to `false`.

## 1.0.11 what is the meaning of below log,HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)

The log message:

```
HHH90000025: PostgreSQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
```

is related to Hibernate's configuration in your Spring Boot application, specifically the `hibernate.dialect` property.
Here's a breakdown of what it means:

### Explanation:

- **`HHH90000025`**: This is a Hibernate log code, where `HHH` refers to Hibernate, and `90000025` is the specific
  message identifier.

- **`PostgreSQLDialect does not need to be specified explicitly`**: Hibernate is telling you that it can automatically
  detect and configure the appropriate dialect for your database (in this case, PostgreSQL) without needing to
  explicitly define the dialect in your configuration file.

- **`hibernate.dialect`**: This is a Hibernate property that tells Hibernate which SQL dialect to use when interacting
  with the database. Each database (e.g., PostgreSQL, MySQL, Oracle) has its own specific SQL dialect that defines how
  SQL queries are generated and executed.

### Why This Happens:

- In earlier versions of Hibernate, it was common to explicitly set the `hibernate.dialect` property in your
  configuration to specify the database type. For example:

  ```properties
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  ```

- **Modern Hibernate versions (5.2 and later)** include the ability to auto-detect the dialect based on the JDBC
  connection metadata. This means Hibernate can automatically figure out which dialect to use by inspecting the
  underlying database connection. Since you're using PostgreSQL, Hibernate can detect that and automatically apply
  the `PostgreSQLDialect` without needing explicit configuration.

### What You Can Do:

- If you're currently specifying the `hibernate.dialect` property in your configuration (such as
  in `application.properties` or `application.yml`), you can **safely remove it**. Hibernate will automatically detect
  and configure the correct dialect based on your database connection.

  For example, if you have this configuration:

  ```properties
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
  ```

  You can remove that line, and Hibernate will still use `PostgreSQLDialect` automatically.

### Impact on Application:

- **No negative impact**: Removing the `hibernate.dialect` property won't cause any issues because Hibernate is capable
  of selecting the correct dialect on its own.
- **Simplifies configuration**: By allowing Hibernate to auto-detect the dialect, you can reduce configuration
  complexity and make your application more portable across databases (if needed).

### Conclusion:

The log message is a reminder that you no longer need to explicitly specify the `hibernate.dialect` property for
PostgreSQL (or most other modern databases). Hibernate can automatically detect the correct dialect based on the JDBC
connection, so you can remove the `hibernate.dialect` configuration for PostgreSQL from your application properties.

## 1.0.12 @Converter use for? jakarta.persistence.AttributeConverter.

The `@Converter` annotation in Java is used in **JPA (Java Persistence API)** to define a custom converter that allows
you to transform the data between the entity attribute and the database column. It helps in converting Java types that
are not directly supported by the database into a database-compatible format, and vice versa.

### Key Points of `@Converter`:

- It is part of **JPA 2.1** and later versions.
- It is typically used to **convert** between an entity field type (in Java) and a corresponding database column type (
  in SQL).
- You can use it for **basic types**, **enums**, or **complex objects** that are stored as a single column in the
  database (e.g., converting a `String` to an `enum` or a `LocalDateTime` to a `Timestamp`).

### How to Use `@Converter`:

#### 1. **Creating a Custom Converter:**

You create a class that implements the `javax.persistence.AttributeConverter<X, Y>` interface, where `X` is the Java
type and `Y` is the database type.

#### Example:

Converting a **Boolean** to a **String** in the database:

```java
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }
}
```

In this example, the `BooleanToStringConverter` class converts a `Boolean` attribute to `"Y"` or `"N"` when saving to
the database, and it converts `"Y"` or `"N"` back to `Boolean` when reading from the database.

#### 2. **Using the Converter with an Entity:**

After creating the converter, you can apply it to an entity attribute by using the `@Convert` annotation.

```java
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;

    @Convert(converter = BooleanToStringConverter.class)
    private Boolean active;

    // getters and setters
}
```

In this `User` entity, the `active` field will be stored as `"Y"` or `"N"` in the database instead of a `Boolean`.

#### 3. **Automatic Application of Converters:**

If you want the converter to be applied automatically to all fields of a certain type across all entities, you can
set `@Converter(autoApply = true)`:

```java

@Converter(autoApply = true)
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {
    // implementation
}
```

With `autoApply = true`, any `Boolean` field in your JPA entities will automatically use this converter without needing
to specify `@Convert` on each field.

### Why Use `@Converter`?

- **Type Mapping**: To map Java types that are not directly supported by JPA or the database (e.g.,
  converting `LocalDateTime` to `Timestamp`).
- **Custom Formatting**: To store complex objects or custom formats in a single database column.
- **Enum Storage**: You can store enums as `String`, `Integer`, or other formats in the database.

#### Example: Storing `LocalDateTime` in a `Timestamp`:

```java

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }
}
```

This converter would automatically handle `LocalDateTime` to `Timestamp` conversions in all entities, making it easier
to work with Java 8+ time API in your database interactions.

### Conclusion:

- The `@Converter` annotation is used to define a custom attribute converter in JPA.
- It provides flexibility to map Java types to database-compatible formats.
- You can control how specific fields or types are saved and retrieved from the database by using the `@Convert`
  annotation or by setting `autoApply = true`.

## 1.0.13 