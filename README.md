# TeacherSupportApp

#Basic app properties

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_BASE_NAME?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=USER_NAME
spring.datasource.password=USER_PASSWORD
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
server.port=9000
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

spring.mail.host=smtp.gmail.com 
spring.mail.port=587 
spring.mail.username=MAIL_USERNAME 
spring.mail.password=MAIL_PASSWORD
spring.mail.protocol=smtp 
spring.mail.test-connection=false
mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true



#MySQL Base (temporary)

1) Check your role id's:

select * from secutityrole;

suppose admin role is 200

2)Create security data for your users 

you can check table content by typing ---->  select * from usersecuritydata;

insert into usersecuritydata values(1,1,"activeAdmin1@email.com","pass","pass",0);

second arg is active 1 allow you to logIn 0 means exsisting in basa but can't login

3)Conect security data with role 

you can check table content by typing ----> select * from securityuserdataandsecurityrole;

insert into securityuserdataandsecurityrole values(1,200);

1 - security data id 
200 -role admin id 

4)Create Person

you can check table content by typing ----> select * from person;

Faculty id is required too so check what faculties you have by typing ----> select * from faculty;


insert into person values(1,null,null,"Bob",null,null,"Kowalski",null,0,null,5,1);

(id,other data ,other data ,name,other data,other data,surname,other data,version,other data,faculty id,user security data id)




