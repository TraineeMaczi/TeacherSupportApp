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

spring.servlet.multipart.enabled=true
spring.servlet.multipart.file-size-threshold=2KB
spring.servlet.multipart.max-file-size=200MB
spring.servlet.multipart.max-request-size=215MB
file.upload-dir=./uploads

#MySQL Base (temporary)

1) Check your role id's:

select * from secutityrole;

suppose admin role is 200

2)Create security data for your users 

you can check table content by typing ---->  select * from usersecuritydata;

insert into usersecuritydata values(10,1,"test@email.com","pass","pass",0);

second arg is active 1 allow you to logIn 0 means exsisting in basa but can't login

3)Conect security data with role 

you can check table content by typing ----> select * from securityuserdataandsecurityrole;

insert into securityuserdataandsecurityrole values(10,200);

10 - security data id 
200 -role admin id 

4)Create Person

you can check table content by typing ----> select * from person;

Faculty id is required too so check what faculties you have by typing ----> select * from faculty;

insert into person values(10,"Degree","www.facebook.com","My hobby is ...","Name","777999111","Profession","Surname","www.twitter.com","usos profile url",0,"Work Adr",null,2,null,10);








