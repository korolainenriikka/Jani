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

## Ongelman kuvaus

Othello on 8x8 pelilaudalla pelattava strategiapeli, pelin säännöt: [othello:wikipedia](https://fi.wikipedia.org/wiki/Othello_(lautapeli))

Pelin pienemmät, 4x4 ja 6x6 -versiot on ratkaistu laskennallisesti, 8x8 versiossa tähän ei ole pystytty. Pelin avauksiin ei ole löydetty yksikäsitteistä parasta strategiaa ja myös pelitilanteiden evaluaattoreista on parhaissa boteissa useita eri versioita. Perusalgoritmit (optimoitu minimax) ovat samanlaisia kaikissa hyvissä boteissa.

## Data structures and algorithms

The bot will be based on an alpha-beta-pruning minimax-algorithm. The static values on the leaves of the minimax tree will be calculated with an evaluation function that will use various principles of Othello strategy, probably detecting patterns from the board.

The primary data structure of the bot will be the tree structure for minimax. Depending on the semantics of the evaluating function a small data structure, most likely a list or a hash table may be needed for storing the ranking information on various game states.

Alpha-beta-karsinnan lisäksi minimaxia pyritään tehostamaan erilaisilla symmetria- ja sääntöpohjaisilla optimoinneilla, ja syventämään puuta tehostusten onnistumisten mukaisesti.

### Time and space complexity

The time complexity of minimax is O(b^m), where b is the amout of possible moves and m is the depth of the tree. The required time will vary as the amount of moves fluctuates during the game. The game will have a time limitation per move, so the depth of the tree will be non-static and the algorithm will compute as deep as the time limitation (in spirit of data structures and algorithms -course 1.0 sec) allows.

Time complexity will also be reduced by pruning and various other symmetry or rule-based optimizations, and increased by the evaluator. The evaluator will have static evaluation values so its time complexity is most likely O(1).

The game itself does not set restrictions on space complexity. The space complexity of the minimax tree is O(bm) with the parameters presented above. Total space complexity will also possibly include the space required by the data structures of the evaluation function, which will most likely be small compared to the game tree.

## Degree and languages

* Degree: TKT

* Documentation language: english

* Programming language: java

## Sources

* [Complexity analysis of minimax](https://cis.temple.edu/~vasilis/Courses/CIS603/Lectures/l7.html)
