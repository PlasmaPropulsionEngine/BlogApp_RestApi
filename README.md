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
---
##ER Diagram
![imGE](/src/main/resources/static/img/BlogERdiagram.PNG)

## Features
- Provides RESTful endpoints to manage:
  - **Users** Endpoints
  ---
| HTTP Method | Endpoint             | Description                       | Request Body       | Response Body         |
|-------------|----------------------|-----------------------------------|--------------------|-----------------------|
| GET         | `/api/users`         | Fetch all users                  | None               | List of users         |
| POST        | `/api/users`         | Create a new user                | JSON with user data| Created user details  |
| GET         | `/api/users/{id}`    | Fetch a specific user by ID      | None               | User details          |
| PUT         | `/api/users/{id}`    | Update an existing user by ID    | JSON with updates  | Updated user details  |
| DELETE      | `/api/users/{id}`    | Delete a specific user by ID     | None               | Success message       |

  - **Posts**Endpoints
  - --
| HTTP Method | Endpoint                                                  | Description                       | Request Body       | Response Body         |
|-------------|---------------------------------------------------------- |---------------------------------- |--------------------|-----------------------|
| GET         | `/api/posts`                                              | Fetch all posts                   | None               | List of posts         |
| POST        | `/api//user/{UserId}/category/{CategoryId}/posts`         | Create a new post                 | JSON with post data| Created post details  |
| GET         | `/api/user/{userId}/posts`                                |Fetch a specific post by userID    | None               | Post details          |
| PUT         | `/api/posts/{postId}`                                     | Update an existing post by userID| JSON with updates   | Updated post details  |
| DELETE      | `/api/posts/{postId}`                                     | Delete a specific post by postID   | None               | Success message       | 
  - **Categories**Endpoints
  ---  
| HTTP Method | Endpoint                                                  | Description                       | Request Body            | Response Body         |
|-------------|---------------------------------------------------------- |---------------------------------- |-------------------------|-----------------------|
| GET         | `/api/category`                                           | Fetch all categories              | None                    | List of categories    |
| POST        | `/api/category`                                           | Create a new category              | JSON with category data| Created category category   |
| GET         | `/api//category/{id}`                                     |Fetch a specific category by categoryID | None               | category details       |
| PUT         | `/api//category/{id}}`                                    | Update an existing category by categoryID| JSON with updates | Updated category details  |
| DELETE      | `/api//category/{id}`                                     | Delete a specific category by categoryID   | None             | Success message      | 

  - **Comments**Endpoints
  ---
  | HTTP Method | Endpoint                    | Description                       | Request Body       | Response Body         |
|-------------|-------------------------------|-----------------------------------|--------------------|-----------------------|
|    POST      | `/api/post/{postId}/comments`| Fetch all Comments                | Json payload       | List of Comments of specific post |
|    DELETE    | `/api/comments/{commId}`     | Delete comment by commentId       | none               | success message  |


  - **Roles** 
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















