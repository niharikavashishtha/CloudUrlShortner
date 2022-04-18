## CloudURLShortner API Shortening service

CloudURLShortner is a scalable URL Shortening service. It provides easy to use RESTFul APIs to create short URL which are 6 character long Base62 string.

It can support upto 62^6 = 58 Billion different URLs.



![Flow](Images/CloudURLShortner.png)

## How Build

* for maven build run

```
$ mvn clean install
```

* For code coverage

```
after build open this file in the browser
/target/site/jacoco/index.html

```



## With In memory DB H2
```
$ mvn clean install
$ cd target/

$ java -jar -Dspring.profiles.active=h2 CloudURLShortner-api-0.0.1-SNAPSHOT.jar

```

## How to test
I have added [PostMan collection](./cloudurl-AWS.postman_collection.json) to test the APIs

## using Sonar
mvn sonar:sonar


## Things I learned and used in this project

#### Spring web REST
SpringBoot tools for building RESTful services.
#### Lombok
Convenient tool for code auto-generation.
#### Spring JPA, mysql, h2
SpringBoot tool for JPA for accessing databse.



### User API key for authorization 
to shorten the URL the POST API call takes JSON in the body request. It can be subject to malicious 
attack i.e. user can flood our system with junk URLs. so we can add API key ID field in the 
LongUrlDTO to only allow register user to allow adding the URL.

###
use of different AWS cloud service
building CICD pipelines
using EC2 instances


## Things to be done

### Performance testing
I have'nt done the performance testing yet, but I would like to know, how the
caching would improve the performance. I would use Apache Jmeter for this.

### Cleaning up old URLs
Old URL entries in the DB are not cleaned up, over the time it will increase DB size
We can use spring scheduler (@EnableScheduling) to run a job every day at mid night to check all entries
which are older than say 5 years and clean them up.

### Caching
We have used default spring cache which is in-memory ( ConcurrentHashMap)
But to optimize the performance we can use distributed cache like Redis.

### Swagger API Documentation
APIs are not documented yet, I would use swagger API documentation tool which provides 
@ApiOperation for this.

