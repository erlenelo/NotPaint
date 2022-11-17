## ImagePersistence
Persistenslaget er delt opp i to deler [Persistence](/notpaint/core/src/main/java/notpaint/persistence/readme.md) og ImagePersistence, denne delen tar for seg ImagePersistence.

*ImagePersistence* hånderer lagring og lasting av bilder. 

*LocalImagePersistence*-klassen er en implementasjon av *ImagePersistence*. Denne bruker `ImageIO`-klassen fra `java.desktop` for å lagre bilder til fil. 

*RemoteImagePersistence*-klassen er en annen implementasjon av *ImagePersistence* og håndterer lagring til en webserver. 

Lasting av bilder håndteres av `JavaFXs` *Image*-klasse, som har en konstruktør som tillater å laste bilder både fra disk og fra nett. 