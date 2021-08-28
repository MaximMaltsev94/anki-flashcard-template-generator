# Anki flashcard template generator tool

## About
Application is intended to help with generation of flashcards for [Anki application](https://apps.ankiweb.net/).

## Key features
1. Generation of quick access links to [Google translate](http://translate.google.com/) and [ReversoContext](https://context.reverso.net/translation/english-russian/) for specified word.
2. Searching and downloading of word pronunciation 
3. Generation of flashcard templates (html) for insertion to Anki application.


## Prerequisite
- Java 11
- Maven 3.x

## Usage
1. Build an application:
```
mvn clean package
```

2. Launch application:
```
java -jar target/anki-flashcard-template-generator-{version}.jar
```

3. Open application in browser:
```
http://localhost:8080/
```