# Week 2

[hours](https://github.com/korolainenriikka/jani/blob/master/documentation/hourly_report.md)

## What I did

* created a dummy bot, random bot and some bot utils for handling the game
* almost finished the client program; will need some tests written but can be used already
* configurated jacoco, javadoc and checkstyle for the bots
* wrote configuration instructions between client and bots, decided how to manage code in two repositories
* wrote unit tests for the created methods & classes
* wrote javadocs for the created methods & classes

## How things are going

I'm happy/proud that I actually did what I set out to do this week, again did things I did not know I could (did the exact things listed under 'problems' last week). The client program is done except a couple tests, proper documentation and timeouts. Client could also have a tournament feature later for testing various bots against each other multiple times in one go.

State of the affairs means that I can actually start with fun stuff next week: the actual algoritm stuff.

## Problems / challenges

Testing the random bot is challenging because randomness makes tests sometimes pass and sometimes fail if there are bugs, which is obviously not good.

## Questions 

* On the game client: is it better for the game program to give the current state of the game as a 2d array or as a board interface with methods to fetch individual tile values and the board size?
* On configuration: current config step no 4 seems like one that should be avoided; however if classes copied have the old import statements othello client does not compile. Can this step be avoided?

## Next week

In the following weeks I wish to have a new bot each week that should for a good part of the times beat all previous bots. An iterative approach could also show good strategy practices for the game.

Next week there could be a simple static board evaluator (for example trying to maximize count of own pieces), a non-pruning minimax playing to depth 1 or 2, and a first performance test if time allows. The client program should not need to be modified after this except for maybe a tournament feature and timeout.
