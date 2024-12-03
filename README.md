# Blogging Platform REST API

This project is a **REST API for a Blogging Platform**, designed to manage blog creation and related functionalities.

## Key Entities and Relationships

### 1. **User**
- **One-to-Many** relationship with **Post**: A user can create multiple posts.
- **Many-to-Many** relationship with **Roles**: Users can have multiple roles.

### 2. **Post**
- **Many-to-One** relationship with **Category**: A post belongs to one category.
- **One-to-Many** relationship with **Comments**: A post can have multiple comments.
- **Many-to-One** relationship with **User**: Multiple posts belong to a single user.

### 3. **Category**
- Defines the grouping of posts.
- **One-to-Many** relationship with **Post**: A single category can have multiple posts.

### 4. **Comments**
- **Many-to-One** relationship with **Post**: All comments belong to a single post.
- Represents user feedback or discussions for a specific post.

## Features
- Provides RESTful endpoints to manage:
  - **Users**
  - **Posts**
  - **Categories**
  - **Roles**
  - **Comments**
- Enables scalable and organized management of blogging functionalities.

---

This project lays the foundation for a robust blogging platform with clearly defined relationships between entities, ensuring maintainability and scalability.

## Dependencies Used

1. **spring-boot-starter-web**: For building RESTful web services.
2. **spring-boot-starter-data-jpa**: For database access and ORM using JPA.
3. **spring-boot-starter-security**: For securing the application with authentication and authorization.
4. **spring-boot-devtools**: For enabling hot-reloading during development.
5. **hibernate-validator**: For validation of entity fields.
6. **javax.validation**: Standard Java validation API.
7. **mysql-connector-j**: For connecting the application to a MySQL database.
8. **jsonwebtoken (JJWT)**: For implementing JWT-based authentication.
9. **modelmapper**: For mapping DTOs to entities and vice versa.
10. **lombok**: For reducing boilerplate code like getters, setters, constructors, etc.


![imGE](\images\BlogERdiagram.PNG)













