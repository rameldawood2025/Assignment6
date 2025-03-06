# Barnes & Noble & Amazon Testing - Assignment 6


[![Build Status](https://github.com/rameldawood2025/Assignment6/actions/workflows/SE333_CI.yml/badge.svg)](https://github.com/rameldawood2025/Assignment6/actions)


## Overview
This project is part of Assignment 6 for the SOEN 333 Software Testing course. The goal is to implement **specification-based testing** and **structural-based testing** for both the **Barnes & Noble** and **Amazon** modules. This includes a **CI/CD pipeline using GitHub Actions** to automate testing, static analysis, and coverage reporting.


## Project Structure
The project is structured as follows:
- **`BarnesAndNoble.java`**: Main class for book transactions.
- **`Amazon.java`**: Main class for Amazon shopping cart transactions.
- **`Book.java`**: Represents a book entity.
- **`Item.java`**: Represents an item entity in the Amazon module.
- **`BookDatabase.java`**: Interface for book retrieval.
- **`BuyBookProcess.java`**: Interface for processing book purchases.
- **`PurchaseSummary.java`**: Stores total price and unavailable books.
- **`ShoppingCart.java`**: Interface for Amazon shopping cart.
- **`ShoppingCartAdapter.java`**: Database-backed implementation of the shopping cart.
- **`Database.java`**: Manages database transactions.


## Part 1: Testing Implementation


### Testing Approach
Implemented testing strategies include:


#### 1️⃣ Specification-Based Testing
- Tests written based on provided specifications.
- Utilizes **JUnit 5** for unit tests.
- Tests are labeled with `@DisplayName("specification-based")`.


#### 2️⃣ Structural-Based Testing
- Ensures all methods in the Barnes & Noble module are correctly implemented.
- Focuses on internal structure-based validation.
- Tests are labeled with `@DisplayName("structural-based")`.


### Test File Implemented
- **`BarnesAndNobleTest.java`** contains all test cases for Part 1, validating book purchasing, price calculations, and tracking of unavailable books.
- **Commit Message for Part 1:**


Ramel Dawood + Part 1 added BarnesAndNoble Tests


## Part 2: CI/CD Automation


### GitHub Actions Workflow
Implemented a GitHub Actions workflow (`SE333_CI.yml`) to automate testing, static analysis, and coverage reporting.


### Workflow Features
- **Triggers**: Activates on push to `main` branch.
- **Environment**: Runs on Ubuntu OS.
- **Static Analysis**: Incorporates Checkstyle for code quality checks.
- **Unit Testing**: Conducted using Maven.
- **Code Coverage**: Managed with JaCoCo.
- **Artifact Uploads**: Stores Checkstyle and JaCoCo reports as artifacts.
- **Build Badge**: Included in `README.md`.


### Setup Instructions
1. Clone the repository and open in **IntelliJ IDEA**.
2. Configure **JUnit 5** for testing.
3. Execute tests via the IntelliJ test runner or Maven.
4. Commit changes with the message:


Ramel Dawood + added Part2 Workflow


## Notes
- Mutation testing is not utilized due to compatibility issues with **Mockito**.
- All necessary test cases are contained within `BarnesAndNobleTest.java`.
- Utilizes Git and GitHub for version control.


## Part 3: Writing Larger Tests


### Integration and Unit Testing for Amazon Package
Developed additional tests for the **Amazon** package, validating functionality through unit and integration tests.


### Test Types Implemented
- **Specification-Based Testing**: Aligns tests with expected behaviors, marked with `@DisplayName("specification-based")`.
- **Structural-Based Testing**: Focuses on the internal system structure and method interactions, marked with `@DisplayName("structural-based")`.


### Integration Testing
- Validates interactions between components like **ShoppingCartAdapter** and **Database.java**.
- Ensures the database is reset before each test to prevent data conflicts.
- Uses `@BeforeEach` and `@BeforeAll` for state management.


### Unit Testing
- Tests components such as **Amazon.java** and **PriceRule.java** individually.
- Uses **Mockito** to mock dependencies and isolate components.


### Test Files Implemented
- **`AmazonUnitTest.java`**: Tests individual methods of Amazon.
- **`AmazonIntegrationTest.java`**: Examines component interactions.
- **`BarnesAndNobleTest.java`**: Validates functionality of Barnes & Noble.


### Commit and Push Instructions
1. Confirm all tests pass.
2. Commit changes with the message:


Ramel Dawood + added Part3 Amazon Tests


3. Push to the `main` branch to activate the GitHub Actions workflow.


## Final CI/CD Confirmation


### GitHub Actions Workflow
- [View GitHub Actions Workflow](https://github.com/rameldawood2025/Assignment6/actions/workflows/SE333_CI.yml)


### CI/CD Execution Success
- Successfully executed workflow.
- All tests passed without issues.
- Status confirmed by **GitHub Actions UI**.


### Generated Artifacts Verification
- **Checkstyle Report** (`checkstyle.xml`) and **JaCoCo Code Coverage Report** are verified under **GitHub Actions > Artifacts**.


### Commit Cleanliness Check
- Commit history is clean and well-structured.
- Commit messages clearly reflect changes (e.g., `added Part3 Amazon Tests`).


## Final Notes
- The project successfully completes **Part 1, Part 2, and Part 3** with continuous testing and quality assurance.
- Adheres to best practices in software testing and CI/CD workflows.


**Great job!**