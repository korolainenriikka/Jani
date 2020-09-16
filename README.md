# Jani

An othello-playing bot for the course data structures project, named after maybe one of the best songs ever: [https://open.spotify.com/track/0P6LQE3LjywlMNrlPctTSZ?si=T4IAE_-vSMesRrord_5NFQ](https://open.spotify.com/track/0P6LQE3LjywlMNrlPctTSZ?si=T4IAE_-vSMesRrord_5NFQ)

The othello game client is copied from [othello-core](https://github.com/vuolen/othello-core)

## How to run

To play against these bots, or see them play against each other:

1. build othello-core client with `mvn package` in the root of the othello-core repository
2. run othello game or tournament with `java -jar <path-to-jar> [bot1name [bot2name [numberOfGames]]]` (unspecified player will be a human player, if third parameter specified a tournament will be run)

Bots currently supported:

* JaniDummy: a bot playing the first allowed move it finds.
* JaniRandom: a bot playing randomly.
* JaniTileScorer: a simple minimax bot trying to maximize count of pieces in strategically important tiles.

## Other command-line arguments

### test coverage

Run `mvn test jacoco:report`

The test coverage raport can be found in *target/site/jacoco/index.html*.

### javadoc

Generate javadoc with `mvn javadoc:javadoc`

JavaDoc can be found in *target/site/apidocs/index.html*.

### checkstyle

Check if coding style corresponds with rules specified in [checkstyle.xml](https://github.com/korolainenriikka/Jani/blob/master/checkstyle.xml) with `mvn jxr:jxr checkstyle:checkstyle`.

Error specifications can be found in *target/site/apidocs/index.html*.

## Documentation
* [Design document](https://github.com/korolainenriikka/Jani/blob/master/documentation/design.md)
* [Implementation](https://github.com/korolainenriikka/Jani/blob/master/documentation/implementation.md)

## Weekly reports
* [Week 1](https://github.com/korolainenriikka/Jani/blob/master/week_reports/week1.md)
* [Week 2](https://github.com/korolainenriikka/Jani/blob/master/week_reports/week2.md)
* [Week 2](https://github.com/korolainenriikka/Jani/blob/master/week_reports/week3.md)
