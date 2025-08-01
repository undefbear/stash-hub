# 📦 stash-hub

Персональное кросс-девайс хранилище данных — "stash-hub"  
Проект на Java + Spring Boot с использованием PostgreSQL и Thymeleaf.

---

## 🗂️ Структура проекта

```
📦 stash-hub
│
├── 📁 src
│   └── 📁 main
│       ├── 📁 java
│       │   └── 📁 ru
│       │       └── 📁 undefbear
│       │           ├── 📁 controller       # REST и HTML контроллеры
│       │           │   └── StashItemController.java
│       │           │
│       │           ├── 📁 dto              # Для контроллеров
│       │           │   └── StashItemDto.java
│       │           │
│       │           ├── 📁 entity           # Для репозитория
│       │           │   └── StashItem.java
│       │           │
│       │           ├── 📁 mapper           # Для конвертации DTO ⇄ Entity
│       │           │   └── StashItemMapper.java
│       │           │
│       │           ├── 📁 repository       # Интерфейсы Spring Data
│       │           │   └── StashItemRepository.java
│       │           │
│       │           ├── 📁 service          # Бизнес-логика
│       │           │   └── StashItemService.java
│       │           │
│       │           ├── 📁 config           # Конфигурации (Web, Security)
│       │           │   └── WebConfig.java
│       │           │
│       │           └── 📁 security         # Проверка API-ключа и пр.
│       │               └── ApiKeyFilter.java
│       │
│       └── 📁 resources
│           ├── 📁 templates                # HTML-шаблоны для Thymeleaf
│           │   └── index.html
│           │
│           └── application.properties       # Настройки приложения
│
├── 📄 pom.xml                               # Зависимости и настройки Maven
└── 📄 README.md                             # Описание проекта и архитектуры
```

