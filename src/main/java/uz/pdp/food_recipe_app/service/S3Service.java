package uz.pdp.food_recipe_app.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadImage(MultipartFile file);
    byte[] getImage(String url);

}
