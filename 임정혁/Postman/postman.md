## 전공 API

###  POST - 전공 생성

`http://localhost:8080/majors`

```
{
  "name": "감귤 따기 전공"
}
```

<img width="1253" height="601" alt="image" src="https://github.com/user-attachments/assets/ddb4636c-3331-4a60-9da4-45723fd95c67" />

### DELETE - 전공 삭제 API

`DELETE http://localhost:8080/majors/{majorId}`

<img width="1258" height="512" alt="image" src="https://github.com/user-attachments/assets/68b013d3-d626-4755-ac77-276ed447a921" />

## 교수님 CRUD API

### POST -   교수님 등록 

`http://localhost:8080/professors`

```
{
  "majorId": 4,
  "name": "귤농부",
  "email": "notorange.naver.com"
}
```

<img width="1262" height="655" alt="image" src="https://github.com/user-attachments/assets/26b6dcd1-9203-4437-b859-1fe2dc8e9225" />

### GET - 특정 교수 조회

`http://localhost:8080/professors/{professorId}`


<img width="1249" height="657" alt="image" src="https://github.com/user-attachments/assets/62889d01-9c71-45ef-a7ba-330efc50346e" />

### GET - 교수 전체 조회

`GET http://localhost:8080/professors`

<img width="1260" height="853" alt="image" src="https://github.com/user-attachments/assets/eeeb0721-a3e1-478c-aa99-573c0fe434da" />


### PATCH - 교수 정보 수정

`http://localhost:8080/professors/{professorId}`

```
{
  "majorId": 2,
  "name": "엘런 튜링"
}
```

<img width="1260" height="639" alt="image" src="https://github.com/user-attachments/assets/cd121e94-94e4-4c67-a983-a7668f6e0b7c" />

<img width="1251" height="830" alt="image" src="https://github.com/user-attachments/assets/707e3725-0b93-472b-96e2-125dd8c12b02" />


### DELETE - 교수님 삭제

`http://localhost:8080/professors/{professorId}`

<img width="1247" height="611" alt="image" src="https://github.com/user-attachments/assets/ffa3589a-40db-4d42-867d-5988548129fd" />

