This project is a REST API for a Blogging Platform, designed to manage blog creation and related functionalities. The key entities and their relationships include:
1.User:
One-to-Many relationship with Post (a user can create multiple posts).
Many-to-Many relationship with Roles (users can have multiple roles).

2.Post:
Many-to-One relationship with Category (a post belongs to one category).
One-to-Many relationship with Comments (a post can have multiple comments).
Many-to-one relationship with User (multiple posts belongs to one user).

3.Category:
Defines the grouping of posts.
one-to-many relationship with Post (single category can have mutiple posts) 

4.Comments:
Many-to-one relationshipe with Post (all comments of single post)
Associated with a single post to represent user feedback or comments.
The project provides endpoints to handle CRUD operations for users, posts, categories, roles, and comments, enabling a scalable and organized blogging platform.
