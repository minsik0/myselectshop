# 1단계: 빌드
FROM gradle:7.6.2-jdk17 AS build
WORKDIR /workspace
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# 2단계: 실행
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
