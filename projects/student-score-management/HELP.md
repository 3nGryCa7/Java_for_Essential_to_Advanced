# Read Me First

## Build Software
- Docker (Postgres)
- Intellij IDE (Community version)
- Java 21

### Setup Steps
1. Run docker container with command or open `docker-compose.yml` file to start
    ```bash
   docker compose up -d
    ```
2. Run `StudentScoreManagementApplication` 

## Specs

### Database
In *StudentScoreManagementApplication*, we use Postgres database.

In the beginning, the application is filled with some sample data.

The initialize file is `init.sql`.

Database information is shown on `docker-compose.yml`. (The following is preview)
```text
POSTGRES_USER: user
POSTGRES_PASSWORD: password
POSTGRES_DB: score_management
```

Get into Postgres shell from docker
```bash
psql -U user -d score_management
```

The database has four tables :
- students : All students 
- courses : All courses
- selected_courses : The courses enrolled by students
- grades : All exam scores of courses

## Project Structure

### Root Folder
- `StudentScoreManagementApplication` is an entry point.
- `DatabaseManager` interact with database directly.
- `ChartGenerator` generates charts for exams.

### Models Folder
`Course`, `Grade`, `SelectedCourse`, `Student`represent basic entity respectively.

### Pages Folder
This application has three pages. 
Two main page `CoursePage` and `StudentPage`. 
Then you can open `CourseDetailPage` after click any courses displayed on `CoursePage`.

### Table Models Folder
To customize the display of each page.


## Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.5/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.5/gradle-plugin/packaging-oci-image.html)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

