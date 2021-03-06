# EzjOOQ (Easy jOOQ) <br> ![Test](https://github.com/DanJSG/EzjOOQ/actions/workflows/build.yml/badge.svg) 
Simple CRUD Repository Wrapper for the jOOQ Library utilising Java Persistence API annotations. 
Makes basic CRUD operations extremely simple, leaving regular jOOQ DSL queries for the complex stuff. 

For more information on jOOQ, [view their docs here](https://www.jooq.org/doc/latest/manual-single-page/).

**This library is not officially associated with jOOQ in any way.**

## Installation with Maven
Add the following to the dependencies section of your pom.xml:

```xml
<dependency>
    <groupId>org.ezlibs</groupId>
    <artifactId>ezjooq</artifactId>
    <version>1.0.1</version>
</dependency>
```

This library also depends on jOOQ and the Java Persistence API, so ensure the following are also in your dependencies list:

```xml
<dependency>
    <groupId>org.jooq</groupId>
    <artifactId>jooq</artifactId>
    <version>3.14.9</version>
</dependency>
<dependency>
    <groupId>javax.persistence</groupId>
    <artifactId>javax.persistence-api</artifactId>
    <version>2.2</version>
</dependency>
```

Versions `3.x.x` of jOOQ should work with this library. Requires Java 8 or above.

## Basic Usage

### Annotating Your POJO Class

Annotate your classes with the `@Table` annotation and fields with the `@Column` annotation to let EzjOOQ know which table to use in your database. 
For example, a `User` class might look like:

```java
@Table(name = "users")
public class User {

    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
```

### Creating a Repository Instance

To instantiate a repository for performing CRUD operations on the `User` class:

```java
CrudRepository<User> repo = new PojoRepository<>(DSL.using(yourDataSource, SQLDialect.POSTGRES), User.class);
```

### Storing Entities

To store a single `User` object:

```java
User user = new User();
repo.create(user);
```

To store multiple `User` objects:

```java
List<User> users = new ArrayList<>();
// ... add users to list ... 
repo.createMany(users);
```

### Reading Entities

To read all users from the database:

```java
List<User> users = repo.readAll();
```

To read all users from the database where a specific condition is met:

```java
Condition condition = // jOOQ condition
List<User> users = repo.readAllWhere(condition);
```

To read one user from the database where a specific condition is met:

```java
Condition condition = // jOOQ condition
User user = repo.readOneWhere(condition);
```

### Updating Entities

To update a user in the database where a condition is met:

```java
User user = new User();
Condition condition = // jOOQ condition
boolean success = repo.updateWhere(user, condition);
```

### Deleting Entities

To delete a user from the database:

```java
User user = new User();
boolean success = repo.delete(user);
```

## License
This repository is available under the [MIT License](https://github.com/DanJSG/EzjOOQ/blob/main/LICENSE).
