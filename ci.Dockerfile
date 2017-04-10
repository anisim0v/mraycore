FROM openjdk:8

ADD build/dist/mray-core.jar .

HEALTHCHECK CMD curl -f http://localhost:8080/
CMD java -jar mray-core.jar