# My Answers

Interactive portfolio of programming challenges solved in **Java**, inspired by [Beecrowd](https://www.beecrowd.com.br/).

Instead of a static list of snippets, visitors can **read the problem**, **inspect the solution**, **read an explanation**, and **run inputs live** — in Portuguese and English.

Built as a practical showcase of backend engineering with Spring Boot.

**Author:** Kleber Lima · Backend Software Engineer  
**Contact:** [kleber-r@outlook.com](mailto:kleber-r@outlook.com)  
**English:** B1 (improving) · Open to remote opportunities abroad

---

## Why this project

Recruiters and engineers can evaluate more than syntax:

- how problems are structured and explained
- how Java solutions are written and simplified
- how a small Spring Boot app is organized (web + API + cache + i18n)
- how the UI behaves on desktop and mobile

## Features

- Problem browser with pagination
- Live input runner for selected challenges
- Solution explanation panel
- Fullscreen code view
- Bilingual UI (`/en`, `/pt`)
- About Me profile with contact
- Caffeine cache for paged answers
- Responsive modern UI

## Tech stack

| Layer | Choices |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4 |
| Web | Spring MVC + Thymeleaf |
| API | REST endpoints for paging and execution |
| Persistence | Spring Data JPA + MySQL |
| Cache | Caffeine |
| Frontend | Bootstrap 5, jQuery, Prism, custom CSS |
| Build | Maven |

## Quick start

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8+

### 1. Clone

```bash
git clone https://github.com/kleberlimaGit/my-answers.git
cd my-answers
```

### 2. Database

Create a schema:

```sql
CREATE DATABASE db_answers CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Default local credentials (dev profile):

- URL: `jdbc:mysql://localhost:3306/db_answers`
- User: `root`
- Password: `root`

Override in `src/main/resources/application-dev.properties` if needed.

### 3. Run

```bash
mvn spring-boot:run
```

Open:

- [http://localhost:8080/en/answers](http://localhost:8080/en/answers)
- [http://localhost:8080/pt/answers](http://localhost:8080/pt/answers)

## Main endpoints

| Method | Path | Description |
|---|---|---|
| `GET` | `/{lang}/answers` | Problem page (UI) |
| `GET` | `/{lang}/answers/page?page=` | Paginated JSON |
| `POST` | `/{lang}/answers` | Execute inputs for a problem |
| `GET` | `/{lang}/about-me` | About Me content (modal) |

`lang` = `en` or `pt`

## Project structure

```text
src/main/java/com/my/answers/
  config/          # locale + messages
  entity/          # JPA entities + DTOs
  repository/      # data access
  resolver/        # question service resolution + locale
  service/         # answers + per-question executors
  web/             # MVC controllers, REST, error handling
src/main/resources/
  static/          # CSS, JS, images
  templates/       # Thymeleaf pages/fragments
  messages_*.properties
```

## Configuration

Profiles:

- `dev` (default): local MySQL
- `prd`: production DB via environment variables

Production variables:

- `PROFILE_ANSWERS=prd`
- `PRD_ANSWERDB_URL`
- `PRD_ANSWERDB_USERNAME`
- `PRD_ANSWERDB_PASSWORD`

## What I’m aiming for next

- Public deploy with a stable portfolio URL
- More curated challenges with stronger explanations
- Broader automated tests around execution flows

## Contact

If you want to talk about backend roles (Java/Spring, remote):

- Email: **kleber-r@outlook.com**
- GitHub: [kleberlimaGit/my-answers](https://github.com/kleberlimaGit/my-answers.git)

---

Inspired by Beecrowd problem-solving practice. Built to show engineering thinking, not only accepted answers.
