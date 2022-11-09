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


## Brukerhistorier
Brukerhistoriene blir laget til hver sprint i prosjektet. Dette innebærer at US1 tilhører første sprint, US2 tilhører andre sprint og US3 tilhører tredje sprint. 

### Brukerhistorie  (US1-1)

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


### Brukerhistorie US2-1:
Som bruker ønsker jeg å kunne opprette et nytt lerret som andre også kan tegne på.

Brukeren skal kunne opprette et nytt lerret og velge hvor lang tid (opptil 2 min) man har for å tegne en tegning. Brukeren skal også kunne velge om hver person som tegner på lerretet skal tegne samme randomisert ord eller om alle skal få forskjellige randomiserte ord. Samt skal brukeren velge hvor mange personer som skal tegne på lerretet før tegningen er ferdig.

Viktig å kunne se:
- EN tilbakeknapp øverst i venstre hjørne
- En knapp som indikerer oprettelsen av et nytt lerret
- Tydelig hvor lang tid man angir at en bruker skal kunne tegne på lerretet
- Et tekstfelt hvor man angir hvorvidt brukerne skal tegne basert på samme- eller forskjellig ord
- Et tekstfelt hvor bruker kan angi antall iterasjoner prosjektet kan gjennomgå (antall personer som kan redigere det totalt)

Viktig å kunne gjøre:
- Lage et nytt lerret
- Sette en gitt tid, velge hvordan man skal tegne et ord og hvor mange som skal tegne på lerretet
- Avbryte hvis man ikke vil lage nytt prosjekt likevel


### Brukerhistorie US2-2:
Som bruker vil jeg kunne bli med i et spill og tegne på et lerret.

Brukeren skal kunne bli med i en tegning som andre personer har tegnet på. Brukeren skal motta et ord med en angitt tid og deretter kunne tegne på et lerret.

Viktig å kunne se: 
- En knapp som tilsier at du blir med i et lerret
- Hva slags ord du skal tegne
- En tid som teller ned som viser hvor lang tid man har før tiden går ut
- En ferdig knapp man trykker på dersom man blir ferdig før tiden er ute
- Hvor mange som har tegnet før deg, samt hvor mange som skal få lov til å tegne på lerretet
- Skal se farger man kan bruke, ulik størrelse og form på pensler, samt viskelær på den en selv har tegnet

Viktig å kunne gjøre:
- Bli med i en tegning
- Kunne tegne med ulike farger og ulik form og størrelse på pensler
- Bruke viskelær på det en selv har tegnet




## Brukerhistorie (US3-1)

Som bruker ønsker jeg å ha en komfortabel tegneopplevelse, uten forsinkelser i tegningen, samt å fortløpende kunne angre og gjøre om endringer jeg gjør mens jeg tegner.

Brukeren skal kunne, etter valg av innstillinger, tegne på lerretet med ønskede funksjoner og såpass lite forsinkelse i bevegelse av penselen at det ikke forstyrrer utseende til det som ble tegnet. Under selve tegningen skal brukeren nå kunne angre (undo) eller gjøre om (redo) endringer som brukeren gjør fortløpende mens de tegner. Dette skal kunne utføres med både utpekte frem- og tilbake knapper som er synlig ved siden av lerretet, samt control-z for undo og control-y for redo på tastaturet.


Viktig å kunne se:
- knapper (i form av piler) for undo og redo
- et kontinuerlig og jevnt penselstrøk på lerrettet

Viktig å kunne gjøre:
- Kunne tegne uten at forsinkelsen på børsten påvirker utseendet til penselstrøket
- Kunne angre (undo) og gjøre om (redo) endringer man gjør fortløpende under tegningen

## Brukerhistorie US3-2:

Som bruker ønsker jeg å kunne se hvem som redigerte både mine og andres prosjekter sist.

Bruker skal kunne opprette et kallenavn for sin bruker. Dette brukernavnet skal bli brukt til å fremheve hvem som opprettet, eller sist redigerte, hvert av prosjektene som ligger i oversiktssiden. Brukernavnet skal velges i et vindu som vises når bruker åpner applikasjonen. Bruker skal også ha mulighet til å enten bli, eller ikke bli husket til neste gang de åpner appen; d.v.s. at bruker skal kunne trykke på en avmerkingsboks og beholde brukernavnet til neste gang, eller ikke, alt etter hva bruker ønsker.


Viktig å kunne se: 
- På vinduet for opprettelse av brukernavn:
  - En tekstfelt hvor bruker kan skrive inn ønsket brukernavn
  - En knapp for å bekrefte og opprette ønsket brukernavn
- På oversiktssiden som kommer opp etter valg av brukernavn:
  - en oversikt over alle pågående prosjekter og hvem som redigerte prosjektet sist
  

Viktig å kunne gjøre:
- På vinduet for opprettelse av brukernavn:
  - Kunne velge tekstfeltet og skrive inn ønsket brukernavn
  - Kunne trykke på knappen som setter valgt brukernavn - videre er det viktig at å trykke på denne knappen fører brukeren til oversiktssiden
- På oversiktssiden som kommer opp etter valg av brukernavn:
  - Kunne trykke på vilkårlig prosjekt og få opp informasjon om hvem som redigerte prosjektet sist
