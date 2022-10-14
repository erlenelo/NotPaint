# NotPaint
Dette prosjektet er en tegneapp hvor flere brukere sammen jobber på felles tegneprosjekter, der hver endring gjort av bruker resulterer i en ny iterasjon av samme tegning. Prosjektene tegnes med bruk av pensler i forskjellig størrelse, samt muligheter for å viske vekk endringer, og fjerne alle endringer for nåværende session. Prosjektene kan settes opp med forskjellige instillinger som begrenser brukernes muligheter til å interagere med prosjektet. Et nytt prosjekt kan settes opp med:
- Tid man har på å utføre sine tegne-endringer
- Antall iterasjoner for hvert prosjekt
- At et randomisert ord knyttes til prosjektet, og skal tegnes (eller om nytt ord genereres for hver nye iterasjon)

Prosjekter som er ferdige (enten markert ferdig manuelt, eller at max antall iterasjoner er nådd), legges i en egen fane for ferdige prosjekter. Det er også en fane for nåværende, aktive prosjekter som man kan lage nye iterasjoner av, som vil overskrive det forrige (dog tidligere iterasjoners endringer kan ikke resettes).

## Bilder av grensesnittet
### PaintView
![](/notpaint/viewScreenshots/release2%20viewScreenshots/PaintView.png)
Dette er grensesnittet til tegne-funksjonene.
- Sirklene setter pensel til indikert radius
- Firkantene setter pensel til indikert størrelse
- Viskelæret setter pensel til hvit farge, slik at tegnede områder kan viskes ut
- Blyanten setter pensel til farge
- Color-pickeren lar bruker velge farge på pensel
- Reset-canvas-knappen fjerner alle endringer gjort i nåværende iterasjon. I praksis laster den opp forrige iterasjon, som overwriter nåværende iterasjon med foreløpige endringer

### GameSelectView
![](/notpaint/viewScreenshots/release2%20viewScreenshots/GameSelectView.png)
Dette er grensesnittet for nye/gamle prosjekter.
- Det er to faner; Active projects viser uferdige prosjekter og Completed Prosjects viser alle ferdige prosjekter
- Seconds to Draw viser hvor mange sekunder prosjektet tillater at en bruker gjør endringer
- Iterations viser hvor mange ganger en endring på prosjektet er blitt lagret
- Last Editor viser brukeren som endret prosjektet sist
- Last Edit viser tiden siste iterasjon ble lagret
- Join Project engasjerer et selektert, aktivt prosjekt, slik at man kan lage en ny iterasjon av prosjektet. Sender bruker til PaintView for prosjektet
- New Project lager et nytt prosjekt. Sender bruker til SettingsView


### SettingsView
![](/notpaint/viewScreenshots/release2%20viewScreenshots/SettingsView.png)
Dette er grensesnittet til instillinger ved laging av nytt prosjekt.
- Back to Menu tar bruker tilbake til GameSelectView
- Time-inputten lager restriksjon på hvor mange sekunder man kan tegne per iterasjon på det nye prosjektet
- Rounds-inputten designerer maks antall iterasjoner som prosjektet kan inneha. Når dette antallet er nådd, markeres prosjektet som ferdig, og kan hentes under "Completed projects" i GameSelectView.
- Radio-buttonsene indikerer om prosjektet skal ha et nytt randomisert ord som skal tegnes for hver iterasjon, eller om samme ord skal beholdes gjennom hele prosjektet.
- Create-knappen skaper selve prosjektet. Da blir det et active project, som kan interageres med i GameSelectView.

## Brukerhistorie (US-1)

Som bruker ønsker jeg å kunne tegne på et lerret og lagre tegningen lokalt.

Brukeren skal kunne tegne med forskjellige farger, samt størrelse og form på pensel. Det som har blitt tegnet skal også kunne viskes ut med et innebygd viskelær. Til slutt, når tegningen er ferdigstilt, skal bruker kunne lagre den, slik at de senere kan fortsette der de slapp og tegne videre.

Viktig å kunne se:
- Et lerret
- Valg av størrelse på pensel
- Valg av form på pensel
- Valg av farge
- Viskelær

Viktig å kunne gjøre:
- Velge pensel og male på lerretet med valgt farge
- Kunne viske ut det man har malt
- Kunne lagre tegningen til senere bruk

## Brukerhistorie US2-2:
Som bruker vil jeg kunne bli med i et spill og tegne på et lerret.

Brukeren skal kunne bli med i en tegning som andre personer har tegnet på. Brukeren skal motta et ord med en angitt tid og deretter kunne tegne på et lerret.

Viktig å kunne se: 
- en knapp som tilsier at du blir med i et lerret.
- se hva slags ord du skal tegne
- en tid som teller ned som viser hvor lang tid man har før tiden går ut
- en ferdig knapp man trykker på hvis man er ferdig før tiden er ute
- hvor mange som har tegnet før deg, samt hvor mange som skal få lov til å tegne på lerretet
- skal se farger man kan bruke, ulik størrelse og form på pensler, samt viskelær på den en selv har tegnet

Viktig å kunne gjøre:
- bli med i en tegning
- kunne tegne med ulike farger og ulik form og størrelse på pensler. 
- Bruke viskelær på det en selv har tegnet. 

