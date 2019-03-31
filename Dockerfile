FROM openjdk:8

WORKDIR /code
ADD . /code

RUN ./gradlew build \
    && rm -r /root/.gradle \
    && mv build/dist/mray-core.jar .

CMD ['java',"-Djava.security.egd=file:/dev/./urandom", '-jar', 'mray-core.jar']