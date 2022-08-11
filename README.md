# blog-search
## Overview



## Development
### Overall
|folder| description  |
|---|---|
|backend|  |
|web-client| SPA  |



### Backend
```
Java 11
Spring boot 2.7.2
Gradle
H2 database
JWT

```

### Backend Module
|Artifact|Description|
|---|---|
|blog-service| Main Spring Boot Web Application |
| user-auth  | User Authentication module using JWT |
|  trends    | Keywords Ranking service module |

### Frontend
```
Vue.js
Bootstrap
Webpack
```



## Getting Started  (Build & Run)

### Build
#### build frontend
enter in to web-client 
```
>	npm run build
```

#### build backend
enter in to backend
```
>	./gradlew bootRun
```

### Run
execute jar file
```
>	java -jar blog-service-0.0.1-SNAPSHOT.jar
```

### Check
Access to <http://localhost:8080>





## Feature
### LongIn
* 로그인 한 사용자만 블로그 검색서비스를 사용할 수 있다.
<img width="1200" alt="스크린샷 2022-08-11 오후 5 50 35" src="https://user-images.githubusercontent.com/8025470/184114690-6691ee64-834d-466a-a1ae-3ed7b436e5d6.png">

### SignIn
<img width="1200" alt="스크린샷 2022-08-11 오후 5 52 03" src="https://user-images.githubusercontent.com/8025470/184114721-7168602b-7e21-4a4e-a462-f22568dbf727.png">

### Search Dashboard
* 검색하기 전 화면은 인기검색어 리스트와 사용자의 즐겨찾기 목록을 보여준다.
* 검색어입력 후, Enter를 눌러 블로그 검색을 할 수있다.
* 페이지를 선택하여 해당 페이지 결과를 조회할 수 있다.
* 검색 옵션으로 ACCURACY(정확도순), RECENCY(최신순)을 선택할 수 있다.
* 검색 결과의 블로그 항목은 하트를 눌러 즐겨찾기에 추가하거나 삭제할 수 있다.

<img width="1200" alt="스크린샷 2022-08-11 오후 5 58 05" src="https://user-images.githubusercontent.com/8025470/184114842-a25372ee-41f7-45d6-ba17-294dc901ae52.png">

### Favorite Dashboard
* 상단의 하트 버튼을 누르면 즐겨찾기 화면으로 이동한다.
* 사용자가 검색결과에서 즐겨찾기에 추가한 항목을 여기서 확인할 수 있다.
* 한번에 최대 10개씩 보여주며, "Fetch more" 버튼을 눌러 10개씩 더 조회할 수 있다.


<img width="1200" alt="스크린샷 2022-08-11 오후 5 59 37" src="https://user-images.githubusercontent.com/8025470/184114877-7f1db98c-4e02-4a3e-97f2-366bd9924ad7.png">

### Popular Dashboard
<img width="1200" alt="스크린샷 2022-08-11 오후 6 05 01" src="https://user-images.githubusercontent.com/8025470/184114906-0790bc57-d696-466e-9f15-dcf2071772c4.png">
