## Persistence
Persistenslaget er delt opp i to deler persistence og [ImagePersistence](notpaint/fxui/src/main/java/notpaint/imagepersistence/readme.md), denne delen tar for seg persistence.

*GameInfo*-klassen inneholder funksjonalitet for spill-informasjonen, enten et pågående spill eller et ferdig spill. Dette er også denne spill-informasjonen som blir serialisert til `JSON` ved bruk av biblioteket [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind). Dette gjøres ved å bruke *JacksonObjectMapperBuilder*-klassen, og å spesifisere attributter på felt som skal **serialiseres** og **deserialiseres**.  

*GameInfoPersistence* er en abstrakt klasse og håndterer lagring og lasting av *GameInfo*-klassen. 
*LocalGameInfoPersistence*-klassen er en implementasjon av *GameInfoPersistence* og håndterer lagring og lasting til lokal disk.
*RemoteGameInfoPersistence*-klassen er en implementasjon av *GameInfoPersistence* og håndterer lagring og lasting fra en ekstern server. 

Sammenheng mellom `JSON`-filer for *GameInfo* og bilder opprettholdes ved at de har samme filnavn (en UUID), med unntak av filendelsen (.json vs .png).

Programmet vårt bruker implisitt lagring, hvor data lagres i en forhåndsbestemt mappe som ikke er styrt av brukeren, og appen lagrer når det er fornuftig ut fra brukerens handlinger.
