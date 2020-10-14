<!--

    Mitä on testattu, miten tämä tehtiin
    Minkälaisilla syötteillä testaus tehtiin (vertailupainotteisissa töissä tärkeää)
    Miten testit voidaan toistaa ZEK
    Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa.
    Testaus on ideaalitapauksessa suoritettava ohjelma. Tällöin testi on helposti toistettavissa, mikä helpottaa toteutuksen tekoa jo varhaisessa vaiheessa. Javalla tehdyissä töissä on erittäin suositeltavaa käyttää testaukseen JUnitia. ZEK

-->

# Testing Document

## Test coverage:
<img src="https://github.com/korolainenriikka/Jani/blob/master/documentation/images/testcoverage_week6.png"/>

Re-run tests with `mvn test` in repository root.

## Performance tests

Performance tests are implemented for both standard and iteratively deepening minimax to check how deep the program computes with various evaluators etc.


### Empirical results of performance tests
<!-- 
tänne joku grafiikka mitähelee
-->

## Game results:

The bots playing against each other yielded the following results, percentiles calculated as an average of total 600 games of the bots playing against each other (3 100-game tournaments for both bots as starting player):

* Random v TileScorer: 53% of games won by TileScorer

* Random v AlphaTileScorer: 56% of games won by Alpha

* Random v Prgrs: 88% of games won by prgrs (bot improved after this result)

* TileScorer v AlphaTileScorer: player 2 wins every time whichever bot is player 2

* Prgrs wins all non-random bots every time



