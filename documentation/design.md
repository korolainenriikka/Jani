<!--Määrittelydokumentti

    Mitä algoritmeja ja tietorakenteita toteutat työssäsi
    Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet
    Mitä syötteitä ohjelma saa ja miten näitä käytetään
    Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)
    Lähteet
    Kurssin hallintaan liittyvistä syistä määrittelydokumentissä tulee mainita opinto-ohjelma johon kuulut. Esimerkiksi tietojenkäsittelytieteen kandidaatti (TKT) tai bachelor’s in science (bSc)
    Määrittelydokumentissa tulee myös mainita projektin dokumentaatiossa käytetty kieli (todennäköisesti sama kuin määrittelydokumentin kieli). Projektin koodin, kommenttien ja dokumenttien teksti on valitulla kielellä. Tyypillisesti Suomi tai Englanti. Tämä vaatimus liittyy projektin puolen välin paikkeilla järjestettäviin koodikatselmointeihin. Tavoitteena on että projektien sisäiset kielivalinnat ovat johdonmukaisia.
-->

# Design document

## The topic

Othello is a strategy game played on a 8x8 board, rules: [othello:wikipedia](https://en.wikipedia.org/wiki/Reversi).

The smaller, 4x4 and 6x6 variations of the game have been solved computationally. Interesting parts about the game are that there is no consensus on the best opening moves and even the best game bots use varying board state evaluation functions. However, most strong bots are based on an alpha-beta pruning minimax, iteratively deepening computation, a state-value storing transposition table, and an opening book.

This bot project implements ab-minimax, iterative deepenig and a transposition table. Minimax was chosen for this project as it is the most used game algorithm and is well suitable for othello. The other performance-enhancing features were chosen because they do not involve machine learning, which is beyond the scope of this course, and were interesting to experiment with.

This document lists the used algorithms and required data structures, and analyzes their time and space complexities. For a brief reference of the principles of these features, see [implementation document](https://github.com/korolainenriikka/Jani/edit/master/documentation/implementation.md).

## Data structures and algorithms

The bots consist of two algorithms: an alpha-beta-pruning minimax algorithm, and a static evaluation function based on common strategy knowledge on othello. Two advanced bots are additionally optimized using iterative deepening and a transposition table.

Progressive deepening uses the exponential growth of the game tree as its advantage; it computes minimax scores with iteratively increasing depths and orders searched moves according to the scores from the previous search. The ordering is implemented using a min/max heap. The amount of moves is small so any less optimal ordering data structure would have sufficed, heap was implemented more or less for the sake of the sport.

The transposition table is a hash table containing moves and the data received when the minimax search is performed on a given move. The table uses Zobrist hashing as its hash function.

### Time and space complexity

The time complexity of minimax is O(b^d), where b is the branching factor (amout of possible moves) and d is the depth of the tree. The branching factor is around 10, but can fluctuate between 1-20 throughout the game. The computation depth is chosen so that the time limit of the game is not exceeded.

Alpha-beta pruning with optimal move ordering is shown to be able to cut off up to 50% of the game tree, so the time complexity is at best O(b^(d/2)). Move ordering is completely implemented only on top level, so the actual complexity lies somewhere between O(b^(d/2)) and O(b^d)

The computation cost of progressive deepening is surprisingly neglible; The exact time complexity of a progressively-deepening minimax computing to depth d-1, marked O(p) is derived in the following:

<img src="https://github.com/korolainenriikka/Jani/blob/master/documentation/images/prgrsO.png"/>

As b^d is approximately in the scale of 10^5, this result can be approximated to be O(b^d-1), which means that the computation added by progressive deepening is approximately zero.

The game itself does not set restrictions on space complexity. The space requirements of minimax is minimal; its space complexity is O(bd) with the parameters presented above; d and b being around 10. Space complexity required by the heap is also minimal as the heap only stores the possible moves on one level; approximately 10 move records. The transposition table takes up most of the space; it is made as big as the compiler allows.

## Degree and languages

* Degree: TKT

* Documentation language: english

* Programming language: java

## Sources

* [Complexity analysis of minimax](https://cis.temple.edu/~vasilis/Courses/CIS603/Lectures/l7.html)

* [Alpha-beta time complexity, advanced features: MIT OCW on artificial intelligence](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-034-artificial-intelligence-fall-2010/lecture-videos/)

