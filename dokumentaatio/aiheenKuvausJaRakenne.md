**Aihe:** 

Touteutetaan ohjelma jossa voi rakentaa palkeista erilaista rakenteita ja sitten simuloida niiden käyttäytymistä. Palkkien rakentaminen tapahtuu tiedostoon kirjoittamalla. Palkeilla on ominaisuuksia, kuten joustavuus, kestävyys ja massa. Tarkoituksena on pyrkiä mahdollisimman lähelle todellista palkkien käyttäytymistä, mutta täyttä realistisuutta ei kuitenkaan vaadita, sillä ohjelma on tarkoitettu lähinnä leikkimiseen. 

Kun rakenne on valmis, käyttäjä voi aloittaa simuloinnin, jolloin ohjelma alkaa laskea palkkien tilaa seuraavalla ajanhetkellä nykyisen tilan perusteella. Palkkien tila päivitetään näytölle, ja sitten lasketaan palkkien tilat seuraavalle ajanhetkelle, ja niin edelleen. Jos palkin kestävyys ylittyy, se katkeaa.

**Käyttäjät:** 

Ihmiset jotka haluavat leikkiä virtuaalisilla rakennelmilla

**Käyttäjän toiminnot:**

* Rakennelman lataaminen tiedostosta
* Simuloinnin käynnistäminen ja katsominen
 
**Rakenteen kuvaus:**

Kun käyttäjä valitsee tiedoston, Gui lähettää tiedoston polun Builderille, joka rakentaa Space olioon palkkeja ja niitä yhdistäviä pisteitä kuvaavia Beam ja Node olioita. Jokaiseen Beamiin lisätään kaksi Nodea, 

Kun simulointi on käynnissä, AnimationFrame pyytää että Space menee eteenpäin sopivan määrän askelia jokaista piirtoa varten. Jokaisessa askeleessa Space ensin päivittää jokaisen Beamin tuottaman voiman määrän, ja sitten päivitetään jokaisen Noden sijainti ja nopeus. Node päivitetään laskemalla siihen kohdistuvien voimien summa, sekä siinä kiinni olevien Beamien massojen summa. Yhteenlaskettujen voimien ja massojen avulla saadaan Noden kiihtyvyys, jonka avulla saadaan nopeus, jonka avulla puolestaan sijainti.

Kun Space on siirtynyt askelten määrän eteenpäin, piirtää AnimationFrame jokaisen komponentin komponentteihin liitettyjen GraphicNoden ja GraphicBeamin avulla.

Monien muuttujien laskemiseen ja säilömiseen käytetään Vector luokan vektorilaskuja.


**Luokkakaavio:**

![](/dokumentaatio/luokkakaavio.png)

**Sekvenssikaavioita:**

![](/dokumentaatio/Tiedoston lataus.png)

![](/dokumentaatio/Simulointi.png)
