FROM gradle:6.8-jdk11
EXPOSE 8080

RUN mkdir /app

COPY build/libs/*.jar /app/kata_twitter-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/kata_twitter-1.0-SNAPSHOT.jar"]
