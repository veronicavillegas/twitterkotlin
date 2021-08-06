# 1st Docker build stage: build the project with Gradle
FROM gradle:7-jdk11 as builder
WORKDIR /kata_twitter
COPY . /kata_twitter/
RUN gradle assemble --no-daemon

# 2nd Docker build stage: copy builder output and configure entry point
FROM gradle:7-jdk11
ENV APP_DIR /app
ENV APP_FILE twitterkata-1.0-SNAPSHOT-all.jar

EXPOSE 8080

WORKDIR $APP_DIR
COPY --from=builder /kata_twitter/build/libs/*-all.jar $APP_DIR/$APP_FILE

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/twitterkata-1.0-SNAPSHOT-all.jar"]

CMD ["exec java -jar $APP_FILE"]