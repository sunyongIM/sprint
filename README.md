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
  - DTO
  
* 도서
  - Entity
    - 저자 Entity와 OneToMany 연관관계 (양방향)
    - 테이블 제약조건 설정
  - 기능
    - 

* 에러처리
  - DTO
    - 

* 캐시 
  - AuthorService에 적용 했으나 주석처리 돼있음
  - BookService에서 조회 시 첫번째 페이지를 캐싱하는 것 고려 중
  
* Response
  - 통일
    - ResponseEntity<HttpResponse>로 성공응답과 실패응답을 통일
