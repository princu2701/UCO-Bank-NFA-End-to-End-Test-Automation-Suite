# 🏦 UCO Bank – NFA End-to-End Test Automation Suite (Selenium + Java + TestNG + Excel + Docker)

---

## 📌 Project Overview

The UCO Bank – NFA (Non-Financial Approval) System is a hierarchical internal approval workflow where NFAs are initiated by a **Senior Manager**, routed through **multiple approvers**, and finalized by a **General Manager**.

This project represents a complete **Test Automation Suite** for the NFA System, focused on scalable, readable, and report-rich automation — implemented using **Selenium WebDriver**, **Java**, **TestNG**, **Excel integration**, **ExtentReports**, and **Dockerized Selenium Grid**.

---

## 🧠 Core Functionalities of the NFA System

- **Initiator (Senior Manager)** can create NFA requests, attach docs, select type/importance, and define route.
- **Approval Routing:** Recommenders → Approvers → Final Approver.
- **Role-Based Access Control:**
  - Initiators: Create, respond to queries, resubmit rejected NFAs.
  - Recommenders: Refer/Forward once per NFA.
  - Approvers: Approve/Reject only when case is assigned.
  - Final Approver (GM): Acts after all lower roles approve.
  - Admins: Visibility only (no action).
- **Confidentiality Handling:** Confidential NFAs shown only to entitled roles.
- **Document Uploads:** Format & size validation supported.
- **Comment History:** Tracks all actions, remarks, timestamps.
- **Draft & Rejection Flow:** Controlled resubmission flow with mid-edit save.
- **Role Dashboards:** Drill-down on Pending/Rejected/Approved stats.

---

## ✅ Automation Coverage

### ✔️ Automated Test Scenarios

- **Happy Flow Testing** – Full journey: Initiator → Approver 1 → Approver 2 → Approver 3 → Final Approver.
- **Negative Testing** – Remarks left blank, mandatory field checks.
- **Random NFA Selection** – Clicks random rows, asserts field values.
- **Role-Based Test Flow** – Each role has dedicated test logic and validations.
- **UI Assertion** – Header text check, error messages, button presence.

### 📌 Test Artifacts Used
- **TestNG XML Suite** – Multi-role tests via `testng.xml`.
- **Excel-Based Data** – Login credentials fetched via Apache POI.
- **ExtentReports** – Stepwise logs, screenshots on failure, success logs.
- **Console Logs** – CDP version handling, assertion statuses shown.

---

## 🛠️ Tech Stack

| Layer         | Tech Used                          |
|---------------|------------------------------------|
| Language      | Java (JDK 21)                      |
| Automation    | Selenium WebDriver 4.23.0          |
| Framework     | TestNG 7.10.2                      |
| Data Driven   | Excel (Apache POI 5.3.0)           |
| Reporting     | ExtentReports 5.1.1                |
| Config Reader | Java `Properties` File             |
| Logging       | Log4j + IntelliJ Console           |
| Grid Support  | Selenium Grid (Docker Compose)     |
| Versioning    | Git & GitHub                       |
| Execution     | IntelliJ IDEA                      |

---

## 📂 Folder Structure

```
UCOBank_Selenium/
│
├── src/
│   ├── main/java/
│   │   └── Utils/
│   │       ├── Retryanalyzerclasses.java
│   │       ├── Propertyreader.java
│   │       └── ExcelReader.java
│
│   └── test/java/
│       ├── InitiatorTests/
│       ├── Approver1Tests/
│       ├── Approver2Tests/
│       ├── Approver3Tests/
│       ├── FinalApproverTests/
│       └── BaseTests/
│
├── resources/
│   ├── ConfigFiles/
│   │   └── data.properties
│   └── Excel/
│       └── UCOSeleniumloginexcel.xlsx
│
├── reports/
│   └── ExtentReport.html
│
├── docker-compose.yml
└── testng.xml
```

---

## 📘 Sample Test Flow Screenshot

**NFA Creation Page (Initiator View):**  
![NFA Form Submission](./screenshots/nfa_form_submission.png)

**Extent Report Output:**  
![Extent Report Screenshot](./screenshots/final_report.png)

**Negative Test Flow - Blank Remarks Validation:**  
![Remarks Error Popup](./screenshots/approver_remark_error.png)

---

## 🧪 RetryAnalyzer Logic

```java
package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retryanalyzerclasses implements IRetryAnalyzer {

    int count = 0;
    int max = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < max) {
                count++;
                return true;
            }
        }
        return false;
    }
}
```

🎯 Purpose: Flaky tests due to slow modals or dynamic pages are automatically retried **up to 3 times**.

---

## 🔧 Property Reader for Config Management

```java
package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propertyreader {
    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/ConfigFiles/data.properties");
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
```

📦 Central config file handles **base URL**, **Excel path**, and more.

---

## 🐳 Docker Compose (Selenium Grid)

```yaml
version: "3"
services:

  selenium-hub:
    image: selenium/hub
    container_name: selenium-hub
    ports:
      - 4444:4444

  chrome:
    image: selenium/node-chrome
    container_name: chrome
    depends_on:
      - selenium-hub
    profiles:
      - "browser"
    environment:
     - SE_EVENT_BUS_HOST=selenium-hub
     - SE_EVENT_BUS_PUBLISH_PORT=4442
     - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  firefox:
    image: selenium/node-firefox
    container_name: firefox
    depends_on:
      - selenium-hub
    profiles:
      - "browser"
    environment:
     - SE_EVENT_BUS_HOST=selenium-hub
     - SE_EVENT_BUS_PUBLISH_PORT=4442
     - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
```

🌐 Supports distributed tests on **Chrome** and **Firefox** containers simultaneously.

---

## 📈 Console Output Example

```
✅ Heading Verified Successfully
✅ Clicked NFA row #5
✅ Error Displayed Perfectly on Blank Remarks
[INFO] Total tests run: 3, Passes: 3, Failures: 0, Skips: 0
Process finished with exit code 0
```

---

## 🎓 Login Data from Excel

Excel file used: `UCOSeleniumloginexcel.xlsx`

- Loaded using Apache POI
- Stored in `HashMap<String, String[]>` with keys like `"initiator1"`, `"approver1"`, etc.
- Used for dynamic login in test cases

---

## 📝 Final Words

> 💬 **Special Thanks** to *Hemant Sir* for support during recovery of lost data due to local machine issues. The lost automation effort was re-written and re-tested under time pressure — reflected in this final build.

> ⏳ **Note:** Due to tight timelines, only **critical flows** were automated; rest explored and validated manually.

---

## 🚀 Result

- ✅ Fully working automation framework.
- 📊 Real-world enterprise test cases.
- 🐞 Retry logic.
- 📄 Dynamic config reader.
- 🔌 Docker Grid ready.
- 📂 Clean folder structure.
- 📸 Real screenshots + console logs.
- 📘 Excel-driven login data.
- 💼 Recruiter-grade project showcase.

---
