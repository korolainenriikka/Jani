# Week 3

[hours](https://github.com/korolainenriikka/jani/blob/master/documentation/hourly_report.md)

## What I did

* did first version of minimax and a simple evaluator
* added a feature to the client
* started performance testing for finding optimal minimax depths
* researched ways to improve the bot
* started implementation and testing documents
* implemented first alpha-beta pruning, currently timeouts often
* improved testing, javadoc and coding style

## How things are going

Current bot has the basic algorithms implemented but does not play especially well; random is beated in about 2/3 of the games played, dummy is won only if current bot plays as player 2. However, these bots are bad "pracitice opponents" for the bot as minimax assumes the other player can choose good moves.

The first implementation of pruning is not yet in git as it is pretty much a work in progress. In a tournament against random or the tile scorer the pruning bot only lost games by disqualification due to timeout

## Problems

* testing minimax is hard; it is hard to determine what the bot should choose to do
* finding optimal search depth

## Next week

* next week there should be an algorithm using pruning and updated minimax depths to compute deeper. Possibly state value hashing using java.util libraries
* fix cli timeout bug
* after that the trasistion table should be transferred to a self-made hash table. I could also start to reserch progressive deepening and opening book implementation. And move ranking/ordering. Endless possibilities, really :).
