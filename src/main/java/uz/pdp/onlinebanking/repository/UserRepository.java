package uz.pdp.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.onlinebanking.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByLogin(String userName);

}
