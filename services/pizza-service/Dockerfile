FROM gradle:8.7-jdk21-alpine as build

ADD . /project
WORKDIR /project

ARG JAR_FILE=user-fullname-service.jar
RUN gradle build -x test

FROM amazoncorretto:21.0.5-alpine

ARG JAR_FILE=user-fullname-service.jar
WORKDIR /app

COPY --from=build /project/build/libs/$JAR_FILE /libs/
COPY --from=build /project/build/resources /resources/

CMD ["java", "-jar", "libs/user-fullname-service.jar"]
