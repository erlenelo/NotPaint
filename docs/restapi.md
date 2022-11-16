# NotPaint REST API
## Innholdsfortegnelse
* [GET /allGameInfos](#GET-allGameInfos)
* [PUT /saveGameInfo](#PUT-saveGameInfo)
* [GET /lock](#GET-lock)
* [POST /lock](#POST-lock)
* [DELETE /lock](#DELETE-lock)
* [GET /image](#GET-image)
* [PUT /image](#PUT-image)
### /allGameInfos
Metoder:

* <a name="GET-allGameInfos"></a>GET - henter en liste med alle `GameInfo` fra tjeneren
  * URI: vert:port/allGameInfos <localhost:8080/allGameInfos>
  * parametre: 
    * Header: `Accept: application/json`
  * returnerer: en liste med alle `GameInfo` i JSON format

Eksempel på liste returnert (list med en `GameInfo`):
```json
[
	{
		"maxIterations": 6, // int
		"secondsPerRound": 6, // int
		"newWordEachRound": true, // bool
		"currentIterations": 6, // int
		"words": [ // List<String>
			"Committee",
			"Bath",
			"Article",
			"Love",
			"Length",
			"Employee"
		],
		"lastEditor": "UnknownEditor", // String
		"lastEditTime": "2022-10-27T12:16:31.873+00:00", // java.util.Date
		"uuid": "21323b4b-c9e0-4c83-b4ce-d612e07eab8a", // java.util.UUID
	}
]
```
### /saveGameInfo
Metoder:

* <a name="PUT-saveGameInfo"></a>PUT - lagrer en ny eller oppdaterer en eksisterende `GameInfo` på tjeneren
  * URI: vert:port/saveGameInfo <localhost:8080/saveGameInfo>
  * parametre:
    * Header: `Content-Type: application/json`
    * body: et `GameInfo` objekt i JSON-format (likt den returnert av `GET /allGameInfos`)
  * returnerer: `200 OK` ved suksess

### /lock

Metoder:
* <a name="GET-lock"></a>GET - sjekker om en `GameInfo` er låst
  * URI: vert:port/lock <localhost:8080/lock?uuid={uuid}>
  * parametre:
    * Query: `uuid` - UUID til `GameInfo` som skal sjekkes
  * returnerer: 
  ```json
  true // hvis låst
  ```
  ```json
  false // hvis ikke låst
  ```

* <a name="POST-lock"></a>POST - be om en lås på en `GameInfo`
  * URI: vert:port/lock <localhost:8080/lock?uuid={uuid}>
  * parametre:
    * Query: `uuid` - UUID til `GameInfo` som skal låses
  * returnerer: 
  ```json
  true // hvis låsing var vellykket
  ```
  ```json
  false // hvis GameInfo allerede var låst
  ```
* <a name="DELETE-lock"></a>DELETE - fjern en lås fra en `GameInfo`
  * URI: vert:port/lock <localhost:8080/lock?uuid={uuid}>
  * parametre:
    * Query: `uuid` - UUID til `GameInfo` som skal låses opp
  * returnerer: `200 OK` ved suksess

### /image
Metoder:

* <a name="GET-image"></a>GET - hent et bilde fra tjeneren
  * URI: vert:port/image <localhost:8080/image?uuid={uuid}>
  * parametre:
    * Query: `uuid` - UUID til `GameInfo` bildet skal hentes fra
  * returnerer: bildet som en byte-tabell i PNG-format

* <a name="PUT-image"></a>PUT - lagre et nytt eller oppdater et eksisterende bilde på tjeneren
  * URI: vert:port/image <localhost:8080/image?uuid={uuid}>
  * parametre:
    * Query: `uuid` - UUID til `GameInfo` som bildet skal lagres for
    * body: bildet som en byte-tabell i PNG-format
  * returnerer: `200 OK` ved suksess


