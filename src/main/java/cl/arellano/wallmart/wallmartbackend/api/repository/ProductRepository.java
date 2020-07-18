package cl.arellano.wallmart.wallmartbackend.api.repository;

import cl.arellano.wallmart.wallmartbackend.api.repository.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Products, String> {
    Optional<Products> findByProductId(Integer productId);

    @Query("{brand: { $regex: ?0}}")
    List<Products> findByBrand(String brand);

    @Query("{description: { $regex: ?0}}")
    List<Products> findByDescription(String description);
}

