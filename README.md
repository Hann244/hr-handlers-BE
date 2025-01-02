## 👋인사잘해팀 : HR(human resources)
### 프로젝트 소개
<img width="100%" alt="2024-12-12_3 23 22" src="https://github.com/user-attachments/assets/3e7c4c4a-80fc-41b7-957d-63eea3b659eb" />

> 인사 및 근태 관리를 통합한 사내 HR 프로그램!

본 프로젝트는 사내 인사 및 근태 관리를 효율화하기 위해 개발된 HR 시스템입니다.
직원들은 사내 채팅, 휴가 신청, 출퇴근 기록, 게시판 활용, 급여 확인 등을 하나의 플랫폼에서 손쉽게 처리할 수 있습니다.
관리자들은 직원 정보 및 근태를 체계적으로 관리할 수 있어 업무 생산성이 향상됩니다.

[인사잘해](http://34.47.90.224:3000/)

**접속 가능한 계정**
- 이상해 사원
  - **ID** : 20241216
  - **PW** : 123123123

---

### API 문서
**Swagger-UI** : http://34.47.90.224:8080/swagger-ui/index.html

---

### 개발 기간
- 2024.11.18 ~ 2024.12.13

---

### 팀원 소개
| 이름 (Name) | 역할 (Role)  | 담당 도메인 (Domain) | 
  |-----------|------------|-----------------|
| 정현        | 팀장 (Leader) | 사원              | 
| 한현        | 팀원 (Member) | 메신저             | 
| 이서율       | 팀원 (Member) | 게시판             |  
| 송진욱       | 팀원 (Member) | 근태/휴가/일정        | 
| 김준수       | 팀원 (Member) | 급여              | 

---

### 기술 스택
- **Frontend** : React, Bootstrap, HTML, CSS, Javascript
- **Backend** : Java, JPA, Spring Boot, Spring Security, JWT, Websocket, STOMP, MyBatis, QueryDSL
- **Storage** : AWS S3, MariaDB
- **Deployment**: GCP, Nginx

---

### 와이어프레임
![와이어프레임](https://github.com/user-attachments/assets/794f6b45-ce24-4aec-a9ec-80a0aacc2d6e)

---

### ERD
![인사잘해 ERD ](https://github.com/user-attachments/assets/0fa349fb-1ecf-4213-a2af-6e6b18d44a8f)

---

### 📌주요 기능
##### 홈
![hr-handlers_11](https://github.com/user-attachments/assets/5ee592d2-935d-4a09-969a-02d05515ed92)

##### 사원
![hr-handlers_12](https://github.com/user-attachments/assets/2da4cc3d-29e0-43c5-841a-5f62b1b595e0)
![hr-handlers_13](https://github.com/user-attachments/assets/1ffe06a6-c8d5-402c-bb87-dc622ab4a86d)

##### 근태
![hr-handlers_15](https://github.com/user-attachments/assets/061f39a5-bfac-4a58-aed8-38c89a9a8d55)

##### 일정
![hr-handlers_17](https://github.com/user-attachments/assets/cc524a52-2c4d-41c3-aae0-476207703b8e)

##### 휴가
![hr-handlers_16](https://github.com/user-attachments/assets/06e589ec-aee9-435e-8abb-84e28501d33b)

##### 게시판
![hr-handlers_18](https://github.com/user-attachments/assets/279b9f35-94d6-48f5-af77-b60ece988d79)
![hr-handlers_19](https://github.com/user-attachments/assets/9f939a35-4861-405e-969a-d63ad1a46232)
![hr-handlers_20](https://github.com/user-attachments/assets/39a6ced3-f0b8-4b3b-848f-490daac23145)

##### 메신저
![hr-handlers_14](https://github.com/user-attachments/assets/2c7237c1-c7ac-4bf4-9a01-a64613d4b730)

##### 급여
![hr-handlers_21](https://github.com/user-attachments/assets/197ff5ec-537f-431a-ad5f-717bce011e6f)
![hr-handlers_22](https://github.com/user-attachments/assets/fde6bdcb-5544-4bfe-a64f-c7c77439e05c)

---

### 🚨트러블슈팅
#### 출근/퇴근 시간이 서버시간과 다르게 9시간전으로 저장되는 이슈 
**시도해본 것**
1. Nginx 서버 시간 UTC -> KST 타임존 변경 
  
   - 서버 시간은 변경 되었지만, 동일한 문제 발생

2. 데이터 베이스 시간 변경 
   - 변경 시 오류가 없던 다른 도메인 시간대가 9시간 전으로 변경 되는 다른 문제 발생

**해결 방법**

시간대를 저장하는 Service 부분에 명시적으로 한국시간을 설정해주는 코드로 변경
`LocalDateTime.now(ZoneId.of("Asia/Seoul"))`
