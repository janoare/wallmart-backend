package cl.arellano.wallmart.wallmartbackend.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Integer productId;
    private String brand;
    private String description;
    private String image;
    private Integer price;
}
