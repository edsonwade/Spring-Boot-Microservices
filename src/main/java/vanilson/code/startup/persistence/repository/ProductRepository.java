package vanilson.code.startup.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vanilson.code.startup.persistence.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
