# 🏦 Banking Management System

A robust, terminal-based Banking Management System built entirely in core Java. This project demonstrates strong software engineering fundamentals, including Object-Oriented Programming (OOP), SOLID design principles, database integration, and automated unit testing without relying on heavy frameworks like Spring or build tools like Maven.

## 🚀 Features & Architecture

This project was developed iteratively to showcase proficiency in various Java concepts:

*   **Version 1 - OOP Principles**: Utilizes Encapsulation, Inheritance, and Polymorphism to model entities like `Customer`, `Account`, `SavingsAccount`, `CurrentAccount`, and `Transaction`.
*   **Version 2 - Java Collections Framework**: Employs `ArrayList` and `List` interfaces to manage and track transaction histories efficiently in memory.
*   **Version 3 - Custom Exception Handling**: Includes custom, robust runtime exceptions (e.g., `InsufficientBalanceException`, `InvalidAccountException`) to safely manage invalid user inputs and operations.
*   **Version 4 - Database Integration**: Uses **SQLite** via JDBC for persistent data storage. The application automatically builds its schema (`customers`, `accounts`, `transactions`) upon initialization, ensuring zero external setup is required.
*   **Version 5 - Automated Testing (JUnit 5)**: Contains a comprehensive suite of unit tests verifying edge cases and business logic in isolation using Dependency Injection.
*   **Version 6 - SOLID Design**: The architecture strictly separates concerns into `Model`, `Repository`, and `Service` layers. Services depend on repository interfaces rather than concrete implementations, keeping the code highly maintainable and testable.

## 🛠️ Technology Stack

*   **Language**: Java 17+
*   **Database**: SQLite (Embedded)
*   **Testing**: JUnit 5
*   **Driver/Libraries**: `sqlite-jdbc`, `slf4j` (included in `lib/`)

## 💻 How to Run Locally

Because this project avoids complex build tools, everything you need is included directly in the repository.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Me-HarishKumar/Banking-Management-System.git
    cd Banking-Management-System
    ```

2.  **Run the Application:**
    The project is pre-compiled in the `bin/` directory. You can run the interactive CLI immediately:
    
    *On Windows:*
    ```bash
    java -cp "lib/*;bin" com.bank.Main
    ```
    *On Mac/Linux:*
    ```bash
    java -cp "lib/*:bin" com.bank.Main
    ```

3.  **Run the Tests:**
    To run the JUnit 5 test suite and verify functionality:
    
    *On Windows:*
    ```bash
    java -cp "lib/junit-platform-console-standalone.jar;bin" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
    ```
    *On Mac/Linux:*
    ```bash
    java -cp "lib/junit-platform-console-standalone.jar:bin" org.junit.platform.console.ConsoleLauncher execute --scan-classpath
    ```

## 📂 Project Structure

```text
├── bin/                       # Compiled .class files
├── lib/                       # External dependencies (JUnit, SQLite)
├── src/com/bank/
│   ├── exception/             # Custom exceptions
│   ├── model/                 # Domain entities
│   ├── repository/            # Database access layer
│   ├── service/               # Core business logic
│   └── Main.java              # CLI Entry point
└── tests/com/bank/service/    # Unit tests
```
