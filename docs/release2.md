# Release 2
Målet med andre sprint var å legge til flerspillerfunksjonalitet til prosjektet, samt gjøre det mulig å lage prosjekter med gitte betingelser for brukerinteraksjon og lagring. Vi har også modularisert strukturen på prosektet og laget tester for modulene. Punktene under dokumenterer endringer litt mer i detalj:

## Funksjonalitet
Fra release1 har vi lagt til muligheten til å legge til diverse spillinstillinger på nye prosjekter (GameInfo-classen, og GameInfoPersistence-classen). Prosjektskaper skal kunne blant annet: 
- legge til antall sekunder man har på hver iterasjon av et prosjekt
- knytte et ord (eller flere for hver iterasjon) til et prosjekt, tatt utifra en liste med flere ord, som skal tegnes
- kunne bla gjennom og hente aktive og ferdige prosjekter

Til dette formålet har vi også laget to nye views:
- GameSelectView gir brukeren mulighet til å velge gamle prosjekt, eller lage nytt prosjekt
- SettingsView håndterer spillinstillingene brukeren kan legge til i et nytt prosjekt

Vi har også lagt til noen ekstrafunksjoner, som muligheten til å endre farge på brushes, og at Clear Canvas-funksjonen nå fjerner egne endringer, og ikke fjerner andre iterasjoners endringer. Det er riktignok noen andre funksjoner som vi hadde planlagt å få implementert denne sprinten (highlighte valgte brushes, undo/redo etc.), men disse har vi nå utsatt til sprint 3 pga. tidshensyn.

## Modularisering
Vi har atskilt alt av Core-logikk fra UI, og laget en mappe for core, og en mappe for ui. Klasser som har vært hybrider mellom fxml og core er blitt refactored med hensyn til sine respektive moduler. Hver modul har også egen test-mappe med tilhørende tester for classene, resources, og egne POM-filer. 

## Tester
Begge moduler (core og fxui) har nå tester til classene, og prosjektet har nå en testdekningsgrad på over 80% i begge moduler. Checkstyle og spotbugs er også blitt implementert.

## Om valg knyttet til arbeid og progresjon under sprint 2
Den formelle koordineringen av arbeidet mellom gruppen foregår på GitLab. Vi har først samlet oss fysiskt for å gå gjennom alt av krav, og utvikle issues til sprint 2. Deretter har vi delt opp ansvar for hver issue ut ifra hva hvert medlem har vært interessert i å jobbe med, samt med et mål om at alle skal erfaring med flere forskjellige oppgaver. Endringer i hvem som har hatt ansvaret for hva har endret seg litt underveis, etter behov og tidshensyn.

Parprogrammering har blitt utført under flere deler av kode-utviklingen, slik at alle har fått tatt del i programmeringen. Hovedsaklig har vi tatt ibruk liveshare på VScode på de fleste av parprogrammerings-sessionene. Vi har også hatt faste dager hvor vi har møtt fysisk for å jobbe sammen.

Utvikling av tester ble gjort etter etter at både modulariseringen var fullført, og de fleste klassene for sprint 2 var programmert. Dette fordi det var lite hensiktsmessig å lage tester for klasser som ikke var ferdige ennå. Checkstyle og Spotbugs har også blitt brukt for forbedret kodekvalitet.

## Om innleveringen
Vi støtte på den del problemer knyttet til at flere av gruppemedlemene måtte reise siste uka mot innlevering. Dette førte til noen mangler på release2 som ikke ble ferdig helt i tide, samt merge utfordringer med de forskjellige branchene. Læringsassisten ble informert 13/10 om at vi ønsket å levere dagen etter for å rette opp i arbeidet. Vi fikk så beskjed om at det var greit, men at vi også skulle sende en mail til fagstaben. Vi fikk så bekreftelse om at dette var iorden, gitt at vi la til litt tekst om dette i dokumentasjonen. 