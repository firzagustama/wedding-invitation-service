# API Spec
This is the api spec of Wedding Invitation API.

All the `Request` must be wrapped with :
- Method : GET / POST
- Header :
    - Content-Type : application/json
    - Accept : application/json
- Body

| Parameter | Type | Description |
| --------- | ---- | ----------- |
| data | Object | `Required` request data that will be detailed in each endpoint |

All the `Response` will be with HTTP Status 200 and wrapped with :

Success :
```json
{
  "success" : true,
  "responseCode": null,
  "responseMessage": null,
  "data": {},
}
```

Failed :
```json
{
  "success" : false,
  "responseCode": 3004,
  "responseMessage": "Username or Email already exists",
  "data" : null,
}
```

- HTTP Status always `200`
- Api call status will be presented in `success`
- Error Codes mapping will be presented in `errorCode` and `errorMessage`
- Response object will be presented in `data`

## Error Codes

| Code | Message | Description |
| --- |---------| --- |
| `1000` | Success | |
| `2000` | Parameter Illegal | |
| `3000` | User not found ||
| `5000` | Internal Error ||

## User Detail
GET `/user/{id}`


Request : `/user/10503633-da77-4051-9361-bdc476887f0c`

| Path Variable | Type | Description                                    |
|---------------| --- |------------------------------------------------|
| id            | String | `Required` userId (UUID)                       ||

Response :

| Parameter | Type | Status | Description                                                                          |
|-----------| --- | --- |--------------------------------------------------------------------------------------|
| title     | String | `Required` | `Mr.` for married/single man</br>`Mrs.` for married woman</br>`Ms.` for single woman |
| name      | String | `Required` | User full name |
| attend    | Boolean | `Optional` | `null` if user not filled RSVP</br>`true` if user filled RSVP and will attend</br>`false` if user filled RSVP and not attend

```json
{
  "success": true,
  "responseCode": "1000",
  "responseMessage": "Success",
  "data": {
    "title": "Mr.",
    "name": "Firza Gustama",
    "attend": true
  }
}
```

## Wish
POST `/wish/create`

User create wish

Request :

| Parameter   | Type | Status | Description |
| --- | --- | --- | --- |
| userId | String (UUID) | `Required` | User Id |
| wish | String | `Required` | User wish |

```json
{
  "data": {
    "userId": "10503633-da77-4051-9361-bdc476887f0c",
    "wish": "Hope you well"
  }
}
```

Response :

| Parameter | Type | Status | Description |
| --- | --- | --- | --- |
| title | String | `Required` | User Title |
| name | String | `Required` | User Full name |
| wish | String | `Required` | User Wish |

```json
{
  "success": true,
  "responseCode": "1000",
  "responseMessage": "Success",
  "data": {
    "title": "Mr.",
    "name": "Firza Gustama",
    "wish": "Hope you well"
  }
}
```

## Wish List Pagination
POST `/wish/getList`

Request :

| Parameter | Type | Status | Description                                    |
|---|---|---|------------------------------------------------|
| pageNo | Integer | `Optional` | Requested page number from 1 to end `default 1` |
| pageSize | Integer | `Optional` | Requested data per page `default 10`           |

```json
{
  "data": {
    "pageNo": 1,
    "pageSize": 10
  }
}
```

Response :

| Parameter | Type                 | Status | Description |
| --- |----------------------| --- | --- |
| info | PaginationResponse   | `Required` | Pagging info |
| wishes | List\<WishResponse\> | `Required` | Wish list |

</br></br>PaginationResponse

| Parameter | Type | Status | Description |
| --- | --- | --- | --- |
| prevPageNo | Integer | `Optional` | `null` if there are no previous page |
| currPageNo | Integer | `Required` | Current page number |
| nextPageNo | Integer | `Optional` | `null` if there are no next page |
| totalData | Integer | `Required` | Total of all data |
| totalPage | Integer | `Required` | Total of all page |
| pageSize | Integer | `Required` | Number of data per page |

</br></br>WishResponse :

| Parameter | Type | Status | Description |
| --- | --- | --- | --- |
| title | String | `Required` | User title |
| name | String | `Required` | User name |
| wish | String | `Required` | User wish |

```json
{
  "success": true,
  "responseCode": "1000",
  "responseMessage": "Success",
  "data": {
    "info": {
      "prevPageNo": null,
      "currPageNo": 1,
      "nextPageNo": null,
      "totalData": 4,
      "totalPage": 1,
      "pageSize": 10
    },
    "wishes": [
      {
        "title": "Mr.",
        "name": "Firza Gustama",
        "wish": "Wish 0"
      },
      {
        "title": "Mr.",
        "name": "Gilang Ramadhan",
        "wish": "Wish 1"
      },
      {
        "title": "Mr.",
        "name": "Fauzy Iskandar",
        "wish": "Wish 2"
      },
      {
        "title": "Mrs.",
        "name": "Silvi Absharina",
        "wish": "Wish 3"
      }
    ]
  }
}
```


## Create RSVP
POST `/rsvp/create`

Request :

| Parameter | Type | Status | Description |
| --- | --- |------------| --- |
| userId | String (UUID) | `Required` | User Id |
| rsvp | Boolean | `Required` | Will attend or not |
| pax | Integer | `Optional` | `rsvp is true` then value must between 1 or 2</br> `rsvp is false` then value can be null |

```json
{
  "data": {
    "userId": "10503633-da77-4051-9361-bdc476887f0c",
    "rsvp": true,
    "pax": 2
  }
}
```

Response :
```json
{
  "success": true,
  "responseCode": "1000",
  "responseMessage": "Success",
  "data": {}
}
```