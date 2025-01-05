# AutomationFrameworkUI
# **Getting Started with AutomationFrameworkUI**

This README provides a step-by-step guide on how to open, set up, and run the **AutomationFrameworkUI** in IntelliJ IDE. You’ll also learn how to view TestNG results and ExtentReports, as well as troubleshoot common issues.

---

## **1. Create & Run This Project in IntelliJ**

1. **Open IntelliJ**
    - Go to **File** > **New** > **Project from Existing Sources** (or **Import Project** if you already cloned the repository from Git).
    - Select the **MySeleniumProject** folder that contains the **`pom.xml`** file.
    - IntelliJ should **auto-detect** it as a Maven project.

2. **Check Maven Dependencies**
    - In the **Maven** tool window (usually on the right side of IntelliJ), ensure all dependencies (Selenium, TestNG, ExtentReports, etc.) are successfully downloaded.
    - If necessary, click **Reload All Maven Projects** to force IntelliJ to update the dependencies.

---

## **2. Set Up the TestNG Runner**

- IntelliJ automatically recognizes **`testng.xml`** files.
- To run all tests defined in **`testng.xml`**:
    1. Right-click on **`testng.xml`**.
    2. Select **`Run 'testng.xml'`**.

- Alternatively, you can run a specific test class:
    1. Right-click on **`LoginTest.java`** (or any other test class).
    2. Select **`Run 'LoginTest'`**.

---

## **3. View Test Results**

- In IntelliJ’s **Run** or **Test** panel, you’ll see standard **TestNG output** (pass/fail summaries, logs, etc.).
- After the test finishes, an **`ExtentReport.html`** file is generated in the **project root**.
    - Open it in your browser to view detailed test steps, logs, and any attached screenshots.

---

## **4. Screenshot Storage**

- When a test **fails**, a screenshot is automatically saved in the **`screenshots/`** folder.
- You may need to **refresh** your project view in IntelliJ to see newly created files.

---

## **5. Common Issues & Troubleshooting**

1. **Java Version**
    - Make sure you have **Java 11** (or whichever version is specified in your `pom.xml`) installed and properly set in IntelliJ’s Project SDK.
2. **Driver Errors**
    - If you encounter driver-related issues, ensure **WebDriverManager** is successfully downloading the correct driver for your browser. Check your network or proxy settings if downloads fail.
3. **Missing Dependencies**
    - If IntelliJ fails to compile tests or can’t find certain packages (e.g., `org.testng`), confirm your **Maven dependencies** are correctly listed in `pom.xml` and that the project is **reloaded**.

---
