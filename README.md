# Backend Developer Assignment - Fresher

This repository contains the solution for the Backend Developer Take-Home Assignment. It is built using **Java (Spring Boot)** and uses an **H2 In-Memory Database** for easy setup and portability.

## 🚀 Tech Stack
* **Language:** Java 17
* **Framework:** Spring Boot (Web, Data JPA)
* **Database:** H2 (In-Memory)
* **Build Tool:** Maven

---

## 🛠️ How to Run the Project

### Prerequisites
* Java 17 or higher installed.
* Maven installed (or use the `mvnw` wrapper included in the project).

### Steps
1.  **Clone the repository:**
    ```bash
    git clone <https://github.com/Abhiishek-webdev/Assignments/>
    cd assignment
    ```

2.  **Build and Run:**
    ```bash
    mvn spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

3.  **Access the Database:**
    To view the data, I have enabled the H2 Console.
    * **URL:** `http://localhost:8080/h2-console`
    * **JDBC URL:** `jdbc:h2:mem:testdb`
    * **User Name:** `sa`
    * **Password:** `password`

---

## 📝 Design Assumptions
1.  **Database Choice:** I chose **H2 (In-Memory)** instead of MySQL/PostgreSQL. This ensures the application runs immediately on any machine without requiring the reviewer to install or configure an external database server.
2.  **Authentication:** As no specific authentication system (like JWT/OAuth) was requested, I am passing the `userId` via request body or query parameters to simulate user actions.
3.  **Idempotency:** For lesson completion, if a record already exists, the API returns a success message without creating a duplicate record, ensuring the operation is idempotent.
4.  **Error Handling:** Custom exceptions (like `ResponseStatusException`) are used to return specific HTTP codes (e.g., `403 Forbidden` for non-enrolled users).

---

## 🔌 API Documentation

### 1. Course Enrollment (Assignment 1)
* **Endpoint:** `POST /courses/{courseId}/enroll`
* **Description:** Enrolls a user in a course. Ensures a user can only enroll once.
* **Sample Request:**
    ```bash
    curl -X POST "http://localhost:8080/courses/101/enroll" \
         -H "Content-Type: application/json" \
         -d "1"
    ```

### 2. Lesson Completion (Assignment 1)
* **Endpoint:** `POST /courses/{courseId}/lessons/{lessonId}/complete`
* **Description:** Marks a lesson as complete. This operation is **idempotent**.
* **Sample Request:**
    ```bash
    curl -X POST "http://localhost:8080/courses/101/lessons/5/complete" \
         -H "Content-Type: application/json" \
         -d "1"
    ```

### 3. Course Rating (Assignment 2)
* **Endpoint:** `POST /courses/{courseId}/rating`
* **Description:** Allows enrolled users to rate a course. Users can only rate a course once.
* **Sample Request:**
    ```bash
    curl -X POST "http://localhost:8080/courses/101/rating" \
         -H "Content-Type: application/json" \
         -d '{"userId": 1, "score": 5, "comment": "Great course!"}'
    ```

### 4. Course Access (Assignment 3)
* **Endpoint:** `GET /courses/{courseId}/lessons`
* **Description:** Fetches lessons. Returns `403 Forbidden` if the user is not enrolled.
* **Sample Request:**
    ```bash
    curl -X GET "http://localhost:8080/courses/101/lessons?userId=1"
    ```

---

## 🧩 DSA Solutions
The solutions for the Data Structure & Algorithm problems are located in the `src/main/java/com/example/assignment/dsa` package.

1.  **Unique Element Counter:** Finds unique elements in a list.
2.  **First Non-Repeating Character:** Finds the first character in a string that does not repeat.
3.  **Balanced Parentheses:** Checks if a string of brackets is valid.
