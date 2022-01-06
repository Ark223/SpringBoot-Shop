package pl.edu.pbs.sklep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pbs.sklep.model.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
