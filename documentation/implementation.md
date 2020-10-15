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

* TileScorer: minimax with weighed tile maximized scores

* AlphaTileScorer: alpha-beta pruning minimax with weighed tile scoring

* Prgrs: a-b pruning progressively deepening minimax with mobility/stability evaluation

* SuperBot: same as prgrs + transposition table

## The features

### Minimax and alpha-beta pruning

Implemented very similar to the standard pseudocode; as a terminal state is used the state where the game is over. Depth 0 uses heuristic evaluation implemented either as weighed tile count maximizing (the sum of tile scores where the player has pieces), or as another using stability and mobility scoring with stability/mobility weighing changing throughout the game.

### Progressive deepening

Progressive deepening uses the big branching factor of the game to its advantage, computes evaluations on every depth iteratively and orders moves by the evaluation scores between every iteration, which is implemented as a min/max heap. This allows the algorithm to alter minimax search depth dynamically to perform the deepest possible search in any situation within the time constraint.

### Transposition table

A hash table storing entries for already searched game states. Entries have types exact, min and max and contain evaluator values and moves estimated best in a situation. The table uses Zobrist hashing and a replacement scheme to decide which entries should be replaced in case of hash collision. Evaluation values are used to save computation, best move to further improve progressive deepening. This feature was not fully completed and did not result in significant performance enhancements.

# Performance achieved

As the algoritms are implemented very much like the pseudocode, the achieved time complexity of minimax is O(b^d), and pruning reduces the computations somewhat, but definitely less than the optimal O(b^(d/2)), as the moves are not optimally ordered. The bot could compute about 2 moves further using pruning.

Iterative deepening adds around 10% of computation, but as it allows dynamic changes of minimax depths, this feature added the most to the bots' ability to predict the course of the game; without iteration the depth assigned to minimax always has to consider worst-case branching to avoid timeouts. Without this constraint the depth increate was usually 0-3 compared to a-b pruning, but in some cases up to 10.

The transposition table added a lot of space usage and proved to have neglible improvements on perfomance. However, this was implemented on the last week as more of an experiment and would probably have needed more refinement to see significant performance increase. 

# Improvements and flaws left in the program
* The transposition table is very much unfinished, it was implemented more for the sake of experimentation than as a necessary part of the bot program

* Evaluation function is not very refined, this would require experimentation and in best case implementation of machine learning features

* Permanent data storage on good opening moves (opening book).

* Generally the testing of the program is not very good; it is especially hard to test AI. The functionalities of core features were tested with prints.

* Code quality is not ideal as there are many bots using different versions of the used algorithms, which causes a lot of repetition in the code. However, the approach was good considering that possibilities for comparative analysis were greatly increased.

## Sources
[CS cornell: othello](http://www.cs.cornell.edu/~yuli/othello/othello.html)

[logistello evaluator](https://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.49.7258&rep=rep1&type=pdf)

[on strong othello bots](https://link.springer.com/content/pdf/10.1007/978-0-387-35660-0_10.pdf)

[samsoft strategy](http://samsoft.org.uk/reversi/strategy.htm#rules)

[transposition table implementation](https://web.archive.org/web/20071031100051/http://www.brucemo.com/compchess/programming/hashing.htm)

[zobrist hashing](https://en.wikipedia.org/wiki/Zobrist_hashing)

