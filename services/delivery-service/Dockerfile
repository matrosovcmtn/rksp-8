FROM gradle:8.7-jdk21-alpine as build

ADD . /project
WORKDIR /project

RUN gradle build -x test

FROM amazoncorretto:21.0.5-alpine

WORKDIR /app

COPY --from=build /project/build/libs/delivery-service.jar /libs/
COPY --from=build /project/build/resources /resources/

CMD ["java", "-jar", "libs/delivery-service.jar"]
