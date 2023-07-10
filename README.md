## 소개
### 해양 생물 보호 목적으로, 플라스틱 사용을 줄이기 위한 환경 실천 계획을 세울 수 있는 앱입니다.
### 혼자서 실천이 어려운 다수를 고려해 친구, 가족 등 좋아하는 사람들과 함께 진행하도록 설계했습니다.
### 제로웨이스트 샵, 기업의 환경 이벤트 등 쉽고 재미있게 실천할 수 있는 환경 활동에 대한 정보를 제공하여 놀이 문화로 일상 속에서 자주 실천하도록 하는 것이 목표입니다.

## 개발 툴
### Spring, MySQL, GCP

## How to run
### <1>
### 0️⃣ Enter command, 'git clone https://github.com/UnderTheSea-E/UnderTheSea_Server/' in your google cloud server.
### 1️⃣ Move to UnderTheSea_Server directory and enter command 'sudo apt install default-jdk' and 'sudo apt install gradle'.
### 2️⃣ Download key files in shared google drive folder, UnderTheSea(.gitignore files) - Google Solution Challenge 2023. (We wrote google drive link in answer to the question 'Share your GitHub Project Link' in Google Developer Student Clubs Solution Challenge 2023 - Submission Form)
### 3️⃣ Put 'google-services.json', 'application.yml' and 'underthesea-1672810871080-firebase-adminsdk-owg0r-f3f1d6a388' files in src/main/resources.
### 4️⃣ Enter command, './gradlew clean build' and when it finish building, enter command 'java -jar build/libs/UnderTheSea_Server-0.0.1-SNAPSHOT'

### <2>
### 0️⃣ Upload 'UnderTheSea_Server-0.0.1-SNAPSHOT' in your cloud server and enter command, 'java -jar build/libs/UnderTheSea_Server-0.0.1-SNAPSHOT'. The file is also uploaded in shared google drive folder, UnderTheSea(.gitignore files) - Google Solution Challenge 2023.

#### If errors related with mysql server happen, please change ip address of mysql server into your mysql server address in 'application.yml'. Enter 'create underthesea;' to make schema in your mysql environment.
