package uz.pdp.food_recipe_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.credentials.access-key}")
    private String accessKeyId;
    @Value("${aws.credentials.secret-key}")
    private String accessKeySecret;
    @Value("${aws.region}")
    private String region;

    @Bean
    public S3Client s3Client(){
        AwsBasicCredentials creds = AwsBasicCredentials.create(accessKeyId, accessKeySecret);
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(creds))
                .build();
    }
}
