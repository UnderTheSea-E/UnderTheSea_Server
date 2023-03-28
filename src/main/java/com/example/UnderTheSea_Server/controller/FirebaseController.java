//package com.example.UnderTheSea_Server.controller;
//
//import com.example.UnderTheSea_Server.config.BaseException;
//import com.example.UnderTheSea_Server.config.BaseResponse;
//import com.example.UnderTheSea_Server.model.PostGUserReq;
//import com.example.UnderTheSea_Server.model.PostUserRes;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.auth.FirebaseAuth;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletResponse;
//import java.io.FileInputStream;
//@Configuration
//@RestController
//@RequiredArgsConstructor
//public class FirebaseController{
//    @PostConstruct
//    public void init(){
//        try{
//            FileInputStream serviceAccount =
//                    new FileInputStream("src/main/resources/underthesea-1672810871080-firebase-adminsdk-owg0r-0ca891def5.json");
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//            FirebaseApp.initializeApp(options);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
