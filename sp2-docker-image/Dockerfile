FROM java:8

MAINTAINER zgy<zgy@123.com>

WORKDIR /usr/local/app

COPY *.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]