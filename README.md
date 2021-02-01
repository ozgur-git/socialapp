# General Info
It is a Twitter-like basic social networking web project presented in the book Learning PHP, MySQL, 
JavaScript, CSS & HTML5, Third Edition, by O’Reilly, originally written in vanilla PHP/JavaScript
with HTML/CSS on an LAMP platform.
Users can sign up with their  own usernames and passwords, login, create profiles, upload profile photos, follow  
and unfollow other users, send private messages to friends or public messages.
All usernames, passwords, public/private and profile messages are stored in MySQL database tables.

**I rewrote the PHP code in Java using the Spring MVC framework and tested successfully using the localhost 
Tomcat Server, and then reconfigured deployment with separate Docker containers as the Tomcat Server in one 
container and the MySQL Server in another container instead of the LAMP platform. 
The application is then deployed successfully to AWS cloud such that the Tomcat container is deployed using ner Service)/EC2 configuration
AWS ECS (Electronic Container Service) with AWS RDS as the database service.**

# Technology
- Java
- Spring MVC
- Apache Tomcat
- MySQL
- JavaScript
- HTML
- CSS

# Setup
Deploy it to Apache Tomcat by following commands
- mvn clean install
- mvn tomcat7:deploy



