# OAuth2 JDBC Authorization server

## Pre requisite
* Database: PostgreSQL
* Connection: see the configuration in file: 'src/main/resources/application.yml'

## Data
### Client
* client_id: 12345
* secret: password
### User
* username: user@email.com
* password: password

## Usage
### API: http://localhost:9292/oauth/token
Command:
* Using base64 of 'username:password'
```shell script
curl --location --request POST 'http://localhost:9292/oauth/token?grant_type=password&username=user@email.com&password=password' \
--header 'Authorization: Basic MTIzNDU6cGFzc3dvcmQ='
```
or
* Plain client_id & password
```shell script
curl --location --request POST 'http://localhost:9292/oauth/token?grant_type=password&username=user@email.com&password=password' \
-u 12345:password
```
Sample response:
```json
{
  "access_token":"68763a89-996d-4494-934e-db6ba1b063f2",
  "token_type":"bearer",
  "refresh_token":"0b66020e-5f8f-4c11-908c-21e31cd5b741",
  "expires_in":45,
  "scope":"read write trust"
}
```