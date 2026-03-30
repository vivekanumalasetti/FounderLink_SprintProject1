# FounderLink — How to Run Locally

FounderLink is a microservices-based platform built with Spring Boot and Spring Cloud.  
Follow these steps to run the full application on your machine.

---

## Prerequisites

Make sure you have these installed before starting:

| Tool | Version | Download |
|------|---------|----------|
| **Java JDK** | 17 or later | https://adoptium.net |
| **VS Code** | Latest | https://code.visualstudio.com |
| **Extension Pack for Java** | (VS Code extension) | Install from VS Code Extensions panel |
| **Spring Boot Extension Pack** | (VS Code extension) | Install from VS Code Extensions panel |
| **Git** | Latest | https://git-scm.com |

> **No Maven installation needed** — VS Code's Java extension handles it automatically.

---

## Step 1 — Clone the Repository

```bash
git clone <your-repo-url>
cd "Vivek project"
```

Open the folder in VS Code:
```
File → Open Folder → select the "Vivek project" folder
```

---

## Step 2 — Wait for VS Code to Load

After opening the folder, VS Code will automatically:
- Detect all 10 Maven projects
- Download all dependencies (this may take **3–5 minutes** the first time)

You'll see a loading spinner in the bottom status bar. **Wait until it disappears** before trying to run anything.

---

## Step 3 — Run the Services (One by One, In Order)

> ⚠️ **Order matters!** Start each service and wait for it to fully start before launching the next.

### 🔴 Method A — Using the Run Button (Recommended)

1. Press `Ctrl+Shift+D` to open the **Run & Debug** panel
2. In the dropdown at the top, select **`🚀 Run All FounderLink Services`**
3. Click the **▶ green play button**

All services will launch. Check the **Terminal** panel to monitor each one.

---

### 🟡 Method B — Manual (one by one, safest)

Open each file below and click the **▶ Run** button above the `main` method:

| Step | File to Open & Run | Wait for message |
|------|-------------------|-----------------|
| **1** | `founderlink-config-server/src/.../FounderlinkConfigServerApplication.java` | `Started FounderlinkConfigServerApplication` |
| **2** | `founderlink-eureka-server/src/.../FounderlinkEurekaServerApplication.java` | `Started FounderlinkEurekaServerApplication` |
| **3** | `founderlink-api-gateway/src/.../FounderlinkApiGatewayApplication.java` | `Started FounderlinkApiGatewayApplication` |
| **4** | `founderlink-auth-service/src/.../FounderlinkAuthServiceApplication.java` | `Started FounderlinkAuthServiceApplication` |
| **5** | `founderlink-user-service/src/.../FounderlinkUserServiceApplication.java` | `Started FounderlinkUserServiceApplication` |
| **6** | `founderlink-team-service/src/.../FounderlinkTeamServiceApplication.java` | `Started FounderlinkTeamServiceApplication` |
| **7** | `founderlink-startup-service/src/.../FounderlinkStartupServiceApplication.java` | `Started FounderlinkStartupServiceApplication` |
| **8** | `founderlink-investment-service/src/.../FounderlinkInvestmentServiceApplication.java` | `Started FounderlinkInvestmentServiceApplication` |
| **9** | `founderlink-messaging-service/src/.../FounderlinkMessagingServiceApplication.java` | `Started FounderlinkMessagingServiceApplication` |
| **10** | `founderlink-notification-service/src/.../FounderlinkNotificationServiceApplication.java` | `Started FounderlinkNotificationServiceApplication` |

---

## Step 4 — Verify Everything is Running

Open your browser and check these URLs:

| Service | URL | What you should see |
|---------|-----|---------------------|
| **Config Server** | http://localhost:8888/actuator/health | `{"status":"UP"}` |
| **Eureka Dashboard** | http://localhost:8761 | Eureka web UI with registered services |
| **API Gateway** | http://localhost:8080/actuator/health | `{"status":"UP"}` |
| **Swagger UI** (all APIs) | http://localhost:8080/swagger-ui | Centralized Swagger docs for all services |

---

## Port Reference

| Service | Port |
|---------|------|
| Config Server | 8888 |
| Eureka Server | 8761 |
| API Gateway | 8080 |
| Auth / User / Team / Startup / Investment / Messaging / Notification | Assigned via Config Server |

---

## Troubleshooting

**❌ "Port already in use" error**
```powershell
# Find and kill the process using the port (e.g. 8888)
netstat -ano | findstr :8888
taskkill /PID <PID_NUMBER> /F
```

**❌ Service fails to start with "Connection refused to config server"**  
→ Make sure **Config Server** (Step 1) is fully started first.

**❌ Service fails to start with "Connection refused to Eureka"**  
→ Make sure **Eureka Server** (Step 2) is fully started first.

**❌ VS Code shows red errors on `pom.xml`**  
→ Right-click the affected project in the Explorer → **Maven → Reload Project**

**❌ Dependencies not downloading**  
→ Check your internet connection. VS Code downloads from Maven Central on first run.

---

## Architecture Overview

```
                        ┌─────────────────┐
                        │  Config Server  │  :8888
                        │  (Git-backed)   │
                        └────────┬────────┘
                                 │ provides config
                        ┌────────▼────────┐
                        │  Eureka Server  │  :8761
                        │ (Service Registry)│
                        └────────┬────────┘
                                 │ service discovery
                        ┌────────▼────────┐
         Client ──────► │   API Gateway   │  :8080
                        │ (single entry)  │
                        └────────┬────────┘
            ┌───────────┬────────┼────────┬───────────┐
            ▼           ▼        ▼        ▼           ▼
         Auth        User      Team   Startup    Investment
        Service     Service  Service  Service     Service

                   Messaging Service   Notification Service
```

All client requests go through the **API Gateway** at `http://localhost:8080`.
