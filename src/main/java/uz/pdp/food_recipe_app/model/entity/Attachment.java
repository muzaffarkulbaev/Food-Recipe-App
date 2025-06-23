package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.food_recipe_app.model.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends BaseEntity {

    private String filename;
    private String s3Key;
    private String contentType;
    private long size;

    private String url;
}
