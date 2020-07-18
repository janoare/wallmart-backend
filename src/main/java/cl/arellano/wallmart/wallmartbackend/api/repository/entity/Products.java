package cl.arellano.wallmart.wallmartbackend.api.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "products")
public class Products {
    @Id
    @Field(name = "_id")
    private String id;
    @Field(name = "id")
    private Integer productId;
    private String brand;
    private String description;
    private String image;
    private Integer price;
}
