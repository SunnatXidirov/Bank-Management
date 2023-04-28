package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onlinebanking.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
