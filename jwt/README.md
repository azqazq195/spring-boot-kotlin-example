TODO

- [x] refresh token 구현
- [x] refresh token 을 이용한 access token 재발급 구현
- [x] JwtStorage Cache Memory 로 구현
- [ ] 코드 정리
- [ ] token expired time 수정
- [ ] test 코드 작성
- [ ] rest docs 작성
- [ ] 권한 구현
- [ ] redis restTemplate 분리해서 얻는 이점은? (blacklist)

# Exception Handler

Error Code 및 ApiException 을 정의해놓고 ApiException 을 상속받아 NotFoundUserException 을 새로 만드는 이유는?

- NotFoundUserException 발생시 로직을 분리하여 확장할 수 있다.

# JWT

Refresh Token의 한계
Access Token을 즉시 차단할 방법의 부재
아무리 Refresh Token이 Access Token의 유효기간을 짧게 만들어 줄 수 있다고 하더라도, 탈취된 Access Token이 유효한 그 짧은 시간 동안에 악용될 수 있다는 위험성이 존재한다.

Refresh Token 그 자체를 탈취 당할 가능성
해커에게 Refresh Token 자체를 탈취 당하면 해커는 마음껏 Access Token을 발행할 수 있다. 서버 DB에서 Refresh Token을 저장해 직접 추적하는 방법을 사용하면 조금이나마 피해를 줄일 수
있겠지만, 피해가 확인되기 전까진 탈취 여부를 알 방법이 없다.

RTR을 사용한다면 Refresh Token을 1회 사용하고 버리게 되어 더 안전하게 사용할 수 있지만, 사용하지 않은 Refresh Token을 탈취당하면 해커는 1회 한정으로 Access Token을 발급받을 수
있다.

즉, 이러나 저러나 Refresh Token을 탈취 당할 위험성이 존재한다. 따라서 클라이언트는 XSS, CSRF 공격으로부터 Refresh Token이 탈취되지 않도록 안전하게 보관해야한다.
https://apim.docs.wso2.com/en/3.2.0/learn/api-security/oauth2/grant-types/refresh-token-grant/
https://pragmaticwebsecurity.com/articles/oauthoidc/refresh-token-protection-implications.html
