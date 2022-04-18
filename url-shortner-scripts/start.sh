#!/usr/bin/env bash
cd /home/ec2-user/server
sudo /usr/bin/java -jar -Dserver.port=80 -Dspring.profiles.active=rds -Durl.prefix=ec2-52-213-233-82.eu-west-1.compute.amazonaws.com\
    cloud-url-shortner*.jar > /dev/null 2> /dev/null < /dev/null &