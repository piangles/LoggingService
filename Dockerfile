FROM amazoncorretto:8
WORKDIR /
ADD ./target/LoggingService.jar LoggingService.jar
ENTRYPOINT ["java", "-Dprocess.name=LoggingService", "-jar", "LoggingService.jar"]
