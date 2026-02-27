# ===== BUILD STAGE =====
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# copy gradle wrapper trước (cache tốt hơn)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon || true

# copy source
COPY src src

RUN ./gradlew bootJar --no-daemon


# ===== RUN STAGE =====
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/build/libs/convert_ISO-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
