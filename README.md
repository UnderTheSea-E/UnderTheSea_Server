#### ✅You can use our distributed server's ip address, 34.22.70.141:3000 directly.

## Other ways to run
### <1>
### 0️⃣ Enter command, 'git clone https://github.com/UnderTheSea-E/UnderTheSea_Server/' in your google cloud server.
### 1️⃣ Move to UnderTheSea_Server directory and enter command 'sudo apt install default-jdk' and 'sudo apt install gradle'.
### 2️⃣ Download key files in shared google drive folder, UnderTheSea(.gitignore files) - Google Solution Challenge 2023. (We wrote google drive link in answer to the question 'Share your GitHub Project Link' in Google Developer Student Clubs Solution Challenge 2023 - Submission Form)
### 3️⃣ Put 'google-services.json', 'application.yml' and 'underthesea-1672810871080-firebase-adminsdk-owg0r-f3f1d6a388' files in src/main/resources.
### 4️⃣ Enter command, './gradlew clean build' and when it finish building, enter command 'java -jar build/libs/UnderTheSea_Server-0.0.1-SNAPSHOT'

### <2>
### 0️⃣ Upload 'UnderTheSea_Server-0.0.1-SNAPSHOT' in your cloud server and enter command, 'java -jar build/libs/UnderTheSea_Server-0.0.1-SNAPSHOT'. The file is also uploaded in shared google drive folder, UnderTheSea(.gitignore files) - Google Solution Challenge 2023.

#### If errors related with mysql server happen, please change ip address of mysql server into your mysql server address in 'application.yml'. Enter 'create underthesea;' to make schema in your mysql environment.
