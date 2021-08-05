# 1st Docker build stage: build the project with Gradle
FROM gradle:6.8.3-jdk11 as builder
WORKDIR /kata_twitter
COPY . /kata_twitter/
RUN gradle assemble --no-daemon

# 2nd Docker build stage: copy builder output and configure entry point
FROM gradle:6.8-jdk11
ENV APP_DIR /application
ENV APP_FILE kata_twitter-1.0-SNAPSHOT.jar

EXPOSE 8080

WORKDIR $APP_DIR
COPY --from=builder /kata_twitter/build/libs/*.jar $APP_DIR/$APP_FILE

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/kata_twitter-1.0-SNAPSHOT.jar"]

CMD ["exec java -jar $APP_FILE"]