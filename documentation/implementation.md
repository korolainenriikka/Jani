# Implementation document

## The evaluation function
(this document contains currently notes for evaluation function implementation)
[CS cornell](http://www.cs.cornell.edu/~yuli/othello/othello.html)

* uses some move ordering in order to help pruning more quickly. The move ordering is very primitive, and it simply tests first the placed around the disc which the opponent has just played, since there is a somewhat larger probability that those places will be more favorable
(minimax optimization)

* heuristic for > 14 empty squares (state evaluation) , brute-force for < 14 (minimax until board full)

* brute-force: tries to win, if cannot, tries to minimize opponent's score (amount of discs)

* legal moves count: +1

* corner square: +10 points

* speed improvement possible with cutting 'obviously bad moves'

[logistello evaluator](https://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.49.7258&rep=rep1&type=pdf)

* number of moves, mobility, parity

* stable discs: unflippable

* mobility: count of legal moves (time consuming) -> logistello approximates

* pattern calculation (seems unnecessarily complex for now)

[on strong othello bots](https://link.springer.com/content/pdf/10.1007/978-0-387-35660-0_10.pdf)

* stability (stable discs), mobility (available moves), parity (trying to get the last move in a region)

*e(p) = C1(P)· EdgeStability(p) + 36· InternaIStability(P)+ C2(p) . CurrentMobility(p) + 99· PotentialMobility(p)
iago evaluator, c1 and c2 increase during the game