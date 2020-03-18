# OAuth2 Resource server

## Usage
### Secure API: http://localhost:9393/secure/user/info
Command:
```shell script
curl --location --request GET 'http://localhost:9393/secure/user/info?access_token=e751aae5-7d60-44d1-a796-f98f860b6b1f'
```

Sample response:
```json
{
    "authorities": [
        {
            "authority": "ROLE_CLIENT"
        }
    ],
    "details": {
        "remoteAddress": "0:0:0:0:0:0:0:1",
        "sessionId": null,
        "tokenValue": "e751aae5-7d60-44d1-a796-f98f860b6b1f",
        "tokenType": "Bearer",
        "decodedDetails": null
    },
    "authenticated": true,
    "userAuthentication": {
        "authorities": [
            {
                "authority": "ROLE_CLIENT"
            }
        ],
        "details": {
            "authorities": [
                {
                    "authority": "ROLE_CLIENT"
                }
            ],
            "details": {
                "remoteAddress": "127.0.0.1",
                "sessionId": null,
                "tokenValue": "e751aae5-7d60-44d1-a796-f98f860b6b1f",
                "tokenType": "Bearer",
                "decodedDetails": null
            },
            "authenticated": true,
            "userAuthentication": {
                "authorities": [
                    {
                        "authority": "ROLE_CLIENT"
                    }
                ],
                "details": {
                    "grant_type": "password",
                    "username": "user@email.com"
                },
                "authenticated": true,
                "principal": {
                    "password": null,
                    "username": "user@email.com",
                    "authorities": [
                        {
                            "authority": "ROLE_CLIENT"
                        }
                    ],
                    "accountNonExpired": true,
                    "accountNonLocked": true,
                    "credentialsNonExpired": true,
                    "enabled": true
                },
                "credentials": null,
                "name": "user@email.com"
            },
            "oauth2Request": {
                "clientId": "12345",
                "scope": [
                    "read",
                    "write",
                    "trust"
                ],
                "requestParameters": {
                    "grant_type": "password",
                    "username": "user@email.com"
                },
                "resourceIds": [],
                "authorities": [
                    {
                        "authority": "ROLE_CLIENT"
                    }
                ],
                "approved": true,
                "refresh": false,
                "redirectUri": null,
                "responseTypes": [],
                "extensions": {},
                "grantType": "password",
                "refreshTokenRequest": null
            },
            "principal": {
                "password": null,
                "username": "user@email.com",
                "authorities": [
                    {
                        "authority": "ROLE_CLIENT"
                    }
                ],
                "accountNonExpired": true,
                "accountNonLocked": true,
                "credentialsNonExpired": true,
                "enabled": true
            },
            "credentials": "",
            "clientOnly": false,
            "name": "user@email.com"
        },
        "authenticated": true,
        "principal": "user@email.com",
        "credentials": "N/A",
        "name": "user@email.com"
    },
    "principal": "user@email.com",
    "credentials": "",
    "clientOnly": false,
    "oauth2Request": {
        "clientId": null,
        "scope": [],
        "requestParameters": {},
        "resourceIds": [],
        "authorities": [],
        "approved": true,
        "refresh": false,
        "redirectUri": null,
        "responseTypes": [],
        "extensions": {},
        "refreshTokenRequest": null,
        "grantType": null
    },
    "name": "user@email.com"
}
```
### Non Secure API: http://localhost:9393/user/info
Command:
```shell script
curl --location --request GET 'http://localhost:9393/user/info'
```

Sample response:
```
OK
```