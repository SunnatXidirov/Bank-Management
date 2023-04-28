package uz.pdp.onlinebanking.repository;

import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.onlinebanking.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
