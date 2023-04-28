package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onlinebanking.entity.Conversion;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion,Integer> {

}
