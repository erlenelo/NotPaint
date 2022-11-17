[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2213/gr2213)

# Gruppe gr2213 repo 

Dette er prosjektet til gruppe 13 i emnet IT1901 høsten 2022. Målet er å lage en trelagsapplikasjon med et domenelag, brukergrensenitt og persistenslag. Prosjektet skal være rigget til å rapportere testdekningsgrad (vha. **JaCoCo**). Prosjektet skal være konfigurert med **Maven** og ha støtte for **Gitpod**.


## Not Paint
Formålet med applikajsonen er å kunne samarbeide om å lage tegninger, hvor hver person som deltar iterativt legger til noe nytt i tegningen.


## Bygging og kjøring
**Bygging:** Kjør `mvn install` fra kodings-prosjektet (**notpaint**-mappen) for å 
bygge alle moduler i prosjektet. Dette vil også kjøre tester og analysere kodekvalitet (se kodekvalitet).
### Kjøring
**Java FXUI-klient:** Kjør `mvn javafx:run` i mappa **notpaint/fxui**. 

**Spring-boot webserver:** Kjør `mvn spring-boot:run` i mappa **notpaint/restserver**.

core-modulen må bygges før både klient og server kan kjøres.

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
Prosjektet består av 3 moduler:
* `core`: Inneholder kode for lagring, lasting, og opprettelse av nye spill.
* `fxui`: Inneholder kode som interagerer med eller er avhengig av JavaFX. 
* `restserver`: Inneholder SpringBoot webserver som implementerer et REST-api for lagring og lasting av bilder og spillinformasjon. 

## Diagrammer 
Vi har tre ulike typer UML diagrammer; klassediagram, pakkediagram og sekvensdiagram. Disse diagrammene er tegnet ved hjelp av et programmeringsspråk som blir definert av utvidelsen (extension) PlantUML.


### Klassediagram

Vi bruker [PlantUML Generator](https://mvnrepository.com/artifact/de.elnarion.maven/plantuml-generator-maven-plugin) for å generere klassediagram.

For å generere eller oppdatere de eksisterende klassediagrammene, kjør:
`mvn generate-test-sources`

Dette vil generere PlantUML-diagram i hver modul sin `target/generated-docs` mappe.

Klassediagram for hver modul ligger i hver modul sin readme.md fil.

### Pakkediagram
Pakkediagrammet viser forholdet mellom pakkene, altså arkitekturen til systemet. 

![](https://www.plantuml.com/plantuml/png/VL5RZi8m3FptAQmS84TOSG_4H-YwRDZceTX1bLRSVPkq4421_Dap7eydEnQJ9Bi9VP1eh1T078ABmPCNL4D8fF0FS7dH3B_cHAXkL5tH5NmYbBu-fim_nAh2rpSAaH9R5l83ARW2jDNZdEqgtP_CLY5-mArRwbTvU3Wj4GjJkb0g1_Oa6gCEtmbdSp7ZV8jbFyvkEZoRLnoJzQSxPItrCGIfpDxzgFKNxi_B4Wx-0D2sLsILUErkT80IrzOfvbfTt07qOcbRUtRQo2Rq-Aa0dP3UIy6E_7Ts4_m3)

### Sekvensdiagram
 Sekvensdiagrammene tar for seg to viktige brukstilfeller, som viser koblingen mellom brukerinteraksjon og hva som skjer inni systemet med REST-kall.

![](https://www.plantuml.com/plantuml/png/hLF9Rk904BttLvGq5sDfPku-ZD249ImH3ai4IdBes1NJeUactKKG-VguJ10MjaWEkRJAhlTgUK_Lz2GTRTTQo8IiWu5q2dx5ogo4AEBV_w0plnb6SALLil9W3QYrpB0-sxVAGEpi2oOaECE8n1foLcjq4IobIJKox1CLDCGfp-WDEgyyeKam0dBxeKrMNRd6ddcsmJ40bJyDKS8orQ6d2C9mZA_RF0n3U30GZoPJ-AlpAlnrEskF5p2RzLg2qPM6JFORQ_o1V3sTnZ0QSFX_r5e0kIsUToslrvTlyW0gBtLIALy_G_096WIo44jbw4xXhbOhPHGfgTKxLaGgmwmvgXAV1XjQcM8Qr6GWg_wxw466r6F7OqdBe21NjJyRYQ6NIYgkL-h_H-VZ1ipwdgVTTcvfKUG7F7tQ4zu_5ZDzwMrd3eKMPyiTt4sTt76MxJhwoLSI5IzuH5zxEnC-GL_GbSb44qsQ7-G7)

Dette sekvensdiagrammet beskriver prosessen som skjer når en bruker vil  tegne på et aktivt prosjekt.


![](https://www.plantuml.com/plantuml/png/dP91SzCm48Nl_XKxvU9aQ7ltWIcj3TEPG4ERK3h0GSYlZgWY5MaT0x-Uo1CdKMCyq8j7qdvwUhj-Pvw5utgf4o7PEhhIWbwSwnf94fR7h-ZDftUJZ2wqaW-UtgEXgRFV8Ja9XG18QzXPhU4oMWXJQgof3PIEabsPZdToQx02UGa3OaitO5QcyXyLcbvXKaQn4bhzHZhgYE0sevk5RVnEglPmiVTRfnYULaBNyBrjNJW81hqLI_I-BjjwAAUl9IjhYXKC4y8txZ2-Pz2qefVctgPFmKSuhpp3I6JamK5vXItUBKX4v_yuqAbGXlyspMYO0_QjwLwTxhnEh2XHfZqLgWxF1fkk3feU9Nl0KpiTDIYvOKugyArrvR3YKMwheqYkcwS7p_2JqvRe3Tow46SfTw9HfkeDM6vDVxBkbL5-iRaw716r51NsHdlYnGffU_gI7WKl3bfry9pHU7oD7pKyZyVqnT1qFgFJzbROVPWNrtSqdr_cONT-bT_Hjr-Cpr-fHGOZA_P-N1pI_t2ohxAr4AiDcN3G47wBJgnyU9QNl9WKiu9EzLeWdajo1bFMI_q7)

Dette sekvensdiagrammet beskriver prosessen når en bruker vil opprette et nytt prosjekt. 


