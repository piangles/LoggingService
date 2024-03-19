FROM eclipse-temurin:17-jre-alpine
WORKDIR /
ADD ./target/LoggingService.jar LoggingService.jar
ENTRYPOINT ["java", "-Dprocess.name=LoggingService", "-jar", "LoggingService.jar"]
