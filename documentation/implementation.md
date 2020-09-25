# Implementation document

## Current bots

* Dummy: first allowed move found

* Random: a random move of the allowed ones

* TileScorer: minimax with weighed tile maximized scores (by samsoft weighs)

* AlphaTileScorer: alpha-beta pruning minimax with weighed tile scoring

* Prgrs: a-b pruning progressively deepening minimax with mobility/stability evaluation (in development)

## The features

### Minimax and alpha-beta pruning

Implemented very similar to the standard pseudocode; as a terminal state is used the state where the game is over. Depth 0 uses heuristic evaluation implemented either as weighed tile count maximizing (the sum of tile scores where the player has pieces), or as another using stability and mobility scoring (not yet implemented)

### Progressive deepening

Progressive deepening uses the big branching factor of the game to its advantege, computes evaluations on every depth iteratively and orders moves by the evaluation scores between every iteration, which is implemented as a min/max heap. This allows the algorithm to alter minimax search depth dynamically to perform the deepest possible search in any situtaion within the time constraint.

### Transistion table (for later versions)

A data stucture for speeding up end of game computation. When minimax calculates to a terminal game state, it hashes the evaluation score of the searched states. This should speed up end-of-game computation up to 50 %. A transistion table is also said to be crucial with progressive deepening, but this idea requires further research.


## Sources
[CS cornell: othello](http://www.cs.cornell.edu/~yuli/othello/othello.html)

[logistello evaluator](https://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.49.7258&rep=rep1&type=pdf)

[on strong othello bots](https://link.springer.com/content/pdf/10.1007/978-0-387-35660-0_10.pdf)

[samsoft strategy](http://samsoft.org.uk/reversi/strategy.htm#rules)

