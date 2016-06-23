#K‰yttˆohjeet

Rakennesimulaattorilla voi simuloida palkeista tehtyj‰ rakenteita. 

##Ohjelman k‰yttˆ

**Open file** -napilla voidaan valita tiedosto josta rakennelma ladataan.

**Reload** -nappi lataa aikaisemmin ladatun tiedoston uudestaan.

**Play/Pause** -nappi k‰ynnist‰‰ ja pys‰ytt‰‰ simuloinnin.

**Step forward** -nappi siirt‰‰ simulointia annetun askelm‰‰r‰n eteenp‰in.


##Rakennelmien tiedostoformaatti

Rakennelmien rakentaminen tehd‰‰n kirjoittamalla tekstitiedostoon halutun rakenteen ominaisuudet. 

Esimerkkitiedosto:

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




Rakennelman luomiseen k‰yttt‰v‰ rivi tulee aloittaa sanalla offset, node, tai beam. Rivien parametrit annetaan kirjoittamalla avainsana, ja whitespacen (v‰lilyˆnti tai tab) erottama arvo. Parametrien v‰liin tulee puolipiste.
**offset**
m‰‰ritt‰‰ rakennelman sijainnin ikkunassa. Koordinaatiston origo sijaitsee tavallisesti kymmenen pikseli‰ ikkunan y‰reunasta alas ja vasemmasta reunasta oikealle, mxoffset:n ja yoffset:n avulla sit‰ voi siirt‰‰ haluttuun suuntaan. offset tulee olla ennen muita osia, koska se vaikuttaa vain sen j‰lkeen tuleviiin rakennelman osiin. Jos offsettia ei m‰‰ritell‰, koordinaatistoa ei siirret‰.

**node**
m‰‰rittelee pisteen sijainnin koordinaatistossa, johon palkkeja voi kiinnitt‰‰. Jokaiselle pisteelle t‰ytyy antaa numero sanan node j‰lkeen, johon palkit voivat viitata. Kahdella pisteell‰ ei saa olla samaa numeroa, mutta muuten ne voivat olla mit‰ tahansa kokonaislukuja, miss‰ tahansa j‰rjestyksess‰.

noden muut pakolliset parametrit ovat:
x:		Pisteen x -koordinaatti.
y:		Pisteen y -koordinaatti.	

Jos haluaa ett‰ piste pysyy simulaation aikana paikallaan (esimerkiksi rakennelman maassa kiinni oleva piste), tulee riviss‰ olla "constanty TRUE; constantx TRUE;". constantx:n ja constanty:n avulla asetetaan pisteen x ja y -koordinaatit vakioiksi. Halutessaan voi k‰ytt‰‰ vain toista, jolloin piste p‰‰see liukumaan yhdell‰ akselilla.
 
**beam**
m‰‰rittelee palkkien ominaisuudet. Parametrit a ja b kertovat mihin pisteisiin palkin halutaan kiinnittyv‰n. A:n ja b:n arvoksi tulee antaa jonkin noden numero. Nodeen voi viitata vain jos node on jo m‰‰ritelty ylemm‰ll‰ rivill‰. Jokaisen palkin t‰ytyy kiinnitty‰ t‰sm‰lleen kahteen pisteeseen, mutta yhteen pisteeseen voi kiinnitt‰‰ mink‰ tahansa m‰‰r‰n palkkeja.

beamin muut parametrit ovat:

**length:** 	Palkin pituus lepotilassa. Asettamalla sen arvoksi 0, ohjelma asettaa palkin pituudeksi siihen liitettyjen pisteiden et‰isyyden, eli simulaation l‰hiess‰ k‰yntiin, palkki on lepotilassa.

**stiffness:**	Palkin j‰ykkyys. Suurempi arvo tarkoittaa j‰ykemp‰‰ palkkia. J‰ykkyys annetaan j‰ykkyyten‰ pituuteen suhteutettuna, joten pidempi palkki puristuu tai venyy enemm‰n samalla j‰ykkyyden arvolla ja samalla rasittavalla voimalla. 

**mass:**		Palkin massa. Suurempi massa lis‰‰ rakennelmaan kohdistuvaa rasitusta.

**strength:** 	Palkin maksimikuorma.






