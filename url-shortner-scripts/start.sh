#!/usr/bin/env bash
cd /home/ec2-user/server
sudo /usr/bin/java -jar -Dserver.port=80 -Dspring.profiles.active=rds \
    cloud-url-shortner*.jar > /dev/null 2> /dev/null < /dev/null &