# My Answers

This project is a web application built with Spring Boot that showcases solutions to programming problems from Beecrowd (URI Online Judge). It allows users to view, search, and execute code snippets for various problems in multiple languages.

## Features

- **Multilingual Support**: Available in Portuguese (PT_BR) and English (EN_US)
- **Problem Solutions**: Displays solutions for Beecrowd problems with code snippets
- **Code Execution**: Interactive execution of code snippets
- **About Me**: Personal information section
- **Responsive Design**: Mobile-friendly interface using Bootstrap 5
- **Caching**: Optimized performance with Caffeine caching

## Technology Stack

- **Backend**: Java 17, Spring Boot 4.0.1
- **Database**: MySQL
- **Frontend**: Thymeleaf, Bootstrap 5, jQuery, Font Awesome
- **Build Tool**: Maven
- **Caching**: Caffeine

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

## Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/kleberlimaGit/my-answers.git
   cd my-answers
   ```

2. **Database Setup**:
   - Create a MySQL database named `db_answers`
   - For development, use the default credentials (root/root) or update `application-dev.properties`

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

   Or run with a specific profile:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

5. **Access the application**:
   - Open your browser and go to `http://localhost:8080/myanswers`
   - Default redirect: `http://localhost:8080/myanswers/en/answers`

## Configuration

The application uses Spring profiles for different environments:

- **dev**: Development profile with local MySQL database
- **prd**: Production profile with environment variables for database connection

### Environment Variables (Production)

Set the following environment variables for production:

- `PROFILE_ANSWERS=prd`
- `PRD_ANSWERDB_URL`: JDBC URL for production database
- `PRD_ANSWERDB_USERNAME`: Database username
- `PRD_ANSWERDB_PASSWORD`: Database password

### Application Properties

Key configuration files:
- `src/main/resources/application.properties`: Common settings
- `src/main/resources/application-dev.properties`: Development settings
- `src/main/resources/application-prd.properties`: Production settings
- `src/main/resources/messages_en_US.properties`: English messages
- `src/main/resources/messages_pt_BR.properties`: Portuguese messages

## Project Structure

```
src/
├── main/
│   ├── java/com/my/answers/
│   │   ├── AnswersApplication.java          # Main application class
│   │   ├── config/                          # Configuration classes
│   │   ├── entity/                          # JPA entities
│   │   ├── repository/                      # Data repositories
│   │   ├── service/                         # Business logic services
│   │   ├── utils/                           # Utility classes
│   │   └── web/                             # Controllers and web layer
│   └── resources/
│       ├── static/                          # CSS, JS, images
│       ├── templates/                       # Thymeleaf templates
│       └── application*.properties          # Configuration files
└── test/
    └── java/com/my/answers/                  # Unit tests
```

## API Endpoints

- `GET /{lang}/answers`: List problems with pagination
- `GET /{lang}/about-me`: About me page
- `POST /{lang}/answers`: to execute code snippets

## Acknowledgments

- [Beecrowd](https://www.beecrowd.com.br/) for the programming problems
- [Spring Boot](https://spring.io/projects/spring-boot) for the framework
- [Bootstrap](https://getbootstrap.com/) for the UI components</content>