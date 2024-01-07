Reference : https://www.baeldung.com/spring-bucket4j
https://lakshyajit165.medium.com/rate-limiting-in-rest-apis-using-springboot-and-redis-135cd5cde754

the buckets will be stored in redis cache

each user id will have different bucket 

The application has a rate limit of 1 request per 30 seconds


Test endpoints : 

for API level rate limiter:
http://localhost:8080/rectangle

for rate limiter configutred at handler interceptor: 

http://localhost:8080/rectangle2



Request Body 

header : x-user-id 

{
    "length":2,
    "width":2
}
