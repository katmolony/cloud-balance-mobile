# Cloud Balance - Mobile App

Cloud Balance is a **Mobile Cloud Resource Manager** that allows students and lecturers to monitor their AWS resource usage and costs. This repository contains the **Android mobile app**, built using **Kotlin** and **Jetpack Compose**.

## 📱 Features
- View AWS resource usage and costs in real time
- Receive alerts for cost overruns and security issues
- Lecturer dashboard for managing multiple student accounts
- Secure authentication (OAuth2 planned)
- Modern UI with Jetpack Compose

## 🛠️ Tech Stack
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI framework
- **Retrofit** - API client for backend communication
- **Firebase Cloud Messaging (Planned)** - Push notifications
- **AWS Cost Explorer API (Planned)** - Fetch AWS spending data

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

| Method | Endpoint           | Description                  |
|--------|--------------------|------------------------------|
| POST   | `/resource-usage/` | Track AWS resource usage     |
| GET    | `/`                | Health check (API status)    |

### **🛠️ Planned Features**
* OAuth2 Authentication
* Push Notifications for Cost Alerts
* Multi-Account Management for Lecturers
* AWS API Integration for Real-Time Cost Tracking

### **👥 Contributors**
* Kate Molony - GitHub