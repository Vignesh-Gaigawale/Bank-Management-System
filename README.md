# 🏦 Bank Management System

A console-based **Java + MySQL** banking application that simulates core banking operations using JDBC for persistent data storage.

---

## 📋 Features

- ✅ Create customer accounts with auto-generated 6-digit account numbers
- ✅ Deposit & withdraw funds with full transaction logging
- ✅ Transfer funds between accounts using atomic DB transactions
- ✅ View complete transaction history for any account
- ✅ SHA-256 password hashing utility

---

## 🛠️ Tech Stack

| Layer       | Technology                        |
|-------------|-----------------------------------|
| Language    | Java 8+                           |
| Database    | MySQL 8.x                         |
| Connectivity| JDBC (mysql-connector-j 9.6.0)    |
| Interface   | Console / CLI                     |

---

## 📁 Project Structure

```
BankManagementSystem/
├── Main.java               # Entry point — CLI menu loop
├── BankService.java        # Core banking operations
├── DBConnection.java       # MySQL connection manager
├── AccountGenerator.java   # Random account number generator
├── PasswordUtil.java       # SHA-256 hashing utility
├── App.java                # Alternate entry point
└── mysql-connector-j-9.6.0.jar  # JDBC driver
```

---

## 🗄️ Database Schema

Run the following SQL to set up the `bankdb` database:

```sql
CREATE DATABASE bankdb;
USE bankdb;

CREATE TABLE customers (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE accounts (
    account_no  INT PRIMARY KEY,
    customer_id INT,
    balance     DOUBLE DEFAULT 0,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE transactions (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    account_no INT,
    type       VARCHAR(20),
    amount     DOUBLE,
    date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_no) REFERENCES accounts(account_no)
);
```

---

## ⚙️ Setup & Installation

### Prerequisites
- Java JDK 8 or higher
- MySQL Server 8.x running on `localhost:3306`

### 1. Clone the Repository

```bash
git clone git clone https://github.com/Vignesh-Gaigawale/Bank-Management-System.git
cd Bank-Management-System
```

### 2. Configure Database Credentials

Open `DBConnection.java` and update the credentials if needed:

```java
conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/bankdb",
    "your_username",
    "your_password"
);
```

### 3. Create the Database

Run the SQL schema provided above in your MySQL client.

### 4. Compile

**Windows:**
```bash
javac -cp .;mysql-connector-j-9.6.0.jar *.java
```

**Linux / macOS:**
```bash
javac -cp .:mysql-connector-j-9.6.0.jar *.java
```

### 5. Run

**Windows:**
```bash
java -cp .;mysql-connector-j-9.6.0.jar Main
```

**Linux / macOS:**
```bash
java -cp .:mysql-connector-j-9.6.0.jar Main
```

---

## 🖥️ Usage

```
--- BANK MANAGEMENT SYSTEM ---
1 Create Account
2 Deposit
3 Withdraw
4 Transfer
5 Transaction History
6 Exit
```

**Example — Create Account:**
```
Enter Name: John Doe
Enter Phone: 9876543210
Enter Email: john@example.com

Account Created Successfully
Account Number: 482931
```

**Example — Deposit:**
```
Account Number: 482931
Amount: 5000

Deposit Successful
```

---

## 🔒 Security Notes

> ⚠️ This project is intended for **educational purposes**. Before deploying to production:

- Store database credentials in environment variables, not source code
- Replace SHA-256 hashing with **BCrypt** or **PBKDF2** (with salt)
- Add a balance check before withdrawals to prevent negative balances
- Add `conn.rollback()` in the transfer catch block to prevent data loss

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.
