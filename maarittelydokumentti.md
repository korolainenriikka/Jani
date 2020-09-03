<!--Määrittelydokumentti

    Mitä algoritmeja ja tietorakenteita toteutat työssäsi
    Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet
    Mitä syötteitä ohjelma saa ja miten näitä käytetään
    Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)
    Lähteet
    Kurssin hallintaan liittyvistä syistä määrittelydokumentissä tulee mainita opinto-ohjelma johon kuulut. Esimerkiksi tietojenkäsittelytieteen kandidaatti (TKT) tai bachelor’s in science (bSc)
    Määrittelydokumentissa tulee myös mainita projektin dokumentaatiossa käytetty kieli (todennäköisesti sama kuin määrittelydokumentin kieli). Projektin koodin, kommenttien ja dokumenttien teksti on valitulla kielellä. Tyypillisesti Suomi tai Englanti. Tämä vaatimus liittyy projektin puolen välin paikkeilla järjestettäviin koodikatselmointeihin. Tavoitteena on että projektien sisäiset kielivalinnat ovat johdonmukaisia.
-->

# Määrittelydokumentti

## Ongelman kuvaus

Othello on 8x8 pelilaudalla pelattava strategiapeli, pelin säännöt: [othello:wikipedia](https://fi.wikipedia.org/wiki/Othello_(lautapeli))

Pelin pienemmät, 4x4 ja 6x6 -versiot on ratkaistu laskennallisesti, 8x8 versiossa tähän ei ole pystytty. Pelin avauksiin ei ole löydetty yksikäsitteistä parasta strategiaa ja myös pelitilanteiden evaluaattoreista on parhaissa boteissa useita eri versioita. Perusalgoritmit (optimoitu minimax) ovat samanlaisia kaikissa hyvissä boteissa.

## Tietorakenteet ja algoritmit

Botin pelaaminen perustuu alpha-beta -karsivaan minimax-algoritmiin. Minimax-puun pohjan staattisten arvojen laskentaan käytetään evaluaatiofunktiota, joka tulee perustumaan hyväksi havaittuihin Othellon pelistrategioihin, ja todennäköisesti evaluoimaan erilaisia osa-alueita pelilaudasta.

Alpha-beta-karsinnan lisäksi minimaxia pyritään tehostamaan erilaisilla symmetria- ja sääntöpohjaisilla optimoinneilla, ja syventämään puuta tehostusten onnistumisten mukaisesti.

### Tavoitellut aika-ja tilavaativuudet

Aikavaativuuden osalta tämänhetkinen tavoite on *mahdollisimman hyvä*. Peli toteutetaan tiran hengessä 1.0 sekunnin aikarajoituksella siirtoa kohden, joten aikavaativuudeltaan tehokkaampi algoritmi voidaan laajentaa laskemaan enemmän niin, että aikarajoituksesta saadaan maksimaalinen hyöty pelin ennustamisen kannalta.

Minimax-algoritmin aikavaativuus on O(b^m), missä b on mahdollisten siirtojen määrä, ja m on puun syvyys. Tilavaativuus samoilla parametreilla on O(bm). Koska othellossa mahdollisten siirtojen määrä vaihtelee hyvin paljon pelin aikana, minimaxille voidaan odottaa suuria nopeusvaihteluita. Laskennan määrä aikarajoitteeseen nähden tulee olemaan siis hyvin keskeinen ongelma-alue.

Lisäksi kokonaisaikavaativuuteen vaikuttaa alpha-beta-karsinnan tuoma tehostus, ja evaluaatiofunktion vaatima laskenta-aika. Evaluaation arvot tulevat olemaan kovakoodattuja, joten siitä pyritään tekemään minimaalisesti aikaa vievä osa, mahdollisuuksien salliessa vaativuus O(1), maksimissaan O(n).

Kokonaisaikavaativuus on siis minimax - tehostukset + evaluaatio. Tilavaativuuden osalta itse peli ei aseta rajoitteita, eli rajoitteena on vielä tarkemmin määrittelemätön kohtuullisuus.

## Opinto-ohjelma ja dokumentaatiokieli

* Opinto-ohjelma: tietojenkäsittelytieteen kandidaatti (TKT)

* Dokumentaatiokieli: suomi

## Lähteet

* [Minimaxin vaativuusanalyysi](https://cis.temple.edu/~vasilis/Courses/CIS603/Lectures/l7.html)
