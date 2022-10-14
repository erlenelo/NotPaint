# Release 2
Under andre sprint har fokuset vært på å videreutvikle funksjonaliteten til prosjektet, modularisering av mappeoppsettet, samt skrive tester for å øke dekningsgraden til modulene tilstrekkelig.

## Funksjonalitet
Fra release1 har vi lagt til muligheten til å legge til diverse spillinstillinger på nye prosjekter (GameInfo-classen, og GameInfoPersistence-classen). Prosjektskaper skal kunne blant annet: 
- legge til antall sekunder man har på hver iterasjon av et prosjekt
- knytte et ord (eller flere for hver iterasjon) til et prosjekt, tatt utifra en liste med flere ord, som skal tegnes
- kunne bla gjennom aktive og ferdige prosjekter

Til dette formålet har vi også laget to nye views:
- GameSelectView gir brukeren mulighet til å velge gamle prosjekt, eller lage nytt prosjekt
- SettingsView håndterer spillinstillingene brukeren kan legge til i et nytt prosjekt

Vi har også lagt til noen ekstrafunksjoner, som muligheten til å endre farge på brushes, og at Clear Canvas-funksjonen nå fjerner egne endringer, og ikke fjerner andre iterasjoners endringer. Det er riktignok noen andre funksjoner som vi hadde planlagt å få implementert denne sprinten (highlighte valgte brushes, undo/redo etc.), men disse har vi nå utsatt til sprint 3 pga. tidshensyn.

## Modularisering
Vi har atskilt alt av Core-logikk fra UI, og laget en mappe for core, og en mappe for ui. Klasser som har vært hybrider mellom fxml og core er blitt refactored med hensyn til sine respektive moduler. Hver modul har også egen test-mappe med tilhørende tester for classene, resources, og egne POM-filer. 

## Tester
Begge moduler (core og fxui) har nå tester til classene, og prosjektet har nå en testdekningsgrad på over 80% i begge moduler. Checkstyle og spotbugs er også blitt implementert.

## Om innleveringen
Vi støtte på den del problemer knyttet til at flere av gruppemedlemene måtte reise siste uka mot innlevering. Dette førte til noen mangler på release2 som ikke ble ferdig helt i tide, samt merge utfordringer med de forskjellige branchene. Læringsassisten ble informert 13/10 om at vi ønsket å levere dagen etter for å rette opp i arbeidet. Vi fikk så beskjed om at det var greit, men at vi også skulle sende en mail til fagstaben. Vi fikk så bekreftelse om at dette var iorden, gitt at vi la til litt tekst om dette i dokumentasjonen. 