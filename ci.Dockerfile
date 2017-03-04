FROM openjdk:8

WORKDIR /code
ADD build/dist/mray-core.jar /code/

CMD java -jar mray-core.jar