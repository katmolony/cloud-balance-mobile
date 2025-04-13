# 📱 Cloud Balance - Mobile App

**Cloud Balance** is a mobile-first **AWS Cloud Resource Manager**, designed specifically for students and lecturers working in cloud-based academic settings. It allows users to securely connect their AWS accounts and monitor resource usage and costs in real time. This repository contains the **Android app**, built using **Kotlin** and **Jetpack Compose**, with secure authentication via **Amazon Cognito** and data fetching through a custom backend API.
## ✨ Features

- 🔐 Secure login and signup with **Amazon Cognito**
- 📊 View real-time AWS cost and EC2 resource data
- 🧾 Submit IAM Role ARN for secure, keyless access
- 📱 Clean and responsive UI built with Jetpack Compose
- 📩 IAM Role onboarding guide embedded in-app

## 🛠️ Tech Stack

- **Kotlin** – Programming language for Android
- **Jetpack Compose** – Modern UI framework
- **Retrofit** – For communicating with backend API
- **EncryptedSharedPreferences** – Secure local token storage
- **Amazon Cognito** – User authentication (no OAuth2 required)
- **AWS STS + Cost Explorer + EC2 APIs** – Real AWS usage integration (via backend)
- **PostgreSQL** – Cloud database via RDS (backend)

## 📦 Installation

### **1️⃣ Clone the repository**
```bash
git clone https://github.com/YOUR-USERNAME/cloud-balance-mobile.git
cd cloud-balance-mobile
```

### **2️⃣ Open in Android Studio**
* Open Android Studio
* Click Open an Existing Project and select the folder

### **3️⃣ Run the App**
* Click Run ▶ in Android Studio or use:
```bash
./gradlew assembleDebug
```

### **🔌 API Integration**
The mobile app connects to the FastAPI backend to fetch AWS cost data.
Update BASE_URL in ApiService.kt with your backend server URL:

```bash
private const val BASE_URL = "http://127.0.0.1:8000"
```

## 📜 API Endpoints

| Method | Endpoint                     | Description                                |
|--------|------------------------------|--------------------------------------------|
| POST   | `/api/users`                 | Create new user                            |
| POST   | `/api/iam-roles/`            | Submit IAM Role ARN                        |
| GET    | `/api/iam-roles/:user_id`    | Retrieve IAM Role for a user               |
| POST   | `/api/aws/fetch/:user_id`    | Trigger AWS resource and cost data fetch   |
| GET    | `/api/aws/costs/:user_id`    | Retrieve stored AWS cost data              |
| GET    | `/api/aws/resources/:user_id`| Retrieve stored AWS EC2 resource metadata  |

### **🛠️ Planned Features**
- 📣 Push notifications for budget alerts
- 📈 Cost analytics visualised with charts
- 🧑‍🏫 Lecturer portal with student role filtering
- 🔄 Periodic data sync using EventBridge (backend)

## 🧭 System Architecture

The diagram below illustrates how the Cloud Balance mobile app, backend API, AWS Lambda functions, and PostgreSQL database interact across public and private cloud environments.

![Cloud Balance Architecture](assets/architecture.png)

### **👥 Contributors**
* Kate Molony - GitHub