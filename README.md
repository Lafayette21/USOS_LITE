A final project for subject Advanced Web Technologies in my first semester of Masters Degree pursue in 2022/2033 academic year. Inspiration for this project was USOS system.

Backend part of the project is implemented in Spring Boot with Java language and H2 database.
Frontend part is implementd in Angular with Typescript

Running application: 
Aplication uses docker contenerization for building and running. In order to build the app:
1. Navigate to each server folder
2. Run in terminal **docker: build -t _your-name_ .**
3. Run in terminal **docker run -p _port_:_port_ _your-name_**

For easier building here are the commands:  
-Frontend:  
  >  docker build -t usoslite-frontend .  
  >  docker run -p 4200:4200 usoslite-frontend  
  
-Backend:  
  >  docker build -t usoslite-backend .  
  >  docker run -p 8080:8080 usoslite-backend  
