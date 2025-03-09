# Weather Visualizer
    Это приложение для получения и хранения данных о погоде.
    Оно использует Spring Boot, Vaadin, PostgreSQL и MongoDB.

# Для локального запуска
    Требования:
        Java 17, Maven, PostgreSQL, MongoDB, Node.js (для сборки фронтенда Vaadin)

1. Клонируйте репозиторий:
   git clone https://github.com/wezde/WeatherApplication.git
   cd WeatherApplication

2. Настройте базы данных:
   Создайте базу данных weather_db в PostgreSQL.
   Убедитесь, что MongoDB запущена и доступна на localhost:27017.

3. Настройте переменные окружения:
   Создайте файл .env в корне проекта и добавьте в него следующие переменные:
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/weather_db
   SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/weather_db
   OPENWEATHER_API_KEY=your_API_KEY
   OPENWEATHER_API_URL=https://api.openweathermap.org/data/2.5/weather

4. Соберите проект:
   бэк:                      mvn clean install
   фронт(если понадобиться): mvn vaadin:build-frontend

6. Запустите приложение:
   mvn spring-boot:run

7. Откройте приложение в браузере:
   Перейдите по адресу: http://localhost:8080

# Через Docker-compose

1. Убедитесь, что у вас установлены Docker и Docker Compose.

2. Клонируйте репозиторий:
      git clone https://github.com/wezde/WeatherApplication.git
      cd WeatherApplication

3. Настройте переменные окружения:
   Создайте файл .env в корне проекта и добавьте в него следующие переменные:
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=postgres
   SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/weather_db
   SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/weather_db
   OPENWEATHER_API_KEY=your_API_KEY
   OPENWEATHER_API_URL=https://api.openweathermap.org/data/2.5/weather

4. Соберите и запустите контейнеры:
   docker-compose up --build

5. Если получаете ошибку:
   Ошибка failed to resolve source metadata for docker.io/library/openjdk:17-jdk-alpine:
   no match for platform in manifest указывает на то, 
   что Docker не может найти образ openjdk:17-jdk-alpine 
   Измените образ java в Dockerfile на вот этот (eclipse-temurin:17-jdk-jammy)