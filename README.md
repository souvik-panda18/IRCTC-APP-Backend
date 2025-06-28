🚆 IRCTC Train Booking Application - Backend

This is the backend system for an IRCTC-style train booking application. It handles core functionalities such as user registration, train search, ticket booking, cancellations, and user history management. This backend is built with Java (Spring Boot) and uses RESTful APIs to communicate with the frontend.

---

## 📁 Project Structure

train-booking-backend/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com.irctc.booking/
│ │ │ ├── controller/ # REST Controllers
│ │ │ ├── model/ # Data models / entities
│ │ │ ├── service/ # Business logic
│ │ │ ├── repository/ # JPA Repositories
│ │ │ └── config/ # Configuration files
│ │ └── resources/
│ │ ├── application.properties
│ │ └── data.sql # Optional: seed data
├── pom.xml # Maven dependencies
└── README.md # Project documentation

yaml
Copy
Edit

---

## ✅ Features

- 🔐 User Authentication and Authorization (Login/Register)
- 🚄 Train Search by source, destination, and date
- 🎫 Ticket Booking (with PNR generation)
- ❌ Ticket Cancellation and Refund logic
- 📜 Booking History and Ticket View
- 📊 Admin Panel APIs (for adding trains, checking bookings)
- 💾 Data Persistence using MySQL/PostgreSQL

---

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **MySQL / PostgreSQL**
- **Spring Security (JWT)**
- **Lombok** (for boilerplate reduction)
- **Swagger/OpenAPI** (for API docs)

---
