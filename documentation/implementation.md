<!--
Toteutusdokumentti

    Ohjelman yleisrakenne: aika guud atm
    Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista): PUUTTUU
    Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen): PUUTTUU
    Työn mahdolliset puutteet ja parannusehdotukset: semiok
    Lähteet-->

# Implementation document

## Current bots

* Dummy: first allowed move found

* Random: a random move of the allowed ones

* TileScorer: minimax with weighed tile maximized scores (by samsoft weighs)

* AlphaTileScorer: alpha-beta pruning minimax with weighed tile scoring

* Prgrs: a-b pruning progressively deepening minimax with mobility/stability evaluation

* SuperBot: same as prgrs + transposition table

## The features

### Minimax and alpha-beta pruning

Implemented very similar to the standard pseudocode; as a terminal state is used the state where the game is over. Depth 0 uses heuristic evaluation implemented either as weighed tile count maximizing (the sum of tile scores where the player has pieces), or as another using stability and mobility scoring (not yet implemented)

### Progressive deepening

Progressive deepening uses the big branching factor of the game to its advantege, computes evaluations on every depth iteratively and orders moves by the evaluation scores between every iteration, which is implemented as a min/max heap. This allows the algorithm to alter minimax search depth dynamically to perform the deepest possible search in any situtaion within the time constraint.

### Transposition table

A hash table storing entries for already searched game states. Entries have types exact, min and max and contain evaluator values and moves estimated best in a situation. The table uses Zobrist hashing and a replacement scheme to decide which entries should be replaced in case of hash collision. Evaluation values are used to save computation, best move to further improve progressive deepening.

# Time / space complexities achieved

# Improvements and flaws left in the program

* Evaluation function is not very refined, this would require experimentation and in best case implementation of machine learning features

* Permanent data storage on good opening mvoes (opening book). Self-improving.

* Generally the testing of the program is not very good; it is especially hard to test AI. The functionalities of core features were tested with prints.

## Sources
[CS cornell: othello](http://www.cs.cornell.edu/~yuli/othello/othello.html)

[logistello evaluator](https://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.49.7258&rep=rep1&type=pdf)

[on strong othello bots](https://link.springer.com/content/pdf/10.1007/978-0-387-35660-0_10.pdf)

[samsoft strategy](http://samsoft.org.uk/reversi/strategy.htm#rules)

[transposition table implementation](https://web.archive.org/web/20071031100051/http://www.brucemo.com/compchess/programming/hashing.htm)

[zobrist hashing](https://en.wikipedia.org/wiki/Zobrist_hashing)

