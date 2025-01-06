
```markdown
# Automation Framework UI

This repository contains a robust UI automation testing framework built using **Java**, **Selenium**, **TestNG**, and other modern tools. It is designed for scalability, reusability, and seamless integration with CI/CD pipelines using Jenkins.

---

## Features

- **Automation Framework**:
  - Built on **Java 17**, **Selenium 4**, and **TestNG**.
  - Implements the **Page Object Model (POM)** design pattern.
  - Supports **Data-Driven Testing** using external files (e.g., Excel, JSON).
  - Includes utilities for logging, reporting, and element interaction.
  - Captures screenshots for failures using **AShot**.
  - Generates detailed HTML reports using **ExtentReports**.

- **Logging**:
  - Integrated with **Log4j 2** for capturing detailed execution logs.

- **Cross-Browser Testing**:
  - Easily configurable for Chrome, Firefox, Edge, and others via WebDriverManager.

- **Continuous Integration (CI)**:
  - Fully integrated with **Jenkins** for automated test execution.
  - Triggers builds via GitHub Webhooks or Poll SCM.

---

## Technologies Used

- **Java 17**
- **Maven** (Build Tool)
- **TestNG** (Test Execution)
- **Selenium 4** (Browser Automation)
- **ExtentReports** (Reporting)
- **Log4j 2** (Logging)
- **AShot** (Screenshots)
- **WebDriverManager** (Driver Management)
- **Jenkins** (CI/CD Pipeline)

---

## Project Structure

```
src
├── main
│   └── java
│       ├── base
│       │   ├── BaseTest.java        # Common setup and teardown logic
│       │   └── BrowserDriverManager.java
│       ├── pages                   # Page Object Model classes
│       │   └── LoginPage.java
│       ├── reporting
│       │   └── ExtentManager.java  # ExtentReports integration
│       └── utils                   # Utility classes (e.g., LogReporter)
├── test
│   └── java
│       └── tests                   # Test classes
│           ├── auth
│           │   └── VerifySuccessfulLogin.java
│           └── dashboard
│               └── VerifyDashboardLoad.java
│   └── resources
│       └── testng.xml              # TestNG suite configuration
pom.xml                              # Maven build configuration
```

---

## Prerequisites

1. Install **Java 17** or later.
2. Install **Maven** (minimum version 3.6.0).
3. Install **Jenkins** for CI/CD.

---

## Setup Instructions

### Running Tests Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/AutomationFrameworkUI.git
   cd AutomationFrameworkUI
   ```

2. Run the tests using Maven:
   ```bash
   mvn clean test -Denv=DEV
   ```

3. HTML Reports:
    - After test execution, detailed HTML reports will be available in `target/ExtentReport.html`.

---

## Jenkins CI/CD Integration

### Jenkins Configuration

1. **Install Jenkins Plugins**:
    - **Git Plugin**
    - **Maven Integration Plugin**
    - **HTML Publisher Plugin**

2. **Create a Jenkins Job**:
    - Set **Source Code Management** to Git and provide your repository URL.
    - Under **Build**, add:
      ```bash
      clean test -Denv=DEV
      ```
    - Use **HTML Publisher** to archive the test reports:
        - HTML Directory: `target`
        - Index Page: `ExtentReport.html`

3. **Set Up Build Triggers**:
    - Use **Poll SCM** or **GitHub Webhooks** to trigger builds automatically on code changes.

---

## Common Issues and Fixes

### Tests Not Running (`Tests run: 0`)
- Ensure `testng.xml` is correctly configured in the Maven Surefire Plugin:
  ```xml
  <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.1.2</version>
      <configuration>
          <suiteXmlFiles>
              <suiteXmlFile>src/test/resources/testng.xml</suiteXmlFile>
          </suiteXmlFiles>
      </configuration>
  </plugin>
  ```

- Verify test classes have `@Test` annotations and are in `src/test/java`.

### NullPointerException for System Properties
- Pass the required system properties:
  ```bash
  mvn clean test -Denv=DEV
  ```
- Use a default value in the code:
  ```java
  String environment = System.getProperty("env", "DEV");
  ```

### HTML Publisher Plugin Failure
- Ensure the report is generated in the `target` directory.
- In Jenkins:
    - HTML Directory: `target`
    - Index Page: `ExtentReport.html`

### SLF4J Warnings
- Add an SLF4J implementation to your `pom.xml`:
  ```xml
  <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-simple</artifactId>
      <version>2.0.9</version>
  </dependency>
  ```

---

## Future Enhancements

- Add **API Testing** with REST Assured.
- Integrate **Selenium Grid** for distributed testing.
- Add support for **performance testing** with JMeter.
- Automate **report uploads** to cloud storage.

---

## Contributors

- **Your Name** - Framework Developer

---

## License

This project is licensed under the MIT License.

```
