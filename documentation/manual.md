# Manual

## Configuration

* generate jar with `mvn package` in project root

* the jar is compiled in */target* directory

* tests are located in *src/test/java/othello*

* to add your own bot: add the bot implementing interface OthelloBot as specified in *Jani/src/main/java/othello/api/OthelloBot.java* to *Jani/src/main/java/othello/bots/* and follow the usage instructions below.

## How to run

* run generated jar with `java -jar target/jarName.jar` in project root to play human against human game.

* optional parameters: `bot1` to play a bot v human -game, `bot1 bot2` to see two bots play against each other, `bot1 bot2 numberofgames` to execute a tournament with specified number of games. All bot names should be specified as in class name.
