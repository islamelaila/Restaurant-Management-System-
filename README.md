# 🍽️ Restaurant Management System (Java CLI)

A simple and modular restaurant management system built using Java and Object-Oriented Programming principles. This project simulates basic restaurant operations including user registration, login, menu viewing, order placement, and delivery handling.

---

## 📌 Features

- 👨‍🍳 Role-based architecture: Customer, Chef, Delivery Guy
- 🧾 Menu management and dish creation
- 🛒 Customer ordering system
- 📦 Order tracking and status update
- ✅ Login/Register functionality
- 💾 In-memory data storage using ArrayLists
- 🧠 Clean OOP design with interfaces and abstraction

---

## 🛠️ Tech Stack

- **Language:** Java 17+
- **Architecture:** Object-Oriented Programming (OOP)
- **IDE:** IntelliJ IDEA / VS Code
- **Storage:** ArrayList (no DB)

---

## 📂 Project Structure
restaurant/
├── Main.java
├── models/
│ ├── User.java (abstract)
│ ├── Customer.java
│ ├── Chef.java
│ ├── DeliveryGuy.java
│ ├── Dish.java
│ └── Order.java
├── services/
│ ├── interfaces/
│ │ ├── CustomerService.java
│ │ ├── ChefService.java
│ │ └── DeliveryService.java
│ └── implementations/
│ ├── CustomerServiceImpl.java
│ ├── ChefServiceImpl.java
│ └── DeliveryServiceImpl.java
├── data/
│ ├── UserData.java
│ ├── DishData.java
│ └── OrderData.java

🎯 Use Case Scenarios
A customer registers and logs in
Views available dishes
Places an order
Chef views new orders and updates status
Delivery guy checks and delivers orders

🚀 Future Improvements
Replace CLI with GUI (JavaFX or Swing)
Add file/database persistence
Implement role-based authentication
Add unit testing






