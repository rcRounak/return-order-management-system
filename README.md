# return-order-management-system
A simple return order management system

Authorization microservice-

Login -
http://localhost:8080/login 			(Post)
Body - 
{
	"username": "user1",
	"password": "user12345"
}
Validate -
http://localhost:8080/validate		(Get)
Header Token
Authorization: Token

_______________________________________________________________________________________


Component Processing microservice-

http://localhost:8081/ProcessDetail		(Post)
Body - 
{
    "name": "john",
    "contactNumber": "123456",
    "componentType": "Integral",
    "componentName": "T.V",
    "quantity": 2
}
Header Token
Authorization: Token

http://localhost:8081/CompleteProcessing	(Post)
Body -
{
    "creditCardNumber": "123456",
    "creditLimit": 10000,
    "dateOfDelivery": "10/15/22",
    "requestID": 300
}

________________________________________________________________________________________


Packaging and Delivery microservice-

http://localhost:8082/GetPackagingDeliveryCharge/{componentType}/{count}
http://localhost:8082/GetPackagingDeliveryCharge/:componentType/:count
Param -
componentType	integral/accessory
count			2

_________________________________________________________________________________________


Hibernate links -

For User - http://localhost:8080/h2-console
For Process request and response - http://localhost:8081/h2-console

__________________________________________________________________________________________


Swagger links -

For authorization service - http://localhost:8080/swagger-ui.html
For component processing service - http://localhost:8081/swagger-ui.html
For packaging and delivery service - http://localhost:8082/swagger-ui.html

___________________________________________________________________________________________
