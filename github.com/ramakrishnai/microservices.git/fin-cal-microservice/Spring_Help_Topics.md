URIs
----
http://localhost:8084/hello-world-bean


GET 	- /users
POST 	- /users
GET		- /users/{id}	- /users/1 	-> for one user
DELETE	- /users/{id}	- /users/1	-> delete one user

Retrieve all posts for a user 	- 	GET  - /users/{id}/  	-> /users/1/posts
create a posts for a user 		-	POST -/users/{id}/posts
Retrieve details of a post		-  	GET  -/users/{id}/posts/{post_id} -> /users/1/posts/1005


Practical:
---------
	- Internationalization
	- Generating Documentation for these services
	- Validation
	- Exception Handling
	- Versioning Your Service
	- Content negotiation
	- How to monitoring the services
	- HATEOAS 
	
	
Questions & Answers
-------------------
	- Debug Mode?:  logging.level.org.springframework = debug
	- How HTTP Message Converters? through Auto configuration: JSON to Object happens
	- Error Mapping by Spring Boot
	- Initial Request goes to DispatcherServlet handling the http request as Front Controller
	- DispatcherServlet looks right mapping through controller - RequestMapping methods. 


Database:
--------
create table user (id integer not null, birth_date timestamp, name varchar(255), primary key (id))



	
	
	
	
	
	
	
	