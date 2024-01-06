Reference : https://www.baeldung.com/spring-bucket4j


The application has a rate limit of 1 request per 30 seconds


Test endpoints : 

for API level rate limiter:
http://localhost:8080/rectangle

for rate limiter configutred at handler interceptor: 

http://localhost:8080/rectangle2



Request Body 

{
    "length":2,
    "width":2
}
