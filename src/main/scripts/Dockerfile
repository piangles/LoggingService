FROM java:8
WORKDIR /
ADD LoggingService.jar LoggingService.jar
ENTRYPOINT ["java", "-Dprocess.name=LoggingService", "-jar", "LoggingService.jar"]
