package vanilson.code.startup.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vanilson.code.startup.persistence.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
