<!--

    Mitä on testattu, miten tämä tehtiin
    Minkälaisilla syötteillä testaus tehtiin (vertailupainotteisissa töissä tärkeää)
    Miten testit voidaan toistaa ZEK
    Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa.
    Testaus on ideaalitapauksessa suoritettava ohjelma. Tällöin testi on helposti toistettavissa, mikä helpottaa toteutuksen tekoa jo varhaisessa vaiheessa. Javalla tehdyissä töissä on erittäin suositeltavaa käyttää testaukseen JUnitia. ZEK

-->

# Testing Document

## Test coverage:
<img src="https://github.com/korolainenriikka/Jani/blob/master/documentation/images/final_test_coverage.png"/>

Re-run tests with `mvn test` in repository root.

Unit tests of AI were written with game states with known best next moves. As there are very few situations like this, the quality of unit tests is not ideal.

## Performance tests

Performance tests are implemented for both standard and iteratively deepening minimax to check how deep the program computes with various minimax versions.

The performance testing was performed used three hardcoded game states from start, middle and the end of the game. The tests measure how deep minimax / pruning minimax / iteratively deepening minimax computes within the 1.0 sec time constraint. These results can be used as an approximation of the actual depths, however, there are extreme cases where the depth reached can be unusually small / large. No checks were done to assure that the game states selected for performance tests are exceptional in this sense.


### Empirical results of performance tests
The output of the performance tests are as follows:

<img src="https://github.com/korolainenriikka/Jani/blob/master/documentation/images/performanceresults.png"/>

Progressive deepening tests are in order beginning -> midgame -> endgame.

## Game results:

The bots playing against each other yielded the following results, percentiles calculated as an average of total 600 games of the bots playing against each other (3 100-game tournaments for both bots as starting player):

* Random v TileScorer: 53% of games won by TileScorer

* Random v AlphaTileScorer: 56% of games won by Alpha

* Random v Prgrs: 95% of games won by Prgrs

* TileScorer v AlphaTileScorer: player 2 wins every time whichever bot is player 2

* Prgrs wins all non-random bots every time

* SuperBot wins all other bots except prgrs approx. 70% of games.



