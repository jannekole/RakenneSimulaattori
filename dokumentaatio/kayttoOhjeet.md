#Käyttöohjeet

Rakennesimulaattorilla voi simuloida palkeista tehtyjä rakenteita. 

##Ohjelman käyttö

**Open file** -napilla voidaan valita tiedosto josta rakennelma ladataan. Kansiosta "Rakennesimulaattori/valmiitRakenteet/" pitäisi löytyä joitain tiedostoja joita voi kokeilla.

**Reload** -nappi lataa aikaisemmin ladatun tiedoston uudestaan.

**Play/Pause** -nappi käynnistää ja pysäyttää simuloinnin.

**Step forward** -nappi siirtää simulointia annetun askelmäärän eteenpäin. Jos ei ole numero, ohjelma ei tee mitään.


##Rakennelmien tiedostoformaatti

Rakennelmien rakentaminen tehdään kirjoittamalla tekstitiedostoon halutun rakenteen ominaisuudet. 

Esimerkkitiedosto:
```
offset ; xoffset 200; yoffset -125

node	0	;	x	0	;	y	0;	constanty	TRUE	;	constantx	TRUE
node	1	;	x	0	;	y	50;	
node	2	;	x	0	;	y	100
node	3	;	x	20	;	y	25;	constanty	TRUE	;	constantx	TRUE
node	4	;	x	20	;	y	75
node	5	;	x	20	;	y	125

beam	;	a	0	;	b	1	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000
beam	;	a	1	;	b	2	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000
beam	;	a	3	;	b	4	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000
beam	;	a	4	;	b	5	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000


beam	;	a	0	;	b	3	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000
beam	;	a	1	;	b	4	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000
beam	;	a	2	;	b	5	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000
beam	;	a	3	;	b	1	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000
beam	;	a	4	;	b	2	;	length	0	;	stiffness	300000	;	mass	5	;	strength	12000

```


Rakennelman luomiseen käytttävä rivi tulee aloittaa sanalla offset, node, tai beam. Rivien parametrit annetaan kirjoittamalla avainsana, ja whitespacen (välilyönti tai tab) erottama arvo. Parametrien väliin tulee puolipiste.

#####offset
määrittää rakennelman sijainnin ikkunassa. Koordinaatiston origo sijaitsee tavallisesti kymmenen pikseliä ikkunan yäreunasta alas ja vasemmasta reunasta oikealle, mxoffset:n ja yoffset:n avulla sitä voi siirtää haluttuun suuntaan. offset tulee olla ennen muita osia, koska se vaikuttaa vain sen jälkeen tuleviiin rakennelman osiin. Jos offsettia ei määritellä, koordinaatistoa ei siirretä.

#####node
määrittelee pisteen sijainnin koordinaatistossa, johon palkkeja voi kiinnittää. Jokaiselle pisteelle täytyy antaa numero sanan node jälkeen, johon palkit voivat viitata. Kahdella pisteellä ei saa olla samaa numeroa, mutta muuten ne voivat olla mitä tahansa kokonaislukuja, missä tahansa järjestyksessä.

noden muut pakolliset parametrit ovat:
######x:		
Pisteen x -koordinaatti.
######y:		
Pisteen y -koordinaatti.	

Jos haluaa että piste pysyy simulaation aikana paikallaan (esimerkiksi rakennelman maassa kiinni oleva piste), tulee rivissä olla "constanty TRUE; constantx TRUE;". constantx:n ja constanty:n avulla asetetaan pisteen x ja y -koordinaatit vakioiksi. Halutessaan voi käyttää vain toista, jolloin piste pääsee liukumaan yhdellä akselilla.
 
#####beam
määrittelee palkkien ominaisuudet. Parametrit a ja b kertovat mihin pisteisiin palkin halutaan kiinnittyvän. A:n ja b:n arvoksi tulee antaa jonkin noden numero. Nodeen voi viitata vain jos node on jo määritelty ylemmällä rivillä. Jokaisen palkin täytyy kiinnittyä täsmälleen kahteen pisteeseen, mutta yhteen pisteeseen voi kiinnittää minkä tahansa määrän palkkeja.

beamin muut parametrit ovat:

**length:**
Palkin pituus lepotilassa. Asettamalla sen arvoksi 0, ohjelma asettaa palkin pituudeksi siihen liitettyjen pisteiden etäisyyden, eli simulaation lähiessä käyntiin, palkki on lepotilassa.

**stiffness:**
Palkin jäykkyys. Suurempi arvo tarkoittaa jäykempää palkkia. Jäykkyys annetaan jäykkyytenä pituuteen suhteutettuna, joten pidempi palkki puristuu tai venyy enemmän samalla jäykkyyden arvolla ja samalla rasittavalla voimalla. 

**mass:**
Palkin massa. Suurempi massa lisää rakennelmaan kohdistuvaa rasitusta.

**strength:**  Palkin maksimikuorma.

Jos tiedostossa on virhe, esimerkiksi jos massa on nolla tai beam viittaa nodeen jota ei ole olemassa, ohjelma ilmoittaa virheen tyypistä ja millä rivillä virhe on. Tätä voi kokeilla esimerkiksi tiedostolla test_error.txt






