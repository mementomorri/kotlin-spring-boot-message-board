# kotlin-spring-boot-message-board
Простое, мультиплатформенное приложение по типу месседж-борда, сделаное с помощью Kotlin и Spring boot на основе веб-сокетов. [Демо приложения.](https://kotin-springboot-message-board.herokuapp.com/)
![](https://github.com/mementomorri/kotlin-spring-boot-message-board/blob/main/screenshot.png)
# Gradle tasks
Запуск приложения происходит с помощью следющих задач:
* backendRun - запускает сервер отладки серверной части приложения, слушающий порт `:8080`;
* frontendRun - запускает отладочный webpack сервер, слушающий порт `:3000`;

Сборка пакетов приложения происхоит с помощью следующих задач:
* frontendBrowserWebpack - собирает скомпилированные JS файлы в папке `build/distributions`;
* frontendJar - собирает отдельный JAR файл включающий в себя всё необходимое для работы пользовательского интерфейса в папке `build/libs/kotlin-spring-boot-message-board-1.0-frontend.jar`;
* backendJar - собирает JAR файл включающий в себя всё необходимое для работы серверной части приложения в папке `build/libs/kotlin-spring-boot-message-board-1.0-backend.jar`;
* jar - собирает JAR файл включающий в себя всё для работы серверной части приложения и пользовательского интерфейса приложения в папке `build/libs/kotlin-spring-boot-message-board-1.0.jar`.
