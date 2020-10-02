# Week 5

[hours](https://github.com/korolainenriikka/jani/blob/master/documentation/hourly_report.md)

Still a lot of other stuff going on in life this week.

## What I did

* refactored classes
* wrote one better evaluator
* implemented progressive deepening with better computation time usage
* wrote and unit tested the heap structure (max heap debug not ready)

## How things are going

Heap implementation was rather effortless as expected. Progressive deepening fared in matches surprisingly well and enabled the dynamic
changing of minimax depths. The depths during games varied a lot, the value was between 4-12. This was a major heureka moment.
It is unsure whether the transistion table will happen or not; i am still unsure how it could be implemented.

## Problems

* Testing AI features remains a challenge.

## Next week

* heap debug and changing the bot to a self-written heap using one
* evaluators
* progressive deepening performance testing
