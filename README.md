# sprint

### Server
- http://43.201.14.97

### Swagger
- http://43.201.14.97/swagger-ui/


### GitHub
- https://github.com/sunyongIM/sprint


## 🔑 프로젝트 백엔드 주요 기능
  
* 저자
  - Entity
    - 도서 Entity와 ManyToOne 연관관계 (양방향)
    - 테이블 제약조건 설정
  - 기능
    - 저자 등록
    - id로 저자 찾기
    - 이름으로 저자 찾기 (사용안함)
  
* 도서
  - Entity
    - 저자 Entity와 OneToMany 연관관계 (양방향)
    - 테이블 제약조건 설정
  - 기능
    - 도서 등록
    - 도서 전체조회 [페이징 적용]
    - 도서 전체조회 및 정렬 [페이징 적용] (사용안함)

* 에러처리
  - Validation
    - RequestDTO를 사용한 Validation 및 예외메시지 관리
    - Setter 대신 Builder 패턴을 사용해 객체의 안정성 증대
  - Exception
    - Enum을 사용한 프로젝트 전체 Exception 관리
    - RestControllerAdvice외에 ExceptionHandler를 사용해 다양한 예외 분류
    - ResponseEntity<HttpResponse>를 이용한 응답형식 통일

* 캐시 
  - AuthorService에 적용 했으나 주석처리 되어있음
  - BookService에서 조회 시 첫번째 페이지를 캐싱하는 것 고려 중
  
* Response
  - 통일
    - ResponseEntity<HttpResponse>로 성공응답과 실패응답/Exception을 통일
  
