Get Authentication Token

  http://localhost:9000/oauth2/authenticate?
          client_id={clientId}&
          redirect_uri={redirectUri}&
          response_type=code
          
          
Get Access Token

  http://localhost:9000/oauth2/access_token
    redirect_uri={redirectUri}&
    client_id={clientId}&
    code={authenticationCode}&
    client_secret=123456&
    grant_type=authorization_code
