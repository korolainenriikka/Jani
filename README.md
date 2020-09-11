# Jani

An othello-playing bot for the course data structures project, named after maybe one of the best songs ever: [https://open.spotify.com/track/0P6LQE3LjywlMNrlPctTSZ?si=T4IAE_-vSMesRrord_5NFQ](https://open.spotify.com/track/0P6LQE3LjywlMNrlPctTSZ?si=T4IAE_-vSMesRrord_5NFQ)

## Configuration

To play against these bots, or see them play against each other:

1. clone this project and the game client [othello-core](https://github.com/vuolen/othello-core) to your local machine
2. copy the bot utility package to the client directory with `cp -r Jani/src/main/java/utils/ othello-core/src/main/java/io/github/vuolen/`
3. copy the bot class you wish to see play with `cp Jani/src/main/java/bots/[botName].java othello-core/src/main/java/io/github/vuolen/bots`
4. build othello-core client with `mvn package` in the root of the othello-core repository
5. run othello-core with `java -jar <path-to-jar> [bot1name [bot2name]]` (unspecified player will be a human player)

## Documentation
* [Design document](https://github.com/korolainenriikka/Jani/blob/master/documentation/design.md)

## Weekly reports
* [Week 1](https://github.com/korolainenriikka/Jani/blob/master/week_reports/week1.md)
* [Week 2](https://github.com/korolainenriikka/Jani/blob/master/week_reports/week2.md)
