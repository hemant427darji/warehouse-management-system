# 🏢 Warehouse Management System

A full-stack Warehouse Management System built with Java, Spring Boot, and MySQL. This system helps manage clients, inventory, stock movements, and roles with secure API access.

---

## 🚀 Features

- 📦 **Inventory Management** — Track items, stock in/out, and current inventory levels.
- 👥 **Client Management** — Manage clients securely via API key authentication.
- 🔐 **Role-Based Access** — Support for multiple user roles (Admin, Client).
- 📊 **Dashboard Summary** — View key warehouse statistics at a glance.
- 🛡️ **API Security** — Custom filter to authenticate requests using API keys.

---

## 🛠️ Tech Stack

- **Backend:** Java 21, Spring Boot, Spring Security
- **Database:** MySQL
- **ORM:** Hibernate (JPA)
- **Security:** Custom Filters, JWT (optional), API key authentication
- **Build Tool:** Maven

---

## 📁 Project Structure

src
├── main
│ ├── java
│ │ └── com.example.warehouse
│ │ ├── controller # REST Controllers
│ │ ├── dto # Data Transfer Objects
│ │ ├── entity # JPA Entity Models
│ │ ├── enums # Enum Definitions
│ │ ├── exceptions # Custom Exceptions
│ │ ├── repository # Spring Data JPA Repositories
│ │ ├── security # API Key Security Filters
│ │ ├── service # Business Logic
│ │ ├── shared # Utilities, Mappers, etc.
│ │ └── WarehouseApiApplication.java # Main Spring Boot Application
│ └── resources
│ ├── application.properties
│ └── static / templates (if used)
├── test # Unit and Integration Tests
├── target # Build Output (Ignored in Git)
├── pom.xml # Maven Configuration



👤 Author
Hemant Darji
📧 write.hemant1996@gmail.com
🔗 LinkedIn



