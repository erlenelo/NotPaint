[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2213/gr2213)

# Gruppe gr2213 repo 

Dette er prosjektet til gruppe 13 i emnet IT1901 høsten 2022. Målet er å lage en trelagsapplikasjon med et domenelag, brukergrensenitt og persistenslag. Prosjektet skal være rigget til å rapportere testdekningsgrad (vha. **JaCoCo**). Prosjektet skal være konfigurert med **Maven** og ha støtte for **Gitpod**.


## Not Paint
Formålet med applikajsonen er å kunne samarbeide om å lage tegninger, hvor hver person som deltar iterativt legger til noe nytt i tegningen.


## Bygging og kjøring
**Bygging:** Kjør `mvn install` fra kodings-prosjektet (**notpaint**-mappa) for å 
bygge alle moduler i prosjektet. Dette vil også kjøre tester og analysere kodekvalitet (se kodekvalitet).

**Kjøring:** Kjør `mvn javafx:run` i mappa **notpaint/fxui**. Alle moduler fxui modulen avhenger av må bygges først.

## Applikasjonen 
**notpaint**-mappen inneholder koden til prosjektet. Brukergrensenittet er laget med **JavaFX** og **FXML**.

## Testing og kodekvalitet

### Testing
 For å teste prosjektet kjør kommandoen `mvn test`. For å sjekke testdekningsgraden kjør kommandoen `mvn jacoco:report`. Dette vil generere en rapport for forrige test i **target/site/jacoco/index.html** for hver modul.
For å få se rapport for alle modulene, åpne **notpaint/codecoverage.html**

### Kodekvalitet
 Vi bruker tre verktøy for å opprettholde kodekvaliteten:
 * **checkstyle**: Gir varlser om stilmessige uregelmessigheter som ikke følger de
 gitte reglene for kodestil. Disse innstillingene er konfigurert i `notpaint/custom_checks.xml` som er en litt modifisert versjon av "Google Java Style"
 * **spotbugs**: Utfører statisk analyse av den kompilerte koden for å se etter vanlige feil. Konfigurasjon for unntak av disse reglene gjøres i `notpaint/spotbugs-exclude.xml`
 * **JaCoCo**: Verktøy som analyserer testdekningsgraden etter testing.

 For å sjekke kodekvaliteten kjører man kommandoen `mvn verify`. 


## Modularisering
Prosjektet består av 2 moduler:
* `core`: Inneholder kode for lagring, lasting, og opprettelse av nye spill.
* `fxui`: Inneholder kode som interagerer med eller er avhengig av JavaFX. 


## Klassediagram

Vi bruker [PlantUML Generator](https://mvnrepository.com/artifact/de.elnarion.maven/plantuml-generator-maven-plugin) for å generere klassediagram.

For å generere eller oppdatere de eksisterende klassediagrammene, kjør:
`mvn generate-test-sources`

Dette vil generere PlantUML-diagram i hver modul sin `target/generated-docs` mappe.

Klassediagram for hver modul ligger i hver modul sin readme.md fil.


## Persistens
Persistenlaget vårt har to deler. Den første er `GameInfoPersistance`-klassen som handterer lagring og lasting av `GameInfo`-klassen. `GameInfo`-klassen serialiseres til JSON ved bruk av biblioteket [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind). Dette gjøres ved å bruke Jacksons `ObjectMapper`-klasse, og å spesifisere attribuer på felt som skal serialiseres og deserialiseres.

Den andre delen av persistenslaget er lagring og lasting av bilder. Dette gjøres av den abstrakte klassen `ImagePersistence`, som ved release2 har en implementasjon: `LocalImagePersistence`. Denne bruker `ImageIO`-klassen fra java.desktop for å lagre bilder til fil. Lasting av bilder handeres av JavaFXs `Image`-klasse, som har en konstruktør som tillater å laste bilder både fra disk og fra nett.

Sammenheng mellom JSON-filer for `GameInfo` og bilder opprettholdes ved at de har samme filnavn (en UUID), med unntak av filendelsen (.json vs .png).

Programmet vårt bruker implisitt lagring, hvor data lagres i en forhandsbestemt mappe ikke styrt av brukeren, og appen lagrer når det er fornuftig utifra brukerens handlinger.