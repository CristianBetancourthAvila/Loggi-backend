FROM amazoncorretto:17-alpine-jdk AS build
ADD target/financiera-contable-0.0.1-SNAPSHOT.jar /financiera-contable.jar
RUN apk add --no-cache msttcorefonts-installer fontconfig
RUN update-ms-fonts
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/financiera-contable.jar"]
EXPOSE 8255