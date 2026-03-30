# Centralized Swagger Verification Guide

Follow this checklist every time you need to validate the shared Swagger UI hosted by `founderlink-api-gateway`.

## 1. Prerequisites
- Java 17+ and Maven wrappers already shipped with each service.
- Local MySQL, RabbitMQ, and Zipkin instances running if the services require them.
- Ports open as defined in each `application.yml` (Config Server `8888`, Eureka `8761`, Gateway `8081`, etc.).
- Optional: a browser session authenticated against your OAuth2 provider if the gateway protects `/swagger-ui/**`.

## 2. Start the Config Server
```powershell
cd "C:\Users\ASUS\OneDrive\Desktop\6th sem\Vivek project\founderlink-config-server"
./mvnw spring-boot:run
```
- Wait for the log line confirming "Started ConfigServerApplication" and that the Git repo cloned successfully.

## 3. Start the Eureka Discovery Server
```powershell
cd "C:\Users\ASUS\OneDrive\Desktop\6th sem\Vivek project\founderlink-eureka-server"
./mvnw spring-boot:run
```
- Verify `http://localhost:8761` loads the Eureka dashboard.

## 4. Launch the Microservices You Want Documented
Run each service in its own terminal. For example:
```powershell
cd "C:\Users\ASUS\OneDrive\Desktop\6th sem\Vivek project\founderlink-auth-service"
./mvnw spring-boot:run
```
Repeat for other services (`user-service`, `startup-service`, etc.). Confirm in the Eureka dashboard that they register and show `UP`.

## 5. (Optional) Smoke-Test Individual `/v3/api-docs`
Before starting the gateway, hit the docs directly to ensure each service emits OpenAPI JSON:
```powershell
curl http://localhost:8082/v3/api-docs | jq ".info"
```
(Substitute the port and service name as needed.)

## 6. Start the API Gateway with Swagger Aggregation
```powershell
cd "C:\Users\ASUS\OneDrive\Desktop\6th sem\Vivek project\founderlink-api-gateway"
./mvnw spring-boot:run
```
- Watch for log entries showing the `SwaggerAggregationConfig` registered the URLs listed under `swagger.services`.
- In Eureka, confirm the `api-gateway` instance is `UP`.

## 7. Validate the Central Swagger UI
1. Open `http://localhost:8081/swagger-ui`.
2. If prompted, complete the OAuth2 login (depends on your configuration).
3. Use the service dropdown to pick each API definition and verify operations render.
4. For a raw check, run:
   ```powershell
   curl http://localhost:8081/swagger/user-service/v3/api-docs | jq ".paths" | head
   ```
   This ensures the gateway successfully proxies the docs endpoint.

## 8. Troubleshooting
- **“Failed to load API definition”**: make sure the target service is running and that its `/v3/api-docs` path matches the gateway route (`/swagger/<service>/v3/api-docs`).
- **Empty dropdown**: confirm `swagger.services` in `founderlink-api-gateway/src/main/resources/application.yml` lists every service you expect.
- **401/403 errors**: ensure your OAuth2 token relays correctly; the gateway uses `TokenRelay` so you must be authenticated.
- **Route mismatches**: compare the gateway `spring.cloud.gateway.routes` entries with the Eureka service IDs (case sensitive) to avoid `404` responses.

## 9. Wrap-Up
After verification, stop the services with `Ctrl+C` in each terminal. If you made config changes, re-run from Step 2 to confirm the Swagger UI stays consistent.

