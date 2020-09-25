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

The smaller, 4x4 and 6x6 variations of the game have been solved computationally. Interesting parts about the game are that there is no consensus on the best opening moves and even the best game bots use varying board state evaluation functions. However, the basic data structure (minimax tree) is similar in most othello bots, and most strong bots evaluate with pattern recognition and have self-correcting opening books.

## Data structures and algorithms

The bot consists of two algorithms: an alpha-beta-pruning minimax algorithm, and a static evaluation function based on common strategy knowledge on othello (pruning not implemented yet).

Alpha-beta pruning can be further optimized with ordering of searched game states, which may be later implemented as an ordering algorithm.

The primary data structure of the algorithm will be a transistion table implemented as a hash table used for storing already-calculated minimax values. Especially at the end of game stage in othello similar states often replicate and thus storing values for these states can make the algorithm significantly more efficient.

The bot can be made stronger with the implementation of an opening book and progressive deepening, but these topics will require further research.

### Time and space complexity

The time complexity of minimax is O(b^d), where b is the amout of possible moves and d is the depth of the tree. The required time will vary as the amount of moves fluctuates during the game usually between 3-15. The game will have a time limitation per move, so the depth of the minimax search will be non-static and used to compute as deep as the 1.0 sec time limit allows. Depths of about 5 moves are easily obtainable, with optimizations depths up to around 10 may be possible. Best bots out there can compute as deep as over 20 moves.

Time complexity will be reduced by pruning and various other symmetry or rule-based optimizations, and increased by the evaluator. The evaluator will have static evaluation values so its time complexity is O(1). Alpha-beta pruning with optimal move ordering has a time complexity of O(b^(d/2)).

The game itself does not set restrictions on space complexity. The space requirements of minimax is minimal; its space complexity is O(bd) with the parameters presented above; d and b being around 10. The hash table for storing minimax values will have the biggest space complexity; othello has a total of around 250 000 game states, a fraction of which will only be stored as hashing is mostly beneficial only at the end of the game and symmetry can be used to reduce the need of data storing.

## Degree and languages

* Degree: TKT

* Documentation language: english

* Programming language: java

## Sources

* [Complexity analysis of minimax](https://cis.temple.edu/~vasilis/Courses/CIS603/Lectures/l7.html)

* [Alpha-beta time complexity, advanced features: MIT OCW on artificial intelligence](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-034-artificial-intelligence-fall-2010/lecture-videos/)

