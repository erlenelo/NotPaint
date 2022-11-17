# Release 3
Målet med siste sprint var å utvide funksjonaliteten til applikasjonen, samt forbedre brukervennlighet. Vi har også bygget en *REST-API* slik at flere brukere kan tegne på samme prosjekt. I tillegg har vi skrevet flere tester for å bedre dekningsgraden. Punktene under dokumenterer endringene i mer detalj:


## Funksjonalitet
Fra *release 2* har vi utvidet funksjonaliteten med å gi brukeren mulighet til å opprette brukernavn, samt utvidet litt funksjon på selve lerretet. I tillegg har vi bygget en *REST-API*. Brukeren skal blant annet kunne:
opprette brukernavn, samt endre dette.
velge om applikasjonen skal huske brukernavnet til neste gang brukeren åpner applikasjonen.
angre *(undo)* og gjøre om *(redo)* endringer knyttet til tegning på prosjektet. 
tegne sammenhengende på lerretet uten at det blir tydelige forsinkelser på grunn av pennen.
samhandle med andre personer ved å kunne tegne på samme prosjekt. 

Vi har opprettet et nytt brukergrensesnitt.
**UsernameSelectView** gir brukeren mulighet til å opprette et brukernavn. 


I tillegg har *GameSelectView* blitt utvidet men noen funksjoner. Blant annet under selve tegningen skal brukeren kunne angre *(undo)* eller gjøre om *(redo)* endringer fortløpende mens de tegner. Dette utføres med enten utpekte frem- og tilbake piler som er synlig ved siden av lerretet, eller *ctrl z* (undo)  og *y* (redo) ved hjelp av tastaturet. 
Vi har brukt *Bresenham`s linje algoritme* for at brukeren skal kunne tegne mer sammenhengende på lerretet uten at det blir veldig tydelige forsinkelser i penselstrøket. 

## Designvalg
For å gjøre appen mer brukervennlig har vi implementert *CSS* på de ulike komponentene som brukeren interagerer med. Dette gjør vi for å skape en bedre brukeropplevelse og å gjøre det lettere for brukeren å se og forstå hva hen selv gjør. 

Utseendet på appen er funksjonelt, men kunne selvfølgelig vært bedre. Dette er noe vi  valgte å ikke prioritere siden det ikke var et krav. Vi ville heller bruke den tiden vi hadde på å gjøre appen mest mulig funksjonell og prøve å få høyest mulig test dekningsgrad. 

## Modularisering
Applikasjonen har nå totalt tre moduler;
- **core:** Inneholder kode for lagring, lasting, og opprettelse av nye spill.
- **fxui:** Inneholder kode som interagerer med eller er avhengig av *JavaFX*.
- **restserver:** Inneholder *SpringBoot* webserver som implementerer et *REST-api* for lagring og lasting av bilder og spillinformasjon.

Hver modul har sin egen *test-mappe* med tilhørende tester for de ulike klassene. Hver modul har også en egen *pom-fil*, *resources*, samt egne *readme-filer* som forklarer innholdet i hver modul. 

## Tester
Alle modulene har tester til de ulike klassene med en test dekningsgrad på over 70%. Denne dekningsgraden har vi fått fra å kjøre `mvn jacoco:report` i terminalen. 

Vi har gjort et bevisst valg om å ikke lage integrasjonstester. Dette følger av at vi har holdt på med annet innenfor produksjon av applikasjonen opp mot innleveringsfrist forbundet med arbeidskravene til prosjektet. Likevel tenker vi at det ville vært et naturlig steg videre dersom vi hadde hatt mer tid.

Vi har brukt *CheckStyle* og *Spotbugs* for å sjekke kodekvalitet. For *CheckStyle* har vi laget en litt modifisert versjon av *“Google Java Style”* som en konfigurert i *notpaint/custome_checks.xml*. De eneste modifiserte endringene som vi lagde var å endre innrykksnivåene fra 2 til 4 mellomrom. Vi synes dette var en bedre løsning fordi vi synes det er lettere og bedre å lese koden med større innrykksnivå. For *spotbugs* har vi også laget noen unntak fra standardregler. Disse konfigurasjonene ligger i *notpaint/spotbugs-exelclude.xml*. Vi måtte ha noen unntak fra reglene fordi spotbugs ga feilmeldinger som var veldig strenge og unødvendige. Dette så vi på som nødvendig for å kunne få kjørt koden. 

## Diagrammer 
Vi har tre ulike typer **UML** diagrammer; klassediagram, pakkediagram og sekvensdiagram. Disse diagrammene er tegnet ved hjelp av et programmeringsspråk som blir definert av utvidelsen (extension) *PlantUML*. Klassediagrammene blir autogenerert ved bruk av kommandoen `mvn generate-test-sources` i terminalen, også ved hjelp av *PlantUML*.

Pakkediagrammet viser forholdet mellom pakkene, altså arkitekturen til systemet. 

Klassediagrammene viser klassene i hver modul i detalj, og illustrerer hvordan klassene innad i en modul avhenger av hverandre. Det er et klassediagram i hver modul, respektivt *core*, *fxui* og *restserver*. Klassediagrammet til restserver ble først ikke autogenerert riktig, fordi den ble oppfattet som en anonym *Comparator*-klasse for *LockInfo-objektet* som en egen klasse. Dette løste vi ved å implementere *Comparable*-interface på *LockInfo*-klassen istedenfor.

Sekvensdiagrammene viser viktige brukstilfeller, som illustrerer koblingen mellom brukerinteraksjon og hva som skjer inne i systemet inkludert *REST*-kall. 

## REST-api
Selve API-dokumentasjonen finnes i [/docs/restapi](/docs/restapi)

Vi har implementert et *REST-api* med en webserver for å kunne lagre og laste spill og bilder på tvers av enheter. Dette gjør vi ved å bruke *SpringBoot*. Vi har valgt å bruke *SpringBoot* fordi det er en enkel måte å lage en webserver på. 

SpringBoot bruker *Jackson* for å serialisere og deserialisere objekter til og fra JSON. *SpringBoot*-serveren har en `Bean` som konfigurerer *Jackson* til å bruke en `ObjectMapper` fra core-modulen. Dette sørger for at serialiseringen oppfører seg likt i klient og tjener, på tvers av modulene.


## Valg knyttet til arbeid og progresjon under sprint 3
Den formelle koordineringen av arbeidet innad i gruppen foregikk på *GitLab*. I starten av arbeidet med sprint 3 samlet vi oss fysisk for å gå gjennom alle arbeidskrav. Deretter lagde vi *issues* til hvert krav, samt *issues* til implementasjon av funksjonalitet i koden som vi skulle ha med. Hver *issue* hadde *labels* som beskrev hvilken prioritet den hadde samt hvilken del av prosjektet den var relatert til. Det sto også oppført hvem som hadde fått *issuen* tildelt til seg. Dersom noen av *issuene* var relatert til hverandre, sto det under “Linked Items” på hver av de relaterte issuene. 

Dersom hver enkelt av oss lagde en ny *issue*, *assignet* vi den *issuen* til oss selv. Deretter lagde vi en *branch* ut fra *issuen*. Da vi skulle *approve* hverandres *merge requester*, satt vi den personen som skulle *approve* som *Reviewer* og den som laget *requesten* til *Assignee*. Hvem som jobbet med de ulike *issuene* ble bestemt ut fra hva hvert medlem var interessert i å jobbe med, samtidig som vi hadde et mål om at alle skulle få erfaring med flere forskjellige typer oppgaver. Endringer angående hvem som tok ansvaret for hva endret seg litt underveis etter behov og tidshensyn. 

Parprogrammering, ved bruk av *LiveShare* (utvidelse i VScode), ble brukt under deler av kode-utviklingen og skriving av tester. Vi møttes ofte fysisk på skolen for å jobbe sammen, hvor vi konsulterte mye med hverandre og diskuterte for å finne den beste løsningen. Studieassistent var til god hjelp dersom vi hadde spørsmål, eller trengte hjelp til struktureringen av prosjektet, både i selve koden og i arbeidsvaner. 

Underveis i prosjektet fikk vi et problem med *error* meldinger fra *FxRobot* som førte til at *FxRobot* ikke klarte å utføre oppgaven sin. Disse *errorene* var relativt like hver gang, men kom ofte fra ulike funksjoner. Dette var i øyeblikket svært forvirrende. Bildene under viser et eksempel på hvordan en slik *error* kunne se ut. 

![](/notpaint/viewScreenshots/errormessenges/error2.png)
![](/notpaint/viewScreenshots/errormessenges/error.png)
![](/notpaint/viewScreenshots/errormessenges/errormeld.png)


I starten syntes vi at feilmeldingene ikke ga mening og mente at de ikke indikerte i tilstrekkelig detalj hva som kunne være problemet. Det eneste vi klarte å lese fra feilmeldingene var at feilen lå i at *FxRobot* ikke klarte å utføre handlingen den prøvde på, og tenkte dermed at feilen kunne være en ubetydelig *bug* i og med at prosjektet kjørte helt fint i *gitpod*. Heller ikke teknisk hjelp så hva det virkelige problemet kunne være etter gjentatte samtaler. Nærmere innleveringsfrist merket vi at testene ikke lenger kjørte i *GitPod*, og bestemte oss for å igjen se nærmere på *errorene*. Etter hvert fant vi ut at feilen var at funksjonen som teller ned antall sekunder man har på å male på lerretet aldri ble avsluttet etter hver test. Den fortsatte i bakgrunnen selv om *FxRobot* gikk ut av prosjektet og førte dermed til at testene krasjet. Da *FxRobot* prøvde å trykke på en knapp etter at nedtellingen var ferdig, var scene byttet, og dermed fant den ikke knappen. Da vi omsider hadde funnet problemet, lagde vi en metode som stopper nedtellingen i *PaintControllerTest*. Denne blir initiert etter hver test ved bruk av **@AfterEach**.

## Om prosjektet
I tillegg til at vi har brukt mye tid, har prosessen med å lage dette prosjektet til tider vært svært krevende. Dette har ført til at vi valgte å ikke lage integrasjonstester slik vi ble anbefalt. Som nevnt over er begrunnelsen for dette at vi prioriterte å gjøre ferdig prosjektet til fristen, heller enn å lage integrasjonstester, da dette ikke var et krav. Dog ville dette vært et naturlig neste steg for oss dersom vi hadde hatt mer tid. Å jobbe med prosjektet har vært svært lærerikt. Vi har tilegnet oss nye måter å strukturere arbeid på, noe som har ført til at vi har vært mer effektive i arbeidsprosessen. Det har vært gøy å jobbe med ting vi tidligere ikke har hatt erfaring med, spesielt når vi har fått god hjelp gjennom hele semesteret.

## Gitpod-problemer
Rett før innlevering fikk vi følgende feil i Gitpod når vi åpner prosjektet, i "Pulling Image"-steget:

```
Request createWorkspace failed with message: 13 INTERNAL: cannot resolve workspace image: pulling from host registry.gitpod.stud.ntnu.no failed with status code [manifests 6fb63762c1bbfe4bbcdf6abb8e36b618ae00f9b3f2f730009bbe9bf0fbb5bfa2]: 502 Bad Gateway

Unknown Error: { "code": -32603 }
```

Vi snakket med andre grupper, og de hadde samme problem. Derfor er det antageligvis et problem med Gitpod/NTNU-serverer.

Dette førte dessverre til at vi ikke fikk mulighet til å teste siste versjon av prosjektet i Gitpod.