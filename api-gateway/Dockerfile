FROM gradle:8.5-jdk17-alpine AS build
WORKDIR /home/gradle/src

COPY --chown=gradle:gradle gradlew .
COPY --chown=gradle:gradle gradle gradle
COPY --chown=gradle:gradle build.gradle .
COPY --chown=gradle:gradle settings.gradle .

RUN ./gradlew build -x test --no-daemon > /dev/null 2>&1 || true

COPY --chown=gradle:gradle . .
RUN ./gradlew build -x test --no-daemon


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

RUN apk add --no-cache curl

COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]