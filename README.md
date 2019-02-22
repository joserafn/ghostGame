# Ghost Game

## Description

Ghost is a game in which players take turns adding letters to a growing word fragment, trying not to be the one to complete a valid word. Each fragment must be the beginning of an actual word, and usually some minimum is set on the length of a word that counts, such as three or four letters. The player who completes a word loses the round and earns a "letter", with players being eliminated when they have been given all five letters of the word "ghost".

## Implementation

There will be two players, player 1 (human) and player 2 (machine). Player 1 always starts the round.
When a not valid word is introduced by human or machine, an alert raises showing the error. When a player fails a word, its score will be increased by 1 and a new round (word) starts. When a player reaches 5 points (letters forming the word GHOST), this player loses the game.

The game has been developed using Spring Boot as framework, Thymeleaf as templates engine and Bootstrap as front end framework.

JUnit and Mokito are the selected testing tools as they come included with Spring.

### Testing

To run all the tests, execute:
```
mvn test
```

### Starting 

To start the app from command line, run the next command:
```
mvn spring-boot:run
```

By default the application starts using the 8080 port, so the URL to reach 
the app will be:
```
http://localhost:8080/ghost
```
