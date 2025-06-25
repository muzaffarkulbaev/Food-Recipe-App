package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import uz.pdp.food_recipe_app.service.S3Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    @SneakyThrows
    @Override
    public String uploadImage(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String key = System.currentTimeMillis() + "_" + filename;
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        try (InputStream inputStream = file.getInputStream()){
            s3Client.putObject(
                    request,
                    RequestBody.fromInputStream(inputStream, file.getSize())
            );
        }

        return key;
    }

    @SneakyThrows
    @Override
    public byte[] getImage(String url) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(url)
                .build();

        try (ResponseInputStream<GetObjectResponse> inputStream = s3Client.getObject(request)){
            return inputStream.readAllBytes();
        }
    }
}
