[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2213/gr2213)

# Gruppe gr2213 repo 

Dette er prosjektet til gruppe 13 i emnet IT1901 høsten 2022. Målet er å lage en trelagsapplikasjon med et domenelag, brukergrensenitt og persistenslag. Bygget skal være rigget til å rapportere testdekningsgrad (vha. **JaCoCo**). Prosjektet skal være konfigurert med **Maven** og ha støtte for direkte tilgang til **Gitpod**.


## Not Paint

Formålet med applikajsonen er å lage en canvas hvor man kan tegne. I applikasjonen skal man tegne og lagre opp til en sky, og laste dem ned igjen for så å kunne fortsette med å tegne. 


### Bygging og kjøring
**Bygging:** Kjør `mvn install` fra kodings-prosjektet (**not-paint**-mappa).

**Kjøring:** Åpne ny terminal, kjør først `cd notpaint` og deretter `mvn javafx:run`. Dette vil da åpne applikajsonen.

**Testing:** For å teste prosjektet kjør kommandoen `mvn test`. For å sjekke testdekningsgraden kjør kommandoen `mvn jacoco:report`. Dette vil generer en rapport for forrige test i *target/site/jacoco/index.html*. 


## Applikasjonen 
Inne i **notpaint**-mappen inneholder koden til prosjektet. Brukergrensenittet er laget med **JavaFX** og **FXML**.
Her finner man også to undermapper som tar for seg to forskjellige logikker med ulike klasser innen for hver mappe; *Brushes* og *PaintTools*. Her vil man også finne hovedklassen som er *PaintSettings*.

- I *Brushes*-mappen finner man tre klasser; Brush, CircleBrush og SquareBrush. I disse klassene er det funksjoner for hva slags størrelse og form man vil ha på "penselen" når man skal tegne på lerretet. 
 
- I *PaintTools*-mappen finner man tre klasser; EraserTool, PenTool og Tool. I disse klassene er det funksjoner for å faktisk tegne på lerretet i tillegg til å kunne fjerne det man har tegnet. 






