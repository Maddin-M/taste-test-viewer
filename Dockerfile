FROM gradle:7.5.1-jdk17-alpine
RUN mkdir /home/gradle/buildWorkspace
COPY . /home/gradle/buildWorkspace

WORKDIR /home/gradle/buildWorkspace
RUN gradle build --no-daemon
RUN tar -xvf /home/gradle/buildWorkspace/app/build/distributions/app.tar
EXPOSE 8080