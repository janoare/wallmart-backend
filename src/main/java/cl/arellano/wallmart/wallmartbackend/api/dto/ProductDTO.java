package cl.arellano.wallmart.wallmartbackend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String brand;
    private String description;
    private String image;
    private Integer price;
    private Integer discountPrice;
}
