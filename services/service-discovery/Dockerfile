FROM gradle:8.7-jdk21-alpine as build

ADD . /project
WORKDIR /project

ARG JAR_FILE=service-discovery.jar
RUN gradle build -x test

FROM amazoncorretto:21.0.5-alpine

ARG JAR_FILE=service-discovery.jar
WORKDIR /app

COPY --from=build /project/build/libs/$JAR_FILE /libs/
COPY --from=build /project/build/resources /resources/

CMD ["java", "-jar", "libs/service-discovery.jar"]
