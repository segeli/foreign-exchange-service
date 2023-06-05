FROM openjdk:17-slim AS builder
COPY . /usr/src/backend
WORKDIR /usr/src/backend
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-slim
COPY --from=builder /usr/src/backend/target/foreign-exchange-service-0.0.1.jar /usr/src/backend/
WORKDIR /usr/src/backend
EXPOSE 8080
CMD ["java", "-jar", "foreign-exchange-service-0.0.1.jar"]