package polyclinic.srs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import polyclinic.srs.entities.Items;

public interface ItemRepository extends JpaRepository<Items, Long> {
}
