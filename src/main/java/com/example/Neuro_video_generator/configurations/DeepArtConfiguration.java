//package com.example.Neuro_video_generator.configurations;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.opensdk.config.ConnectionConfiguration;
//import com.amazonaws.opensdk.config.TimeoutConfiguration;
//import com.deeparteffects.sdk.java.DeepArtEffectsClient;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//@Data
//@Configuration
//public class DeepArtConfiguration {
//    @Value("{deep.art.api.key}")
//    private String apiKey;
//    @Value("{deep.art.acces.key}")
//    private String accesKey;
//    @Value("{deep.art.secret.key}")
//    private String secretKey;
//    @Bean
//    public DeepArtEffectsClient configureDeepArtEffectsClient(){
//        BasicAWSCredentials credentials = new BasicAWSCredentials(accesKey, secretKey);
//        DeepArtEffectsClient deepArtEffectsClient = DeepArtEffectsClient
//                .builder()
//                .connectionConfiguration(
//                        new ConnectionConfiguration().maxConnections(100)
//                                .connectionMaxIdleMillis(1000))
//                .timeoutConfiguration(
//                        new TimeoutConfiguration().httpRequestTimeout(3000)
//                                .totalExecutionTimeout(10000)
//                                .socketTimeout(2000))
//                .apiKey(apiKey)
//                .iamCredentials(new AWSStaticCredentialsProvider(credentials))
//                .build();
//        return deepArtEffectsClient;
//    }
//}
