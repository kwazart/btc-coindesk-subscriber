FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./target/btc-coindesk-subscriber-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "btc-coindesk-subscriber-0.0.1-SNAPSHOT.jar"]