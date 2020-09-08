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

The smaller, 4x4 and 6x6 variations of the game have been solved computationally. Interesting parts about the game are that there is no consensus on the best opening moves and even the best game bots use varying board state evaluation functions. However, the basic data structure (minimax tree) is similar in most othello bots.

## Data structures and algorithms

The bot will be based on an alpha-beta-pruning minimax-algorithm. The static values on the leaves of the minimax tree will be calculated with an evaluation function that will use various principles of Othello strategy, probably detecting patterns from the board.

The primary data structure of the bot will be the tree structure for minimax. Depending on the semantics of the evaluating function a small data structure, most likely a list or a hash table may be needed for storing the ranking information on various game states.

### Time and space complexity

The time complexity of minimax is O(b^m), where b is the amout of possible moves and m is the depth of the tree. The required time will vary as the amount of moves fluctuates during the game. The game will have a time limitation per move, so the depth of the tree will be non-static and the algorithm will compute as deep as the time limitation (in spirit of data structures and algorithms -course 1.0 sec) allows.

Time complexity will also be reduced by pruning and various other symmetry or rule-based optimizations, and increased by the evaluator. The evaluator will have static evaluation values so its time complexity is most likely O(1).
https://en.wikipedia.org/wiki/Reversi
The game itself does not set restrictions on space complexity. The space complexity of the minimax tree is O(bm) with the parameters presented above. Total space complexity will also possibly include the space required by the data structures of the evaluation function, which will most likely be small compared to the game tree.

## Degree and languages

* Degree: TKT

* Documentation language: english

* Programming language: java

## Sources

* [Complexity analysis of minimax](https://cis.temple.edu/~vasilis/Courses/CIS603/Lectures/l7.html)
