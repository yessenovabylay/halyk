package polyclinic.srs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polyclinic.srs.entities.Payment;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
