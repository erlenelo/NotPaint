## Persistens
Persistenlaget vårt har to deler. Den første er `GameInfoPersistance`-klassen som handterer lagring og lasting av `GameInfo`-klassen. `GameInfo`-klassen serialiseres til JSON ved bruk av biblioteket [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind). Dette gjøres ved å bruke Jacksons `ObjectMapper`-klasse, og å spesifisere attribuer på felt som skal serialiseres og deserialiseres.

Den andre delen av persistenslaget er lagring og lasting av bilder. Dette gjøres av den abstrakte klassen `ImagePersistence`, som ved release2 har en implementasjon: `LocalImagePersistence`. Denne bruker `ImageIO`-klassen fra java.desktop for å lagre bilder til fil. Lasting av bilder handeres av JavaFXs `Image`-klasse, som har en konstruktør som tillater å laste bilder både fra disk og fra nett.

Sammenheng mellom JSON-filer for `GameInfo` og bilder opprettholdes ved at de har samme filnavn (en UUID), med unntak av filendelsen (.json vs .png).

Programmet vårt bruker implisitt lagring, hvor data lagres i en forhandsbestemt mappe ikke styrt av brukeren, og appen lagrer når det er fornuftig utifra brukerens handlinger.