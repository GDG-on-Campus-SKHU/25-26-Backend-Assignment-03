# JPA를 활용한 REST API 만들기
- JPA를 사용하여 원하는 주제로 CRUD 구현
- **도메인 간 연관관계**: `@OneToMany` / `@ManyToOne` 필수
- Postman 테스트 후 결과 첨부

---

# 제출 형식
- git clone 후, 본인 이름으로 된 폴더를 생성하여 과제를 구현합니다.
- 과제명: 25-26-Backend-Assignment-03_본인이름

- **PR 제출물**
  - 구현 코드 (예외 처리 꼼꼼히 하기)
  - Postman 테스트 실행 결과 사진 첨부
  - 설계한 ERD 사진 첨부
  - SQL 쿼리 로그를 PR에 명시해주세요

---

## CRUD 및 쿼리 로그 예시

| 기능       | 발생 쿼리 예시                                                   |
|----------|------------------------------------------------------------|
| 가수 생성    | insert into singer (name, debut_year) values ('뉴진스', 2022)  |
| 가수 삭제    | delete from singer where id = 1                            |
| 뮤직 생성    | insert into music(title, singer_id) values ('어텐션', 1)      |
| 뮤직 수정    | update music set title = '슈퍼내추럴', singer_id = 1 where id = 1 |
| 뮤직 삭제    | delete from music where id = 1;                            |
| 특정 뮤직 조회 | select * from music where id = 1;                          |
| 전체 뮤직 조회 | select * from music;                                       |

---

## 과제 마감 기한
- 최초 과제 제출: 11월 2일 (일) 23:59:59
- 리팩토링 제출: 11월 5일 (수) 23:59:59
