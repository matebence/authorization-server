From openjdk:8
copy ./target/authorization-server-0.0.1-SNAPSHOT.jar authorization-server-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","authorization-server-0.0.1-SNAPSHOT.jar"]