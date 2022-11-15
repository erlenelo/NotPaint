# NotPaint REST API
## Table of contents
* [GET /allGameInfos](#GET-allGameInfos)
* [PUT /saveGameInfo](#PUT-saveGameInfo)
* [GET /lock](#GET-lock)
* [POST /lock](#POST-lock)
* [DELETE /lock](#DELETE-lock)
* [GET /image](#GET-image)
* [PUT /image](#PUT-image)
### /allGameInfos
Methods:

* <a name="GET-allGameInfos"></a>GET - retrieves a list of all GameInfos from the server
  * URI: host:port/allGameInfos <localhost:8080/allGameInfos>
  * parameters: 
    * Header: `Accept: application/json`
  * returns: a list of all `GameInfo`s in JSON format

Example return with type comments (list with one GameInfo):
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
Methods:

* <a name="PUT-saveGameInfo"></a>PUT - saves a new or updates an existing `GameInfo` on the server
  * URI: host:port/saveGameInfo <localhost:8080/saveGameInfo>
  * parameters:
    * Header: `Content-Type: application/json`
    * body: a `GameInfo` object in JSON format (like the  one returned by `GET /allGameInfos`)
  * returns: `200 OK` if successful

### /lock

Methods:
* <a name="GET-lock"></a>GET - check if a `GameInfo` is locked
  * URI: host:port/lock <localhost:8080/lock?uuid={uuid}>
  * parameters:
    * Query: `uuid` - the UUID of the `GameInfo` to check
  * returns: 
  ```json
  true // if locked
  ```
  ```json
  false // if not locked
  ```

* <a name="POST-lock"></a>POST - request a lock on a `GameInfo`
  * URI: host:port/lock <localhost:8080/lock?uuid={uuid}>
  * parameters:
    * Query: `uuid` - the UUID of the `GameInfo` to lock
  * returns: 
  ```json
  true // if locking was successful
  ```
  ```json
  false // if GameInfo is already locked
  ```
* <a name="DELETE-lock"></a>DELETE - release a lock on a `GameInfo`
  * URI: host:port/lock <localhost:8080/lock?uuid={uuid}>
  * parameters:
    * Query: `uuid` - the UUID of the `GameInfo` to unlock
  * returns: `200 OK` if successful

### /image
Methods:

* <a name="GET-image"></a>GET - retrieve an image from the server
  * URI: host:port/image <localhost:8080/image?uuid={uuid}>
  * parameters:
    * Query: `uuid` - the UUID of the `GameInfo` to retrieve the image from
  * returns: the image as a byte array in PNG format
* <a name="PUT-image"></a>PUT - save or update an existing image on the server
  * URI: host:port/image <localhost:8080/image?uuid={uuid}>
  * parameters:
    * Query: `uuid` - the UUID of the `GameInfo` to save the image to
    * body: the image as a byte array in PNG format
  * returns: `200 OK` if successful


