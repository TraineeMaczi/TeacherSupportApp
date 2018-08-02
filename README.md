# TeacherSupportApp

#Basic app properties

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_BASE_NAME?allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=YOUR_USER_NAME
spring.datasource.password=YOUR_PASS
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL57Dialect
server.port = 9000
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl



spring.mail.host=smtp.gmail.com
#spring.mail.port=587
spring.mail.username=YOUR_E_MAIL@gmail.com
spring.mail.password=YOUR_E_MAIL_PASS
spring.mail.protocol=smtp
spring.mail.test-connection=false


#mail properties
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.enable = true
spring.mail.properties.mail.smtp.starttls.required=true


#MySQL Base (temporary)
