[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2213/gr2213)

# Gruppe gr2213 repo 

Dette er prosjektet til gruppe 13 i emnet IT1901 høsten 2022. Målet er å lage en trelagsapplikasjon med et domenelag, brukergrensenitt og persistenslag. Prosjektet skal være rigget til å rapportere testdekningsgrad (vha. **JaCoCo**). Prosjektet skal være konfigurert med **Maven** og ha støtte for **Gitpod**.


## Not Paint
Formålet med applikajsonen er å kunne samarbeide om å lage tegninger, hvor hver person som deltar iterativt legger til noe nytt i tegningen.


## Bygging, kjøring og shipping
**Bygging:** Kjør `mvn install` fra kodings-prosjektet (**notpaint**-mappa) for å 
bygge alle moduler i prosjektet. Dette vil også kjøre tester og analysere kodekvalitet (se kodekvalitet).
### Kjøring
**Java FXUI-klient:** Kjør `mvn javafx:run` i mappa **notpaint/fxui**. 

**Spring-boot webserver:** Kjør `mvn spring-boot:run` i mappa **notpaint/restserver**.

core-modulen må bygges før både klient og server kan kjøres.

### Shipping
For å lage en distibuerbar versjon av klienten, må modulene først bygges (se "Bygging").

Kjør `mvn javafx:jlink` i mappa **notpaint/fxui**. Dette vil bygge en distribuerbar versjon av klienten i mappa **notpaint/fxui/target/notpaint**.

Etter å ha kjørt jlink, kan du lage en installasjonsveiviser for klienten ved å kjøre `mvn jpackage:jpackage` i mappa **notpaint/fxui**. Dette vil bygge en installasjonsveiviser for klienten i mappa **notpaint/fxui/target/dist**.

**NB**: For å lage en installasjonsveiviser må du ha [WiX Toolset](https://wixtoolset.org/) installert og konfigurert i PATH.


## Applikasjonen 
**notpaint**-mappen inneholder koden til prosjektet. Brukergrensenittet er laget med **JavaFX** og **FXML**. Webserveren som implementerer et REST-API er laget med Spring Boot.

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
